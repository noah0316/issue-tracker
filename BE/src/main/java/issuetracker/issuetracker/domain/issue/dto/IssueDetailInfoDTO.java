package issuetracker.issuetracker.domain.issue.dto;

import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.user.dto.AssigneeDTO;
import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class IssueDetailInfoDTO {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private LocalDateTime createTime;
    @NotNull
    private LocalDateTime updateTime;
    @NotNull
    private Boolean isOPen;
    private String milestone;
    @NotNull
    private AuthorDTO author;

    @MappedCollection(idColumn = "issue_id")
    private Set<AssigneeDTO> assignees;

    @MappedCollection(idColumn = "issue_id")
    private Set<LabelDTO> label;

    private Set<CommentInIssueDTO> reply;
}
