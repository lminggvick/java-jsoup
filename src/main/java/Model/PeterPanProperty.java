package Model;

import lombok.*;

import Builder.PostBuilder;

/**
 * Todo)
 *  1. 정형 게시글 / 비정형 게시글 객체를 나눌 필요가 있다
 *  2. 위 Type 에 따라 파싱 전략이 달라야 한다
 *  3. 정형 게시글이나 빈 값을 입력한 객체는 어떻게 처리할 것인지 고려해야한다
 *  4. 거래 타입이 명시안되어 있는 경우, injectType 메소드로 처리하도록 해야한다
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeterPanProperty {
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

    public PeterPanProperty(PostBuilder builder) {
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
        return "\nPost {" +
                "\n\t title='" + title + '\'' +
                ",\n\t url='" + url + '\'' +
                ",\n\t date=" + date +
                ",\n\t phone='" + phone + '\'' +
                ",\n\t address='" + address + '\'' +
                ",\n\t price='" + price + '\'' +
                ",\n\t managementPrice='" + managementPrice + '\'' +
                ",\n\t propertyType=" + propertyType + '\'' +
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
