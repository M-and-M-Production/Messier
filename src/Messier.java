import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Messier {
    
    
    /**
     * An Enum that will store the units for the degrees of ascension and declination.
     */
    public enum DegreesUnits {

        /**
         * The units for ascension and declination are in degrees, minutes, and seconds.
         */
		DMS("°'\""),

        
		HMS("HMS");
        

        /**
         * The units for ascension and declination.
         * @
         */
        public final String units;
        /**
         * constructs the enum with the units value.
         * @param units the units for ascension and declination.
         */
        private DegreesUnits(String units) {
            this.units = units;
		}

        /**
         * returns the unit at the index.
         * Example: 
         * <blockquote><pre>
         * DMS.getUnit(0) returns °
         * DMS.getUnit(1) returns '
         * HMS.getUnit(2) returns S
         * </pre></blockquote>
         * 
         * @param index the index of the unit to return
         * @return the unit at the index
         * @throws IndexOutOfBoundsException if the index is not within the range of the 0<=index<3
         */
        public char getUnit(int index) {
            return units.charAt(index);
        };

        //TODO convert to radians
        private String toUnits (double UnitInTime) {	
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                result.append(Math.floor(UnitInTime) + getUnit(i) + " "); // Append the number and the unit
                UnitInTime = (UnitInTime%1)*60; // get the remainder and multiply by 60
            }
            // append the last unit rounded to 2 decimal places with the last unit
            result.append(String.format("%.2f", UnitInTime) + getUnit(2));
            return result.toString();
        }
	}

    public class NGCIC {
        public enum NGCICType {
            NGC,
            IC
        }
        public final NGCICType type;
        public final int value;
        public NGCIC(NGCICType type, int value) {
            this.type = type;
            this.value = value;
        }
    }
 

    int messierNumber;
    String ngcic; // can be None
    String name; // can be None
    String type;
    double distance;
    String constellation;
    double magnitude;
    double ascension;
    double declination;

    
    Messier() {
        // create a constructor that will store the data in as attributes
    }

}
