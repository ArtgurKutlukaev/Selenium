package ru.netology.webselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1() {
      driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иваныч-Ивановичев Иван");
      driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991309067");
      driver.findElement(By.cssSelector("[data-test-id='agreement'] ")).click();
      driver.findElement(By.cssSelector("button.button")).click();
      var actualElement=driver.findElement(By.cssSelector("[data-test-id='order-success'] "));
      var actualText=actualElement.getText().trim();
      assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",actualText);
    }


    }