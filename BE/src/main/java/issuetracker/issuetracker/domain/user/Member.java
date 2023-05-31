package issuetracker.issuetracker.domain.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private Long memberId;

    @NotBlank
    private String memberName;

    @Column("profile_url")
    private String profileUrl;
}
