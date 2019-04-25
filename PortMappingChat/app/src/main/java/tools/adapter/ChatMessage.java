package tools.adapter;

public class ChatMessage {
    private boolean isMe;
    private String message;

    public ChatMessage(boolean isMe, String message) {
        this.isMe = isMe;
        this.message = message;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
