
package org.tempuri;

import java.math.BigDecimal;
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
 *         &lt;element name="SourceAcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeneficiaryAcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeneficiaryBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Narration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sourceAcc",
    "beneficiaryAcc",
    "beneficiaryBankCode",
    "amount",
    "narration"
})
@XmlRootElement(name = "PostNEFTNAPS")
public class PostNEFTNAPS {

    @XmlElement(name = "SourceAcc")
    protected String sourceAcc;
    @XmlElement(name = "BeneficiaryAcc")
    protected String beneficiaryAcc;
    @XmlElement(name = "BeneficiaryBankCode")
    protected String beneficiaryBankCode;
    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "Narration")
    protected String narration;

    /**
     * Gets the value of the sourceAcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceAcc() {
        return sourceAcc;
    }

    /**
     * Sets the value of the sourceAcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceAcc(String value) {
        this.sourceAcc = value;
    }

    /**
     * Gets the value of the beneficiaryAcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryAcc() {
        return beneficiaryAcc;
    }

    /**
     * Sets the value of the beneficiaryAcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryAcc(String value) {
        this.beneficiaryAcc = value;
    }

    /**
     * Gets the value of the beneficiaryBankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryBankCode() {
        return beneficiaryBankCode;
    }

    /**
     * Sets the value of the beneficiaryBankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryBankCode(String value) {
        this.beneficiaryBankCode = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the narration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNarration() {
        return narration;
    }

    /**
     * Sets the value of the narration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNarration(String value) {
        this.narration = value;
    }

}
