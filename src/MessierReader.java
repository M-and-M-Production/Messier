import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class MessierReader {
    
    
    MessierReader(FileReader fReader) throws IOException {
        // create a constructor that will store the data in as attributes
        // new FileReader(filename, Charset.forName("UTF-8")))
        try (BufferedReader br = new BufferedReader(fReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                    // process the line.
                    // create a regex that will split on commas within quotation marks
                    String regex = ", (?=([^\"]*\"[^\"]*\")*[^\"]*$)";
                    

                    // removes the final quote from the declination so that the regex will work
                    String[] tokens = line.substring(0, line.length()-1).split(regex, -1);
                    System.out.println(line);
                    // String[] tokens = line.split(regex, -1);
                    for(String t : tokens) {
                        System.out.println("> "+t);
                    }
                }
            }
    }
}
