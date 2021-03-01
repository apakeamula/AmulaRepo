
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
 *         &lt;element name="PostReversalByRefNoResult" type="{http://tempuri.org/}PaymentResponse" minOccurs="0"/>
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
    "postReversalByRefNoResult"
})
@XmlRootElement(name = "PostReversalByRefNoResponse")
public class PostReversalByRefNoResponse {

    @XmlElement(name = "PostReversalByRefNoResult")
    protected PaymentResponse postReversalByRefNoResult;

    /**
     * Gets the value of the postReversalByRefNoResult property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentResponse }
     *     
     */
    public PaymentResponse getPostReversalByRefNoResult() {
        return postReversalByRefNoResult;
    }

    /**
     * Sets the value of the postReversalByRefNoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentResponse }
     *     
     */
    public void setPostReversalByRefNoResult(PaymentResponse value) {
        this.postReversalByRefNoResult = value;
    }

}
