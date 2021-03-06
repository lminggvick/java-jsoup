package Utils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Todo)
 *  1. 현재 피터팬 카페에 관한 액션만 구현되어 있어서, 다형성을 갖도록 리팩토링 해야한다
 *  2. 이름이 갖는 의미가 에매하다
 */
public class CommonsUtils {

    private static final String APT_DIRECT_PROVINCES_URL = "https://cafe.naver.com/ArticleList.nhn?search.clubid=10322296&search.menuid=1115&search.boardtype=L";
    private static final Logger logger = LoggerFactory.getLogger(CommonsUtils.class);

    public static String getPostsUrlWithKeyword(String key, WebClient webClient) throws IOException {

        // Get Page
        HtmlPage currPage = webClient.getPage(APT_DIRECT_PROVINCES_URL);
        logger.debug("page ? {}", currPage);

        // Find Form with 'name' attribute
        HtmlForm form = currPage.getFormByName("frmBoardSearch");
        logger.debug("form ? {}", form);

        // Find 'input' element with 'name' attribute
        HtmlTextInput query = form.getInputByName("query");
        logger.debug("query ? {}", query);

        // Find 'button' element with XPath
        HtmlButton btn = (HtmlButton) form
                .getByXPath("//*[@id=\"info-search\"]/form/button").get(0);

        /**
         * Result URL : https://cafe.naver.com/ArticleSearchList.nhn?search.clubid=10322296&search.searchBy=0&search.query=%C1%F8%C1%D6
         * 1. Set attribute at input box
         * 2. Find button and click
         * 3. Get URL at Response page with keyword
         */
        query.setValueAttribute(key);
        currPage = btn.click();
        logger.debug("currPage ? {}", currPage.getUrl());

        return currPage.getUrl().toString().concat("&search.sortBy=date");
    }
}
