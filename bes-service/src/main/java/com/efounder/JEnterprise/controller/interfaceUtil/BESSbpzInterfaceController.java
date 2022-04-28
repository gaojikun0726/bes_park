package com.efounder.JEnterprise.controller.interfaceUtil;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.intrerfaceUtil.BESSbpzInterfaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 移动端接口类
 *
 * @author LvSihan
 * @date 2019年7月23日
 * @version 1.0
 */
@RestController
@Api(value="BESSbpzInterfaceController",tags={"设备配置"})
@ApiSort(value = 1)
public class BESSbpzInterfaceController {
	private static final Logger logger = LoggerFactory.getLogger(BESSbpzInterfaceController.class);
	@Autowired
	private BESSbpzInterfaceService besSbpzInterfaceService;


	@ApiOperation(value="获取设备类型信息", notes="获取设备类型信息")
	@GetMapping(value = "/api/v1.0/deviceTypes")
	public ISSPReturnObject getDeviceTypes() {
		logger.info("获取设备类型信息");
		return besSbpzInterfaceService.getDeviceTypes();
	}

	@ApiOperation(value="获取设备信息", notes="获取设备信息")
	@GetMapping(value = "/api/v1.0/devices")
	public ISSPReturnObject getDevices() {
		logger.info("获取设备信息");
		return besSbpzInterfaceService.getDevices();
	}

	@ApiOperation(value="获取设备功能信息", notes="获取设备功能信息")
	@GetMapping(value = "/api/v1.0/deviceFunctions")
	public ISSPReturnObject getDeviceFunctions() {
		logger.info("获取设备功能信息");
		return besSbpzInterfaceService.getDeviceFunctions();
	}

	@ApiOperation(value="获取功能值", notes="获取功能值")
	@GetMapping(value = "/api/v1.0/functionValues")
	public ISSPReturnObject getFunctionValues() {
		logger.info("获取功能值");
		return besSbpzInterfaceService.getFunctionValues();
	}

	@ApiOperation(value="获取功能点状态", notes="获取功能点状态")
	@GetMapping(value = "/api/v1.0/functionPointStates")
	public ISSPReturnObject getFunctionPointStates(String deviceId, Integer deviceFunctionId) {
		logger.info("获取功能点状态");
		return besSbpzInterfaceService.getFunctionPointStates(deviceId, deviceFunctionId);
	}

	@ApiOperation(value="设备功能控制", notes="设备功能控制")
	@GetMapping(value = "/api/v1.0/deviceControl")
	public ISSPReturnObject setDeviceControl(String deviceId, Integer deviceFunctionId, String value) {
		logger.info("设备功能控制");
		return besSbpzInterfaceService.setDeviceControl(deviceId, deviceFunctionId, value);
	}

	@ApiOperation(value="获取电表信息", notes="获取电表信息")
	@GetMapping(value = "/api/v1.0/ammeterInfo")
	public ISSPReturnObject getAmmeterInfo() {
		logger.info("获取电表信息");
		return besSbpzInterfaceService.getAmmeterInfo();
	}


	@ApiOperation(value="所有设备配置信息", notes="获取所有设备配置信息")
	@RequestMapping(value = "/api/v1.0/getSbdy", method = RequestMethod.GET)
	public JSONObject getSbdy() {
		logger.info("获取所有设备配置信息信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> sbdyList = besSbpzInterfaceService.getSbdyList();
		dataJObject.put("data", sbdyList);
		return dataJObject;

	}
	@ApiOperation(value="所有空调配置信息", notes="获取所有空调配置信息")
	@RequestMapping(value = "/api/v1.0/getKtpzList", method = RequestMethod.GET)
	public JSONObject getKtpzList() {
		logger.info("获取所有空调配置信息信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> sbdyList = besSbpzInterfaceService.getKtpzList();
		dataJObject.put("data", sbdyList);
		return dataJObject;

	}
	@ApiOperation(value="空调配置信息", notes="获取空调配置信息")
	@RequestMapping(value = "/api/v1.0/getKtpz", method = RequestMethod.GET)
	public JSONObject getKtpz(@ApiParam(value = "空调名称", required = false) @RequestParam String ktName) {
		logger.info("获取设备配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> SingleLevel;
		try {
			SingleLevel = besSbpzInterfaceService.getKtpz(ktName);
			dataJObject.put("data", SingleLevel);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

	@ApiOperation(value="所有温控器配置信息", notes="获取所有温控器配置信息")
	@RequestMapping(value = "/api/v1.0/getWkqpzList", method = RequestMethod.GET)
	public JSONObject getWkqpzList() {
		logger.info("获取所有温控器配置信息信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> sbdyList = besSbpzInterfaceService.getWkqpzList();
		dataJObject.put("data", sbdyList);
		return dataJObject;

	}
	@ApiOperation(value="温控器配置信息", notes="获取温控器配置信息")
	@RequestMapping(value = "/api/v1.0/getWkqpz", method = RequestMethod.GET)
	public JSONObject getWkqpz(@ApiParam(value = "温控器名称", required = false) @RequestParam String wkqName) {
		logger.info("获取设备配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> SingleLevel;
		try {
			SingleLevel = besSbpzInterfaceService.getWkqpz(wkqName);
			dataJObject.put("data", SingleLevel);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

	@ApiOperation(value="所有电动窗帘配置信息", notes="获取所有电动窗帘配置信息")
	@RequestMapping(value = "/api/v1.0/getDdclList", method = RequestMethod.GET)
	public JSONObject getDdclList() {
		logger.info("获取所有电动窗帘配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> DdclList = besSbpzInterfaceService.getDdclList();
		dataJObject.put("data", DdclList);
		return dataJObject;
	}

	@ApiOperation(value="电动窗帘配置信息", notes="获取电动窗帘信息")
	@RequestMapping(value = "/api/v1.0/getDdcl", method = RequestMethod.GET)
	public JSONObject getDdcl(@ApiParam(value = "窗帘名称", required = false) @RequestParam String CLName) {
		logger.info("获取设备配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> SingleLevel;
		try {
			SingleLevel = besSbpzInterfaceService.getDdcl(CLName);
			dataJObject.put("data", SingleLevel);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}


	@ApiOperation(value="设备配置信息", notes="获取设备配置信息")
	@RequestMapping(value = "/api/v1.0/getSingleLevel", method = RequestMethod.GET)
	public JSONObject getSingleLevel(@ApiParam(value = "系统名称", required = false) @RequestParam String fSysName) {
		logger.info("获取设备配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> SingleLevel;
		try {
			SingleLevel = besSbpzInterfaceService.getSingleLevel(fSysName);
			dataJObject.put("data", SingleLevel);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

	@ApiOperation(value="获取设备实时值", notes="获取设备实时值")
	@RequestMapping(value = "/api/v1.0/getRealTime", method = RequestMethod.GET)
	public ISSPReturnObject getRealTime(@ApiParam(value = "系统名称", required = false) @RequestParam String fSysName) {
		logger.info("获取设备实时值");
		return besSbpzInterfaceService.getRealTime(fSysName);
	}

	@ApiOperation(value="设备配置父级信息", notes="获取设备配置父级信息")
	@RequestMapping(value = "/api/v1.0/getPLevel", method = RequestMethod.GET)
	public JSONObject getPLevel(@ApiParam(value = "系统名称", required = false) @RequestParam String F_PSYS_NAME) {
		logger.info("获取设备配置父级信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> pLevel;
		try {
			pLevel = besSbpzInterfaceService.getPLevel(F_PSYS_NAME);
			dataJObject.put("data", pLevel);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

	@ApiOperation(value="监控界面信息", notes="获取监控界面信息")
	@RequestMapping(value = "/api/v1.0/getMonitoring", method = RequestMethod.GET)
	public JSONObject getMonitoring() {
		logger.info("获取监控界面信息");
		JSONObject dataJObject = new JSONObject();
		try {
			List<Map<String, Object>> SingleLevel = besSbpzInterfaceService.getMonitoring();
			dataJObject.put("data", SingleLevel);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}


	@ApiOperation(value="所有新风配置信息", notes="所有新风配置信息")
	@RequestMapping(value = "/api/v1.0/getFreshairconfigList", method = RequestMethod.GET)
	public JSONObject getFreshairconfigList() {
		logger.info("获取所有新风配置信息信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> sbdyList = besSbpzInterfaceService.getFreshairconfigList();
		dataJObject.put("data", sbdyList);
		return dataJObject;
	}

	@ApiOperation(value="新风配置信息", notes="新风配置信息")
	@RequestMapping(value = "/api/v1.0/getFreshairconfig", method = RequestMethod.GET)
	public JSONObject getFreshairconfig(@ApiParam(value = "新风名称", required = false) @RequestParam String windName) {
		logger.info("获取新风配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> SingleLevel;
		try {
			SingleLevel = besSbpzInterfaceService.getFreshairconfig(windName);
			dataJObject.put("data", SingleLevel);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}


	@ApiOperation(value="灯光配置信息", notes="获取灯光配置信息")
	@RequestMapping(value = "/api/v1.0/getLight", method = RequestMethod.GET)
	public JSONObject getLight(@ApiParam(value = "灯光名称", required = false) @RequestParam String lightName) {
		logger.info("获取灯光配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> lightList;
		try {
			lightList = besSbpzInterfaceService.getLight(lightName);
			dataJObject.put("data", lightList);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

    @ApiOperation(value="所有灯光配置信息", notes="获取所有灯光配置信息")
    @RequestMapping(value = "/api/v1.0/getLightList", method = RequestMethod.GET)
    public JSONObject getLightList() {
        logger.info("获取所有灯光配置信息");
        JSONObject dataJObject = new JSONObject();
        List<Map<String,Object>> lightList = besSbpzInterfaceService.getLightList();
        dataJObject.put("data", lightList);
        return dataJObject;

    }

	@ApiOperation(value="可调光灯光配置信息", notes="获取可调光灯光配置信息")
	@RequestMapping(value = "/api/v1.0/getTgLight", method = RequestMethod.GET)
	public JSONObject getTgLight(@ApiParam(value = "可调光灯光名称", required = false) @RequestParam String lightName) {
		logger.info("获取可调光灯光配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> lightList;
		try {
			lightList = besSbpzInterfaceService.getTgLight(lightName);
			dataJObject.put("data", lightList);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

	@ApiOperation(value="所有可调光灯光配置信息", notes="获取所有可调光灯光配置信息")
	@RequestMapping(value = "/api/v1.0/getTgLightList", method = RequestMethod.GET)
	public JSONObject getTgLightList() {
		logger.info("获取所有可调光灯光配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> lightList = besSbpzInterfaceService.getTgLightList();
		dataJObject.put("data", lightList);
		return dataJObject;

	}

	@ApiOperation(value="插座配置信息", notes="获取插座配置信息")
	@RequestMapping(value = "/api/v1.0/getSocket", method = RequestMethod.GET)
	public JSONObject getSocket(@ApiParam(value = "插座名称", required = false) @RequestParam String socketName) {
		logger.info("获取插座配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> socketList;
		try {
			socketList = besSbpzInterfaceService.getSocket(socketName);
			dataJObject.put("data", socketList);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

	@ApiOperation(value="所有插座配置信息", notes="获取所有插座配置信息")
	@RequestMapping(value = "/api/v1.0/getSocketList", method = RequestMethod.GET)
	public JSONObject getSocketList() {
		logger.info("获取所有插座配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> socketList = besSbpzInterfaceService.getSocketList();
		dataJObject.put("data", socketList);
		return dataJObject;

	}

	@ApiOperation(value="组合式空调机组配置信息", notes="获取组合式空调机组配置信息")
	@RequestMapping(value = "/api/v1.0/getAirconditioningUnit", method = RequestMethod.GET)
	public JSONObject getAirconditioningUnit(@ApiParam(value = "组合式空调机组名称", required = false) @RequestParam String airconditioningUnitName) {
		logger.info("获取组合式空调机组配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> airconditioningUnitList;
		try {
			airconditioningUnitList = besSbpzInterfaceService.getAirconditioningUnit(airconditioningUnitName);
			dataJObject.put("data", airconditioningUnitList);
			dataJObject.put("status", "1");
			dataJObject.put("msg", "成功");
		} catch (Exception e) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "失败");
			e.printStackTrace();
		}
		return dataJObject;
	}

	@ApiOperation(value="所有组合式空调机组配置信息", notes="获取所有组合式空调机组配置信息")
	@RequestMapping(value = "/api/v1.0/getAirconditioningUnitList", method = RequestMethod.GET)
	public JSONObject getAirconditioningUnitList() {
		logger.info("获取所有组合式空调机组配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String,Object>> airconditioningUnitList = besSbpzInterfaceService.getAirconditioningUnitList();
		dataJObject.put("data", airconditioningUnitList);
		return dataJObject;

	}


	@ApiOperation(value="设备配置信息状态获取", notes="获取配置信息状态")
	@RequestMapping(value = "/api/v1.0/getAlloffList", method = RequestMethod.GET)
	public JSONObject getAlloffList(@ApiParam(value = "系统名称", required = false) @RequestParam String systemName) {
		logger.info("获取灯光配置信息");
		JSONObject dataJObject = new JSONObject();
		List<Map<String, Object>> lightList;
		if(("").equals(systemName) || null == systemName || systemName.equals("null")) {
			dataJObject.put("status", "0");
			dataJObject.put("msg", "请输入系统名称");
		}else {
			try {
				lightList = besSbpzInterfaceService.getAlloffList(systemName);
				dataJObject.put("data", lightList);
				dataJObject.put("status", "1");
				dataJObject.put("msg", "成功");
			} catch (Exception e) {
				dataJObject.put("status", "0");
				dataJObject.put("msg", "失败");
				e.printStackTrace();
			}
		}

		return dataJObject;
	}




}
