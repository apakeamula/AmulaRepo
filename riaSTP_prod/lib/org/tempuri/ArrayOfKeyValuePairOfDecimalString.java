
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfKeyValuePairOfDecimalString complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfKeyValuePairOfDecimalString">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValuePairOfDecimalString" type="{http://tempuri.org/}KeyValuePairOfDecimalString" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfKeyValuePairOfDecimalString", propOrder = {
    "keyValuePairOfDecimalString"
})
public class ArrayOfKeyValuePairOfDecimalString {

    @XmlElement(name = "KeyValuePairOfDecimalString")
    protected List<KeyValuePairOfDecimalString> keyValuePairOfDecimalString;

    /**
     * Gets the value of the keyValuePairOfDecimalString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyValuePairOfDecimalString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyValuePairOfDecimalString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValuePairOfDecimalString }
     * 
     * 
     */
    public List<KeyValuePairOfDecimalString> getKeyValuePairOfDecimalString() {
        if (keyValuePairOfDecimalString == null) {
            keyValuePairOfDecimalString = new ArrayList<KeyValuePairOfDecimalString>();
        }
        return this.keyValuePairOfDecimalString;
    }

}
