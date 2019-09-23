package Model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PeterPanPost {
    private String url;
    private String title;
    private LocalDateTime date;
    private String phone;
    private String address;
    private int deposit;
    private int monthlyPrice;
    private PropertyType propertyType;
    private TradeType tradeType;
}
