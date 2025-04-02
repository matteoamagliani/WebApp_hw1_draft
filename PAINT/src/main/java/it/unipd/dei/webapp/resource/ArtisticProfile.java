package it.unipd.dei.webapp.resource;

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

    public ArtisticProfile() {
        this.userId = null;
        this.role = null;
        this.biography = null;
        this.followerCount = 0;
        this.numPublishedArtPieces = 0;
        this.numSoldArtPieces = 0;
        this.lastPubDate = null;
    }

    public ArtisticProfile(final UUID userId, final String role, final String biography, final int followerCount,
                           final int numPublishedArtPieces, final int numSoldArtPieces, final LocalDate lastPubDate) {
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