package Strategy;

import org.jsoup.select.Elements;

public interface ValidationStrategy {
    boolean isInvalidPost(Elements elements);
    boolean isRegularPost(Elements elements);
}