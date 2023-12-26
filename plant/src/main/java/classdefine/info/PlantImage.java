package classdefine.info;

// 植物图片实体类
public class PlantImage {
    private int imageID; // 图片编号
    private int plantID; // 植物编号
    private String location; // 拍摄地点
    private String description; // 描述
    private String photographer; // 拍摄人
    private String photoPath; // 图片路径

    // 构造函数

    public PlantImage() {

    }
    public PlantImage(int imageID, int plantID, String location, String description, String photographer, String photoPath) {
        this.imageID = imageID;
        this.plantID = plantID;
        this.location = location;
        this.description = description;
        this.photographer = photographer;
        this.photoPath = photoPath;
    }

    // Getter 和 Setter 方法

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "PlantImage{" +
                "imageID=" + imageID +
                ", plantID=" + plantID +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", photographer='" + photographer + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
