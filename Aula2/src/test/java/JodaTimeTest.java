import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class JodaTimeTest {

	@Test
	public void dataAtualCerta() {
		LocalDate _14deMarçoDe2017 = new LocalDate(2017, 3, 14);
		assertEquals(14, _14deMarçoDe2017.getDayOfMonth());
		assertEquals(DateTimeConstants.MARCH, _14deMarçoDe2017.getMonthOfYear());
		assertEquals(2017, _14deMarçoDe2017.getYear());
	}
	
	@Test(expected = IllegalFieldValueException.class)
	public void dataComDiaNegativo() {
		new LocalDate(2017, 3, -14);
	}
	
	@Test(expected = IllegalFieldValueException.class)
	public void anoBissextoComDiaErrado() {
		new LocalDate(2017, 2, 29);
	}
	
	@Test
	public void dataNiverCerta() {
		LocalDate _30deDezembro1993 = new LocalDate(1993, 12, 30);
		assertEquals(30, _30deDezembro1993.getDayOfMonth());
		assertEquals(DateTimeConstants.DECEMBER, _30deDezembro1993.getMonthOfYear());
		assertEquals(1993, _30deDezembro1993.getYear());
	}
	
	@Test
	public void meioDia() {
		LocalTime meioDia = new LocalTime(12, 00, 00, 00);
		assertEquals(12, meioDia.getHourOfDay());
		assertEquals(00, meioDia.getMinuteOfHour());
		assertEquals(00, meioDia.getSecondOfMinute());
		assertEquals(00, meioDia.getMillisOfSecond());
	}
	
	@Test
	public void somarUmMinutoAoMeioDia() {
		LocalTime meioDia = new LocalTime(12, 00, 00, 00);
		LocalTime meioDiaEum = meioDia.plusMinutes(1);
		assertNotEquals(meioDiaEum, meioDia);
		assertEquals(1, meioDiaEum.getMinuteOfHour());
	}
	
	@Test
	public void meiaNoite() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		assertEquals(23, meiaNoite.getHourOfDay());
		assertEquals(59, meiaNoite.getMinuteOfHour());
		assertEquals(59, meiaNoite.getSecondOfMinute());
		assertEquals(999, meiaNoite.getMillisOfSecond());
	}
	
	@Test
	public void somarUmMiliAmeiaNoite() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		LocalTime zeroHora = meiaNoite.plusMillis(1);
		assertNotEquals(zeroHora, meiaNoite);
		assertEquals(00, zeroHora.getHourOfDay());
	}
	
	@Test
	public void testBeforeComDatasDiferentes() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		LocalTime zeroHora = meiaNoite.plusMillis(1);
		assertTrue(zeroHora.isBefore(meiaNoite));
	}
	
	@Test
	public void testBeforeComDatasIguais() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		assertFalse(meiaNoite.isBefore(meiaNoite));
	}
	
	@Test
	public void testAfterComDatasDiferentes() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		LocalTime zeroHora = meiaNoite.plusMillis(1);
		assertTrue(meiaNoite.isAfter(zeroHora));
	}
	
	@Test
	public void testAfterComDatasIguais() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		assertFalse(meiaNoite.isAfter(meiaNoite));
	}
	
	@Test
	public void testDateTimeNoMeioDia() {
		DateTime meioDia = new DateTime(2017, 3, 14, 12, 00);
		assertEquals(2017, meioDia.getYear());
		assertEquals(3, meioDia.getMonthOfYear());
		assertEquals(14, meioDia.getDayOfMonth());
		
		assertEquals(12, meioDia.getHourOfDay());
		assertEquals(00, meioDia.getMinuteOfHour());
	}
	
	@Test
	public void testIntervalContains(){
		DateTime onzeEvinteEum = new DateTime(2017, 3, 14, 11, 21);
		DateTime onzeEmeia = new DateTime(2017, 3, 14, 11, 30);
		DateTime meioDia = new DateTime(2017, 3, 14, 12, 00);
		Interval intv = new Interval(onzeEvinteEum, meioDia);
		assertTrue(intv.contains(onzeEmeia));
	}

}
