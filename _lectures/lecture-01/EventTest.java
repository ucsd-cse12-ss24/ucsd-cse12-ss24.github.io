import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.time.LocalDateTime;

public class EventTest {

	@Test
	public void testEventConstructor() {
		LocalDateTime s1 = LocalDateTime.of(2024, 7, 1, 9, 45);
		LocalDateTime s2 = LocalDateTime.of(2024, 7, 1, 10, 45);
		
		Event e1 = new Event(s1, s2, "FAH 1450");
		
		assertEquals(e1.start, s1);
		assertEquals(e1.end, s2);
		assertEquals(e1.location, "FAH 1450");
	}

	@Test
	public void testConflict() {
		LocalDateTime s1 = LocalDateTime.of(2024, 10, 1, 9, 45);
		LocalDateTime s2 = LocalDateTime.of(2024, 10, 1, 10, 45);
		
		LocalDateTime longAgo = LocalDateTime.of(2019, 10, 1, 9, 45);
		LocalDateTime inTheFuture = LocalDateTime.of(2039, 10, 1, 10, 45);
				
		Event e1 = new Event(s1, s2, "FAH 1450");
		Event e2 = new Event(longAgo, inTheFuture, "FAH 1450");
		
		assertEquals(e1.conflict(e2), true);

		// other cases?
		
	}
}
