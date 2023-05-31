package issuetracker.issuetracker.domain.issue;

import issuetracker.issuetracker.domain.label.Label;
import issuetracker.issuetracker.domain.user.Member;
import jdk.jshell.Snippet;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Table("label_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class IssueAttachedLabel {
    @Id
    @Column("label_list_id")
    private Long id;

    AggregateReference<Label, @NotNull Long> labelId;

    public IssueAttachedLabel(Long index) {
        this.labelId = AggregateReference.to(index);
    }
}
