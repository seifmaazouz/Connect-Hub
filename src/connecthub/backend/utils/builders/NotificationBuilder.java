package connecthub.backend.utils.builders;

import connecthub.backend.models.Notification;

public class NotificationBuilder {
    private String message;
    private Notification.Type type;
    private String senderUserId;
    private String contentId;

    public NotificationBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationBuilder setType(Notification.Type type) {
        this.type = type;
        return this;
    }

    public NotificationBuilder setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }

    public NotificationBuilder setContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }

    public Notification build() {
        return new Notification(message, type, senderUserId, contentId);
    }
}
