package Builder;

import Interface.Buildable;
import Model.PeterPanProperty;
import Model.TradeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBuilder implements Buildable {
    private static final String NON_PRICE = "없음";

    private String title;
    private String url;
    private String date;
    private String phone;
    private String address;
    private String price;
    private String managementPrice;
    private String propertyType;
    private String roomCount;
    private String floor;
    private String managementCategory;
    private String heatingType;
    private String option;
    private String description;
    private String movePossibleDate;
    private TradeType tradeType;

    public PostBuilder(String title, String url, String date) {
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public PostBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public PostBuilder address(String address) {
        this.address = address;
        return this;
    }

    public PostBuilder price(String price) {
        this.price = price;
        return this;
    }

    public PostBuilder propertyType(String propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public PostBuilder tradeType(String tradeTypeCode) {
        this.tradeType = TradeType.create(tradeTypeCode);
        return this;
    }

    public PostBuilder managementPrice(String price) {
        if(price.trim().isEmpty()) {
            this.managementPrice = NON_PRICE;
            return this;
        }
        this.managementPrice = price;
        return this;
    }

    public PostBuilder roomCount(String count) {
        this.roomCount = count;
        return this;
    }

    public PostBuilder floor(String floor) {
        this.floor = floor;
        return this;
    }

    public PostBuilder managementCategory(String category) {
        this.managementCategory = category;
        return this;
    }

    public PostBuilder heatingType(String type) {
        this.heatingType = type;
        return this;
    }

    public PostBuilder option(String option) {
        this.option = option;
        return this;
    }

    public PostBuilder description(String description) {
        this.description = description;
        return this;
    }

    public PostBuilder movePossibleDate(String moveDate) {
        this.movePossibleDate = moveDate;
        return this;
    }

    @Override
    public PeterPanProperty build() {
        return new PeterPanProperty(this);
    }
}
