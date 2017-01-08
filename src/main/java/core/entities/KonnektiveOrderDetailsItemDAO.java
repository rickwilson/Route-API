package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface KonnektiveOrderDetailsItemDAO extends CrudRepository<KonnektiveOrderDetailsItem, Long> {
}
