package domparseF03HHD;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyF03HHD {
    public static void main(String[] args) {
        String filePath = "XMLF03HHD.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            delete(doc);

            newAdd(doc);

            writeXMLFile(doc);

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    private static void writeXMLFile(Document doc)
    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("modifiedmovie.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML modified");
    }

    private static void newAdd(Document doc) {
        NodeList movie = doc.getElementsByTagName("Film");
        Element emp = null;

        for (int i = 0; i < movie.getLength(); i++) {
            emp = (Element) movie.item(i);
            Element awardElement = doc.createElement("Award");
            awardElement.appendChild(doc.createTextNode("Oscar Award"));
            emp.appendChild(awardElement);
        }
    }

    private static void delete(Document doc) {
        NodeList movie = doc.getElementsByTagName("Film");
        Element film = null;
        for (int i = 0; i < movie.getLength(); i++) {
            film = (Element) movie.item(i);
            Node yearNode = film.getElementsByTagName("Year").item(0);
            film.removeChild(yearNode);
        }

    }

}