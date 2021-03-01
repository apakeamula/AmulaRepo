
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.datacontract.schemas._2004._07.interbankfundtransfers.CustomAccountCharges;


/**
 * <p>Java class for PaymentCustomAccountChargeRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentCustomAccountChargeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FromAcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToAcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToAccName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Narration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TraceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TranStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TranRefNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Charges" type="{http://schemas.datacontract.org/2004/07/InterbankFundTransfers}CustomAccountCharges" minOccurs="0"/>
 *         &lt;element name="ServiceID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsReversed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentCustomAccountChargeRequest", propOrder = {
    "fromAcc",
    "toAcc",
    "toBankCode",
    "toAccName",
    "amount",
    "narration",
    "traceID",
    "tranStatus",
    "tranRefNo",
    "requestDate",
    "charges",
    "serviceID",
    "isReversed"
})
public class PaymentCustomAccountChargeRequest {

    @XmlElement(name = "FromAcc")
    protected String fromAcc;
    @XmlElement(name = "ToAcc")
    protected String toAcc;
    @XmlElement(name = "ToBankCode")
    protected String toBankCode;
    @XmlElement(name = "ToAccName")
    protected String toAccName;
    @XmlElement(name = "Amount")
    protected String amount;
    @XmlElement(name = "Narration")
    protected String narration;
    @XmlElement(name = "TraceID")
    protected String traceID;
    @XmlElement(name = "TranStatus")
    protected int tranStatus;
    @XmlElement(name = "TranRefNo")
    protected String tranRefNo;
    @XmlElement(name = "RequestDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestDate;
    @XmlElement(name = "Charges")
    protected CustomAccountCharges charges;
    @XmlElement(name = "ServiceID")
    protected int serviceID;
    @XmlElement(name = "IsReversed")
    protected boolean isReversed;

    /**
     * Gets the value of the fromAcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromAcc() {
        return fromAcc;
    }

    /**
     * Sets the value of the fromAcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromAcc(String value) {
        this.fromAcc = value;
    }

    /**
     * Gets the value of the toAcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToAcc() {
        return toAcc;
    }

    /**
     * Sets the value of the toAcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToAcc(String value) {
        this.toAcc = value;
    }

    /**
     * Gets the value of the toBankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToBankCode() {
        return toBankCode;
    }

    /**
     * Sets the value of the toBankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToBankCode(String value) {
        this.toBankCode = value;
    }

    /**
     * Gets the value of the toAccName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToAccName() {
        return toAccName;
    }

    /**
     * Sets the value of the toAccName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToAccName(String value) {
        this.toAccName = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
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

    /**
     * Gets the value of the traceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraceID() {
        return traceID;
    }

    /**
     * Sets the value of the traceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraceID(String value) {
        this.traceID = value;
    }

    /**
     * Gets the value of the tranStatus property.
     * 
     */
    public int getTranStatus() {
        return tranStatus;
    }

    /**
     * Sets the value of the tranStatus property.
     * 
     */
    public void setTranStatus(int value) {
        this.tranStatus = value;
    }

    /**
     * Gets the value of the tranRefNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranRefNo() {
        return tranRefNo;
    }

    /**
     * Sets the value of the tranRefNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranRefNo(String value) {
        this.tranRefNo = value;
    }

    /**
     * Gets the value of the requestDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestDate() {
        return requestDate;
    }

    /**
     * Sets the value of the requestDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDate(XMLGregorianCalendar value) {
        this.requestDate = value;
    }

    /**
     * Gets the value of the charges property.
     * 
     * @return
     *     possible object is
     *     {@link CustomAccountCharges }
     *     
     */
    public CustomAccountCharges getCharges() {
        return charges;
    }

    /**
     * Sets the value of the charges property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomAccountCharges }
     *     
     */
    public void setCharges(CustomAccountCharges value) {
        this.charges = value;
    }

    /**
     * Gets the value of the serviceID property.
     * 
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     */
    public void setServiceID(int value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the isReversed property.
     * 
     */
    public boolean isIsReversed() {
        return isReversed;
    }

    /**
     * Sets the value of the isReversed property.
     * 
     */
    public void setIsReversed(boolean value) {
        this.isReversed = value;
    }

}
