package it.unipd.dei.webapp.resource;

public class Tag {
    private final String name;
    private final String category;

    public Tag(final String name, final String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}