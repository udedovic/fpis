package fpis.repository;

import fpis.domain.Worksheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorksheetRepository extends JpaRepository<Worksheet, Integer> {
    @Query(nativeQuery = true, value = "select max(sifra) from worksheet")
    Integer getNewId();
}
