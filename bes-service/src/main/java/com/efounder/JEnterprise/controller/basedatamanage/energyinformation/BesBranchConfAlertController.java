package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.core.common.json.JsonHelper;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.BranchConfAlarmConfigurationCache;
import com.efounder.JEnterprise.initializer.BranchConfParameterCache;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesBranchConfAlert;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfAlarmConfiguration;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESPark_EnergyTypeService;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BesBranchConfAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 支路报警controller
 * @author sunzhiyuan
 * @datetime 2018-7-31
 *
 */
@RequestMapping(value = "/view/basedatamanage/energyinformation")
@Controller
public class BesBranchConfAlertController {
	private static final Logger log = LoggerFactory.getLogger(BesBranchConfAlertController.class);
	@Autowired
	private BesBranchConfAlertService besBranchConfAlertService;
	@Autowired
	private BESPark_EnergyTypeService besParkEnergyTypeservice;

	//支路报警配置缓存
	@Resource
	private BranchConfAlarmConfigurationCache besBranchConfAlarmConfigurationCache;

	//支路报警规则
	@Resource
	private BranchConfParameterCache branchConfParameterCache;

	/**
	 * 初始化主界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getalertConf", method = RequestMethod.GET)
	public String getalertConf() {
		return "view/basedatamanage/energyinformation/branchalertconf";
	}
	
	/**
	 * 获取支路报警数据
	 * @param besBranchConfAlert
	 * @return
	 */
	@RequestMapping(value = "/getAlertList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAlertList(BesBranchConfAlert besBranchConfAlert) {
		log.info("besBranchConfAlert获取支路报警配置数据");
		//统一返回格式
		ISSPReturnObject returnObject = besBranchConfAlertService.getAlertList(besBranchConfAlert);
		return returnObject;
	}
	
	/**
	 * 生成园区下拉框
	 * @return returnObject
	 */
	
	@RequestMapping(value = "/park_tree" , method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject Park_tree() {
		log.info("# 生成园区下拉框" );
		ISSPReturnObject returnObject = besBranchConfAlertService.Park_tree();
		return returnObject;
	}
	

	/**
	 * 生成能源下拉框
	 * @return returnObject
	 */
	
	@RequestMapping(value = "/getAllEnergyType" , method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getAllEnergyType() {
		log.info("# 生成能源下拉框" );
		ISSPReturnObject returnObject = besBranchConfAlertService.getAllEnergyType();
		return returnObject;
	}
	
	/**
	 * 生成支路配置树
	 * @return
	 */
	@RequestMapping(value = "/branchalert_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getbranchTree(String fYqbh ,String fNybh) {
		log.info("生成支路树获取信息");
		ISSPReturnObject returnObject = besBranchConfAlertService.getbranchTree(fYqbh, fNybh);
		return returnObject;
	}
	/**
	 *
	 * @Description: 生成电表树获取信息
	 *
	 * @auther: wanghongjie sunzhiyuan xiugai
	 * @date: 16:45 2020/5/29
	 * @param: [fYqbh, fNybh, resp]
	 * @return: void
	 *
	 */
	@RequestMapping(value = "/branchalert_ammeter", method = RequestMethod.POST)
	@ResponseBody
	public void branchalert_ammeter(String fYqbh , String fNybh, HttpServletResponse resp) {
		log.info("生成电表树获取信息");
		PrintWriter out = null;
		try{
			resp.setContentType("text/html;charset=utf-8");
			out = resp.getWriter();
			ISSPReturnObject returnObject = besBranchConfAlertService.getbranchalert_ammeter(fYqbh, fNybh);
			String jsonString = JsonHelper.encodeObject2Json(returnObject.getList());

			// 传到前台js
			out.write(jsonString);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	@RequestMapping(value = "/branchalert_ammeter_rlgl", method = RequestMethod.POST)
	@ResponseBody
	public void branchalert_ammeter_rlgl(String parentId , HttpServletResponse resp) {
		log.info("生成支路树获取信息");
		PrintWriter out = null;
		try{
			resp.setContentType("text/html;charset=utf-8");
			out = resp.getWriter();
			ISSPReturnObject returnObject = besBranchConfAlertService.getbranchalert_ammeter_rlgl(parentId);
			String jsonString = JsonHelper.encodeObject2Json(returnObject.getList());

			// 传到前台js
			out.write(jsonString);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}
	/**
	 * 查找子类
	 * @param fZlbh 支路编号
	 * @param yqbh 园区编号
	 * @param nybh 能源编号
	 * @return
	 */
	@RequestMapping(value = "/branchalert_chlildtreegrid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject branchalert_chlildtreegrid (String fZlbh, String yqbh, String nybh, String searchInfo) {
		log.info("BESSubitemConfController查找支路报警子类");
		//统一返回格式
		ISSPReturnObject returnObject = besBranchConfAlertService.branchalert_chlildtreegrid(fZlbh,yqbh,nybh,searchInfo);
		return returnObject;
	}
	
	/**
	 * 添加支路报警 孙志远
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/insbranchalert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject insbranchalert (@RequestBody BesBranchConfAlert besBranchConfAlert) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String uuid = UUIDUtil.getRandom32BeginTimePK();
		log.info("新增配置");
		String Operator= besBranchConfAlert.getfOperator();
		String date = DateUtil.getCurrTime();
		besBranchConfAlert.setfId(uuid);
		String fCrdate = date;
		String fChdate = date;
		besBranchConfAlert.setfCrdate(fCrdate);
		besBranchConfAlert.setfChdate(fChdate);
		Boolean op = Operator.contains("$");

		String name = besBranchConfAlert.getfAlertname();

		List<Object> list = besBranchConfAlertService.selectFalertname(name);

		if (list.size()<=0){
			if (op){

				besBranchConfAlertService.insbranchalert(besBranchConfAlert);

				Map<String,Object> paraMap = new HashMap<>();

				String str1 = besBranchConfAlert.getfOperator();

				String[] fsortno = besBranchConfAlert.getFsortno();

				String[] pId1 = besBranchConfAlert.getpId();

				String[] strs = str1.split("\\$");

				String fparameter = besBranchConfAlert.getFparameter();

				String[] str = fparameter.split(",");

				for (int i = 0;i <strs.length-1; i++){

//					String fDnbh = besBranchConfAlertService.getDnbhbyParams(str[i]);

					paraMap.put("F_ALERTID",uuid);

					paraMap.put("F_SORTNO",i);

					String elednbh = fsortno[i];

					if (pId1[i] == null){
						paraMap.put("F_ELEDNBH",10000);
						paraMap.put("F_AMMSYS_NAME",str[i]);//电表id
					}else {
						paraMap.put("F_ELEDNBH",fsortno[i]);
						paraMap.put("F_AMMSYS_NAME",pId1[i]);//电表id
					}

					paraMap.put("F_CRDATE",fCrdate);

					paraMap.put("F_CHDATE",fCrdate);

					besBranchConfAlertService.insertAlertParameter(paraMap);

				}
				if (paraMap.size()>0){
					returnObject.setStatus("1");
					returnObject.setMsg("报警信息插入成功");
					besBranchConfAlarmConfigurationCache.loadCache();
					branchConfParameterCache.loadCache();
				}else {
					returnObject.setStatus("0");
					returnObject.setMsg("报警信息插入失败");
				}
			}else {
				returnObject.setStatus("0");
				returnObject.setMsg("字符输入有误");
			}
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("不允许输入相同的报警名称！");
		}
		return returnObject;
	}
	
	/**
	 * 删除分户
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/del_branchalert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_branchalert (String fId) {
		log.info("删除分项配置");
		ISSPReturnObject returnObject = besBranchConfAlertService.del_branchalert(fId);
		return returnObject;
	}
	
	/**
	 * 支路报警单行查询
	 * 
	 * @param fId
	 * @return
	 */
	@RequestMapping(value = "/selbranchalert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selbranchalert(String fId) {
		log.info("查询支路报警信息");
		return besBranchConfAlertService.selbranchalert(fId);
	}

	/**
	 * 更新支路报警信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updbranchalert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updbranchalert(@RequestBody BesBranchConfAlert besBranchConfAlert) {
		log.info("更新支路报警信息");
		ISSPReturnObject returnObject = besBranchConfAlertService.updbranchalert(besBranchConfAlert);
		String date = DateUtil.getCurrTime();
		String fChdate = date;
		besBranchConfAlert.setfChdate(fChdate);
		return returnObject;
	}
	
	/**
	 * 传入园区编号查询相关能源信息下拉框
	 * @param f_yqbh
	 * @return
	 */
	@RequestMapping(value = "/getgllist" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getgllist(String f_yqbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#传入园区编号查询相关能源信息");
		 List<Map<String,Object>> getgllist = besParkEnergyTypeservice.getgllist(f_yqbh);
		    returnObject.setList(getgllist);
		    return returnObject;
	}

	/**
	 * 生成设备定义树（未格式化）
	 * @return
	 */
	@RequestMapping(value = "/loadOrganizationTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadOrganizationTree(String fZlbh){
		log.info("BESSbdyController生成单独树结构");
		ISSPReturnObject returnObject = besBranchConfAlertService.loadOrganization(fZlbh);
		return  returnObject;
	}

	/**
	 * 获取参数id
	 * @param
	 * @return
	 */
//	@RequestMapping(value = "/getCanshuId", method = RequestMethod.POST)
//	@ResponseBody
//	public  Map<Integer, String> getCanshuId(@RequestParam (value = "fsortno[]" ) String fsortno){
//		//根据逗号截取字符串数组
//		String[] str1 = fsortno.split(",");
//		BesBranchConfAlert besBranchConfAlert = new BesBranchConfAlert();
//		Map<Integer, String> map = new HashMap<>();
//		for (int i = 0;i<str1.length;i++){
//			map.put(i,str1[1]);
//			besBranchConfAlert.setFsortno(str1[1]);
//		}
//		return  besBranchConfAlertService.insertParameter(fsortno);
//	}

//	/**
//	 * 接受数据 并处理
//	 * @param besBranchConfAlert
//	 * @param
//	 * @return
//	 */
//	public Map<String,Object> getAlertParameter(BesBranchConfAlert besBranchConfAlert){
//
//		Map<String,Object> map = (Map<String, Object>) insbranchalert(besBranchConfAlert).getMap();
//
//		String F_SORTNO = besBranchConfAlert.getFsortno();
//		return null;
//	}

	/**
	 * 报警名称验重
	 * @param alertName
	 * @return
	 */
	@RequestMapping(value = "/selectAlertNameRepeat", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectAlertNameRepeat(String alertName){
		log.info("报警名称验重");
		ISSPReturnObject returnObject = besBranchConfAlertService.selectAlertNameRepeat(alertName);
		return returnObject;
	}

	/**
	 * 报警名称验重
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getAlertTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAlertTypeInfo(){
		log.info("获取报警类型数据");
		return besBranchConfAlertService.getAlertTypeInfo();
	}

}
