package issuetracker.issuetracker.domain.issue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import issuetracker.issuetracker.domain.comment.Comment;
import issuetracker.issuetracker.domain.issue.dto.Request.IssueTitleDTO;
import issuetracker.issuetracker.domain.issue.dto.Request.PostingIssueDTO;
import issuetracker.issuetracker.domain.label.Label;
import issuetracker.issuetracker.domain.milestone.Milestone;
import issuetracker.issuetracker.domain.user.Member;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Builder
@Table("issue") // 테이블 매핑을 추가
@ToString
public class Issue {
    @Id
    @Column("issue_id")
    private Long id;

    @Column("title")
    private String title;

    private String description;
    // Other commented out properties

    @Column("create_time")
    private LocalDateTime createTime;

    @Column("update_time")
    private LocalDateTime updateTime;

    @Column("is_open")
    private Boolean isOpen;

    @Column("is_delete")
    private Boolean isDelete;

    @Column("milestone_id")
    private AggregateReference<Milestone, @NotNull Long> milestoneId;

    @Column("author")
    private AggregateReference<Member, @NotNull Long> author;

    @JsonIgnore
    @MappedCollection(idColumn = "issue_id", keyColumn = "label_list_id")
    @Builder.Default
    private Set<IssueAttachedLabel> attachedLabels = new HashSet<>();

    @MappedCollection(idColumn = "issue_id", keyColumn = "assignee_id")
    @Builder.Default
    private Set<Assignee> assignees = new HashSet<>();


    public static Issue create(PostingIssueDTO postingIssueDTO) {
        Set<Assignee> assigneeSet = new HashSet<>();
        if (postingIssueDTO.getAssignees() != null) {
            assigneeSet = postingIssueDTO.getAssignees()
                    .stream()
                    .filter(Objects::nonNull)
                    .map(e -> new Assignee(e.longValue())).collect(Collectors.toSet());
        }

        Set<IssueAttachedLabel> attacheSet = new HashSet<>();
        if (postingIssueDTO.getLabels() != null) {
            postingIssueDTO.getLabels().
                    stream()
                    .filter(Objects::nonNull)
                    .map(e -> new IssueAttachedLabel(e.longValue()))
                    .forEach(a -> attacheSet.add(a));
        }

        return Issue.builder()
                .id(null)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isOpen(true)
                .isDelete(false)
                .title(postingIssueDTO.getTitle())
                .description(postingIssueDTO.getDescription())
                //TODO 사용자 id넣어야함
                .author(AggregateReference.to(postingIssueDTO.getTokenuser().getId()))
                .milestoneId(AggregateReference.to(postingIssueDTO.getMilestoneId()))
                .attachedLabels(attacheSet)
                .assignees(assigneeSet)
                .build();
    }


    public Issue update(IssueTitleDTO issueTitleDTO) {
        this.title = issueTitleDTO.getTitle();
        return this;
    }

    public Issue deleteMilestone() {
        this.milestoneId = null;
        return this;
    }

//    public Issue addComment(Comment comment) {
//        this.comments.add(comment);
//        return this;
//    }

    public Set<IssueAttachedLabel> getAttachedLabels() {
        return Collections.unmodifiableSet(this.attachedLabels);
    }

    public Set<Assignee> getAssignees() {
        return Collections.unmodifiableSet(this.assignees);
    }

    public void addAttachedLabels(Label label) {
        this.attachedLabels.add(new IssueAttachedLabel(label.getId(), AggregateReference.to(label.getId())));
    }

    public void removeAttachedLabels(Label label) {
        this.attachedLabels.removeIf(attachedLabel -> attachedLabel.labelId.getId() == label.getId());
    }

    public Issue updateStatus(boolean state) {
        this.isOpen = state;
        return this;
    }
}
