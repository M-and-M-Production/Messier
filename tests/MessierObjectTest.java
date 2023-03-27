import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class MessierObjectTest {
    // test creation of DegreesUnits enum
    @Test
    public void testDegreesUnits() {
        assertEquals("°'\"", MessierObject.DegreesUnits.DMS.units);
        assertEquals("hms", MessierObject.DegreesUnits.HMS.units);
    }
    // test getUnit method of DegreesUnits enum
    @Test
    public void testGetUnit() {
        // all units are single characters
        assertEquals('°', MessierObject.DegreesUnits.DMS.getUnit(0));
        assertEquals('\'', MessierObject.DegreesUnits.DMS.getUnit(1));
        assertEquals('"', MessierObject.DegreesUnits.DMS.getUnit(2));
        assertEquals('h', MessierObject.DegreesUnits.HMS.getUnit(0));
        assertEquals('m', MessierObject.DegreesUnits.HMS.getUnit(1));
        assertEquals('s', MessierObject.DegreesUnits.HMS.getUnit(2));
    }
    // test toUnitsBetter method of DegreesUnits enum
    @Test
    public void testToUnitsBetter() {
        // TODO
    }
    // test if NGCIC class constructor works
    @Test
    public void testNGCICConstructer1() {
        MessierObject.NGCIC ngc = new MessierObject.NGCIC(MessierObject.NGCIC.NGCICType.NGC, 1234);
        MessierObject.NGCIC ic = new MessierObject.NGCIC(MessierObject.NGCIC.NGCICType.IC, 321);
        assertEquals(MessierObject.NGCIC.NGCICType.NGC, ngc.type);
        assertEquals(1234, ngc.value);
        assertEquals(MessierObject.NGCIC.NGCICType.IC, ic.type);
        assertEquals(321, ic.value);
    }
    // test the second NGCIC class constructor
    @Test 
    public void testNGCICConstructer2() {
        MessierObject.NGCIC ngc = new MessierObject.NGCIC("NGC 1234");
        MessierObject.NGCIC ic = new MessierObject.NGCIC("IC 321");
        assertEquals(MessierObject.NGCIC.NGCICType.NGC, ngc.type);
        assertEquals(1234, ngc.value);
        assertEquals(MessierObject.NGCIC.NGCICType.IC, ic.type);
        assertEquals(321, ic.value);
    } 
    // test the error handling of the second NGCIC class constructor
    @Test
    public void testNGCICConstructer2Error() {
        try {
            new MessierObject.NGCIC("NGC1234");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("argument contains 1 tokens instead of 2 tokens.", e.getMessage());
        }
        try {
            new MessierObject.NGCIC("IC 321 123");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("argument contains 3 tokens instead of 2 tokens.", e.getMessage());
        }
        try {
            new MessierObject.NGCIC("ABC 1234");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown NGCIC type - ABC.", e.getMessage());
        }
        try {
            new MessierObject.NGCIC("NGC abc");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("can't parse value to integer - abc.", e.getMessage());
        }
    }
    // test the toString method of the NGCIC class
    @Test
    public void testNGCICToString() {
        MessierObject.NGCIC ngc = new MessierObject.NGCIC(MessierObject.NGCIC.NGCICType.NGC, 1234);
        MessierObject.NGCIC ic = new MessierObject.NGCIC(MessierObject.NGCIC.NGCICType.IC, 321);
        assertEquals("NGC 1234", ngc.toString());
        assertEquals("IC 321", ic.toString());
    }
    // create a test that generates a random hour, minute, second data set and tests the randianToUnits method and the unitsToRandian method
    @Test
    public void testConversion() {
        for (int i = 0; i < 500; i++) {
            int hour = (int) (Math.random() * 24);
            int minute = (int) (Math.random() * 60);
            double second = Math.random() * 60;
            // make test succeed
            String unitString = String.format("%dh %dm %.4fs", hour, minute, second);
            assertEquals(unitString, MessierObject.DegreesUnits.HMS.radiansToUnits(MessierObject.DegreesUnits.HMS.unitsToRadians(unitString)));

            int degree = (int) (Math.random() * 180 - 90);
            minute = (int) (Math.random() * 60);
            second = Math.random() * 60;
            String unitString2 = String.format("%d° %d' %.4f\"", degree, minute, second);
            assertEquals(unitString2, MessierObject.DegreesUnits.DMS.radiansToUnits(MessierObject.DegreesUnits.DMS.unitsToRadians(unitString2)));
        }
    }
}
