package issuetracker.issuetracker.domain.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@Table(name = "member")
@AllArgsConstructor
public class Member implements Persistable {
    @Id
    @Column("member_id")
    private Long id;

    @NotBlank
    @Column("member_name")
    private String name;

    @Column("profile_url")
    private String profileUrl;

    @Override
    public boolean isNew() {
        return true;
    }
}
