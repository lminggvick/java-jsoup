package Service.Peterpan.parser;

import Builder.RegularPostBuilder;
import Interface.ValidationStrategy;
import Model.PeterPan.RegularProperty;
import Service.PeterPanService;
import Service.PeterPanValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Todo) 무엇을 파싱 할 것인지?
 */
public class RegularParser extends PeterPanService {
    private static final Logger logger = LoggerFactory.getLogger(RegularParser.class);

    private ValidationStrategy validator;
    private List<RegularProperty> properties;

    private Document document;
    private String url;
    private String date;
    private String title;

    public RegularParser() {
        super();
        this.validator = new PeterPanValidator();
    }

    @Override
    public List<RegularProperty> parse(Elements elements, Map<String, String> cookies) throws IOException {
        properties = new ArrayList<>();

        for(Element post : elements) {
            url = super.prefix.concat(post.select("a").attr("href"));

            document = Jsoup.connect(url)
                    .cookies(cookies)
                    .get();

            if (validator.isInvalidPost(document.select(".tit-box div table tbody tr td a"))) {
                continue;
            }

            if (!validator.isRegularPost(document.select("#tbody"))) {
                continue;
            }

            date = document.select(".date").text();
            title = post.select("a").text();

            document.select("table tbody");

            properties.add(new RegularPostBuilder(title, url, date)
                    .address(document.select("#pp_location").text())
                    .price(document.select("#pp_fee").text())
                    .managementPrice(document.select("#pp_maintenance").text())
                    .phone(document.select("#pp_contact").text())
                    .propertyType(document.select("#pp_building_type").text())
                    .roomCount(document.select("#pp_room_count").text())
                    .floor(document.select("#pp_floor").text())
                    .managementCategory(document.select("#pp_maintenance_include").text())
                    .movePossibleDate(document.select("#pp_moving_date").text())
                    .option(document.select("#pp_options").text())
                    .heatingType(document.select("#pp_heating").text())
                    .description(document.select("#pp_description").text())
                    .build()
            );
        }

        return properties;
    }
}