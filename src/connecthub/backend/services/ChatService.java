package connecthub.backend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.database.JSONParser;
import connecthub.backend.models.Chat;
import connecthub.backend.models.ChatMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static connecthub.backend.constants.FilePath.CHATIDS_FILE_PATH;
import static connecthub.backend.constants.FilePath.CHATS_FILE_PATH;


public class ChatService {
    public ChatService() {}

    public Chat loadHistory(String user1, String user2) throws IOException {
        String chatId = getChatId(user1, user2);
        System.out.println(checkHistoryExists(user1, user2));
        HashMap<String, ArrayList<String>> chatData = new JSONParser()
                .readJSON(CHATS_FILE_PATH + "/" + chatId + ".json", new TypeReference<HashMap<String, ArrayList<String>>>() {});
        Chat chat = new Chat(chatId);
        int length = chatData.size();
        for (int i = 0; i < length; i++) {
            ArrayList<String> entry = chatData.get("" + i);
            chat.addToMessages(new ChatMessage(entry));
        }
        return chat;
    }

    public String getChatId(String user1, String user2) throws IOException {
        HashMap<String, String> chatIds = new JSONParser()
                .readJSON(CHATIDS_FILE_PATH, new TypeReference<HashMap<String, String>>() {});

        String chatId = chatIds.get(user1 + "+" + user2);

        if (chatId == null) {
            chatId = user1 + "+" + user2;
            chatIds.put(user1 + "+" + user2, chatId);
            chatIds.put(user2 + "+" + user1, chatId);
            new JSONParser().writeJSON(CHATIDS_FILE_PATH, chatIds);
            new JSONParser().writeJSON(CHATS_FILE_PATH + "/" + chatId + ".json", new HashMap<>());
        }

        return chatId;
    }

    public boolean checkHistoryExists(String user1, String user2) throws IOException {
        String chatId = getChatId(user1, user2);
        HashMap<String, ArrayList<String>> chatData = new JSONParser()
                .readJSON(CHATS_FILE_PATH + "/" + chatId + ".json", new TypeReference<HashMap<String, ArrayList<String>>>() {});
        return !chatData.isEmpty();
    }

    public void saveHistory(Chat chat) throws IOException {
        HashMap<String, ArrayList<String>> chatData = new HashMap<>();
        int length = chat.getSize();
        for (int i = 0; i < length; i++) {
            chatData.put("" + i, chat.getMessages().get(i).toArrayList());
        }
        new JSONParser().writeJSON(CHATS_FILE_PATH + "/" + chat.getChatId() + ".json", chatData);
    }
}
