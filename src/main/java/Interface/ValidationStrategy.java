package Interface;

import org.jsoup.select.Elements;

public interface ValidationStrategy {
    boolean isValidPost(Elements elements);
}