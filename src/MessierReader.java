import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MessierReader {
    ArrayList<MessierObject> object = new ArrayList<MessierObject>();

    /**
     * Constructor for MessierReader that will read in the data from the file and store it in an ArrayList of MessierObjects
     * 
     * @param fReader
     * @throws IOException
     */
    MessierReader(FileReader fReader) throws IOException {
        // create a constructor that will store the data in as attributes
        // new FileReader(filename, Charset.forName("UTF-8")))
        try (BufferedReader br = new BufferedReader(fReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                // create a regex that will split on commas within quotation marks
                // regex from https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                String regex = ", (?=([^\"]*\"[^\"]*\")*[^\"]*$)";
                // removes the final quote from the declination so that the regex will work
                String[] tokens = line.substring(0, line.length() - 1).split(regex, 0);
                object.add(new MessierObject(
                        Integer.parseInt(tokens[0].substring(1)),
                        tokens[1].substring(1, tokens[1].length() - 1).split(", ", -1),
                        tokens[2], tokens[3], tokens[4], tokens[5], Double.parseDouble(tokens[6]), tokens[7],
                        tokens[8]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MessierReader mr = new MessierReader(new FileReader("messier.txt", Charset.forName("UTF-8")));
    }
}
