package ssfc;




	import org.testng.annotations.BeforeClass;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.Test;

	import java.nio.file.Paths;
	import java.sql.*;
	import java.time.Duration;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class fgufhwgg extends DataProvider4CSMP {
			WebDriver driver= new ChromeDriver();
				
			@BeforeClass 
			public void CSMP_Login () {
				driver.get("https://10.0.6.115:8000/csmp/");
			    driver.manage().window().setSize(new Dimension(1050, 700));
			    driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();
			    driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();
			    driver.findElement(By.name("username")).sendKeys("tayana");
			    driver.findElement(By.name("password")).sendKeys("Tayana@1234");
			    driver.findElement(By.id("subBttn")).click();
		}
			@Test (dataProvider = "getDetails", priority = 1, enabled = false)
			public void PackCreation(String pkg_name, String keywd, String description , String RenewCount , String validity, String purchaseUnit, String minBal, String PackPurchaseDebitComment,
					String renewUnits, String RenewminBal, String renewComment, String purchNotiTemplCsmp, String purchNotiTempl, String  renewNotiTempl ) throws InterruptedException, ClassNotFoundException {
				
				
				 	String dbURL = "jdbc:oracle:thin:@//10.0.0.191:1521/BPLORA"; 
			        String dbUser = "csmp";
			        String dbPass = "T55_csmp";
			        
				try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
		            
		            // 1️⃣ SELECT pkg_id and alias first
		            String selectQuery = "SELECT PKG_ID, ALIAS FROM CSMP_PKG_MAST WHERE ALIAS = ?";
		            PreparedStatement pstmtSelect = conn.prepareStatement(selectQuery);
		            pstmtSelect.setString(1, pkg_name);
		            ResultSet rs = pstmtSelect.executeQuery();

		            int pkgId = -1;
		            String alias = "";

		            if (rs.next()) {
		                pkgId = rs.getInt("PKG_ID");
		                alias = rs.getString("ALIAS");
		                System.out.println("Found Package → ID: " + pkgId + ", Alias: " + alias);
		            } else {
		                System.out.println("⚠️ Package not found: " + pkg_name);
		               
		            }

		            // 2️⃣ UPDATE status and alias
		            String updateQuery = "UPDATE CSMP_PKG_MAST " +
		                                 "SET STATUS = -1, " +
		                                 "    ALIAS = ALIAS || TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') " +
		                                 "WHERE PKG_ID = ?";
		            PreparedStatement pstmtUpdate = conn.prepareStatement(updateQuery);
		            pstmtUpdate.setInt(1, pkgId);
		            int rowsUpdated = pstmtUpdate.executeUpdate();
		            conn.setAutoCommit(true);
		            System.out.println("Rows Updated in CSMP_PKG_MAST: " + rowsUpdated);

		            // 3️⃣ DELETE from CSMP_KEY_MAST
		            String deleteQuery1 = "DELETE FROM CSMP_KEY_MAST WHERE KEY_ID = ?";
		            PreparedStatement pstmtDelete1 = conn.prepareStatement(deleteQuery1);
		            pstmtDelete1.setInt(1, pkgId);
		            int rowsDeleted1 = pstmtDelete1.executeUpdate();
		            conn.setAutoCommit(true);
		            System.out.println("Rows Deleted in CSMP_KEY_MAST: " + rowsDeleted1);

		           // 4️⃣ DELETE from CSMP_PKG_KEYWORD_DETAILS
		            String deleteQuery2 = "DELETE FROM CSMP_PKG_KEYWORD_DETAILS WHERE PKG_ID = ?";
		            PreparedStatement pstmtDelete2 = conn.prepareStatement(deleteQuery2);
		            pstmtDelete2.setInt(1, pkgId);
		            int rowsDeleted2 = pstmtDelete2.executeUpdate();
		            conn.setAutoCommit(true);
		            System.out.println("Rows Deleted in CSMP_PKG_KEYWORD_DETAILS: " + rowsDeleted2);

		            // ✅ Commit changes
		           // conn.commit();

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			
		    
				// Create a Pack
				
				driver.findElement(By.xpath("//*[@id=\"tssContentDiv\"]/section[2]/div/div[2]/div/div[2]/ul/li[8]/a")).click();  // Click on Create Pack
				Thread.sleep(3000);
				driver.findElement(By.id("pkgName")).sendKeys(pkg_name); // Click on Pack name
				Thread.sleep(3000);
				driver.findElement(By.id("pkgKwd")).sendKeys(keywd); // enter keyword name
				Thread.sleep(3000);
				driver.findElement(By.id("description")).sendKeys(description); // enter description
				Thread.sleep(3000);
				driver.findElement(By.id("AllowGifting")).click(); // Click on gift option
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"renewType\"]/option[2]")).click();  // click on renew auto otion
				Thread.sleep(3000);
				driver.findElement(By.id("renewCount")).sendKeys(RenewCount);  // enter on renew validity
				Thread.sleep(3000);
				driver.findElement(By.id("validityValue")).sendKeys(validity);  // enter the validity 
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"packClassSel\"]/option[3]")).click();  // click on pack group
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"chargePanel\"]/div[1]/div/a/div/b")).click();  // click on charging price
				Thread.sleep(3000);
				driver.findElement(By.id("purchaseUnits")).sendKeys(purchaseUnit);
				Thread.sleep(3000);
				driver.findElement(By.id("minBal")).sendKeys(minBal);
				Thread.sleep(3000);
				driver.findElement(By.id("PackPurchaseDebitComment")).sendKeys(PackPurchaseDebitComment);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//b[normalize-space()='Renew/Repeat']")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("renewUnits")).sendKeys(renewUnits);
				Thread.sleep(3000);
				driver.findElement(By.id("renewMinBal")).sendKeys(RenewminBal);
				Thread.sleep(3000);
				driver.findElement(By.id("renewComment")).sendKeys(renewComment);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[@href='#servicePanelBody']//div[@class='col-xs-12']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"service_1\"]/option[6]")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"grant_1\"]/option[2]")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"valPanel\"]/div[1]/div/a/div/b")).click();  // Click on validation
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"valPanelBody\"]/div/div[1]/div/button")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"valPanelBody\"]/div/div[1]/div/ul/li[2]/a/label/input")).click();  // Click on subscriber type
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"valPanelBody\"]/div/div[2]/div/button")).click(); // Click on interface select
				driver.findElement(By.xpath("//*[@id=\"valPanelBody\"]/div/div[2]/div/ul/li[2]/a/label/input")).click();  // Click on interface
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"valPanelBody\"]/div/div[3]/div/button")).click();
				driver.findElement(By.xpath("//*[@id=\"valPanelBody\"]/div/div[3]/div/ul/li[2]/a/label/input")).click();  // Click on cust grop
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[4]//div[1]//button[1]")).click();
				driver.findElement(By.xpath("//*[@id=\"smsPanel\"]/div[1]/div/a/div/b")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"purchNotiTemplCsmp\"]")).sendKeys(purchNotiTemplCsmp);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"purchNotiTempl\"]")).sendKeys(purchNotiTempl);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"renewNotiTempl\"]")).sendKeys(renewNotiTempl);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"AddPackButton\"]/button")).click();
				Thread.sleep(3000);
				
				Alert alert = driver.switchTo().alert();
			    System.out.println("Alert message: " + alert.getText());
			    alert.accept();
			    Thread.sleep(1000);
			    
			    Alert alert1 = driver.switchTo().alert();
			    System.out.println("Alert message: " + alert1.getText());
			    alert1.accept();
			    Thread.sleep(2000);
			    
			    
			    // Check the package in db.
		        Class.forName("oracle.jdbc.OracleDriver");
				try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.0.0.191:1521/BPLORA", "csmp",
						"T55_csmp")) {
					// Creating a statement object
					Statement statement = connection.createStatement();
					// Executing the SQL query
					String sqlQuery = "SELECT PKG_ID, STATUS FROM CSMP_PKG_MAST WHERE ALIAS = '" + pkg_name
							+ "' ";
					ResultSet resultSet = statement.executeQuery(sqlQuery);
					// Check if any results exist
					if (resultSet.next()) {
						int pkgID = resultSet.getInt("PKG_ID");
						int status= resultSet.getInt("STATUS");
						System.out.println("PKG_ID: " + pkgID );
						System.out.println("STATUS : "+ status);
					} else {
						System.out.println("No data found for Alias = '" + pkg_name + "'");
					}

					// Close resources
					resultSet.close();
					statement.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Thread.sleep(3000);
				
				// Wait until the alert is visible
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
				                                By.xpath("//*[@id='pkgAddMainDiv']/div[1]/div/div")));

				// Get the text
				String alertText = successAlert.getText().trim();
				System.out.println("Alert message: " + alertText);

				// Assert that it contains expected message
				Assert.assertTrue(alertText.contains("Package Created successfully"),
				                  "Success message not displayed as expected!");
				Thread.sleep(2000);
			}
			
			@Test ( dataProvider = "getDetails", priority = 2 , enabled = true)
			public void AddEmail2CreatedPack(String pkg_name, String purchNotiTemplCsmp ) throws InterruptedException {
				driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"tssContentDiv\"]/section[2]/div/div[2]/div/div[2]/ul/li[9]/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"pkgDet_filter\"]/label/input")).sendKeys(pkg_name);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"pkgDet\"]/tbody/tr[2]/td[1]/a")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//b[normalize-space()='SMS Templates(Advanced)']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"managePanel\"]/div[1]/a/h4")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"smsGroup\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"smsGroup\"]/option[6]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"smsEvent\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"smsEvent\"]/option[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"emailTempl\"]")).sendKeys(purchNotiTemplCsmp);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"AddPackButton\"]/button")).click();
				Thread.sleep(2000);
				Alert alert = driver.switchTo().alert();
			    System.out.println("Alert message: " + alert.getText());
			    alert.accept();
			    Thread.sleep(1000);
			    
			    Alert alert1 = driver.switchTo().alert();
			    System.out.println("Alert message: " + alert1.getText());
			    alert1.accept();
			    Thread.sleep(2000);
			}
			
			
			@Test (enabled = false)
			public void SubscriberBaseReport() throws InterruptedException {
				driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/aside[1]/section[2]/div[1]/div[4]/div[1]/div[2]/ul[1]/li[1]/a[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@id='submitVal']//input[@value='GO']")).click();
				Thread.sleep(2000);
			}
			
			@Test (enabled = false)
			public void PackSubsReport() throws InterruptedException {
				driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Pack Subscription']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SubsMisDiv\"]/div[1]/div[1]/div[1]/div[1]/div/button")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SubsMisDiv\"]/div[1]/div[1]/div[1]/div[1]/div/ul/li[2]/a/label")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SubsMisDiv\"]/div[1]/div[1]/div[1]/div[2]/div/button")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SubsMisDiv\"]/div[1]/div[1]/div[1]/div[2]/div/ul/li[2]/a/label/input")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"YearlyBtn\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"SubsMisDiv\"]/div[1]/div[1]/div[2]/div[6]/button")).click();
				Thread.sleep(2000);
			}
			
			@Test (enabled = false)
			public void PromoSubsReport() throws InterruptedException {
				driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Promo Subscription']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='box-body']//div[1]//div[1]//div[1]//button[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value='3969']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='btn-group']//button[@title='None Selected '][normalize-space()='None Selected']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='btn-group open']//input[@value='multiselect-all']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='YearlyBtn']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[normalize-space()='GO']")).click();
				Thread.sleep(2000);
			}
			
			@Test (dataProvider = "getDetails" , enabled = false )
			public void RequestTracker(String MSISDN) throws InterruptedException {
				driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Request Tracker']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='fullMsisdn']")).sendKeys(MSISDN);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
				Thread.sleep(2000);
				// Details for the Subscriber
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
				                                By.xpath("//div[@class='text-center']//label[1]")));

				// Get the text
				String alertText = successAlert.getText().trim();
				System.out.println("Alert message: " + alertText);

				// Assert that it contains expected message
				Assert.assertTrue(alertText.contains("Details for the Subscriber"),
				                  "Success message not displayed as expected!");
				Thread.sleep(2000);
			}
			
			@Test (enabled = false)
			public void EmailImport() throws InterruptedException {
				driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Email Address Import']")).click();
				Thread.sleep(2000);
				WebElement uploadEmailMsisdn= driver.findElement(By.xpath("//input[@id='fileMsisdn']"));
				Thread.sleep(2000);
				String filepath = Paths.get("C:\\Users\\Indira\\OneDrive - Tayana Mobility Technologies\\IndiraNewDoc\\Notepad++\\email.txt").toString();
				Thread.sleep(2000);
				uploadEmailMsisdn.sendKeys(filepath);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@name='addBtn']")).click();
				Thread.sleep(2000);
				
				Alert alert = driver.switchTo().alert();
			    System.out.println("Alert message: " + alert.getText());
			    alert.accept();
			    Thread.sleep(2000);
				
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
				                                By.xpath("//div[@class='alert alert-success alert-dismissable text-center']")));

				// Get the text
				String alertText = successAlert.getText().trim();
				System.out.println("Alert message: " + alertText);

				// Assert that it contains expected message
				Assert.assertTrue(alertText.contains("Request Submit Successful."),
				                  "Success message not displayed as expected!");
				Thread.sleep(2000);
			}
			
			@AfterClass
			public void CSMP_Logout() {
				driver.quit();
			}
	}


