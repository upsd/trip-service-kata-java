package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TripServiceShould {

    @Test(expected = UserNotLoggedInException.class)
    public void throw_exception_if_no_user_is_logged_in() {
        TripService tripService = new TestableTripService(null, null);

        tripService.getTripsByUser(new User());
    }

    @Test
    public void return_empty_list_of_trips_if_logged_in_user_is_not_friends_with_user() {
        TripService tripService = new TestableTripService(new User(), null);

        List<Trip> trips = tripService.getTripsByUser(new User());

        assertThat(trips.size(), is(0));
    }

    @Test
    public void return_trips_when_logged_in_user_is_friend_of_user() {
        User userWithFriends = new User();
        User loggedInUser = new User();
        userWithFriends.addFriend(loggedInUser);
        TripService tripService = new TestableTripService(loggedInUser, asList(new Trip(), new Trip()));

        List<Trip> trips = tripService.getTripsByUser(userWithFriends);

        assertThat(trips.size(), is(2));
    }

    private class TestableTripService extends TripService {

        private final User loggedInUser;
        private List<Trip> trips;

        public TestableTripService(User loggedInUser, List<Trip> trips) {
            this.loggedInUser = loggedInUser;
            this.trips = trips;
        }

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return this.trips;
        }

    }
}
