package report;

import java.util.List;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public interface IReportService {

  Integer TABLE_COL_NUMBER = 2;
  Integer TABLE_ROW_NUMBER = 4;

  String SEVERITY_TEXT = "Severity";
  String CONFIDENCE_TEXT = "Confidence";
  String HOST_TEXT = "Host";
  String PATH_TEXT = "Path";
  String SUMMARY_TEXT = "Summary";
  String ISSUE_DETAIL_TEXT = "Issue detail";
  String REMEDIATION_DETAIL_TEXT = "Remediation detail";
  String ISSUE_BACKGROUND_TEXT = "Issue background";
  String REMEDIATION_BACKGROUND_TEXT = "Remediation background";
  String REQUEST_TEXT = "Request";
  String RESPONSE_TEXT = "Response";

  void generateReport(List<Issue> issues, String outputDir);


  void addVulnerabilityPage(WordprocessingMLPackage wordMLPackage, Issue issue);
}
