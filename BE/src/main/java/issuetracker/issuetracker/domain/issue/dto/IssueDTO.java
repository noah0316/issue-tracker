package issuetracker.issuetracker.domain.issue.dto;

import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.user.dto.AssigneeDTO;
import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private LocalDateTime createTime;
    @NotNull
    private LocalDateTime updateTime;
    @NotNull
    private Boolean isOpen;

    private MileStoneDTO milestone;
    @NotNull
    private AuthorDTO author;

    @MappedCollection(idColumn = "issue_id")
    private Set<AssigneeDTO> assignees;

    @MappedCollection(idColumn = "issue_id")
    private Set<LabelDTO> labels;
}
