package fpis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PresenceItemDTO {
    private Integer worksheetId;
    private Integer redniBroj;
    private Integer vrstaPrisustva;
    private String opis;
    private Date datum;
}
