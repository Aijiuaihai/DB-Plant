import java.util.List;

public interface seriesDAO {
    series getSeriesBySpaceName(String SpaceName);
    void addSeries(series series);
    void updateSeries(String SpaceName, int attribute, String newValue);
    void deleteSeries(String SpaceName);
    List<series> getSeriesByGenusName(String genusName);
    List<series> getSeriesByFamilyName(String familyName);
    List<series> getSeriesByCity(String city);
}
