package it.unipd.dei.webapp.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import it.unipd.dei.webapp.validation.Validatable;
import it.unipd.dei.webapp.validation.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserProfile implements Validatable {

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
    private final String name;
    private final String surname;
    private final String brandName;
    private final LocalDate birthDate;
    private final LocalDate registrationDate;
    private final String locationCountry;
    private final String locationCity;
    private final String locationPostalCode;
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

    public Map<String, String> validateFields() {
        Map<String, String> output = new HashMap<String, String>();
        // Name validation
        String result = Validator.validateString(name, 50);
        output.put(NAME_NAME, result);
        // Surname validation
        result = Validator.validateString(surname, 50);
        output.put(SURNAME_NAME, result);
        // BirthDate validation
        result = "";
        if(birthDate == null) {
            result += "Is null";
        } else {
            if(ChronoUnit.YEARS.between(birthDate, registrationDate) < 18) {
                result += "Not adult (<18)";
            } else {
                result += "Valid";
            }
        }
        output.put(BIRTH_DATE_NAME, result);
        // RegistrationDate validation
        result = Validator.validateObject(registrationDate);
        output.put(REGISTRATION_DATE_NAME, result);
        // LocationCountry validation
        result = Validator.validateString(locationCountry, 30);
        output.put(LOCATION_COUNTRY_NAME, result);
        // LocationCity validation
        result = Validator.validateObject(locationCity);
        output.put(LOCATION_CITY_NAME, result);
        // LocationPostalCode validation
        result = Validator.validateString(locationPostalCode, 10);
        output.put(LOCATION_POSTAL_CODE_NAME, result);
        // LocationAddress validation
        result = Validator.validateString(locationAddress, 254);
        output.put(LOCATION_ADDRESS_NAME, result);
        return output;
    }
}