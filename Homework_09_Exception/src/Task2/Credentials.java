
package Task2;

public class Credentials {

    private String username;
    private String password;
    private String[] passwords = new String[100];
    private int passwordsCounter = 0;

    public Credentials(String username, String password) {
        this.username = username;
        try {
            setPassword(password);
        } catch (OldPasswordConflictException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        }
        return false;
    }

    public boolean changePassword(Credentials c, String password, String newPassword) throws OldPasswordConflictException {
        if (c.checkPassword(password)) {
            int tmp = passwordsCounter;
            c.setPassword(newPassword);
            if (tmp < passwordsCounter) {
                return true;
            } 
        }
        return false;
    }

    private boolean isNewPassValid(String password) throws OldPasswordConflictException {
        boolean isValid = false;
        boolean isAnOld = isTheNewPassAnOldOne(password);
        if (!isAnOld && password.length() > 1) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isTheNewPassAnOldOne(String newPassword) throws OldPasswordConflictException {
        boolean isAnOld = false;
        for (int i = 0; i < passwords.length; i++) {
            if (passwords[i] == null) return isAnOld;
            if (newPassword.equals(passwords[i])) {
                throw new OldPasswordConflictException(passwordsCounter - i - 1);
            }
        }
        return isAnOld;
    }

    public void setPassword(String password) throws OldPasswordConflictException {
        if (isNewPassValid(password)) {
            this.password = password;
            passwords[passwordsCounter] = password;
            passwordsCounter++;
        }
    }

    public String getUsername() {
        return this.username;
    }
}
