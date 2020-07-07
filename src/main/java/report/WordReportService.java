package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import org.docx4j.toc.TocGenerator;

import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static report.DocHelper.HEADING1;
import static report.DocHelper.HEADING2;
import static report.DocHelper.TITLE;

public class WordReportService implements IReportService {


  private final static Logger LOGGER = LoggerFactory.getLogger(WordReportService.class);
  private DocHelper docHelper = new DocHelper();


  public List<Issue> getIssues(String filePath) {
    try(InputStream inStream = new FileInputStream(filePath)) {
      JAXBContext jaxbContext = JAXBContext.newInstance(Issues.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      Issues issues = (Issues)jaxbUnmarshaller.unmarshal(inStream);
      return issues.getIssueList();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  private List<Header> getContentTable() {
    List<Header> headers = new ArrayList<>();
    headers.add(createHeader("Penetration testing report", TITLE, 1));

    Header summary = createHeader("Executive summary", HEADING1, 1);
    Header summaryResult = createHeader("Summary Result", HEADING2, 0);
    summary.getSubheaders().add(summaryResult);
    headers.add(summary);

    headers.add(createHeader("Attack Narrative", HEADING1, 1));

    Header conclusion = createHeader("Conclusion", HEADING1, 1);
    Header recommendation = createHeader("Recommendations", HEADING2, 0);
    Header riskRating = createHeader("Risk Rating", HEADING2, 0);
    conclusion.getSubheaders().add(recommendation);
    conclusion.getSubheaders().add(riskRating);
    headers.add(conclusion);
    return headers;
  }

  @Override
  public void generateReport(List<Issue> issues, String outputDir) {

    try {
      List<Header> contentTable = getContentTable();
      WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
      MainDocumentPart document = wordMLPackage.getMainDocumentPart();
      docHelper.setupStyles(wordMLPackage);
      //        docHelper.addHeaderPart(wordMLPackage, header.getTitle());

      for (Header header: contentTable) {
        document.addStyledParagraphOfText(header.getStyling(), header.getTitle());
        for (Header subheader: header.getSubheaders()) {
          document.addStyledParagraphOfText(subheader.getStyling(), subheader.getTitle());
          for (int i = 0; i < subheader.getPageBreakNumber(); i++) {
            docHelper.addPageBreak(document);
          }
        }

        for (int i = 0; i < header.getPageBreakNumber(); i++) {
          docHelper.addPageBreak(document);
        }
      }

      for (int i = 0; i < issues.size(); i++) {
        addVulnerabilityPage(wordMLPackage, issues.get(i));
        if (i + 1 != issues.size()) {
          docHelper.addPageBreak(document);
        }
      }

      TocGenerator tocGenerator = new TocGenerator(wordMLPackage);

      tocGenerator.generateToc( 2, " TOC \\o \"1-3\" \\h \\z \\u ", true);

      wordMLPackage.save(new File(outputDir));

    } catch (Exception e) {
      LOGGER.error("report generating service", e);
    }

  }


  @Override
  public void addVulnerabilityPage(WordprocessingMLPackage wordMLPackage, Issue issue) {
    MainDocumentPart document = wordMLPackage.getMainDocumentPart();

    docHelper.addHeader(document,issue.getName(), HEADING1);
    docHelper.addHeader(document, SUMMARY_TEXT, HEADING2);
    addSummaryTable(wordMLPackage, issue);
    if (StringUtils.isNotEmpty(issue.getIssueDetail())) {
      docHelper.addHeader(document, ISSUE_DETAIL_TEXT, HEADING2);
      docHelper.addParagraph(document, issue.getIssueDetail());
    }

    if (StringUtils.isNotEmpty(issue.getRemediationDetail())) {
      docHelper.addHeader(document, REMEDIATION_DETAIL_TEXT, HEADING2);
      docHelper.addParagraph(document, issue.getRemediationDetail());
    }

    if (StringUtils.isNotEmpty(issue.getIssueBackground())) {
      docHelper.addHeader(document, ISSUE_BACKGROUND_TEXT, HEADING2);
      docHelper.addParagraph(document, issue.getIssueBackground());
    }


    if (StringUtils.isNotEmpty(issue.getRemediationBackground())) {
      docHelper.addHeader(document, REMEDIATION_BACKGROUND_TEXT, HEADING2);
      docHelper.addParagraph(document, issue.getRemediationBackground());
    }

    if (StringUtils.isNotEmpty(issue.getIssueDetail())) {
      docHelper.addHeader(document, ISSUE_DETAIL_TEXT, HEADING2);
      docHelper.addParagraph(document, issue.getIssueDetail());
    }

    if (issue.getRequestResponse() != null && StringUtils.isNotEmpty(issue.getRequestResponse().getRequest())) {
      docHelper.addHeader(document, REQUEST_TEXT, HEADING2);
      addRequestResponse(document, issue.getRequestResponse().getRequest());
    }

    if (issue.getRequestResponse() != null && StringUtils.isNotEmpty(issue.getRequestResponse().getResponse())) {
      docHelper.addHeader(document, RESPONSE_TEXT, HEADING2);
      addRequestResponse(document, issue.getRequestResponse().getResponse());
    }

  }

  private Header createHeader(String title, String styling, int breakNumber) {
    return new Header(title, new ArrayList<>(),breakNumber, styling);
  }

  private void addRequestResponse(MainDocumentPart document, String request) {
    String[] lines = request.split("\n");
    P paragraph = DocHelper.objectFactory.createP();
    R run = DocHelper.objectFactory.createR();
    docHelper.makeItalic(run);
    paragraph.getContent().add(run);
    document.getContent().add(paragraph);
    for (int i = 0; i < lines.length; i++) {
      Text text = DocHelper.objectFactory.createText();
      text.setValue(lines[i]);
      run.getContent().add(text);
      if (i + 1 != lines.length) {
        run.getContent().add(docHelper.createLineBreak());
      }
    }
  }

  private void addSummaryTable(WordprocessingMLPackage wordMLPackage, Issue issue) {
    int writableWidthTwips = wordMLPackage.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
    Tbl tbl = TblFactory.createTable(TABLE_ROW_NUMBER, TABLE_COL_NUMBER, writableWidthTwips / TABLE_COL_NUMBER);
    List<Object> rows = tbl.getContent();

    fillRow((Tr)rows.get(0), SEVERITY_TEXT, issue.getSeverity());
    fillRow((Tr)rows.get(1), CONFIDENCE_TEXT, issue.getConfidence());
    fillRow((Tr)rows.get(2), HOST_TEXT, issue.getHost());
    fillRow((Tr)rows.get(3), PATH_TEXT, issue.getHost());

    wordMLPackage.getMainDocumentPart().getContent().add(tbl);
  }


  private void fillRow(Tr row, String col1, String col2) {
    List<Object> cells = row.getContent();

    Tc td1 = (Tc) cells.get(0);
    P paragraph = docHelper.createParagraph(col1);
    docHelper.makeBold((R)paragraph.getContent().get(0));
    td1.getContent().add(paragraph);
    Tc td2 = (Tc) cells.get(1);
    td2.getContent().add(docHelper.createParagraph(col2));
  }

}
