package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserShould {

    private User user;

    @Before
    public void setup() {
        user = new User();
    }

    @Test
    public void not_have_any_trips_by_default() {
        List<Trip> trips = user.trips();

        assertThat(trips.size(), is(0));
    }

    @Test
    public void add_friend() {
        TestableUser testableUser = new TestableUser();
        testableUser.addFriend(new User());

        List<User> friends = testableUser.getFriends();

        assertThat(friends.size(), is(1));
    }

    @Test
    public void add_trips() {
        user.addTrip(new Trip());

        List<Trip> trips = user.trips();

        assertThat(trips.size(), is(1));
    }

    @Test
    public void determine_if_friends_with_another_user() {
        User friend = new User();
        user.addFriend(friend);

        boolean areFriends = user.isFriendsWith(friend);

        assertThat(areFriends, is(true));
    }

    private class TestableUser extends User {

        private List<User> friends;

        public TestableUser() {
            this.friends = new ArrayList<User>();
        }

        @Override
        public void addFriend(User user) {
            this.friends.add(user);
            super.addFriend(user);
        }

        public List<User> getFriends() {
            return this.friends;
        }
    }
}



