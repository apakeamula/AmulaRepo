
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="tran" type="{http://tempuri.org/}PayReq" minOccurs="0"/>
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
    "tran"
})
@XmlRootElement(name = "PostTransReq")
public class PostTransReq {

    protected PayReq tran;

    /**
     * Gets the value of the tran property.
     * 
     * @return
     *     possible object is
     *     {@link PayReq }
     *     
     */
    public PayReq getTran() {
        return tran;
    }

    /**
     * Sets the value of the tran property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayReq }
     *     
     */
    public void setTran(PayReq value) {
        this.tran = value;
    }

}
