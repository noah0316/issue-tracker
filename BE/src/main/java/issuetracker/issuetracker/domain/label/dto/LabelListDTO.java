package issuetracker.issuetracker.domain.label.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelListDTO {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    private String description;
    private String fontColor;
    @NotNull
    private String backgroundColor;
}
