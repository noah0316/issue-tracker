package issuetracker.issuetracker.domain.exception;

public class MemberNotFoundException extends ElementNotFoundException {
    public MemberNotFoundException() {
        super("멤버를 찾을수 없습니다.");
    }
}
