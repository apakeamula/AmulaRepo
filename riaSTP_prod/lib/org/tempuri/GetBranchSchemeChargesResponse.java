
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.interbankfundtransfers.SchemeCharge;


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
 *         &lt;element name="GetBranchSchemeChargesResult" type="{http://schemas.datacontract.org/2004/07/InterbankFundTransfers}SchemeCharge" minOccurs="0"/>
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
    "getBranchSchemeChargesResult"
})
@XmlRootElement(name = "GetBranchSchemeChargesResponse")
public class GetBranchSchemeChargesResponse {

    @XmlElement(name = "GetBranchSchemeChargesResult")
    protected SchemeCharge getBranchSchemeChargesResult;

    /**
     * Gets the value of the getBranchSchemeChargesResult property.
     * 
     * @return
     *     possible object is
     *     {@link SchemeCharge }
     *     
     */
    public SchemeCharge getGetBranchSchemeChargesResult() {
        return getBranchSchemeChargesResult;
    }

    /**
     * Sets the value of the getBranchSchemeChargesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SchemeCharge }
     *     
     */
    public void setGetBranchSchemeChargesResult(SchemeCharge value) {
        this.getBranchSchemeChargesResult = value;
    }

}
