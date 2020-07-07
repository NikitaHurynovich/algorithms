package report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "requestresponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestResponse {
  private String request;
  private String response;

  public RequestResponse() {
  }

  public RequestResponse(String request, String response) {
    this.request = request;
    this.response = response;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }
}
