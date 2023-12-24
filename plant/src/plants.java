public class plants {
    private int plantID;
    private String diseaseName;
    private String alias;
    private String familyName;
    private String speciesName;
    private String morphologicalFeatures;
    private String cultivationTechniques;
    private String applicationValue;
    private int imageID;
    plants(){}
    public plants(int plantID, String diseaseName, String alias, String familyName, String speciesName, String morphologicalFeatures, String cultivationTechniques, String applicationValue, int imageID) {
        this.plantID = plantID;
        this.diseaseName = diseaseName;
        this.alias = alias;
        this.familyName = familyName;
        this.speciesName = speciesName;
        this.morphologicalFeatures = morphologicalFeatures;
        this.cultivationTechniques = cultivationTechniques;
        this.applicationValue = applicationValue;
        this.imageID = imageID;
    }
     // Getters
     public int getPlantID() {
        return plantID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getAlias() {
        return alias;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String getMorphologicalFeatures() {
        return morphologicalFeatures;
    }

    public String getCultivationTechniques() {
        return cultivationTechniques;
    }

    public String getApplicationValue() {
        return applicationValue;
    }

    public int getImageID() {
        return imageID;
    }

    // Setters
    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public void setMorphologicalFeatures(String morphologicalFeatures) {
        this.morphologicalFeatures = morphologicalFeatures;
    }

    public void setCultivationTechniques(String cultivationTechniques) {
        this.cultivationTechniques = cultivationTechniques;
    }

    public void setApplicationValue(String applicationValue) {
        this.applicationValue = applicationValue;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
