import Strategy.Login;
import Service.NaverLoginService;
import Utils.CommonsUtils;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.logging.Level;

/**
 * Todo)
 *  1. 모든 루프가 끝나면 csv 파일로 저장해야한다
 *
 * Todo)
 *  1. WebClient 의 Main 에서의 역할이 에매하다
 *  2. 이 때문에, 파라미터로 넣어줘야 하는 경우가 빈번함
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private String pageUrl;
    private WebClient webClient;
    private Login loginService;
    private Map<String, String> cookies;

    private String id;
    private String pw;
    private Elements elements;

    public Main(String id, String pw) throws Exception {
        this.id = id;
        this.pw = pw;
        this.loginService = new NaverLoginService();
        this.webClient = new WebClient();

        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        getElements();

        webClient.close();
    }

    public static void main(String[] args) throws Exception {
        new Main("uioo9034", "tkfkd01");
    }

    public Elements getElements() throws Exception {
        // "진주"로 검색한 전체 게시글 목록을 가져온다.
        String url = CommonsUtils.getPostsUrlWithKeyword("진주", webClient);
        Document doc = getDocumentAfterLogin(url);

        // OUT
        if (doc != null) {
            elements = doc.select(".inner_list");
            for (Element el : elements) {
                System.out.println("\n\t[title] : " + el.select(".article").text());
                System.out.println("\t[url] : " + el.select(".article").attr("href"));
            }
        }

        // PAGE_URL GET
        pageUrl = doc.select(".prev-next a").attr("href");
        logger.debug("TEST : {}", pageUrl);

        return elements;
    }

    private Document getDocumentAfterLogin(String url) throws Exception {
        Document doc = null;

        if (!loginService.isLogin()) {
            boolean flag = loginService.doLogin(webClient, id, pw);
            if (flag) {
                cookies = loginService.makeLoginCookie(webClient);
            }
        }

        if (cookies != null) {
            logger.debug("Cookie Getting Success ...\n");
            doc = Jsoup.connect(url).cookies(cookies).get();
        } else throw new Exception("쿠키 값이 존재하지 않습니다.");

        return doc;
    }
}