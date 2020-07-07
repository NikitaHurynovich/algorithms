package report;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "issues")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Issues {

  private List<Issue> issueList;

  @XmlElement(name = "issue")
  public List<Issue> getIssueList() {
    return issueList;
  }

  public void setIssueList(List<Issue> issueList) {
    this.issueList = issueList;
  }
}
