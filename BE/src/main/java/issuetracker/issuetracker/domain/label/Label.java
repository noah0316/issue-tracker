package issuetracker.issuetracker.domain.label;

import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@AllArgsConstructor
@Table("label")
@Builder
public class Label {
    @Id
    @Column("label_id")
    private Long id;
    private String title;
    private String fontColor;
    private String description;
    //추가
    private boolean isDelete;
    private String backgroundColor;

    public Label update(PostingLabelDTO newLabelLine) {
        this.title = newLabelLine.getTitle();
        this.backgroundColor = newLabelLine.getBackgroundColor();
        this.description = newLabelLine.getDescription();
        return this;
    }
}
