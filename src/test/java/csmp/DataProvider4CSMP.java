package csmp;


	import java.lang.reflect.Method;
	 
	import org.testng.annotations.DataProvider;
	 
	public class DataProvider4CSMP {
		@DataProvider
		public static Object[] [] getDetails(Method deatils){
		switch (deatils.getName()) {
		case "PackCreation" : 
				return new Object[][] {
					{"AutomationPack", "AUTOPack" , "Description", "10", "30", "1000", "500", "Purchase price", "2000", "1000", "Renew purchase Price", "Dear Subscriber, your data pack _$pkgname$_ has been successfully activated. Enjoy uninterrupted browsing! For details, dial *123#.",
	"Dear Subscriber, your data pack _$pkgname$_ has been successfully activated. Enjoy uninterrupted browsing! For details, dial *123#.",
	"Dear Subscriber, your data pack _$pkgname$_ has been successfully activated. Enjoy uninterrupted browsing! For details, dial *123#."
					}
			};
		case "AddEmail2CreatedPack" : 
			return new Object[][] {
				{"AutomationPack","Dear Subscriber, your data pack _$pkgname$_ has been successfully activated. Enjoy uninterrupted browsing! For details, dial *123#."}
			};
		case "RequestTracker" :
			return new Object[][] {
				{"57000001"}
			};
		}
		return null;
		}
	 
	}

