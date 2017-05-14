package Task2_CredentialsManager;

public class Credentials {

    private static Credentials[] users = new Credentials[10];
    private static int usersCount = 0;
    private String username;
    private String password;
    private String[] passwords = new String[100];
    private int passwordsCounter = 0;

    public Credentials(String username, String password) {
        setUserName(username);
        setPassword(password);
        users[usersCount] = this;
        usersCount++;
    }

    public static boolean isUserExists(String username) {
        boolean isExists = false;
        for (Credentials user : users) {
            if (username.equals(user.getUsername())) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    public static Credentials getUserByName(String username, String password) {
        for (Credentials user : users) {
            if (user != null && username.equals(user.getUsername())) {
                if (user.checkPassword(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    public boolean checkPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        }
        return false;
    }

    public boolean changePassword(Credentials c, String password, String newPassword) {
        if (c.checkPassword(password)) {
            int tmp = passwordsCounter;
            c.setPassword(newPassword);
            if (tmp < passwordsCounter) {
                return true;
            } 
        }
        return false;
    }

    private boolean isNewUserNameValid(String username) {
        boolean isValid = true;
        if (usersCount == 0) {
            return isValid;
        }
        for (Credentials user : users) {
            if (user != null && username.equals(user.getUsername())) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private boolean isNewPassValid(String password) {
        boolean isValid = false;
        boolean isAnOld = isTheNewPassAnOldOne(password);
        if (!isAnOld && password.length() > 1) {
            isValid = true;
        }

        return isValid;
    }

    private boolean isTheNewPassAnOldOne(String newPassword) {
        boolean isAnOld = false;
        for (String pass : passwords) {
            if (newPassword.equals(pass)) {
                isAnOld = true;
                break;
            }
        }
        return isAnOld;
    }

    private void setUserName(String username) {
        if (isNewUserNameValid(username)) {
            this.username = username;
        } else {
            System.out.println("Fail. Enter new name.");
        }
    }

    public void setPassword(String password) {
        if (isNewPassValid(password)) {
            this.password = password;
            passwords[passwordsCounter] = password;
            passwordsCounter++;
        }
    }
 
    public int getUsersCount() {
        return usersCount;
    }

    public String getUsername() {
        return this.username;
    }
}
