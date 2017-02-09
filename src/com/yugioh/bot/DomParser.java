package com.yugioh.bot;
import java.io.*;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomParser extends SAXBuilder{
	public String filename;
	private File inputFile;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	private List list;
	private Element rootNode;
	public DomParser(String filename) {
		this.filename = filename;
		this.inputFile = new File(this.filename);
		
		try {
			this.doc = (Document)build(inputFile);
			this.rootNode =  doc.getRootElement();
			this.list = rootNode.getChildren();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List parseCoordinates() {
		List<Integer> coords = new ArrayList();
		for(int i = 0; i < 4; i++) {
			Element node = (Element) list.get(i);

			coords.add(Integer.parseInt((node.getAttributeValue("xcoord"))));
			coords.add(Integer.parseInt((node.getAttributeValue("ycoord"))));
		}
		for(int i = 4; i < 6; i++) {
			Element node = (Element) list.get(i);

			coords.add(Integer.parseInt((node.getAttributeValue("red"))));
			coords.add(Integer.parseInt((node.getAttributeValue("green"))));
			coords.add(Integer.parseInt((node.getAttributeValue("blue"))));
		}
		System.out.println(coords.toString());
		return coords;
	}
	public void modifyCoordinates(List<Integer> coords) {
		for(int i = 0; i < 4; i++) {
			Element node = (Element)list.get(i);
			System.out.println("Old values for " + node.getName() + " is " + node.getAttributeValue("xcoord") + ", " + node.getAttributeValue("ycoord"));
			node.setAttribute("xcoord", Integer.toString((Integer) coords.remove(0)));
			node.setAttribute("ycoord", Integer.toString((Integer) coords.remove(0)));
			System.out.println("New values for " + node.getName() + " is " + node.getAttributeValue("xcoord") + ", " + node.getAttributeValue("ycoord"));
			
		}
		for(int i = 4; i < 6; i++) {
			Element node = (Element) list.get(i);
			System.out.println("Old values for " + node.getName() + " is " + node.getAttributeValue("red") + ", " + node.getAttributeValue("green") + ", " + node.getAttributeValue("blue"));
			node.setAttribute("red", Integer.toString((Integer) coords.remove(0)));
			node.setAttribute("green", Integer.toString((Integer) coords.remove(0)));
			node.setAttribute("blue", Integer.toString((Integer) coords.remove(0)));
			System.out.println("New values for " + node.getName() + " is " + node.getAttributeValue("red") + ", " + node.getAttributeValue("green") + ", " + node.getAttributeValue("blue"));
			
		}
		XMLOutputter xmlOutput = new XMLOutputter();
		try {
			xmlOutput.output(doc, new FileWriter("config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
