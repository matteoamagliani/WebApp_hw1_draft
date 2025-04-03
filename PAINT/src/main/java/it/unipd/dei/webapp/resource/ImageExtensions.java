package it.unipd.dei.webapp.resource;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ImageExtensions {
    jpg, 
    jpeg, 
    png, 
    bmp;

    public static ImageExtensions fromString(String extension) {
        if (extension == null) return null;
        return valueOf(extension);
    }

    public static String getAcceptAttribute() {
        return Arrays.stream(values())
                .map(ext -> "." + ext.toString())
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return name();
    }
}
