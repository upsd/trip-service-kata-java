package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        if (getLoggedUser() == null) {
            throw new UserNotLoggedInException();
        }

        return getTrips(user, getLoggedUser());
    }

    private List<Trip> getTrips(User user, User loggedUser) {
        if (user.getFriends().contains(loggedUser)) {
            return findTripsByUser(user);
        }
        return new ArrayList<Trip>();
    }

    protected List<Trip> findTripsByUser(User user) {
        return user.trips();
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
