
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.interbankfundtransfers.SchemeCharge;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetSchemeChargesResult" type="{http://schemas.datacontract.org/2004/07/InterbankFundTransfers}SchemeCharge" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getSchemeChargesResult"
})
@XmlRootElement(name = "GetSchemeChargesResponse")
public class GetSchemeChargesResponse {

    @XmlElement(name = "GetSchemeChargesResult")
    protected SchemeCharge getSchemeChargesResult;

    /**
     * Gets the value of the getSchemeChargesResult property.
     * 
     * @return
     *     possible object is
     *     {@link SchemeCharge }
     *     
     */
    public SchemeCharge getGetSchemeChargesResult() {
        return getSchemeChargesResult;
    }

    /**
     * Sets the value of the getSchemeChargesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SchemeCharge }
     *     
     */
    public void setGetSchemeChargesResult(SchemeCharge value) {
        this.getSchemeChargesResult = value;
    }

}
