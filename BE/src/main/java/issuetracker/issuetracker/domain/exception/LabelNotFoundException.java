package issuetracker.issuetracker.domain.exception;

public class LabelNotFoundException extends ElementNotFoundException {
    public LabelNotFoundException() {
        super("라벨을 찾을수 없습니다.");
    }
}
