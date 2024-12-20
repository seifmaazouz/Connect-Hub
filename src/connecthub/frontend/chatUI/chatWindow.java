package connecthub.frontend.chatUI;

import connecthub.backend.models.Chat;
import connecthub.backend.models.ChatMessage;
import connecthub.backend.models.User;
import connecthub.backend.services.ChatService;
import connecthub.backend.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class chatWindow extends JFrame{

    private User activeUser;
    private User friend;
    private Chat chat;
    private ChatService cs;

    public chatWindow(User me, User he) throws IOException {
        this.cs = new ChatService();
        this.activeUser = me;
        this.friend = he;
        this.chat = cs.loadHistory(activeUser.getUserId(), friend.getUserId());

        setContentPane(panel1);
        setTitle("Chat with " + friend.getUsername());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        sendButton.addActionListener(e -> messageSent());
        textField1.addActionListener(e -> messageSent());

        chatBody.setVisible(true);

        for (ChatMessage message : chat.getMessages()) {
            ChatMessageEntry entry = new ChatMessageEntry(UserService.getInstance().getUserById(message.getSenderId()), message.getMessageContent());
            chatBody.add(entry);
            revalidate();
            repaint();
        }
    }

    private void messageSent() {
        String message = textField1.getText();
        if (message.isEmpty()) {
            return;
        }
        System.out.println("Message: " + message);
        chat.sendMessage(activeUser.getUserId(), message);
        chatBody.add(new ChatMessageEntry(activeUser, message));
        chatBody.revalidate();
        chatBody.repaint();
        try {
            cs.saveHistory(chat);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        textField1.setText("");
    }

    public static void main(String[] args) {
        User me = UserService.getInstance().getUserById("1023");
        User he = UserService.getInstance().getUserById("1022");
        try {
            new chatWindow(me, he).setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JPanel panel1;
    private JButton sendButton;
    private JTextField textField1;
    private JScrollPane chatBody;
}
