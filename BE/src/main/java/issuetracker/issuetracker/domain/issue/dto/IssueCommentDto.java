package issuetracker.issuetracker.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueCommentDto {
    private Long issueId;
    private String comment;
    private String fileAttachmentUrl;
}
