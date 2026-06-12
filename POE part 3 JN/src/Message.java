import java.util.Random;

public class Message {

    private static int totalMessagesSent = 0;
    private static int messageCounter = 0;

    private String messageId;
    private int messageNumber;
    private String recipient;
    private String text;
    private String status;
    private String messageHash;

    public Message(String recipient, String text) {
        this.recipient = recipient;
        this.text = text;
        this.messageNumber = messageCounter++;
        this.messageId = generateMessageId();
        this.messageHash = createMessageHash();
    }


    public boolean checkMessageId() {
        return messageId.length() <= 10;
    }


    public String checkRecipientCell() {
        if (recipient.startsWith("+") && recipient.length() <= 10) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String validateMessageLength() {
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = text.length() - 250;
            return "Message exceeds 250 characters by " + extra + ": please reduce the size";
        }
    }

    public String createMessageHash() {
        String firstTwo = messageId.substring(0, 2);
        String[] words = text.split(" ");
        String first = words[0];
        String last = words[words.length - 1];
        return (firstTwo + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public String sentMessage(int option) {
        switch (option) {
            case 1:
                status = "SENT";
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                status = "DISCARDED";
                return "Press 0 to delete the message";
            case 3:
                status = "STORED";
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    private String generateMessageId() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    public String getMessageId()   { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient()   { return recipient; }
    public String getText()        { return text; }
    public String getStatus()      { return status; }

    public String getSender() {
    }
}

