import java.util.Objects;

public class Login {
    public String username;
    public String password;
    public String phonenumber;

    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            return false;
        }
    }

    public boolean checkPasswordComplexity(String password) {
        if (password.length() <= 8) {
            System.out.println("Password was not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password was not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            System.out.println("Password was not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            System.out.println("Password was not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
        System.out.println("Password was successfully captured");
        return true;
    }

    public boolean checkCellPhoneNumber(String phonenumber) {
        if (!phonenumber.matches("\\+27[0-9]+")) {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            return false;
        }
        System.out.println("Cell phone Number Successfully Added.");
        return true;
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return Objects.equals(username, inputUsername) && Objects.equals(password, inputPassword);
    }

    public String returnLoginStatus(String inputUsername, String inputPassword) {
        if (loginUser(inputUsername, inputPassword)) {
            return "Welcome " + username + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again";
    }
}
