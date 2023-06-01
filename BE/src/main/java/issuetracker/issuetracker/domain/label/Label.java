package issuetracker.issuetracker.domain.label;

import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@Table("label")
@Builder
@NoArgsConstructor
@ToString
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

    public static Label create(PostingLabelDTO labelDTO) {
        return Label.builder()
                .id(null)
                .title(labelDTO.getTitle())
                .backgroundColor(labelDTO.getBackgroundColor())
                .description(labelDTO.getDescription())
                .fontColor(labelDTO.getFontColor())
                .isDelete(false)
                .build();
    }

    public Label update(PostingLabelDTO newLabelLine) {
        this.title = newLabelLine.getTitle();
        this.backgroundColor = newLabelLine.getBackgroundColor();
        this.description = newLabelLine.getDescription();
        return this;
    }
}
