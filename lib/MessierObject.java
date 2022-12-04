public class MessierObject {

    int messierNumber;
    String ngcic; // can be None
    String name; // can be None
    String type;
    double distance;
    String constellation;
    double magnitude;
    double ascension;
    double declination;
    // private class NGCIC<T ,Num>{
    // public final T label;
    // public final Num num;
    // public NGCIC(T type,Num num){
    // this.label = type; // NGC/IC
    // this.num = num; // Number
    // }
    // }

    public MessierObject(int messierNum, String ngcic, String name,
            String starType, double dist, String constellation,
            double appMagnitude, double rightAscension, double declination) {
        messierNumber = messierNum;
        this.ngcic = ngcic;
        this.name = name;
        type = starType;
        distance = dist;
        this.constellation = constellation;
        magnitude = appMagnitude;
        ascension = rightAscension;
        this.declination = declination;

    }
    private static String toUnits(double integer, int depth, String units) {
        //gonna ignore the rules
        if (depth > 1) {integer = Math.abs(integer);
        
            return String.format("%.3f", integer);
            }
        double number = Math.floor(integer);
        return new String(number+""+units.charAt(depth)+" ") + toUnits((integer%1)*60, depth+1,units);
    }



	/**
	 * This method is used to convert the degrees into (Hour, Minutes, Seconds) or (Degrees, Arcmin, Arcsec). 
	 * Knowledge used:
	 * Degrees = 60 Arcmin
	 * Arcmin = 60 Arcsec
	 * Hour, Minutes, Seconds are synonyms of Degrees, Arcmin, Arcsec
	 * 
	 * DESCRIBE IT MAYBE?
	**/
	private static String toUnitsBetter (double UnitInTime, DegreesUnits units) {	
		double number;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 2; i++) {
			result.append(Math.floor(UnitInTime) + units.getUnit(i) + " "); // Append the number and the unit
			UnitInTime = (UnitInTime%1)*60 // get the remainder and multiply by 60
		}
		// append the last unit rounded to 2 decimal places with the last unit
		result.append(String.format("%.2f", UnitInTime) + units.getUnit(2));
		return result.toString();
	}
	public String toString() {
		return String.format("%d %s %s %s %.2f %s %.2f %s %s", messierNumber,
				ngcic, name, type, distance, constellation, magnitude,
				toUnits(ascension, 0, "hms"), toUnits(declination, 0, "dms"));
		// todo!()
	}

	public static double unitsToRadians(double degrees, double minutes, double seconds) {
		return Math.toRadians(degrees + (minutes + seconds/60)/60);
	}
	


	/**
	 * This enum is used represent the different types of units degrees can be converted into.
	 * The 2 Units are: Hour, Minutes, Seconds and Degrees, Arcmin, Arcsec
	 * The main reason for this enum is to make the code more readable and less error prone,
	 * since the previous iteration accepted a string without any checks. 
	 * this enum will be used to make sure that any future maintainer will not make a mistake and input the wrong string.
	**/
	enum DegreesUnits {
		DMS("°'\""),
		HMS("HMS"),
		
		final String getUnit(int index) {
			return units.charAt(index);
		};

		Units(String units) {
			this.units = units;
		}

	}




@Override
    public String toString()
    {
        String ascString = toUnits(ascension, 0, new String("HMS"));
        String decString = toUnits(declination, 0,new String("°'yi\""));
        String output;

        output = messierNumber+" " +ngcic+" " +name+" " +type+" " +distance+" " +constellation+" " +magnitude+" " +ascString+" " +decString;
        return output;
    }

    public static void main(String[] args) {

    }
}


