package issuetracker.issuetracker.domain.user;

//TODO 차후.. 토큰 서비스를 만들자.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenUser {
    @NotNull
    private Long id;
}
