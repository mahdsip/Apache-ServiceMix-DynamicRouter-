package es.altana.md.gestordominios;

import java.io.File;
import java.io.StringReader;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


/**
 * @author miguelangel.huerta
 */
public class GestorDominio1 {
    protected static Log log = LogFactory.getLog(GestorDominio1.class.getName());
    private String ficheroRespuesta;

    public GestorDominio1(String ficheroRespuesta) {
        this.ficheroRespuesta = ficheroRespuesta;
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
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        //InputSource is = new InputSource(new StringReader(content));
        Document document = builder.parse(URI);
        // Create SOAPMessage
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();
        message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        //SOAPBody body = message.getSOAPBody();
        SOAPBody body = (SOAPBody) envelope.getBody();
        envelope.getBody().detachNode();
        body.addDocument(document);
//        body.addDocument(document);
        return message;
    }


    public SOAPMessage onMessage() {
        SOAPMessage messageResp = null;
        Object mensaje = null;
        //MessageConverter messageConverter = null;

        try {
            messageResp = createMessageFromFile(ficheroRespuesta);
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


    private SOAPMessage crearMensaje(String path) throws Exception {

        String ficheroXml = path;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(ficheroXml));
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();
        SOAPBody body = message.getSOAPBody();
        body.addDocument(document);
        return message;
    }

}
