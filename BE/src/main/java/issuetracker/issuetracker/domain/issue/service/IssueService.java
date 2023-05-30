package issuetracker.issuetracker.domain.issue.service;

import issuetracker.issuetracker.domain.issue.Assignee;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.issue.IssueController;
import issuetracker.issuetracker.domain.issue.dto.IssueDetailDTO;
import issuetracker.issuetracker.domain.issue.dto.IssueDetailLabelDto;
import issuetracker.issuetracker.domain.issue.dto.Request.IssueTitleDTO;
import issuetracker.issuetracker.domain.issue.dto.Request.PostingIssueDTO;
import issuetracker.issuetracker.domain.issue.repository.IssueRepository;
import issuetracker.issuetracker.domain.label.Label;
import issuetracker.issuetracker.domain.label.LabelRepository;
import issuetracker.issuetracker.domain.label.LabelService;
import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.milestone.MilestoneService;
import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.user.Member;
import issuetracker.issuetracker.domain.user.MemberRepository;
import issuetracker.issuetracker.domain.user.MemberService;
import issuetracker.issuetracker.domain.user.dto.AssigneeDTO;
import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final MilestoneService mileStoneService;
    private final LabelRepository labelRepository;
    private final MemberService userService;
    private final MemberRepository memberRepository;
    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    public Long save(PostingIssueDTO postingIssueDTO) {
        log.debug("postingIssueDTO = {}", postingIssueDTO);
        Issue issue = Issue.create(postingIssueDTO);
        //TODO 로그인한 사용자 ID 차후 수정필요
        log.debug("Issue DTO 로 issue생성 = {}", issue);
        Issue save = issueRepository.save(issue);
        log.debug("save = {}", save);
        return save.getId();
    }

    public IssueDetailDTO findByIssueDetailPage(Long id) { // 이슈 id
        Issue issue = issueRepository.findById(id).get();
        log.debug("byId = {}", issue);
        log.debug("issue = {}", issue);
        //라벨 n관계
        List<LabelDTO> labels = getLabelsByIssueId(id);

        //담당자 n관계
        List<Assignee> assigneeList = issue.getAssignees();
        List<AssigneeDTO> assignees = new ArrayList<>();

        for (Assignee assignee : assigneeList) {
            Long labelId = assignee.getId();
            Member member = memberRepository.findById(labelId).get();
            log.debug("member ={}", member);
            AssigneeDTO assigneeDTO = AssigneeDTO.builder()
                    .id(member.getMemberId())
                    .name(member.getMemberName())
                    .profileUrl(member.getProfileUrl())
                    .build();
            assignees.add(assigneeDTO);
        }

        //작성자 1관계
        log.debug("이슈 작성자 번호 = {}", issue.getAuthor());
        AuthorDTO author = userService.findByAuthor(issue.getAuthor());
        log.debug("author = {}", author);

        // 마일스톤 1관계
        MileStoneDTO milestone = mileStoneService.findMilestoneInIssue(issue.getMilestoneId());

        IssueDetailDTO issueDetailDTO = IssueDetailDTO.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .createTime(issue.getCreateTime())
                .updateTime(issue.getUpdateTime())
                .isOPen(issue.getIsOpen())
                .author(author)
                .labels(labels)
                .assignees(assignees)
                .milestone(milestone)
                .build();

        return issueDetailDTO;
    }

    public List<LabelDTO> getLabelsByIssueId(final Long issueId) {
        Issue issue = issueRepository.findById(issueId).get();
        List<IssueAttachedLabel> attachedLabels = issue.getAttachedLabels();
        List<LabelDTO> labels = new ArrayList<>();

        for (IssueAttachedLabel attachedLabel : attachedLabels) {
            AggregateReference<Label, Long> labelId = attachedLabel.getLabelId();
            Label label = labelRepository.findById(labelId.getId()).get();

            LabelDTO labelInIssueDTO = LabelDTO.builder()
                    .id(label.getId())
                    .labelName(label.getTitle())
                    .fontColor(label.getFontColor())
                    .backgroundColor(label.getBackgroundColor())
                    .build();
            labels.add(labelInIssueDTO);
        }

        return labels;
    }

    //TODO 수정필요
    public void updateIssueTitle(Long issueId, IssueTitleDTO issueTitleDTO) {
        Issue issue = issueRepository.findById(issueId).get();
        Issue updatedIssueInTitle = issue.update(issueTitleDTO);
        //
        Issue savedIssue = issueRepository.save(updatedIssueInTitle);
        //  IssueTitleDTO.of(savedIssue);
    }

    //TODO 수정필요
    public void deleteIssue(Long issueId) {
        Issue issue = issueRepository.findIssueById(issueId);
        issueRepository.delete(issue);
    }

    public List<Issue> getIssues() {
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : issueRepository.findAll()) {
            issues.add(issue);
        }
        return issues;
    }

    public Long findByIsOpen(Long milestoneId) {
        Iterable<Issue> all = issueRepository.findAll();
        Long count = 0L;
        for (Issue issue : all) {
            if (issue.getMilestoneId().getId() == milestoneId) {
                if (issue.getIsOpen() == true) {
                    count++;
                }
            }
        }
        return count;
    }

    public Long count() {
        return issueRepository.count();
    }

    public Issue update(Issue issue) {
        return Issue.builder()
                .id(issue.getId())
                .author(issue.getAuthor())
                .assignees(issue.getAssignees())
                .attachedLabels(issue.getAttachedLabels())
                .build();
    }

    public void deleteCommnet(Long userId, Long issueId, Long commentId) {
    }

    public void updateLabels(Long issueId, PostingIssueDTO issueDto) {
        Issue issue = issueRepository.findById(issueId).get();
        List<Long> labels = issueDto.getLabels();
        issueRepository.save(issue);
    }

    public IssueDetailLabelDto removeAttachedLabels(Long issueId, Label label) {
        Issue issue = issueRepository.findById(issueId).get();
        issue.removeAttachedLabels(label);
        Issue newIssue = issueRepository.save(issue);
        List<Label> newAttachedLabel = labelRepository.findAllAttachedLabelByIssues(newIssue.getId());
        return new IssueDetailLabelDto(newIssue, newAttachedLabel);
    }
}
