package it.unipd.dei.webapp.validation;

import java.util.Map;

public interface Validatable {

    // Validation method
    Map<String, String> validateFields();
}
