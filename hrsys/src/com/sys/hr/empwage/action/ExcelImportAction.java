package com.sys.hr.empwage.action;

import java.io.File;
import java.util.List;

import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.empwage.CmpnMonthAll;
import com.sys.util.ExcelUtil;

public class ExcelImportAction {
	private IBaseBIZ commonBiz;
	private File excel;

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	/**
	 * ��ͨ�ã�һ�����׶�Ӧһ��excelģ�壬
	 * @return
	 * @throws Exception
	 */
	public String excelImport() throws Exception {
		List excelList=ExcelUtil.readExcelByFile(excel);//list�д�ŵ���list����һ�ж�Ӧһ��list����һ���Ǳ��⣩
		for(int i=1;i<excelList.size();i++){//�ӵڶ��ж�����
			List list=(List) excelList.get(i);//Ա����� Ա������ �������� ����  �籣
			CmpnMonthAll cma =new CmpnMonthAll();
		}
		return "excelImport";
	}

}
