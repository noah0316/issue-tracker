package issuetracker.issuetracker.domain.label;

import issuetracker.issuetracker.domain.issue.Assignee;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.issue.service.IssueService;
import issuetracker.issuetracker.domain.label.dto.LabelListDTO;
import issuetracker.issuetracker.domain.label.dto.PostingLabelDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jdbc.core.mapping.AggregateReference;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LabelRepositoryTest {

    @Autowired
    LabelRepository repository;
    @Autowired
    IssueService issueService;


    @Test
    @DisplayName("모든 라벨출력")
    void getLabelList() {
        Iterable<Label> all = repository.findAll();
        List<LabelListDTO> labelList = new ArrayList<>();

        Iterator<Label> iterator = all.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            Label label = iterator.next();
            LabelListDTO dto = new LabelListDTO();
            dto.setId(label.getId());
            dto.setTitle(label.getTitle());
            dto.setBackgroundColor(label.getBackgroundColor());
            labelList.add(dto);
            size++;
        }

        assertEquals(size, labelList.size());
    }

    @Test
    @DisplayName("라벨 필터링")
    void getLabelFilter() {
    }

    @Test
    @DisplayName("라벨 저장")
    void insert() {
        Label label = Label.builder()
                .id(null)
                .title("감자 라벨")
                .backgroundColor("black")
                .fontColor("yellow")
                .description("이슈1")
                .build();

        Label save = repository.save(label);
        assertEquals(label.getId(), save.getId());
    }

    @Test
    @DisplayName("모든 라벨 삭제")
    void delete() {
        Long before = repository.count();
        Label label = Label.builder()
                .id(null)
                .title("감자 라벨")
                .backgroundColor("black")
                .fontColor("yellow")
                .description("이슈1")
                .build();

        Label save = repository.save(label);

        Long after = repository.count();
        repository.delete(save);
        assertEquals(before, after - 1);
    }

    @Test
    @DisplayName("모든 라벨 수정")
    void update() {
        Label label = Label.builder()
                .id(null)
                .title("감자 라벨")
                .backgroundColor("black")
                .fontColor("yellow")
                .description("이슈1")
                .build();

        repository.save(label);

        label.update(new PostingLabelDTO("고구마 라벨", "안녕", "black"));
        repository.save(label);

        Label findLabel = repository.findById(label.getId()).get();
        assertEquals("고구마 라벨", findLabel.getTitle());
    }

    @Test
    @DisplayName("이슈 에있는 라벨 삭제")
    void issueDeleteLabel() {

    }
}