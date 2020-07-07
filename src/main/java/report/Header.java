package report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header {

  private String title;
  private List<Header> subheaders;
  private int pageBreakNumber = 0;
  private String styling;

}
