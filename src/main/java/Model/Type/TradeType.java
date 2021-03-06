package Model.Type;

import Mapper.TypeMapper;

public enum TradeType implements TypeMapper {
    /**
     * 매물 거래 유형
     */
    MONTHLY("월세"),
    JEONSE("전세"),
    SALE("매매"),
    UNKNOWN("Mismatch TradeType Type");

    private String name;

    TradeType(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getName() {
        return name;
    }

    public static TradeType create(String code) {
        for (TradeType type : values()) {
            if (type.name.equals(code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String toString() {
        return "TradeType{" +
                "name='" + name + '\'' +
                '}';
    }
}
