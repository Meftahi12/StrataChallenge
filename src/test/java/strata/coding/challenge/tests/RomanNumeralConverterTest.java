package strata.coding.challenge.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import strata.coding.challenge.models.RomanNumeralConverter;
import strata.coding.challenge.models.RomanNumeralConverterImp;

class RomanNumeralConverterTest {

	private RomanNumeralConverter testConverterImp;

	@BeforeEach
	public void setUp() {
		testConverterImp = new RomanNumeralConverterImp();
	}
	
	@Test
	public void checkIntegerIsInRangeToRomanNumeral() {
		String returned = testConverterImp.toRomanNumeral(0);
		assertEquals("-1", returned);
		returned = testConverterImp.toRomanNumeral(4000);
		assertEquals("-1", returned);
		returned = testConverterImp.toRomanNumeral(2000);
		assertNotEquals(-1, returned);
	}

	@Test
	public void checkOneDigitNumberToRomanNumeral() {
		String returned = testConverterImp.toRomanNumeral(1);
		String expected = "I";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(4);
		expected = "IV";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(8);
		expected = "VIII";
		assertEquals(expected, returned);
	}
	
	@Test
	public void checkTwoDigitsNumberToRomanNumeral() {
		String returned = testConverterImp.toRomanNumeral(76);
		String expected = "LXXVI";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(18);
		expected = "XVIII";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(50);
		expected = "L";
		assertEquals(expected, returned);
	}
	
	@Test
	public void checkThreeDigitsNumberToRomanNumeral() {
		String returned = testConverterImp.toRomanNumeral(789);
		String expected = "DCCLXXXIX";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(246);
		expected = "CCXLVI";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(207);
		expected = "CCVII";
		assertEquals(expected, returned);
	}
	
	@Test
	public void checkFourDigitsNumberToRomanNumeral() {
		String returned = testConverterImp.toRomanNumeral(3421);
		String expected = "MMMCDXXI";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(2014);
		expected = "MMXIV";
		assertEquals(expected, returned);
		returned = testConverterImp.toRomanNumeral(1954);
		expected = "MCMLIV";
		assertEquals(expected, returned);
	}
	
	
	@Test
	public void checkOneDigitNumberFromRomanNumeral() {
		Integer returned = testConverterImp.fromRomanNumeral("I");
		Integer expected = 1;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("IV");
		expected = 4;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("VIII");
		expected = 8;
		assertEquals(expected, returned);
	}
	
	@Test
	public void checkTwoDigitsNumberFromRomanNumeral() {
		Integer returned = testConverterImp.fromRomanNumeral("LXXVI");
		Integer expected = 76;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("XVIII");
		expected = 18;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("L");
		expected = 50;
		assertEquals(expected, returned);
	}
	
	@Test
	public void checkThreeDigitsNumberFromRomanNumeral() {
		Integer returned = testConverterImp.fromRomanNumeral("DCCLXXXIX");
		Integer expected = 789;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("CCXLVI");
		expected = 246;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("CCXCCLVI");
		expected = -1;
		assertEquals(expected, returned);
	}
	
	@Test
	public void checkFourDigitsNumberFromRomanNumeral() {
		Integer returned = testConverterImp.fromRomanNumeral("MMMCDXXI");
		Integer expected = 3421;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("MMXIV");
		expected = 2014;
		assertEquals(expected, returned);
		returned = testConverterImp.fromRomanNumeral("MMCDXXI");
		expected = 2421;
		assertEquals(expected, returned);
	}
}
