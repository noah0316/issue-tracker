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
    private String loginId;

    @NotBlank
    private String memberName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @Column("profile_url")
    private String profileUrl;
}
