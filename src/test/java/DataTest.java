import Service.NaverLoginService;
import Service.PeterPanValidator;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static org.junit.Assert.assertTrue;

public class DataTest {

    private static final Logger logger = LoggerFactory.getLogger(DataTest.class);
    private static final String MOCK_URL = "https://cafe.naver.com/ArticleRead.nhn?clubid=10322296&page=1&inCafeSearch=true&searchBy=1&query=%C1%F8%C1%D6&includeAll=&exclude=&include=&exact=&searchdate=all&media=0&sortBy=date&articleid=12953032&referrerAllArticles=true";

    private Map<String, String> cookies;
    private WebClient webClient;

    private PeterPanValidator validator;
    private NaverLoginService service;

    @Before
    public void setup() throws Exception {
        this.service = new NaverLoginService();
        this.cookies = new HashMap<>();
        this.webClient = new WebClient();
        this.validator = new PeterPanValidator();

        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        service.doLogin(webClient, LoginTest.id, LoginTest.pw);
        this.cookies = service.makeLoginCookie(webClient);
    }

    @After
    public void after() {
        webClient.close();
    }

    @Test
    public void postValidator() throws IOException {
        Elements elements = Jsoup.connect(MOCK_URL)
                .cookies(cookies)
                .get()
                .select(".tit-box tbody tr a");

        assertTrue(validator.isValidPost(elements));
    }

    @Test
    public void crawling() {

    }
}