package Model;

import lombok.*;

import Builder.PostBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeterPanPost {
    private String title;
    private String url;
    private String date;
    private String phone;
    private String address;
    private String price;
    private String managementPrice;
    private PropertyType propertyType;
    private TradeType tradeType;

    public PeterPanPost(PostBuilder builder) {
        this.title = builder.getTitle();
        this.url = builder.getUrl();
        this.date = builder.getDate();
        this.phone = builder.getPhone();
        this.address = builder.getAddress();
        this.price = builder.getPrice();
        this.managementPrice = builder.getManagementPrice();
        this.propertyType = builder.getPropertyType();
        this.tradeType = builder.getTradeType();
    }

    @Override
    public String toString() {
        return "\nPeterPanPost {" +
                "\n\t title='" + title + '\'' +
                ",\n\t url='" + url + '\'' +
                ",\n\t date=" + date +
                ",\n\t phone='" + phone + '\'' +
                ",\n\t address='" + address + '\'' +
                ",\n\t price='" + price + '\'' +
                ",\n\t managementPrice='" + managementPrice + '\'' +
                ",\n\t propertyType=" + propertyType +
                ",\n\t tradeType=" + tradeType +
                "\n}";
    }
}
