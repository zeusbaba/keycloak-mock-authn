package no.elhub.keycloak.mockauthn.storage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
public class MockUser {
    private final String id;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    @Setter private String password;
    private final boolean enabled;
    private final Long created;
    private final List<String> roles;

    public MockUser(String username, String password, String firstName, String lastName, boolean enabled, Long created, List<String> roles) {
        this.id = username;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = "admin@elhub.cloud";
        this.enabled = enabled;
        this.created = created;
        this.roles = roles;
    }

}
