package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TestOpenApiQuoteDAO extends CrudRepository<TestOpenApiQuote, Long> {
    TestOpenApiQuote findByIdAndTestApiKey(long id, String testApiKey);
}
