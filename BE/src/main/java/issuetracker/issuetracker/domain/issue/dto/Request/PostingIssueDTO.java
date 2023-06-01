package issuetracker.issuetracker.domain.issue.dto.Request;

import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.user.TokenUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostingIssueDTO {
    @NotNull
    private String title;
    private String contents;
    private String description;
    private String fileUrl;
    private List<Long> assignees;
    private List<Long> labels;
    private Long milestoneId;
    //TODO 로그인 한 사용자 아이디
    private TokenUser tokenuser;

}
