package fpis.repository;

import fpis.domain.PresenceItem;
import fpis.identity.PresenceItemIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceItemRepository extends JpaRepository<PresenceItem, PresenceItemIdentity> {
}
