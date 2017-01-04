package com.sys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static final int SPLIT_COUNT = 15; // Excel每个工作簿的行数

	/**
	 * 
	 * @param list
	 *            excel的数据集合，里面放的是一个对象
	 * @param title
	 *            excel的标题集合
	 * @param xlsPath
	 *            保存路径
	 * @throws Exception
	 */
	public static void createExcel(List list, String[] title, String xlsPath)
			throws Exception {

		Object vo = list.get(0);
		Class currentClass = vo.getClass();
		Field[] fields = currentClass.getDeclaredFields();
		Method[] methods = currentClass.getMethods();

		// 在内存中创建excel
		XSSFWorkbook workBook = new XSSFWorkbook();
		int rows = list.size();
		int sheetNum = 0;

		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			// 标题输出
			XSSFSheet sheet = workBook.createSheet("Page " + i);
			XSSFRow headRow = sheet.createRow(0);
			for (int j = 0; j < title.length; j++) {
				XSSFCell cell = headRow.createCell(j);
				// 添加样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if (title[j] != null) {
					cell.setCellValue(title[j]);
				} else {
					cell.setCellValue("-");
				}
			}

			// 添加内容
			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				XSSFRow row = sheet.createRow((k + 1));//创建一行
				vo = list.get(k);
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					String fieldName = field.getName();
					StringBuffer get_method_name = new StringBuffer();
					get_method_name.append("get");
					get_method_name.append(fieldName);
					for (int n = 0; n < methods.length; n++) {
						Method method = methods[n];
						if (method.getName().equalsIgnoreCase(
								get_method_name.toString())) {
							if ("int".equals(field.getType().getName())) {
								int value = (Integer) method.invoke(vo, null);
								// 第一个属性值放第一列，第二个属性值放第二列。。。第一行放的标题，因此i+1
								XSSFCell cell = row.createCell(j);//创建单元格
								cell.setCellValue(value);//填值

							} else {
								String value = (String) method.invoke(vo, null);
								XSSFCell cell = row.createCell(j);
								cell.setCellValue(value);
							}

						}

					}
				}
			}

		}
		FileOutputStream fos = new FileOutputStream(xlsPath);
		workBook.write(fos);

	}
	
	
	
	/**
	 * 
	 * @param fieldName   excel标题  该list中存放的是String，List<String>
	 * @param fieldData   excel数据集   该list中存放的是List<List>
	 * @param xlsPath     excel路径
	 * @throws Exception
	 */
	public static void createExcel(List fieldName,List fieldData,String xlsPath ) throws Exception {

		XSSFWorkbook workBook = new XSSFWorkbook();
		int rows = fieldData.size();
		int sheetNum = 0;

		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			XSSFSheet sheet = workBook.createSheet("Page " + i);
			XSSFRow headRow = sheet.createRow(0); 
			for (int j = 0; j < fieldName.size(); j++) {
				XSSFCell cell = headRow.createCell(j);
				//添加样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(fieldName.get(j) != null){
					cell.setCellValue((String) fieldName.get(j));
				}else{
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				XSSFRow row = sheet.createRow((short) (k + 1));
				//将数据内容放入excel单元格
				ArrayList rowList = (ArrayList) fieldData.get((i - 1)
						* SPLIT_COUNT + k);
				for (int n = 0; n < rowList.size(); n++) {
					XSSFCell cell = row.createCell(n);
					if(rowList.get(n) != null){
						cell.setCellValue((String) rowList.get(n).toString());
					}else{
						cell.setCellValue("");
					}
				}
			}
		}
		
		FileOutputStream fos = new FileOutputStream(xlsPath);
		workBook.write(fos);
	}
	
	
	/**
	 * 通过下载的形式导出excel
	 * @param fieldName  标题
	 * @param fieldData   数据
	 * @param os         
	 * @throws IOException
	 */
	public static void exportExcel(List fieldName,List fieldData,OutputStream os) throws IOException {

		XSSFWorkbook workBook = new XSSFWorkbook();
		int rows = fieldData.size();
		int sheetNum = 0;

		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			XSSFSheet sheet = workBook.createSheet("Page " + i);
			XSSFRow headRow = sheet.createRow(0); 
			for (int j = 0; j < fieldName.size(); j++) {
				XSSFCell cell = headRow.createCell(j);
				//添加样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(fieldName.get(j) != null){
					cell.setCellValue((String) fieldName.get(j));
				}else{
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				XSSFRow row = sheet.createRow((short) (k + 1));
				//将数据内容放入excel单元格
				ArrayList rowList = (ArrayList) fieldData.get((i - 1)
						* SPLIT_COUNT + k);
				for (int n = 0; n < rowList.size(); n++) {
					XSSFCell cell = row.createCell(n);
					if(rowList.get(n) != null){
						cell.setCellValue((String) rowList.get(n).toString());
					}else{
						cell.setCellValue("");
					}
				}
			}
		}
		workBook.write(os);
		os.close();
	}

	
	
	
	
	
	/**
	 * 读取excel数据
	 * @param xlsPath   excel路径
	 * @param className 每一行记录对应的对象
	 * @return list
	 */
	public static List readExcel(String xlsPath, String className)
			throws Exception{
		List list = new ArrayList();
		// 通过className获得类中的信息
		Class currentClass = Class.forName(className);
		Field[] fields = currentClass.getDeclaredFields();
		Method[] methods = currentClass.getMethods();

		// 将文件加载到内存中
		XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(xlsPath));
		// 获得所有sheet页
		int sheetNum = workBook.getNumberOfSheets();
		// 读取内容
		for (int i = 0; i < sheetNum; i++) {
			XSSFSheet  sheet = workBook.getSheetAt(i);//获得一个sheet页
			int rows = sheet.getLastRowNum();//获取sheet页的行数
			
			// 从第二行开始读取
			for (int row = 1; row <= rows; row++) {
				XSSFRow xssfRow=  sheet.getRow(row);//得到一行
				//一行记录是一个对象
				Object vo = currentClass.newInstance();//默认调用无参构造函数
				for (int column = 0; column < fields.length; column++) {
					XSSFCell cell=  xssfRow.getCell(column);
//					String ss=cell.getStringCellValue();
//					int cellType=cell.getCellType();
					//每一列是一个属性
					Field field = fields[column];
					//通过属性名字拼出set方法，并调用该方法
					StringBuffer set_method_name = new StringBuffer();
					set_method_name.append("set");
					set_method_name.append(field.getName());
					
					for(int n=0;n<methods.length;n++){
						Method method = methods[n];
						if(method.getName().equalsIgnoreCase(set_method_name.toString())){
							if(field.getType().getName().equals("int")){
								String value=cell.getStringCellValue();
								method.invoke(vo, Integer.valueOf(value));
							}else{
								String value=cell.getStringCellValue();
								method.invoke(vo, value);
							}
								
						}
					}
					
				}
				list.add(vo);
			}
			
		}
		return list;
	}
	/**
	 * 通过文件选择框的形式导入
	 * @param xlsPath
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static List readExcelByFile(File file)
			throws Exception{
		List list = new ArrayList();
		// 将文件加载到内存中
		XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(file));
		// 获得所有sheet页
		int sheetNum = workBook.getNumberOfSheets();
		// 读取内容
		for (int i = 0; i < sheetNum; i++) {
			XSSFSheet  sheet = workBook.getSheetAt(i);//获得一个sheet页
			int rows = sheet.getLastRowNum();//获取sheet页的行数
			
			for (int row = 1; row <= rows; row++) {
				List rowList = new ArrayList();//每一行数据存放到一个list中
				XSSFRow xssfRow=  sheet.getRow(row);//得到一行
				int columns=xssfRow.getLastCellNum();//改行的列数
				for (int column = 0; column < columns; column++) {
					XSSFCell cell=  xssfRow.getCell(column);		
					String value=cell.getStringCellValue();
					
					rowList.add(value);
								
				}
				list.add(rowList);
			}
		}
					
			
		return list;
	}
	
	
	
	public String getCellXlsx(XSSFCell cell) {
		  if (cell == null)
		   return "";
		  switch (cell.getCellType()) {
		 
		  case XSSFCell.CELL_TYPE_STRING:
		   return cell.getStringCellValue();
		  case XSSFCell.CELL_TYPE_FORMULA:
		   return cell.getCellFormula();
		  case XSSFCell.CELL_TYPE_BLANK:
		   return "";
		  case XSSFCell.CELL_TYPE_BOOLEAN:
		   return cell.getBooleanCellValue() + "";
		  case XSSFCell.CELL_TYPE_ERROR:
		   return cell.getErrorCellValue() + "";
		  }
		  return "";
		 }


}
