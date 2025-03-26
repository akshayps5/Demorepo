package mfslyca;


	 
	import java.lang.reflect.Method;
	 
	import org.testng.annotations.DataProvider;
	 
	public class dataproviderforview {
	 
		@DataProvider
		public static Object[] [] getDetails(Method deatils){
		switch (deatils.getName()) {
		case "RateCard":
			return new Object[][] {
				{"Indira", "11111" , "4", "299", "99"}
			};
		case "DuplicatePlanId":
			return new Object[][] {
				{ 
	                "Indira1", "11111" , "4", "299", "99", 
	                "11111" , "4", "299", "99"
	            }
			};
		case "MultiplePlanId" :
			return new Object[][] {
				{
					"Indira2", "11111" , "4", "299", "99", 
	                "2222" , "4", "299", "99"
				}
			};
		case "MissingDatatoRateCard":
			return new Object[][] {
				{ 
	                "Indira3", "11111" , "4", "299", "99"
				}
			};
		case "ExisitingName":
			return new Object[][] {
				{
					"Indira"
				}
			};	
		}	
			return null;
		}
	}

