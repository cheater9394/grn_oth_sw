
package vilsmeier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse fur transaktionTaetigen complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="transaktionTaetigen"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="transaktion" type="{http://service.vilsmeier/}transaktion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transaktionTaetigen", propOrder = {
    "transaktion"
})
public class TransaktionTaetigen {

    protected Transaktion transaktion;

    /**
     * Ruft den Wert der transaktion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Transaktion }
     *     
     */
    public Transaktion getTransaktion() {
        return transaktion;
    }

    /**
     * Legt den Wert der transaktion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Transaktion }
     *     
     */
    public void setTransaktion(Transaktion value) {
        this.transaktion = value;
    }

}
