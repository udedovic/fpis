package fpis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorksheetDTO {
    private Integer sifra;
    private WorkerDTO worker;
    private List<PresenceItemDTO> presenceItemList;
    private List<AbsenceItemDTO> absenceItemList;
}
