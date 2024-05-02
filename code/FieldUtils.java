public class FieldUtils {
    public static String parseFirstName(String[] entry) {
        String nameField = entry[entry.length - 1];
        // if name is empty
        if(nameField.length() < 5) {
            return "Team";
        } else {
            // return first word in name field
            if(nameField.indexOf(" ") == -1) {
                return nameField;
            } else {
                return nameField.split(" ")[0];
            }
        }
    }

    public static String parseLastName(String[] entry) {
        String nameField = entry[entry.length - 1];
        // if name is empty
        if(nameField.length() < 5 || nameField.indexOf(" ") == -1 || nameField.indexOf(" --") == -1) {
            // return inferred company name
            return entry[41];
        } else {
            // return everything after the first name until the -- (necessary because of names including more than two words)
            return nameField.substring(nameField.indexOf(" "), nameField.indexOf(" --"));
        }
    }

    public static String parseEmail(String[] entry) {
        String emailField = entry[9];
        String emailToReturn = "";
        if(emailField.length() < 5) {
            emailField = entry[8];
        }
        // logic to ensure we only return first email address
        if(emailField.indexOf(" ") == -1) {
            emailToReturn =  emailField;
        } else {
            emailToReturn = emailField.split(" ")[0];
        }
        if(emailToReturn.indexOf("@") == -1 || emailField.indexOf("info@mysite") != -1) {
            return "abort";
        } else {
            return emailToReturn;
        }
        // note on email addresses: there are very few entries in the example wapp csv which have names and then email addresses in brackets. 
        // Those entries will be ignored.  Something I can fix in v2
    }

    public static String parseCompanyName(String[] entry) {
        // return inferred company name
        return entry[entry.length - 8];
    }

    public static String parseURL(String[] entry) {
        // return URL
        return entry[0];
    }
}
