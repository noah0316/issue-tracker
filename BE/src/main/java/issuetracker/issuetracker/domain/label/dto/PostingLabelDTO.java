package issuetracker.issuetracker.domain.label.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostingLabelDTO {
    private String title;
    private String description;
    private String backgroundColor;
    private String fontColor;
}
