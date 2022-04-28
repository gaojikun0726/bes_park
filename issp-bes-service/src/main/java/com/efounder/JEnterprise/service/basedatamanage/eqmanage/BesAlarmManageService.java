package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAlarmModel;
import com.github.pagehelper.PageInfo;

public interface BesAlarmManageService {

	PageInfo<BesAlarmModel> getHistoryAlarmData(Integer pageNum,  BesAlarmModel besAlarmModel);

}
