package com.zblog.core.tag;

import javax.servlet.jsp.JspException;
import com.zblog.core.plugin.PageModel;
import java.io.IOException;

public class LastTag extends AbstartTagSupport{

  private static final long serialVersionUID = 1L;

  @Override
  protected void handleTag() throws JspException, IOException {
    PageModel<?> model = getPagination().getModel();
    if(model.getTotalPage()!=model.getPageIndex()) {
      setPageAttribute(model.getTotalPage());
      getJspBody().invoke(null);
    }
  }
}
