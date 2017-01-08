package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LimeLightInitializerDAO extends CrudRepository<LimeLightInitializer, Long> {
    LimeLightInitializer findByAccountIdAndParentOrderId(long accountId, String parentOrderId);
}
