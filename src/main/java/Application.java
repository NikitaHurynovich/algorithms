import report.IReportService;
import report.Issue;
import report.WordReportService;

import java.util.List;

public class Application {


  public static void main(String[] args) {
//    ArraysDemo.startDemo();
//    SortingDemo.startBubbleAndInsertDemo();

//    String[] command = {"open -a calculator"};
//    final Transformer[] transformers = new Transformer[]{
//        new ConstantTransformer(Runtime.class), //(1)
//        new InvokerTransformer("getMethod",
//            new Class[]{ String.class, Class[].class},
//            new Object[]{"getRuntime", new Class[0]}
//        ), //(2)
//        new InvokerTransformer("invoke",
//            new Class[]{Object.class, Object[].class},
//            new Object[]{null, new Object[0]}
//        ), //(3)
//        new InvokerTransformer("exec",
//            new Class[]{String.class},
//            command
//        ) //(4)
//    };
//    ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
//    Map map = new HashMap<>();
//    Map lazyMap = LazyMap.decorate(map, chainedTransformer);
//    lazyMap.get("gursev");

    WordReportService reportService = new WordReportService();
    List<Issue> issueList = reportService.getIssues("/Users/gurinovichnikita/Public/Projects/hacking-soft/issues.xml");

    reportService.generateReport(issueList, "/Users/gurinovichnikita/Public/Projects/hacking-soft/recon_report.docx");
  }

}
