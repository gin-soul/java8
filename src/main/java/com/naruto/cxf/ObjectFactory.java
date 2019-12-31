
package com.naruto.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.naruto.cxf package.
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

    private final static QName _InquiryRiskAmlBorrInfo_QNAME = new QName("http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/", "inquiryRiskAmlBorrInfo");
    private final static QName _InquiryRiskAmlBorrInfoResponse_QNAME = new QName("http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/", "inquiryRiskAmlBorrInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.naruto.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InquiryRiskAmlBorrInfoResponse }
     * 
     */
    public InquiryRiskAmlBorrInfoResponse createInquiryRiskAmlBorrInfoResponse() {
        return new InquiryRiskAmlBorrInfoResponse();
    }

    /**
     * Create an instance of {@link InquiryRiskAmlBorrInfo }
     * 
     */
    public InquiryRiskAmlBorrInfo createInquiryRiskAmlBorrInfo() {
        return new InquiryRiskAmlBorrInfo();
    }

    /**
     * Create an instance of {@link OutputCollection }
     * 
     */
    public OutputCollection createOutputCollection() {
        return new OutputCollection();
    }

    /**
     * Create an instance of {@link InputCollection }
     * 
     */
    public InputCollection createInputCollection() {
        return new InputCollection();
    }

    /**
     * Create an instance of {@link RRiskAmlBorrInfoV }
     * 
     */
    public RRiskAmlBorrInfoV createRRiskAmlBorrInfoV() {
        return new RRiskAmlBorrInfoV();
    }

    /**
     * Create an instance of {@link MsgHeader }
     * 
     */
    public MsgHeader createMsgHeader() {
        return new MsgHeader();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link PageInfo }
     * 
     */
    public PageInfo createPageInfo() {
        return new PageInfo();
    }

    /**
     * Create an instance of {@link PageList }
     * 
     */
    public PageList createPageList() {
        return new PageList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InquiryRiskAmlBorrInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/", name = "inquiryRiskAmlBorrInfo")
    public JAXBElement<InquiryRiskAmlBorrInfo> createInquiryRiskAmlBorrInfo(InquiryRiskAmlBorrInfo value) {
        return new JAXBElement<InquiryRiskAmlBorrInfo>(_InquiryRiskAmlBorrInfo_QNAME, InquiryRiskAmlBorrInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InquiryRiskAmlBorrInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://queryRiskAmlBorrInfo.risk.inquiry.ws.pccw.com/", name = "inquiryRiskAmlBorrInfoResponse")
    public JAXBElement<InquiryRiskAmlBorrInfoResponse> createInquiryRiskAmlBorrInfoResponse(InquiryRiskAmlBorrInfoResponse value) {
        return new JAXBElement<InquiryRiskAmlBorrInfoResponse>(_InquiryRiskAmlBorrInfoResponse_QNAME, InquiryRiskAmlBorrInfoResponse.class, null, value);
    }

}
