import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class seriesDAOim extends DAObese implements seriesDAO{
    private static final String SERIES_SELECT_SQL = "select * from series where spaceName=?";
    public series getSeriesBySpaceName(String spaceName) {
        Connection con = null;
        series series = new series();
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SERIES_SELECT_SQL);
            psmt.setString(1, spaceName);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                series.setSpaceName(rs.getString("spaceName"));
                series.setAlias(rs.getString("alias"));
                series.setFamilyName(rs.getString("familyName"));
                series.setGenusName(rs.getString("genusName"));
                series.setCity(rs.getString("city"));
                series.setEnvironment(rs.getString("environment"));
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
        return series;
    }

private static final String SERIES_SELECT_BY_GENUS_SQL = "select * from series where genusName=?";
private static final String SERIES_SELECT_BY_FAMILY_SQL = "select * from series where familyName=?";

public List<series> getSeriesByGenusName(String genusName) {
    Connection con = null;
    List<series> seriesList = new ArrayList<>();
    try {
        con = getConnection();
        PreparedStatement psmt = con.prepareStatement(SERIES_SELECT_BY_GENUS_SQL);
        psmt.setString(1, genusName);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
            series series = new series();
            series.setSpaceName(rs.getString("spaceName"));
            series.setAlias(rs.getString("alias"));
            series.setFamilyName(rs.getString("familyName"));
            series.setGenusName(rs.getString("genusName"));
            series.setCity(rs.getString("city"));
            series.setEnvironment(rs.getString("environment"));
            seriesList.add(series);
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
    return seriesList;
}
private static final String SERIES_SELECT_BY_CITY_SQL = "select * from series where city like ?";
public List<series> getSeriesByCity(String city) {
    Connection con = null;
    List<series> seriesList = new ArrayList<>();
    try {
        con = getConnection();
        PreparedStatement psmt = con.prepareStatement(SERIES_SELECT_BY_CITY_SQL);
        psmt.setString(1, "%" + city + "%");
        ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
            series series = new series();
            series.setSpaceName(rs.getString("spaceName"));
            series.setAlias(rs.getString("alias"));
            series.setFamilyName(rs.getString("familyName"));
            series.setGenusName(rs.getString("genusName"));
            series.setCity(rs.getString("city"));
            series.setEnvironment(rs.getString("environment"));
            seriesList.add(series);
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
    return seriesList;
}
public List<series> getSeriesByFamilyName(String familyName) {
    Connection con = null;
    List<series> seriesList = new ArrayList<>();
    try {
        con = getConnection();
        PreparedStatement psmt = con.prepareStatement(SERIES_SELECT_BY_FAMILY_SQL);
        psmt.setString(1, familyName);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
            series series = new series();
            series.setSpaceName(rs.getString("spaceName"));
            series.setAlias(rs.getString("alias"));
            series.setFamilyName(rs.getString("familyName"));
            series.setGenusName(rs.getString("genusName"));
            series.setCity(rs.getString("city"));
            series.setEnvironment(rs.getString("environment"));
            seriesList.add(series);
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
    return seriesList;
}
    private static final String SERIES_INSERT_SQL = "insert into series (spaceName, alias, familyName, genusName, city, environment) values (?, ?, ?, ?, ?, ?)";

    public void addSeries(series series) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SERIES_INSERT_SQL);
            psmt.setString(1, series.getSpaceName());
            psmt.setString(2, series.getAlias());
            psmt.setString(3, series.getFamilyName());
            psmt.setString(4, series.getGenusName());
            psmt.setString(5, series.getCity());
            psmt.setString(6, series.getEnvironment());
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

    public void updateSeries(String SpaceName, int attribute, String newValue) {
        Connection con = null;
        try {
            con = getConnection();
            String updateQuery = "UPDATE series SET ";
            switch (attribute) {
                case 1:
                    updateQuery += "alias = ?";
                    break;
                case 2:
                    updateQuery += "familyName = ?";
                    break;
                case 3:
                    updateQuery += "genusName = ?";
                    break;
                case 4:
                    updateQuery += "city = ?";
                    break;
                case 5:
                    updateQuery += "environment = ?";
                    break;
                default:
                    System.out.println("Invalid selection");
                    return;
            }
            updateQuery += " WHERE SpaceName = ?";
            
            PreparedStatement psmt = con.prepareStatement(updateQuery);
            psmt.setString(1, newValue);
            psmt.setString(2, SpaceName);
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

    private static final String SERIES_DELETE_SQL = "delete from series where SpaceName=?";

    public void deleteSeries(String SpaceName) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SERIES_DELETE_SQL);
            psmt.setString(1, SpaceName);
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
