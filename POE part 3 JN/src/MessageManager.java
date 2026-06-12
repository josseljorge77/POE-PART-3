import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessageManager {

    // Parte 2 — você já tinha
    private ArrayList<Message> sentMessages = new ArrayList<>();

    // Parte 3 — arrays exigidos
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();


    public MessageManager() {
        loadStoredMessagesFromJSON();
    }

    // Parte 2 — manter
    public void addMessage(Message m) {
        sentMessages.add(m);
    }

    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append("Message ID: ").append(m.getMessageId()).append("\n");
            sb.append("Message Hash: ").append(m.getMessageHash()).append("\n");
            sb.append("Recipient: ").append(m.getRecipient()).append("\n");
            sb.append("Message: ").append(m.getText()).append("\n");
            sb.append("-------------------------\n");
        }
        return sb.toString();
    }


    public void loadStoredMessagesFromJSON() {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Message>>() {}.getType();
            FileReader reader = new FileReader("messages.json");

            storedMessages = gson.fromJson(reader, listType);
            reader.close();

            if (storedMessages == null) {
                storedMessages = new ArrayList<>();
            }

            for (Message msg : storedMessages) {
                messageHashes.add(msg.getMessageHash());
                messageIDs.add(msg.getMessageId());
            }

        } catch (Exception e) {
            System.out.println("No stored messages found.");
        }
    }


    public void displayStoredSendersAndRecipients() {
        for (Message msg : storedMessages) {
            System.out.println("Sender: " + msg.getSender() +
                    " | Recipient: " + msg.getRecipient());
        }
    }


    public void displayLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }

        Message longest = storedMessages.get(0);

        for (Message msg : storedMessages) {
            if (msg.getText().length() > longest.getText().length()) {
                longest = msg;
            }
        }

        System.out.println("Longest message:");
        System.out.println(longest.getText());
    }


    public void searchByMessageID(String id) {
        for (Message msg : storedMessages) {
            if (msg.getMessageId().equals(id)) {
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getText());
                return;
            }
        }
        System.out.println("Message ID not found.");
    }


    public void searchMessagesByRecipient(String recipient) {
        boolean found = false;

        for (Message msg : storedMessages) {
            if (msg.getRecipient().equalsIgnoreCase(recipient)) {
                System.out.println("Message ID: " + msg.getMessageId());
                System.out.println("Message: " + msg.getText());
                System.out.println("----------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }


    public void deleteMessageByHash(String hash) {
        for (Message msg : storedMessages) {
            if (msg.getMessageHash().equals(hash)) {
                storedMessages.remove(msg);
                System.out.println("Message deleted.");
                return;
            }
        }
        System.out.println("Hash not found.");
    }


    public void displayFullReport() {
        for (Message msg : storedMessages) {
            System.out.println("ID: " + msg.getMessageId());
            System.out.println("Sender: " + msg.getSender());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getText());
            System.out.println("Hash: " + msg.getMessageHash());
            System.out.println("---------------------------");
        }
    }

    public MessageManager() {
        loadStoredMessagesFromJSON();
        loadTestData();
    }
        public void loadTestData () {

            Message m1 = new Message("+27834557896", "Did you get the cake?");
            m1.sentMessage(1);
            sentMessages.add(m1);

            Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
            m2.sentMessage(3);
            storedMessages.add(m2);

            Message m3 = new Message("+27834484567", "Yohoooo, I am at your gate.");
            m3.sentMessage(2);
            disregardedMessages.add(m3);

            Message m4 = new Message("08388884567", "It is dinner time!");
            m4.sentMessage(1);
            sentMessages.add(m4);

            Message m5 = new Message("+27838884567", "Ok, I am leaving without you.");
            m5.sentMessage(3);
            storedMessages.add(m5);
        }


}
