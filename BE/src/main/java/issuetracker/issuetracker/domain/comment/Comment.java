package issuetracker.issuetracker.domain.comment;

import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.dto.Request.CommentPostDTO;
import issuetracker.issuetracker.domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table("comment")
@ToString
@Getter
@Builder
public class Comment {
    @Id
    @Column("comment_id")
    private Long id;
    private String contents;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private AggregateReference<Member, @NotNull Long> memberId;
    private AggregateReference<Issue, @NotNull Long> issueId;

    public static Comment create(Long memberId, Long issueId, @NotNull String comment) {
        return Comment.builder()
                .id(null)
                .contents(comment)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .memberId(AggregateReference.to(memberId))
                .issueId(AggregateReference.to(issueId))
                .build();
    }

    public void update(CommentPostDTO commentPostDTO) {
        this.contents = commentPostDTO.getComment();
    }
}
