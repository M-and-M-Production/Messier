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
            //catalogue.sortByMagnitude();catalogue.printAll();
            //System.out.println(catalogue.avgMagOfType("Open cluster"));
            //System.out.println(catalogue.mostDistantOfType("Globular cluster"));
            //System.out.println(catalogue.lowestDecOfConstel("Sagittarius"));
            //System.out.println(catalogue.nearestStar(45));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}