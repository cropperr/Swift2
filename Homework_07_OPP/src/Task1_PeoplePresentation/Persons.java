package Task1_PeoplePresentation;


// Not Complete ! 

public class Persons {
    private String firstName;
    private String lastName;

    public Persons(String firstName, String lastName) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setFirstName(String firstName) throws Exception {

        if ('Z' < firstName.charAt(0) && firstName.charAt(0) <= 'A') {
           throw new Exception("Expected upper case letter at first\n" +
                    "position of firstName.");
           
        }

        if (!validateLatinLetters(firstName)){
            throw new Exception("Expected only Latin letters in\n" +
                    "firstName");
            
        }

        if (2 > firstName.length() && firstName.length() > 30) {
            throw new Exception("Expected length for firstName is\n" +
                    "between 2 and 30 symbols.");
            
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws Exception {
        if ('Z' <= lastName.charAt(0) && lastName.charAt(0) <= 'A') {
           throw new Exception("Expected upper case letter at first\n" +
                    "position of lastName.");
            
        }

        if (!validateLatinLetters(lastName)){
            throw new Exception("Expected only Latin letters in\n" +
                    "lastName");
            
        }

        if (2 > lastName.length() && lastName.length() > 30) {
            throw new Exception("Expected length for lastName is\n" +
                    "between 2 and 30 symbols.");
            
        }

        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private boolean validateLatinLetters(String s) {
        for (char c : s.toCharArray()) {
            if (!('A' <= c && c <= 'Z') && !('a' <= c && c <= 'z')) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "First name: " + firstName + "\n"
                + "Last name: " + lastName + "\n";
        return result;
    }
    
    
}