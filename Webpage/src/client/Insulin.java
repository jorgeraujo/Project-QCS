
package client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Insulin", targetNamespace = "http://server/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Insulin {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "mealtimeInsulinDose", targetNamespace = "http://server/", className = "client.MealtimeInsulinDose")
    @ResponseWrapper(localName = "mealtimeInsulinDoseResponse", targetNamespace = "http://server/", className = "client.MealtimeInsulinDoseResponse")
    @Action(input = "http://server/Insulin/mealtimeInsulinDoseRequest", output = "http://server/Insulin/mealtimeInsulinDoseResponse")
    public int mealtimeInsulinDose(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        int arg4);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "backgroundInsulinDose", targetNamespace = "http://server/", className = "client.BackgroundInsulinDose")
    @ResponseWrapper(localName = "backgroundInsulinDoseResponse", targetNamespace = "http://server/", className = "client.BackgroundInsulinDoseResponse")
    @Action(input = "http://server/Insulin/backgroundInsulinDoseRequest", output = "http://server/Insulin/backgroundInsulinDoseResponse")
    public int backgroundInsulinDose(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "personalSensitivityToInsulin", targetNamespace = "http://server/", className = "client.PersonalSensitivityToInsulin")
    @ResponseWrapper(localName = "personalSensitivityToInsulinResponse", targetNamespace = "http://server/", className = "client.PersonalSensitivityToInsulinResponse")
    @Action(input = "http://server/Insulin/personalSensitivityToInsulinRequest", output = "http://server/Insulin/personalSensitivityToInsulinResponse")
    public int personalSensitivityToInsulin(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        List<Integer> arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        List<Integer> arg2);

}
