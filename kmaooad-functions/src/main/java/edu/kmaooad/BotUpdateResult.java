package edu.kmaooad;

public class BotUpdateResult {
    private final boolean success;

    public boolean getSuccess() {
        return success;
    }
    private final String messageId;

    public String getMessageId() {
        return messageId;
    }
    public BotUpdateResult(boolean success, String messageId) {
        this.success = success;
        this.messageId = messageId;
    }
    public static BotUpdateResult Success(String messageId) {
        return new BotUpdateResult(true, messageId);
    }
    public static BotUpdateResult Error(String messageId) {
        return new BotUpdateResult(false, messageId);
    }
}