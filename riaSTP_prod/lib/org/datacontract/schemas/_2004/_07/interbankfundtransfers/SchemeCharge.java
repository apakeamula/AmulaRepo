
package org.datacontract.schemas._2004._07.interbankfundtransfers;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SchemeCharge complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchemeCharge">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Bank" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BankVAT" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NIBSS" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NIBSSVAT" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchemeCharge", propOrder = {
    "bank",
    "bankVAT",
    "nibss",
    "nibssvat"
})
public class SchemeCharge {

    @XmlElement(name = "Bank")
    protected BigDecimal bank;
    @XmlElement(name = "BankVAT")
    protected BigDecimal bankVAT;
    @XmlElement(name = "NIBSS")
    protected BigDecimal nibss;
    @XmlElement(name = "NIBSSVAT")
    protected BigDecimal nibssvat;

    /**
     * Gets the value of the bank property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBank() {
        return bank;
    }

    /**
     * Sets the value of the bank property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBank(BigDecimal value) {
        this.bank = value;
    }

    /**
     * Gets the value of the bankVAT property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBankVAT() {
        return bankVAT;
    }

    /**
     * Sets the value of the bankVAT property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBankVAT(BigDecimal value) {
        this.bankVAT = value;
    }

    /**
     * Gets the value of the nibss property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNIBSS() {
        return nibss;
    }

    /**
     * Sets the value of the nibss property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNIBSS(BigDecimal value) {
        this.nibss = value;
    }

    /**
     * Gets the value of the nibssvat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNIBSSVAT() {
        return nibssvat;
    }

    /**
     * Sets the value of the nibssvat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNIBSSVAT(BigDecimal value) {
        this.nibssvat = value;
    }

}
