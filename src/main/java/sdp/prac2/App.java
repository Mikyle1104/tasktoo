import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;

public class App {
    public static void main(String[] args) {
        try {
            // Specify the path to your XML file
            String filePath = "tasktoo/resources/practical2.xml";

            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file to create a Document object
            Document doc = builder.parse(new File(filePath));

            // Get the root element of the  document
            Element root = doc.getDocumentElement();

            // Get the list of elements
            NodeList recordList = root.getElementsByTagName("record");

            // Iterate through each  element
            for (int i = 0; i < recordList.getLength(); i++) {
                Element record = (Element) recordList.item(i);

                // Get list of child elements 
                NodeList fields = record.getChildNodes();

                // Print  field values
                for (int j = 0; j < fields.getLength(); j++) {
                    if (fields.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        System.out.println(fields.item(j).getNodeName() + ": " + fields.item(j).getTextContent());
                    }
                }

                
                System.out.println("/////////////////////////////////");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
