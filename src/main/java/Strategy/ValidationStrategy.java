package Strategy;

import org.jsoup.select.Elements;

public interface ValidationStrategy {
    boolean postValidate(Elements elements);
    boolean isRegularPost(Elements elements);
}