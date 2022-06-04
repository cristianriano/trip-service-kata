package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    public static final User CURRENT_USER = new User();
    private static final User user = new User();

    @Spy
    private TripService tripService;

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
        mockLoggedUser(CURRENT_USER);

        assertEquals(tripService.getTripsByUser(user), List.of());
    }

    private void mockLoggedUser(User user) {
        doReturn(user).when(tripService).getLoggedUser();
    }
}
