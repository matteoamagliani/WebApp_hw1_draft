package it.unipd.dei.webapp.resource;

public final class Actions {
    public static final String CREATE_USERPROFILE = "CREATE_USERPROFILE";
    public static final String SINGUP_ADDRESS_INFORMATION = "SINGUP_ADDRESS";
    public static final String SINGUP_PERSONAL_INFORMATION = "SINGUP_PERSONAL_INFORMATION";

    private Actions() {throw new AssertionError(String.format("No instance of %s allowed.",Actions.class.getName()));}
}
