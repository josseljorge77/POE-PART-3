public class Registration {

    private Login login = new Login();

    public String registerUser(String inputUsername, String inputPassword, String inputPhonenumber) {

        if (!login.checkUserName(inputUsername)) {
            return "The username is incorrectly formatted.";
        }

        if (!login.checkPasswordComplexity(inputPassword)) {
            return "The password does not meet the complexity.";
        }

        if (!login.checkCellPhoneNumber(inputPhonenumber)) {
            return "The cellphone number is incorrectly formatted.";
        }

        login.username = inputUsername;
        login.password = inputPassword;
        login.phonenumber = inputPhonenumber;

        return "The two above conditions have been met, and the user has been registered successfully";
    }

    public Login getLogin() {
        return login;
    }
}
