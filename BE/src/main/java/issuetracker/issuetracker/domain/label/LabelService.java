package issuetracker.issuetracker.domain.label;

import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final IssueService issueService;
    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    public List<LabelListDTO> getLabelList() {
        return labelRepository.getLabelList();
    }

    @Transactional(readOnly = true)
    public List<LabelFilterDTO> getLabelFilter() {
        return labelRepository.getLabelFilter();
    }

    public void save(PostingLabelDTO labelDTO) {
        Label label = Label.create(labelDTO);
        labelRepository.save(label);
    }

    public void delete(Long labelId) {
        Label label = labelRepository.findById(labelId).get();
        //TODO 이슈에있는 라벨도 삭제해야함.
        // Remove the label from all issues

        issueService.getIssues().stream()
                .filter(issue -> issue.getAttachedLabels().contains(label))
                .forEach(issue -> issue.getAttachedLabels().remove(label));

        labelRepository.delete(label);
    }

    public void update(Long labelId, PostingLabelDTO newLabelLine) {
        Label label = labelRepository.findById(labelId).get();
        labelRepository.save(label.update(newLabelLine));
    }

    public List<Label> findByAll(List<Long> labelIds) {
        Iterable<Label> allById = labelRepository.findAllById(labelIds);
        List<Label> labels = new ArrayList<>((Collection) allById);
        return labels;
    }
}
