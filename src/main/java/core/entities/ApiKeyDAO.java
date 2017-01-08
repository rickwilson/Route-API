package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ApiKeyDAO extends CrudRepository<ApiKey, Long> {

    ApiKey findByApiKeyAndActive(String apiKey, boolean active);
    ApiKey findByApiKeyAndSecretAndActive(String apiKey, String secret, boolean active);
    ApiKey findByTestApiKeyAndTestSecretAndActive(String testApiKey, String testSecret, boolean active);
    ApiKey findByWidgetKeyOrApiKeyOrSecretOrTestWidgetKeyOrTestApiKeyOrTestSecret(String widgetKey, String apiKey, String secret, String testWidgetKey, String testApiKey, String testSecret);
    ApiKey findByWidgetKeyOrTestWidgetKey(String widgetKey, String testWidgetKey);
}
