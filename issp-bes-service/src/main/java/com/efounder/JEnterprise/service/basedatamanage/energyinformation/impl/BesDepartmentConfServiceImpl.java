package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BesDepartmentConfMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesDepartmentConf;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BesDepartmentConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 分户配置树Impl
 *
 * @author suhang
 * @date 2018-07-19
 */
@Service("besDepartmentConfService")
public class BesDepartmentConfServiceImpl implements BesDepartmentConfService {
    private static final Logger log = LoggerFactory.getLogger(BesDepartmentConfServiceImpl.class);

    //    @Autowired
//    private ESBmjgMapper esBmjgMapper;
//    @Autowired
//
//    private BESBranchConfMapper besBranchConfMapper;
//    @Autowired
//    private BesDepartmentBranchRlglMapper besDepartmentBranchRlglMapper;
    @Autowired
    private BesDepartmentConfMapper besDepartmentConfMapper;
//    @Autowired
//    private BESParkMapper besParkMapper;
//    @Autowired
//    private BESEnergyTypeMapper besenergyTypeMapper;
//    @Autowired
//    private DepartmentConfigCache DepartmentConfigCache;
//    @Autowired
//    private DepartmentBranchRlglCache DepartmentBranchRlglCache;


    /**
     * 查询全部分户配置信息
     *
     * @return
     */
    @Override
    public List<BesDepartmentConf> findAll() {
        return besDepartmentConfMapper.findAllDepartment();
    }


    /**
     * 获取部门配置树
     *
     * @param fDepId
     * @return
     */
    @Override
    public ISSPReturnObject getDepartmentList(String fDepId) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            List<Map<String, Object>> list = besDepartmentConfMapper.getDepartmentList(fDepId);
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getfFhbh() != null && !"".equals(list.get(i).getfFhbh())) {
//                    list.get(i).setfFhbh(list.get(i).getfFhbh());
//                } else {
//                    list.get(i).setfFhbh(null);
//                }
//            }
            returnObject.setList(list);
            returnObject.setMsg("获取部门数据配置成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("获取部门数据配置失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    @Override
    public ISSPReturnObject getDepartmentAllList(String fDepId, String zzjginfo) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            List<Map<String, Object>> list = besDepartmentConfMapper.getDepartmentAllList(fDepId, zzjginfo);
            returnObject.setList(list);
            returnObject.setMsg("获取部门数据配置成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("获取部门数据配置失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 删除部门
     */
    @Override
    public ISSPReturnObject del_Department(String id) throws Exception {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        //查询删除后的系数
        List<Map<String, Object>> countList = besDepartmentConfMapper.queryDepartmentCount(id);
        if (countList.size() > 0) {
            double i = countList.size();
            double dou = 1 / i;
            //如果有其余数据则去修改系数
            if ("zl".equals(countList.get(0).get("F_TYPE"))) {
                besDepartmentConfMapper.updaeDepartmentCount(countList, dou);
            } else {
                besDepartmentConfMapper.updaeDepartmentCount1(countList, dou);
            }
        }
        //删除信息
        boolean flag = besDepartmentConfMapper.del_Department(id);
        if (flag) {
            returnObject.setMsg("删除部门成功");
            returnObject.setStatus("1");
        } else {
            returnObject.setMsg("删除部门失败");
            returnObject.setStatus("0");
        }

        return returnObject;
    }

    /**
     * 添加部门
     */
    @Override
    public ISSPReturnObject add_Department(List<Map<String, Object>> list) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (list != null) {
            //先删除数据
            for (Map map : list) {
                besDepartmentConfMapper.deleteDepartment(map.get("F_DEP_ID").toString());
                break;
            }

            boolean flag = besDepartmentConfMapper.add_Department(list);
            if (flag) {
                returnObject.setMsg("编辑部门配置成功");
                returnObject.setStatus("1");
            } else {
                returnObject.setMsg("编辑部门配置失败");
                returnObject.setStatus("0");
            }
            for (Map map : list) {
                if (map.get("F_ZLXS") != null /*&& Double.parseDouble(map.get("F_ZLXS").toString())<1*/) {
                    besDepartmentConfMapper.updateBranchCoefficient(Double.parseDouble(map.get("F_ZLXS").toString()), map.get("F_ZLBH").toString(), map.get("F_LEVEL").toString());
                }
                if (map.get("F_DBXS") != null /*&& Double.parseDouble(map.get("F_DBXS").toString())<1*/) {
                    besDepartmentConfMapper.updateAmmeterCoefficient(Double.parseDouble(map.get("F_DBXS").toString()), map.get("F_SYS_NAME").toString(), map.get("F_LEVEL").toString());
                }
            }
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }
        return returnObject;
    }

    /**
     * 查询部门
     */
    @Override
    public ISSPReturnObject queryDepartment(String fDepId) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            List<BesDepartmentConf> returnList = besDepartmentConfMapper.queryDepartment(fDepId);
            returnObject.setList(returnList);
            returnObject.setMsg("查询编辑部门信息成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("查询编辑部门信息失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 编辑部门
     */
    @Override
    public ISSPReturnObject edit_Department(BesDepartmentConf besDepartmentConf) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        String zl_id = besDepartmentConf.getfZlbh();
        String db_id = besDepartmentConf.getfDbId();
        Double zl_xs = besDepartmentConf.getfZlxs();
        Double db_xs = besDepartmentConf.getfDbxs();
        if (("".equals(zl_id) && "".equals(db_id)) || zl_xs > 1 || db_xs > 1) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        boolean flag = besDepartmentConfMapper.edit_Department(besDepartmentConf);

        if (flag) {
//            List<BesDepartmentConf> returnList = besDepartmentConfMapper.queryDepartment(besDepartmentConf.getfFhbh());
//            // 更新缓存中的数据
//            DepartmentConfigCache.updateOneDepartmentConfCache(returnList.get(0));
//            returnObject.setList(returnList);
            returnObject.setMsg("更新分户配置成功");
            returnObject.setStatus("1");
        } else {
            returnObject.setMsg("更新分户配置失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    @Override
    public List<Map<String, Object>> getSelectBranchInfoList(String bmbh, String keywords) {
        return besDepartmentConfMapper.getSelectBranchInfoList(bmbh, keywords);
    }

    @Override
    public List<Map<String, Object>> getSelectBranchInfoChoose(String bmbh,String F_LEVEL) {
        return besDepartmentConfMapper.getSelectBranchInfoChoose(bmbh,F_LEVEL);
    }

    @Override
    public List<Map<String, Object>> getSelectBranchInfoById(String fZlbh) {
        return besDepartmentConfMapper.getSelectBranchInfoById(fZlbh);
    }

    @Override
    public List<Map<String, Object>> getSelectBranchCount(String F_DEP_ID, String fZlbh,String F_LEVEL) {
        return besDepartmentConfMapper.getSelectBranchCount(F_DEP_ID, fZlbh, F_LEVEL);
    }

    @Override
    public List<Map<String, Object>> getSelectElectricityMeterInfoList(String bmbh, String keywords) {
        return besDepartmentConfMapper.getSelectElectricityMeterInfoList(bmbh, keywords);
    }

    @Override
    public List<Map<String, Object>> getSelectElectricityMeterInfoChoose(String bmbh,String F_LEVEL) {
        return besDepartmentConfMapper.getSelectElectricityMeterInfoChoose(bmbh,F_LEVEL);
    }

    @Override
    public List<Map<String, Object>> getSelectElectricityMeterInfoById(String fZlbh) {
        return besDepartmentConfMapper.getSelectElectricityMeterInfoById(fZlbh);
    }

    @Override
    public List<Map<String, Object>> getSelectElectricityMeterCount(String F_DEP_ID, String fZlbh,String F_LEVEL) {
        return besDepartmentConfMapper.getSelectElectricityMeterCount(F_DEP_ID, fZlbh, F_LEVEL);
    }

    @Override
    public Map<String, Object> getDepPeopleNumber(String F_DEP_ID) {
        return besDepartmentConfMapper.getDepPeopleNumber(F_DEP_ID);
    }

    @Override
    public ISSPReturnObject setDepPeopleNumber(String F_DEP_ID, int number) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, Object> numberMap = besDepartmentConfMapper.getDepPeopleNumber(F_DEP_ID);
        boolean flag = false;
        if(numberMap != null && numberMap.size()>0){
            flag =besDepartmentConfMapper.setDepPeopleNumberUpdate(F_DEP_ID,number);
        }else{
            flag =besDepartmentConfMapper.setDepPeopleNumber(F_DEP_ID,number);
        }
        if (flag) {
            returnObject.setMsg("编辑部门人数成功");
            returnObject.setStatus("1");
        } else {
            returnObject.setMsg("编辑部门人数失败");
            returnObject.setStatus("0");
        }

        return returnObject;
    }
}

