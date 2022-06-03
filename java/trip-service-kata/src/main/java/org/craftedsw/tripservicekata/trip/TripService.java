package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    public List<Trip> getTripsByUser(final User user) throws UserNotLoggedInException {
        final User loggedUser = Optional.ofNullable(getUserFromSession())
                                        .orElseThrow(UserNotLoggedInException::new);

        return user.isFriendOf(loggedUser) ?
            findTripsByUser(user)
            : Collections.emptyList();
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getUserFromSession() {
        return UserSession.getInstance()
                          .getLoggedUser();
    }

}
