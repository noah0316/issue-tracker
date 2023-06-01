package issuetracker.issuetracker.domain.label;

import issuetracker.issuetracker.domain.exception.LabelNotFoundException;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueController;
import issuetracker.issuetracker.domain.issue.service.IssueUtilService;
import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.label.dto.LabelFilterDTO;
import issuetracker.issuetracker.domain.label.dto.LabelListDTO;
import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import issuetracker.issuetracker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final IssueUtilService issueUtilService;
    private final Logger log = LoggerFactory.getLogger(LabelService.class);

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

    @Transactional
    public void delete(Long labelId) {
        Label label = labelRepository.findById(labelId).get();
        //TODO 이슈에있는 라벨도 삭제해야함.
        labelRepository.deleteAllIssueInLabelAndLabel(labelId);
        labelRepository.delete(label);
    }

    public void update(Long labelId, PostingLabelDTO newLabelLine) {
        Label label = findLabelById(labelId);
        labelRepository.save(label.update(newLabelLine));
    }

    private Label findLabelById(Long labelId) {
        if (labelId == null) {
            return null;
        }
        return labelRepository.findById(labelId).orElseThrow(() ->
                new LabelNotFoundException());
    }
}
