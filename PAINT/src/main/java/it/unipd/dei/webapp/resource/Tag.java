package it.unipd.dei.webapp.resource;

public class Tag {
    
    public static final String TABLE_NAME = "Tag";
    public static final String NAME_NAME = "name";
    public static final String CATEGORY_NAME = "category";

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