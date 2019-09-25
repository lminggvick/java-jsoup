package Service.Peterpan.parser;

import Model.PeterPan.RegularProperty;
import Service.PeterPanService;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class IrregularParser extends PeterPanService {

    @Override
    public List<RegularProperty> parse(Elements elements, Map<String, String> cookies) throws IOException {
        return null;
    }
}
