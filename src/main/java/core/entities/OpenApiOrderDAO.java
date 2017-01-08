package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OpenApiOrderDAO extends CrudRepository<OpenApiOrder, Long> {
    OpenApiOrder findByIdAndApiKey(long id, String apiKey);
    OpenApiOrder findByOpenApiQuoteIdAndApiKey(long openApiQuoteId, String apiKey);
}
