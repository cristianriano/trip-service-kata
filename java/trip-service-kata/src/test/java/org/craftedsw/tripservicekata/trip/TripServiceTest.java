package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    private User currentUser;
    private User user;

    @Spy
    private TripService tripService;

    @BeforeEach
    void setUp() {
        currentUser = new User();
        user = new User();
    }

    @Test
    void getTripsByUser_whenUserIsNotLogged_raiseException() {
        mockLoggedUser(null);

        assertThrows(
            UserNotLoggedInException.class,
            () -> tripService.getTripsByUser(user)
        );
    }

    @Test
    void getTripsByUser_whenLoggedUserIsNotAFriend_returnEmptyList() {
        mockLoggedUser(currentUser);

        assertEquals(tripService.getTripsByUser(user), List.of());
    }

    @Test
    void getTripsByUser_whenLoggedUserIsAFriend_returnsTripsOfUser() {
        mockLoggedUser(currentUser);

        user.addFriend(currentUser);

        doReturn(List.of(new Trip())).when(tripService).getTrips(user);
        assertEquals(tripService.getTripsByUser(user).size(), 1);
    }

    private void mockLoggedUser(User user) {
        doReturn(user).when(tripService).getLoggedUser();
    }
}
