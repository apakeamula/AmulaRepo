
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
 *         &lt;element name="QueryTransactionResult" type="{http://tempuri.org/}ArrayOfPaymentResponse" minOccurs="0"/>
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
    "queryTransactionResult"
})
@XmlRootElement(name = "QueryTransactionResponse")
public class QueryTransactionResponse {

    @XmlElement(name = "QueryTransactionResult")
    protected ArrayOfPaymentResponse queryTransactionResult;

    /**
     * Gets the value of the queryTransactionResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPaymentResponse }
     *     
     */
    public ArrayOfPaymentResponse getQueryTransactionResult() {
        return queryTransactionResult;
    }

    /**
     * Sets the value of the queryTransactionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPaymentResponse }
     *     
     */
    public void setQueryTransactionResult(ArrayOfPaymentResponse value) {
        this.queryTransactionResult = value;
    }

}
