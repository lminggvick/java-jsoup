package Model;

import Mapper.TypeMapper;

public enum InvalidKeyWord implements TypeMapper {
    KEY1("구해요"),
    KEY2("머무를"),
    KEY3("머물"),
    KEY4("머무를");

    private String name;

    InvalidKeyWord(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "InvalidKeyWord{" +
                "name='" + name + '\'' +
                '}';
    }
}
