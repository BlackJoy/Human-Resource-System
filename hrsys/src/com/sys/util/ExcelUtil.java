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
	private static final int SPLIT_COUNT = 15; // Excelÿ��������������

	/**
	 * 
	 * @param list
	 *            excel�����ݼ��ϣ�����ŵ���һ������
	 * @param title
	 *            excel�ı��⼯��
	 * @param xlsPath
	 *            ����·��
	 * @throws Exception
	 */
	public static void createExcel(List list, String[] title, String xlsPath)
			throws Exception {

		Object vo = list.get(0);
		Class currentClass = vo.getClass();
		Field[] fields = currentClass.getDeclaredFields();
		Method[] methods = currentClass.getMethods();

		// ���ڴ��д���excel
		XSSFWorkbook workBook = new XSSFWorkbook();
		int rows = list.size();
		int sheetNum = 0;

		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			// �������
			XSSFSheet sheet = workBook.createSheet("Page " + i);
			XSSFRow headRow = sheet.createRow(0);
			for (int j = 0; j < title.length; j++) {
				XSSFCell cell = headRow.createCell(j);
				// �����ʽ
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if (title[j] != null) {
					cell.setCellValue(title[j]);
				} else {
					cell.setCellValue("-");
				}
			}

			// �������
			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				XSSFRow row = sheet.createRow((k + 1));//����һ��
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
								// ��һ������ֵ�ŵ�һ�У��ڶ�������ֵ�ŵڶ��С�������һ�зŵı��⣬���i+1
								XSSFCell cell = row.createCell(j);//������Ԫ��
								cell.setCellValue(value);//��ֵ

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
	 * @param fieldName   excel����  ��list�д�ŵ���String��List<String>
	 * @param fieldData   excel���ݼ�   ��list�д�ŵ���List<List>
	 * @param xlsPath     excel·��
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
				//�����ʽ
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(fieldName.get(j) != null){
					cell.setCellValue((String) fieldName.get(j));
				}else{
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				XSSFRow row = sheet.createRow((short) (k + 1));
				//���������ݷ���excel��Ԫ��
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
	 * ͨ�����ص���ʽ����excel
	 * @param fieldName  ����
	 * @param fieldData   ����
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
				//�����ʽ
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(fieldName.get(j) != null){
					cell.setCellValue((String) fieldName.get(j));
				}else{
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				XSSFRow row = sheet.createRow((short) (k + 1));
				//���������ݷ���excel��Ԫ��
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
	 * ��ȡexcel����
	 * @param xlsPath   excel·��
	 * @param className ÿһ�м�¼��Ӧ�Ķ���
	 * @return list
	 */
	public static List readExcel(String xlsPath, String className)
			throws Exception{
		List list = new ArrayList();
		// ͨ��className������е���Ϣ
		Class currentClass = Class.forName(className);
		Field[] fields = currentClass.getDeclaredFields();
		Method[] methods = currentClass.getMethods();

		// ���ļ����ص��ڴ���
		XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(xlsPath));
		// �������sheetҳ
		int sheetNum = workBook.getNumberOfSheets();
		// ��ȡ����
		for (int i = 0; i < sheetNum; i++) {
			XSSFSheet  sheet = workBook.getSheetAt(i);//���һ��sheetҳ
			int rows = sheet.getLastRowNum();//��ȡsheetҳ������
			
			// �ӵڶ��п�ʼ��ȡ
			for (int row = 1; row <= rows; row++) {
				XSSFRow xssfRow=  sheet.getRow(row);//�õ�һ��
				//һ�м�¼��һ������
				Object vo = currentClass.newInstance();//Ĭ�ϵ����޲ι��캯��
				for (int column = 0; column < fields.length; column++) {
					XSSFCell cell=  xssfRow.getCell(column);
//					String ss=cell.getStringCellValue();
//					int cellType=cell.getCellType();
					//ÿһ����һ������
					Field field = fields[column];
					//ͨ����������ƴ��set�����������ø÷���
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
	 * ͨ���ļ�ѡ������ʽ����
	 * @param xlsPath
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static List readExcelByFile(File file)
			throws Exception{
		List list = new ArrayList();
		// ���ļ����ص��ڴ���
		XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(file));
		// �������sheetҳ
		int sheetNum = workBook.getNumberOfSheets();
		// ��ȡ����
		for (int i = 0; i < sheetNum; i++) {
			XSSFSheet  sheet = workBook.getSheetAt(i);//���һ��sheetҳ
			int rows = sheet.getLastRowNum();//��ȡsheetҳ������
			
			for (int row = 1; row <= rows; row++) {
				List rowList = new ArrayList();//ÿһ�����ݴ�ŵ�һ��list��
				XSSFRow xssfRow=  sheet.getRow(row);//�õ�һ��
				int columns=xssfRow.getLastCellNum();//���е�����
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
