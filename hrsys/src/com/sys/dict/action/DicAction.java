package com.sys.dict.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.dict.DicData;
import com.sys.dict.DicType;
import com.sys.dict.biz.IDicBIZ;

public class DicAction extends ActionSupport {

	private static final long serialVersionUID = -7171906208410996479L;
	private IDicBIZ dicBiz;
	private int pageIndex;
	private String dicTypeId;
	private DicType dicType;
	private DicData dicData;
	private String dicDataId;

	public IDicBIZ getDicBiz() {
		return dicBiz;
	}

	public void setDicBiz(IDicBIZ dicBiz) {
		this.dicBiz = dicBiz;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getDicTypeId() {
		return dicTypeId;
	}

	public void setDicTypeId(String dicTypeId) {
		this.dicTypeId = dicTypeId;
	}

	public DicType getDicType() {
		return dicType;
	}

	public void setDicType(DicType dicType) {
		this.dicType = dicType;
	}

	public DicData getDicData() {
		return dicData;
	}

	public void setDicData(DicData dicData) {
		this.dicData = dicData;
	}

	public String getDicDataId() {
		return dicDataId;
	}

	public void setDicDataId(String dicDataId) {
		this.dicDataId = dicDataId;
	}

	/**
	 * 字典列表页
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = dicBiz.getDicTypeList(pageIndex, IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("dicType_list_page", page);
		return "list";
	}
	
	/**
	 * 字典数据列表页
	 * @return
	 * @throws Exception
	 */
	public String toDataList() throws Exception {
		List<DicData> dataList = dicBiz.getDicDataByDicTypeId(dicTypeId);
		ActionContext.getContext().put("dicDataList", dataList);
		return "dicDataList";
	}
	
	/**
	 * 添加字典类型
	 * @return
	 * @throws Exception
	 */
	public String addType() throws Exception {
		dicBiz.saveDicType(dicType);
		ActionContext.getContext().put("dicMsg", "保存成功");
		return "toList";
	}
	
	public String toDataAdd() throws Exception {
		return "toDataAdd";
	}
	/**
	 * 添加新字典数据
	 * @return
	 * @throws Exception
	 */
	public String addData() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典类型才能添加字典数据");
			return "toList";
		}
		dicData.setDicTypeId(dicTypeId);
		dicBiz.saveDicData(dicData);
		ActionContext.getContext().put("dicMsg", "保存成功");
		return "toDataAddAction";
	}
	
	/**
	 * 跳转字典数据更新页
	 * @return
	 * @throws Exception
	 */
	public String toDicDataUpdate() throws Exception {
		if (dicDataId == null || dicDataId.equals("")) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典数据");
			return "toDataList";
		}
		DicData dd = dicBiz.getDicDataById(dicDataId);
		if (dd == null) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典数据");
			return "toDataList";
		}
		ActionContext.getContext().put("currDicData", dd);
		return "toDicDataUpdate";
	}
	
	/**
	 * 更新字典数据
	 * @return
	 * @throws Exception
	 */
	public String updateData() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典类型");
			return "toList";
		}
		if (dicData == null) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典数据");
			return "toDataList";
		}
		dicBiz.updateDicData(dicData);
		ActionContext.getContext().put("dicMsg", "保存成功");
		return "toDataList";
	}
	
	/**
	 * 删除字典数据
	 * @return
	 * @throws Exception
	 */
	public String deleteDicData() throws Exception {
		if (dicDataId == null || dicDataId.equals("")) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典数据");
			return "toDataList";
		}
		DicData dd = dicBiz.getDicDataById(dicDataId);
		if (dd == null) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典数据");
			return "toDataList";
		}
		dicBiz.deleteDicData(dd);
		ActionContext.getContext().put("dicMsg", "删除成功");
		return "toDataList";
	}
	
	/**
	 * 跳转更新字典类型页
	 * @return
	 * @throws Exception
	 */
	public String toUpdateDicType() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典类型");
			return "toList";
		}
		DicType dt = dicBiz.getDicTypeById(dicTypeId);
		if (dt == null) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典类型");
			return "toList";
		}
		ActionContext.getContext().put("currDicType", dt);
		return "toUpdateDicType";
	}
	
	/**
	 * 修改字典类型
	 * @return
	 * @throws Exception
	 */
	public String updateType() throws Exception {
		if (dicType == null) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典类型");
			return "toList";
		}
		dicBiz.updateDicType(dicType);
		ActionContext.getContext().put("dicMsg", "修改成功");
		return "toList";
	}
	
	public String deleteDicType() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典类型");
			return "toList";
		}
		DicType dt = dicBiz.getDicTypeById(dicTypeId);
		if (dt == null) {
			ActionContext.getContext().put("dicMsg", "请选择一个字典类型");
			return "toList";
		}
		dicBiz.deleteDicType(dt);
		ActionContext.getContext().put("dicMsg", "删除成功");
		return "toList";
	}
}
