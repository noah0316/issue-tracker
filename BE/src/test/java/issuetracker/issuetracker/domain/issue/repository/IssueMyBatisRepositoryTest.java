package issuetracker.issuetracker.domain.issue.repository;

import issuetracker.issuetracker.domain.issue.IssueFilterCondition;
import issuetracker.issuetracker.domain.issue.dto.IssueDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional(readOnly = true)
class IssueMyBatisRepositoryTest {

    @Autowired
    private IssueMybatisRepository repository;

    // TODO: 서버와 동일한 DB를 사용해서 테스트가 너무 제한적 -> 테스트용 DB로 교체 예정
    @Test
    @DisplayName("모든 IssueList 조회 테스트 ")
    void findAllList() {
        // given
        List<Long> assignees = List.of(1L);
        List<Long> labels = List.of(1L);

        Long authorId = 1L;
        Boolean isOpen = true;
        IssueFilterCondition issueFilterCondition = new IssueFilterCondition(assignees, labels, null, authorId, isOpen, null, null, null);


        // when
        List<IssueDTO> issueFilter = repository.findIssueFilter(issueFilterCondition);


        // then
        Assertions.assertThat(issueFilter.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("마이바티스 필터링 테스트, 담당자가 5명인 경우는 없으므로 0이 나와야 한다.")
    void 메서드명() {
        // given
        List<Long> assignees = List.of(1L, 2L, 3L, 4L, 5L);
        List<Long> labels = List.of(1L);

        Long authorId = 1L;
        Boolean isOpen = true;
        IssueFilterCondition issueFilterCondition = new IssueFilterCondition(assignees, labels, null, authorId, isOpen, null, null, null);


        // when
        List<IssueDTO> issueFilter = repository.findIssueFilter(issueFilterCondition);


        // then
        Assertions.assertThat(issueFilter.size()).isEqualTo(0);

    }


}