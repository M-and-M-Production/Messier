import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

public class MessierReaderTest {
    // test if MessierReader class constructor works
    @Test
    public void testMessierReaderConstructer() throws IOException {
        MessierReader reader = new MessierReader(new FileReader("messier.txt", Charset.forName("UTF-8")));
        assertEquals(110, reader.object.size());
    }
}