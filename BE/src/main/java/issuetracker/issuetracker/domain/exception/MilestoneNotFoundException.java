package issuetracker.issuetracker.domain.exception;


public class MilestoneNotFoundException extends ElementNotFoundException {

    public MilestoneNotFoundException() {
        super("마일 스톤을 찾지 못했습니다.");
    }
}
