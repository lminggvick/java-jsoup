package Strategy;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public interface InitStrategy {
    Elements initPosts(Document document, int maxPage) throws IOException;
    String convertPageToNext(String url, int next);
}
