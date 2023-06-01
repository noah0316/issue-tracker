package issuetracker.issuetracker.domain.issue.service;

import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.issue.dto.IssueDetailLabelDto;
import issuetracker.issuetracker.domain.issue.repository.IssueRepository;
import issuetracker.issuetracker.domain.label.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class IssueUtilService {
    private final IssueRepository issueRepository;
    //TODO 상당히 비효율적 개선필요 마일스톤
    public Long countOpenedIssues(Long milestoneId) {
        Stream<Issue> allIssue = StreamSupport.stream(issueRepository.findAll().spliterator(), false);
        Long count = allIssue.filter(issue -> issue.getMilestoneId().getId() == milestoneId)
                .filter(issue -> true == (issue.getIsOpen()))
                .count();

        return count;
    }

    public Long countClosedIssues(Long milestoneId) {
        Stream<Issue> allIssue = StreamSupport.stream(issueRepository.findAll().spliterator(), false);
        Long count = allIssue.filter(issue -> issue.getMilestoneId().getId() == milestoneId)
                .filter(issue -> false == (issue.getIsOpen()))
                .count();

        return count;
    }

    public List<Issue> getIssues() {
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : issueRepository.findAll()) {
            issues.add(issue);
        }
        return issues;
    }


    public void issueMilestoneUpdate(long milestoneId) {
        getIssues().stream()
                .filter(issue -> issue.getMilestoneId() != null && issue.getMilestoneId().getId() == milestoneId)
                .forEach(issue -> {
                    issue.deleteMilestone();
                    issueRepository.save(issue);
                });
    }

    public void issueLabelUpdate(Label label) {
//        List<AggregateReference<Label, Long>> byAttachedLabels = issueRepository.findByAttachedLabels();
//        System.out.println("byAttachedLabels = " + byAttachedLabels);

    }
}
