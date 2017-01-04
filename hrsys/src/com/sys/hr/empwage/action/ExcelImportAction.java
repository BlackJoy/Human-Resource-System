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
	 * 不通用，一个帐套对应一个excel模板，
	 * @return
	 * @throws Exception
	 */
	public String excelImport() throws Exception {
		List excelList=ExcelUtil.readExcelByFile(excel);//list中存放的是list，（一行对应一个list，第一行是标题）
		for(int i=1;i<excelList.size();i++){//从第二行读数据
			List list=(List) excelList.get(i);//员工编号 员工姓名 基本工资 考勤  社保
			CmpnMonthAll cma =new CmpnMonthAll();
		}
		return "excelImport";
	}

}
