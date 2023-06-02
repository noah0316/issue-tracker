package issuetracker.issuetracker.domain.issue.dto.Request;

import issuetracker.issuetracker.domain.issue.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueStateListDTO {

    private List<Long> issueIndexList;
    private State state;
}
