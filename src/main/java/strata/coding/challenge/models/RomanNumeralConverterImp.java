package strata.coding.challenge.models;

import strata.coding.challenge.exceptions.NotInRangeException;

public class RomanNumeralConverterImp implements RomanNumeralConverter {

	enum DecimalPlace {unit, ten, hundred, thousand};
	
	final private String[] thousandsRep = {"", "M", "MM", "MMM"};
	final private String[] hundredsRep = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	final private String[] tensRep = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	final private String[] unitsRep = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
	
	final private int MAX_THOUSANDS_NUMBER = 3;

	public int getThousandsDigit(String str) throws Exception {
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != 'M' ) {
				if(i > MAX_THOUSANDS_NUMBER) throw new Exception();
				return i;
			}
		}
		if(str.length() > MAX_THOUSANDS_NUMBER)
			throw new Exception();
		return str.length();
	}
	
	// a, b, c are chars that may reprensents the decimal digit we're looking for
	// for example for units a = 'I', b = 'V', c = 'X'
	public int getDigit(String str, char a, char b, char c, String[] romanList) throws Exception {
		
		int val = -1;
		int i = 0;
		String RomanDigit = "";
		
		while(i < str.length() && (str.charAt(i) == a || str.charAt(i) == b)) {
			i++; 
		}
		
		if(i == str.length()) {
			//the entire string contains chararters reprensenting the sane decimal digit
			val = indexOf(romanList, str);
		}
		else {
			if(str.charAt(i) == c) {
				RomanDigit  = str.substring(0, i + 1);
				val = indexOf(romanList, RomanDigit);
			}
			else {
				if(i == 0) {
					val = 0;
				}
				else {
					RomanDigit = str.substring(0, i);
					val = indexOf(romanList, RomanDigit);
				}
			}
		}
		
		
		if(val == -1) {
			throw new Exception();
		}
		return val;
		
	}
	


	//returns the index of the romanDigit in the list, that index is eq to its decimal reprensentation 
	private int indexOf(String[] list, String romanDigit) {
		for(int i = 0; i < list.length; i++) 
			if(list[i].equals(romanDigit))
				return i;
		return -1;
	}

	@Override
	public int fromRomanNumeral(String romanNumeral) {
		try {
			int thousandDigit = getThousandsDigit(romanNumeral);
			romanNumeral = romanNumeral.substring(thousandDigit);
			
			
			int hundredDigit = getDigit(romanNumeral, 'C', 'D', 'M', hundredsRep);
			romanNumeral = romanNumeral.substring(hundredsRep[hundredDigit].length());
			
			int tenDigit = getDigit(romanNumeral, 'X', 'L', 'C', tensRep);
			romanNumeral = romanNumeral.substring(tensRep[tenDigit].length());
			
			int unitDigit = getDigit(romanNumeral, 'I', 'V', 'X', unitsRep);
			romanNumeral = romanNumeral.substring(unitsRep[unitDigit].length());
			int returnedNumber = thousandDigit * 1000 + hundredDigit * 100 + tenDigit * 10 + unitDigit;
			
			if(romanNumeral.length() > 0)
				return -1;
			else
				return returnedNumber;

		} catch (Exception e) {
			return -1;
		}
	}

	
	
	@Override
	public String toRomanNumeral(int number) {
		
		String romanNumeralStr = "";
		try {
			checkIfInRange(number);
		}
		catch (NotInRangeException e) {
			return "-1";
		}
		
		DecimalPlace decPlace = DecimalPlace.unit;
		
		//the resulting string is the concatenation of its digits representation
		while(number != 0) {
			int currentDigit = number % 10;
			switch (decPlace){
			case unit:
				romanNumeralStr = unitsRep[currentDigit] + romanNumeralStr;
				decPlace = DecimalPlace.ten;
				break;
			case ten:
				romanNumeralStr = tensRep[currentDigit] + romanNumeralStr;
				decPlace = DecimalPlace.hundred;
				break;
			
			case hundred:
				romanNumeralStr = hundredsRep[currentDigit] + romanNumeralStr;
				decPlace = DecimalPlace.thousand;
				break;			
			case thousand:
				romanNumeralStr = thousandsRep[currentDigit] + romanNumeralStr;
				break;
			}
			number /= 10;
		}
		return romanNumeralStr;
	}

	private boolean checkIfInRange(int number) throws NotInRangeException {
		if(number < 1 || number > 3999)
			throw new NotInRangeException();
		return true;
	}

}
