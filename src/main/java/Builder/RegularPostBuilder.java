package Builder;

import Model.PeterPan.RegularProperty;
import Model.Type.TradeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegularPostBuilder implements Buildable {
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

    public RegularPostBuilder(String title, String url, String date) {
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public RegularPostBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public RegularPostBuilder address(String address) {
        this.address = address;
        return this;
    }

    public RegularPostBuilder price(String price) {
        this.price = price;
        return this;
    }

    public RegularPostBuilder propertyType(String propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public RegularPostBuilder tradeType(String tradeTypeCode) {
        this.tradeType = TradeType.create(tradeTypeCode);
        return this;
    }

    public RegularPostBuilder managementPrice(String price) {
        if(price.trim().isEmpty()) {
            this.managementPrice = NON_PRICE;
            return this;
        }
        this.managementPrice = price;
        return this;
    }

    public RegularPostBuilder roomCount(String count) {
        this.roomCount = count;
        return this;
    }

    public RegularPostBuilder floor(String floor) {
        this.floor = floor;
        return this;
    }

    public RegularPostBuilder managementCategory(String category) {
        this.managementCategory = category;
        return this;
    }

    public RegularPostBuilder heatingType(String type) {
        this.heatingType = type;
        return this;
    }

    public RegularPostBuilder option(String option) {
        this.option = option;
        return this;
    }

    public RegularPostBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RegularPostBuilder movePossibleDate(String moveDate) {
        this.movePossibleDate = moveDate;
        return this;
    }

    @Override
    public RegularProperty build() {
        return new RegularProperty(this);
    }
}
