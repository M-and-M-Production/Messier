public class App {
    private static String toUnits(double integer, int depth, String units) {
        //gonna ignore the rules
        if (depth > 1) {integer = Math.abs(integer);
            return String.format("%.3f", integer);
            }
        double number = Math.floor(integer);
        System.out.println(number+""+units.charAt(depth));
        return new String(number+""+units.charAt(depth)) + toUnits((integer%1)*60, depth+1,units);
    }
    public static void main(String[] args) throws Exception {
        double num = 5.5755;
        System.out.println(toUnits(num, 0, new String("HMS")));

        
        MessierObject tObject = new MessierObject(1,"NGC 1925","Crab Nebula","Supernova remenanat",8.1,"Taurus",8.4,5.5755,79252.2);
        System.out.println(tObject.toString());
    }
}
