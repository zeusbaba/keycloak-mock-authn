package no.elhub.keycloak.mockauthn.storage;

import java.util.Collections;
import java.util.List;

public class MockUserRepository {

    private final List<MockUser> users;
    private final String commonPassword;

    MockUserRepository(String commonPassword) {
        this.commonPassword = commonPassword;
        users = Collections.singletonList(
                makeMockUser("11112222444")
        );

    }

    public boolean isValidSsn(String userName) {
        return (
                userName != null
                        && userName.length() == 11
                        && userName.matches("[0-9]+")
        );
    }
    public MockUser makeMockUser(String userName) {

        return new MockUser(
                userName,
                commonPassword,
                "Platform", "User",
                true,
                System.currentTimeMillis(),
                Collections.emptyList()
        );
    }

    List<MockUser> getAllUsers() {
        return users;
    }

    int getUsersCount() {
        return users.size();
    }

    MockUser findUserById(String id) {
        return isValidSsn(id) ? makeMockUser(id) : null;
    }

    MockUser findUserByUsernameOrEmail(String username) {
        return isValidSsn(username) ? makeMockUser(username) : null;
    }

    List<MockUser> findUsers(String query) {

        return isValidSsn(query) ?
                Collections.singletonList(makeMockUser(query))
                : Collections.emptyList();
    }

    boolean validateCredentials(String username, String password) {
        return findUserByUsernameOrEmail(username).getPassword().equals(password);
    }

    boolean updateCredentials(String username, String password) {
        findUserByUsernameOrEmail(username).setPassword(password);
        return true;
    }

}
