package com.sys.approve;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sys.menu.Menu;

public class LoadApproveContentConfig {

	private Document doc;
	
	public LoadApproveContentConfig(){
		try {
			InputStream in = LoadApproveContentConfig.class.getClassLoader().getResourceAsStream(IApproveContentConstant.APPROVECONTENT_XML);
			doc = new SAXReader().read(in).getDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setProperties(ApproveContent ac, Element element){
		ac.setId(element.attributeValue("id"));
		ac.setName(element.attributeValue("name"));
		ac.setDesc(element.attributeValue("desc"));
		ac.setAction(element.attributeValue("action"));
	}
	
	//º”‘ÿ≈‰÷√–≈œ¢
	@SuppressWarnings("unchecked")
	public List<ApproveContent> loadApproveContent(){
		List<ApproveContent> acList = new ArrayList<ApproveContent>();
		for (Element element : (List<Element>)doc.getRootElement().elements("contentPage")) {
			ApproveContent ac = new ApproveContent();
			setProperties(ac, element);
			acList.add(ac);
		}
		return acList;
	}
}
