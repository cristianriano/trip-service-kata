package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserService;

public class TripService {

    private UserService userService;

    public TripService(UserService userService) {
        this.userService = userService;
    }

    public List<Trip> getTripsByUser(final User user) throws UserNotLoggedInException {
        final User loggedUser = Optional.ofNullable(userService.getUserFromSession())
                                        .orElseThrow(UserNotLoggedInException::new);

        return user.isFriendOf(loggedUser) ?
            findTripsByUser(user)
            : Collections.emptyList();
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
