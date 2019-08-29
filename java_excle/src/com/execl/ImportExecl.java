package com.execl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @描述：测试excel读取
 * 
 *               导入的jar包
 * 
 *               poi-3.8-beta3-20110606.jar
 * 
 *               poi-ooxml-3.8-beta3-20110606.jar
 * 
 *               poi-examples-3.8-beta3-20110606.jar
 * 
 *               poi-excelant-3.8-beta3-20110606.jar
 * 
 *               poi-ooxml-schemas-3.8-beta3-20110606.jar
 * 
 *               poi-scratchpad-3.8-beta3-20110606.jar
 * 
 *               xmlbeans-2.3.0.jar
 * 
 *               dom4j-1.6.1.jar
 * 
 *               jar包官网下载地址：http://poi.apache.org/download.html
 * 
 *               下载poi-bin-3.8-beta3-20110606.zipp
 * 
 */

public class ImportExecl {
	/** 总行数 */
	private int totalRows = 0;
	/** 总列数 */
	private int totalCells = 0;
	/** 错误信息 */
	private String errorInfo;

	/** 构造方法 */
	public ImportExecl() {
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public boolean validateExcel(String filePath) {
		/** 检查文件名是否为空或者是否是Excel格式的文件 */
		if (filePath == null
				|| !(WDWUtil.isExcel2003(filePath) || WDWUtil
						.isExcel2007(filePath))) {
			errorInfo = "文件名不是excel格式";
			return false;
		}
		/** 检查文件是否存在 */
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			errorInfo = "文件不存在";
			return false;
		}
		return true;
	}

	public List<List<String>> read(String filePath) {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		InputStream is = null;
		try {
			/** 验证文件是否合法 */
			if (!validateExcel(filePath)) {
				System.out.println(errorInfo);
				return null;
			}
			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (WDWUtil.isExcel2007(filePath)) {
				isExcel2003 = false;
			}
			/** 调用本类提供的根据流读取的方法 */
			File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = read(is, isExcel2003);
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		/** 返回最后读取的结果 */
		return dataLst;
	}

	public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
		List<List<String>> dataLst = null;
		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataLst;
	}

	private List<List<String>> read(Workbook wb) {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		/** 得到第一个sheet */
		Sheet sheet = wb.getSheetAt(0);
		/** 得到Excel的行数 */
		this.totalRows = sheet.getPhysicalNumberOfRows();
		/** 得到Excel的列数 */
		if (this.totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		/** 循环Excel的行 */
		for (int r = 0; r < this.totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			List<String> rowLst = new ArrayList<String>();
			/** 循环Excel的列 */
			for (int c = 0; c < this.getTotalCells(); c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellTypeEnum()) {
					case NUMERIC: // 数字
						cellValue = cell.getNumericCellValue() + "";
						break;
					case STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;
					case BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;
					case FORMULA: // 公式
						cellValue = cell.getCellFormula() + "";
						break;
					case BLANK: // 空值
						cellValue = "";
						break;
					case ERROR: // 故障
						cellValue = "非法字符";
						break;
					default:
						cellValue = "未知类型";
						break;
					}
				}
				rowLst.add(cellValue);
			}
			/** 保存第r行的第c列 */
			dataLst.add(rowLst);
		}
		return dataLst;
	}

	public static void main(String[] args) throws Exception {
		ImportExecl poi = new ImportExecl();
		/*List<List<String>> ddd = poi.read("E:/temp/excle/dddd.xls");
		
		for(int i=0; i<ddd.size(); i++) {
			List<String> dddCell = ddd.get(i);
			dddCell.add(1, "test" + i);
		}
		
		File dddd1 = new File("E:/temp/excle/dddd-1.xls");
		
		HSSFWorkbook wb = ExportExcleUtils.createWookBook(ddd);
		wb.write(dddd1);
		wb.close();*/
		
		List<List<String>> tax_raw_1 = poi.read("E:/temp/excle/tax_raw-1.xls");
		List<List<String>> zotutab_1 = poi.read("E:/temp/excle/zotutab-1.xls");
		
		for(int i=0; i<zotutab_1.size(); i++){
			List<String> zoList = zotutab_1.get(i);
			String zoCell1 = zoList.get(0);
			
			for(int j=0; j<tax_raw_1.size(); j++){
				List<String> tax_List = tax_raw_1.get(j);
				String taxCell1 = tax_List.get(0);
				String taxCell2 = tax_List.get(1);
				if (zoCell1.equals(taxCell1)) {
					System.out.println(zoCell1);
					zoList.add(1, taxCell2);
				}
			}
		}
		
		File zotutab = new File("E:/temp/excle/zotutab.xls");
		if (zotutab.exists()) {
			HSSFWorkbook wb = ExportExcleUtils.createWookBook(zotutab_1);
			wb.write(zotutab);
			wb.close();
		}
		
	}

	public static File createFile(String string) {
		File f = new File("E:/temp/ssss", string);
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static void createCer() throws Exception{
		ImportExecl poi = new ImportExecl();
		List<List<String>> list = poi.read("E:/temp/dddd.xls");
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print("第" + (i) + "行");
				List<String> cellList = list.get(i);
				for (int j = 0; j < cellList.size(); j++) {
					// System.out.print("    第" + (j + 1) + "列值：");
					File file = null;
					if (j == 1) {
						file = createFile(cellList.get(j) + ".cer");
						System.out.println(file);
						System.out.println(cellList.get(j + 1));
						String sss = cellList.get(j + 1);
						FileWriter fos = new FileWriter(file);
						fos.write(sss);
						fos.flush();
						fos.close();
					}
				}
				System.out.println();
			}
		}
	}
}



class WDWUtil {
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
