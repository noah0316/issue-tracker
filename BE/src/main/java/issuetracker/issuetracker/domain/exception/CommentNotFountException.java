package issuetracker.issuetracker.domain.exception;

public class CommentNotFountException extends ElementNotFoundException {

    public CommentNotFountException() {
        super("코멘트를 찾을수 없습니다");
    }
}
