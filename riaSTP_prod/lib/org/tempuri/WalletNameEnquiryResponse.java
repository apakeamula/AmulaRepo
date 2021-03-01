
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
 *         &lt;element name="WalletNameEnquiryResult" type="{http://tempuri.org/}WalletNameEnquiryResponse" minOccurs="0"/>
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
    "walletNameEnquiryResult"
})
@XmlRootElement(name = "WalletNameEnquiryResponse")
public class WalletNameEnquiryResponse {

    @XmlElement(name = "WalletNameEnquiryResult")
    protected WalletNameEnquiryResponse2 walletNameEnquiryResult;

    /**
     * Gets the value of the walletNameEnquiryResult property.
     * 
     * @return
     *     possible object is
     *     {@link WalletNameEnquiryResponse2 }
     *     
     */
    public WalletNameEnquiryResponse2 getWalletNameEnquiryResult() {
        return walletNameEnquiryResult;
    }

    /**
     * Sets the value of the walletNameEnquiryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WalletNameEnquiryResponse2 }
     *     
     */
    public void setWalletNameEnquiryResult(WalletNameEnquiryResponse2 value) {
        this.walletNameEnquiryResult = value;
    }

}
