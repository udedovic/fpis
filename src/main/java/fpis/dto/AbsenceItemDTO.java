package fpis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AbsenceItemDTO {
    private Integer worksheetId;
    private Integer redniBroj;
    private Integer brojOdluke;
    private Integer vrstaOdsustva;
    private Date datumOd;
    private Date datumDo;
    private String status;
}
