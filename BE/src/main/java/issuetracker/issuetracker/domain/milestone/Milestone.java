package issuetracker.issuetracker.domain.milestone;

import issuetracker.issuetracker.domain.milestone.dto.MilestonePostDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Table("milestone")
@Builder
@ToString
public class Milestone {

    @Id
    //    @Column("milestone_id")
    private Long milestoneId;
    @Column("title")
    private String title;
    @Column("description")
    private String description;
    @Column("create_time")
    private LocalDateTime createTime;
    @Column("is_delete")
    private boolean isDelete;

    public static Milestone create(MilestonePostDTO postingIssueDTO) {
        return Milestone.builder()
                .milestoneId(null)
                .title(postingIssueDTO.getTitle())
                .description(postingIssueDTO.getDescription())
                .createTime(LocalDateTime.now())
                .isDelete(false)
                .build();
    }

    public void update(MilestonePostDTO milestonePostDTO) {
        this.title = milestonePostDTO.getTitle();
        this.createTime = milestonePostDTO.getCompleteDate();
        this.description = milestonePostDTO.getDescription();
    }
}
