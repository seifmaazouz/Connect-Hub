package connecthub.backend.background;

import connecthub.frontend.chatUI.chatWindow;

import javax.swing.*;
import java.io.IOException;

public class ChatFetcher implements Runnable {
    private static final long FetchInterval = 1000;
    private boolean running = true;
    private final chatWindow window;

    public ChatFetcher(chatWindow window) {
        this.window = window;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                window.refreshChat();
                Thread.sleep(FetchInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        running = false;
    }
}
