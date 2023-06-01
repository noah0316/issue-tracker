package issuetracker.issuetracker.domain.issue.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
public class CommentInIssueDTO {
    // 유저 pk,유저 이름, 유저 url, (작성자)
    @NotNull
    private Long userId;
    @NotNull
    private String userName;
    private String userUrl;

    // 댓글 제목, 내용,
    @NotNull
    private String replyContents;
    private LocalDateTime createTime;

    // 이모지 (모양, 개수)
    private String emojiShape;
    private Long emojiCount;

    // 파일 URL
    private String fileAttachmentUrl;

}
