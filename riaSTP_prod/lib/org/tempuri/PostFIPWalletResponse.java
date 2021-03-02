
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
 *         &lt;element name="PostFIPWalletResult" type="{http://tempuri.org/}PaymentWalletResponse" minOccurs="0"/>
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
    "postFIPWalletResult"
})
@XmlRootElement(name = "PostFIPWalletResponse")
public class PostFIPWalletResponse {

    @XmlElement(name = "PostFIPWalletResult")
    protected PaymentWalletResponse postFIPWalletResult;

    /**
     * Gets the value of the postFIPWalletResult property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentWalletResponse }
     *     
     */
    public PaymentWalletResponse getPostFIPWalletResult() {
        return postFIPWalletResult;
    }

    /**
     * Sets the value of the postFIPWalletResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentWalletResponse }
     *     
     */
    public void setPostFIPWalletResult(PaymentWalletResponse value) {
        this.postFIPWalletResult = value;
    }

}
