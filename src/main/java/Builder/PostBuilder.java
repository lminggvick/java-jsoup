package Builder;

import Interface.Buildable;
import Model.PeterPanPost;
import Model.PropertyType;
import Model.TradeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostBuilder implements Buildable {
    private String title;
    private String url;
    private LocalDateTime date;
    private String phone;
    private String address;
    private String price;
    private String managementPrice;
    private PropertyType propertyType;
    private TradeType tradeType;

    public PostBuilder(String title, String url, LocalDateTime date) {
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

    public PostBuilder propertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public PostBuilder tradeType(TradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public PostBuilder managementPrice(String price) {
        this.managementPrice = price;
        return this;
    }

    @Override
    public PeterPanPost build() {
        return new PeterPanPost(this);
    }
}
