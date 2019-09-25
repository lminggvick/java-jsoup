package Factory;

import Mapper.ModelMapper;
import Model.PeterPan.IrregularProperty;
import Model.PeterPan.RegularProperty;
import Service.Peterpan.parser.IrregularParser;
import Service.Peterpan.parser.RegularParser;
import Strategy.ParseStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceFactory {
    private static final Logger logger = LoggerFactory.getLogger(ServiceFactory.class);

    private ParseStrategy<RegularProperty> regularParser;
    private ParseStrategy<IrregularProperty> irregularParser;

    public <P extends ModelMapper> ParseStrategy<P> getTypeServiceCreator(boolean isRegular) {
        if (isRegular) {
            regularParser = new RegularParser();
            return (ParseStrategy<P>) regularParser;
        }
        else {
            irregularParser = new IrregularParser();
            return (ParseStrategy<P>) irregularParser;
        }
    }
}
