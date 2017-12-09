package com.org.frameworklib;

import java.io.File;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReadWrite {
	static File testDataXML;
	static DocumentBuilderFactory dbFactory;
	static DocumentBuilder dBuilder;
	static Document doc;

	public static void readTestData(String tcName) {
		try {
			testDataXML = new File("TestData//TestDataSheet.xml");
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(testDataXML);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("testcase");
			Constants.testDataMap = new LinkedHashMap<>();
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (eElement.getAttribute("id").equalsIgnoreCase(tcName)) {
						NodeList list = eElement.getChildNodes();
						for (int i = 1; i < list.getLength(); i = i + 2) {
							Node node = list.item(i);
							Constants.testDataMap.put(node.getNodeName(), node.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
