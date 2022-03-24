package strata.coding.challenge.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import strata.coding.challenge.models.ConversionRequest;
import strata.coding.challenge.models.RomanNumeralConverter;
import strata.coding.challenge.models.RomanNumeralConverterImp;

@Controller
public class RomanNumeralConversionController {
	
	RomanNumeralConverter myConverter = new RomanNumeralConverterImp();
	
	
	@GetMapping("/Converter")
	  public String converterIndex(Model model) {
	    //define default values of our form's inputs
		model.addAttribute("toConvert", new ConversionRequest("0", ""));
		//route the application into the Convert page
	    return "Converter";
	  }
	
	@PostMapping("/Converter")
	public String getRomanFromNumeral(@ModelAttribute ConversionRequest toConvert, Model model) {
		
		String conversionType = "";
		String converted = "";
		
		if(toConvert.getConversionType().equals("1")) {
			converted = myConverter.toRomanNumeral(Integer.parseInt(toConvert.getToConvert()));
			conversionType = "1";
		}
		else {
			
			converted = Integer.toString(myConverter.fromRomanNumeral(toConvert.getToConvert()));
			conversionType = "0";
		}
		
		if(converted.equals("-1") || converted.equals("0"))
			converted = "make sure the value you entered is correct";
		
		model.addAttribute("toConvert", new ConversionRequest("0", converted));

		//route the application into the result page
		return "result"; 
	}
	
}
