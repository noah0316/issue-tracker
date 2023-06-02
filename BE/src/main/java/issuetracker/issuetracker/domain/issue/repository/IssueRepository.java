package issuetracker.issuetracker.domain.issue.repository;

import issuetracker.issuetracker.domain.comment.Comment;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.issue.dto.Request.IssueStateListDTO;
import issuetracker.issuetracker.domain.label.Label;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    Issue findIssueById(Long issueId);

    @Query("SELECT i FROM issue i JOIN i.label l WHERE l.id = ?;")
    List<Issue> findByLabels(Long id);

}
