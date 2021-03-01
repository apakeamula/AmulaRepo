
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
 *         &lt;element name="PostFIPCustomChargesResult" type="{http://tempuri.org/}PaymentCustomChargeResponse" minOccurs="0"/>
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
    "postFIPCustomChargesResult"
})
@XmlRootElement(name = "PostFIPCustomChargesResponse")
public class PostFIPCustomChargesResponse {

    @XmlElement(name = "PostFIPCustomChargesResult")
    protected PaymentCustomChargeResponse postFIPCustomChargesResult;

    /**
     * Gets the value of the postFIPCustomChargesResult property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentCustomChargeResponse }
     *     
     */
    public PaymentCustomChargeResponse getPostFIPCustomChargesResult() {
        return postFIPCustomChargesResult;
    }

    /**
     * Sets the value of the postFIPCustomChargesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentCustomChargeResponse }
     *     
     */
    public void setPostFIPCustomChargesResult(PaymentCustomChargeResponse value) {
        this.postFIPCustomChargesResult = value;
    }

}
