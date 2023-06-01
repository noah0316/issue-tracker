package issuetracker.issuetracker.domain.comment;

import issuetracker.issuetracker.domain.issue.Issue;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByIssueId(AggregateReference<Issue, Long> issueId);
}
