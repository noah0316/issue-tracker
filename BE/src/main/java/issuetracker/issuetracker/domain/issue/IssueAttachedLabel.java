package issuetracker.issuetracker.domain.issue;

import issuetracker.issuetracker.domain.label.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Value
@Table("label_list")
@AllArgsConstructor
@Builder
public class IssueAttachedLabel {
    @Id
    @Column("label_list_id")
    private Long id;

    AggregateReference<Label, @NotNull Long> labelId;
}
