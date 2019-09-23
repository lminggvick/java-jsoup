package Model;

import Interface.TypeMapper;

public enum ValidKeyword implements TypeMapper {
    KEY1("경상도지역 직거래"),
    KEY2("[직거래]아파트.전세.지방");

    private String name;

    ValidKeyword(String name) {
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

    @Override
    public String toString() {
        return "ValidKeyword{" +
                "name='" + name + '\'' +
                '}';
    }
}