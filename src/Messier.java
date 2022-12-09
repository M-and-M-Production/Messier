import java.util.Arrays;
import java.util.HashSet;

public class Messier implements Comparable<Messier> {

    /**
     * Class for holding distance bounds.
     * 
     * @param min the minimum distance
     * @param max the maximum distance
     * @throws IllegalArgumentException if min is greater than max
     */
    public class DistanceBounds {
        /**
         * The minimum distance.
         */
        public final double min;
        /**
         * The maximum distance.
         */
        public final double max;

        /**
         * Constructs the DistanceBounds object with the minimum and maximum distance.
         * 
         * @param min the minimum distance
         * @param max the maximum distance
         * @throws IllegalArgumentException if min is greater than max
         * @return the DistanceBounds object
         */
        public DistanceBounds(double min, double max) {
            this.min = min;
            this.max = max; // can be Double
            // check if min is greater than max
            if (min > max) {
                throw new IllegalArgumentException(String.format(
                        "min is greater than max - min: %s, max: %s.", min, max));
            }
        }

        /**
         * Constructs the DistanceBounds object with a string.
         * Example:
         * <blockquote>
         * 
         * <pre>
         * "1" -> DistanceBounds(1, 1)
         * "1-2" -> DistanceBounds(1, 2)
         * </pre>
         * 
         * </blockquote>
         * 
         * @throws IllegalArgumentException if the string is not in the format "MIN-MAX"
         *                                  or "MIN" where MIN and MAX are doubles.
         * @throws NumberFormatException    if the string can't be parsed to a double
         * @param string the string that contains the minimum and maximum distance
         * @return the DistanceBounds object
         */
        public DistanceBounds(String string) {
            // string is "min-max" or "min"
            String[] tokens = string.split("-");
            if (tokens.length == 1) {
                min = Double.parseDouble(tokens[0]);
                max = min;
            } else if (tokens.length == 2) {
                min = Double.parseDouble(tokens[0]);
                max = Double.parseDouble(tokens[1]);
            } else {
                throw new IllegalArgumentException(String.format(
                        "argument contains %s tokens instead of 1 or 2 tokens.", tokens.length));
            }
        }

        /**
         * Returns the value of the DistanceBounds object.
         * Example:
         * <blockquote>
         * 
         * <pre>
         * DistanceBounds(1, 2) -> 1
         * DistanceBounds(1, 1) -> 1
         * </pre>
         * 
         * </blockquote>
         * 
         * @param value the value to be checked
         * @return the value of the DistanceBounds object
         */
        public double value(double value) {
            return min;
        }

        /**
         * Returns the string representation of the DistanceBounds object.
         * Example:
         * <blockquote>
         * 
         * <pre>
         * DistanceBounds(1, 2) -> "1-2"
         * DistanceBounds(1, 1) -> "1"
         * </pre>
         * 
         * </blockquote>
         * 
         * @return the string representation of the DistanceBounds object
         */
        @Override
        public String toString() {
            // use a ternary operator to check if the min and max are equal
            return min == max ? String.format("%s", min) : String.format("%s-%s", min, max);
        }
    }

    /**
     * An Enum that will store the units for the degrees of ascension and
     * declination.
     */
    public enum DegreesUnits {

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
         * 
         * @param units the units for ascension and declination.
         */
        private DegreesUnits(String units, double conversionFactor) {
            this.units = units;
            this.conversionFactor = conversionFactor;
        }

        /**
         * returns the unit at the index.
         * Example:
         * <blockquote>
         * 
         * <pre>
         * DMS.getUnit(0) returns °
         * DMS.getUnit(1) returns '
         * HMS.getUnit(2) returns s
         * </pre>
         * 
         * </blockquote>
         * 
         * @param index the index of the unit to return
         * @return the unit at the index
         * @throws IndexOutOfBoundsException if the index is not within the range of the
         *                                   0<=index<3
         */
        public char getUnit(int index) {
            return units.charAt(index);
        };

        /**
         * Converts the radian to the units of the enum.
         * Example:
         * <blockquote>
         * 
         * <pre>
         * DMS.radiansToUnits(Math.toRadians(45)) returns 45° 0' 0"
         * HMS.radiansToUnits(Math.toRadians(45)) returns 3h 0m 0s
         * </pre>
         * 
         * </blockquote>
         * 
         * @param unitAsRadians the degrees to convert to the units of the enum.
         * @return the radian is converted to the units of the enum.
         */
        public String radiansToUnits(double unitAsRadians) {
            double unit = Math.abs(Math.toDegrees(unitAsRadians / conversionFactor));
            StringBuilder result = new StringBuilder();
            double number;
            for (int i = 0; i < 2; i++) {
                number = Math.floor(unit);
                result.append(String.format("%.0f%s ", number, getUnit(i))); // Append the number and the unit
                unit = (unit - number) * 60; // get the remainder and multiply by 60
            }
            // append the last unit rounded to 2 decimal places with the last unit
            result.append(String.format("%.4f%s", unit, getUnit(2)));
            if (unitAsRadians<0) {
                result.insert(0, '-');
            }
            
            return result.toString();
        }

        /**
         * Converts the units to radians.
         * Example:
         * <blockquote>
         * 
         * <pre>
         * DMS.unitsToRadians("45° 0' 0\"") returns 0.7853981633974483
         * HMS.unitsToRadians("3h 0m 0s") returns 0.5235987755982988
         * </pre>
         * 
         * </blockquote>
         * 
         * @param units the units to convert to radians.
         * @return the units converted to radians.
         */
        public double unitsToRadians(String units) {
            String[] tokens = units.split(" ");
            if (tokens.length != 3) {
                throw new IllegalArgumentException(String.format(
                        "argument contains %s tokens instead of 3 tokens.", tokens.length));
            }
            try {
                // use a map function to remove the unit of the string and convert it to a
                // double.
                Double[] tokenss = Arrays.stream(tokens)
                        .map(token -> Double.parseDouble(token.substring(0, token.length() - 1)))
                        .toArray(Double[]::new);
                        double output = Math.toRadians(Math.abs(tokenss[0]) + (tokenss[1] + tokenss[2] / 60) / 60) * conversionFactor;
                return tokenss[0] < 0 ?  output*-1:output ;
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
         * <blockquote>
         * 
         * <pre>
         * NGC 1234 -> NGCIC(NGC, 1234)
         * IC 321 -> NGCIC(IC, 321)
         * </pre>
         * 
         * </blockquote>
         * 
         * @param type  the type of NGCIC object
         * @param value the value of the NGCIC object
         * @throws IllegalArgumentException if the type is not NGCICType.NGC or
         *                                  NGCICType.IC
         * @return the NGCIC object
         */
        public NGCIC(NGCICType type, int value) {
            this.type = type;
            this.value = value;
        }

        /**
         * Constructs the NGCIC object with a string.
         * Example:
         * <blockquote>
         * 
         * <pre>
         * "NGC 1234" -> NGCIC(NGC, 1234)
         * "IC 321" -> NGCIC(IC, 321)
         * </pre>
         * 
         * </blockquote>
         * 
         * @param object the string that contains the type and value
         * @throws IllegalArgumentException if the string is not in the format "TYPE
         *                                  VALUE" where TYPE is either "NGC" or "IC"
         *                                  and VALUE is an integer.
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
                    throw new IllegalArgumentException(String.format(
                            "Unknown NGCIC type - %s.", tokens[0]));
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
         * <blockquote>
         * 
         * <pre>
         * NGCIC(NGC, 1234) -> "NGC 1234"
         * NGCIC(IC, 321) -> "IC 321"
         * </pre>
         * 
         * </blockquote>
         * 
         * @return the string representation of the NGCIC object
         */
        @Override
        public String toString() {
            return String.format("%s %s", type, value);
        }
    }

    int messierNumber;
    // hashset because there can be multiple NGCIC objects, they are unique and they
    // are unordered.
    HashSet<NGCIC> ngcic; // can be empty
    String name; // can be None
    String type;
    DistanceBounds distance;
    String constellation;
    double magnitude;
    double ascension;
    double declination;

    Messier(int messierNum,String[] NGCIC,String name, String type,String distance,String conste,Double mag,String asc,String dec) {
        messierNumber = messierNum;
        for(String s : NGCIC){
            ngcic.add(new NGCIC(s));
        }
        this.name = name;
        this.type = type;
        this.distance = new DistanceBounds(distance);
        constellation = conste;
        magnitude = mag;
        ascension = DegreesUnits.HMS.unitsToRadians(asc);
        declination = DegreesUnits.DMS.unitsToRadians(dec);
        // create a constructor that will store the data in as attributes
    }

    /**
     * Compares the magnitude of the Messier object to another Messier object.
     * 
     * @param o the other Messier object
     * @return the difference between the magnitude of the Messier object and the
     *         other Messier object
     */

    @Override
    public int compareTo(Messier o) {
        return (int) (this.magnitude - o.magnitude);
    }

    public static void main(String[] args) {
    }
}
