package com.framework.common.utils;

import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨超--YangChao
 * @version 创建时间：2018年12月10日 下午5:53:06
 * @parameter 基于poi的Excel导出导入工具类
 * @version 1.0
 */

/*
 * ExcelUtil工具类实现功能:
 * 导出时传入list<T>,即可实现导出为一个excel,其中每个对象Ｔ为Excel中的一条记录.
 * 导入时读取excel,得到的结果是一个list<T>.T是自己定义的对象.
 * 需要导出的实体对象只需简单配置注解,通过注解可以方便实现下面功能:
 * 1.实体属性配置了注解就能导出到excel中,每个属性都对应一列.
 * 2.列名称可以通过注解配置.
 * 3.导出到哪一列可以通过注解配置.
 * 4.鼠标移动到该列时提示信息可以通过注解配置.
 * 5.用注解设置只能下拉选择不能随意填写功能.
 * 6.用注解设置是否只导出标题而不导出内容,这在导出内容作为模板以供用户填写时比较实用.
 */

public class ExcelUtil<T> {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	Class<T> clazz;

	public ExcelUtil(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * excel数据导入
	 * @param sheetName
	 * @param input
	 * @return
	 */
	public List<T> importExcel(String sheetName, InputStream input) {
		List<T> list = new ArrayList<T>();
		try {
			Workbook book = Workbook.getWorkbook(input);
			Sheet sheet = null;
			if (!"".equals(sheetName.trim())) {
				// 如果指定sheet名,则取指定sheet中的内容.
				sheet = book.getSheet(sheetName);
			}
			if (sheet == null) {
				// 如果传入的sheet名不存在则默认指向第1个sheet.
				sheet = book.getSheet(0);
			}
			// 得到数据的行数
			int rows = sheet.getRows();
			// 有数据时才处理
			if (rows > 0) {
				// 得到类的所有field.
				Field[] allFields = clazz.getDeclaredFields();
				// 定义一个map用于存放列的序号和field.
				Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
				for (Field field : allFields) {
					// 将有注解的field存放到map中.
					if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
						ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
						// 获得列号
						int col = getExcelCol(attr.column());
						// 设置类的私有字段属性可访问.
						field.setAccessible(true);
						fieldsMap.put(col, field);
					}
				}
				// 从第2行开始取数据,默认第一行是表头
				for (int i = 1; i < rows; i++) {
					// 得到一行中的所有单元格对象.
					Cell[] cells = sheet.getRow(i);
					T entity = null;
					for (int j = 0; j < cells.length; j++) {
						// 单元格中的内容.
						String c = cells[j].getContents();
						if ("".equals(c)) {
							continue;
						}
						// 如果不存在实例则新建.
						entity = (entity == null ? clazz.newInstance() : entity);
						// 从map中得到对应列的field.
						Field field = fieldsMap.get(j);
						// 取得类型,并根据对象类型设置值.
						Class<?> fieldType = field.getType();
						if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
							field.set(entity, Integer.parseInt(c));
						} else if (String.class == fieldType) {
							field.set(entity, String.valueOf(c));
						} else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
							field.set(entity, Long.valueOf(c));
						} else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
							field.set(entity, Float.valueOf(c));
						} else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
							field.set(entity, Short.valueOf(c));
						} else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
							field.set(entity, Double.valueOf(c));
						} else if (Character.TYPE == fieldType) {
							if ((c != null) && (c.length() > 0)) {
								field.set(entity, Character.valueOf(c.charAt(0)));
							}
						}
					}
					if (entity != null) {
						list.add(entity);
					}
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
     *
     * @Title: resList
     * @Description:导出返回(数据源是含实体类的List)
     * @return: ExcelReturn
     * @throws
     */
	public ExcelReturn resList(String FileName, String FilePath, List<T> list, boolean isFreezeFirstRow) {
		return resListDynamicProcess(FileName, FilePath, list, null, null, null, null, isFreezeFirstRow);
	}

	public ExcelReturn resList(String FileName, String FilePath, List<T> list) {
		return resListDynamicProcess(FileName, FilePath, list, null, null, null, null, true);
	}

    /**
     *
     * @Description:导出返回(数据源是含实体类的List)(含有合并单元格样式mergedRegionList)
     * @param FileName
     * @param FilePath
     * @param list
     * @return ExcelReturn
     */
    public ExcelReturn resList(String FileName, String FilePath, List<T> list, List<List<Map<String, Integer>>> mergedRegionList, boolean isFreezeFirstRow) {
    	return resListDynamicProcess(FileName, FilePath, list, null, null, null, mergedRegionList, isFreezeFirstRow);
    }

    public ExcelReturn resList(String FileName, String FilePath, List<T> list, List<List<Map<String, Integer>>> mergedRegionList) {
    	return resListDynamicProcess(FileName, FilePath, list, null, null, null, mergedRegionList, true);
    }

    /**
     *
     * @Title: resListDynamic
     * @Description:导出返回(数据源是含不含实体类的Map<key,value>实体类的List),alias:key的List,names:value的List
     * @return: ExcelReturn
     * @throws
     */
    public ExcelReturn resListDynamic(String FileName, String FilePath, List<Map<String, Object>> list, List<Object> alias, List<Object> names, boolean isFreezeFirstRow) {
    	return resListDynamicProcess(FileName, FilePath, null, list, alias, names, null, isFreezeFirstRow);
    }

    public ExcelReturn resListDynamic(String FileName, String FilePath, List<Map<String, Object>> list, List<Object> alias, List<Object> names) {
    	return resListDynamicProcess(FileName, FilePath, null, list, alias, names, null, true);
    }

    /**
     *
     * @Description:导出返回(数据源是含不含实体类的Map<key,value>实体类的List)(含有合并单元格样式mergedRegionList),alias:key的List,names:value的List
     * @param FileName
     * @param FilePath
     * @param list
     * @param alias
     * @param names
     * @param mergedRegionList
     * @return ExcelReturn
     */
    public ExcelReturn resListDynamic(String FileName, String FilePath, List<Map<String, Object>> list, List<Object> alias, List<Object> names, List<List<Map<String, Integer>>> mergedRegionList, boolean isFreezeFirstRow) {
    	return resListDynamicProcess(FileName, FilePath, null, list, alias, names, mergedRegionList, isFreezeFirstRow);
    }
    public ExcelReturn resListDynamic(String FileName, String FilePath, List<Map<String, Object>> list, List<Object> alias, List<Object> names, List<List<Map<String, Integer>>> mergedRegionList) {
    	return resListDynamicProcess(FileName, FilePath, null, list, alias, names, mergedRegionList, true);
    }

    /**
     * 动态处理excel是否根据实体类生成
     * @param FileName
     * @param FilePath
     * @param list 数据源List(根据实体类生成)
     * @param dynamicList 数据源List(不根据实体类生成)
     * @param alias 数据源List中对应数据的key(不根据实体类生成)
     * @param names 数据源List中对应数据的标题名(不根据实体类生成)
     * @param mergedRegionList 数据源合并单元格List
     * @return
     */
	private ExcelReturn resListDynamicProcess(String FileName, String FilePath, List<T> list, List<Map<String, Object>> dynamicList, List<Object> alias, List<Object> names,
			List<List<Map<String, Integer>>> mergedRegionList, boolean isFreezeFirstRow) {
		File file = new File(FilePath);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		ExcelReturn res = new ExcelReturn();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(FilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean flag = false;
		if (mergedRegionList == null) {
			if (list != null)
				flag = exportExcel(list, FileName, list.size(), out, isFreezeFirstRow);
			else
				flag = exportExcelDynamic(dynamicList, alias, names, FileName, dynamicList.size(), out, isFreezeFirstRow);
		} else {
			if (list != null)
				flag = exportExcel(list, FileName, list.size(), out, mergedRegionList, isFreezeFirstRow);
			else
				flag = exportExcelDynamic(dynamicList, alias, names, FileName, dynamicList.size(), out, mergedRegionList, isFreezeFirstRow);
		}
		String status = "0";
		if (flag) {
			status = "1";
		}
		res.setStatus(status);
		log.debug("\r" + "excel导出生成文件成功--路径为：" + FilePath);
		return res;
	}

	/**
     * 对list数据源将其里面的数据导入到excel表单--实体类,固定表头
     *
     * @param sheetName 工作表的名称
     * @param sheetSize 每个sheet中数据的行数,此数值必须小于等于65535
     * @param output    java输出流
     */
    private boolean exportExcel(List<T> list, String sheetName, int sheetSize, OutputStream output, boolean isFreezeFirstRow) {
    	return exportExcel(list, sheetName, sheetSize, output, null, isFreezeFirstRow);
	}

    /**
     *
     * @author: YangChao
     * @createTime: 2019年1月2日 下午2:48:07
     * @Description:填加合并单元格
     * @param list
     * @param sheetName
     * @param sheetSize
     * @param output
     * @return boolean
     */
    @SuppressWarnings("deprecation")
	private boolean exportExcel(List<T> list, String sheetName, int sheetSize, OutputStream output, List<List<Map<String, Integer>>> mergedRegionList, boolean isFreezeFirstRow) {
		Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
		List<Field> fields = new ArrayList<Field>();
		// 得到所有field并存放到一个list中.
		for (Field field : allFields) {
			if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
				fields.add(field);
			}
		}
		HSSFWorkbook workbook = new HSSFWorkbook();// 产生工作薄对象
		// excel2003中每个sheet中最多有65535行,为避免产生错误所以加这个逻辑.
		if (sheetSize > 65535 || sheetSize < 1) {
			sheetSize = 65535;
		}
		double sheetNo = Math.ceil(list.size() / (double) sheetSize);// 取出一共有多少个sheet.
		for (int index = 0; index < sheetNo; index++) {
			HSSFSheet sheet = workbook.createSheet();// 产生工作表对象
			HSSFCellStyle style = getStyle(workbook); // 单元格样式对象
			if (isFreezeFirstRow != false)
				sheet.createFreezePane(0, 1, 0, 1);// 固定第一行标题
			HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook, isFreezeFirstRow);// 获取列头样式对象
			String pageIndex = index == 0 ? "" : index + "";
			workbook.setSheetName(index, sheetName + pageIndex);// 设置工作表的名称.
			HSSFRow row;
			HSSFCell cell;// 产生单元格
			row = sheet.createRow(0);// 产生一行
			// 写入各个字段的列头名称
			if (isFreezeFirstRow != false) {
				for (int i = 0; i < fields.size(); i++) {
					Field field = fields.get(i);
					ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
					int col = getExcelCol(attr.column());// 获得列号
					cell = row.createCell(col);// 创建列
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列中写入内容为String类型
					cell.setCellValue(attr.name());// 写入列名
					short width = 20, height = 25 * 20;
					sheet.setDefaultColumnWidth(width);// 设置列宽
					cell.setCellStyle(columnTopStyle);// 设置列头样式
					// 如果设置了提示信息则鼠标放上去提示.
					if (!"".equals(attr.prompt().trim())) {
						setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);// 这里默认设了2-101列提示.
					}
					// 如果设置了combo属性则本列只能选择不能输入
					if (attr.combo().length > 0) {
						setHSSFValidation(sheet, attr.combo(), 1, 100, col, col);// 这里默认设了2-101列只能选择不能输入.
					}
				}
			}
			int startNo = index * sheetSize;
			int endNo = Math.min(startNo + sheetSize, list.size());
			// 写入各条记录,每条记录对应excel表中的一行
			for (int i = startNo; i < endNo; i++) {
				row = sheet.createRow(i + 1 - startNo);
				if (isFreezeFirstRow == false) {
					short width = 20, height = 25 * 20;
					sheet.setDefaultColumnWidth(width);// 设置列宽
					row = sheet.createRow(i - startNo);
				}
				T vo = list.get(i); // 得到导出对象.
				for (int j = 0; j < fields.size(); j++) {
					Field field = fields.get(j);// 获得field.
					field.setAccessible(true);// 设置实体类私有属性可访问
					ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
					try {
						// 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望填写这一列.
						if (attr.isExport()) {
							cell = row.createCell(getExcelCol(attr.column()));// 创建cell
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));// 如果数据存在就填入,不存在填入空格.
							cell.setCellStyle(style);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			// 判断是否需要合并
			if (mergedRegionList != null && !mergedRegionList.isEmpty() && mergedRegionList.size() > 0) {
				for (Map<String, Integer> map : mergedRegionList.get(index)) {
					sheet.addMergedRegion(new CellRangeAddress(map.get("firstRow"), map.get("endRow"),
							map.get("firstCol"), map.get("endCol")));// 合并单元格
				}
			}
		}
		try {
			output.flush();
			workbook.write(output);
			output.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

    /**
     * 对list数据源将其里面的数据导入到excel表单--可动态表头
     *
     * @param fieldName [] 导出到excel文件里的表头名
     * @param columnIt  [] 导出到excel文件里的表头NAME
     * @param sheetName 工作表的名称
     * @param sheetSize 每个sheet中数据的行数,此数值必须小于等于65535
     * @param output    java输出流
     */
    private static boolean exportExcelDynamic(List<?> list, List<Object> fieldName, List<Object> columnIt, String sheetName, int sheetSize, OutputStream output, boolean isFreezeFirstRow) {
    	return exportExcelDynamic(list, fieldName, columnIt, sheetName, sheetSize, output, null, isFreezeFirstRow);
    }

    /**
     *
     * @author: YangChao
     * @createTime: 2019年1月2日 下午2:16:15
     * @Description:填加合并单元格mergedRegionList
     * @param list
     * @param fieldName
     * @param columnIt
     * @param sheetName
     * @param sheetSize
     * @param output
     * @param mergedRegionList
     * @param firstRow         开始行
     * @param endRow           结束行
     * @param firstCol         开始列
     * @param endCol           结束列
     * @return boolean
     */
    @SuppressWarnings("deprecation")
    private static boolean exportExcelDynamic(List<?> list, List<Object> fieldName, List<Object> columnIt, String sheetName, int sheetSize, OutputStream output, List<List<Map<String, Integer>>> mergedRegionList, boolean isFreezeFirstRow) {
		HSSFWorkbook workbook = new HSSFWorkbook();// 产生工作薄对象
		if (sheetSize >= 65535) {
			sheetSize = 65535;
		}
		double sheetNo = 1;
		if(sheetSize > 0){
			sheetNo = Math.ceil(list.size() / (double) sheetSize);
		}

		for (int index = 0; index < sheetNo; index++) {
			HSSFSheet sheet = workbook.createSheet();// 产生工作表对象
			String pageIndex = index == 0 ? "" : index + "";
			workbook.setSheetName(index, sheetName + pageIndex);// 设置工作表的名称.
			if (isFreezeFirstRow != false)
				sheet.createFreezePane(0, 1, 0, 1);// 固定第一行标题
			HSSFCellStyle style = getStyle(workbook); // 单元格样式对象
			HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook, isFreezeFirstRow);// 获取列头样式对象
			HSSFRow row = sheet.createRow(0);// 产生一行
			HSSFCell cell;// 产生单元格
			// 写入各个字段的名称
			if (isFreezeFirstRow != false) {
				for (int i = 0; i < fieldName.size(); i++) {
					cell = row.createCell(i); // 创建第一行各个字段名称的单元格
					cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置单元格内容为字符串型
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					// //为了能在单元格中输入中文,设置字符集为UTF_16
					cell.setCellValue(fieldName.get(i).toString()); // 给单元格内容赋值
					short width = 20, height = 25 * 20;
					sheet.setDefaultColumnWidth(width);// 设置列宽
					cell.setCellStyle(columnTopStyle);// 设置列头样式
				}
			}
			int startNo = index * sheetSize;
			int endNo = Math.min(startNo + sheetSize, list.size());
			// 写入各条记录,每条记录对应excel表中的一行
			for (int i = startNo; i < endNo; i++) {
				row = sheet.createRow(i + 1 - startNo);
				if (isFreezeFirstRow == false) {
					short width = 20, height = 25 * 20;
					sheet.setDefaultColumnWidth(width);// 设置列宽
					row = sheet.createRow(i - startNo);
				}
				HashMap<?, ?> map = (HashMap<?, ?>) list.get(i);
				for (int j = 0; j < columnIt.size(); j++) {
					cell = row.createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					Object value = map.get(columnIt.get(j));
					cell.setCellStyle(style);// 设置单元格样式
					if (value != null) {
						cell.setCellValue(map.get(columnIt.get(j)).toString());
					} else {
						cell.setCellValue("");
					}
				}
			}
			// 判断是否需要合并
			if (mergedRegionList != null && !mergedRegionList.isEmpty() && mergedRegionList.size() > 0) {
				for (Map<String, Integer> map : mergedRegionList.get(index)) {
					sheet.addMergedRegion(new CellRangeAddress(map.get("firstRow"), map.get("endRow"),
							map.get("firstCol"), map.get("endCol")));// 合并单元格
				}
			}
		}
		try {
			output.flush();
			workbook.write(output);
			output.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output is closed ");
			return false;
		}
	}

	/**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col
     */
	private int getExcelCol(String col) {
		col = col.toUpperCase();
		// 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
		int count = -1;
		char[] cs = col.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
		}
		return count;
	}

    /**
     *
     * @author: YangChao
     * @createTime: 2019年1月2日 上午10:59:57
     * @Description:设置导出Excel基本样式--列头
     * @param workbook
     * @return HSSFCellStyle
     */
	private static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook, boolean isFreezeFirstRow) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("宋体");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置单元格背景颜色
		if(isFreezeFirstRow!=false)
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		else
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		return style;
	}

    /**
     *
     * @author: YangChao
     * @createTime: 2019年1月2日 上午9:33:24
     * @Description:设置导出Excel基本样式--内容
     * @param workbook
     * @return HSSFCellStyle
     */
	private static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 10);
		// 字体加粗
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("宋体");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;
	}

	/**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
	private HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
		// 构造constraint对象
		DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
		// 四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		// 数据有效性对象
		HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);
		data_validation_view.createPromptBox(promptTitle, promptContent);
		sheet.addValidationData(data_validation_view);
		return sheet;
	}

	/**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
	private HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
		// 加载下拉列表内容
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
		// 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		// 数据有效性对象
		HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
		sheet.addValidationData(data_validation_list);
		return sheet;
	}

}
