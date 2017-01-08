package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface KonnektiveOrderDetailsDAO extends CrudRepository<KonnektiveOrderDetails, Long> {
}
