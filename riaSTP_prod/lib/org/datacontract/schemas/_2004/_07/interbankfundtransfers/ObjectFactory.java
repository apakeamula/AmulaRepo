
package org.datacontract.schemas._2004._07.interbankfundtransfers;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.interbankfundtransfers package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.interbankfundtransfers
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HeaderResponse }
     * 
     */
    public HeaderResponse createHeaderResponse() {
        return new HeaderResponse();
    }

    /**
     * Create an instance of {@link CustomAccountCharges }
     * 
     */
    public CustomAccountCharges createCustomAccountCharges() {
        return new CustomAccountCharges();
    }

    /**
     * Create an instance of {@link Bank }
     * 
     */
    public Bank createBank() {
        return new Bank();
    }

    /**
     * Create an instance of {@link SchemeCharge }
     * 
     */
    public SchemeCharge createSchemeCharge() {
        return new SchemeCharge();
    }

    /**
     * Create an instance of {@link CustomCharges }
     * 
     */
    public CustomCharges createCustomCharges() {
        return new CustomCharges();
    }

    /**
     * Create an instance of {@link NIPCheckResponse }
     * 
     */
    public NIPCheckResponse createNIPCheckResponse() {
        return new NIPCheckResponse();
    }

    /**
     * Create an instance of {@link SNWERESPONSE }
     * 
     */
    public SNWERESPONSE createSNWERESPONSE() {
        return new SNWERESPONSE();
    }

}
