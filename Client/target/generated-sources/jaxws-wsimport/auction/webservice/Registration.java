
package auction.webservice;

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
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Registration", targetNamespace = "http://webservice.auction/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Registration {


    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "clean", targetNamespace = "http://webservice.auction/", className = "auction.webservice.Clean")
    @ResponseWrapper(localName = "cleanResponse", targetNamespace = "http://webservice.auction/", className = "auction.webservice.CleanResponse")
    @Action(input = "http://webservice.auction/Registration/cleanRequest", output = "http://webservice.auction/Registration/cleanResponse")
    public void clean();

    /**
     * 
     * @param arg0
     * @return
     *     returns auction.webservice.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://webservice.auction/", className = "auction.webservice.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://webservice.auction/", className = "auction.webservice.GetUserResponse")
    @Action(input = "http://webservice.auction/Registration/getUserRequest", output = "http://webservice.auction/Registration/getUserResponse")
    public User getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<auction.webservice.User>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUsers", targetNamespace = "http://webservice.auction/", className = "auction.webservice.GetUsers")
    @ResponseWrapper(localName = "getUsersResponse", targetNamespace = "http://webservice.auction/", className = "auction.webservice.GetUsersResponse")
    @Action(input = "http://webservice.auction/Registration/getUsersRequest", output = "http://webservice.auction/Registration/getUsersResponse")
    public List<User> getUsers();

    /**
     * 
     * @param arg0
     * @return
     *     returns auction.webservice.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registerUser", targetNamespace = "http://webservice.auction/", className = "auction.webservice.RegisterUser")
    @ResponseWrapper(localName = "registerUserResponse", targetNamespace = "http://webservice.auction/", className = "auction.webservice.RegisterUserResponse")
    @Action(input = "http://webservice.auction/Registration/registerUserRequest", output = "http://webservice.auction/Registration/registerUserResponse")
    public User registerUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
