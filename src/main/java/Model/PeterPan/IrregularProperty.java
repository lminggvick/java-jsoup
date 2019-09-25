package Model.PeterPan;

import Mapper.ModelMapper;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class IrregularProperty implements ModelMapper {
    private String title;
    private String description;
    private String date;
}