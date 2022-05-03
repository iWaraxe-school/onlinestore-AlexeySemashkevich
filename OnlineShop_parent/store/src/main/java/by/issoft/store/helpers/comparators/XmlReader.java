package by.issoft.store.helpers.comparators;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class XmlReader extends Thread {
    private CopyOnWriteArrayList<SortConfig> sortOrderList = new CopyOnWriteArrayList<>();

    private XmlReader() {    }

    private static volatile XmlReader instance;

    public static XmlReader getInstance() {
        XmlReader localInstance = instance;
        if (localInstance == null) {
            synchronized (XmlReader.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new XmlReader();
                }
            }
        }
        return localInstance;
    }

    protected CopyOnWriteArrayList<SortConfig> getSortOrderList() {
        try {

            FileInputStream file = new FileInputStream(new File("OnlineShop_parent/store/src/main/resources/config.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            Document xmlDocument = builderFactory.newDocumentBuilder().parse(file);
            Node root = (Node) xmlDocument.getFirstChild();
            NodeList nodeList = root.getChildNodes();

            int bound = nodeList.getLength();
            IntStream.range(0, bound).filter(i -> nodeList.item(i)
                            .getNodeType() == Node.ELEMENT_NODE)
                    .forEach(i -> {
                        SortConfig sortConfig = new SortConfig(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent().toUpperCase());
                        sortOrderList.add(sortConfig);
                    });
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return  sortOrderList;}

}