package Strategy;

import Mapper.ModelMapper;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ParseStrategy extends ModelMapper {
    Elements initPosts(Document document, int maxPage) throws IOException;
    String convertPageToNext(String url, int next);
}
