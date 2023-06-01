package issuetracker.issuetracker.domain.issue.repository;


import issuetracker.issuetracker.domain.issue.Assignee;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.issue.dto.IssueDetailLabelDto;
import issuetracker.issuetracker.domain.issue.service.IssueService;
import issuetracker.issuetracker.domain.label.Label;
import issuetracker.issuetracker.domain.label.LabelRepository;
import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import issuetracker.issuetracker.domain.milestone.Milestone;
import issuetracker.issuetracker.domain.user.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IssueRepositoryTest {

    private IssueRepository issueRepository;

    private LabelRepository labelRepository;

    @Autowired
    public IssueRepositoryTest(IssueRepository issueRepository, LabelRepository labelRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
    }

    private final AggregateReference<Milestone, Long> mileId = AggregateReference.to(1L);
    private final AggregateReference<Member, Long> userId = AggregateReference.to(1L);
    private final List<Long> labelIds = List.of(
            1L,
            2L,
            3L
    );


    private final List<Long> assigneesIds = List.of(
            1L,
            2L
    );

    private final List<Issue> issues = List.of(
            Issue.builder()
                    .id(1L)
                    .title("issue 1")
                    .isDelete(false)
                    .isOpen(true)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .milestoneId(mileId)
                    .author(userId)
                    .build()
    );

    @Test
    void insert() {
        // given
        Issue issue = this.issues.get(0);

        // when
        Issue actual = this.issueRepository.save(issue);

        // then
        System.out.println(actual);
        assertEquals(actual.getId(), issue.getId());
        assertEquals(actual.getTitle(), issue.getTitle());
        assertEquals(actual.getAuthor(), issue.getAuthor());
        assertEquals(actual.getIsOpen(), issue.getIsOpen());
        assertEquals(actual.getMilestoneId(), issue.getMilestoneId());
        assertEquals(actual.getAttachedLabels(), issue.getAttachedLabels());
        assertEquals(actual.getAssignees(), issue.getAssignees());

//        assertEquals(actual.getAttachedLabels().get(0), issue.getAttachedLabels().get(0));
//        assertEquals(actual.getAssignees().get(0), issue.getAssignees().get(0));
    }

    @Test
    @DisplayName("이슈 라벨 삭제")
    void deleteLabelFromIssues() {

        Label label = labelRepository.findById(1L).get();
        System.out.println("label = " + label);
        //TODO 이슈에있는 라벨도 삭제해야함.
        // Remove the label from all issues

        labelRepository.delete(label);
    }

    @Test
    @DisplayName("이슈 라벨 업데이트")
    void updateLabelFromIssues() {
        // Given
        //라벨찾고
        Label label = labelRepository.findById(1L).get();
        System.out.println("label" + label.getTitle());

        // When
        //모든 이슈 이슈를 뒤지면서 라벨이 바뀐걸 update
        Label new_title = label.update(new PostingLabelDTO("New Title", "", ""));
        Label save = labelRepository.save(new_title);
        System.out.println("IssueRepositoryTest.updateLabelFromIssues" + save.getTitle());
        // Then
        Iterable<Issue> afterAll = issueRepository.findAll();
        for (Issue issue : afterAll) {
            Set<IssueAttachedLabel> attachedLabels = issue.getAttachedLabels();
            for (IssueAttachedLabel attachedLabel : attachedLabels) {
                if (attachedLabel.getId() == 1L) {
                    System.out.println("title = " + issue.getTitle());
                }
            }
        }
        System.out.println("afterAll = " + afterAll);
    }


    @Test
    @DisplayName("이슈 라벨 업데이트")
    void updateLabelFromIssue() {
        // Given
        //라벨찾고
        Label label = Label.builder()
                .id(null)
                .title("감자 라벨")
                .backgroundColor("black")
                .fontColor("yellow")
                .description("이슈1")
                .build();
        labelRepository.save(label);

        Issue issue = Issue.builder()
                .id(null)
                .title("issue 1")
                .isDelete(false)
                .isOpen(true)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
//                .attachedLabels(new ArrayList<>(List.of(
//                        IssueAttachedLabel.builder()
//                                .labelId(AggregateReference.to(label.getId()))
//                                .build()
//                )))
                .milestoneId(null)
                .author(AggregateReference.to(1L))
                .build();
        issueRepository.save(issue);
        Issue findIssue = issueRepository.findById(issue.getId()).get();
        findIssue.removeAttachedLabels(label);
        Issue newIssue = issueRepository.save(findIssue);
        List<Label> newAttachedLabel = labelRepository.findAllAttachedLabelByIssues(newIssue.getId());
        assertEquals(newAttachedLabel.size(), 0);
    }
}
