package issuetracker.issuetracker.domain.milestone;

import issuetracker.issuetracker.domain.exception.MilestoneNotFoundException;
import issuetracker.issuetracker.domain.issue.service.IssueUtilService;
import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneFilterDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneListDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestonePostDTO;
import issuetracker.issuetracker.domain.page.PageService;
import issuetracker.issuetracker.domain.page.dto.CountInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final IssueUtilService issueUtilService;
    private final PageService pageService;
    private final Logger log = LoggerFactory.getLogger(MilestoneService.class);

    @Transactional(readOnly = true)
    public List<MilestoneFilterDTO> getMilestoneFilter() {
        return milestoneRepository.getMilestoneFilter();
    }


    public MileStoneDTO findMilestoneInIssue(AggregateReference<Milestone, @NotNull Long> issue) {
        return milestoneRepository.findByMilestoneInIssue(issue.getId());
    }

    public void save(MilestonePostDTO postingIssueDTO) {
        Milestone milestone = Milestone.create(postingIssueDTO);
        log.debug("Milestone issue생성 = {}", milestone);
        Milestone save = milestoneRepository.save(milestone);
        log.debug("save = {}", save);
    }

    public List<MilestoneListDTO> findAll() {
        Iterable<Milestone> all = milestoneRepository.findAll();
        List<MilestoneListDTO> listDTOS = new ArrayList<>();
        for (Milestone milestone : all) {
            CountInfo countInfo = pageService.getCountInfo();
            long openedIssues = countInfo.getCloseCount();
            long closedIssues = countInfo.getCloseCount();
            countInfo.getOpenCount();
            //    Long openedIssues = issueUtilService.countOpenedIssues(milestone.getMilestoneId());
            //   Long closedIssues = issueUtilService.countClosedIssues(milestone.getMilestoneId());
            listDTOS.add(MilestoneListDTO.of(milestone, openedIssues, closedIssues));
        }

        return listDTOS;
    }


    public void delete(long milestoneId) {
        Milestone milestone = findMilestoneById(milestoneId);
        issueUtilService.issueMilestoneUpdate(milestone.getMilestoneId());
        milestoneRepository.delete(milestone);
    }

    private Milestone findMilestoneById(Long milestoneId) {
        if (milestoneId == null) {
            log.debug("마일스톤 id null");
            return null;
        }
        return milestoneRepository.findById(milestoneId).orElseThrow(()
                -> new MilestoneNotFoundException());
    }

    public void update(Long milestoneId, MilestonePostDTO milestonePostDTO) {
        Milestone milestone = milestoneRepository.findById(milestoneId).get();
        milestone.update(milestonePostDTO);
        milestoneRepository.save(milestone);
    }
}
