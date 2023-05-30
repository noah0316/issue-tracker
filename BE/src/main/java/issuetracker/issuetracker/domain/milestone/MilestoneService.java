package issuetracker.issuetracker.domain.milestone;

import issuetracker.issuetracker.domain.issue.Assignee;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueController;
import issuetracker.issuetracker.domain.issue.service.IssueService;
import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneFilterDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneListDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestonePostDTO;
import issuetracker.issuetracker.domain.user.Member;
import issuetracker.issuetracker.domain.user.dto.AssigneeDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository repository;
    private final IssueService issueService;
    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    @Transactional(readOnly = true)
    public List<MilestoneFilterDTO> getMilestoneFilter() {
        return repository.getMilestoneFilter();
    }


    public MileStoneDTO findMilestoneInIssue(AggregateReference<Milestone, @NotNull Long> issue) {
        return repository.findByMilestoneInIssue(issue.getId());
    }

    public void save(MilestonePostDTO postingIssueDTO) {
        Milestone milestone = Milestone.create(postingIssueDTO);
        log.debug("Milestone issue생성 = {}", milestone);
        Milestone save = repository.save(milestone);
        log.debug("save = {}", save);
    }

    public List<MilestoneListDTO> findAll() {

        Iterable<Milestone> all = repository.findAll();
        List<MilestoneListDTO> listDTOS = new ArrayList<>();
        for (Milestone milestone : all) {
            Long byIsOpenCount = issueService.findByIsOpen(milestone.getMilestoneId());
            MilestoneListDTO milestoneListDTO = MilestoneListDTO.builder()
                    .id(milestone.getMilestoneId())
                    .title(milestone.getTitle())
                    .closeIssueCount(issueService.count() - byIsOpenCount)
                    .openIssueCount(byIsOpenCount)
                    .completeDate(LocalDateTime.now())
                    .description(milestone.getDescription())
                    .build();

            listDTOS.add(milestoneListDTO);
        }

        return listDTOS;
    }

    public void delete(Long milestoneId) {
        Milestone milestone = repository.findById(milestoneId).get();
        issueService.getIssues().stream()
                .map(Issue::deleteMilestone)
                //TODO 이부분 상세히구현
                .forEach(issueService::update);

        repository.delete(milestone);
    }

    public void update(Long milestoneId, MilestonePostDTO milestonePostDTO) {
        Milestone milestone = repository.findById(milestoneId).get();
        milestone.update(milestonePostDTO);
        repository.save(milestone);
    }
}