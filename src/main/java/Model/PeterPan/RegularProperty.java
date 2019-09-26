package Model.PeterPan;

import Mapper.ModelMapper;
import Model.Type.TradeType;
import lombok.*;

import Builder.RegularPostBuilder;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class RegularProperty implements ModelMapper {
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

    public RegularProperty(RegularPostBuilder builder) {
        this.title = builder.getTitle();
        this.url = builder.getUrl();
        this.date = builder.getDate();
        this.phone = builder.getPhone();
        this.address = builder.getAddress();
        this.price = builder.getPrice();
        this.managementPrice = builder.getManagementPrice();
        this.propertyType = builder.getPropertyType();
        this.tradeType = builder.getTradeType();
        this.roomCount = builder.getRoomCount();
        this.floor = builder.getFloor();
        this.managementCategory = builder.getManagementCategory();
        this.heatingType = builder.getHeatingType();
        this.option = builder.getOption();
        this.description = builder.getDescription();
        this.movePossibleDate = builder.getMovePossibleDate();
    }

    @Override
    public String toString() {
        return "\nRegularProperty {" +
                "\n\t title='" + title + '\'' +
                ",\n\t url='" + url + '\'' +
                ",\n\t date=" + date +
                ",\n\t phone='" + phone + '\'' +
                ",\n\t address='" + address + '\'' +
                ",\n\t price='" + price + '\'' +
                ",\n\t managementPrice='" + managementPrice + '\'' +
                ",\n\t propertyType='" + propertyType + '\'' +
                ",\n\t roomCount='" + roomCount + '\'' +
                ",\n\t floor='" + floor + '\'' +
                ",\n\t managementCategory='" + managementCategory + '\'' +
                ",\n\t heatingType='" + heatingType + '\'' +
                ",\n\t option='" + option + '\'' +
                ",\n\t description='" + description + '\'' +
                ",\n\t movePossibleDate='" + movePossibleDate + '\'' +
//                ",\n\t tradeType=" + tradeType.getName() +
                "\n}";
    }
}
