package report;

import java.math.BigInteger;
import java.util.List;

import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Style;
import org.docx4j.wml.Styles;
import org.docx4j.wml.Text;

public class DocHelper {


  public static ObjectFactory objectFactory = new ObjectFactory();
  public static final String HEADING1 = "Heading1";
  public static final String HEADING2 = "Heading2";
  public static final String HEADING3 = "Heading3";
  public static final String HEADING4 = "Heading4";
  public static final String NORMAL = "Normal";
  public static final String TITLE = "Title";
  public static Integer HEADING1_SIZE = 40;
  public static Integer HEADING2_SIZE = 32;
  public static Integer HEADING3_SIZE = 28;
  public static Integer NORMAL_SIZE = 24;



  public void createHeaderReference(
      WordprocessingMLPackage wordprocessingMLPackage,
      Relationship relationship )
      throws InvalidFormatException {

    List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();

    SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
    // There is always a section wrapper, but it might not contain a sectPr
    if (sectPr == null) {
      sectPr = objectFactory.createSectPr();
      wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
      sections.get(sections.size() - 1).setSectPr(sectPr);
    }

    HeaderReference headerReference = objectFactory.createHeaderReference();
    headerReference.setId(relationship.getId());
    headerReference.setType(HdrFtrRef.DEFAULT);
    sectPr.getEGHdrFtrReferences().add(headerReference);// add header or
    // footer references

  }

  public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage, String content) throws Exception {
    Hdr hdr = objectFactory.createHdr();
    hdr.getContent().add(createParagraph(content));

//    File file = new File(System.getProperty("user.dir")
//        + "/src/test/resources/images/greentick.png" );
//    java.io.InputStream is = new java.io.FileInputStream(file );
//
//    hdr.getContent().add(
//        newImage(wordprocessingMLPackage,
//            sourcePart,
//            BufferUtil.getBytesFromInputStream(is),
//            "filename", "alttext", 1, 2
//        )
//    );
    return hdr;

  }

  public void addHeaderPart(WordprocessingMLPackage wordprocessingMLPackage, String title) throws Exception {
    Relationship relationship = createHeaderPart(wordprocessingMLPackage, title);
    createHeaderReference(wordprocessingMLPackage, relationship);
  }


  public Relationship createHeaderPart(WordprocessingMLPackage wordprocessingMLPackage, String content)
      throws Exception {

    HeaderPart headerPart = new HeaderPart();
    Relationship rel =  wordprocessingMLPackage.getMainDocumentPart()
        .addTargetPart(headerPart);

    headerPart.setContents(getHdr(wordprocessingMLPackage, content));

    return rel;
  }

  public void addParagraph(MainDocumentPart document, String content) {
    P para = objectFactory.createP();
    Text text = objectFactory.createText();
    R run = objectFactory.createR();
    RPr sample = objectFactory.createRPr();

    HpsMeasure size = new HpsMeasure();
    size.setVal(BigInteger.valueOf(21));

    sample.setSz(size);
    run.setRPr(sample);

    text.setValue(content);
    run.getContent().add(text);
    para.getContent().add(run);
    document.getContent().add(para);
  }

  public P createParagraph(String content) {
    P para = objectFactory.createP();
    Text text = objectFactory.createText();
    R run = objectFactory.createR();

    text.setValue(content);
    run.getContent().add(text);
    para.getContent().add(run);
    return para;
  }

  public void addHeader(MainDocumentPart document, Header header) {
    document.addStyledParagraphOfText(header.getStyling(), header.getTitle());
  }


  public void addHeader(MainDocumentPart document, String text, String styling) {
    document.addStyledParagraphOfText(styling,text);
  }

  public void addPageBreak(MainDocumentPart document) {
    Br objBr = new Br();
    objBr.setType(STBrType.PAGE);
    P para = objectFactory.createP();
    para.getContent().add(objBr);
    document.addObject(para);
  }

  public Br createLineBreak() {
    Br objBr = new Br();
    objBr.setType(STBrType.TEXT_WRAPPING);
    return  objBr;
  }

  public void setupStyles(WordprocessingMLPackage wordMLPackage) {
    MainDocumentPart mp = wordMLPackage.getMainDocumentPart();

    HpsMeasure hpsMeasure = objectFactory.createHpsMeasure();
    hpsMeasure.setVal(BigInteger.valueOf(HEADING1_SIZE));

    Style heading1 = mp.getStyleDefinitionsPart().getStyleById(HEADING1);
    RPr rpr = heading1.getRPr();
    rpr.setSz(hpsMeasure);

    Style heading2 = mp.getStyleDefinitionsPart().getStyleById(HEADING2);
    hpsMeasure = objectFactory.createHpsMeasure();
    hpsMeasure.setVal(BigInteger.valueOf(HEADING2_SIZE));
    rpr = heading2.getRPr();
    rpr.setSz(hpsMeasure);


    Style heading3 = mp.getStyleDefinitionsPart().getStyleById(HEADING3);
    hpsMeasure = objectFactory.createHpsMeasure();
    hpsMeasure.setVal(BigInteger.valueOf(HEADING3_SIZE));
    rpr = heading3.getRPr();
    rpr.setSz(hpsMeasure);

    Style normal = mp.getStyleDefinitionsPart().getStyleById(NORMAL);
    hpsMeasure = objectFactory.createHpsMeasure();
    hpsMeasure.setVal(BigInteger.valueOf(NORMAL_SIZE));
    rpr = normal.getRPr();
    if (rpr == null) {
      rpr = objectFactory.createRPr();
      normal.setRPr(rpr);
    }
    rpr.setSz(hpsMeasure);

  }

  public void makeItalic(R run) {
    RPr rpr = objectFactory.createRPr();
    BooleanDefaultTrue b = new BooleanDefaultTrue();
    b.setVal(true);
    rpr.setI(b);
    run.setRPr(rpr);
  }

  public void makeBold(R run) {
    RPr rpr = objectFactory.createRPr();
    BooleanDefaultTrue b = new BooleanDefaultTrue();
    b.setVal(true);
    rpr.setB(b);
    run.setRPr(rpr);
  }
}
