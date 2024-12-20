package connecthub.backend.models;

import connecthub.backend.services.ChatService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat {
    private String chatId;
    private ArrayList<ChatMessage> chatHistory;

    public Chat(String chatId) {
        this.chatHistory = new ArrayList<>();
        this.chatId = chatId;
    }

    public String getChatId() { return chatId; }

    public int getSize() { return chatHistory.size(); }

    public void addToMessages(ChatMessage message) { this.chatHistory.add(message); }

    public void sendMessage(String sender, String message) {
        ChatMessage chatMessage = new ChatMessage(sender, message);
        this.chatHistory.add(chatMessage);
    }

    public void preview() {
        for (ChatMessage message : chatHistory) {
            System.out.println(message.toString());
        }
    }

    public ArrayList<ChatMessage> getMessages() {
        return chatHistory;
    }

    public static void main(String[] args) {
        ChatService cs = new ChatService();
        try {
            Chat chat = cs.loadHistory("1022", "1023");
            chat.preview();
            chat.sendMessage("1022", "Hello, 1023!");
            chat.preview();
            cs.saveHistory(chat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
