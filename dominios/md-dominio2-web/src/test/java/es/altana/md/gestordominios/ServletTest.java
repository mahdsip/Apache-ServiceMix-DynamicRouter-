package es.altana.md.gestordominios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import junit.framework.TestCase;

public class ServletTest extends TestCase {

	
	public void testCreateMessage()throws Exception {
		
		SOAPMessage messageResp=null;
		try {
			SOAPMessage msg= crearMensaje("C:/Mensaje_consulta.xml");
            String content = extractMessageContent(msg);
            String sFichero = "C:/Tomcat 6.0/huecos/huecoPet.txt";
            File fichero = new File(sFichero);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write(content);
            bw.close();
            messageResp = crearMensaje("C:/Mensaje_Respuesta.xml");
            content = extractMessageContent(messageResp);
            sFichero = "C:/Tomcat 6.0/huecos/huecoResp.txt";
            File fichero2 = new File(sFichero);
            bw = new BufferedWriter(new FileWriter(fichero2));
            bw.write(content);
            bw.close();
        	}catch (Exception e) {
        		e.printStackTrace();
        		String mensaje = new String("<ERROR>Se ha producido una excepción al intentar obtener el mensaje de respuesta</ERROR>");
        		try{
        			messageResp = createMessage((String) mensaje);
        		}catch (Exception e1) {
					e1.printStackTrace();
        		}
         
        	} 
          
        }

private SOAPMessage crearMensaje(String path) throws Exception{
	
	String ficheroXml = path;
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document document = builder.parse( new File(ficheroXml) );
	MessageFactory messageFactory = MessageFactory.newInstance();
	SOAPMessage message = messageFactory.createMessage();
	SOAPBody body = message.getSOAPBody();
	body.addDocument(document);
	return message;
}

protected String extractMessageContent(SOAPMessage msg) throws Exception {
	String content = null;
	//TODO Extract user & password info and store it in the request
	
	// Extract Document from SOAPMessage
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document document = builder.newDocument();
	document.appendChild(document.importNode(msg.getSOAPBody().getFirstChild(), true));
	// Convert org.w3c.dom.Document to String
	Transformer t = TransformerFactory.newInstance().newTransformer();
	t.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
	StringWriter sw = new StringWriter();
	t.transform(new DOMSource(document), new StreamResult(sw));
	content = sw.toString();
	return content;
}

protected SOAPMessage createMessage(String content) throws Exception {
	// Convert String to org.w3c.dom.Document
	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = domFactory.newDocumentBuilder();
	InputSource is = new InputSource(new StringReader(content));
	Document document = builder.parse(is);
	// Create SOAPMessage
	MessageFactory messageFactory = MessageFactory.newInstance();
	SOAPMessage message = messageFactory.createMessage();
	SOAPBody body = message.getSOAPBody();
	body.addDocument(document);
	return message;
}

}
