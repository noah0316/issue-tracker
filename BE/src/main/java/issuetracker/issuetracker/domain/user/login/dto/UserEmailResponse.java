package issuetracker.issuetracker.domain.user.login.dto;

import lombok.Getter;
import lombok.Setter;

// 유저의 이메일이 여러개일 수 있다.
@Getter
@Setter
public class UserEmailResponse {
    private String email;
    private boolean primary;
    private boolean verified;
    private String visibility;
}
