package Interface;

import com.gargoylesoftware.htmlunit.WebClient;

import java.util.Map;

public interface Login {
    boolean isLogin();
    Map<String, String> makeLoginCookie(WebClient webClient);
    boolean doLogin(WebClient webClient, String id, String pw) throws Exception;
}