public interface plantsDAO {
    plants getPlantByID(int plantID);
    void addPlant(plants plant);
    public void updatePlant(int plantID, int attribute, String newValue);
    public void deletePlant(int plantID);
}
