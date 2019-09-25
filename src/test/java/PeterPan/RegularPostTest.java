package PeterPan;

import Builder.RegularPostBuilder;
import Interface.ParseStrategy;
import Interface.ValidationStrategy;
import Model.PeterPan.RegularProperty;
import Model.Type.TradeType;
import Service.NaverLoginService;
import Service.Peterpan.parser.RegularParser;
import Service.PeterPanValidator;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegularPostTest {

    private static final Logger logger = LoggerFactory.getLogger(RegularPostTest.class);
    private static final String MOCK_URL = "https://cafe.naver.com/ArticleRead.nhn?clubid=10322296&page=1&inCafeSearch=true&searchBy=1&query=%C1%F8%C1%D6&includeAll=&exclude=&include=&exact=&searchdate=all&media=0&sortBy=date&articleid=12953032&referrerAllArticles=true";
    private static final String MOCK_POSTS_URL = "https://cafe.naver.com/ArticleSearchList.nhn?search.clubid=10322296&search.searchBy=0&search.query=%C1%F8%C1%D6";

    private static Map<String, String> cookies;
    private static WebClient webClient;

    private static ValidationStrategy validator;
    private static ParseStrategy parser;

    private static NaverLoginService service;

    @BeforeClass
    public static void setup() throws Exception {
        service = new NaverLoginService();
        cookies = new HashMap<>();
        webClient = new WebClient();
        validator = new PeterPanValidator();
        parser = new RegularParser();

        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        service.doLogin(webClient, LoginTest.id, LoginTest.pw);
        cookies = service.makeLoginCookie(webClient);
    }

    @After
    public void after() {
        cookies.clear();
    }

    @Test
    public void postValidator() throws IOException {
        Elements elements = Jsoup.connect(MOCK_URL)
                .cookies(cookies)
                .get()
                .select(".tit-box tbody tr a");

        logger.debug(elements.toString());

        assertFalse(validator.isInvalidPost(elements));
    }

    @Test
    public void injectType() throws IOException {
        RegularProperty post = new RegularProperty();

        Elements elements = Jsoup.connect(MOCK_URL)
                .cookies(cookies)
                .get()
                .select("#tbody");

        for (TradeType tType : TradeType.values()) {
            if (elements.html().contains(tType.getName())) {
                post.setTradeType(tType);
            }
        }

//        for (PropertyType pType : PropertyType.values()) {
//            if (elements.html().contains(pType.getName())) {
//                post.setPropertyType(pType);
//            }
//        }

        logger.debug("Post? {}", post);
    }

    @Test
    public void checkPostType() throws IOException {
        Elements elements = Jsoup.connect(MOCK_URL)
                .cookies(cookies)
                .get()
                .select("#tbody");

        assertTrue(validator.isRegularPost(elements));
    }

    @Test
    public void parsePost() throws IOException {
        RegularProperty post;

        Elements elements = Jsoup.connect(MOCK_URL)
                .cookies(cookies)
                .get()
                .select("#tbody table tbody");

        post = new RegularPostBuilder("TITLE", MOCK_URL, "DATE")
                .address(elements.select("#pp_location").text())
                .price(elements.select("#pp_fee").text())
                .managementPrice(elements.select("#pp_maintenance").text())
                .phone(elements.select("#pp_contact").text())
                .propertyType(elements.select("#pp_building_type").text())
                .roomCount(elements.select("#pp_room_count").text())
                .floor("[해당층/전체층] " + elements.select("#pp_floor").text())
                .managementCategory(elements.select("#pp_maintenance_include").text())
                .movePossibleDate(elements.select("#pp_moving_date").text())
                .option(elements.select("#pp_options").text())
                .heatingType(elements.select("#pp_heating").text())
                .description(elements.select("#pp_description").text())
                .build();

        logger.debug("Parsed post : {}", post);
    }

    @Test
    public void initPosts() throws IOException {
        String MOCK_URL = "https://cafe.naver.com/ArticleSearchList.nhn?search.clubid=10322296&search.searchBy=0&search.query=%C1%F8%C1%D6";
        Document doc = Jsoup.connect(MOCK_URL).get();
        Elements elements = parser.initPosts(doc, 3);

        for(Element el : elements) {
            logger.debug(el.toString());
        }

        assertThat(elements.size(), is(45));
    }

    @Test
    public void parsePosts() throws IOException {
        String MOCK_URL = "https://cafe.naver.com/ArticleSearchList.nhn?search.clubid=10322296&search.searchBy=0&search.query=%C1%F8%C1%D6";
        Document doc = Jsoup.connect(MOCK_URL).get();
        Elements elements = parser.initPosts(doc, 3);

        logger.debug("el ? {}", elements);
        logger.debug("거르고 거른 매물 객체! : {}", parser.parse(elements, cookies));
    }

    @Test
    public void sub() {
        String str = "";
        String str2 = "   ";

        assertTrue(str.isEmpty());
        assertTrue(str.isBlank());
        assertTrue(str2.trim().isEmpty());
    }
}