
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
 *         &lt;element name="NeftNapsEnquiryResult" type="{http://tempuri.org/}NAPSNeftEnquiryResponse" minOccurs="0"/>
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
    "neftNapsEnquiryResult"
})
@XmlRootElement(name = "NeftNapsEnquiryResponse")
public class NeftNapsEnquiryResponse {

    @XmlElement(name = "NeftNapsEnquiryResult")
    protected NAPSNeftEnquiryResponse neftNapsEnquiryResult;

    /**
     * Gets the value of the neftNapsEnquiryResult property.
     * 
     * @return
     *     possible object is
     *     {@link NAPSNeftEnquiryResponse }
     *     
     */
    public NAPSNeftEnquiryResponse getNeftNapsEnquiryResult() {
        return neftNapsEnquiryResult;
    }

    /**
     * Sets the value of the neftNapsEnquiryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link NAPSNeftEnquiryResponse }
     *     
     */
    public void setNeftNapsEnquiryResult(NAPSNeftEnquiryResponse value) {
        this.neftNapsEnquiryResult = value;
    }

}
