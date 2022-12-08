import java.io.*;
import java.util.*;

public class MessierCatalogue {
    List<MessierObject> objects = new ArrayList<>();
    MessierObject[] obj0;
    MessierObject[] obj1;
    MessierObject[] obj2;

    public MessierCatalogue(String fileName) throws Exception {
        BufferedReader bRead = new BufferedReader(new FileReader(fileName));
        FileReader fread = new FileReader(fileName);
        String[] tokens = new String[9];
        do {
            StringTokenizer toke = new StringTokenizer(bRead.readLine(), "\"");
            int i = 0;
            int y = 0;
            while (toke.hasMoreTokens() != false) {
                switch (i) {
                    case 1:
                        toke.nextToken("\"");
                        tokens[y] = toke.nextToken("\"");
                        break;
                    case 2:
                        toke.nextToken(",\"");
                        tokens[y] = toke.nextToken("\"");
                        toke.nextToken(",");
                        break;
                    case 4:
                        String temp = toke.nextToken(",");
                        if (temp.contains("-")) {

                            tokens[y] = temp.substring(temp.indexOf("-") + 1);
                        } else {
                            tokens[y] = temp;
                        }
                        break;

                    default:

                        tokens[y] = toke.nextToken(",");
                        break;
                }
                y++;
                i++;
            }
        } while (bRead.ready() != false);

        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        MessierCatalogue catalogue = new MessierCatalogue("messier.txt");

    }
}
