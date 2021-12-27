package fpis.controller;

import fpis.dto.ProductDTO;
import fpis.dto.WorkerDTO;
import fpis.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "worker")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorkerContoller {
    @Autowired
    private WorkerService workerService;

    @GetMapping("/{id}")
    public WorkerDTO getWorker(@PathVariable("id") Integer id) {
        return workerService.getWorker(id);
    }
}
