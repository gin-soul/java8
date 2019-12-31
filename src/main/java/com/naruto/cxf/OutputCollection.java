
package com.naruto.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for outputCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="outputCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pageInfo" type="{http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/}pageInfo" minOccurs="0"/>
 *         &lt;element name="pageLists" type="{http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/}rRiskAmlBorrInfoV" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="priKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "outputCollection", propOrder = {
    "pageInfo",
    "pageLists",
    "priKey",
    "serviceFlag",
    "serviceMessage"
})
public class OutputCollection {

    protected PageInfo pageInfo;
    @XmlElement(nillable = true)
    protected List<RRiskAmlBorrInfoV> pageLists;
    protected String priKey;
    protected String serviceFlag;
    protected String serviceMessage;

    /**
     * Gets the value of the pageInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PageInfo }
     *     
     */
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     * Sets the value of the pageInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageInfo }
     *     
     */
    public void setPageInfo(PageInfo value) {
        this.pageInfo = value;
    }

    /**
     * Gets the value of the pageLists property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pageLists property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPageLists().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RRiskAmlBorrInfoV }
     * 
     * 
     */
    public List<RRiskAmlBorrInfoV> getPageLists() {
        if (pageLists == null) {
            pageLists = new ArrayList<RRiskAmlBorrInfoV>();
        }
        return this.pageLists;
    }

    /**
     * Gets the value of the priKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriKey() {
        return priKey;
    }

    /**
     * Sets the value of the priKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriKey(String value) {
        this.priKey = value;
    }

    /**
     * Gets the value of the serviceFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceFlag() {
        return serviceFlag;
    }

    /**
     * Sets the value of the serviceFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceFlag(String value) {
        this.serviceFlag = value;
    }

    /**
     * Gets the value of the serviceMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceMessage() {
        return serviceMessage;
    }

    /**
     * Sets the value of the serviceMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceMessage(String value) {
        this.serviceMessage = value;
    }

}
