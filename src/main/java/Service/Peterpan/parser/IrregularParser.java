package Service.Peterpan.parser;

import Model.PeterPan.IrregularProperty;
import Strategy.ParseStrategy;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IrregularParser implements ParseStrategy<IrregularProperty> {
    private static final Logger logger = LoggerFactory.getLogger(IrregularParser.class);

    @Override
    public IrregularProperty parse(Document document, String url, String title) throws IOException {
        logger.debug("IrregularParser.parse initialize");
        return new IrregularProperty(title, document.select("#tbody").text(), document.select(".date").text(), url);
    }
}
