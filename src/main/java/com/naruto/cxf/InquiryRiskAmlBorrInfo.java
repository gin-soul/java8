
package com.naruto.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inquiryRiskAmlBorrInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inquiryRiskAmlBorrInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgHeader" type="{http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/}msgHeader" minOccurs="0"/>
 *         &lt;element name="InputCollection" type="{http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/}inputCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inquiryRiskAmlBorrInfo", propOrder = {
    "msgHeader",
    "inputCollection"
})
public class InquiryRiskAmlBorrInfo {

    @XmlElement(name = "MsgHeader")
    protected MsgHeader msgHeader;
    @XmlElement(name = "InputCollection")
    protected InputCollection inputCollection;

    /**
     * Gets the value of the msgHeader property.
     * 
     * @return
     *     possible object is
     *     {@link MsgHeader }
     *     
     */
    public MsgHeader getMsgHeader() {
        return msgHeader;
    }

    /**
     * Sets the value of the msgHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgHeader }
     *     
     */
    public void setMsgHeader(MsgHeader value) {
        this.msgHeader = value;
    }

    /**
     * Gets the value of the inputCollection property.
     * 
     * @return
     *     possible object is
     *     {@link InputCollection }
     *     
     */
    public InputCollection getInputCollection() {
        return inputCollection;
    }

    /**
     * Sets the value of the inputCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputCollection }
     *     
     */
    public void setInputCollection(InputCollection value) {
        this.inputCollection = value;
    }

}
