package org.csanchez.selenium.example;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExampleTest {

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://localhost:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "10000"));

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities(SELENIUM_BROWSER, "", Platform.ANY);
        // Retry connecting
        WebDriverException ex = null;
        for (int i = 0; i < 10; i++) {
            try {
                this.driver = new RemoteWebDriver(new URL(SELENIUM_URL), capabilities);
                return;
            } catch (WebDriverException e) {
                ex = e;
                System.out.println(String.format("Error connecting to %s: %s. Retrying", SELENIUM_URL, e));
                Thread.sleep(1000);
            }
        }
        throw ex;
    }

    @Test
    public void test() throws Exception {
        // 与浏览器同步非常重要，必须等待浏览器加载完毕
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //打开目标地址
        //driver.get("https://www.baidu.com");
        
        //Thread.sleep(1000);
        //输入关键字搜索
        //driver.findElement(By.cssSelector("input#kw")).sendKeys("谢海军");
        //driver.findElement(By.cssSelector("input#su")).click();
        //Thread.sleep(1000);
        //driver.findElements(By.className("t")).forEach(x -> {
            //System.out.println(x.getText());
        //});
        
        //暂停5秒钟后关闭
        //Thread.sleep(2000);
        
        // 抓取网页新闻
        driver.get("https://www.163.com");
        try{
                List<String> list_rul_hp = new ArrayList<>();
                //获取网易新闻首页‘要闻’内容-url
                //使用浏览器开发者工具 查看“要闻”的布局   找到对应li的上级div的classname
                //使用driver的findElement方法 一步一步找到新闻链接的li标签的内容
                List<WebElement> list = driver.findElement(By.className("bd"))
                        .findElements(By.tagName("li"));
                //遍历 所有抓到的链接
                for(WebElement e: list){
                    //获取a标签 并使用getAttribute得到对应的url
                    String href = e.findElement(By.tagName("a")).getAttribute("href");
                    //这里可以过滤你希望抓的网址类型，这里方便解析只抓取了news、war、jiankang三个类别的新闻
                    if(href.contains("news.163.com") 
                            || href.contains("war.163.com")
                            || href.contains("jiankang.163.com")){
                        list_rul_hp.add(href);
                        System.out.println("---163 -home page- url:"+href);
                    }
                }
                //休眠（最好使用随机函数产生随机的休眠时间，来模拟浏览器被浏览的错觉）
                Thread.sleep(2000);
            
               
                //driver.quit();
                System.out.println("---163 -home page- list.size:"+list.size());
                System.out.println("---163 -home page- end---");
            }catch (Exception e) {
                //driver.quit();
                System.out.println("---163 exception1 end---");
            }
        // And now use this to visit Google
        //driver.get("http://www.baidu.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        //Thread.sleep(SLEEP);

        // Find the text input element by its name
        //WebElement element = driver.findElement(By.name("wd"));

        // Enter something to search for
        //element.sendKeys("谢海军");

        //Thread.sleep(SLEEP);

        // Now submit the form. WebDriver will find the form for us from the element
        //element.submit();

        // Check the title of the page
        // System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        //(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            //public Boolean apply(WebDriver d) {
                //return d.getTitle().toLowerCase().startsWith("谢海军");
            //}
        //});

        //Thread.sleep(SLEEP);

        // Should see: "cheese! - Google Search"
        //System.out.println("百度的Page title is: " + driver.getTitle());
        //System.out.println("搜索百度的后的页面源码: " + driver.getPageSource());
        //System.out.println("页面内容: " + driver.execute_script("return document.documentElement.outerHTML"));
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }
    
    /**操作 上传控件upload 参考https://www.cnblogs.com/puresoul/p/4286910.html和https://www.cnblogs.com/wuyn/p/10057236.html
    * https://blog.csdn.net/qq_22003641/article/details/79137327
    * 1.一般是把路他径直接sendKeys到这个输入框中
    * 2.如果输入框被加了readonly属性，不能输入，则需要用JS来去掉readonly属性！
    */
    public void testUpload(String locator,String path){
        //WebElement load = this.locateElementByXpath(locator);
        //load.sendKeys(path);
    }
}
