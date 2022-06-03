package org.craftedsw.tripservicekata.user;

public class UserService {

    public User getUserFromSession() {
        return UserSession.getInstance()
                          .getLoggedUser();
    }
}
