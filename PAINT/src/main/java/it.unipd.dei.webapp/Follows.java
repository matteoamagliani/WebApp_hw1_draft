import java.util.UUID;
import java.time.LocalDate;

public class Follows {
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