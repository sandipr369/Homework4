import jdk.nashorn.internal.AssertsEnabled;
//import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Registration {
//automation programme for registration on nopcommerce

    protected static WebDriver driver;
    static String expectedResultMsg = "Your registration completed";

    public static void main(String[] args) {

// for chrome driver to talk to actual chrom
        //src\BrowserDriver\chromedriver.exe--path of chrome driver through SRC
        System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");

        //creat driver object

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        driver.findElement(By.xpath("//div[@class=\"gender\"]/span[@class=\"male\"]/input[@id=\"gender-male\"]\n")).click();


        driver.findElement(By.xpath("//input[@id=\"FirstName\"]\n")).sendKeys("sandip");
        driver.findElement(By.xpath("//input[@id=\"LastName\"]\n")).sendKeys("patel");
        driver.findElement(By.xpath("//select[@name=\"DateOfBirthDay\"]\n"));
        Select Day = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthDay\"]\n")));
        Day.selectByIndex(3);
        Select Month = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]\n")));

        Month.selectByVisibleText("May");
        Select Year = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthYear\"]\n")));
        Year.selectByValue("2008");
        DateFormat dateFormat = new SimpleDateFormat("mmddyyyyhhmmss");
        Date date = new Date();


        String date1 = dateFormat.format(date);

        //add the time stamp with mail address to creat unique mail address
        driver.findElement(By.xpath("//input[@id=\"Email\"]\n")).sendKeys("sandip.patel" + date1 + "@gmail.com");

        driver.findElement(By.xpath("//input[@id=\"Company\"]\n")).sendKeys("demonopcommerce");
        driver.findElement(By.xpath("//input[@type=\"password\"]\n")).sendKeys("san123");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("san123");
        driver.findElement(By.xpath("//input[@id=\"register-button\"]\n")).click();
//driver will get the text msg displayeds as registration successfull and store the value in actualregistratio  msg

        String actualRegistersuccessfullMsg = driver.findElement(By.xpath("//div[@class='result']")).getText();

        //To compare the actual and expected strings
        Assert.assertEquals("Test case fail registration scenario", expectedResultMsg, actualRegistersuccessfullMsg);
        driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();


//to quit web page
        driver.quit();


    }

    @Test
    public void currancy() {
        WebDriver driver1;
        System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");

        driver1 = new ChromeDriver();
        driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver1.manage().window().fullscreen();
        driver1.get("https://demo.nopcommerce.com/");
        //driver1.findElement(By.xpath("customerCurrency"));
        Select dollar = new Select(driver1.findElement(By.xpath("//select[@id='customerCurrency']")));
        dollar.selectByVisibleText("US Dollar");
        System.out.println("dollar");

        String actualcurrancyUSD=driver1.findElement(By.xpath("//span[contains(text(),'$1,800.00')]")).getText();
        String expectedcurrancyUSD= "$1,800.00";

        Assert.assertEquals("Price is not in daller",actualcurrancyUSD,expectedcurrancyUSD);
        System.out.println("$1,800.00");
        Select euro = new Select(driver1.findElement(By.xpath("//select[@id='customerCurrency']")));
        euro.selectByVisibleText("Euro");


    }
    @Test
    public  void refertofriend(){
WebDriver driver2;
System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");

driver2=new ChromeDriver();

driver2.manage().window().fullscreen();
driver2.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver2.get("https://demo.nopcommerce.com/");
driver2.findElement(By.xpath("//input[@value='Add to wishlist']")).click();
driver2.findElement(By.xpath("//input[@ value='Email a friend']")).click();
//input[@class='friend-email']
        //DateFormat dateFormat = new SimpleDateFormat("mmddyyyyhhmmss");
        //        Date date = new Date();
        //
        //
        //        String date1 = dateFormat.format(date);
        DateFormat date= new SimpleDateFormat("mmddyyhhmmss");
Date date1=new Date();
        String date2=date.format(date1);

        System.out.println("hello");
        driver2.findElement(By.xpath("//input[@id=\"FriendEmail\"]")).sendKeys("bhavinpatel"+date2+"@gmail.com");
        System.out.println("taken friend email address");
driver2.findElement(By.xpath("//input[@id='YourEmailAddress']")).sendKeys("sandip.patel" +date2 + "@gmail.com");
//textarea[@id='PersonalMessage']
driver2.findElement(By.xpath("//textarea[@id='PersonalMessage']")).click();
driver2.findElement(By.xpath("//textarea[@id='PersonalMessage']")).sendKeys("dnd");
driver2.findElement(By.xpath("//input[@name='send-email']")).click();
//Only registered customers can use email a friend feature
String actualresultrefertofriend=driver2.findElement(By.xpath("//li[contains(text(),'Only registered customers can use email a friend feature')]")).getText();
String expectedresultsrefertofrined="Only registered customers can use email a friend feature";
Assert.assertEquals("The testcase scenario for refertofrend",expectedresultsrefertofrined,actualresultrefertofriend);



    }
}

