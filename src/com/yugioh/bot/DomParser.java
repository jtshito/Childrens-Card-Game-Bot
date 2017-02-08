package com.yugioh.bot;
import java.io.*;

import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomParser {
	public String filename;
	private File inputFile;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	public DomParser(String filename) {
		this.filename = filename;
		this.inputFile = new File(this.filename);
		this.dbFactory = DocumentBuilderFactory.newInstance();
		try {
			this.dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.doc = dBuilder.parse(inputFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//private int[] parseCoordinates() {
		//int[] coords = {0,0,0,0,0,0};
		//coords[0] = doc.getFirstChild().getAttributes().
		//return coords;
		
	//}
}
