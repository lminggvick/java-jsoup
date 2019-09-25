package Model;

import Interface.TypeMapper;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RegularType implements TypeMapper {

    /**
     * 게시글 작성 시 제공되는 규격에 입력한 게시글의 핵심 필드 id 값
     */
    pp_location("매물주소"),
    pp_fee("매물가격"),
    pp_building_type("건물형태");

    private String name;

    RegularType(String name) {
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
}