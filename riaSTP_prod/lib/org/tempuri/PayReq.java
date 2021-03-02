
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PayReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PayReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://tempuri.org/}PaymentRequest">
 *       &lt;sequence>
 *         &lt;element name="FromCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayReq", propOrder = {
    "fromCustID"
})
public class PayReq
    extends PaymentRequest
{

    @XmlElement(name = "FromCustID")
    protected String fromCustID;

    /**
     * Gets the value of the fromCustID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromCustID() {
        return fromCustID;
    }

    /**
     * Sets the value of the fromCustID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromCustID(String value) {
        this.fromCustID = value;
    }

}
