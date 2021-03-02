
package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "PostBridge", targetNamespace = "http://tempuri.org/", wsdlLocation = "file:/E:/PostBridgeService/ProductionService/PostBridge.asmx.wsdl")
public class PostBridge
    extends Service
{

    private final static URL POSTBRIDGE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.tempuri.PostBridge.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.tempuri.PostBridge.class.getResource(".");
            url = new URL(baseUrl, "file:/E:/PostBridgeService/ProductionService/PostBridge.asmx.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/E:/PostBridgeService/ProductionService/PostBridge.asmx.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        POSTBRIDGE_WSDL_LOCATION = url;
    }

    public PostBridge(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PostBridge() {
        super(POSTBRIDGE_WSDL_LOCATION, new QName("http://tempuri.org/", "PostBridge"));
    }

    /**
     * 
     * @return
     *     returns PostBridgeSoap
     */
    @WebEndpoint(name = "PostBridgeSoap")
    public PostBridgeSoap getPostBridgeSoap() {
        return super.getPort(new QName("http://tempuri.org/", "PostBridgeSoap"), PostBridgeSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PostBridgeSoap
     */
    @WebEndpoint(name = "PostBridgeSoap")
    public PostBridgeSoap getPostBridgeSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "PostBridgeSoap"), PostBridgeSoap.class, features);
    }

}
