package Strategy;

import Mapper.ModelMapper;
import org.jsoup.nodes.Document;

import java.io.IOException;

public interface ParseStrategy<P extends ModelMapper> {
   <P> P parse(Document document, String url, String title) throws IOException;
}
