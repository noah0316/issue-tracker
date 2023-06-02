package issuetracker.issuetracker.domain.issue;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import issuetracker.issuetracker.domain.issue.dto.CommentInIssueDTO;
import issuetracker.issuetracker.domain.issue.dto.Request.CommentPostDTO;
import issuetracker.issuetracker.domain.issue.dto.IssueDTO;
import issuetracker.issuetracker.domain.issue.dto.IssueDetailDTO;
import issuetracker.issuetracker.domain.issue.dto.IssueDetailLabelDto;
import issuetracker.issuetracker.domain.issue.dto.Request.IssueStateListDTO;
import issuetracker.issuetracker.domain.issue.dto.Request.IssueTitleDTO;
import issuetracker.issuetracker.domain.issue.dto.Request.PostingIssueDTO;
import issuetracker.issuetracker.domain.issue.repository.IssueMybatisRepository;
import issuetracker.issuetracker.domain.issue.dto.IssueCommentDto;
import issuetracker.issuetracker.domain.issue.service.IssueCommentService;
import issuetracker.issuetracker.domain.issue.service.IssueService;
import issuetracker.issuetracker.domain.label.Label;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "posts", description = "issue 메인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/issues")
public class IssueController {

    private final IssueMybatisRepository repository;
    private final IssueService issueService;
    private final IssueCommentService issueCommentService;
    private final Logger log = LoggerFactory.getLogger(IssueController.class);

    @GetMapping
    public List<IssueDTO> getAllIssues(@ModelAttribute IssueFilterCondition issueFilterCondition) {
        log.debug("모든 이슈 출력  필터링 적용");
        return repository.findIssueFilter(issueFilterCondition);
    }

    @PostMapping
    public Long postIssue(@RequestBody PostingIssueDTO issue) {
        // 저장하는 로직 추가
        log.debug("이슈저장");
        return issueService.save(issue);
    }

    @Operation(
            summary = "이슈 하나 조회",
            tags = "issue",
            description = "."
    )
    @GetMapping("/{issueId}")
    public IssueDetailDTO explainIssue(@PathVariable Long issueId) {
        // findByIssue
        log.debug("이슈 하나 조회");
        return issueService.findByIssueDetailPage(issueId);
    }

    @PatchMapping("/{issueId}/title")
    public void updateTitle(@PathVariable Long issueId, @RequestBody IssueTitleDTO issueTitleDTO) {
        // 수정하는 메서드 생성
        log.debug("이슈 제목 편집");
        issueService.updateIssueTitle(issueId, issueTitleDTO);
    }

    @Operation(
            summary = "이슈 상태 수정",
            tags = "issue",
            description = "사용자는 이슈 상태만 수정할 수 있다."
    )
    @PatchMapping("/{issueId}")
    public void updateIssueStatus(@PathVariable Long issueId, @RequestBody State state) {
        // 수정하는 메서드 생성
        log.debug("이슈 오픈,클로스 상태 수정");
        issueService.updateStatus(issueId, state);
    }

    @Operation(
            summary = "다중 이슈 상태 수정",
            tags = "issue",
            description = "사용자는 다중 이슈 상태 수정 할 수 있다."
    )
    @PatchMapping
    public void updateIssueStatus(@RequestBody IssueStateListDTO issueStateListDTO) {
        // 수정하는 메서드 생성
        log.debug("여러 이슈 오픈,클로스 상태 수정");
        issueService.updateListStatus(issueStateListDTO);
    }

    @PatchMapping("/{issueId}/labels")
    public void updateLabels(@PathVariable Long issueId, @RequestBody PostingIssueDTO issue) {
        // 수정하는 메서드 생성
        log.debug("이슈 레이블 편집");
        issueService.updateLabels(issueId, issue);
    }

    @PatchMapping("/{issueId}/comments")
    public void updateCommnet(@PathVariable Long issueId, @RequestBody PostingIssueDTO issue) {
        // 수정하는 메서드 생성
        log.debug("이슈 코멘트 편집");
    }

    @PatchMapping("/{issueId}/assignees")
    public void updateAssignees(@PathVariable Long issueId, @RequestBody PostingIssueDTO issue) {
        // 수정하는 메서드 생성
        log.debug("이슈 담당자 편집");
    }

    @DeleteMapping("/{issueId}")
    public void deleteIssue(@PathVariable Long issueId) {
        log.debug("이슈의 삭제");
        issueService.deleteIssue(issueId);
    }

    //TODO 이슈에 있는 라벨 삭제..
    @PutMapping("/{issueId}/remove")
    public IssueDetailLabelDto deleteAttachedLabels(@PathVariable Long issueId, @RequestBody Label label) {
        log.debug("이슈에 있는 라벨삭제");
        return issueService.removeAttachedLabels(issueId, label);
    }

    @Operation(
            summary = "이슈에 맞는 코멘트 출력 ",
            tags = "comment",
            description = "."
    )
    @GetMapping("/{issueId}/comments")
    public List<CommentInIssueDTO> showComment(@PathVariable long issueId) {
        // TODO 댓글 리스트 구현
        return issueCommentService.readComment(issueId);
    }

    @Operation(
            summary = "코멘트 작성 ",
            tags = "comment",
            description = "."
    )
    @PostMapping("/{loginId}/{issueId}/comments")
    public IssueCommentDto postComment(@PathVariable Long loginId, @PathVariable Long issueId, @RequestBody CommentPostDTO comment) {
        // TODO 댓글 작성하기 구현
        log.debug("이슈의 코멘트 작성");
        return issueCommentService.creatComment(loginId, issueId, comment);
    }

    @Operation(
            summary = "코멘트 수정 ",
            tags = "comment",
            description = "."
    )
    @PatchMapping("/comments/{commentId}")
    public void updateComment(@PathVariable long commentId,
                              @RequestBody CommentPostDTO comment) {
        // TODO 댓글 수정하기 구현
        // TODO 댓글 작성하기 구현
        log.debug("이슈의 코멘트 수정");
        issueCommentService.updateComment(commentId, comment);
    }

    @Operation(
            summary = "모든 코멘트 출력 ",
            tags = "comment",
            description = "."
    )
    @DeleteMapping("/{issueId}/comments/{commentId}/{userId}")
    public void deleteComment(@PathVariable Long issueId, @PathVariable Long commentId, @PathVariable Long userId) {
        // TODO 댓글 삭제하기 구현
        issueCommentService.deleteComment(userId, issueId, commentId);
    }
}
