import java.util.UUID;
import java.time.LocalDate;

public class Advertisement {
    private final UUID artPieceId;
    private final double price;
    private final LocalDate publicationDate;

    public Advertisement(UUID artPieceId, double price, LocalDate publicationDate) {
        this.artPieceId = artPieceId;
        this.price = price;
        this.publicationDate = publicationDate;
    }

    public UUID getArtPieceId() {
        return artPieceId;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }
}