package issuetracker.issuetracker.domain.issue;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum State {
    OPEN, CLOSE;

    @JsonCreator
    public static State getEnumFromValue(String value) {
        try {
            return State.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
