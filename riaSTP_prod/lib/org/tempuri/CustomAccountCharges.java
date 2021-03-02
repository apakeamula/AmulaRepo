
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomAccountCharges complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomAccountCharges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NIP_COMM" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="NIP_VAT" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="BANK_COMM" type="{http://tempuri.org/}KeyValuePairOfStringDecimal"/>
 *         &lt;element name="VAT" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomAccountCharges", propOrder = {
    "nipcomm",
    "nipvat",
    "bankcomm",
    "vat"
})
public class CustomAccountCharges {

    @XmlElement(name = "NIP_COMM", required = true)
    protected BigDecimal nipcomm;
    @XmlElement(name = "NIP_VAT", required = true)
    protected BigDecimal nipvat;
    @XmlElement(name = "BANK_COMM", required = true)
    protected KeyValuePairOfStringDecimal bankcomm;
    @XmlElement(name = "VAT", required = true)
    protected BigDecimal vat;

    /**
     * Gets the value of the nipcomm property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNIPCOMM() {
        return nipcomm;
    }

    /**
     * Sets the value of the nipcomm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNIPCOMM(BigDecimal value) {
        this.nipcomm = value;
    }

    /**
     * Gets the value of the nipvat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNIPVAT() {
        return nipvat;
    }

    /**
     * Sets the value of the nipvat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNIPVAT(BigDecimal value) {
        this.nipvat = value;
    }

    /**
     * Gets the value of the bankcomm property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValuePairOfStringDecimal }
     *     
     */
    public KeyValuePairOfStringDecimal getBANKCOMM() {
        return bankcomm;
    }

    /**
     * Sets the value of the bankcomm property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValuePairOfStringDecimal }
     *     
     */
    public void setBANKCOMM(KeyValuePairOfStringDecimal value) {
        this.bankcomm = value;
    }

    /**
     * Gets the value of the vat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVAT() {
        return vat;
    }

    /**
     * Sets the value of the vat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVAT(BigDecimal value) {
        this.vat = value;
    }

}
