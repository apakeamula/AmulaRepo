
package org.datacontract.schemas._2004._07.interbankfundtransfers;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomCharges complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomCharges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BANK_COMM" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NIP_COMM" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NIP_VAT" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="VAT" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomCharges", propOrder = {
    "bankcomm",
    "nipcomm",
    "nipvat",
    "vat"
})
public class CustomCharges {

    @XmlElement(name = "BANK_COMM")
    protected BigDecimal bankcomm;
    @XmlElement(name = "NIP_COMM")
    protected BigDecimal nipcomm;
    @XmlElement(name = "NIP_VAT")
    protected BigDecimal nipvat;
    @XmlElement(name = "VAT")
    protected BigDecimal vat;

    /**
     * Gets the value of the bankcomm property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBANKCOMM() {
        return bankcomm;
    }

    /**
     * Sets the value of the bankcomm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBANKCOMM(BigDecimal value) {
        this.bankcomm = value;
    }

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
