//number 2 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.Scanner;

public class XMLReader {
    public static void main(String[] args) {
        try {
            // Specify the path to your XML file
            String filePath = "desktop/tasktoo/resources/practical2.xml";

            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file to create a Document object
            Document doc = builder.parse(new File(filePath));

            // Get the root element of the XML document
            Element root = doc.getDocumentElement();

            // Get the list of elements
            NodeList recordList = root.getElementsByTagName("record");

            // Create a scanner to read user input
            Scanner scanner = new Scanner(System.in);

            // Prompt the user to input field names
            System.out.print("Enter the field names separated by commas (e.g., field1, field2): ");
            String input = scanner.nextLine();

            // Split the input into an array of field names
            String[] fieldNames = input.split(",");

            // Iterate through each  element
            for (int i = 0; i < recordList.getLength(); i++) {
                Element record = (Element) recordList.item(i);

                // Print out the selected field values
                for (String fieldName : fieldNames) {
                    NodeList fields = record.getElementsByTagName(fieldName.trim());
                    if (fields.getLength() > 0) {
                        System.out.println(fieldName.trim() + ": " + fields.item(0).getTextContent());
                    } else {
                        System.out.println(fieldName.trim() + ": Not found");
                    }
                }

                
                System.out.println("-------------------------");
            }

            
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
