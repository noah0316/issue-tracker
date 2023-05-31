package issuetracker.issuetracker.domain.milestone;

import issuetracker.issuetracker.domain.milestone.dto.MileStoneDTO;
import issuetracker.issuetracker.domain.milestone.dto.MilestoneFilterDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone, Long> {

    @Query("SELECT " +
            "milestone.milestone_id AS id, " +
            "milestone.title AS title " +
            "FROM milestone")
    List<MilestoneFilterDTO> getMilestoneFilter();


    @Query("SELECT " +
            "milestone.milestone_id AS id, " +
            "milestone.title AS title " +
            "FROM milestone " +
            "WHERE milestone.milestone_id = :id")
    MileStoneDTO findByMilestoneInIssue(@Param("id") @NotNull Long id);
}
