package core.entities;

import core.entities.enums.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OmniTransactionDAO extends JpaRepository<OmniTransaction, Long> {
    OmniTransaction findBySourceEntityAndSourceEntityIdAndApiKey(SourceEntity sourceEntity, long sourceEntityId, String apiKey);
}
