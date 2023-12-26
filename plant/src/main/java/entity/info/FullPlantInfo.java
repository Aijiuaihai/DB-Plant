package entity.info;

import java.util.ArrayList;

public class FullPlantInfo {
    private Plant plant;
    private Taxonomy taxonomy;
    private ArrayList<PlantImage> imageArrayList;

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }

    public ArrayList<PlantImage> getImageArrayList() {
        return imageArrayList;
    }

    public void setImageArrayList(ArrayList<PlantImage> imageArrayList) {
        this.imageArrayList = imageArrayList;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
