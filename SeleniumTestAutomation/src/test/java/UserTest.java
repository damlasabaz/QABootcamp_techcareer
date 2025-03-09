import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {

    @Test
    public void loginSuccesful () throws InterruptedException {
        //WebDriver driver = new ChromeDriver();
        //driver.get("https://www.trendyol.com/"); // get ilgili tarayıcıya link atamasını sağlar.

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.trendyol.com/giris");
        // login buttona çerezlere müdale etmeden tıklaması için ekranı büyüttük.
        driver.manage().window().fullscreen(); //driver.manage().window().maximize();
        driver.findElement(By.id("login-email")).sendKeys("testingediyorum@gmail.com");
        driver.findElement(By.name("login-password")).sendKeys("Testdamla123.");
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        Thread.sleep(5000); // testler beklemeden devam ettiği için, verilerin yüklenmesi için süre verdik Giriş yap > Hesabıma geçti böylelikle.
        String text=driver.findElements(By.cssSelector("[class='link-text']")).get(0).getText();
        System.out.println(text);
        Assert.assertEquals(text,"Hesabım");

    }

    @Test
    public void loginWithEmptyFields() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.trendyol.com/giris");
        driver.manage().window().fullscreen();
        driver.findElement(By.id("login-email")).sendKeys("testingediyorum@gmail.com");
        driver.findElement(By.name("login-password")).sendKeys("Testdamla123.");
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        Thread.sleep(3000);
        String errorText = driver.findElement(By.cssSelector(".message")).getText();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "Lütfen e-posta adresinizi ve şifrenizi girin.");
        driver.quit();
    }

    @Test
    public void searchProduct() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.trendyol.com/");
        driver.manage().window().fullscreen();
        driver.findElement(By.cssSelector("input[placeholder='Aradığınız ürün, kategori veya markayı yazınız']")).sendKeys("Laptop");
        driver.findElement(By.cssSelector(".search-icon")).click();
        Thread.sleep(5000);
        String resultText = driver.findElement(By.cssSelector(".dscrptn")).getText();
        System.out.println(resultText);
        Assert.assertTrue(resultText.contains("Laptop"));
        driver.quit();
    }
}
