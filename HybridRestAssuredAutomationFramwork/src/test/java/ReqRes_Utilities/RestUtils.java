package ReqRes_Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	
	
	public static String empName()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Akshay"+generatedString);
		
	}
	
	public static String empRole()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return("QA"+generatedString);
		
	}
}
