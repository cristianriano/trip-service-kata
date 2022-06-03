package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.UserService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private UserService userService;

    private TripService testedInstance;

    @BeforeEach
    void setUp() {
        testedInstance = spy(new TripService(userService));
    }

    @Test
    void getTripsByUser_whenUserHasNoFriends_returnsEmptyList() {
        // Given
        User user = new User();
        doReturn(user).when(userService)
                      .getUserFromSession();

        // When
        List<Trip> tripsByUser = testedInstance.getTripsByUser(user);

        // Then
        assertTrue(tripsByUser.isEmpty());
    }

    @Test
    void getTripsByUser_whenUserIsNotInTheListOfFriends_returnsEmptyList() {
        // Given
        User user = new User();
        User loggedInUser = new User();
        User friend = new User();
        user.addFriend(friend);
        doReturn(loggedInUser).when(userService)
                      .getUserFromSession();

        // When
        List<Trip> tripsByUser = testedInstance.getTripsByUser(user);

        // Then
        assertTrue(tripsByUser.isEmpty());
    }

    @Test
    void getTripsByUser_whenUserHasFriends_returnsListOfTrips() {
        // Given
        User user = new User();
        user.addFriend(user);
        doReturn(user).when(userService)
                      .getUserFromSession();

        Trip trip = new Trip();
        doReturn(Arrays.asList(trip)).when(testedInstance).findTripsByUser(user);

        // When
        List<Trip> tripsByUser = testedInstance.getTripsByUser(user);

        // Then
        assertEquals(Arrays.asList(trip), tripsByUser);
    }

    @Test
    void getTripsByUser_whenNoLoggedInUserFound_throwsException() {
        // Given
        User user = new User();
        doReturn(null).when(userService)
                      .getUserFromSession();
        // When Then
        assertThrows(UserNotLoggedInException.class, () -> testedInstance.getTripsByUser(user));
    }

//  If is not logged in raise error
//  If returns trips of friends only
}
