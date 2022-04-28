package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig;

import java.io.Serializable;

/**
 *
 * 冷却塔配置
 *
 * @author xiepufeng
 *
 */
public class CoolingTowerConfigModel implements Serializable {

    private static final long serialVersionUID = 446551219300583489L;
    private Integer  id;                             // 主键
    private String   name;                           // 冷却塔名称
    private String   fanSwitch;                      // 风机启停
    private String   waterValveOpen;                 // 水阀开
    private String   waterValveClose;                // 水阀关
    private String   inletValveOpenArrives;          // 进水阀开到位
    private String   inletValveCloseArrives;         // 进水阀关到位
    private String   outletValveOpenArrives;         // 出水阀开到位
    private String   outletValveCloseArrives;        // 出水阀关到位
    private String   inletValveFault;                // 进水阀故障
    private String   outletValveFault;               // 出水阀故障
    private String   fanFault;                       // 风机故障
    private String   runningStatus;                  // 运行状态
    private String   fanSwitchDisplay;               // 风机启停显示名称
    private String   waterValveOpenDisplay;          // 水阀开显示名称
    private String   waterValveCloseDisplay;         // 水阀关显示名称
    private String   inletValveOpenArrivesDisplay;   // 进水阀开到位显示名称
    private String   inletValveCloseArrivesDisplay;  // 进水阀关到位显示名称
    private String   outletValveOpenArrivesDisplay;  // 出水阀开到位显示名称
    private String   outletValveCloseArrivesDisplay; // 出水阀关到位显示名称
    private String   inletValveFaultDisplay;         // 进水阀故障显示名称
    private String   outletValveFaultDisplay;        // 出水阀故障显示名称
    private String   fanFaultDisplay;                // 风机故障显示名称
    private String   runningStatusDisplay;           // 运行状态显示名称
    private String   electric_meter_number;          //电表的id
    private Integer    ids;                    // 主键
    private String     names;                 // 电表名
    private String     cabinet_name;          // 机柜名称
    private String     instant_energy;        // 瞬时能耗
    private String     total_energy;          // 累计能耗
    private String     a_phase_voltage;        // A相电流
    private String     b_phase_voltage;        // B相电流
    private String     c_phase_voltage;        // C相电流
    private String     a_phase_current;        // A相电压
    private String     b_phase_current;        // B相电压
    private String     c_phase_current;        // C相电压
    private String     instant_energy_display; // 瞬时能耗显示名称
    private String     total_energy_display;   // 累计能耗显示名称
    private String     a_phase_voltage_display; // A相电流显示名称
    private String     b_phase_voltage_display; // B相电流显示名称
    private String     c_phase_voltage_display; // C相电流显示名称
    private String     a_phase_current_display; // A相电压显示名称
    private String     b_phase_current_display; // B相电压显示名称
    private String     c_phase_current_display; // C相电压显示名称

    private String operatingMode;           //工作模式
    private String faultState;              //故障状态
    private String operationHours;          //运行时间

    private String operatingModeDisplay;    //工作模式显示名称
    private String faultStateDisplay;       //故障状态显示名称
    private String operationHoursDisplay;   //运行时间显示名称


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFanSwitch() {
        return fanSwitch;
    }

    public void setFanSwitch(String fanSwitch) {
        this.fanSwitch = fanSwitch;
    }

    public String getWaterValveOpen() {
        return waterValveOpen;
    }

    public void setWaterValveOpen(String waterValveOpen) {
        this.waterValveOpen = waterValveOpen;
    }

    public String getWaterValveClose() {
        return waterValveClose;
    }

    public void setWaterValveClose(String waterValveClose) {
        this.waterValveClose = waterValveClose;
    }

    public String getInletValveOpenArrives() {
        return inletValveOpenArrives;
    }

    public void setInletValveOpenArrives(String inletValveOpenArrives) {
        this.inletValveOpenArrives = inletValveOpenArrives;
    }

    public String getInletValveCloseArrives() {
        return inletValveCloseArrives;
    }

    public void setInletValveCloseArrives(String inletValveCloseArrives) {
        this.inletValveCloseArrives = inletValveCloseArrives;
    }

    public String getOutletValveOpenArrives() {
        return outletValveOpenArrives;
    }

    public void setOutletValveOpenArrives(String outletValveOpenArrives) {
        this.outletValveOpenArrives = outletValveOpenArrives;
    }

    public String getOutletValveCloseArrives() {
        return outletValveCloseArrives;
    }

    public void setOutletValveCloseArrives(String outletValveCloseArrives) {
        this.outletValveCloseArrives = outletValveCloseArrives;
    }

    public String getInletValveFault() {
        return inletValveFault;
    }

    public void setInletValveFault(String inletValveFault) {
        this.inletValveFault = inletValveFault;
    }

    public String getOutletValveFault() {
        return outletValveFault;
    }

    public void setOutletValveFault(String outletValveFault) {
        this.outletValveFault = outletValveFault;
    }

    public String getFanFault() {
        return fanFault;
    }

    public void setFanFault(String fanFault) {
        this.fanFault = fanFault;
    }

    public String getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(String runningStatus) {
        this.runningStatus = runningStatus;
    }

    public String getFanSwitchDisplay() {
        return fanSwitchDisplay;
    }

    public void setFanSwitchDisplay(String fanSwitchDisplay) {
        this.fanSwitchDisplay = fanSwitchDisplay;
    }

    public String getWaterValveOpenDisplay() {
        return waterValveOpenDisplay;
    }

    public void setWaterValveOpenDisplay(String waterValveOpenDisplay) {
        this.waterValveOpenDisplay = waterValveOpenDisplay;
    }

    public String getWaterValveCloseDisplay() {
        return waterValveCloseDisplay;
    }

    public void setWaterValveCloseDisplay(String waterValveCloseDisplay) {
        this.waterValveCloseDisplay = waterValveCloseDisplay;
    }

    public String getInletValveOpenArrivesDisplay() {
        return inletValveOpenArrivesDisplay;
    }

    public void setInletValveOpenArrivesDisplay(String inletValveOpenArrivesDisplay) {
        this.inletValveOpenArrivesDisplay = inletValveOpenArrivesDisplay;
    }

    public String getInletValveCloseArrivesDisplay() {
        return inletValveCloseArrivesDisplay;
    }

    public void setInletValveCloseArrivesDisplay(String inletValveCloseArrivesDisplay) {
        this.inletValveCloseArrivesDisplay = inletValveCloseArrivesDisplay;
    }

    public String getOutletValveOpenArrivesDisplay() {
        return outletValveOpenArrivesDisplay;
    }

    public void setOutletValveOpenArrivesDisplay(String outletValveOpenArrivesDisplay) {
        this.outletValveOpenArrivesDisplay = outletValveOpenArrivesDisplay;
    }

    public String getOutletValveCloseArrivesDisplay() {
        return outletValveCloseArrivesDisplay;
    }

    public void setOutletValveCloseArrivesDisplay(String outletValveCloseArrivesDisplay) {
        this.outletValveCloseArrivesDisplay = outletValveCloseArrivesDisplay;
    }

    public String getInletValveFaultDisplay() {
        return inletValveFaultDisplay;
    }

    public void setInletValveFaultDisplay(String inletValveFaultDisplay) {
        this.inletValveFaultDisplay = inletValveFaultDisplay;
    }

    public String getOutletValveFaultDisplay() {
        return outletValveFaultDisplay;
    }

    public void setOutletValveFaultDisplay(String outletValveFaultDisplay) {
        this.outletValveFaultDisplay = outletValveFaultDisplay;
    }

    public String getFanFaultDisplay() {
        return fanFaultDisplay;
    }

    public void setFanFaultDisplay(String fanFaultDisplay) {
        this.fanFaultDisplay = fanFaultDisplay;
    }

    public String getRunningStatusDisplay() {
        return runningStatusDisplay;
    }

    public void setRunningStatusDisplay(String runningStatusDisplay) {
        this.runningStatusDisplay = runningStatusDisplay;
    }

    public String getCabinet_name() {
        return cabinet_name;
    }

    public void setCabinet_name(String cabinet_name) {
        this.cabinet_name = cabinet_name;
    }

    public String getInstant_energy() {
        return instant_energy;
    }

    public void setInstant_energy(String instant_energy) {
        this.instant_energy = instant_energy;
    }

    public String getTotal_energy() {
        return total_energy;
    }

    public void setTotal_energy(String total_energy) {
        this.total_energy = total_energy;
    }

    public String getA_phase_voltage() {
        return a_phase_voltage;
    }

    public void setA_phase_voltage(String a_phase_voltage) {
        this.a_phase_voltage = a_phase_voltage;
    }

    public String getB_phase_voltage() {
        return b_phase_voltage;
    }

    public void setB_phase_voltage(String b_phase_voltage) {
        this.b_phase_voltage = b_phase_voltage;
    }

    public String getC_phase_voltage() {
        return c_phase_voltage;
    }

    public void setC_phase_voltage(String c_phase_voltage) {
        this.c_phase_voltage = c_phase_voltage;
    }

    public String getA_phase_current() {
        return a_phase_current;
    }

    public void setA_phase_current(String a_phase_current) {
        this.a_phase_current = a_phase_current;
    }

    public String getB_phase_current() {
        return b_phase_current;
    }

    public void setB_phase_current(String b_phase_current) {
        this.b_phase_current = b_phase_current;
    }

    public String getC_phase_current() {
        return c_phase_current;
    }

    public void setC_phase_current(String c_phase_current) {
        this.c_phase_current = c_phase_current;
    }

    public String getInstant_energy_display() {
        return instant_energy_display;
    }

    public void setInstant_energy_display(String instant_energy_display) {
        this.instant_energy_display = instant_energy_display;
    }

    public String getTotal_energy_display() {
        return total_energy_display;
    }

    public void setTotal_energy_display(String total_energy_display) {
        this.total_energy_display = total_energy_display;
    }

    public String getA_phase_voltage_display() {
        return a_phase_voltage_display;
    }

    public void setA_phase_voltage_display(String a_phase_voltage_display) {
        this.a_phase_voltage_display = a_phase_voltage_display;
    }

    public String getB_phase_voltage_display() {
        return b_phase_voltage_display;
    }

    public void setB_phase_voltage_display(String b_phase_voltage_display) {
        this.b_phase_voltage_display = b_phase_voltage_display;
    }

    public String getC_phase_voltage_display() {
        return c_phase_voltage_display;
    }

    public void setC_phase_voltage_display(String c_phase_voltage_display) {
        this.c_phase_voltage_display = c_phase_voltage_display;
    }

    public String getA_phase_current_display() {
        return a_phase_current_display;
    }

    public void setA_phase_current_display(String a_phase_current_display) {
        this.a_phase_current_display = a_phase_current_display;
    }

    public String getB_phase_current_display() {
        return b_phase_current_display;
    }

    public void setB_phase_current_display(String b_phase_current_display) {
        this.b_phase_current_display = b_phase_current_display;
    }

    public String getC_phase_current_display() {
        return c_phase_current_display;
    }

    public void setC_phase_current_display(String c_phase_current_display) {
        this.c_phase_current_display = c_phase_current_display;
    }

    public String getElectric_meter_number() {
        return electric_meter_number;
    }

    public void setElectric_meter_number(String electric_meter_number) {
        this.electric_meter_number = electric_meter_number;
    }

    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getOperatingMode() {
        return operatingMode;
    }

    public void setOperatingMode(String operatingMode) {
        this.operatingMode = operatingMode;
    }

    public String getFaultState() {
        return faultState;
    }

    public void setFaultState(String faultState) {
        this.faultState = faultState;
    }

    public String getOperationHours() {
        return operationHours;
    }

    public void setOperationHours(String operationHours) {
        this.operationHours = operationHours;
    }

    public String getOperatingModeDisplay() {
        return operatingModeDisplay;
    }

    public void setOperatingModeDisplay(String operatingModeDisplay) {
        this.operatingModeDisplay = operatingModeDisplay;
    }

    public String getFaultStateDisplay() {
        return faultStateDisplay;
    }

    public void setFaultStateDisplay(String faultStateDisplay) {
        this.faultStateDisplay = faultStateDisplay;
    }

    public String getOperationHoursDisplay() {
        return operationHoursDisplay;
    }

    public void setOperationHoursDisplay(String operationHoursDisplay) {
        this.operationHoursDisplay = operationHoursDisplay;
    }
}
