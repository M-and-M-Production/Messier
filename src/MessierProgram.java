import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class MessierProgram {

    public static void main(String[] args) {
        MessierReader reader;
        MessierCatalogue catalogue;
        try {
            reader = new MessierReader(new FileReader("messier.txt",Charset.forName("UTF-8")));
            catalogue = new MessierCatalogue(reader.object);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        catalogue.printAll();

        // sort through a collection with a lambda expression
        catalogue.sort((o1, o2) -> o1.getConstellation().compareTo(o2.getConstellation()));
        
    }
    
}
