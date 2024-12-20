package connecthub.frontend.chatUI;

import connecthub.backend.background.ChatFetcher;
import connecthub.backend.models.Chat;
import connecthub.backend.models.ChatMessage;
import connecthub.backend.models.User;
import connecthub.backend.services.ChatService;
import connecthub.backend.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class chatWindow extends JFrame {
    private User activeUser;
    private User friend;
    private Chat chat;
    private ChatService cs;
    private ChatFetcher chatFetcher;

    private JPanel chatPanel; // Holds chat message entries

    public chatWindow(User me, User he) throws IOException {
        this.cs = new ChatService();
        this.activeUser = me;
        this.friend = he;
        this.chat = cs.loadHistory(activeUser.getUserId(), friend.getUserId());

        // Initialize JFrame properties
        setTitle("Chat with " + friend.getUsername());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Main panel setup
        panel1 = new JPanel(new BorderLayout());

        // Chat panel inside the scroll pane
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS)); // Vertical layout for chat messages
        chatPanel.setBackground(Color.WHITE);

        chatBody = new JScrollPane(chatPanel);
        chatBody.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Input panel with text field and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        textField1 = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(textField1, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add components to main panel
        panel1.add(chatBody, BorderLayout.CENTER);
        panel1.add(inputPanel, BorderLayout.SOUTH);

        setContentPane(panel1);

        // Add chat messages from history
        for (ChatMessage message : chat.getMessages()) {
            Color color = message.getSenderId().equals(activeUser.getUserId()) ? Color.decode("#20A520") : Color.BLUE;
            ChatMessageEntry entry = new ChatMessageEntry(UserService.getInstance().getUserById(message.getSenderId()), message.getMessageContent(), color);
            chatPanel.add(entry, BorderLayout.WEST);
        }

        SwingUtilities.invokeLater(() -> {
            chatBody.getVerticalScrollBar().setUnitIncrement(8);
            chatBody.getVerticalScrollBar().setValue(chatBody.getVerticalScrollBar().getMaximum());
        });

        // Ensure the panel updates
        chatPanel.revalidate();
        chatPanel.repaint();

        // Set up listeners
        sendButton.addActionListener(e -> messageSent());
        textField1.addActionListener(e -> messageSent());

        // create chatFetcher thread
        this.chatFetcher = new ChatFetcher(this);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                chatFetcher.stop();
                dispose();
            }
        });

        setVisible(true);
    }



    private void messageSent() {
        String message = textField1.getText();
        if (message.isEmpty()) {
            return;
        }
        System.out.println("Message: " + message);
        chat.sendMessage(activeUser.getUserId(), message);

        // Add the new message to the chat panel
        ChatMessageEntry entry = new ChatMessageEntry(activeUser, message, Color.decode("#20A520"));
        chatPanel.add(entry);
        chatPanel.revalidate();
        chatPanel.repaint();
        chatBody.getVerticalScrollBar().setValue(chatBody.getVerticalScrollBar().getMaximum());

        // Save the chat history
        try {
            cs.saveHistory(chat);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Clear the input field
        textField1.setText("");
    }

    public void refreshChat() throws IOException {
        chat = cs.loadHistory(activeUser.getUserId(), friend.getUserId());
        chatPanel.removeAll();
        for (ChatMessage message : chat.getMessages()) {
            Color color = message.getSenderId().equals(activeUser.getUserId()) ? Color.decode("#20A520") : Color.BLUE;
            ChatMessageEntry entry = new ChatMessageEntry(UserService.getInstance().getUserById(message.getSenderId()), message.getMessageContent(), color);
            chatPanel.add(entry, BorderLayout.WEST);
        }
        chatPanel.revalidate();
        chatPanel.repaint();
        chatBody.getVerticalScrollBar().setValue(chatBody.getVerticalScrollBar().getMaximum());
    }

    public static void main(String[] args) {
        User me = UserService.getInstance().getUserById("1022");
        User he = UserService.getInstance().getUserById("1023");
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