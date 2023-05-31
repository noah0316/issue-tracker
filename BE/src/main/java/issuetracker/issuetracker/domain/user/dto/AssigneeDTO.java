package issuetracker.issuetracker.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class AssigneeDTO {
    @Id
    @NotNull
    private long id;
    private String name;
    private String profileUrl;
}
