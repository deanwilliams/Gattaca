import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class testGattaca {

	@Test
	public void testGattacaExample() throws FileNotFoundException {
		gattaca g = new gattaca("./tests/example.in");
		assertEquals(100, g.getBestDNAScore());
	}
	
}
