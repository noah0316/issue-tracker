package issuetracker.issuetracker.domain.label;

import issuetracker.issuetracker.domain.label.dto.LabelFilterDTO;
import issuetracker.issuetracker.domain.label.dto.LabelListDTO;
import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LabelService {

    private final LabelRepository repository;

    public List<LabelListDTO> getLabelList() {
        return repository.getLabelList();
    }

    @Transactional(readOnly = true)
    public List<LabelFilterDTO> getLabelFilter() {
        return repository.getLabelFilter();
    }

    public void save(PostingLabelDTO labelDTO) {
        Label label = Label.builder()

                .build();
    }

    public void delete(Long labelId) {
        Label label = repository.findById(labelId).get();
        //TODO 이슈에있는 라벨도 삭제해야함.
        repository.delete(label);
    }

    public void update(Long labelId, PostingLabelDTO newLabelLine) {
        Label label = repository.findById(labelId).get();
        //TODO 이슈에있는 라벨도 업데이트 해줘야함.
        repository.save(label.update(newLabelLine));
    }
}
