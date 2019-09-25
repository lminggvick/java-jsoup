package Service;

import Strategy.Login;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NaverLoginService implements Login {

    private static final Logger logger = LoggerFactory.getLogger(NaverLoginService.class);

    private final String LOGIN_URL = "https://nid.naver.com/nidlogin.login";

    private Map<String, String> cookies;
    private HtmlPage page;

    boolean isLogin;

    @Override
    public boolean isLogin() {
        return isLogin;
    }

    public Map<String, String> makeLoginCookie(WebClient webClient) {
        cookies = new HashMap<>();
        CookieManager cookieManager = webClient.getCookieManager();

        Set<Cookie> cookieSet = cookieManager.getCookies();

        for (Cookie c : cookieSet) {
            cookies.put(c.getName(), c.getValue());
        }

        return cookies;
    }

    public boolean doLogin(WebClient webClient, String id, String pw) throws Exception {
        page = webClient.getPage(LOGIN_URL);

        HtmlTextInput inputId = page.getFirstByXPath("//*[@id=\"id\"]");
        HtmlPasswordInput inputPassword = page.getFirstByXPath("//*[@id=\"pw\"]");
        HtmlSubmitInput inputSubmit = page.getFirstByXPath("//*[@id=\"frmNIDLogin\"]/fieldset/input");

        inputId.setText(id);
        inputPassword.setText(pw);
        inputSubmit.dblClick();

        if (!page.asText().contains("Naver Sign in")) {
            isLogin = false;
            throw new Exception("cannot login with the id and pw");
        }

        else {
            isLogin = true;
            logger.debug("login success !!");
        }

        return isLogin;
    }
}
