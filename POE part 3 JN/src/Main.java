
void main() {
    void class Main {

        public static void main(String[] args) {
        }

        public static void storedMessagesMenu(MessageManager manager, Scanner scanner) {

            System.out.println("\nStored Messages Menu");
            System.out.println("1. Display senders and recipients");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search by Message ID");
            System.out.println("4. Search messages by recipient");
            System.out.println("5. Delete message by hash");
            System.out.println("6. Display full report");
            System.out.println("7. Back");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> manager.displayStoredSendersAndRecipients();
                case 2 -> manager.displayLongestStoredMessage();
                case 3 -> {
                    System.out.print("Enter Message ID: ");
                    String id = scanner.nextLine();
                    manager.searchByMessageID(id);
                }
               
                case 4 -> {
                    System.out.print("Enter recipient: ");
                    String rec = scanner.nextLine();
                    manager.searchMessagesByRecipient(rec);
                }
               
                case 5 -> {
                    System.out.print("Enter hash: ");
                    String hash = scanner.nextLine();
                    manager.deleteMessageByHash(hash);
                }
                case 6 -> manager.displayFullReport();
                case 7 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option.");
            }
        }

    }
}



