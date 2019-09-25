package PeterPan;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    protected static final String id = "uioo9034";
    protected static final String pw = "tkfkd01";
    protected static final String LOGIN_URL = "https://nid.naver.com/nidlogin.login";

    private WebClient webClient;
    private HtmlPage htmlPage;
    protected Map<String, String> cookies;

    @Before
    public void setup() {
        this.webClient = new WebClient();
        this.cookies = new HashMap<>();

        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
    }

    @After
    public void after() {
        webClient.close();
    }

    @Test
    public void doLogin() throws IOException {
        htmlPage = webClient.getPage(LOGIN_URL);

        HtmlTextInput inputId = htmlPage.getFirstByXPath("//*[@id=\"id\"]");
        HtmlPasswordInput inputPassword = htmlPage.getFirstByXPath("//*[@id=\"pw\"]");
        HtmlSubmitInput inputSubmit = htmlPage.getFirstByXPath("//*[@id=\"frmNIDLogin\"]/fieldset/input");

        inputId.setText(id);
        inputPassword.setText(pw);
        inputSubmit.dblClick();

        assertTrue(htmlPage.asText().contains("Naver Sign in"));
    }

    @Test
    public void getCookie() throws IOException {
        doLogin();
        Set<Cookie> cookies = this.webClient.getCookieManager().getCookies();
        logger.debug("Cookie : {}", cookies);
        assertNotEquals(cookies.size(), 0);
    }
}