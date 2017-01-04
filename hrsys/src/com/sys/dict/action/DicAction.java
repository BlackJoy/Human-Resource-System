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
	 * �ֵ��б�ҳ
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
	 * �ֵ������б�ҳ
	 * @return
	 * @throws Exception
	 */
	public String toDataList() throws Exception {
		List<DicData> dataList = dicBiz.getDicDataByDicTypeId(dicTypeId);
		ActionContext.getContext().put("dicDataList", dataList);
		return "dicDataList";
	}
	
	/**
	 * ����ֵ�����
	 * @return
	 * @throws Exception
	 */
	public String addType() throws Exception {
		dicBiz.saveDicType(dicType);
		ActionContext.getContext().put("dicMsg", "����ɹ�");
		return "toList";
	}
	
	public String toDataAdd() throws Exception {
		return "toDataAdd";
	}
	/**
	 * ������ֵ�����
	 * @return
	 * @throws Exception
	 */
	public String addData() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ����Ͳ�������ֵ�����");
			return "toList";
		}
		dicData.setDicTypeId(dicTypeId);
		dicBiz.saveDicData(dicData);
		ActionContext.getContext().put("dicMsg", "����ɹ�");
		return "toDataAddAction";
	}
	
	/**
	 * ��ת�ֵ����ݸ���ҳ
	 * @return
	 * @throws Exception
	 */
	public String toDicDataUpdate() throws Exception {
		if (dicDataId == null || dicDataId.equals("")) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toDataList";
		}
		DicData dd = dicBiz.getDicDataById(dicDataId);
		if (dd == null) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toDataList";
		}
		ActionContext.getContext().put("currDicData", dd);
		return "toDicDataUpdate";
	}
	
	/**
	 * �����ֵ�����
	 * @return
	 * @throws Exception
	 */
	public String updateData() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toList";
		}
		if (dicData == null) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toDataList";
		}
		dicBiz.updateDicData(dicData);
		ActionContext.getContext().put("dicMsg", "����ɹ�");
		return "toDataList";
	}
	
	/**
	 * ɾ���ֵ�����
	 * @return
	 * @throws Exception
	 */
	public String deleteDicData() throws Exception {
		if (dicDataId == null || dicDataId.equals("")) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toDataList";
		}
		DicData dd = dicBiz.getDicDataById(dicDataId);
		if (dd == null) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toDataList";
		}
		dicBiz.deleteDicData(dd);
		ActionContext.getContext().put("dicMsg", "ɾ���ɹ�");
		return "toDataList";
	}
	
	/**
	 * ��ת�����ֵ�����ҳ
	 * @return
	 * @throws Exception
	 */
	public String toUpdateDicType() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toList";
		}
		DicType dt = dicBiz.getDicTypeById(dicTypeId);
		if (dt == null) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toList";
		}
		ActionContext.getContext().put("currDicType", dt);
		return "toUpdateDicType";
	}
	
	/**
	 * �޸��ֵ�����
	 * @return
	 * @throws Exception
	 */
	public String updateType() throws Exception {
		if (dicType == null) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toList";
		}
		dicBiz.updateDicType(dicType);
		ActionContext.getContext().put("dicMsg", "�޸ĳɹ�");
		return "toList";
	}
	
	public String deleteDicType() throws Exception {
		if (dicTypeId == null || dicTypeId.equals("")) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toList";
		}
		DicType dt = dicBiz.getDicTypeById(dicTypeId);
		if (dt == null) {
			ActionContext.getContext().put("dicMsg", "��ѡ��һ���ֵ�����");
			return "toList";
		}
		dicBiz.deleteDicType(dt);
		ActionContext.getContext().put("dicMsg", "ɾ���ɹ�");
		return "toList";
	}
}
