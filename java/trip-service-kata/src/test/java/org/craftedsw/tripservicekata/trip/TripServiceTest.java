package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

  private static final User user = new User();

  private final TripService tripService = new TripService();

  @Mock
  private UserSession userSession;

  @BeforeEach
  void setUp() {
    when(UserSession.getInstance()).thenReturn(userSession);
  }

  @Test
  void getTripsByUser_whenUserIsNotLoggerIn_raiseException() {
    when(userSession.getLoggedUser()).thenReturn(null);
    assertThrows(
        UserNotLoggedInException.class,
        () -> tripService.getTripsByUser(user)
    );
  }

  @Test
  void getTripsByUser_whenUserHasNoFriends_returnsEmptyList() {

  }

//  If returns trips of friends only
}
