
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
 *         &lt;element name="GetBanksResult" type="{http://tempuri.org/}ArrayOfBank" minOccurs="0"/>
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
    "getBanksResult"
})
@XmlRootElement(name = "GetBanksResponse")
public class GetBanksResponse {

    @XmlElement(name = "GetBanksResult")
    protected ArrayOfBank getBanksResult;

    /**
     * Gets the value of the getBanksResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBank }
     *     
     */
    public ArrayOfBank getGetBanksResult() {
        return getBanksResult;
    }

    /**
     * Sets the value of the getBanksResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBank }
     *     
     */
    public void setGetBanksResult(ArrayOfBank value) {
        this.getBanksResult = value;
    }

}
