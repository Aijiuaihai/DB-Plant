import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class plantsDAOim extends DAObese implements plantsDAO{
    private static final String PLANT_SELECT_SQL = "select * from plants where plantID=?";
    
    public plants getPlantByID(int plantID) {
        Connection con = null;
        plants plant = new plants();
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(PLANT_SELECT_SQL);
            psmt.setInt(1, plantID);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                plant.setPlantID(rs.getInt("plantID"));
                plant.setDiseaseName(rs.getString("diseaseName"));
                plant.setAlias(rs.getString("alias"));
                plant.setFamilyName(rs.getString("familyName"));
                plant.setSpeciesName(rs.getString("speciesName"));
                plant.setMorphologicalFeatures(rs.getString("morphologicalFeatures"));
                plant.setCultivationTechniques(rs.getString("cultivationTechniques"));
                plant.setApplicationValue(rs.getString("applicationValue"));
                plant.setImageID(rs.getInt("imageID"));
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return plant;
    }
    private static final String PLANT_INSERT_SQL = "insert into plants (plantID, diseaseName, alias, familyName, speciesName, morphologicalFeatures, cultivationTechniques, applicationValue, imageID) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void addPlant(plants plant) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(PLANT_INSERT_SQL);
            psmt.setInt(1, plant.getPlantID());
            psmt.setString(2, plant.getDiseaseName());
            psmt.setString(3, plant.getAlias());
            psmt.setString(4, plant.getFamilyName());
            psmt.setString(5, plant.getSpeciesName());
            psmt.setString(6, plant.getMorphologicalFeatures());
            psmt.setString(7, plant.getCultivationTechniques());
            psmt.setString(8, plant.getApplicationValue());
            psmt.setInt(9, plant.getImageID());
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void updatePlant(int plantID, int attribute, String newValue) {
        Connection con = null;
        try {
            con = getConnection();
            String updateQuery = "UPDATE plants SET ";
            switch (attribute) {
                case 1:
                    updateQuery += "diseaseName = ?";
                    break;
                case 2:
                    updateQuery += "alias = ?";
                    break;
                case 3:
                    updateQuery += "familyName = ?";
                    break;
                case 4:
                    updateQuery += "speciesName = ?";
                    break;
                case 5:
                    updateQuery += "morphologicalFeatures = ?";
                    break;
                case 6:
                    updateQuery += "cultivationTechniques = ?";
                    break;
                case 7:
                    updateQuery += "applicationValue = ?";
                    break;
                case 8:
                    updateQuery += "imageID = ?";
                    break;
                default:
                    System.out.println("无效选择");
                    return;
            }
            updateQuery += " WHERE plantID = ?";
            
            PreparedStatement psmt = con.prepareStatement(updateQuery);
            psmt.setString(1, newValue);
            psmt.setInt(2, plantID);
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static final String PLANT_DELETE_SQL = "delete from plants where plantID=?";

    public void deletePlant(int plantID) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(PLANT_DELETE_SQL);
            psmt.setInt(1, plantID);
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
