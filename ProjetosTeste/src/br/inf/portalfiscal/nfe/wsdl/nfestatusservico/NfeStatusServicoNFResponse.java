
package br.inf.portalfiscal.nfe.wsdl.nfestatusservico;

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
 *         &lt;element name="nfeStatusServicoNFResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "nfeStatusServicoNFResult"
})
@XmlRootElement(name = "nfeStatusServicoNFResponse")
public class NfeStatusServicoNFResponse {

    protected String nfeStatusServicoNFResult;

    /**
     * Gets the value of the nfeStatusServicoNFResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNfeStatusServicoNFResult() {
        return nfeStatusServicoNFResult;
    }

    /**
     * Sets the value of the nfeStatusServicoNFResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNfeStatusServicoNFResult(String value) {
        this.nfeStatusServicoNFResult = value;
    }

}
