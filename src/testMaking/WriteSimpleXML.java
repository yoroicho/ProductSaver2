/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

/**
 *
 * @author http://www.techscore.com/tech/Java/JavaSE/DOM/4/
 */
import org.w3c.dom.Document;

import org.w3c.dom.DOMImplementation;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.transform.TransformerFactory;

import javax.xml.transform.Transformer;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

import java.io.File;

import java.io.FileOutputStream;

public class WriteSimpleXML {

    public static void main(String args[]) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        DOMImplementation domImpl = builder.getDOMImplementation();

        Document document = domImpl.createDocument("", "root", null);

        TransformerFactory transFactory = TransformerFactory.newInstance();

        Transformer transformer = transFactory.newTransformer();

        DOMSource source = new DOMSource(document);

        File newXML = new File("newXML.xml");

        FileOutputStream os = new FileOutputStream(newXML);

        StreamResult result = new StreamResult(os);

        transformer.transform(source, result);

    }

}
