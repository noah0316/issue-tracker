package issuetracker.issuetracker.domain.issue.dto;

import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.milestone.Milestone;
import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.user.dto.AssigneeDTO;
import issuetracker.issuetracker.domain.user.dto.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class IssueDetailDTO {

    @Id
    private final Long id;
    private final String title;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;
    private final Boolean isOPen;
    private final AuthorDTO author;
    private final MileStoneDTO milestone;
    private final List<AssigneeDTO> assignees;
    private final List<LabelDTO> labels;

    //지워야 할수도
}
