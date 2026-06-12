import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Enclosed.class)
public class TestMain {

    @Test
    public void TestUsernameCorrectlyFormatted() {
        Login system = new Login();

        system.username = "KyL_1";
        system.password = "Test123!@;";
        system.phonenumber = "+27638399070";

        String result = system.returnLoginStatus("KyL_1", "Test123!@;");
        assertEquals("Welcome KyL_1 it is great to see you again.", result);
    }

    @Test
    public void TestUsernameIncorrectlyFormatted() {
        Login system = new Login();
        boolean result = system.checkUserName("Kyle!!!!!!!");
        Assertions.assertFalse(result);
    }

    @Test
    public void TestPasswordCorrectlyFormatted() {
        Login system = new Login();
        boolean result = system.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void TestPasswordIncorrectlyFormatted() {
        Login system = new Login();
        boolean result = system.checkPasswordComplexity("password");
        Assertions.assertFalse(result);
    }

    @Test
    public void TestCellphoneCorrectlyFormatted() {
        Login system = new Login();
        boolean result = system.checkCellPhoneNumber("+27838968976");
        assertTrue(result);
    }

    @Test
    public void TestCellphoneIncorrectlyFormatted() {
        Login system = new Login();
        boolean result = system.checkCellPhoneNumber("08966553");
        Assertions.assertFalse(result);
    }

    @Test
    public void TestLoginSuccessful() {
        Login system = new Login();

        system.username = "KyL_1";
        system.password = "Test123!@:";
        system.phonenumber = "+27638399070";

        boolean result = system.loginUser("KyL_1", "Test123!@:");
        assertTrue(result);
    }

    @Test
    public void TestLoginFailed() {
        Login system = new Login();

        system.username = "KyL_1";
        system.password = "Test123!@:";
        system.phonenumber = "+27638399070";

        boolean result = system.loginUser("KyL_1", "WrongPassword");
        Assertions.assertFalse(result);
    }
;
    public class TestMessage {

        @Test
        void testCheckMessageId_lengthIsExactlyTen() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            assertTrue(msg.checkMessageId());
            assertEquals(10, msg.getMessageId().length());
        }

        @Test
        void testCheckRecipientCell_valid() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
        }

        @Test
        void testCheckRecipientCell_missingPlus() {
            Message msg = new Message("08575975889", "Hi Keegan, did you receive the payment?");
            assertEquals(
                    "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                    msg.checkRecipientCell()
            );
        }

        @Test
        void testValidateMessageLength_withinLimit() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            assertEquals("Message ready to send.", msg.validateMessageLength());
        }


        @Test
        void testValidateMessageLength_tooLong() {
            String longText = "a".repeat(260);
            Message msg = new Message("+27718693002", longText);
            int extra = 260 - 250;
            assertEquals(
                    "Message exceeds 250 characters by " + extra + ": please reduce the size",
                    msg.validateMessageLength()
            );
        }

        @Test
        void testValidateMessageLength_exactLimit() {
            String exactText = "a".repeat(250);
            Message msg = new Message("+27718693002", exactText);
            assertEquals("Message ready to send.", msg.validateMessageLength());
        }


        @Test
        void testCreateMessageHash_formatIsCorrect() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            String hash = msg.getMessageHash();
            assertNotNull(hash);
            assertTrue(hash.contains(":"));
            assertEquals(hash, hash.toUpperCase());
        }

        @Test
        void testCreateMessageHash_singleWord() {
            Message msg = new Message("+27718693002", "Hello");
            String hash = msg.getMessageHash();
            // Formato: XX:N:HELLOHELLO
            assertTrue(hash.contains("HELLOHELLO"));
        }

        @Test
        void testSentMessage_send() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            assertEquals("Message successfully sent.", msg.sentMessage(1));
        }

        @Test
        void testSentMessage_discard() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            assertEquals("Press 0 to delete the message", msg.sentMessage(2));
        }

        @Test
        void testSentMessage_store() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            assertEquals("Message successfully stored.", msg.sentMessage(3));
        }

        @Test
        void testSentMessage_invalidOption() {
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            assertEquals("Invalid option.", msg.sentMessage(99));
        }


        @Test
        void testReturnTotalMessages_incrementsOnSend() {
            int before = Message.returnTotalMessages();
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            msg.sentMessage(1);
            assertEquals(before + 1, Message.returnTotalMessages());
        }


        @Test
        void testReturnTotalMessages_doesNotIncrementOnDiscard() {
            int before = Message.returnTotalMessages();
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            msg.sentMessage(2);
            assertEquals(before, Message.returnTotalMessages());
        }
    }


    public class TestMessageManager {

        @Test
        void testPrintMessages_containsAllFields() {
            MessageManager manager = new MessageManager();
            Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            msg.sentMessage(1);
            manager.addMessage(msg);

            String output = manager.printMessages();

            assertTrue(output.contains("Message ID: "));
            assertTrue(output.contains("Message Hash: "));
            assertTrue(output.contains("Recipient: "));
            assertTrue(output.contains("Message: "));
        }

        @Test
        void testPrintMessages_emptyList() {
            MessageManager manager = new MessageManager();
            assertEquals("", manager.printMessages());
        }

        @Test
        void testAddMessage_multipleMessages() {
            MessageManager manager = new MessageManager();
            Message msg1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
            Message msg2 = new Message("+27831234567", "Hello world");
            manager.addMessage(msg1);
            manager.addMessage(msg2);

            String output = manager.printMessages();
            // Dois separadores indicam duas mensagens
            assertEquals(2, output.split("-------------------------").length - 1);
        }
    }
}

