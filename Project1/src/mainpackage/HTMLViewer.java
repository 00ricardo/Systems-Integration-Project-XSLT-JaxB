package mainpackage;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author david
 */
public class HTMLViewer {
    
    
    public static void main(String[] args) {
        try {
            viewerInterface();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Create a HTML file from a xml and a xls file
     * @throws Exception if occurs an error
     */
    public static void viewerInterface() throws Exception{
        try {
            TransformerFactory tFactory =  TransformerFactory.newInstance();
            //Open the XML and the xslt -> and create a HTML file with the information formated
            Source xslFile = new StreamSource("HTMLViewer.xsl");
            Source xmlFile = new StreamSource("listofauthors.xml");
            OutputStream htmlFile = new FileOutputStream("finalOutputHTML.html");
            Transformer transform = tFactory.newTransformer(xslFile);
            transform.transform(xmlFile, new StreamResult(htmlFile));
	}
	catch (Exception e) {
            throw new Exception("Exception creating HTML page.");
	}
        File htmlFile = new File("finalOutputHTML.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException ex) {
            throw new Exception("Exception open browser to show the HTML page.");
        }
    }
}
