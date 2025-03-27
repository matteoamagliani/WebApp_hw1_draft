import java.util.UUID;
import java.time.LocalDate;

public class ArtisticProfile {
    private final UUID userId;
    private final String role;
    private final String biography;
    private final int followerCount;
    private final int numPublishedArtPieces;
    private final int numSoldArtPieces;
    private final LocalDate lastPubDate;

    public ArtisticProfile(UUID userId, String role, String biography, int followerCount,
                           int numPublishedArtPieces, int numSoldArtPieces, LocalDate lastPubDate) {
        this.userId = userId;
        this.role = role;
        this.biography = biography;
        this.followerCount = followerCount;
        this.numPublishedArtPieces = numPublishedArtPieces;
        this.numSoldArtPieces = numSoldArtPieces;
        this.lastPubDate = lastPubDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getBiography() {
        return biography;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getNumPublishedArtPieces() {
        return numPublishedArtPieces;
    }

    public int getNumSoldArtPieces() {
        return numSoldArtPieces;
    }

    public LocalDate getLastPubDate() {
        return lastPubDate;
    }
}