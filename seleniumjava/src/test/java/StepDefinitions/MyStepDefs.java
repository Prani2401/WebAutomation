package StepDefinitions;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.io.FileHandler;

import org.checkerframework.checker.units.qual.s;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions.*;

import com.google.common.base.Verify;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import java.io.File;

// import io.cucumber.java.en.*;

public class MyStepDefs {
    
    public WebDriver driver;
    public WebDriverWait wait;
    public Map<String,Integer> checkingPrice;
    public int totalprice=0;
    public Actions action;
    public JavascriptExecutor js;

    @Given("^setup webbrowser$")
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\prani\\Documents\\seleniumjava\\seleniumjava\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver) ;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        js= (JavascriptExecutor)driver;
        
    }

    @When("^Launching Browser$")
    public void launching(){
       driver.get("https://testautomationpractice.blogspot.com/");
        
    }

    @And("^Navigating to pages$")
    public void navigation() throws InterruptedException{
        boolean check = false;
        driver.findElement(By.linkText("Udemy Courses")).click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.findElement(By.partialLinkText("Udemy ")).click();
        Thread.sleep(2000);
        driver.navigate().back();
        String url=driver.getCurrentUrl();
        String title=driver.getTitle();
        Assert.assertEquals("https://testautomationpractice.blogspot.com/", url);
        Assert.assertEquals("Automation Testing Practice", title);
        

        if (url.equals("https://testautomationpractice.blogspot.com/") && title.equals("Automation Testing Practice")){
            System.out.println("passed");
            check=true;
        }
        Assert.assertTrue(check);

    }
    @And("^Using sendkeys to send text to input")
    public void sendkeys_to_input() throws InterruptedException{ 
        driver.findElement(By.className("form-control")).sendKeys("kanna");
        driver.findElement(By.id("email")).sendKeys("pannu@gmail.com");
        driver.findElement(By.className("form-control")).sendKeys("pannu@gmail.com");
        driver.findElement(By.cssSelector("#phone")).sendKeys("9014653332");
        driver.findElement(By.tagName("textarea")).sendKeys("Gadegudur");
        

    }

    @And("^testing checkbox and radio$")
    public void checkbox() throws InterruptedException{
        driver.findElement(By.xpath("//input[@value='female']")).click();

        //selecting single days
        driver.findElement(By.cssSelector("#monday")).click();

        Thread.sleep(2000);
        
        driver.findElement(By.cssSelector("#monday")).click();

        Thread.sleep(1000);

        List<WebElement> days= driver.findElements(By.xpath("//div[@class='form-group']/div[contains(@class,'form-check') and contains(@class,'form-check-inline')]/input[@type='checkbox']"));
        for (WebElement day:days){
            day.click();
        }
    }
    @And("^testing dropdown$")
    public void dropdown() throws InterruptedException{
        Select country=new Select(driver.findElement(By.id("country")));
        country.selectByValue("usa");
        Thread.sleep(1000);
        
        Select colors=new Select(driver.findElement(By.id("colors")));
        colors.selectByIndex(5);
        Thread.sleep(1000);
        
        Select animals=new Select(driver.findElement(By.id("animals")));
        animals.selectByVisibleText("Zebra");
        Thread.sleep(1000);   

    }
    @And("^checking dates")
    public void dates() throws InterruptedException{
        driver.findElement(By.id("datepicker")).click();
       
        //String month=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[1]")).getText();
        Boolean check=true;
        do{
            String year=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[2]")).getText();
            String month=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[1]")).getText();
            Thread.sleep(1000);
            if(year.equals("2024") && month.equals("November")){
                check=false;
                driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[5]/a")).click();
                
            }
            else{
                driver.findElement(By.cssSelector("#ui-datepicker-div > div > a.ui-datepicker-prev.ui-corner-all")).click();
                
            }
        }while(check);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"txtDate\"]")).click();
        Select year =new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]")));
        year.selectByValue("2023");
        Select month =new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
        month.selectByVisibleText("Oct");
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[5]/a")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='start-date']")).sendKeys("24/06/2002");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='end-date']")).sendKeys("24/06/2002");
        Thread.sleep(1000);
        
        driver.findElement(By.xpath("//*[@id=\'post-body-1307673142697428135\']/div[8]/button")).click();
        
        String result=driver.findElement(By.id("result")).getText();
        Assert.assertEquals("You selected a range of 0 days.",result);
    }
    @And("^uploading files$")
    public void filesUpload() throws InterruptedException{
        action.moveToElement(driver.findElement(By.cssSelector("#singleFileInput"))).perform();
        driver.findElement(By.cssSelector("#singleFileInput")).sendKeys("C:\\Users\\prani\\Downloads\\Pranitha_Resume.pdf");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#multipleFilesInput")).sendKeys("C:\\Users\\prani\\Downloads\\Pranitha_Resume.pdf\nC:\\Users\\prani\\Downloads\\Krishna_Resume.pdf");

    }

    @And("^testing tables$")
    public void tables() throws InterruptedException{
        Assert.assertEquals("Selenium", driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr[5]/td[3]")).getText());
        driver.findElement(By.xpath("//a[text()='1']")).click();
        driver.findElement(By.cssSelector("#productTable > tbody > tr:nth-child(2) > td:nth-child(4) > input[type=checkbox]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='3']")).click();
        //*[@id="productTable"]/tbody/tr[1]/td[4]/input
        for(int i=1;i<=5;i++){
            driver.findElement(By.xpath("//*[@id=\"productTable\"]/tbody/tr["+i+"]/td[4]/input")).click();
        }
        Thread.sleep(1000);

    }

    @And("^testing hidden elements$")
    public void hiddenelements() throws InterruptedException{
        driver.findElement(By.partialLinkText("Hidden Elements")).click();
        boolean result = driver.findElement(By.xpath("//div[@id='container']/input[@id='input2']")).isDisplayed();
        Thread.sleep(1000);
        if (!result){
            driver.findElement(By.xpath("//button[@id='toggleInput']")).click();
        }

        String h=driver.findElement(By.cssSelector("#checkbox2")).getAttribute("class");
        Thread.sleep(1000);
        if(h.equals("hidden")){
            driver.findElement(By.xpath("//button[@id='toggleCheckbox']")).click();
        }
    }
    @And("^testing search tabs$")
    public void tabs() throws InterruptedException{
        List<WebElement> check =driver.findElements(By.id("wikipedia-search-results"));
        if(check.isEmpty()){
            driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).clear();
            driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys("python");
            action.sendKeys(Keys.ENTER).perform();
            Thread.sleep(2000);
            List<WebElement> elements=driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']/a"));
            System.out.println(elements.isEmpty());
            for(WebElement element:elements){
                element.click();
                Thread.sleep(1000);
            }
        }     
    }
    @And("^testing dynamic button$")
    public void dynamics() throws InterruptedException{
        driver.findElement(By.xpath("//button[(@name='stop') or (@name='start')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[(@name='stop') or (@name='start')]")).click();
    }
    @And("^testing alerts$")
    public void alerts() throws InterruptedException{
        // driver.findElement(By.xpath("//button[@id='alertBtn']")).click();
        // Alert alert=driver.switchTo().alert();
        // Thread.sleep(1000);
        // alert.accept();
        // Thread.sleep(1000);
        // driver.findElement(By.xpath("//button[@id='confirmBtn']")).click();
        // Alert alert1=driver.switchTo().alert();
        // Thread.sleep(1000);
        // alert1.accept();
        // Thread.sleep(1000);
        // driver.findElement(By.xpath("//button[@id='confirmBtn']")).click();
        // Alert alert2=driver.switchTo().alert();
        // Thread.sleep(1000);
        // alert2.dismiss();
        driver.findElement(By.xpath("//button[@id='promptBtn']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert3=driver.switchTo().alert();
        // Thread.sleep(1000);
        // try{
        //     Thread.sleep(1000);
        //     System.out.println(alert.getText());
        // }
        // catch (Exception e){
        //     System.out.println(e);
        // }
        //Thread.sleep(1000);
        
        alert3.sendKeys("panuuuu");
        Thread.sleep(1000);
        alert3.accept();
        
    }
    @And("^window handles$")
    public void windowHandles() throws InterruptedException{
        String Mainwindow=driver.getWindowHandle();
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();
        Set<String> windowhandles=driver.getWindowHandles();
        Thread.sleep(2000);
        for(String handle:windowhandles){
                driver.switchTo().window(handle);
                if(handle.equals(Mainwindow)){
                    
                }
                else{
                    Thread.sleep(2000);
                    driver.close();
                }
                
        }
        driver.switchTo().window(Mainwindow);

    }
    @And("^testing popups$")
    public void popups() throws InterruptedException{
        
        String Mainwindow=driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@id='PopUp']")).click();
        ArrayList<String> windowhandles=new ArrayList<>(driver.getWindowHandles());
        Thread.sleep(2000);
        for(String handle:windowhandles){   
                driver.switchTo().window(handle);
                if(handle.equals(Mainwindow)){
                    
                }
                else{
                    Thread.sleep(2000);
                    driver.close();
                }
        }
        driver.switchTo().window(windowhandles.get(0));
    }

    @And("^testing mousehover$")
    public void pointer() throws InterruptedException {
        action.moveToElement(driver.findElement(By.cssSelector("#HTML3 > div.widget-content > div > button"))).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"HTML3\"]/div[1]/div/div/a[1]")).click();
    }
    @And("^Double Click$")
    public void double_Click() throws InterruptedException {
        action.doubleClick(driver.findElement(By.xpath("//*[@id='HTML10']/div[1]/button"))).perform();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.cssSelector("#field1")).getText(), driver.findElement(By.cssSelector("#field2")).getText());
    }
    
    @And("^DragAndDrop$")
    public void DragAndDrop() throws InterruptedException{
        action.dragAndDrop(driver.findElement(By.xpath("//*[@id='draggable']")), driver.findElement(By.xpath("//*[@id='droppable']"))).perform();
        Thread.sleep(500);
        Assert.assertEquals("Dropped!", driver.findElement(By.xpath("//*[@id='droppable']/p")).getText());
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        action.clickAndHold(driver.findElement(By.xpath("//*[@id='draggable']"))).moveToElement(driver.findElement(By.xpath("//*[@id='droppable']"))).release().perform();
        Thread.sleep(500);
        Assert.assertEquals("Dropped!", driver.findElement(By.xpath("//*[@id='droppable']/p")).getText());
    }
    @And("^testinig slidbar$")
    public void testinig_slidbar() throws InterruptedException{
        action.clickAndHold(driver.findElement(By.xpath("//*[@id='slider-range']/span[1]"))).moveToElement(driver.findElement(By.xpath("//*[@id='slider-range']/span[1]")),60,0).release().perform();
        Thread.sleep(500);
        // Assert.assertEquals("Dropped!", driver.findElement(By.xpath("//*[@id='droppable']/p")).getText());
    }

    @And("^testing scrolling dropdown$")
    public void scrolling_dropdown() throws InterruptedException{
        driver.findElement(By.cssSelector("#comboBox")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='dropdown']/div[10]")))).click();
    }

    @And ("^testing brokenlinks$")
    public void brokenlinks() throws InterruptedException, IOException{
        String bl=driver.findElement(By.xpath("//*[@id='broken-links']/a[2]")).getAttribute("href");
        String wl=driver.findElement(By.xpath("//*[@id='apple']")).getAttribute("href");
        URL url=new URL(bl);
        URLConnection connection=url.openConnection();
        
        HttpURLConnection https=(HttpURLConnection)connection;
        https.setConnectTimeout(5000);
        https.connect();
        Integer errorcode=https.getResponseCode();
        System.out.println(errorcode);
        Thread.sleep(1000);
        if(errorcode==200){
            driver.findElement(By.xpath("//*[@id='broken-links']/a[2]")).click();
        }
        url=new URL(wl);
        connection=url.openConnection();
        https=(HttpURLConnection)connection;
        https.setConnectTimeout(5000);
        https.connect();
        errorcode=https.getResponseCode();
        System.out.println(errorcode);
        Thread.sleep(1000);
        if(errorcode==200){
            driver.findElement(By.xpath("//*[@id='apple']")).click();
        }
    }
    @And("^testing autosuggestions$")
    
    public void autosuggestions() throws InterruptedException{
        driver.get("https://practice.expandtesting.com/autocomplete");
        driver.findElement(By.xpath("//input[@id='country']")).sendKeys("In");
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='countryautocomplete-list']/div")));
        List<WebElement> auto = driver.findElements(By.xpath("//div[@id='countryautocomplete-list']/div"));
            
        for (WebElement a:auto){
            if (a.findElement(By.tagName("input")).getAttribute("value").equalsIgnoreCase("Indonesia")){
                a.click();
                break;
            }
        }
        
    }

    @And("^taking ss$")
    public void ss() throws InterruptedException, IOException{
        
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dst = new File("C:\\Users\\prani\\Desktop\\pannu.png");
        FileHandler.copy(src,dst);
    }
    @And("^testing datatables$")
    public void datatab(DataTable data) throws InterruptedException{
        List<Map<String,String>> couple=data.asMaps();
        for(Map a:couple){
            System.out.println(a.get("Name"));
        }
    }

   @And("testing values by Examples {word} {word} {word} {int}")
public void examples(String name1, String name2, String relation, int years) {
    System.out.println(name1 + " and " + name2 + " are " + relation + " for " + years + " year(s)");
}
    @And("^testing file download$")
    public void fileDownload() throws Exception{
        driver.get("https://practice.expandtesting.com/download");
        WebElement download = driver.findElement(By.xpath("//a[@data-testid='1750176575059_BGV']"));
        js.executeScript("arguments[0].click();", download);
    }
    @And("^Dimension and Points$")
    public void DimensionAndPoint(){
        //Position and dimension of window
        Dimension d=new Dimension(300,400);
        driver.manage().window().setSize(d);
        Point p=new Point(500, 500);
        driver.manage().window().setPosition(p);
        //get location and size of elememt
        WebElement element=driver.findElement(By.xpath("//*[@id='Wikipedia1']/h2"));
        Dimension d2=element.getSize();
        Point p2 = element.getLocation();
        System.out.println("x: "+p2.getX()+" y: "+p2.getY());
        System.out.println("Height: "+d2.getHeight()+" Width: "+d2.getWidth());
        
    }

    @And("testing cookies")
    public void cookies() {
        Cookie c = new Cookie("name","krishna");
        driver.manage().addCookie(c);
        Cookie c1 = new Cookie("name","pannu");
        driver.manage().addCookie(c1);
        Set<Cookie> coo = driver.manage().getCookies();
        for (Cookie a:coo){
            System.out.println(a);
        }
        System.out.println(driver.manage().getCookieNamed("name"));
        driver.manage().deleteCookieNamed("name");
        Set<Cookie> coo1 = driver.manage().getCookies();
        for (Cookie a:coo1){
            System.out.println(a+"coo1");
        }



    }
}