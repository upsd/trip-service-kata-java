package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTestShould {

    private User user;

    @Before
    public void setup() {
        user = new User();
    }

    @Test
    public void not_have_any_trips_or_friends_by_default() {
        List<User> friends = user.getFriends();
        List<Trip> trips = user.trips();

        assertThat(friends.size(), is(0));
        assertThat(trips.size(), is(0));
    }

    @Test
    public void add_friend() {
        user.addFriend(new User());

        List<User> friends = user.getFriends();

        assertThat(friends.size(), is(1));
    }

    @Test
    public void add_trips() {
        user.addTrip(new Trip());

        List<Trip> trips = user.trips();

        assertThat(trips.size(), is(1));
    }
}
