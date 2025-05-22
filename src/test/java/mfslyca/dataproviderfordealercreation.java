package mfslyca;


	import java.lang.reflect.Method;

	import org.testng.annotations.DataProvider;


	public class dataproviderfordealercreation {
	

		
		
		@DataProvider
		public static Object[] [] getDetails(Method deatils)
		{
			
		switch (deatils.getName()) 
		{
		
		case "DealerCreation":
			return new Object[][]
		    {
				{"9456787662" ,"lyca","abcd1236@gmail.com","23456789043567898","909876543234583","Recharge","234067890345692"}
		    };
		case "DealerCreationwithdupICCID":
			return new Object[][]
		    {
				{"9123456784","lyca","abcd12390@gmail.com","236789043567890","909876543234578","Rechargee","234067890345059"}
		    };
		case "DealerCreationwithdupimsi":
			return new Object[][]
			  {
					{"9123456784","lyca","abcd12390@gmail.com","236789043567890","909876589234509","Rechare","234067890345673"}
	    
			  };
		    
		case "CreditControlValidation":
			return new Object[][]
		    {
				{"RECHARGE"}
		    };
		case "AMLValidation":
			return new Object[][]
		    {
				{"RECHARGE"}
		    };
		case "FinalValidation":
			return new Object[][]
		    {
				{"RECHARGE"}
		    };

		case "RejectCreditControlValidation":
			return new Object[][]
		    {
				{"INIDRA","ertyuio"}
		    };
		case "RejectAMLValidation":
			return new Object[][]
		    {
				{"INIDRA","ertyuio"}
		    };
		case "RejectFianlValidation":
			return new Object[][]
		    {
				{"INIDRA","ertyuio"}
		    };
		
		    
	    }
		return null;
		}
	}


