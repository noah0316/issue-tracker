package issuetracker.issuetracker.domain.label;

import issuetracker.issuetracker.domain.issue.IssueController;
import issuetracker.issuetracker.domain.issue.service.IssueService;
import issuetracker.issuetracker.domain.label.dto.LabelFilterDTO;
import issuetracker.issuetracker.domain.label.dto.LabelListDTO;
import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LabelService {

    private final LabelRepository repository;
    private final IssueService issueService;
    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    public List<LabelListDTO> getLabelList() {
        return repository.getLabelList();
    }

    @Transactional(readOnly = true)
    public List<LabelFilterDTO> getLabelFilter() {
        return repository.getLabelFilter();
    }

    public void save(PostingLabelDTO labelDTO) {
        Label label = Label.create(labelDTO);
        repository.save(label);
    }

    public void delete(Long labelId) {
        Label label = repository.findById(labelId).get();

        //TODO 이슈에있는 라벨도 삭제해야함.
        // Remove the label from all issues
        issueService.getIssues().forEach(issue -> issue.getAttachedLabels().removeIf(attachedLabel -> attachedLabel.getLabelId().equals(labelId)));
        repository.delete(label);
    }

    public void update(Long labelId, PostingLabelDTO newLabelLine) {
        Label label = repository.findById(labelId).get();
        //TODO 이슈에있는 라벨도 업데이트 해줘야함.

//        label.getIssues().stream()
//                .map(issue -> issue.deleteLabel(label))
//                .forEach(this::synchronizeIssue);
        repository.save(label.update(newLabelLine));
    }
}
