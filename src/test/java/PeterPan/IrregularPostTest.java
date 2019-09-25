package PeterPan;

import Strategy.ParseStrategy;
import Service.Peterpan.parser.IrregularParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IrregularPostTest {
    private static final Logger logger = LoggerFactory.getLogger(IrregularPostTest.class);

    private ParseStrategy parser;
    private IrregularParser parser2;

    @Before
    public void setup() {
        parser = new IrregularParser();
        parser2 = new IrregularParser();
    }

    @Test
    public void objectTest() {
        logger.debug("parser {}", parser2.getter());
    }

}
