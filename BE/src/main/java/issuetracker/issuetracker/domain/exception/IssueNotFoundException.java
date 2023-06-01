package issuetracker.issuetracker.domain.exception;

public class IssueNotFoundException extends ElementNotFoundException {
    public IssueNotFoundException() {
        super("이슈를 찾을수 없습니다.");
    }
}
