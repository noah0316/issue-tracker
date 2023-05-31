package issuetracker.issuetracker.domain.milestone.dto;

import issuetracker.issuetracker.domain.milestone.Milestone;
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
    private long openIssueCount;
    private long closeIssueCount;

    public static MilestoneListDTO of(Milestone milestone, Long openedIssues, Long closedIssues) {
        return MilestoneListDTO.builder()
                .id(milestone.getMilestoneId())
                .title(milestone.getTitle())
                .description(milestone.getDescription())
                .completeDate(milestone.getCreateTime())
                .openIssueCount(openedIssues)
                .closeIssueCount(closedIssues)
                .build();
    }
}
