
package com.naruto.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inputCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inputCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inReserved1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inReserved2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inReserved3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inReserved4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inReserved5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageInfo" type="{http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/}pageInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inputCollection", propOrder = {
    "inReserved1",
    "inReserved2",
    "inReserved3",
    "inReserved4",
    "inReserved5",
    "pageInfo"
})
public class InputCollection {

    protected String inReserved1;
    protected String inReserved2;
    protected String inReserved3;
    protected String inReserved4;
    protected String inReserved5;
    protected PageInfo pageInfo;

    /**
     * Gets the value of the inReserved1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInReserved1() {
        return inReserved1;
    }

    /**
     * Sets the value of the inReserved1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInReserved1(String value) {
        this.inReserved1 = value;
    }

    /**
     * Gets the value of the inReserved2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInReserved2() {
        return inReserved2;
    }

    /**
     * Sets the value of the inReserved2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInReserved2(String value) {
        this.inReserved2 = value;
    }

    /**
     * Gets the value of the inReserved3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInReserved3() {
        return inReserved3;
    }

    /**
     * Sets the value of the inReserved3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInReserved3(String value) {
        this.inReserved3 = value;
    }

    /**
     * Gets the value of the inReserved4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInReserved4() {
        return inReserved4;
    }

    /**
     * Sets the value of the inReserved4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInReserved4(String value) {
        this.inReserved4 = value;
    }

    /**
     * Gets the value of the inReserved5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInReserved5() {
        return inReserved5;
    }

    /**
     * Sets the value of the inReserved5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInReserved5(String value) {
        this.inReserved5 = value;
    }

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

}
