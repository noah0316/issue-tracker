package issuetracker.issuetracker.domain.milestone.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilestonePostDTO {

    @NotNull
    private String title;
    private String description;
    private LocalDateTime completeDate;
}
