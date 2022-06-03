package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Spy
    private TripService testedInstance = new TripService();


    @Test
    void getTripsByUser_whenUserHasNoFriends_returnsEmptyList() {
        // Given
        User user = new User();
        doReturn(user).when(testedInstance)
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
        doReturn(user).when(testedInstance)
                      .getUserFromSession();

        // When
        List<Trip> tripsByUser = testedInstance.getTripsByUser(user);

        // Then
        assertTrue(tripsByUser.isEmpty());
    }

//  If is not logged in raise error
//  If returns trips of friends only
}
