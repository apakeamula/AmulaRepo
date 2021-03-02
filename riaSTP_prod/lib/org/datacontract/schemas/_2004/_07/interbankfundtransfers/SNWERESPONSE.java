
package org.datacontract.schemas._2004._07.interbankfundtransfers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SNWERESPONSE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SNWERESPONSE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcctName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BVN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Channel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DestBankCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Header" type="{http://schemas.datacontract.org/2004/07/InterbankFundTransfers}HeaderResponse"/>
 *         &lt;element name="KYC" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RespCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Response" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Session" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SNWERESPONSE", propOrder = {
    "acctName",
    "bvn",
    "channel",
    "destBankCode",
    "header",
    "kyc",
    "phoneNumber",
    "respCode",
    "response",
    "session"
})
public class SNWERESPONSE {

    @XmlElement(name = "AcctName", required = true, nillable = true)
    protected String acctName;
    @XmlElement(name = "BVN", required = true, nillable = true)
    protected String bvn;
    @XmlElement(name = "Channel")
    protected Integer channel;
    @XmlElement(name = "DestBankCode", required = true, nillable = true)
    protected String destBankCode;
    @XmlElement(name = "Header", required = true, nillable = true)
    protected HeaderResponse header;
    @XmlElement(name = "KYC", nillable = true)
    protected Integer kyc;
    @XmlElement(name = "PhoneNumber", required = true, nillable = true)
    protected String phoneNumber;
    @XmlElement(name = "RespCode")
    protected Integer respCode;
    @XmlElement(name = "Response", required = true, nillable = true)
    protected String response;
    @XmlElement(name = "Session", required = true, nillable = true)
    protected String session;

    /**
     * Gets the value of the acctName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * Sets the value of the acctName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctName(String value) {
        this.acctName = value;
    }

    /**
     * Gets the value of the bvn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBVN() {
        return bvn;
    }

    /**
     * Sets the value of the bvn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBVN(String value) {
        this.bvn = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChannel(Integer value) {
        this.channel = value;
    }

    /**
     * Gets the value of the destBankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestBankCode() {
        return destBankCode;
    }

    /**
     * Sets the value of the destBankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestBankCode(String value) {
        this.destBankCode = value;
    }

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse }
     *     
     */
    public HeaderResponse getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse }
     *     
     */
    public void setHeader(HeaderResponse value) {
        this.header = value;
    }

    /**
     * Gets the value of the kyc property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getKYC() {
        return kyc;
    }

    /**
     * Sets the value of the kyc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setKYC(Integer value) {
        this.kyc = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the respCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRespCode() {
        return respCode;
    }

    /**
     * Sets the value of the respCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRespCode(Integer value) {
        this.respCode = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponse(String value) {
        this.response = value;
    }

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSession(String value) {
        this.session = value;
    }

}
