package fpis.controller;

import fpis.dto.WorksheetDTO;
import fpis.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "worksheet")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorksheetController {
    @Autowired
    private WorksheetService worksheetService;

    @GetMapping("/new-id")
    public Integer getNewId() {
        return worksheetService.getNewId();
    }

    @GetMapping("/{id}")
    public WorksheetDTO getWorksheet(@PathVariable("id") Integer id) {
        return worksheetService.getWorksheet(id);
    }
}
