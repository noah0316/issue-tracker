package issuetracker.issuetracker.domain.milestone;

import io.swagger.v3.oas.annotations.tags.Tag;
import issuetracker.issuetracker.domain.issue.IssueController;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneFilterDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneListDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestonePostDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Tag(name = "posts", description = "issue 메인 API")
@RequestMapping("/milestones")
@RequiredArgsConstructor
@RestController
public class MilestoneController {

    private final MilestoneService service;
    private final MilestoneRepository repository;
    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    @GetMapping
    public List<MilestoneListDTO> showMilestoneList() {
        // TODO 마일스톤 전체 목록 조회
        return service.findAll();
    }

    @PostMapping
    public void postMilestone(@RequestBody MilestonePostDTO milestone) {
        // TODO 마일스톤 등록
        service.save(milestone);
    }

    @DeleteMapping("/{milestoneId}")
    public void deleteMilestone(@RequestParam Long milestoneId) {
        //TODO 마일스톤 삭제
        service.delete(milestoneId);
    }

    @PutMapping("/{milestoneId}")
    public void updateMilestone(@RequestParam Long milestoneId, @RequestBody MilestonePostDTO milestone) {
        //TODO 마일스톤 업데이트
        service.update(milestoneId, milestone);
    }

    @GetMapping("/filter")
    public List<MilestoneFilterDTO> getMilestoneFilter() {
        return repository.getMilestoneFilter();
    }

}
