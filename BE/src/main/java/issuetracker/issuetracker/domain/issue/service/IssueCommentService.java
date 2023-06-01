package issuetracker.issuetracker.domain.issue.service;

import issuetracker.issuetracker.domain.comment.Comment;
import issuetracker.issuetracker.domain.comment.CommentRepository;
import issuetracker.issuetracker.domain.exception.CommentNotFountException;
import issuetracker.issuetracker.domain.exception.IssueNotFoundException;
import issuetracker.issuetracker.domain.exception.MemberNotFoundException;
import issuetracker.issuetracker.domain.issue.Issue;
import issuetracker.issuetracker.domain.issue.dto.CommentInIssueDTO;
import issuetracker.issuetracker.domain.issue.dto.Request.CommentPostDTO;
import issuetracker.issuetracker.domain.issue.dto.IssueCommentDto;
import issuetracker.issuetracker.domain.issue.repository.IssueRepository;
import issuetracker.issuetracker.domain.user.Member;
import issuetracker.issuetracker.domain.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IssueCommentService {

    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public IssueCommentDto creatComment(Long loginId, Long issueId, CommentPostDTO commentPostDTO) {
        Issue issue = findIssueById(issueId);
        Member member = findMemberById(loginId);
        //TODO 로그인된 사용자의 아이디가 들어와야 할 거같음
        Comment comment = Comment.create(member.getId(), issue.getId(), commentPostDTO.getComment());
        //   Issue updatedIssue = issueRepository.save(issue.addComment(comment));
        commentRepository.save(comment);

        return IssueCommentDto.builder()
                .issueId(issueId)
                .comment(commentPostDTO.getComment())
                .fileAttachmentUrl(commentPostDTO.getFileAttachmentUrl())
                .build();
    }


    public void updateComment(Long commentId, CommentPostDTO commentPostDTO) {
        Comment comment = findCommentById(commentId);
        Issue issue = findIssueById(commentPostDTO.getIssueId());
        Member member = findMemberById(commentPostDTO.getUserId());
        Stream<Comment> commentStream = Stream.of(comment);
        System.out.println(issue.getId() == (comment.getMemberId().getId()) || member.getId() == (comment.getMemberId().getId()));

        System.out.println("1 = " + comment.getMemberId().getId()); //코멘트 작성자 2
        System.out.println("1 = " + member.getId());  // 수정하려는 작성자 2
        System.out.println("2 = " + comment.getIssueId().getId());
        System.out.println("2 = " + issue.getId()); // 2
        System.out.println("3 = " + commentId); //2
        System.out.println("3 = " + commentId); // 2


        commentStream.filter(c -> issue.getId() == (c.getMemberId().getId())
                        || member.getId() == (c.getMemberId().getId()))
                .forEach(el -> {
                    System.out.println("el = " + el);
                    comment.update(commentPostDTO);
                    commentRepository.save(comment);
                });
    }

    public void deleteComment(Long userId, Long issueId, Long commentId) {
        if (commentRepository.existsById(commentId)) {
            Comment comment = findCommentById(commentId);
            Issue issue = findIssueById(issueId);
            Member member = findMemberById(userId);

            Stream<Comment> commentStream = Stream.of(comment);

            // 멤버와 이슈의 ID가 일치하는 경우에만 삭제
            commentStream.filter(c -> issue.getId() == (c.getMemberId().getId())
                            || member.getId() == (c.getMemberId().getId()))
                    .forEach(commentRepository::delete);
        }
    }


    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFountException());
    }

    private Member findMemberById(Long loginId) {
        return memberRepository.findById(loginId).orElseThrow(() -> new MemberNotFoundException());
    }

    private Issue findIssueById(Long issueId) {
        return issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException());
    }

    public List<CommentInIssueDTO> readComment(Long issueId) {
        List<Comment> comments = commentRepository.findByIssueId(AggregateReference.to(issueId));
        List<CommentInIssueDTO> commentInIssueDTOS = new ArrayList<>();

        for (Comment comment : comments) {
            Member member = findMemberById(comment.getMemberId().getId());

            commentInIssueDTOS.add(CommentInIssueDTO.builder()
                    .userId(comment.getId())
                    .userName(member.getName())
                    .userUrl(member.getProfileUrl())
                    .replyContents(comment.getContents())
                    .createTime(comment.getCreateTime())
                    .emojiCount(null)
                    .emojiShape(null)
                    .fileAttachmentUrl(null)
                    .build());
        }

        return commentInIssueDTOS;
    }
}
