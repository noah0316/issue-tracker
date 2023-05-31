package issuetracker.issuetracker.domain.milestone;

import issuetracker.issuetracker.domain.milestone.dto.MilestoneFilterDTO;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MilestoneServiceTest {

    @Autowired
    private MilestoneService service;
    private MilestoneRepository repository;

    @Test
    @DisplayName("설명")
    @Transactional
    void milestoneFilterTest() {
        // when
        List<MilestoneFilterDTO> milestoneFilter = service.getMilestoneFilter();

        // then
        Assertions.assertThat(milestoneFilter.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("마일스톤삭제")
    public void delete() {
        Milestone milestone = Milestone.builder()
                .milestoneId(null)
                .title("1")
                .description("123")
                .createTime(LocalDateTime.now())
                .isDelete(false)
                .build();

    }

    @Test
    @DisplayName("마일스톤 수정")
    public void update() {
        Milestone milestone = Milestone.builder()
                .milestoneId(null)
                .title("1")
                .description("123")
                .createTime(LocalDateTime.now())
                .isDelete(false)
                .build();
    }

    @Test
    @DisplayName("마일스톤 저장")
    public void insert() {
        Milestone milestone = Milestone.builder()
                .milestoneId(null)
                .title("1")
                .description("123")
                .createTime(LocalDateTime.now())
                .isDelete(false)
                .build();

    }
}