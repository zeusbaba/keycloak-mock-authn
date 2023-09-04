package no.elhub.keycloak.mockauthn.storage;

import org.keycloak.Config;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.services.ServicesLogger;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.Collections;
import java.util.List;

public class MockUserStorageProviderFactory implements UserStorageProviderFactory<MockUserStorageProvider> {

    public static final String PROVIDER_ID = "mock-user";
    protected static ServicesLogger log = ServicesLogger.LOGGER;
    protected static final String COMMON_PASSWORD = "common-password";

    @Override
    public MockUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        // here you can set up the user storage provider, initiate some connections, etc.

        log.debug("COMMON_PASSWORD: " + model.getConfig().getFirst(COMMON_PASSWORD));
        String commonPassword = model.getConfig().containsKey(COMMON_PASSWORD)
                ? model.getConfig().getFirst(COMMON_PASSWORD)
                : "justapassword";
        MockUserRepository repository = new MockUserRepository(commonPassword);

        return new MockUserStorageProvider(session, model, repository);
    }


    @Override
    public String getId() {
        return PROVIDER_ID;
    }
    @Override
    public String getHelpText() {
        return "Allow login with any norwegian personnummer";
    }
    @Override
    public void init(Config.Scope scope) {

        log.info(PROVIDER_ID + " init with scope: " + scope);
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        log.info(PROVIDER_ID + " postInit with sessionFactory: " + keycloakSessionFactory);
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        ProviderConfigProperty configProperties = new ProviderConfigProperty();

        configProperties.setType(ProviderConfigProperty.STRING_TYPE);
        configProperties.setName(COMMON_PASSWORD);
        configProperties.setLabel("Common Password");
        configProperties.setHelpText("Common password for mock users e.g. justapassword");

        log.info(PROVIDER_ID + " getConfigProperties: " + configProperties);
        return Collections.singletonList(configProperties);
    }
    /*@Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property("myParam", "My Param", "Some Description", ProviderConfigProperty.STRING_TYPE, "some value", null)
                .build();
    }*/
}
