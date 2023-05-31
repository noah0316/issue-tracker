package issuetracker.issuetracker.domain.label;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import issuetracker.issuetracker.domain.label.dto.LabelFilterDTO;
import issuetracker.issuetracker.domain.label.dto.LabelListDTO;
import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "posts", description = "issue 메인 API")
@RequestMapping("/labels")
@RequiredArgsConstructor
@RestController
public class LabelController {

    private final LabelService labelService;
    private final Logger log = LoggerFactory.getLogger(LabelController.class);

    @Operation(
            summary = "모든 라벨 출력 ",
            tags = "issue",
            description = "."
    )
    @GetMapping
    public List<LabelListDTO> showLabelList() {
        return labelService.getLabelList();
    }

    @Operation(
            summary = "라벨의 상태 필터링",
            tags = "issue",
            description = "."
    )
    @GetMapping("/filter")
    public List<LabelFilterDTO> getLabelFilter() {
        return labelService.getLabelFilter();
    }

    @Operation(
            summary = "여러 라벨 저장",
            tags = "issue",
            description = "."
    )
    @PostMapping
    public void postLabel(@RequestBody PostingLabelDTO label) {
        // TODO 등록 로직 작성
        log.debug("라벨 저장");
        labelService.save(label);
    }

    @Operation(
            summary = "라벨 삭제",
            tags = "issue",
            description = "."
    )
    @DeleteMapping("/{labelId}")
    public void deleteLabel(@PathVariable long labelId) {
        // TODO 라벨 삭제 라벨 삭제 권한이 있어야 할 것같다.
        log.debug("라벨 삭제");
        labelService.delete(labelId);
    }

    @Operation(
            summary = "라벨 수정",
            tags = "issue",
            description = "."
    )
    @PutMapping("/{labelId}")
    public void updateLabel(@PathVariable long labelId, @RequestBody PostingLabelDTO label) {
        // TODO 라벨 수정
        log.debug("라벨 수정");
        labelService.update(labelId, label);
    }
}
