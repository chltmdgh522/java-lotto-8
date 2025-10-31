package lotto.global.message;

public enum MessageCode {
    TEST("");
    private final String message;

    MessageCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
