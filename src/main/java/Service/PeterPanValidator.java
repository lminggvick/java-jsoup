package Service;

import Interface.ValidationStrategy;
import Model.ValidKeyword;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeterPanValidator implements ValidationStrategy {
    private static final Logger logger = LoggerFactory.getLogger(PeterPanValidator.class);

    @Override
    public boolean isValidPost(Elements elements) {
        if (elements == null) {
            throw new NullPointerException("[Parameter is Null : elements]");
        }

        for (ValidKeyword keyword : ValidKeyword.values()) {
            if (elements.text().equals(keyword.getName())) {
                return true;
            }
        }

        return false;
    }
}