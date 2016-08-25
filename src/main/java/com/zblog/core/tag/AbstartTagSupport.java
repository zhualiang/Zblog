package com.zblog.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

abstract class AbstartTagSupport extends SimpleTagSupport{
  private static final long serialVersionUID = 1L;

  protected void setPageAttribute(int pageNumber){
    getJspContext().setAttribute("pageNumber", pageNumber);
    getJspContext().setAttribute("pageUrl", getPagination().getPageUrl() + pageNumber);
  }

  protected void clearPageAttribute(){
    getJspContext().removeAttribute("pageNumber");
    getJspContext().removeAttribute("pageUrl");
  }

  @Override
  public void doTag() throws JspException, IOException {
    handleTag();
    clearPageAttribute();
  }

  protected abstract void handleTag()throws JspException, IOException;
  
  protected Pagination<?> getPagination(){
    return (Pagination<?>) findAncestorWithClass(this, Pagination.class);
  }
  
}
