package Interface;

import Model.PeterPan.RegularProperty;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ParseStrategy {
    List<RegularProperty> parse(Elements elements, Map<String, String> cookies) throws IOException;
    Elements initPosts(Document document, int maxPage) throws IOException;
    String convertPageToNext(String url, int next);
}
