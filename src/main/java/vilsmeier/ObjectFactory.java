
package vilsmeier;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vilsmeier package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AlleTransaktionenAnzeigenFuerKonto_QNAME = new QName("http://service.vilsmeier/", "alleTransaktionenAnzeigenFuerKonto");
    private final static QName _AlleTransaktionenAnzeigenFuerKontoResponse_QNAME = new QName("http://service.vilsmeier/", "alleTransaktionenAnzeigenFuerKontoResponse");
    private final static QName _TransaktionDurchfuehren_QNAME = new QName("http://service.vilsmeier/", "transaktionDurchfuehren");
    private final static QName _TransaktionDurchfuehrenResponse_QNAME = new QName("http://service.vilsmeier/", "transaktionDurchfuehrenResponse");
    private final static QName _TransaktionTaetigen_QNAME = new QName("http://service.vilsmeier/", "transaktionTaetigen");
    private final static QName _TransaktionTaetigenResponse_QNAME = new QName("http://service.vilsmeier/", "transaktionTaetigenResponse");
    private final static QName _TransaktionsTypFinden_QNAME = new QName("http://service.vilsmeier/", "transaktionsTypFinden");
    private final static QName _TransaktionsTypFindenResponse_QNAME = new QName("http://service.vilsmeier/", "transaktionsTypFindenResponse");
    private final static QName _TransaktionFailedException_QNAME = new QName("http://service.vilsmeier/", "TransaktionFailedException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vilsmeier
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AlleTransaktionenAnzeigenFuerKonto }
     * 
     */
    public AlleTransaktionenAnzeigenFuerKonto createAlleTransaktionenAnzeigenFuerKonto() {
        return new AlleTransaktionenAnzeigenFuerKonto();
    }

    /**
     * Create an instance of {@link AlleTransaktionenAnzeigenFuerKontoResponse }
     * 
     */
    public AlleTransaktionenAnzeigenFuerKontoResponse createAlleTransaktionenAnzeigenFuerKontoResponse() {
        return new AlleTransaktionenAnzeigenFuerKontoResponse();
    }

    /**
     * Create an instance of {@link TransaktionDurchfuehren }
     * 
     */
    public TransaktionDurchfuehren createTransaktionDurchfuehren() {
        return new TransaktionDurchfuehren();
    }

    /**
     * Create an instance of {@link TransaktionDurchfuehrenResponse }
     * 
     */
    public TransaktionDurchfuehrenResponse createTransaktionDurchfuehrenResponse() {
        return new TransaktionDurchfuehrenResponse();
    }

    /**
     * Create an instance of {@link TransaktionTaetigen }
     * 
     */
    public TransaktionTaetigen createTransaktionTaetigen() {
        return new TransaktionTaetigen();
    }

    /**
     * Create an instance of {@link TransaktionTaetigenResponse }
     * 
     */
    public TransaktionTaetigenResponse createTransaktionTaetigenResponse() {
        return new TransaktionTaetigenResponse();
    }

    /**
     * Create an instance of {@link TransaktionsTypFinden }
     * 
     */
    public TransaktionsTypFinden createTransaktionsTypFinden() {
        return new TransaktionsTypFinden();
    }

    /**
     * Create an instance of {@link TransaktionsTypFindenResponse }
     * 
     */
    public TransaktionsTypFindenResponse createTransaktionsTypFindenResponse() {
        return new TransaktionsTypFindenResponse();
    }

    /**
     * Create an instance of {@link TransaktionFailedException }
     * 
     */
    public TransaktionFailedException createTransaktionFailedException() {
        return new TransaktionFailedException();
    }

    /**
     * Create an instance of {@link Transaktion }
     * 
     */
    public Transaktion createTransaktion() {
        return new Transaktion();
    }

    /**
     * Create an instance of {@link GeneratedIdEntity }
     * 
     */
    public GeneratedIdEntity createGeneratedIdEntity() {
        return new GeneratedIdEntity();
    }

    /**
     * Create an instance of {@link Transaktionstyp }
     * 
     */
    public Transaktionstyp createTransaktionstyp() {
        return new Transaktionstyp();
    }

    /**
     * Create an instance of {@link StringIdEntity }
     * 
     */
    public StringIdEntity createStringIdEntity() {
        return new StringIdEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlleTransaktionenAnzeigenFuerKonto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AlleTransaktionenAnzeigenFuerKonto }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "alleTransaktionenAnzeigenFuerKonto")
    public JAXBElement<AlleTransaktionenAnzeigenFuerKonto> createAlleTransaktionenAnzeigenFuerKonto(AlleTransaktionenAnzeigenFuerKonto value) {
        return new JAXBElement<AlleTransaktionenAnzeigenFuerKonto>(_AlleTransaktionenAnzeigenFuerKonto_QNAME, AlleTransaktionenAnzeigenFuerKonto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlleTransaktionenAnzeigenFuerKontoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AlleTransaktionenAnzeigenFuerKontoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "alleTransaktionenAnzeigenFuerKontoResponse")
    public JAXBElement<AlleTransaktionenAnzeigenFuerKontoResponse> createAlleTransaktionenAnzeigenFuerKontoResponse(AlleTransaktionenAnzeigenFuerKontoResponse value) {
        return new JAXBElement<AlleTransaktionenAnzeigenFuerKontoResponse>(_AlleTransaktionenAnzeigenFuerKontoResponse_QNAME, AlleTransaktionenAnzeigenFuerKontoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransaktionDurchfuehren }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransaktionDurchfuehren }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "transaktionDurchfuehren")
    public JAXBElement<TransaktionDurchfuehren> createTransaktionDurchfuehren(TransaktionDurchfuehren value) {
        return new JAXBElement<TransaktionDurchfuehren>(_TransaktionDurchfuehren_QNAME, TransaktionDurchfuehren.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransaktionDurchfuehrenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransaktionDurchfuehrenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "transaktionDurchfuehrenResponse")
    public JAXBElement<TransaktionDurchfuehrenResponse> createTransaktionDurchfuehrenResponse(TransaktionDurchfuehrenResponse value) {
        return new JAXBElement<TransaktionDurchfuehrenResponse>(_TransaktionDurchfuehrenResponse_QNAME, TransaktionDurchfuehrenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransaktionTaetigen }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransaktionTaetigen }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "transaktionTaetigen")
    public JAXBElement<TransaktionTaetigen> createTransaktionTaetigen(TransaktionTaetigen value) {
        return new JAXBElement<TransaktionTaetigen>(_TransaktionTaetigen_QNAME, TransaktionTaetigen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransaktionTaetigenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransaktionTaetigenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "transaktionTaetigenResponse")
    public JAXBElement<TransaktionTaetigenResponse> createTransaktionTaetigenResponse(TransaktionTaetigenResponse value) {
        return new JAXBElement<TransaktionTaetigenResponse>(_TransaktionTaetigenResponse_QNAME, TransaktionTaetigenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransaktionsTypFinden }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransaktionsTypFinden }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "transaktionsTypFinden")
    public JAXBElement<TransaktionsTypFinden> createTransaktionsTypFinden(TransaktionsTypFinden value) {
        return new JAXBElement<TransaktionsTypFinden>(_TransaktionsTypFinden_QNAME, TransaktionsTypFinden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransaktionsTypFindenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransaktionsTypFindenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "transaktionsTypFindenResponse")
    public JAXBElement<TransaktionsTypFindenResponse> createTransaktionsTypFindenResponse(TransaktionsTypFindenResponse value) {
        return new JAXBElement<TransaktionsTypFindenResponse>(_TransaktionsTypFindenResponse_QNAME, TransaktionsTypFindenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransaktionFailedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransaktionFailedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.vilsmeier/", name = "TransaktionFailedException")
    public JAXBElement<TransaktionFailedException> createTransaktionFailedException(TransaktionFailedException value) {
        return new JAXBElement<TransaktionFailedException>(_TransaktionFailedException_QNAME, TransaktionFailedException.class, null, value);
    }

}
