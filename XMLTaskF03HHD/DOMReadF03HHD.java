package domparseF03HHD;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DomReadF03HHD {

  private static final String FILENAME = "XMLF03HHD.xml";

  public static void main(String[] args) {

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {

          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File(FILENAME));

          doc.getDocumentElement().normalize();

          NodeList list1 = doc.getElementsByTagName("film");

          for (int temp = 0; temp < list1.getLength(); temp++) {

              Node node = list1.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  String id1 = element.getAttribute("filmID");
                  String id2 = element.getAttribute("directedby");

                  String title = element.getElementsByTagName("title").item(0).getTextContent();
                  String actor = element.getElementsByTagName("actor").item(0).getTextContent();
                  String year = element.getElementsByTagName("year").item(0).getTextContent();
                  String type = element.getElementsByTagName("type").item(0).getTextContent();
                  
                  System.out.println("Film ID : " + id1);
                  System.out.println("Title : " + title);
                  System.out.println("Director : " + id2);
                  System.out.println("Actor: " + actor);
                  System.out.println("Year : " + year);                  
              }
          }
          
          NodeList list2 = doc.getElementsByTagName("director");

          for (int temp = 0; temp < list2.getLength(); temp++) {

              Node node = list2.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  String id1 = element.getAttribute("directID");
                  String id2 = element.getAttribute("created");

                  String name = element.getElementsByTagName("name").item(0).getTextContent();
                  
                  System.out.println("Director ID: " + id1);
                  System.out.println("Director : " + name);
          }

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }

  }

}