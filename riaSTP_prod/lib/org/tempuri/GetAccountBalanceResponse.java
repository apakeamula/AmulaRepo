
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
 *         &lt;element name="GetAccountBalanceResult" type="{http://tempuri.org/}AccountBalanceObj" minOccurs="0"/>
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
    "getAccountBalanceResult"
})
@XmlRootElement(name = "GetAccountBalanceResponse")
public class GetAccountBalanceResponse {

    @XmlElement(name = "GetAccountBalanceResult")
    protected AccountBalanceObj getAccountBalanceResult;

    /**
     * Gets the value of the getAccountBalanceResult property.
     * 
     * @return
     *     possible object is
     *     {@link AccountBalanceObj }
     *     
     */
    public AccountBalanceObj getGetAccountBalanceResult() {
        return getAccountBalanceResult;
    }

    /**
     * Sets the value of the getAccountBalanceResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountBalanceObj }
     *     
     */
    public void setGetAccountBalanceResult(AccountBalanceObj value) {
        this.getAccountBalanceResult = value;
    }

}
