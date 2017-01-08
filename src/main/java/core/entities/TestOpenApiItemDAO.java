package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TestOpenApiItemDAO extends CrudRepository<TestOpenApiItem, Long> {
    List<TestOpenApiItem> findByTestOpenApiQuoteIdAndTestApiKey(long testOpenApiQuoteId, String testApiKey);
}
