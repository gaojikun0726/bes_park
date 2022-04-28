package com.efounder.JEnterprise.controller;

import com.efounder.JEnterprise.model.BesPzlj;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class GHEEViewController {

    private static final Logger log = LoggerFactory.getLogger(GHEEViewController.class);
    
	@Autowired
	private BesPzlj bespz;

    @Autowired
    private BesPzlj bespzlj;
    
	@RequestMapping(value = "view/main-index", method = RequestMethod.GET)
    String mainIdex(ModelMap model) {
    	log.info("# loding view/main-index");
    	//qflb  区分类别   1(能耗)   2(Cross)  3(整合)
    	String qflb=bespz.getPZLJ();
        String runingTime = bespz.getRuningTime();// 系统运行开始时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String currentTime = df.format(new Date());// 当前时间
        String DistanceTime = getDistanceTime(runingTime, currentTime);// 两个时间相差距离多少天多少小时，以String形式返回
        String[] arr = DistanceTime.split("@");
        String day = arr[0];
        String hour = arr[1];
        String return_url = "";
    	switch (qflb) {
        case "0":
            return_url = "view/newmain-index";
            break;
		case "1":
			return_url="view/main-index";
			break;
		case "2":
			return_url="view/main-index-cross";
			break;
		case "3":
			return_url="view/main-index-integration";
			break;
		default:
			return_url="view/main-index";
			break;
		}
        model.addAttribute("day", day);
        model.addAttribute("hour", hour);
    	return return_url;
    }

    public static void main(String[] args) {
        System.err.println(getDistanceTime("2019-04-11 12:00:00", "2024-07-18 12:00:00"));
    }
    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String starttime, String endtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(starttime);
            two = df.parse(endtime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "@" + hour;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年12月20日 上午11:45:05
     * @Description:token验证通过大屏展示
     * @param model
     * @return String
     */
    @RequestMapping(value = "/bes/npsLogin", method = RequestMethod.GET)
    String mainIdex11(ModelMap model) {
        log.info("# loding view/ScreenDisplayMainIndex");
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        if (currentUser.isAuthenticated()) {
            String runingTime = bespz.getRuningTime();// 系统运行开始时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
            String currentTime = df.format(new Date());// 当前时间
            String DistanceTime = getDistanceTime(runingTime, currentTime);// 两个时间相差距离多少天多少小时，以String形式返回
            String[] arr = DistanceTime.split("@");
            String day = arr[0];
            String hour = arr[1];
            model.addAttribute("day", day);
            model.addAttribute("hour", hour);
            return "view/ScreenDisplayMainIndex";
        } else {
            token.clear();
            return "redirect:/login";
        }

    }
}
