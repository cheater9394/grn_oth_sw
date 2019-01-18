
package vilsmeier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse fur transaktion complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="transaktion">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.vilsmeier/}generatedIdEntity">
 *       &lt;sequence>
 *         &lt;element name="abgeschlossen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="bankVon" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bankZu" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="betrag" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="typ" type="{http://service.vilsmeier/}transaktionstyp" minOccurs="0"/>
 *         &lt;element name="verwendungszweck" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="von" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="zu" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transaktion", propOrder = {
    "abgeschlossen",
    "bankVon",
    "bankZu",
    "betrag",
    "datum",
    "typ",
    "verwendungszweck",
    "von",
    "zu"
})
public class Transaktion
    extends GeneratedIdEntity
{

    protected boolean abgeschlossen;
    protected long bankVon;
    protected long bankZu;
    protected long betrag;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datum;
    protected Transaktionstyp typ;
    protected String verwendungszweck;
    protected long von;
    protected long zu;

    /**
     * Ruft den Wert der abgeschlossen-Eigenschaft ab.
     * 
     */
    public boolean isAbgeschlossen() {
        return abgeschlossen;
    }

    /**
     * Legt den Wert der abgeschlossen-Eigenschaft fest.
     * 
     */
    public void setAbgeschlossen(boolean value) {
        this.abgeschlossen = value;
    }

    /**
     * Ruft den Wert der bankVon-Eigenschaft ab.
     * 
     */
    public long getBankVon() {
        return bankVon;
    }

    /**
     * Legt den Wert der bankVon-Eigenschaft fest.
     * 
     */
    public void setBankVon(long value) {
        this.bankVon = value;
    }

    /**
     * Ruft den Wert der bankZu-Eigenschaft ab.
     * 
     */
    public long getBankZu() {
        return bankZu;
    }

    /**
     * Legt den Wert der bankZu-Eigenschaft fest.
     * 
     */
    public void setBankZu(long value) {
        this.bankZu = value;
    }

    /**
     * Ruft den Wert der betrag-Eigenschaft ab.
     * 
     */
    public long getBetrag() {
        return betrag;
    }

    /**
     * Legt den Wert der betrag-Eigenschaft fest.
     * 
     */
    public void setBetrag(long value) {
        this.betrag = value;
    }

    /**
     * Ruft den Wert der datum-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Legt den Wert der datum-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Ruft den Wert der typ-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Transaktionstyp }
     *     
     */
    public Transaktionstyp getTyp() {
        return typ;
    }

    /**
     * Legt den Wert der typ-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Transaktionstyp }
     *     
     */
    public void setTyp(Transaktionstyp value) {
        this.typ = value;
    }

    /**
     * Ruft den Wert der verwendungszweck-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerwendungszweck() {
        return verwendungszweck;
    }

    /**
     * Legt den Wert der verwendungszweck-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerwendungszweck(String value) {
        this.verwendungszweck = value;
    }

    /**
     * Ruft den Wert der von-Eigenschaft ab.
     * 
     */
    public long getVon() {
        return von;
    }

    /**
     * Legt den Wert der von-Eigenschaft fest.
     * 
     */
    public void setVon(long value) {
        this.von = value;
    }

    /**
     * Ruft den Wert der zu-Eigenschaft ab.
     * 
     */
    public long getZu() {
        return zu;
    }

    /**
     * Legt den Wert der zu-Eigenschaft fest.
     * 
     */
    public void setZu(long value) {
        this.zu = value;
    }

}
