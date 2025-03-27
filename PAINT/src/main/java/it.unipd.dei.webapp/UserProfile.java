import java.util.UUID;
import java.time.LocalDate;

public class UserProfile {
    private final UUID id;
    private final byte[] profilePicture;
    private final String pictureExtension;
    private final String name;
    private final String surname;
    private final String brandName;
    private final LocalDate birthDate;
    private final LocalDate registrationDate;
    private final String locationCountry;
    private final String locationCity;
    private final String locationPostalCode;
    private final String locationAddress;

    public UserProfile(final UUID id, final byte[] profilePicture, final String pictureExtension, final String name, final String surname,
                       final String brandName, final LocalDate birthDate, final LocalDate registrationDate,
                       final String locationCountry, final String locationCity, final String locationPostalCode, final String locationAddress) {
        this.id = id;
        this.profilePicture = profilePicture;
        this.pictureExtension = pictureExtension;
        this.name = name;
        this.surname = surname;
        this.brandName = brandName;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.locationCountry = locationCountry;
        this.locationCity = locationCity;
        this.locationPostalCode = locationPostalCode;
        this.locationAddress = locationAddress;
    }

    public final UUID getId() {
        return id;
    }

    public final byte[] getProfilePicture() {
        return profilePicture;
    }

    public final String getPictureExtension() {
        return pictureExtension;
    }

    public final String getName() {
        return name;
    }

    public final String getSurname() {
        return surname;
    }

    public final String getBrandName() {
        return brandName;
    }

    public final LocalDate getBirthDate() {
        return birthDate;
    }

    public final LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public final String getLocationCountry() {
        return locationCountry;
    }

    public final String getLocationCity() {
        return locationCity;
    }

    public final String getLocationPostalCode() {
        return locationPostalCode;
    }

    public final String getLocationAddress() {
        return locationAddress;
    }
}