package dao.info;

import com.sun.jdi.ArrayReference;
import com.sun.jdi.request.StepRequest;
import entity.info.Plant;
import entity.info.Taxonomy;
import util.MySQLConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaxonomyDAOImpl implements TaxonomyDAO {

    @Override
    public Taxonomy getTaxonomyBySpecies(String species) {
        Taxonomy taxonomy = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM taxonomy WHERE species = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, species);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                taxonomy = new Taxonomy();
                taxonomy.setTaxonomyID(rs.getInt("taxonomyID"));
                taxonomy.setFamily(rs.getString("family"));
                taxonomy.setGenus(rs.getString("genus"));
                taxonomy.setSpecies(rs.getString("species"));
                taxonomy.setPlantID(rs.getInt("plantID"));
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taxonomy;
    }
    @Override
    public Taxonomy getTaxonomyById(int taxonomyID) {
        Taxonomy taxonomy = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM taxonomy WHERE taxonomyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, taxonomyID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                taxonomy = new Taxonomy();
                taxonomy.setTaxonomyID(rs.getInt("taxonomyID"));
                taxonomy.setFamily(rs.getString("family"));
                taxonomy.setGenus(rs.getString("genus"));
                taxonomy.setSpecies(rs.getString("species"));
                taxonomy.setPlantID(rs.getInt("plantID"));
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taxonomy;
    }

    @Override
    public Taxonomy getTaxonomyByPlantId(int plantID) {
        Taxonomy taxonomy = null;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT * FROM taxonomy WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, plantID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                taxonomy = new Taxonomy();
                taxonomy.setTaxonomyID(rs.getInt("taxonomyID"));
                taxonomy.setFamily(rs.getString("family"));
                taxonomy.setGenus(rs.getString("genus"));
                taxonomy.setSpecies(rs.getString("species"));
                taxonomy.setPlantID(rs.getInt("plantID"));
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taxonomy;
    }

    @Override
    public boolean addTaxonomy(Taxonomy taxonomy) {
        boolean success = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "INSERT INTO taxonomy (family, genus, species, plantID) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, taxonomy.getFamily());
            ps.setString(2, taxonomy.getGenus());
            ps.setString(3, taxonomy.getSpecies());
            ps.setInt(4, taxonomy.getPlantID());

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
    public boolean updateTaxonomy(Taxonomy taxonomy) {
        boolean success = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "UPDATE taxonomy SET family = ?, genus = ?, species = ?, plantID = ? WHERE taxonomyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, taxonomy.getFamily());
            ps.setString(2, taxonomy.getGenus());
            ps.setString(3, taxonomy.getSpecies());
            ps.setInt(4, taxonomy.getPlantID());
            ps.setInt(5, taxonomy.getTaxonomyID());

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
    public boolean deleteTaxonomy(int taxonomyID) {
        boolean success = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM taxonomy WHERE taxonomyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, taxonomyID);

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
    public boolean deleteTaxonomyByPlantId(int taxonomyID) {
        boolean success = false;

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "DELETE FROM taxonomy WHERE plantID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, taxonomyID);

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
    public ArrayList<String> getAllFamily() {
        boolean success = false;

        ArrayList<String> strings = new ArrayList<>();
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "SELECT DISTINCT family FROM taxonomy";
            PreparedStatement ps = connection.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                strings.add(rs.getString(1));
            }

            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Override
    public ArrayList<Taxonomy> getByFamilyName(String name) {
        ArrayList<Taxonomy> taxonomyArrayList = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            String sql = "select * from " + name +"_view";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Taxonomy taxonomy = new Taxonomy();
                taxonomy.setTaxonomyID(rs.getInt("taxonomyID"));
                taxonomy.setFamily(rs.getString("family"));
                taxonomy.setGenus(rs.getString("genus"));
                taxonomy.setSpecies(rs.getString("species"));
                taxonomy.setPlantID(rs.getInt("plantID"));

                taxonomyArrayList.add(taxonomy);
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taxonomyArrayList;
    }

    @Override
    public boolean createView(String s) {
        if (!checkViewExists(s + "_view")) {
            String sql = "CREATE VIEW " + s + "_view" + " AS SELECT * FROM taxonomy WHERE family = '" + s + "'";
            try {
                Connection connection = MySQLConnectionPool.getConnection();
                Statement statement = connection.createStatement();

                // 执行SQL语句
                statement.executeUpdate(sql);
                System.out.println("视图创建成功！");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public boolean checkViewExists(String name) {
        try {
            Connection connection = MySQLConnectionPool.getConnection();
            Statement statement = connection.createStatement();

            // 查询 INFORMATION_SCHEMA.VIEWS 视图，检查视图是否存在
            String sql = "SELECT * FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_NAME = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                System.out.println("视图 " + name + " 已存在！");
                return true;
            } else {
                System.out.println("视图 " + name + " 不存在，可以创建。");
                return false;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Taxonomy> getTaxonomyBySingleParam(String sql) {
//        System.out.println("sql 语句是");
//        System.out.println(sql);
        ArrayList<Taxonomy> taxonomyArrayList = new ArrayList<>();

        try {
            Connection connection = MySQLConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Taxonomy taxonomy = new Taxonomy();
                taxonomy.setTaxonomyID(rs.getInt("taxonomyID"));
                taxonomy.setFamily(rs.getString("family"));
                taxonomy.setGenus(rs.getString("genus"));
                taxonomy.setSpecies(rs.getString("species"));
                taxonomy.setPlantID(rs.getInt("plantID"));

                taxonomyArrayList.add(taxonomy);
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taxonomyArrayList;
    }
}
