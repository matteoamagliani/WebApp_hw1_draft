package it.unipd.dei.webapp.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidationHashMap extends HashMap<String, String> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : this.entrySet()) {
            sb.append(entry.getKey())
              .append(": ")
              .append(entry.getValue())
              .append("\n");
        }
        
        if (!this.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
}
