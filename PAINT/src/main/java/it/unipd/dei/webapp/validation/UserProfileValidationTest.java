package it.unipd.dei.webapp.validation;

import it.unipd.dei.webapp.resource.UserProfile;
import it.unipd.dei.webapp.resource.ImageExtensions;
import it.unipd.dei.webapp.validation.ValidationHashMap;

import java.time.LocalDate;
import java.util.UUID;

public class UserProfileValidationTest {
    public static void main(String[] args) {
        // Crea un'istanza di UserProfile con dati di esempio
        UserProfile userProfile = new UserProfile(
            UUID.randomUUID(), // ID univoco
            null, // Nessuna immagine di profilo
            ImageExtensions.jpg, // Estensione immagine valida
            "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd", // Nome
            "Doe", // Cognome
            "BrandNameExample", // Nome del brand
            LocalDate.of(2000, 1, 1), // Data di nascita
            LocalDate.now(), // Data di registrazione
            "Italy", // Paese
            "Padova", // Città
            "35100", // Codice postale
            "Via Roma 1" // Indirizzo
        );

        // Chiama il metodo validateFields() e ottieni il risultato
        ValidationHashMap validationResult = userProfile.validateFields();

        // Stampa il risultato della validazione utilizzando il metodo toString()
        System.out.println("Risultato della validazione:");
        System.out.println(validationResult.toString());
    }
}
