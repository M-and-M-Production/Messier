import java.util.Arrays;
import java.util.HashSet;
public class Messier implements Comparable<Messier> {
    
    
    /**
     * An Enum that will store the units for the degrees of ascension and declination.
     */
    public enum DegreesUnits  {

        /**
         * The units for ascension are in hours, minutes, and seconds.
         */
		HMS("hms", 15),
        /**
         * The units for declination is in degrees, arcminutes, and arcseconds.
         */
		DMS("°'\"", 1);

        /**
         * The units for ascension and declination.
         */
        public final String units;
        public final double conversionFactor;
        /**
         * constructs the enum with the units value.
         * @param units the units for ascension and declination.
         */
        private DegreesUnits(String units, double conversionFactor) {
            this.units = units;
            this.conversionFactor = conversionFactor;
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

        
        private String radiansToUnits (double unitAsRadians) {
            double unit = Math.toDegrees(unitAsRadians/conversionFactor);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                result.append(String.format("%.0f%s ", Math.floor(unit), getUnit(i))); // Append the number and the unit
                unit = (unit%1)*60; // get the remainder and multiply by 60
            }
            // append the last unit rounded to 2 decimal places with the last unit
            result.append(String.format("%.4f%s", unit, getUnit(2)));
            return result.toString();
        }

        public double unitsToRadians(double hours, double minutes, double seconds) {
            return Math.toRadians(hours + (minutes + seconds/60)/60)*conversionFactor;
        }

        public double unitsToRadians(String units) {
            String[] tokens = units.split(" ");
            if (tokens.length != 3) {
                throw new IllegalArgumentException(String.format(
                    "argument contains %s tokens instead of 3 tokens.", tokens.length));
            }
            try {
                // use a map function to remove the unit of the string and convert it to a double.
                Double[] tokenss = Arrays.stream(tokens).map(token -> Double.parseDouble(token.substring(0, token.length()-1))).toArray(Double[]::new);
                return unitsToRadians(tokenss[0], tokenss[1], tokenss[2]); 
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format(
                    "can't parse value to double - %s.", 
                    tokens[1]));
            }
        }
	}
    /**
     * An Enum that will store the type of NGCIC object and the value number.
     */
    public static class NGCIC {
        /**
         * An Enum that holds the 2 valid types of NGCIC objects.
         * NGC and IC
         */
        public enum NGCICType {
            NGC,
            IC
        }
        public final NGCICType type;
        public final int value;
        
        /**
         * Constructs the NGCIC object with the type and value.
         * Example: 
         * <blockquote><pre>
         * NGC 1234 -> NGCIC(NGC, 1234)
         * IC 321 -> NGCIC(IC, 321)
         * </pre></blockquote>
         * @param type the type of NGCIC object
         * @param value the value of the NGCIC object
         * @throws IllegalArgumentException if the type is not NGCICType.NGC or NGCICType.IC
         * @return the NGCIC object
         */
        public NGCIC(NGCICType type, int value) {
            this.type = type;
            this.value = value;
        }
        /**
         * Constructs the NGCIC object with a string.
         * Example:
         * <blockquote><pre>
         * "NGC 1234" -> NGCIC(NGC, 1234)
         * "IC 321" -> NGCIC(IC, 321)
         * </pre></blockquote>
         * @param object the string that contains the type and value
         * @throws IllegalArgumentException if the string is not in the format "TYPE VALUE" where TYPE is either "NGC" or "IC" and VALUE is an integer.
         */
        public NGCIC(String object) {
            String[] tokens = object.split(" ");
            if (tokens.length != 2) {
                throw new IllegalArgumentException(String.format(
                    "argument contains %s tokens instead of 2 tokens.", tokens.length));
            }
            switch (tokens[0]) {
                case "NGC":
                    this.type = NGCICType.NGC;
                    break;
                case "IC":
                    this.type = NGCICType.IC;
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unknown NGCIC type - %s.", tokens[0]));
            }
            try {
                this.value = Integer.parseInt(tokens[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format(
                    "can't parse value to integer - %s.", 
                    tokens[1]));
            }
        }
        /**
         * Returns the string representation of the NGCIC object.
         * Example:
         * <blockquote><pre>
         * NGCIC(NGC, 1234) -> "NGC 1234"
         * NGCIC(IC, 321) -> "IC 321"
         * </pre></blockquote>
         * @return the string representation of the NGCIC object
         */
        @Override
        public String toString() {
            return String.format("%s %s", type, value);
        }
    }
 

    int messierNumber;
    // hashset because there can be multiple NGCIC objects, they are unique and they are unordered.
    HashSet<NGCIC> ngcic; // can be empty
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

    

    

    @Override
    public int compareTo(Messier o) {
        return (int) (this.magnitude - o.magnitude);
    }

    public static void main(String[] args) {
        // create DMS
        DegreesUnits dms = DegreesUnits.DMS;
        System.out.println(dms.radiansToUnits(dms.unitsToRadians(90, 0, 0)));
        System.out.println(dms.unitsToRadians(-90, 0, 0));
        
    }

}
