package fpis.repository;

import fpis.domain.AbsenceItem;
import fpis.identity.AbsenceItemIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceItemRepository extends JpaRepository<AbsenceItem, AbsenceItemIdentity> {
}
