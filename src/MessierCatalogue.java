import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MessierCatalogue {
    List<MessierObject> objects = new ArrayList<MessierObject>();
    MessierObject[] obj = new MessierObject[110];

    public MessierCatalogue(String fileName) throws Exception {
        BufferedReader bRead = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),StandardCharsets.UTF_8));
        String[] tokens = new String[13];
        int x=0;
        do {
            StringTokenizer toke = new StringTokenizer(bRead.readLine(), "");

            int i = 0;
            int y = 0;
            while (toke.hasMoreTokens() != false) {
                switch (i) {
                    case 1:
                        toke.nextToken("\"");
                        tokens[y] = toke.nextToken("\"").trim();
                        break;
                    case 2:
                        toke.nextToken(",\"");
                        tokens[y] = toke.nextToken("\"").trim();
                        toke.nextToken(",");
                        break;
                    case 4:
                        String temp = toke.nextToken(",");
                        if (temp.contains("-")) {

                            tokens[y] = temp.substring(temp.indexOf("-") + 1).trim();
                        } else {
                            tokens[y] = temp.trim();
                        }
                        break;
                    case 7:
                    Double num0=Double.parseDouble(toke.nextToken(",h "));toke.nextToken(" ");
                    Double num1=Double.parseDouble(toke.nextToken("m "));toke.nextToken(" ");
                    Double num2=Double.parseDouble(toke.nextToken("s "));toke.nextToken(" ");
                    tokens[y] =String.format("%f", num0 + (num1 +(num2/60))/60);
                    break;
                    case 8:
                    Double num3=Double.parseDouble(toke.nextToken(",° "));toke.nextToken(" ");
                    Double num4=Double.parseDouble(toke.nextToken("\' "));toke.nextToken(" ");
                    Double num5=Double.parseDouble(toke.nextToken("\" "));toke.nextToken(" ");
                    tokens[y] =String.format("%f", num3+(num4 +(num5/60))/60);
                    break;
                    default:
                        tokens[y] = toke.nextToken(",").trim();
                        break;
                }
                y++;
                i++;
            }
            obj[x] = new MessierObject(tokens[0],tokens[1],tokens[2],tokens[3],
                                    Double.parseDouble(tokens[4]),tokens[5],
                                    Double.parseDouble(tokens[6]),Double.parseDouble(tokens[7]),Double.parseDouble(tokens[8]));
                                    x++;
        } while (bRead.ready() != false);
        bRead.close();
    }

    @Override
    public String toString(){
        String output="";
        for (int i = 0; i < obj.length; i++) {
            output = output + obj[i].toString() + "\n";
        }
        return output;
    }

    // public static void main(String[] args) throws Exception {
    //     MessierCatalogue catalogue = new MessierCatalogue("messier.txt");
    // }
}
