public class series {
    private String spaceName;
    private String alias;
    private String familyName;
    private String genusName;
    private String city;
    private String environment;

    series() {}

    public series(String SpaceName, String alias, String familyName, String genusName, String city, String environment) {
        this.spaceName = SpaceName;
        this.alias = alias;
        this.familyName = familyName;
        this.genusName = genusName;
        this.city = city;
        this.environment = environment;
    }

    // Getters
    public String getSpaceName() {
        return spaceName;
    }

    public String getAlias() {
        return alias;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGenusName() {
        return genusName;
    }

    public String getCity() {
        return city;
    }

    public String getEnvironment() {
        return environment;
    }

    // Setters
    public void setSpaceName(String SpaceName) {
        this.spaceName = SpaceName;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}