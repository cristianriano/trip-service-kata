package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserService;

public class TripService {

    private final TripRepository tripRepository;
    private final UserService userService;

    public TripService(UserService userService, TripRepository tripRepository) {
        this.userService = userService;
        this.tripRepository = tripRepository;
    }

    public List<Trip> getTripsByUser(final User user) throws UserNotLoggedInException {
        final User loggedUser = Optional.ofNullable(userService.getUserFromSession())
                                        .orElseThrow(UserNotLoggedInException::new);

        final TripRepository tripRepository = this.tripRepository;
        return user.isFriendOf(loggedUser) ?
            tripRepository.findTripsByUser(user)
            : Collections.emptyList();
    }
}
