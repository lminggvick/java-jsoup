package Service;

import Model.InvalidKeyWord;
import Strategy.ValidationStrategy;
import Model.Type.RegularType;
import Model.ValidKeyword;
import org.apache.commons.lang3.ObjectUtils;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeterPanValidator implements ValidationStrategy {
    private static final Logger logger = LoggerFactory.getLogger(PeterPanValidator.class);

    @Override
    public boolean postValidate(Elements elements) {
        parameterHandler(elements);

        for (InvalidKeyWord keyWord : InvalidKeyWord.values()) {
            if (elements.text().contains(keyWord.getName())) {
                return true;
            }
        }

        for (ValidKeyword keyword : ValidKeyword.values()) {
            if (elements.select(".tit-box div table tbody tr td a").text().equals(keyword.getName())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isRegularPost(Elements elements) {
        parameterHandler(elements);

        for (RegularType type : RegularType.values()) {
            String code = type.getCode();
            if (elements.select("#".concat(code)).text() == null) {
                return false;
            }

            if (elements.select("#".concat(code)).text().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private void parameterHandler(Object object) {
        if (object == null || ObjectUtils.isEmpty(object)) {
            throw new NullPointerException("Elements is null");
        }
    }
}