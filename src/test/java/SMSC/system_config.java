package SMSC;


	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	/**
	 * Test class for adding barring systemwise from the source side.
	 * This class automates login, navigation, deletion of existing barring, and adding new barring Rule.
	 */
	public class system_config {

	    static WebDriver driver; // WebDriver instance for browser automation

	    /**
	     * Sets up the WebDriver and opens the target URL.
	     * @throws InterruptedException 
	     */
	    @BeforeClass
	    public void login() throws InterruptedException {
	        driver = new FirefoxDriver(); // Initializes the Firefox browser
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Implicit wait for 5 seconds
	        driver.get("https://10.0.6.115:3003/smsc"); // Opens the SMSC URL
	        driver.findElement(By.xpath("/html/body/div/div/div/form/div[1]/div[1]/div/div[1]/div/span/input")).sendKeys("admin"); // Inputs username
	        Thread.sleep(1500); // Waits for 1.5 seconds
	        driver.findElement(By.xpath("/html/body/div/div/div/form/div[2]/div/div/div/div/span/input")).sendKeys("T4y4n4"); // Inputs password
	        Thread.sleep(1500);
	        driver.findElement(By.cssSelector("#tsslogin-form > div:nth-child(5) > div > div > div > div > button")).click(); // Clicks the login button
	        Thread.sleep(1000);
	        
	    }
	    /**
	     * Deletes an existing barring entry if it exists.
	     * Logs a message indicating whether an entry was deleted or not.
	     * @throws InterruptedException 
	     */
	    @Test(priority = 1)
	    public void deleteExistingBarringsource() throws InterruptedException {
	    	driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[4]/a")).click(); // Clicks on the Barring Module link
	        Thread.sleep(1000);
	        try {
	            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg")).click();
	            Thread.sleep(1500);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/fieldset/input")).click();
	            Thread.sleep(1500);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/div/ul/li")).click();
	            Thread.sleep(1500);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button[1]")).click();
	            Thread.sleep(1500);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("7975671916");
	            Thread.sleep(1500);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]")).click();
	            Thread.sleep(1500);
	            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div:nth-child(3) > div > div > div:nth-child(1) > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(11) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon > path")).click();
	            Thread.sleep(1500);
	            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	            Thread.sleep(1500);
	            System.out.println("Existing barring deleted successfully.");
	        } catch (Exception e) {
	            System.out.println("No existing barring, proceeding to add a new one.");
	        }
	    }

	    /**
	     * Adds a new barring entry with predefined details.
	     * @throws InterruptedException Thrown if thread sleep is interrupted.
	     */
	    @Test(priority = 2)
	    public void addNewBarringsource() throws InterruptedException {
	        driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div.button-container > svg")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/fieldset/input")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("//*[@id=\"TSSGUI_MultiSelectOptionsStyle\"]")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button[1]")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("7975671916");
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[2]/div/div/fieldset/input")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[3]/div/div/fieldset/input")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[3]/div/div/fieldset/input")).click();
	        Thread.sleep(1500);
	        WebElement firstInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[4]/div/fieldset/div[1]/div/input"));
	        firstInput.clear();
	        firstInput.sendKeys("10"); // Sets the first input field to 10
	        Thread.sleep(1500);
	        WebElement secondInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[5]/div/fieldset/div[1]/div/input"));
	        secondInput.clear();
	        secondInput.sendKeys("12"); // Sets the second input field to 12
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[6]/div/div/fieldset/input")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[6]/div/div/div/div/ul/li[3]")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/fieldset/input")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/div/div/ul/li[1]")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[8]/div/fieldset/textarea")).sendKeys("This is Automated barring Feature from systemwise source");
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]")).click();
	        Thread.sleep(1500);
	        driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	        Thread.sleep(1500);
	    }

	    /* Locates and deletes The existing barring rule which is exist
	     * Trys to locate the duplicate barring rule
	     * catches the duplicate barring rule and deletes the existing barring rule
	     */
	 
	        @Test(priority = 3)
	        public void deleteExistingdestinationsource() throws InterruptedException {
	        	
	        	try {
	            // Locate and click on the Modification icon
	            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg")).click();
	            Thread.sleep(1000);
	 
	            // Select destination address
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[2]/div/div/fieldset/input")).click();
	            Thread.sleep(1000);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[2]/div/div/div/ul/li[1]")).click();
	            Thread.sleep(1000);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button[1]")).click();
	            Thread.sleep(1000);
	 
	            // Fill in and search for the destination address
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9980862664");
	            Thread.sleep(1000);
	            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]")).click();
	            Thread.sleep(1000);
	 
	            // Click delete icon
	            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div:nth-child(3) > div > div > div:nth-child(1) > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(11) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon > path"))
	                    .click();
	            Thread.sleep(1000);
	            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	            Thread.sleep(1000);
	            System.out.println("Existing barring deleted successfully.");
	        } catch (Exception e) {
	            System.out.println("No existing barring found: " + e.getMessage());
	        }
	    }

	        /*
	         * After deletion From here The addition Functionality will start
	         */
	        
	        
	            @Test(priority = 4)
	            public void addNewBarringdestinationsource() throws InterruptedException {
	     
	                // Locate and click on add icon
	                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div.button-container > svg"))
	                        .click();
	                Thread.sleep(1000);
	     
	                // Select destination address
	                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[2]/div/div/fieldset/input"))
	                        .click();
	                Thread.sleep(1000);
	                driver.findElement(By.xpath("//*[@id=\"TSSGUI_MultiSelectOptionsStyle\"]")).click();
	                Thread.sleep(1000);
	                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button[1]"))
	                        .click();
	                Thread.sleep(1000);
	     
	                // Fill in source address
	                driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("9980862664");
	                Thread.sleep(1000);
	     
	                // Select Source (TON & NPI) and set min/max values
	                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[2]/div/div/fieldset/input")).click();
	                Thread.sleep(1000);
	                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[2]/div/div/fieldset/input")).click();
	                Thread.sleep(1000);
	                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[3]/div/div/fieldset/input")).click();
	                Thread.sleep(1000);
	                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[3]/div/div/fieldset/input")).click();
	                Thread.sleep(1000);
	                WebElement firstInput = driver.findElement(By.xpath(
	                        "/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[4]/div/fieldset/input"));
	                firstInput.clear();
	                firstInput.sendKeys("10");
	                Thread.sleep(1000);
	     
	                WebElement secondInput = driver.findElement(By.xpath(
	                        "/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[5]/div/fieldset/input"));
	                secondInput.clear();
	                secondInput.sendKeys("12");
	                Thread.sleep(1000);
	     
	                // Select Action and Status
	                driver.findElement(By.xpath(
	                        "/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[6]/div/div/fieldset/input"))
	                        .click();
	                Thread.sleep(1000);
	                driver.findElement(By.xpath(
	                        "/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[6]/div/div/div/div/ul/li[3]"))
	                        .click();
	                Thread.sleep(1000);
	     
	                driver.findElement(By.xpath(
	                        "/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/fieldset/input"))
	                        .click();
	                Thread.sleep(1000);
	                driver.findElement(By.xpath(
	                        "/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/div/div/ul/li[1]"))
	                        .click();
	                Thread.sleep(1000);
	     
	                // Fill description
	                driver.findElement(By.xpath(
	                        "/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[8]/div/fieldset/textarea"))
	                        .sendKeys("This is Automated barring Feature from systemwise destination");
	                Thread.sleep(1000);
	     
	                // Click Add icon
	                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]"))
	                        .click();
	                Thread.sleep(1000);
	     
	                // Confirm addition
	                driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
	                Thread.sleep(1000);
	     
	                System.out.println("Barring added successfully.");
	     
	        }
        @Test(priority = 5)
        public void deleteExistingBarringAppsource() throws InterruptedException {
        	//driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[4]/a")).click();
        	
        	//Locates and clicks on application button in barring page 
        	driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[2]/a")).click();
            Thread.sleep(1000);
            
            try {
                // Click the modification icon for an existing barring entry
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg")).click();
                Thread.sleep(1500);

                // Select source address to delete
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/fieldset/input")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/div/ul/li")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button[1]")).click();
                Thread.sleep(1500);

                // Select and delete the specific barring entry
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/fieldset/input")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/div/div/ul/li[5]")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[2]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9696969693");
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]")).click();
                Thread.sleep(1500);

                // Confirm deletion
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div:nth-child(3) > div > div > div:nth-child(1) > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(11) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon > path")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
                Thread.sleep(1500);

                // Close the modification window
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[3]/div/div/div[2]/div/button")).click();
                Thread.sleep(1500);

                System.out.println("Existing barring deleted successfully.");
            } catch (Exception e) {
                System.out.println("No existing barring found, proceeding to add a new one.");
            }
        }
        
        /*
         * Adds the new barring rule After deletion 
         */

        @Test(priority = 6)
        public void addNewBarringAppsource() throws InterruptedException {
        ///html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/fieldset/input
        	//Locates and clicks on application button in barring page 
        	driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[2]/a")).click();
            Thread.sleep(1000);
        	
            // Click the Add icon to create a new barring entry
            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div.card > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div > svg.svg-inline--fa.fa-plus.tss-add.tss-icon")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            
           
            driver.findElement(By.xpath("//*[@id=\"TSSGUI_MultiSelectOptionsStyle\"]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button[1]")).click();
            Thread.sleep(1500);
            // Select the source address for the new barring entry
           
            // Fill in the details for application barring
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/div/div/ul/li[5]")).click();
            Thread.sleep(1500);

            // Enter the source address details
            driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("9696969693");
            Thread.sleep(1500);

            // Specify the Min and Max source address values
            WebElement firstInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[5]/div/fieldset/div[1]/div/input"));
            firstInput.clear();
            firstInput.sendKeys("10");
            Thread.sleep(1500);

            WebElement secondInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[6]/div/fieldset/div[1]/div/input"));
            secondInput.clear();
            secondInput.sendKeys("12");
            Thread.sleep(1500);

            // Set action and status for the barring entry
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/div/div/ul/li[3]")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[8]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[8]/div/div/div/div/ul/li[1]")).click();
            Thread.sleep(1500);

            // Add a description for the barring entry
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[9]/div/fieldset/textarea")).sendKeys("This is an Automated barring Feature for Applicationwise source");
            Thread.sleep(1500);

            // Save the barring entry
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]")).click();
            Thread.sleep(1500);

            // Confirm the save action
            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
            Thread.sleep(6000);
        }
        @Test(priority = 7)
        public void existingDeletionAppdestination() throws InterruptedException {
            try {

                // Locate and click on the Modification icon
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg")).click();
                Thread.sleep(1500);

                // Select and click on the destination address
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[2]/div/div/fieldset/input")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[2]/div/div/div/ul/li[1]")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button")).click();
                Thread.sleep(1500);

                // Fill the details to be deleted
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/fieldset/input")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/div/div/ul/li[11]")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[2]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9449320783");
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]")).click();
                Thread.sleep(1500);

                // Click delete icon
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div:nth-child(3) > div > div > div:nth-child(1) > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(11) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon > path")).click();
                Thread.sleep(1500);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
                Thread.sleep(1500);
                
                // Click close icon after deletion
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[3]/div/div/div[2]/div/button"));
                Thread.sleep(1500);

                System.out.println("Existing charging deleted successfully.");
            } catch (Exception e) {
                System.out.println("No existing charging, proceeding to add a new one.");
            }
        }

        @Test(priority = 8)
        public void additionAppdestination() throws InterruptedException {
            // Locate and click on add icon
            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div.button-container > svg.svg-inline--fa.fa-plus.tss-add.tss-icon")).click();
            Thread.sleep(1500);

            // Locating and selecting destination address
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[2]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//*[@id=\"TSSGUI_MultiSelectOptionsStyle\"]")).click();
            Thread.sleep(1500);

            // Locates and clicks on proceed After selecting source address
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[3]/button[1]")).click();
            Thread.sleep(1500);

            // Locates and select the application barring
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[1]/div/div/div/div/ul/li[11]")).click();
            Thread.sleep(1500);

            // Locates and fills the source address in the text box
            driver.findElement(By.xpath("//*[@id=\"TSSGUI_InputTextFieldStyle\"]")).sendKeys("9449320783");
            Thread.sleep(1500);

            // Locates and selects the Source (TON & NPI)
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[3]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[4]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[4]/div/div/fieldset/input")).click();
            Thread.sleep(1500);

            // Locates and fills the values of Min and Max source address value
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[5]/div/fieldset/input")).clear();
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[5]/div/fieldset/input")).sendKeys("10");
            Thread.sleep(1500);

            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[6]/div/fieldset/input")).clear();
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[6]/div/fieldset/input")).sendKeys("12");
            Thread.sleep(1500);

            // Locates and selects the Action
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[7]/div/div/div/div/ul/li[3]")).click();
            Thread.sleep(1500);

            // Locates and selects the status of the action
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[8]/div/div/fieldset/input")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[8]/div/div/div/div/ul/li[1]")).click();
            Thread.sleep(1500);

            // Locates and fills the description text box
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[1]/div[9]/div/fieldset/textarea")).sendKeys("This is Automated barring Feature from Applicationwise destination");
            Thread.sleep(1500);

            // Locates and clicks on Add icon
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/section/div/div/div[2]/button[1]")).click();
            Thread.sleep(1500);

            // Click add on the alert message tab
            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
            Thread.sleep(1500);

            System.out.println("Barring addition completed successfully.");
        }
        
        //Redirection module 
        
        @Test(priority = 9)
        public void deleteExistingRedirectrulesourcesystemwise() throws InterruptedException {
        	// Navigate to the site map icon
            driver.findElement(By.xpath("/html/body/div/div/nav/ul[2]/li[1]/div/i")).click();
            Thread.sleep(1000);
        	
            // Navigate to Redirection module
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[5]/a")).click();
            Thread.sleep(1000);

            try {
                // Locate and delete existing charging
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(3) > div > svg > path")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[1]/div/fieldset/div[1]/div/input")).sendKeys("9638527410");
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[2]/button[1]")).click();
                Thread.sleep(1000);
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(5) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
                Thread.sleep(1000);

                System.out.println("Existing charging deleted successfully.");
            } catch (Exception e) {
                System.out.println("No existing charging found, skipping deletion.");
            }
        }
        
        @Test(priority = 10)
        public void addRedirectionrulesourcesystemwise() throws InterruptedException {
            // Click on Add icon
            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div.button-container > svg.svg-inline--fa.fa-plus.tss-add.tss-icon")).click();
            Thread.sleep(1000);

            // Fill in redirection details
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[1]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9638527410");
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[2]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[3]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[3]/div/div/fieldset/input")).click();
            Thread.sleep(1000);


            // Set Min and Max destination address values
            WebElement firstInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[4]/div/fieldset/div[1]/div/input"));
            firstInput.clear();
            firstInput.sendKeys("10");
            Thread.sleep(1000);

            WebElement secondInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[5]/div/fieldset/div[1]/div/input"));
            secondInput.clear();
            secondInput.sendKeys("12");
            Thread.sleep(1000);

            // Select Redirect Type and Application
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[6]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[6]/div/div/div/div/ul/li[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/div/div/div/ul/li[1]")).click();
            Thread.sleep(1000);

            // Add description and save
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[9]/div/fieldset/textarea")).sendKeys("This Is Automated Redirection creation");
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[2]/button[1]")).click();
            Thread.sleep(1000);

            // Confirm addition
            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
            Thread.sleep(1000);

            System.out.println("Redirection added successfully.");
        }
        
        @Test(priority = 11)
        public void existingDeletion() throws InterruptedException {

            // Navigate to the application redirection page
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/ul/li[2]/a")).click();
            Thread.sleep(1000);

            try {
                // Attempt to delete existing charging
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(3) > div > svg > path")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/fieldset/input")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[1]/div/div/div/div/ul/li[11]")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9874563210");
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[2]/button[1]")).click();
                Thread.sleep(1000);
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(5) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div/div[2]/div/button")).click();
                Thread.sleep(1000);
                System.out.println("Existing charging deleted successfully.");
            } catch (Exception e) {
                System.out.println("No existing charging found, skipping deletion.");
            }
        }

        @Test(priority = 12)
        public void redirectionAddition() throws InterruptedException {
            // Add new redirection
            driver.findElement(By.cssSelector("#rightSectionDiv > section > div > div > div > div > div > div > div > div.p-datatable-header > div > div.mt-2 > div.button-container > svg.svg-inline--fa.fa-plus.tss-add.tss-icon")).click();
            Thread.sleep(1000);

            // Select "From" application
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[1]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[1]/div/div/div/div/ul/li[11]")).click();
            Thread.sleep(1000);

            // Enter destination address
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[2]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9874563210");
            Thread.sleep(1000);

            // Set Min and Max destination values
            WebElement firstInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[5]/div/fieldset/div[1]/div/input"));
            firstInput.clear();
            firstInput.sendKeys("10");
            Thread.sleep(1000);

            WebElement secondInput = driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[6]/div/fieldset/div[1]/div/input"));
            secondInput.clear();
            secondInput.sendKeys("12");
            Thread.sleep(1000);

            // Set redirect type
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[7]/div/div/div/div/ul/li[1]")).click();
            Thread.sleep(1000);

            // Select application
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[8]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[8]/div/div/div/div/ul/li[5]")).click();
            Thread.sleep(1000);

            // Set status
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[9]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[9]/div/div/div/div/ul/li[1]")).click();
            Thread.sleep(1000);

            // Enter description
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[1]/div[10]/div/fieldset/textarea")).sendKeys("This Is Automated Redirection creation");
            Thread.sleep(1000);

            // Click Add button
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[1]/section/div/div/div[2]/button[1]")).click();
            Thread.sleep(1000);

            // Confirm addition
            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
            Thread.sleep(2000);
        }
        
        //Charging module 
        
        @Test(priority = 13, enabled=false)
        public void chargingexistingDeletionsource() throws InterruptedException {
        	
        	/*// Navigate to the site map icon
            driver.findElement(By.xpath("/html/body/div/div/nav/ul[2]/li[1]/div/i")).click();
            Thread.sleep(1000);
        	
            // Locates and clicks on charging module in site map
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[6]/a")).click();
            Thread.sleep(1000);*/
            
            try {

                // Locate and click on the Modification icon
                driver.findElement(By.cssSelector(
                        "#rightSectionDiv > section > div:nth-child(3) > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg"))
                        .click();
                Thread.sleep(1000);

                // Select and click on the source address
                driver.findElement(By.xpath(
                        "/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/fieldset/input"))
                        .click();
                Thread.sleep(1000);
                driver.findElement(By.xpath(
                        "/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/div/ul/li"))
                        .click();
                Thread.sleep(1000);
                driver.findElement(
                        By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button[1]"))
                        .click();
                Thread.sleep(1000);

                // Fill in the source address and search
                driver.findElement(By.xpath(
                        "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[1]/div/fieldset/input"))
                        .sendKeys("9874543256");
                Thread.sleep(1000);
                driver.findElement(
                        By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[2]/button[1]"))
                        .click();
                Thread.sleep(1000);

                // Click delete icon
                driver.findElement(By.cssSelector(
                        "#rightSectionDiv > section > div:nth-child(3) > div:nth-child(3) > div > div.card-body.d-flex.align-items-center.py-8 > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path"))
                        .click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
                Thread.sleep(1000);

                System.out.println("Existing charging deleted successfully.");
            } catch (Exception e) {
                System.out.println("No existing charging, proceeding to add a new one.");
            }
        }
        
        @Test(priority = 14, enabled=false)
        public void Additionofchargingrulesourcewise() throws InterruptedException {
        	
        	 // Locates and clicks on charging module in site map
            //driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[6]/a")).click();
            //Thread.sleep(1000);
            
            // Locates and clicks on add icon
            driver.findElement(By.xpath(
                    "//*[name()='svg']/*[name()='path' and @d='M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32l0 144L48 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l144 0 0 144c0 17.7 14.3 32 32 32s32-14.3 32-32l0-144 144 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-144 0 0-144z']"))
                    .click();
            Thread.sleep(1000);

            // Locates and select the source option
            driver.findElement(By.xpath(
                    "/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/fieldset/input"))
                    .click();
            Thread.sleep(1000);
            driver.findElement(By
                    .xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[1]/div/div/div/ul/li"))
                    .click();
            Thread.sleep(1000);

            // Locates and clicks on the proceed Button
            driver.findElement(
                    By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button[1]"))
                    .click();
            Thread.sleep(1000);

            // Locates and Fills the source VMSC
            driver.findElement(By.xpath(
                    "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[1]/div/fieldset/div[1]/div/input"))
                    .sendKeys("9874543256");
            Thread.sleep(1000);

            // Locates and Fills the description
            driver.findElement(By.xpath(
                    "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[2]/div/fieldset/textarea"))
                    .sendKeys("This is automated Charging creation");
            Thread.sleep(1000);

            // Locates and selects the status of the action
            driver.findElement(By.xpath(
                    "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[4]/div/div/fieldset/input"))
                    .click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(
                    "/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[4]/div/div/div/div/ul/li[1]"))
                    .click();
            Thread.sleep(1000);

            // Locates and clicks on Add icon
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[2]/button[1]"))
                    .click();
            Thread.sleep(1000);

            // To Click add on the alert message tab
            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
            Thread.sleep(1000);

            System.out.println("Charging rule added successfully.");
        }

        @Test(priority = 15, enabled=false)
        public void deletingexistingchargingruledestination() throws InterruptedException {
            // Navigate to Charging Module
         //   driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[6]/a")).click();
            Thread.sleep(1000);
            
            try {
                // Locate and click on the Modification icon
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div:nth-child(3) > div > div > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg")).click();
                Thread.sleep(1000);
        
                // Select and click on the destination address
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/fieldset/input")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/div/ul/li")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button")).click();
                Thread.sleep(1000);
        
                // Fill in the destination address and search
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[1]/div/fieldset/input")).sendKeys("9845687516");
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div/div[2]/button[1]")).click();
                Thread.sleep(1000);
        
                // Click delete icon
                driver.findElement(By.cssSelector("#rightSectionDiv > section > div:nth-child(3) > div:nth-child(3) > div > div.card-body.d-flex.align-items-center.py-8 > div > div > div > div.p-datatable-wrapper > table > tbody > tr > td:nth-child(6) > div > svg.svg-inline--fa.fa-trash.tss-delete.tss-icon.gap > path")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
                Thread.sleep(1000);
        
                System.out.println("Existing charging deleted successfully.");
            } catch (Exception e) {
                System.out.println("No existing charging, proceeding to add a new one.");
            }
        }
        
        @Test(priority = 16, enabled=false)
        public void AddNewchargingruledestination() throws InterruptedException {
            // Locate and click on Add icon
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div/div/div[2]/div/div[2]/ul/li[6]/a")).click();
            Thread.sleep(1000);
            
            
            driver.findElement(By.xpath("//*[name()='svg']/*[name()='path' and @d='M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32l0 144L48 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l144 0 0 144c0 17.7 14.3 32 32 32s32-14.3 32-32l0-144 144 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-144 0 0-144z']")).click();
            Thread.sleep(1000);
            
            // Select destination option
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[2]/div/div/div/ul/li")).click();
            Thread.sleep(1000);
            
            // Click process button
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[1]/section/div/div/div/div[3]/button[1]")).click();
            Thread.sleep(1000);
            
            // Fill destination address and length
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[1]/div/div/div[2]/div/fieldset/div[1]/div/input")).sendKeys("9845687516");
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[2]/div/fieldset/div[1]/div/input")).sendKeys("10");
            Thread.sleep(1000);
            
            // Fill description
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[3]/div/fieldset/textarea")).sendKeys("This is automated Charging creation");
            Thread.sleep(1000);
            
            // Select status
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[5]/div/div/fieldset/input")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[1]/div[5]/div/div/div/div/ul/li[1]")).click();
            Thread.sleep(1000);
            
            // Click Add icon to save
            driver.findElement(By.xpath("/html/body/div/div/div[1]/section/div[2]/div[2]/section/div/div/div[2]/button[1]")).click();
            Thread.sleep(1000);
            
            // Handle alert
            driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]")).click();
            Thread.sleep(1000);

            System.out.println("Charging added successfully.");
        }

         @AfterClass
	    public void tearDown() {
	       // driver.quit(); // Quits the browser
	    }
	}


