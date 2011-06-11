import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class testGattaca {

	@Test
	public void testGattaca100() throws FileNotFoundException {
		gattaca g = new gattaca("./tests/100.in");
		assertEquals(100, g.getBestDNAScore());
	}
	
	@Test
	public void testGattaca50() throws FileNotFoundException {
		gattaca g = new gattaca("./tests/50.in");
		assertEquals(50, g.getBestDNAScore());
	}
}
