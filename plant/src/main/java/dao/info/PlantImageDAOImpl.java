package dao.info;

import entity.info.PlantImage;
import util.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlantImageDAOImpl implements PlantImageDAO {
    @Override
    public PlantImage getImageByPlantId(int imageID) {
        PlantImage image = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plantimage WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, imageID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                image = new PlantImage();
                image.setImageID(rs.getInt("imageID"));
                image.setPlantID(rs.getInt("plantID"));
                image.setLocation(rs.getString("location"));
                image.setDescription(rs.getString("description"));
                image.setPhotographer(rs.getString("photographer"));
                image.setPhotoPath(rs.getString("photoPath"));
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }
    @Override
    public PlantImage getImageById(int imageID) {
        PlantImage image = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plantimage WHERE imageID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, imageID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                image = new PlantImage();
                image.setImageID(rs.getInt("imageID"));
                image.setPlantID(rs.getInt("plantID"));
                image.setLocation(rs.getString("location"));
                image.setDescription(rs.getString("description"));
                image.setPhotographer(rs.getString("photographer"));
                image.setPhotoPath(rs.getString("photoPath"));
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    @Override
    public List<PlantImage> getImagesByPlantId(int plantID) {
        ArrayList<PlantImage> imageArrayList = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM plantimage WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, plantID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PlantImage image = new PlantImage();
                image.setImageID(rs.getInt("imageID"));
                image.setPlantID(rs.getInt("plantID"));
                image.setLocation(rs.getString("location"));
                image.setDescription(rs.getString("description"));
                image.setPhotographer(rs.getString("photographer"));
                image.setPhotoPath(rs.getString("photoPath"));

                imageArrayList.add(image);
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageArrayList;
    }

    @Override
    public boolean addImage(PlantImage image) {
        boolean success = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO plantimage (plantID, location, description, photographer, photoPath) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, image.getPlantID());
            ps.setString(2, image.getLocation());
            ps.setString(3, image.getDescription());
            ps.setString(4, image.getPhotographer());
            ps.setString(5, image.getPhotoPath());

            int rowsAffected = ps.executeUpdate();
            success = rowsAffected > 0;

            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean deleteImageById(int imageID) {
        boolean success = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM plantimage WHERE imageID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, imageID);

            int rowsAffected = ps.executeUpdate();
            success = rowsAffected > 0;

            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
    @Override
    public boolean deleteImageByPlantId(int id) {
        boolean success = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM plantimage WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            success = rowsAffected > 0;

            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}
