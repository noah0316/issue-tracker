package issuetracker.issuetracker.domain.milestone;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import issuetracker.issuetracker.domain.issue.IssueController;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneFilterDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneListDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestonePostDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "posts", description = "issue 메인 API")
@RequestMapping("/milestones")
@RequiredArgsConstructor
@RestController
public class MilestoneController {

    private final MilestoneService service;
    private final Logger log = LoggerFactory.getLogger(MilestoneController.class);

    @Operation(
            summary = "마일스톤 전체 조회",
            tags = "milestone",
            description = "."
    )
    @GetMapping
    public List<MilestoneListDTO> showMilestoneList() {
        // TODO 마일스톤 전체 목록 조회
        log.debug("모든 마일스톤 조회");
        return service.findAll();
    }

    @Operation(
            summary = "라벨 등록",
            tags = "milestone",
            description = "."
    )
    @PostMapping
    public void postMilestone(@RequestBody MilestonePostDTO milestone) {
        // TODO 마일스톤 등록
        service.save(milestone);
    }

    @Operation(
            summary = "라벨 삭제",
            tags = "milestone",
            description = "."
    )
    @DeleteMapping("/{milestoneId}")
    public void deleteMilestone(@PathVariable long milestoneId) {
        //TODO 마일스톤 삭제
        service.delete(milestoneId);
    }

    @Operation(
            summary = "마일스톤 수정",
            tags = "milestone",
            description = "."
    )
    @PutMapping("/{milestoneId}")
    public void updateMilestone(@PathVariable long milestoneId, @RequestBody MilestonePostDTO milestone) {
        //TODO 마일스톤 업데이트
        service.update(milestoneId, milestone);
    }

    @Operation(
            summary = "마일스톤 필터링 삭제",
            tags = "milestone",
            description = "."
    )
    @GetMapping("/filter")
    public List<MilestoneFilterDTO> getMilestoneFilter() {
        return service.getMilestoneFilter();
    }
}
