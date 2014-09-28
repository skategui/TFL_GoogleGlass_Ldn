import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by guillaume on 28/09/2014.
 */
public class TFLXmlReader {

    /**
     *
     */
    private String urlFile = "http://www.tfl.gov.uk/tfl/syndication/feeds/cycle-hire/livecyclehireupdates.xml";

    private URLConnection connection;

    private DocumentBuilder builder;


    public TFLXmlReader(){


        URL url = null;
        try {
            url = new URL(urlFile);
            this.connection = url.openConnection();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            this.builder = factory.newDocumentBuilder();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Document updateXMLFile()
    {
        Document doc = null;
        try {
            doc = builder.parse(this.connection.getInputStream());

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public synchronized ArrayList<station> updateDocks()
   {
        return this.getObjects(updateXMLFile());
   }


    private ArrayList<station> getObjects(Document document)
    {
        ArrayList<station> result = new ArrayList<station>();

        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("station");
        for (int getChild = 0; getChild < nodeList.getLength(); getChild++) {

            Node Listnode = nodeList.item(getChild);
            if(Listnode.getNodeType()==Node.ELEMENT_NODE) {
                NodeList ParentNode = Listnode.getChildNodes();
                station current = new station();
                for(int offset = 0; offset < ParentNode.getLength(); offset++) {
                    Node node = ParentNode.item(offset);
                    current = setAttributeInStation(current, node);
                }
            result.add(current);
            }
        }
        return result;
    }

    private station setAttributeInStation(station current, Node node)
    {
        if (constString.ID.equals(node.getNodeName().toString()) == true)
                current.setId(Integer.parseInt(node.getTextContent().toString()));
        if (constString.NAME.equals(node.getNodeName().toString()) == true)
            current.setName(node.getTextContent().toString());
        if (constString.TERMNAME.equals(node.getNodeName().toString()) == true)
            current.setTerminalName(Integer.parseInt(node.getTextContent().toString()));
        if (constString.LAT.equals(node.getNodeName().toString()) == true)
            current.setLatitude(Float.parseFloat(node.getTextContent().toString()));
        if (constString.LONG.equals(node.getNodeName().toString()) == true)
            current.setLongitude(Float.parseFloat(node.getTextContent().toString()));
        if (constString.INSTALLED.equals(node.getNodeName().toString()) == true)
            current.setInstalled(Boolean.parseBoolean(node.getTextContent().toString()));
        if (constString.NBBIKES.equals(node.getNodeName().toString()) == true)
            current.setAvailable(Integer.parseInt(node.getTextContent().toString()));
        if (constString.NBEMPTYDOCKS.equals(node.getNodeName().toString()) == true)
            current.setEmptyDock(Integer.parseInt(node.getTextContent().toString()));
        if (constString.TOTAL.equals(node.getNodeName().toString()) == true)
            current.setTotal(Integer.parseInt(node.getTextContent().toString()));
        return current;
    }

}
