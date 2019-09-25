package Model.PeterPan;

import Mapper.ModelMapper;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class IrregularProperty implements ModelMapper {
    private String title;
    private String description;
    private String date;
    private String url;

    @Override
    public String toString() {
        return "\nIrregularProperty{" +
                "\n\t title='" + title + '\'' +
                ",\n\t description='" + description + '\'' +
                ",\n\t date='" + date + '\'' +
                ",\n\t url='" + url + '\'' +
                '}';
    }
}