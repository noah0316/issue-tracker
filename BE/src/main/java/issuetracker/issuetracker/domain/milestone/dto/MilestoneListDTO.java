package issuetracker.issuetracker.domain.milestone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MilestoneListDTO {


    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    private String description;
    private LocalDateTime completeDate;
    private Long openIssueCount;
    private Long closeIssueCount;
}
