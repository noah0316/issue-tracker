package issuetracker.issuetracker.domain.issue;

import issuetracker.issuetracker.domain.user.Member;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Table("assignee")
@Getter
@ToString
public class Assignee {
    @Id
    @Column("assignee_id")
    private Long id;

    AggregateReference<Member, @NotNull Long> memberId;

    public Assignee(Long index) {
        this.memberId = AggregateReference.to(index);
    }
}
