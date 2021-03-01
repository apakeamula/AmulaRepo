
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.interbankfundtransfers.SNWERESPONSE;


/**
 * <p>Java class for WalletNameEnquiryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WalletNameEnquiryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Request" type="{http://tempuri.org/}WalletNameEnquiryRequest" minOccurs="0"/>
 *         &lt;element name="FIPResponse" type="{http://schemas.datacontract.org/2004/07/InterbankFundTransfers}SNWERESPONSE" minOccurs="0"/>
 *         &lt;element name="AccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResponseMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WalletNameEnquiryResponse", propOrder = {
    "request",
    "fipResponse",
    "accountName",
    "responseCode",
    "responseMessage"
})
public class WalletNameEnquiryResponse2 {

    @XmlElement(name = "Request")
    protected WalletNameEnquiryRequest request;
    @XmlElement(name = "FIPResponse")
    protected SNWERESPONSE fipResponse;
    @XmlElement(name = "AccountName")
    protected String accountName;
    @XmlElement(name = "ResponseCode")
    protected String responseCode;
    @XmlElement(name = "ResponseMessage")
    protected String responseMessage;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link WalletNameEnquiryRequest }
     *     
     */
    public WalletNameEnquiryRequest getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link WalletNameEnquiryRequest }
     *     
     */
    public void setRequest(WalletNameEnquiryRequest value) {
        this.request = value;
    }

    /**
     * Gets the value of the fipResponse property.
     * 
     * @return
     *     possible object is
     *     {@link SNWERESPONSE }
     *     
     */
    public SNWERESPONSE getFIPResponse() {
        return fipResponse;
    }

    /**
     * Sets the value of the fipResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link SNWERESPONSE }
     *     
     */
    public void setFIPResponse(SNWERESPONSE value) {
        this.fipResponse = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the responseMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the value of the responseMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

}
