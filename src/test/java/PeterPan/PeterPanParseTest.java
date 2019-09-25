package PeterPan;

import Builder.RegularPostBuilder;
import Mapper.ModelMapper;
import Mapper.TypeMapper;
import Model.PeterPan.IrregularProperty;
import Model.PeterPan.RegularProperty;
import Model.Type.TradeType;
import Service.NaverLoginService;
import Service.PeterPanService;
import Service.PeterPanValidator;
import Service.Peterpan.parser.IrregularParser;
import Service.Peterpan.parser.RegularParser;
import Strategy.ParseStrategy;
import Strategy.ValidationStrategy;
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

public class PeterPanParseTest {

    private static final Logger logger = LoggerFactory.getLogger(PeterPanParseTest.class);
    private static final String REGULAR_POST = "https://cafe.naver.com/ArticleRead.nhn?clubid=10322296&page=1&inCafeSearch=true&searchBy=1&query=%C1%F8%C1%D6&includeAll=&exclude=&include=&exact=&searchdate=all&media=0&sortBy=date&articleid=12953032&referrerAllArticles=true";
    private static final String IRREGULAR_POST = "https://cafe.naver.com/ArticleRead.nhn?clubid=10322296&page=2&inCafeSearch=true&searchBy=1&query=%C1%F8%C1%D6&includeAll=&exclude=&include=&exact=&searchdate=all&media=0&sortBy=date&articleid=12885000&referrerAllArticles=true";

    private static Map<String, String> cookies;
    private static WebClient webClient;

    private static ValidationStrategy validator;

    private static PeterPanService pService;

    private static NaverLoginService service;

    @BeforeClass
    public static void setup() throws Exception {
        service = new NaverLoginService();
        cookies = new HashMap<>();
        webClient = new WebClient();
        validator = new PeterPanValidator();
        pService = new PeterPanService();

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
        Elements elements = Jsoup.connect(REGULAR_POST)
                .cookies(cookies)
                .get()
                .select(".tit-box tbody tr a");

        logger.debug(elements.toString());

        assertFalse(validator.isInvalidPost(elements));
    }

    @Test
    public void injectType() throws IOException {
        RegularProperty post = new RegularProperty();

        Elements elements = Jsoup.connect(REGULAR_POST)
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
        Elements elements = Jsoup.connect(REGULAR_POST)
                .cookies(cookies)
                .get()
                .select("#tbody");

        assertTrue(validator.isRegularPost(elements));
    }

    @Test
    public void parsePost() throws IOException {
        RegularProperty post;

        Elements elements = Jsoup.connect(REGULAR_POST)
                .cookies(cookies)
                .get()
                .select("#tbody table tbody");

        post = new RegularPostBuilder("TITLE", REGULAR_POST, "DATE")
                .address(elements.select("#pp_location").text())
                .price(elements.select("#pp_fee").text())
                .managementPrice(elements.select("#pp_maintenance").text())
                .phone(elements.select("#pp_contact").text())
                .propertyType(elements.select("#pp_building_type").text())
                .roomCount(elements.select("#pp_room_count").text())
                .floor(elements.select("#pp_floor").text())
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
        Elements elements = pService.initPosts(doc, 3);

        for(Element el : elements) {
            logger.debug(el.toString());
        }

        assertThat(elements.size(), is(45));
    }

    @Test
    public void parsePosts() throws IOException {
        String MOCK_URL = "https://cafe.naver.com/ArticleSearchList.nhn?search.clubid=10322296&search.searchBy=0&search.query=%C1%F8%C1%D6";
        Document doc = Jsoup.connect(MOCK_URL).get();
        Elements elements = pService.initPosts(doc, 3);

        logger.debug("el ? {}", elements);
        logger.debug("거르고 거른 매물 객체! : {}", pService.parseAll(elements, cookies));
    }

    @Test
    public void parseIrregularPost() throws IOException {
        IrregularProperty post;

        Elements elements = Jsoup.connect(IRREGULAR_POST)
                .cookies(cookies)
                .get()
                .select("#tbody");

        post = new IrregularProperty("TITLE", elements.select("#tbody").text(), "DATE", "URL");

        logger.debug("Post? {}", post);
    }
}