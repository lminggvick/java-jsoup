package Service;

import Builder.PostBuilder;
import Interface.ParseStrategy;
import Interface.ValidationStrategy;
import Model.PeterPanProperty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Todo) 무엇을 파싱 할 것인지?
 */
public class PeterPanParser implements ParseStrategy {
    private static final Logger logger = LoggerFactory.getLogger(PeterPanParser.class);

    private final String prefix = "https://cafe.naver.com";
    private final String postfix = "&search.sortBy=date";
    private ValidationStrategy validator;
    private List<PeterPanProperty> properties;
    private Elements elements;
    private Document document;
    private String url;
    private String date;
    private String title;
    private String pageUrl;

    public PeterPanParser() {
        this.validator = new PeterPanValidator();
    }

    @Override
    public List<PeterPanProperty> parse(Elements elements, Map<String, String> cookies) throws IOException {
        properties = new ArrayList<>();

        for(Element post : elements) {
            url = prefix.concat(post.select("a").attr("href"));

            document = Jsoup.connect(url)
                    .cookies(cookies)
                    .get();

            if (validator.isInvalidPost(document.select(".tit-box div table tbody tr td a"))) {
                continue;
            }

            if (!validator.isRegularPost(document.select("#tbody"))) {
                continue;
            }

            date = document.select(".date").text();
            title = post.select(".board-list .article").text();

            document.select("table tbody");

            properties.add(new PostBuilder(title, url, date)
                    .address(document.select("#pp_location").text())
                    .price(document.select("#pp_fee").text())
                    .managementPrice(document.select("#pp_maintenance").text())
                    .phone(document.select("#pp_contact").text())
                    .build()
            );
        }

        return properties;
    }

    @Override
    public Elements initPosts(Document document, int maxPage) throws IOException {
        this.elements = document.select(".board-list .article");
        this.pageUrl = prefix.concat(document.select(".prev-next .on").attr("href"));

        for (int n = 2; n < maxPage + 1; n++) {
            elements.addAll(
                    Jsoup.connect(convertPageToNext(pageUrl, n)).get().select(".board-list .article")
            );
        }

        return elements;
    }

    @Override
    public String convertPageToNext(String url, int next) {
        String str = "";

        str = url.substring(0, url.length() - 1);
        str = str.concat(Integer.toString(next));

        return str.concat(postfix);
    }
}