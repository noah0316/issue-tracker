package issuetracker.issuetracker.domain.label;

import io.swagger.v3.oas.annotations.tags.Tag;
import issuetracker.issuetracker.domain.label.dto.LabelFilterDTO;
import issuetracker.issuetracker.domain.label.dto.LabelListDTO;
import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Tag(name = "posts", description = "issue 메인 API")
@RequestMapping("/labels")
@RequiredArgsConstructor
@RestController
public class LabelController {

    private final LabelService labelService;
    private final Logger log = LoggerFactory.getLogger(LabelController.class);

    @GetMapping
    public List<LabelListDTO> showLabelList() {
        return new ArrayList<>();
    }

    @GetMapping("/filter")
    public List<LabelFilterDTO> getLabelFilter() {
        return labelService.getLabelFilter();
    }

    @PostMapping
    public void postLabel(@RequestBody PostingLabelDTO label) {
        // TODO 등록 로직 작성
        log.debug("라벨 저장");
        labelService.save(label);
    }

    @DeleteMapping("/{labelId}")
    public void deleteLabel(@PathVariable Long labelId) {
        // TODO 라벨 삭제 라벨 삭제 권한이 있어야 할 것같다.
        log.debug("라벨 삭제");
        labelService.delete(labelId);
    }

    @PutMapping("/{labelId}")
    public void updateLabel(@PathVariable Long labelId, @RequestBody PostingLabelDTO label) {
        // TODO 라벨 수정
        log.debug("라벨 수정");
        labelService.update(labelId, label);
    }
}
