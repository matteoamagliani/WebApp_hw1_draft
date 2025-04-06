package it.unipd.dei.webapp.resource;

/**
 * Represents a tag used for categorizing or labeling resources such as events, art pieces, etc.
 * <p>
 * This class stores information about a tag, including its name and category, which can be used to
 * organize or filter resources in a system.
 * </p>
 */

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