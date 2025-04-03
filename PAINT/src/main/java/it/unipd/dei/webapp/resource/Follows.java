package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

public class Follows {

    public static final String TABLE_NAME = "Follows";
    public static final String USER_ID_FOLLOWER_NAME = "userIdFollower";
    public static final String ARTISTIC_USER_ID_FOLLOWED_NAME = "artisticUserIdFollowed";
    public static final String FOLLOW_DATE_NAME = "followDate"; 

    private final UUID userIdFollower;
    private final UUID artisticUserIdFollowed;
    private final LocalDate followDate;

    public Follows(final UUID userIdFollower, final UUID artisticUserIdFollowed, final LocalDate followDate) {
        this.userIdFollower = userIdFollower;
        this.artisticUserIdFollowed = artisticUserIdFollowed;
        this.followDate = followDate;
    }

    public UUID getUserIdFollower() {
        return userIdFollower;
    }

    public UUID getArtisticUserIdFollowed() {
        return artisticUserIdFollowed;
    }

    public LocalDate getFollowDate() {
        return followDate;
    }
}