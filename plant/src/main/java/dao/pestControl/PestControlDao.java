package dao.pestControl;

import entity.pestControl.PestControl;

public interface PestControlDao {
    PestControl getPestControlById(int pestControlId);

    // 新增养护信息
    boolean addPestControl(PestControl pestControl);

    // 更新养护信息
    boolean updatePestControl(PestControl pestControl);

    // 删除养护信息
    boolean deletePestControl(int pestControlId);
}
