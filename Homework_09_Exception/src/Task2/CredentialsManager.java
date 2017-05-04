
package Task2;

import java.util.ArrayList;
import java.util.Scanner;

public class CredentialsManager {

    private ArrayList<Credentials> credentials = new ArrayList<>();

    private String execute(String[] commandSet) {
        switch (commandSet[0]) {
            case "ENROLL":
                if (commandSet.length != 3) {
                    return "fail";
                }
                if (!isNewUserNameValid(commandSet[1])) {
                    throw new IllegalArgumentException("The name " + commandSet[1] + " already registered.");
                }
                credentials.add(new Credentials(commandSet[1], commandSet[2]));
                return "success";
            case "CHPASS":
                if (commandSet.length != 4) {
                    return "fail";
                }
                Credentials c = this.getUserByName(commandSet[1], commandSet[2]);
                if (c != null) {
                    try {
                        c.changePassword(c, commandSet[2], commandSet[3]);
                    } catch (OldPasswordConflictException e){
                        return "fail " + e.getMessage();
                    }
                    return "success";
                }
                return "fail";
            case "AUTH":
                if (commandSet.length != 3) {
                    return "fail";
                }
                Credentials c1 = this.getUserByName(commandSet[1], commandSet[2]);
                if (c1 == null) {
                    return "fail";
                } else {
                    return "success";
                }
            default:
                return "unsupported command";
        }
    }

    public Credentials getUserByName(String username, String password) {
        for (Credentials user : credentials) {
            if (user != null && username.equals(user.getUsername())) {
                if (user.checkPassword(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    private boolean isNewUserNameValid(String username) {
        boolean isValid = true;
        for (Credentials user : credentials) {
            if (user != null && username.equals(user.getUsername())) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public ArrayList<Credentials> getCredentials() {
        return credentials;
    }

    public static void main(String[] args) throws OldPasswordConflictException {
        CredentialsManager credentialsManager = new CredentialsManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] commandSet = scanner.nextLine().split(" ");
            if (commandSet[0].equals("END")) {
                break;
            }
            System.out.printf("%s %s\n", commandSet[0], credentialsManager.execute(commandSet));
        }

    }
}