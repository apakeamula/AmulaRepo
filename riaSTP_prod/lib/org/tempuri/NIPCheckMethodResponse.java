
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.interbankfundtransfers.NIPCheckResponse;


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
 *         &lt;element name="NIPCheckMethodResult" type="{http://schemas.datacontract.org/2004/07/InterbankFundTransfers}NIPCheckResponse" minOccurs="0"/>
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
    "nipCheckMethodResult"
})
@XmlRootElement(name = "NIPCheckMethodResponse")
public class NIPCheckMethodResponse {

    @XmlElement(name = "NIPCheckMethodResult")
    protected NIPCheckResponse nipCheckMethodResult;

    /**
     * Gets the value of the nipCheckMethodResult property.
     * 
     * @return
     *     possible object is
     *     {@link NIPCheckResponse }
     *     
     */
    public NIPCheckResponse getNIPCheckMethodResult() {
        return nipCheckMethodResult;
    }

    /**
     * Sets the value of the nipCheckMethodResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link NIPCheckResponse }
     *     
     */
    public void setNIPCheckMethodResult(NIPCheckResponse value) {
        this.nipCheckMethodResult = value;
    }

}
