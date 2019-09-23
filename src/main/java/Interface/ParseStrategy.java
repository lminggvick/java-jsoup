package Interface;

import Model.PeterPanPost;

import java.util.Map;

public interface ParseStrategy {
    PeterPanPost parse(String url, Map<String, String> cookie);
}
