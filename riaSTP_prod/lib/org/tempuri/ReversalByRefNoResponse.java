
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="ReversalByRefNoResult" type="{http://tempuri.org/}PaymentResponseWithCharge" minOccurs="0"/>
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
    "reversalByRefNoResult"
})
@XmlRootElement(name = "ReversalByRefNoResponse")
public class ReversalByRefNoResponse {

    @XmlElement(name = "ReversalByRefNoResult")
    protected PaymentResponseWithCharge reversalByRefNoResult;

    /**
     * Gets the value of the reversalByRefNoResult property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentResponseWithCharge }
     *     
     */
    public PaymentResponseWithCharge getReversalByRefNoResult() {
        return reversalByRefNoResult;
    }

    /**
     * Sets the value of the reversalByRefNoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentResponseWithCharge }
     *     
     */
    public void setReversalByRefNoResult(PaymentResponseWithCharge value) {
        this.reversalByRefNoResult = value;
    }

}
