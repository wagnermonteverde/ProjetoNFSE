
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
 *         &lt;element name="nfeCabecMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nfeDadosMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "nfeCabecMsg",
    "nfeDadosMsg"
})
@XmlRootElement(name = "nfeStatusServicoNF")
public class NfeStatusServicoNF {

    protected String nfeCabecMsg;
    protected String nfeDadosMsg;

    /**
     * Gets the value of the nfeCabecMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNfeCabecMsg() {
        return nfeCabecMsg;
    }

    /**
     * Sets the value of the nfeCabecMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNfeCabecMsg(String value) {
        this.nfeCabecMsg = value;
    }

    /**
     * Gets the value of the nfeDadosMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNfeDadosMsg() {
        return nfeDadosMsg;
    }

    /**
     * Sets the value of the nfeDadosMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNfeDadosMsg(String value) {
        this.nfeDadosMsg = value;
    }

}
