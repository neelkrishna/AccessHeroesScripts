public class StringUtils {
    public static String toTitleCase(String s) {
        if(s.length() < 2) {
            return s.toUpperCase();
        }
        if(s.indexOf(" ") != -1) {
            String output = "";
            String[] words = s.split(" ");

            for(String word: words) {
                if(word.length() == 0) output += "";
                else if(word.length() == 1) output += word.toUpperCase() + " ";
                else {
                    output += word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase() + " ";
                }
            }
            return output;
        } else {
            return s.substring(0, 1).toUpperCase() + s.substring(1, s.length()).toLowerCase() + " ";
        }
        
    }
    
    public static String removeLeadingOrTrailingSpaces(String s) {
        if(s.length() < 2) return s;
        else {
            if(s.substring(0, 1).equals(" ") || s.substring(0, 1).equals("\"")) {
                s = s.substring(1, s.length());
            }
            if(s.substring(s.length() - 1, s.length()).equals(" ") || s.substring(s.length() - 1, s.length()).equals("\"")) {
                s = s.substring(0, s.length() - 1);
            }
            return s;
        }
        
    }
}
