import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessierTest {
    // test creation of DegreesUnits enum
    @Test
    public void testDegreesUnits() {
        assertEquals("°'\"", Messier.DegreesUnits.DMS.units);
        assertEquals("HMS", Messier.DegreesUnits.HMS.units);
    }
    // test getUnit method of DegreesUnits enum
    @Test
    public void testGetUnit() {
        // all units are single characters
        assertEquals('°', Messier.DegreesUnits.DMS.getUnit(0));
        assertEquals('\'', Messier.DegreesUnits.DMS.getUnit(1));
        assertEquals('"', Messier.DegreesUnits.DMS.getUnit(2));
        assertEquals('H', Messier.DegreesUnits.HMS.getUnit(0));
        assertEquals('M', Messier.DegreesUnits.HMS.getUnit(1));
        assertEquals('S', Messier.DegreesUnits.HMS.getUnit(2));
    }
    // test toUnitsBetter method of DegreesUnits enum
    @Test
    public void testToUnitsBetter() {
        // TODO
    }

}
