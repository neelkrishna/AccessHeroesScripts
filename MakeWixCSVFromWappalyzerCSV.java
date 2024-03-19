import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


// before running, make sure to delete description, title, and other fields which may have commas from wapp CSV
public class MakeWixCSVFromWappalyzerCSV {

    // input file path
    private static final String WAPP_CSV_PATH = "/Users/neelkrishna/projects/AccessHeroes/wappCSV.csv";
    // output file path
    private static final String OUTPUT_CSV_PATH = "/Users/neelkrishna/projects/AccessHeroes/outputCSV.csv";

    private static final String NEW_LINE_SEPARATOR = "\n"; 
    // header names
    private static final String FILE_HEADER = "First Name,Last Name,Email Address,Company Name"; 

    private static String[] outputA = new String[100000];

    public static void main(String[] args) {
        readWappCSV();
        writeCSV();
    }

    public static void readWappCSV() {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(WAPP_CSV_PATH));
            int count = 0;

            while((line = br.readLine()) != null) {
                String[] entry = line.split(",");
                if(entry.length < 20) {
                    continue;
                }

                String parsedFirstName = parseFirstName(entry);
                String parsedLastName = parseLastName(entry);
                String parsedEmail = parseEmail(entry);
                String parsedCompanyName = parseCompanyName(entry);

                // check to see if any of the fields collected make the entry invalid, as defined by logic in each function. Skips invalid entries
                if(parsedFirstName.length() < 4 || parsedLastName.length() < 4 || parsedEmail == "abort" || parsedCompanyName.length() < 4) {
                    continue;
                } else {
                    // adds entry to output array to be written to output CSV
                    String outputEntry = parsedFirstName + "," + parsedLastName + "," + parsedEmail + "," + parsedCompanyName;
                    outputEntry = outputEntry.replace("\"", "");
                    outputEntry = outputEntry.replace("  ", " ");
                    outputA[count] = outputEntry;
                    count++;
                }
            }
            br.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

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
        return entry[41];
    }

    public static void writeCSV() {
        FileWriter fileWriter = null; 
 
        try { 
            fileWriter = new FileWriter(OUTPUT_CSV_PATH); 
 
            // Write the CSV file header 
            fileWriter.append(FILE_HEADER.toString()); 
            // Add a new line separator after the header 
            fileWriter.append(NEW_LINE_SEPARATOR); 
 
            // Write row to the CSV file 
            for(int i = 1; i < outputA.length; i++) { 
                if(outputA[i] == null) {
                    break;
                }
                fileWriter.append(outputA[i]);
                fileWriter.append(NEW_LINE_SEPARATOR); 
            } 
 
            System.out.println("CSV file was created successfully!"); 
 
        } catch (IOException e) { 
            System.out.println("Error in CsvFileWriter !!!"); 
            e.printStackTrace(); 
        } finally { 
            try { 
                fileWriter.flush(); 
                fileWriter.close(); 
            } catch (IOException e) { 
                System.out.println("Error while flushing/closing fileWriter !!!"); 
                e.printStackTrace(); 
            } 
 
        } 
    }
    
}