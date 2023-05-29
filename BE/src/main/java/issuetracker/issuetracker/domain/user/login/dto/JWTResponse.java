package issuetracker.issuetracker.domain.user.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JWTResponse {

    private String message;
    private String token;

}
