package issuetracker.issuetracker.domain.user.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResponse {

    private String login;
    private String name;
    private String email;
    @JsonProperty("avatar_url")
    private String avatarUrl;
}
