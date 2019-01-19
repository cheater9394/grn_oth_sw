
package vilsmeier;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TransaktionsServiceService", targetNamespace = "http://service.vilsmeier/", wsdlLocation = "http://im-lamport:8080/vilsmeierBank-0.1/TransaktionsService?wsdl")
public class TransaktionsServiceService
    extends Service
{

    private final static URL TRANSAKTIONSSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException TRANSAKTIONSSERVICESERVICE_EXCEPTION;
    private final static QName TRANSAKTIONSSERVICESERVICE_QNAME = new QName("http://service.vilsmeier/", "TransaktionsServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://im-lamport:8080/vilsmeierBank-0.1/TransaktionsService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRANSAKTIONSSERVICESERVICE_WSDL_LOCATION = url;
        TRANSAKTIONSSERVICESERVICE_EXCEPTION = e;
    }

    public TransaktionsServiceService() {
        super(__getWsdlLocation(), TRANSAKTIONSSERVICESERVICE_QNAME);
    }

    public TransaktionsServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRANSAKTIONSSERVICESERVICE_QNAME, features);
    }

    public TransaktionsServiceService(URL wsdlLocation) {
        super(wsdlLocation, TRANSAKTIONSSERVICESERVICE_QNAME);
    }

    public TransaktionsServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRANSAKTIONSSERVICESERVICE_QNAME, features);
    }

    public TransaktionsServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TransaktionsServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TransaktionsService
     */
    @WebEndpoint(name = "TransaktionsServicePort")
    public TransaktionsService getTransaktionsServicePort() {
        return super.getPort(new QName("http://service.vilsmeier/", "TransaktionsServicePort"), TransaktionsService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TransaktionsService
     */
    @WebEndpoint(name = "TransaktionsServicePort")
    public TransaktionsService getTransaktionsServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.vilsmeier/", "TransaktionsServicePort"), TransaktionsService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TRANSAKTIONSSERVICESERVICE_EXCEPTION!= null) {
            throw TRANSAKTIONSSERVICESERVICE_EXCEPTION;
        }
        return TRANSAKTIONSSERVICESERVICE_WSDL_LOCATION;
    }

}