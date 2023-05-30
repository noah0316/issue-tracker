package issuetracker.issuetracker.domain.issue.dto;

import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.label.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDetailLabelDto {

    private Issue issue;
    private List<Label> labels;

}
