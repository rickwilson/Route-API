package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TestOpenApiOrderDAO extends CrudRepository<TestOpenApiOrder, Long> {
    TestOpenApiOrder findByIdAndTestApiKey(long id, String testApiKey);
    TestOpenApiOrder findByTestOpenApiQuoteIdAndTestApiKey(long testOpenApiQuoteId, String testApiKey);
}
