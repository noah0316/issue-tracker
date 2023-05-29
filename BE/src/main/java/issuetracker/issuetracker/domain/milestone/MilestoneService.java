package issuetracker.issuetracker.domain.milestone;

import issuetracker.issuetracker.domain.issue.IssueController;
import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneFilterDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestonePostDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository repository;
    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    @Transactional(readOnly = true)
    public List<MilestoneFilterDTO> getMilestoneFilter() {
        return repository.getMilestoneFilter();
    }


    public MileStoneDTO findMilestoneInIssue(AggregateReference<Milestone, @NotNull Long> issue) {
        return repository.findByMilestoneInIssue(issue.getId());
    }

    public void save(MilestonePostDTO postingIssueDTO) {
        Milestone milestone = Milestone.create(postingIssueDTO);
        log.debug("Milestone issue생성 = {}", milestone);
        Milestone save = repository.save(milestone);
        log.debug("save = {}", save);
    }
}