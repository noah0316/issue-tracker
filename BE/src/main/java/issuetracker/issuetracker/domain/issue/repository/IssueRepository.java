package issuetracker.issuetracker.domain.issue.repository;

import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    Issue findIssueById(Long issueId);

    @Query("SELECT i FROM issue i JOIN i.label l WHERE l.id = ?;")
    List<Issue> findByLabels(Long id);

}
