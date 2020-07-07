package report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
public class Issue {

  private String name;
  private String host;
  private String path;
  private String location;
  private String severity;
  private String confidence;
  private String issueBackground;
  private String remediationBackground;
  private String issueDetail;
  private String remediationDetail;
  @XmlElement(name = "requestresponse")
  private RequestResponse requestResponse;

  public Issue() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public String getConfidence() {
    return confidence;
  }

  public void setConfidence(String confidence) {
    this.confidence = confidence;
  }

  public String getIssueBackground() {
    return issueBackground;
  }

  public void setIssueBackground(String issueBackground) {
    this.issueBackground = issueBackground;
  }

  public String getRemediationBackground() {
    return remediationBackground;
  }

  public void setRemediationBackground(String remediationBackground) {
    this.remediationBackground = remediationBackground;
  }

  public String getIssueDetail() {
    return issueDetail;
  }

  public void setIssueDetail(String issueDetail) {
    this.issueDetail = issueDetail;
  }

  public String getRemediationDetail() {
    return remediationDetail;
  }

  public void setRemediationDetail(String remediationDetail) {
    this.remediationDetail = remediationDetail;
  }

  public RequestResponse getRequestResponse() {
    return requestResponse;
  }

  public void setRequestResponse(RequestResponse requestResponse) {
    this.requestResponse = requestResponse;
  }
}
