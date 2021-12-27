package fpis.service;

import fpis.domain.Worker;
import fpis.dto.WorkerDTO;
import fpis.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    public WorkerDTO getWorker(Integer id) {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        return workerOptional.map(this::fromEntity).orElse(null);
    }

    private WorkerDTO fromEntity(Worker worker) {
        WorkerDTO dto = new WorkerDTO();
        dto.setSifra(worker.getSifra());
        dto.setImePrezime(worker.getImePrezime());
        dto.setJmbg(worker.getJmbg());
        dto.setRadnoMesto(worker.getRadnoMesto());
        return dto;
    }
}
