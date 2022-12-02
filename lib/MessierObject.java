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
	 * This method is used to convert the degrees into (Hour, Minutes, Seconds) or (Degrees, Arcmin, Arcsec)!. 
	 * Knowledge used:
	 * Degrees = 60 Arcmin
	 * Arcmin = 60 Arcsec
	 * Hour, Minutes, Seconds are synonyms of Degrees, Arcmin, Arcsec
	 * 
	 * DESCRIBE IT MAYBE?
	**/
	private static String toUnitsBetter (double UnitInTime, String units) {	
		double number;
		for (int i = 0; i < 3; i++) {
			number = Math.floor(integer); 
			UnitInTime = (UnitInTime%1)*60 // get the remainder and multiply by 60
	}

@Override
    public String toString()
    {
        String ascString = toUnits(ascension, 0, new String("HMS"));
        String decString = toUnits(declination, 0,new String("°'\""));
        String output;

        output = messierNumber+" " +ngcic+" " +name+" " +type+" " +distance+" " +constellation+" " +magnitude+" " +ascString+" " +decString;
        return output;
    }

    public static void main(String[] args) {

    }
}

// make a branch, make a commit and push it to github

