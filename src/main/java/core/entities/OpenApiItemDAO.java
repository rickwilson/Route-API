package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OpenApiItemDAO extends CrudRepository<OpenApiItem, Long> {
    List<OpenApiItem> findByOpenIdQuoteIdAndApiKey(long openIdQuoteId, String apiKey);
}
