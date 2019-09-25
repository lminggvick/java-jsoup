package Service;

import Mapper.ModelMapper;
import Strategy.ParseStrategy;
import Strategy.ValidationStrategy;
import lombok.Getter;
import org.apache.xpath.operations.Mod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Getter
public abstract class PeterPanService<P extends ModelMapper> implements ParseStrategy {
    public final String postfix = "&search.sortBy=date";
    public final String prefix = "https://cafe.naver.com";
    public ValidationStrategy validator;

    private Elements elements;
    private String pageUrl;

    public PeterPanService() {
        this.validator = new PeterPanValidator();
    }

    public abstract List<P> parse(Elements elements, Map<String, String> cookies) throws IOException;

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
