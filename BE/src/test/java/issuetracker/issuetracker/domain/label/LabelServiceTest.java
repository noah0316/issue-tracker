package issuetracker.issuetracker.domain.label;

import com.fasterxml.jackson.databind.ObjectMapper;
import issuetracker.issuetracker.domain.issue.Assignee;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.issue.repository.IssueRepository;
import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.label.dto.LabelFilterDTO;
import issuetracker.issuetracker.domain.milestone.Milestone;
import issuetracker.issuetracker.domain.user.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LabelServiceTest {

    @Autowired
    private LabelService labelService;
    private LabelRepository repository;
    private IssueRepository issueRepository;


    private final AggregateReference<Milestone, Long> mileId = AggregateReference.to(1L);
    private final AggregateReference<Member, Long> userId = AggregateReference.to(1L);
    private final List<Long> labelIds = List.of(
            1L,
            2L,
            3L
    );


    private final List<Long> assigneesIds = List.of(
            1L,
            2L
    );

    private final List<Issue> issues = List.of(
            Issue.builder()
                    .id(1L)
                    .title("issue 1")
                    .isDelete(false)
                    .isOpen(true)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .attachedLabels(new ArrayList<>(List.of(
                            IssueAttachedLabel.builder()
                                    .labelId(AggregateReference.to(labelIds.get(0)))
                                    .build(),
                            IssueAttachedLabel.builder()
                                    .labelId(AggregateReference.to(labelIds.get(1)))
                                    .build()
                    )))
                    .assignees(new ArrayList<>(List.of(
                            Assignee.builder()
                                    .memberId(AggregateReference.to(assigneesIds.get(0)))
                                    .build(),
                            Assignee.builder()
                                    .memberId(AggregateReference.to(assigneesIds.get(1)))
                                    .build()
                    )))
                    .milestoneId(mileId)
                    .author(userId)
                    .build()
    );
    // TODO: DB 변경 후 테스트 수정할 것
    @Test
    @DisplayName("라벨 필터 팝업 데이터는 4개가 저장되어 있으므로, 4개가 나와야 한다.")
    void getLabelFilter() {
        //when
        List<LabelFilterDTO> labelFilter = labelService.getLabelFilter();

        // then
        Assertions.assertThat(labelFilter.size()).isEqualTo(4);
    }

    @Test
    @Transactional
    @DisplayName("라벨 생성")
    void create() throws Exception {
        String name = "gamja";
        String description = "기능 향상";
        String backgroundColor = "#F00000";
        String fontColor = "yellow";

        LabelDTO labelDTO = new LabelDTO(4L, name, backgroundColor, fontColor);
    }


    @Test
    @Transactional
    @DisplayName("라벨 삭제, 이슈가 가지고있는 라벨 삭제")
    void delete() {
//        Label label = repository.findById(labelId).get();
//        //TODO 이슈에있는 라벨도 삭제해야함.
//        List<Issue> issues = issueRepository.findByLabels(label);
//        log.debug("라벨 id 에 해당하는 issue 리스트 출력 = {}", issues);
//        for (Issue issue : issues) {
//            issue.getAttachedLabels().remove(label);
//        }
//
//        issueRepository.saveAll(issues);
//        repository.delete(label);
    }

}