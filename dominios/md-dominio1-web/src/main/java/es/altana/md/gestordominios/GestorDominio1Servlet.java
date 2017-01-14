package es.altana.md.gestordominios;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import javax.xml.messaging.JAXMServlet;
import javax.xml.messaging.ReqRespListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;



/**
 * @author miguelangel.huerta
 *
 */
public class GestorDominio1Servlet extends JAXMServlet implements ReqRespListener {
	protected static Log log = LogFactory.getLog(GestorDominio1Servlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//Configuramos el Contexto de Request para toda la aplicación
		
		super.doPost(req, res);
		
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
	
	protected SOAPMessage createMessageFromFile(String URI) throws Exception {
		//Convert String to org.w3c.dom.Document
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		//InputSource is = new InputSource(new StringReader(content));
		Document document = builder.parse(URI);
		// Create SOAPMessage
		MessageFactory messageFactory = MessageFactory.newInstance();
		
		SOAPMessage message = messageFactory.createMessage();
		SOAPBody body = message.getSOAPBody();
		body.addDocument(document);
		return message;
	}
	
	
	public SOAPMessage onMessage(SOAPMessage msg) {
		SOAPMessage messageResp = null;
        Object mensaje = null;
        //MessageConverter messageConverter = null;

        try {
        	/*
            String content = extractMessageContent(msg);
            String sFichero = "C:/Tomcat 6.0/huecos/huecoPet.txt";
            File fichero = new File(sFichero);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write(content);
            bw.close();
            */
            //messageResp = crearMensaje("C:/Mensaje_Respuesta1.xml");
            messageResp = crearMensaje("C:/Mensaje_Respuesta1.xml");
        	/*
            content = extractMessageContent(messageResp);
            sFichero = "C:/Tomcat 6.0/huecos/huecoResp.txt";
            File fichero2 = new File(sFichero);
            bw = new BufferedWriter(new FileWriter(fichero2));
            bw.write(content);
            bw.close();
            */
        	}	 
        	catch (Exception e) {
            log.error("Se ha producido una excepción al intentar obtener el mensaje de respuesta", e);
            mensaje = new String("<ERROR>Se ha producido una excepción al intentar obtener el mensaje de respuesta del dominio1</ERROR>");
            try {
				messageResp = createMessage((String) mensaje);
			} catch (Exception e1) {
					e1.printStackTrace();
			}
         
        	} 
       
            return messageResp;
          
        }
	

	private SOAPMessage crearMensaje(String path) throws Exception{
		
		String ficheroXml = path;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse( new File(ficheroXml) );
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage message = messageFactory.createMessage();
		SOAPBody body = message.getSOAPBody();
		body.addDocument(document);
		return message;
	}

}
