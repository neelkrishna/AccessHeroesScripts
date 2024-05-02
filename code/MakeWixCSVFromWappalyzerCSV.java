import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


// before running, make sure to delete description, title, and other columns which may have commas from wapp CSV
public class MakeWixCSVFromWappalyzerCSV {

    // input file path
    private static final String WAPP_CSV_PATH = "/Users/neelkrishna/projects/AccessHeroes/files/wappCSV4_24_24.csv";
    // output file path
    private static final String OUTPUT_CSV_PATH = "/Users/neelkrishna/projects/AccessHeroes/files/outputCSVMay2.csv";

    private static final String NEW_LINE_SEPARATOR = "\n"; 
    // header names
    private static final String FILE_HEADER = "First Name,Last Name,Email Address,Company Name, URL"; 

    private static String[] outputA = new String[100000];

    public static void main(String[] args) {
        readWappCSV();
        writeCSV();
    }

    public static void readWappCSV() {
        BufferedReader br = null;
        try {
            String line = "";
            br = new BufferedReader(new FileReader(WAPP_CSV_PATH));
            int count = 0;
            while((line = br.readLine()) != null) {
                String[] entry = line.split(",");
                if(ReadCSVUtils.isEntryViable(entry)) {
                    ParsedColumns pc = ReadCSVUtils.generateParsedColumns(entry);
                    if(ReadCSVUtils.validateFieldsForOutput(pc)) {
                        // adds entry to output array to be written to output CSV
                        String outputEntry = ReadCSVUtils.buildOutputEntry(pc);
                        outputA[count] = outputEntry;
                        count++;
                    }
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeCSV() {
        FileWriter fileWriter = null; 
        try { 
            fileWriter = new FileWriter(OUTPUT_CSV_PATH); 
            // Write the CSV file header 
            fileWriter.append(FILE_HEADER.toString()); 
            // Add a new line separator after the header 
            fileWriter.append(NEW_LINE_SEPARATOR); 
            // Write each row to the CSV file (start with 1 because of header)
            for(int i = 1; i < outputA.length; i++) { 
                if(outputA[i] == null) {
                    break;
                }
                fileWriter.append(outputA[i]);
                fileWriter.append(NEW_LINE_SEPARATOR); 
            } 
            System.out.println("CSV file was created successfully!"); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                fileWriter.flush(); 
                fileWriter.close(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        }
    }
}