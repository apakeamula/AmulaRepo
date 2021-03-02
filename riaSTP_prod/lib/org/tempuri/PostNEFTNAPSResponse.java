
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
 *         &lt;element name="PostNEFTNAPSResult" type="{http://tempuri.org/}NAPSNeftRequestResponse" minOccurs="0"/>
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
    "postNEFTNAPSResult"
})
@XmlRootElement(name = "PostNEFTNAPSResponse")
public class PostNEFTNAPSResponse {

    @XmlElement(name = "PostNEFTNAPSResult")
    protected NAPSNeftRequestResponse postNEFTNAPSResult;

    /**
     * Gets the value of the postNEFTNAPSResult property.
     * 
     * @return
     *     possible object is
     *     {@link NAPSNeftRequestResponse }
     *     
     */
    public NAPSNeftRequestResponse getPostNEFTNAPSResult() {
        return postNEFTNAPSResult;
    }

    /**
     * Sets the value of the postNEFTNAPSResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link NAPSNeftRequestResponse }
     *     
     */
    public void setPostNEFTNAPSResult(NAPSNeftRequestResponse value) {
        this.postNEFTNAPSResult = value;
    }

}
