package it.unipd.dei.webapp.resource;

import java.util.UUID;
import java.time.LocalDate;

public class UserProfile {

    public static final String TABLE_NAME = "UserProfile";
    public static final String ID_NAME = "id";
    public static final String PROFILE_PICTURE_NAME = "ProfilePicture";
    public static final String PICTURE_EXTENSION_NAME = "PictureExtension";
    public static final String NAME_NAME = "\"Name\"";
    public static final String SURNAME_NAME = "Surname";
    public static final String BRAND_NAME_NAME = "BrandName";
    public static final String BIRTH_DATE_NAME = "BirthDate";
    public static final String REGISTRATION_DATE_NAME = "RegistrationDate";
    public static final String LOCATION_COUNTRY_NAME = "LocationCountry";
    public static final String LOCATION_CITY_NAME = "LocationCity";
    public static final String LOCATION_POSTAL_CODE_NAME = "LocationPostalCode";
    public static final String LOCATION_ADDRESS_NAME = "LocationAddress";

    private final UUID id;
    private final byte[] profilePicture;
    private final ImageExtensions pictureExtension;
    // Validate (0)
    private final String name;
    // Validate (1)
    private final String surname;
    // Validate (2)
    private final String brandName;
    // Validate (3)
    private final LocalDate birthDate;
    private final LocalDate registrationDate;
    // Validate (4)
    private final String locationCountry;
    private final String locationCity;
    // Validate (5)
    private final String locationPostalCode;
    // Validate (6)
    private final String locationAddress;


    public UserProfile(final UUID id, final byte[] profilePicture, ImageExtensions pictureExtension, final String name, final String surname,
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

    public final ImageExtensions getPictureExtension() {
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