package Service.Peterpan.parser;

import Model.PeterPan.IrregularProperty;
import Service.PeterPanService;
import Strategy.ValidationStrategy;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IrregularParser extends PeterPanService<IrregularProperty> {
    private static final Logger logger = LoggerFactory.getLogger(IrregularParser.class);

    private List<IrregularProperty> properties;

    public IrregularParser() {
        super();
    }

    @Override
    public List<IrregularProperty> parse(Elements elements, Map<String, String> cookies) throws IOException {
        properties = new ArrayList<>();


        return properties;
    }

    public ValidationStrategy getter() {
        return validator;
    }
}
