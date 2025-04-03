package it.unipd.dei.webapp.validation;

import java.util.Map;

public interface Validatable {

    // Validation method
    public Map<String, String> validateFields();
}
