package Model.Type;

import Mapper.TypeMapper;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PropertyType implements TypeMapper {

    /**
     * 매물 유형
     */
    APARTMENT("아파트"),
    ONE_ROOM("원룸"),
    TWO_ROOM("투룸"),
    THREE_ROOM("쓰리룸"),
    ONE_PLUS_ROOM("원플"),
    TOW_PLUS_ROOM("투플"),
    SHOP("상가"),
    FACTORY("공장"),
    OFFICE("오피스텔"),
    HOUSE("다세대 빌라"),
    HOUSE2("주택"),

    UNKNOWN("Mismatch Property Type");

    private String name;

    PropertyType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return name();
    }

    public static PropertyType create(String code) {
        for (PropertyType type : values()) {
            if (type.name.equals(code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String toString() {
        return "PropertyType{" +
                "name='" + name + '\'' +
                '}';
    }
}
