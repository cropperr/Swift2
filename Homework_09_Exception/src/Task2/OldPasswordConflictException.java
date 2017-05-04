
package Task2;


public class OldPasswordConflictException extends Exception {

    int passwordConflictIndex;

    OldPasswordConflictException(int n) {
        super("[Password matches a recently used one: " + n + "]");
        passwordConflictIndex = n;
    }

    public int getPasswordConflictIndex() {
        return passwordConflictIndex;
    }
}
