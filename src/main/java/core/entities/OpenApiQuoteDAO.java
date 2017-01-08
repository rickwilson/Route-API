package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OpenApiQuoteDAO extends CrudRepository<OpenApiQuote, Long> {
    OpenApiQuote findByIdAndApiKey(long id, String apiKey);
    OpenApiQuote findByIdAndWidgetKey(long id, String widgetKey);
}
