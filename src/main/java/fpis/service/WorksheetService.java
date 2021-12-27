package fpis.service;

import fpis.domain.AbsenceItem;
import fpis.domain.PresenceItem;
import fpis.domain.Product;
import fpis.domain.Worksheet;
import fpis.dto.AbsenceItemDTO;
import fpis.dto.PresenceItemDTO;
import fpis.dto.WorksheetDTO;
import fpis.repository.WorksheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorksheetService {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private WorksheetRepository worksheetRepository;

    public Integer getNewId() {
        Integer newId = worksheetRepository.getNewId();
        return newId == null ? 1 : newId + 1;
    }

    public WorksheetDTO getWorksheet(Integer id) {
        Optional<Worksheet> worksheetOptional = worksheetRepository.findById(id);
        return worksheetOptional.map(this::fromEntity).orElse(null);
    }

    private WorksheetDTO fromEntity(Worksheet worksheet) {
        WorksheetDTO dto = new WorksheetDTO();
        dto.setSifra(worksheet.getSifra());
        if(worksheet.getWorker() != null){
            dto.setWorker(workerService.fromEntity(worksheet.getWorker()));
        }
        if(worksheet.getPresenceItemList() != null && worksheet.getPresenceItemList().size() > 0){
            dto.setPresenceItemList(
                    worksheet.getPresenceItemList()
                            .stream()
                            .map(this::toPresenceItemDTO)
                            .collect(Collectors.toList()));
        }
        if(worksheet.getAbsenceItemList() != null && worksheet.getAbsenceItemList().size() > 0){
            dto.setAbsenceItemList(
                    worksheet.getAbsenceItemList()
                            .stream()
                            .map(this::toAbsenceItemDTO)
                            .collect(Collectors.toList()));
        }
        return dto;
    }

    private PresenceItemDTO toPresenceItemDTO(PresenceItem presenceItem) {
        PresenceItemDTO dto = new PresenceItemDTO();
        dto.setWorksheetId(presenceItem.getWorksheet().getSifra());
        dto.setRedniBroj(presenceItem.getRedniBroj());
        dto.setVrstaPrisustva(presenceItem.getVrstaPrisustva());
        dto.setOpis(presenceItem.getOpis());
        dto.setDatum(presenceItem.getDatum());
        return dto;
    }

    private AbsenceItemDTO toAbsenceItemDTO(AbsenceItem absenceItem) {
        AbsenceItemDTO dto = new AbsenceItemDTO();
        dto.setWorksheetId(absenceItem.getWorksheet().getSifra());
        dto.setRedniBroj(absenceItem.getRedniBroj());
        dto.setBrojOdluke(absenceItem.getBrojOdluke());
        dto.setVrstaOdsustva(absenceItem.getVrstaOdsustva());
        dto.setDatumOd(absenceItem.getDatumOd());
        dto.setDatumDo(absenceItem.getDatumDo());
        return dto;
    }
}
