package issuetracker.issuetracker.domain.milestone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MileRepositoryTest {

    public final MilestoneRepository repository;

    @Autowired
    MileRepositoryTest(MilestoneRepository repository) {
        this.repository = repository;
    }

    @Test
    @DisplayName("마일스톤 db 저장테스트")
    public void basicSave() {
        Milestone milestone = Milestone.builder()
                .milestoneId(null)
                .title("1")
                .description("123")
                .createTime(LocalDateTime.now())
                .isDelete(false)
                .build();

        Milestone save = repository.save(milestone);
        assertEquals(milestone.getMilestoneId(), save.getMilestoneId());
        assertEquals(milestone.getTitle(), save.getTitle());
        assertEquals(milestone.getDescription(), save.getDescription());
    }


    @Test
    @DisplayName("마일스톤 db 삭제 테스트")
    public void basicDelete() {
        Milestone milestone = Milestone.builder()
                .milestoneId(null)
                .title("1")
                .description("123")
                .createTime(LocalDateTime.now())
                .isDelete(false)
                .build();

        Milestone save = repository.save(milestone);
        Optional<Milestone> deletedMilestone = repository.findById(save.getMilestoneId());
        assertFalse(deletedMilestone.isPresent());
    }

    @Test
    @DisplayName("마일스톤 db select")
    public void basicSelect() {
        Milestone milestone = Milestone.builder()
                .milestoneId(null)
                .title("1")
                .description("123")
                .createTime(LocalDateTime.now())
                .isDelete(false)
                .build();

        Milestone save = repository.save(milestone);
        Optional<Milestone> findMilestone = repository.findById(save.getMilestoneId());
        assertEquals(findMilestone.get().getMilestoneId(), save.getMilestoneId());
    }
}