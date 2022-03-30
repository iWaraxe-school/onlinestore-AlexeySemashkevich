package by.issoft.store.helpers.comparators;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class XmlReader {

   private List<SortConfig> sortOrderList= new ArrayList<>();


    public List<SortConfig> getSortOrderList() {
    try {

        FileInputStream file = new FileInputStream(new File("OnlineShop_parent/store/src/main/resources/config.xml"));
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =  builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(file);
        NodeList nodeList = xmlDocument.getDocumentElement().getElementsByTagName("sortBy");

        IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item).
                map(Node::getAttributes).
                forEach(attributes -> sortOrderList.add(new SortConfig(attributes.getNamedItem("field").getNodeValue(), attributes.getNamedItem("order").getNodeValue())));

    } catch (ParserConfigurationException ex) {
        ex.printStackTrace();
    } catch (SAXException ex) {
        ex.printStackTrace();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return sortOrderList;}








}

