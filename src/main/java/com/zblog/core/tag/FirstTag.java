package com.zblog.core.tag;

import com.zblog.core.plugin.PageModel;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class FirstTag extends AbstartTagSupport{
  private static final long serialVersionUID = 1L;

  @Override
  protected void handleTag() throws JspException, IOException {
    PageModel<?> model = getPagination().getModel();
    if(model.getPageIndex()!=1)
    {
      setPageAttribute(1);
      getJspBody().invoke(null);
    }
  }
}
