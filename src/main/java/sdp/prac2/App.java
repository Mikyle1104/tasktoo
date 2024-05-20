
package sdp.prac2;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;

public class App {
    public static void main(String[] args) {
        try {
            // Path to the XML file
            String filePath = "src/resources/practical2.xml";

            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file to create a Document object
            Document doc = builder.parse(new File(filePath));

            // Normalize the document
            doc.getDocumentElement().normalize();

            // Get the list of 'record' elements
            NodeList recordList = doc.getElementsByTagName("record");

            // Iterate through each 'record' element
            for (int i = 0; i < recordList.getLength(); i++) {
                Node recordNode = recordList.item(i);
                if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
                    // Get the list of child elements of 'record'
                    NodeList fieldList = recordNode.getChildNodes();
                    // Iterate through each child element
                    for (int j = 0; j < fieldList.getLength(); j++) {
                        Node fieldNode = fieldList.item(j);
                        if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
                            // Print field name and value
                            System.out.println(fieldNode.getNodeName() + ": " + fieldNode.getTextContent());
                        }
                    }
                    System.out.println(); // Add a newline after each record
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
