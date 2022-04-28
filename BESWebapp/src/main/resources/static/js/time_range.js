/**
 * 日期范围工具类
 */

function getPreviousDate() {
	var date = new Date();
	var hm = (Date.parse(date) - (3600 * 1000 * 24));
	date.setTime(hm);
	return date;
}
/*******************************************************************************
 * 获得当前时间
 */
function getCurrentDate() {
	return new Date();
}
//获取昨天的时间
function getLestDayDate() {
	let currentDate = this.getCurrentDate();
	currentDate.setTime(currentDate.getTime()-24*60*60*1000);
	return currentDate.getFullYear()+"-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate();
}

/*******************************************************************************
 * 得到去年的当前时间
 ******************************************************************************/
function getPreviouDate() {
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	currentYear--;
	// 返回当前时间的月
	var month = currentDate.getMonth();
	// 返回当前时间的天
	var day = currentDate.getDate();
	var priorDay=new Date(currentYear,month,day);
	return priorDay;
}

/*******************************************************************************
 * 获得本周起止时间
 */
function getCurrentWeek() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 返回date是一周中的某一天
	var week = currentDate.getDay();
	// 返回date是一个月中的某一天
	var month = currentDate.getDate();

	// 一天的毫秒数
	var millisecond = 1000 * 60 * 60 * 24;
	// 减去的天数
	var minusDay = week != 0 ? week - 1 : 6;
	// alert(minusDay);
	// 本周 周一
	var monday = new Date(currentDate.getTime() - (minusDay * millisecond));
	// 本周 周日
	var sunday = new Date(monday.getTime() + (6 * millisecond));
	// 添加本周时间
	startStop.push(monday); // 本周起始时间
	// 添加本周最后一天时间
	startStop.push(sunday); // 本周终止时间
	// 返回
	return startStop;
}

/*******************************************************************************
 * 获得本月的起止时间
 */
function getCurrentMonth() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前月份0-11
	var currentMonth = currentDate.getMonth();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	// 求出本月第一天
	var firstDay = new Date(currentYear, currentMonth, 1);

	// 当为12月的时候年份需要加1
	// 月份需要更新为0 也就是下一年的第一个月
	if (currentMonth == 11) {
		currentYear++;
		currentMonth = 0; // 就为
	} else {
		// 否则只是月份增加,以便求的下一月的第一天
		currentMonth++;
	}

	// 一天的毫秒数
	var millisecond = 1000 * 60 * 60 * 24;
	// 下月的第一天
	var nextMonthDayOne = new Date(currentYear, currentMonth, 1);
	// 求出上月的最后一天
	var lastDay = new Date(nextMonthDayOne.getTime() - millisecond);

	// 添加至数组中返回
	startStop.push(firstDay);
	startStop.push(lastDay);
	// 返回
	return startStop;
}

/*******************************************************************************
 * 得到本季度开始的月份
 * 
 * @param month
 *            需要计算的月份
 ******************************************************************************/
function getQuarterSeasonStartMonth(month) {
	var quarterMonthStart = 0;
	var spring = 11; // 春
	var summer = 2; // 夏
	var fall = 5; // 秋
	var winter = 8; // 冬
	// 月份从0-11
	if (month < 2 || month > 10) {
		return spring;
	}

	if (month < 5) {
		return summer;
	}

	if (month < 8) {
		return fall;
	}

	return winter;
}

/**
 * 获得该月的天数
 * 
 * @param year年份
 * @param month月份
 */
function getMonthDays(year, month) {
	// 本月第一天 1-31
	var relativeDate = new Date(year, month, 1);
	// 获得当前月份0-11
	var relativeMonth = relativeDate.getMonth();
	// 获得当前年份4位年
	var relativeYear = relativeDate.getFullYear();

	// 当为12月的时候年份需要加1
	// 月份需要更新为0 也就是下一年的第一个月
	if (relativeMonth == 11) {
		relativeYear++;
		relativeMonth = 0;
	} else {
		// 否则只是月份增加,以便求的下一月的第一天
		relativeMonth++;
	}
	// 一天的毫秒数
	var millisecond = 1000 * 60 * 60 * 24;
	// 下月的第一天
	var nextMonthDayOne = new Date(relativeYear, relativeMonth, 1);
	// 返回得到上月的最后一天,也就是本月总天数
	return new Date(nextMonthDayOne.getTime() - millisecond).getDate();
}

/**
 * 获得本季度的起止日期
 */
function getCurrentSeason() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前月份0-11
	var currentMonth = currentDate.getMonth();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	// 获得本季度开始月份
	var quarterSeasonStartMonth = this.getQuarterSeasonStartMonth(currentMonth);
	// 获得本季度结束月份
	var quarterSeasonEndMonth = quarterSeasonStartMonth + 2;
	if (quarterSeasonStartMonth == 11 && currentMonth != 11) {
		currentYear -= 1;
	}
	// 获得本季度开始的日期
	var quarterSeasonStartDate = new Date(currentYear, quarterSeasonStartMonth,
			1);
	// 获得本季度结束的日期
	var quarterSeasonEndDate = new Date(currentYear, quarterSeasonEndMonth,
			this.getMonthDays(currentYear, quarterSeasonEndMonth));
	// 加入数组返回
	startStop.push(quarterSeasonStartDate);
	startStop.push(quarterSeasonEndDate);
	// 返回
	return startStop;
}

/**
 * 得到上季度的起止日期
 * **/
function getPreviousSeason () {
	//起止日期数组
	var startStop = new Array();
	//获取当前时间
	var currentDate = this.getCurrentDate();
	//获得当前月份0-11
	var currentMonth = currentDate.getMonth();
	//获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	//上季度的第一天
	var priorSeasonFirstDay = this.getPriorSeasonFirstDay(currentYear, currentMonth);
	//上季度的最后一天
	var priorSeasonLastDay = new Date(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2, this.getMonthDays(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2));
	//添加至数组
	startStop.push(priorSeasonFirstDay);
	startStop.push(priorSeasonLastDay);
	return startStop;
};

/**
 * 得到上两季度的起止日期
 * **/
function getPreviousTwoSeason () {
	//起止日期数组
	var startStop = new Array();
	//获取当前时间
	var currentDate = this.getCurrentDate();
	//获得当前月份0-11
	var currentMonth = currentDate.getMonth();
	//获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	//上季度的第一天
	var priorSeasonFirstDay = this.getPriorTwoSeasonFirstDay(currentYear, currentMonth);
	//上季度的最后一天
	var priorSeasonLastDay = new Date(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2, this.getMonthDays(priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay.getMonth() + 2));
	//添加至数组
	startStop.push(priorSeasonFirstDay);
	startStop.push(priorSeasonLastDay);
	return startStop;
};

/*******************************************************************************
 * 得到本年的起止日期
 * 
 */
function getCurrentYear() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();

	// 本年第一天
	var currentYearFirstDate = new Date(currentYear, 0, 1);
	// 本年最后一天
	var currentYearLastDate = new Date(currentYear, 11, 31);
	// 添加至数组
	startStop.push(currentYearFirstDate);
	startStop.push(currentYearLastDate);
	// 返回
	return startStop;
}

/**
 * 返回上一个月的第一天Date类型
 * 
 * @param year
 *            年
 * @param month
 *            月
 */
function getPriorMonthFirstDay(year, month) {
	// 年份为0代表,是本年的第一月,所以不能减
	if (month == 0) {
		month = 11; // 月份为上年的最后月份
		year--; // 年份减1
		return new Date(year, month, 1);
	}
	// 否则,只减去月份
	month--;
	return new Date(year, month, 1);
	;
}

/**
 * 返回上两个月的第一天Date类型
 *
 * @param year
 *            年
 * @param month
 *            月
 */
function getPriorTwoMonthFirstDay(year, month) {
	// 年份为0代表,是本年的第一月,所以不能减
	if (month == 0) {
		month = 10; // 月份为上年的最后月份
		year--; // 年份减1
		return new Date(year, month, 1);
	} else if(month == 1){
		month = 11; // 月份为上年的最后月份
		year--; // 年份减1
		return new Date(year, month, 1);
	}
	// 否则,只减去月份
	month--;
	month--;
	return new Date(year, month, 1);
	;
}

/*******************************************************************************
 * 获得上一月的起止日期
 ******************************************************************************/
function getPreviousMonth() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前月份0-11
	var currentMonth = currentDate.getMonth();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	// 获得上一个月的第一天
	var priorMonthFirstDay = this.getPriorMonthFirstDay(currentYear,
			currentMonth);
	// 获得上一月的最后一天
	var priorMonthLastDay = new Date(priorMonthFirstDay.getFullYear(),
			priorMonthFirstDay.getMonth(), this.getMonthDays(priorMonthFirstDay
					.getFullYear(), priorMonthFirstDay.getMonth()));
	// 添加至数组
	startStop.push(priorMonthFirstDay);
	startStop.push(priorMonthLastDay);
	// 返回
	return startStop;
}
/*******************************************************************************
 * 获得上两个月的起止日期
 ******************************************************************************/
function getPreviousTwoMonth() {
	// 起止日期数组
	let startStop = new Array();
	// 获取当前时间
	let currentDate = this.getCurrentDate();
	// 获得当前月份0-11
	let currentMonth = currentDate.getMonth();
	// 获得当前年份4位年
	let currentYear = currentDate.getFullYear();
	// 获得上一个月的第一天
	let priorMonthFirstDay = this.getPriorTwoMonthFirstDay(currentYear,
			currentMonth);
	// 获得上一月的最后一天
	let priorMonthLastDay = new Date(priorMonthFirstDay.getFullYear(),
			priorMonthFirstDay.getMonth(), this.getMonthDays(priorMonthFirstDay
					.getFullYear(), priorMonthFirstDay.getMonth()));
	// 添加至数组
	startStop.push(priorMonthFirstDay);
	startStop.push(priorMonthLastDay);
	// 返回
	return startStop;
}

/*******************************************************************************
 * 获得上一周的起止日期
 ******************************************************************************/
function getPreviousWeek() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 返回date是一周中的某一天
	var week = currentDate.getDay();
	// 返回date是一个月中的某一天
	var month = currentDate.getDate();
	// 一天的毫秒数
	var millisecond = 1000 * 60 * 60 * 24;
	// 减去的天数
	var minusDay = week != 0 ? week - 1 : 6;
	// 获得当前周的第一天
	var currentWeekDayOne = new Date(currentDate.getTime()
			- (millisecond * minusDay));
	// 上周最后一天即本周开始的前一天
	var priorWeekLastDay = new Date(currentWeekDayOne.getTime() - millisecond);
	// 上周的第一天
	var priorWeekFirstDay = new Date(priorWeekLastDay.getTime()
			- (millisecond * 6));

	// 添加至数组
	startStop.push(priorWeekFirstDay);
	startStop.push(priorWeekLastDay);

	return startStop;
}

/**
 * 得到上季度的起始日期 year 这个年应该是运算后得到的当前本季度的年份 month 这个应该是运算后得到的当前季度的开始月份
 */
function getPriorSeasonFirstDay(year, month) {
	var quarterMonthStart = 0;
	var spring = 11; // 春
	var summer = 2; // 夏
	var fall = 5; // 秋
	var winter = 8; // 冬
	// 月份从0-11
	if (month < 2 || month > 10) {
		if (month != 11) {
			year--;
		}
		month = winter;
	} else if (month < 5) {
		year--;
		month = spring;
	} else if (month < 8) {
		month = summer;
	} else {
		month = fall;
	}
	return new Date(year, month, 1);
}
/**
 * 得到上两季度的起始日期 year 这个年应该是运算后得到的当前本季度的年份 month 这个应该是运算后得到的当前季度的开始月份
 */
function getPriorTwoSeasonFirstDay(year, month) {
	var quarterMonthStart = 0;
	var spring = 11; // 春
	var summer = 2; // 夏
	var fall = 5; // 秋
	var winter = 8; // 冬
	// 月份从0-11
	if (month < 2 || month > 10) {
		if (month != 11) {
			year--;
		}
		month = fall;
	} else if (month < 5) {
		year--;
		month = winter;
	} else if (month < 8) {
		month = spring;
	} else {
		month = summer;
	}
	return new Date(year, month, 1);
}

/*******************************************************************************
 * 得到上季度的起止日期
 ******************************************************************************/
function getPreviousSeason() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前月份0-11
	var currentMonth = currentDate.getMonth();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	// 上季度的第一天
	var priorSeasonFirstDay = this.getPriorSeasonFirstDay(currentYear,
			currentMonth);
	// 上季度的最后一天
	var priorSeasonLastDay = new Date(priorSeasonFirstDay.getFullYear(),
			priorSeasonFirstDay.getMonth() + 2, this.getMonthDays(
					priorSeasonFirstDay.getFullYear(), priorSeasonFirstDay
							.getMonth() + 2));
	// 添加至数组
	startStop.push(priorSeasonFirstDay);
	startStop.push(priorSeasonLastDay);
	return startStop;
}

/*******************************************************************************
 * 得到去年的这个月起止日期
 ******************************************************************************/
function getLestYearMonth() {
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	currentYear--;
	// 返回当前时间的月
	var month = currentDate.getMonth();
	var priorDay=new Date(currentYear,month,1);
	return priorDay;
}

/*******************************************************************************
 * 得到去年的起止日期
 ******************************************************************************/
function getPreviousYear() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	currentYear--;
	var priorYearFirstDay = new Date(currentYear, 0, 1);
	var priorYearLastDay = new Date(currentYear, 11, 31);
	// 添加至数组
	startStop.push(priorYearFirstDay);
	startStop.push(priorYearLastDay);
	return startStop;
}

/*******************************************************************************
 * 得到前年的起止日期
 ******************************************************************************/
function getTwoPreviousYear() {
	// 起止日期数组
	var startStop = new Array();
	// 获取当前时间
	var currentDate = this.getCurrentDate();
	// 获得当前年份4位年
	var currentYear = currentDate.getFullYear();
	currentYear--;
	currentYear--;
	var priorYearFirstDay = new Date(currentYear, 0, 1);
	var priorYearLastDay = new Date(currentYear, 11, 31);
	// 添加至数组
	startStop.push(priorYearFirstDay);
	startStop.push(priorYearLastDay);
	return startStop;
}

// 格式化时间 yyyy-MM-dd
function getFormatDate(date) {
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

// 格式化时间 yyyy-MM-dd hh:mm:ss
function getTimeFormatDate(date) {
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hour = date.getHours();
    var min =  date.getMinutes();
    var second =  date.getSeconds();
    if(hour >= 0 && hour <= 9)
	{
        hour = "0" + hour;
	}
    if(min >= 0 && min <= 9)
    {
        min = "0" + min;
    }
    if(second >= 0 && second <= 9)
    {
        second = "0" + second;
    }



    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate + " " + hour + seperator2 + min + seperator2 + second;
    return currentdate;
}
// 格式化时间 yyyy-MM-dd hh:mm:ss (时间戳)
function parseTime(time, cFormat) {
	if (arguments.length === 0) {
		return null
	}
	const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
	let date
	if (typeof time === 'object') {
		date = time
	} else {
		if (('' + time).length === 10) time = parseInt(time) * 1000
		date = new Date(time)
	}
	const formatObj = {
		y: date.getFullYear(),
		m: date.getMonth() + 1,
		d: date.getDate(),
		h: date.getHours(),
		i: date.getMinutes(),
		s: date.getSeconds(),
		a: date.getDay()
	}
	const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
		let value = formatObj[key]
		if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
		if (result.length > 0 && value < 10) {
			value = '0' + value
		}
		return value || 0
	})
	return time_str
}
