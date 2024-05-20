import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.Scanner;
import org.json.JSONObject;

public class App{
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

            // Iterate through each element
            for (int i = 0; i < recordList.getLength(); i++) {
                Element record = (Element) recordList.item(i);

                // Create a JSON object to hold selected fields for this record
                JSONObject jsonObject = new JSONObject();

                // Add selected fields to the JSON object
                for (String fieldName : fieldNames) {
                    String fieldValue = getFieldValue(record, fieldName.trim());
                    jsonObject.put(fieldName.trim(), fieldValue);
                }

                // Print the JSON object for this record
                System.out.println(jsonObject.toString());

                System.out.println("-------------------------");
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to get the value of a field from a record element
    private static String getFieldValue(Element record, String fieldName) {
        NodeList fields = record.getElementsByTagName(fieldName);
        if (fields.getLength() > 0) {
            return fields.item(0).getTextContent();
        } else {
            return "Not found";
        }
    }
}
