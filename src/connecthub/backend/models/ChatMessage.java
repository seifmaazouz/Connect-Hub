package connecthub.backend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatMessage {
    LocalDateTime timeStamp;
    String senderId;
    String messageContent;

    public ChatMessage(ArrayList<String> entry) {
        this.timeStamp = LocalDateTime.parse(entry.get(0));
        this.senderId = entry.get(1);
        this.messageContent = entry.get(2);
    }

    public ChatMessage(String senderId, String messageContent) {
        this.timeStamp = LocalDateTime.now();
        this.senderId = senderId;
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return timeStamp.toString() + "; " + senderId + "; \"" + messageContent + "\"";
    }

    public ArrayList<String> toArrayList() {
        ArrayList<String> entry = new ArrayList<>();
        entry.add(timeStamp.toString());
        entry.add(senderId);
        entry.add(messageContent);
        return entry;
    }
}
