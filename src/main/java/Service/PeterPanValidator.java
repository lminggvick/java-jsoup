package Service;

import Strategy.ValidationStrategy;
import Model.Type.RegularType;
import Model.ValidKeyword;
import org.apache.commons.lang3.ObjectUtils;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeterPanValidator implements ValidationStrategy {
    private static final Logger logger = LoggerFactory.getLogger(PeterPanValidator.class);

    /**
     * Todo) 소제목에 직거래 및 양식 틀 그대로 올려놓고 방 구하는 글도 긁히는 이슈 해결해야한다
     * @param elements
     * @return
     */
    @Override
    public boolean isInvalidPost(Elements elements) {
        parameterHandler(elements);

        for (ValidKeyword keyword : ValidKeyword.values()) {
            if (elements.text().equals(keyword.getName())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Todo) 매물주소/매물가격은 있는데 건물형태가 빈 경우가 있다 (매매)
     * @param elements
     * @return
     */
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