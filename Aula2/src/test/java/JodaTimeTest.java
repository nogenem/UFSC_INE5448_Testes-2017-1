import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.Test;

public class JodaTimeTest {
	
	//Criação de data
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
	
	// Soma de datas 
	@Test
	public void somarDuasDatas() {
		LocalDate _30deDezembro1993 = new LocalDate(1993, 12, 30);
		LocalDate _01deJaneiro1994 = new LocalDate(1994, 01, 01);
		assertEquals(_30deDezembro1993.plusDays(2), _01deJaneiro1994);
	}
	
	// Subtração de datas
	@Test
	public void subtrairDuasDatas() {
		LocalDate _01deJaneiro1994 = new LocalDate(1994, 01, 01);
		LocalDate _30deDezembro1993 = new LocalDate(1993, 12, 30);
		assertEquals(_01deJaneiro1994.minusDays(2), _30deDezembro1993);
	}
	
	// Criação de horas
	@Test
	public void meioDia() {
		LocalTime meioDia = new LocalTime(12, 00, 00, 00);
		assertEquals(12, meioDia.getHourOfDay());
		assertEquals(00, meioDia.getMinuteOfHour());
		assertEquals(00, meioDia.getSecondOfMinute());
		assertEquals(00, meioDia.getMillisOfSecond());
	}
	
	@Test
	public void meiaNoite() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		assertEquals(23, meiaNoite.getHourOfDay());
		assertEquals(59, meiaNoite.getMinuteOfHour());
		assertEquals(59, meiaNoite.getSecondOfMinute());
		assertEquals(999, meiaNoite.getMillisOfSecond());
	}
	
	// Soma de horas 
	@Test
	public void somarUmMinutoAoMeioDia() {
		LocalTime meioDia = new LocalTime(12, 00, 00, 00);
		LocalTime meioDiaEum = meioDia.plusMinutes(1);
		assertNotEquals(meioDiaEum, meioDia);
		assertEquals(12, meioDiaEum.getHourOfDay());
		assertEquals(01, meioDiaEum.getMinuteOfHour());
	}
	
	@Test
	public void somarUmMiliAmeiaNoite() {
		LocalTime meiaNoite = new LocalTime(23, 59, 59, 999);
		LocalTime zeroHora = meiaNoite.plusMillis(1);
		assertNotEquals(zeroHora, meiaNoite);
		assertEquals(00, zeroHora.getHourOfDay());
		assertEquals(00, zeroHora.getMinuteOfHour());
		assertEquals(00, zeroHora.getSecondOfMinute());
		assertEquals(00, zeroHora.getMillisOfSecond());
	}
	
	// Subtração de horas
	@Test
	public void subtrairUmMinutoDeMeioDia() {
		LocalTime meioDia = new LocalTime(12, 00, 00, 00);
		LocalTime onzeEcinquentaEnove = meioDia.minusMinutes(1);
		assertNotEquals(onzeEcinquentaEnove, meioDia);
		assertEquals(11, onzeEcinquentaEnove.getHourOfDay());
		assertEquals(59, onzeEcinquentaEnove.getMinuteOfHour());
	}
	
	@Test
	public void subtrairUmMiliDeZeroHora() {
		LocalTime zeroHora = new LocalTime(00, 00, 00, 00);
		LocalTime meiaNoite = zeroHora.minusMillis(1);
		assertNotEquals(zeroHora, meiaNoite);
		assertEquals(23, meiaNoite.getHourOfDay());
		assertEquals(59, meiaNoite.getMinuteOfHour());
		assertEquals(59, meiaNoite.getSecondOfMinute());
		assertEquals(999, meiaNoite.getMillisOfSecond());
	}
	
	// Before e After 
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
	
	// Criação de data/hora 
	@Test
	public void testDateTimeNoMeioDia() {
		DateTime meioDia = new DateTime(2017, 3, 14, 12, 00);
		assertEquals(2017, meioDia.getYear());
		assertEquals(3, meioDia.getMonthOfYear());
		assertEquals(14, meioDia.getDayOfMonth());
		
		assertEquals(12, meioDia.getHourOfDay());
		assertEquals(00, meioDia.getMinuteOfHour());
	}
	
	// Intervalo de datas (Interval)
	@Test
	public void testIntervalContains(){
		DateTime onzeEvinteEum = new DateTime(2017, 3, 14, 11, 21);
		DateTime onzeEmeia = new DateTime(2017, 3, 14, 11, 30);
		DateTime meioDia = new DateTime(2017, 3, 14, 12, 00);
		Interval intv = new Interval(onzeEvinteEum, meioDia);
		assertTrue(intv.contains(onzeEmeia));
	}
	
	// Periodo
	@Test
	public void testeDoPeriodoDaAula() throws Exception {
		DateTime inicio = new DateTime(2017, 03, 21, 8, 20);
		DateTime fim = new DateTime(2017, 03, 21, 11, 50);
		Period periodo = new Period(inicio, fim);
		
		assertEquals(3, periodo.getHours());
		assertEquals(30, periodo.getMinutes());
	}
}
