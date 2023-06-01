package issuetracker.issuetracker.domain.issue.dto.Request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentPostDTO {
    @NotNull
    private Long issueId;
    @NotNull
    private String comment;
    private String fileAttachmentUrl;
    @NotNull
    private Long userId;
    @NotNull
    private String userName;
    private String userProfileUrl;
}
