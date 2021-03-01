
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
 *         &lt;element name="PostTransactResult" type="{http://tempuri.org/}PaymentResponse" minOccurs="0"/>
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
    "postTransactResult"
})
@XmlRootElement(name = "PostTransactResponse")
public class PostTransactResponse {

    @XmlElement(name = "PostTransactResult")
    protected PaymentResponse postTransactResult;

    /**
     * Gets the value of the postTransactResult property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentResponse }
     *     
     */
    public PaymentResponse getPostTransactResult() {
        return postTransactResult;
    }

    /**
     * Sets the value of the postTransactResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentResponse }
     *     
     */
    public void setPostTransactResult(PaymentResponse value) {
        this.postTransactResult = value;
    }

}
