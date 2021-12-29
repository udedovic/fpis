package fpis.service;

import fpis.domain.*;
import fpis.dto.AbsenceItemDTO;
import fpis.dto.PresenceItemDTO;
import fpis.dto.WorksheetDTO;
import fpis.repository.AbsenceItemRepository;
import fpis.repository.PresenceItemRepository;
import fpis.repository.WorksheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorksheetService {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private WorksheetRepository worksheetRepository;
    @Autowired
    private PresenceItemRepository presenceItemRepository;
    @Autowired
    private AbsenceItemRepository absenceItemRepository;

    public Integer getNewId() {
        Integer newId = worksheetRepository.getNewId();
        return newId == null ? 1 : newId + 1;
    }

    public WorksheetDTO getWorksheet(Integer id) {
        Optional<Worksheet> worksheetOptional = worksheetRepository.findById(id);
        return worksheetOptional.map(this::fromEntity).orElse(null);
    }

    @Transactional
    public Boolean insertWorksheet(WorksheetDTO worksheetDTO) {
        Optional<Worksheet> worksheetOptional = worksheetRepository.findById(worksheetDTO.getSifra());
        if (worksheetOptional.isPresent()) {
            worksheetDTO.setSifra(getNewId());
        }
        Worksheet worksheet = toEntity(worksheetDTO);
        worksheetRepository.save(worksheet);
        if (worksheetDTO.getPresenceItemList() != null && worksheetDTO.getPresenceItemList().size() > 0) {
            presenceItemRepository.saveAll(worksheetDTO.getPresenceItemList()
                    .stream()
                    .map(p -> toPresenceItem(p, worksheet))
                    .collect(Collectors.toList()));
        }
        if (worksheetDTO.getAbsenceItemList() != null && worksheetDTO.getAbsenceItemList().size() > 0) {
            absenceItemRepository.saveAll(worksheetDTO.getAbsenceItemList()
                    .stream()
                    .map(a -> toAbsenceItem(a, worksheet))
                    .collect(Collectors.toList()));
        }
        return true;
    }

    @Transactional
    public Boolean updateWorksheet(WorksheetDTO worksheetDTO) {
        return true;
    }

    private WorksheetDTO fromEntity(Worksheet worksheet) {
        WorksheetDTO dto = new WorksheetDTO();
        dto.setSifra(worksheet.getSifra());
        if (worksheet.getWorker() != null) {
            dto.setWorker(workerService.fromEntity(worksheet.getWorker()));
        }
        if (worksheet.getPresenceItemList() != null && worksheet.getPresenceItemList().size() > 0) {
            dto.setPresenceItemList(
                    worksheet.getPresenceItemList()
                            .stream()
                            .map(this::toPresenceItemDTO)
                            .collect(Collectors.toList()));
        }
        if (worksheet.getAbsenceItemList() != null && worksheet.getAbsenceItemList().size() > 0) {
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


    private Worksheet toEntity(WorksheetDTO worksheetDTO) {
        Worksheet worksheet = new Worksheet();
        worksheet.setSifra(worksheetDTO.getSifra());
        if (worksheetDTO.getWorker() != null) {
            worksheet.setWorker(workerService.findById(worksheetDTO.getWorker().getSifra()));
        }
        return worksheet;
    }

    private PresenceItem toPresenceItem(PresenceItemDTO presenceItemDTO, Worksheet worksheet) {
        PresenceItem presenceItem = new PresenceItem();
        presenceItem.setWorksheet(worksheet);
        presenceItem.setRedniBroj(presenceItemDTO.getRedniBroj());
        presenceItem.setVrstaPrisustva(presenceItemDTO.getVrstaPrisustva());
        presenceItem.setOpis(presenceItemDTO.getOpis());
        presenceItem.setDatum(presenceItemDTO.getDatum());
        return presenceItem;
    }

    private AbsenceItem toAbsenceItem(AbsenceItemDTO absenceItemDTO, Worksheet worksheet) {
        AbsenceItem absenceItem = new AbsenceItem();
        absenceItem.setWorksheet(worksheet);
        absenceItem.setRedniBroj(absenceItemDTO.getRedniBroj());
        absenceItem.setBrojOdluke(absenceItemDTO.getBrojOdluke());
        absenceItem.setVrstaOdsustva(absenceItemDTO.getVrstaOdsustva());
        absenceItem.setDatumOd(absenceItemDTO.getDatumOd());
        absenceItem.setDatumDo(absenceItemDTO.getDatumDo());
        return absenceItem;
    }
}
