package issuetracker.issuetracker.domain.issue.repository;


import issuetracker.issuetracker.domain.issue.Assignee;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.milestone.Milestone;
import issuetracker.issuetracker.domain.user.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IssueRepositoryTest {

    @Autowired
    private IssueRepository repository;

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
                    .attachedLabels(new ArrayList<>(List.of(
                            IssueAttachedLabel.builder()
                                    .labelId(AggregateReference.to(labelIds.get(0)))
                                    .build(),
                            IssueAttachedLabel.builder()
                                    .labelId(AggregateReference.to(labelIds.get(1)))
                                    .build()
                    )))
                    .assignees(new ArrayList<>(List.of(
                            Assignee.builder()
                                    .memberId(AggregateReference.to(assigneesIds.get(0)))
                                    .build(),
                            Assignee.builder()
                                    .memberId(AggregateReference.to(assigneesIds.get(1)))
                                    .build()
                    )))
                    .milestoneId(mileId)
                    .author(userId)
                    .build()
    );

    @Test
    void insert() {
        // given
        Issue issue = this.issues.get(0);

        // when
        Issue actual = this.repository.save(issue);

        // then
        System.out.println(actual);
//        assertThat(issue.getVersion()).isEqualTo(1L);
//        assertThat(issue.getContent()).isSameAs(actual.getContent());
//
//        assertThat(issue.getAttachedLabels().get(0)).isSameAs(actual.getAttachedLabels().get(0));
//        assertThat(issue.getAttachedLabels().get(1)).isSameAs(actual.getAttachedLabels().get(1));
//
//        Optional<Issue> load = this.sut.findById(issue.getId());
//        assertThat(load).isPresent();
//        assertThat(load.get().getId()).isEqualTo(issue.getId());
//        assertThat(load.get().getVersion()).isEqualTo(1L);
//        assertThat(load.get().getRepoId()).isEqualTo(this.repoId);
//        assertThat(load.get().getStatus()).isEqualTo(Status.OPEN);
//        assertThat(load.get().getTitle()).isEqualTo("issue 1");
//        assertThat(load.get().getContent().getBody()).isEqualTo("content 1");
//        assertThat(load.get().getContent().getMimeType()).isEqualTo("text/plain");
//        assertThat(load.get().getCreatedBy()).isEqualTo(this.creatorId);
    }

}
