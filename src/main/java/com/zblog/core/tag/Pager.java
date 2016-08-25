package com.zblog.core.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import com.zblog.core.plugin.PageModel;
import java.io.IOException;

/**
 * pager显示效果（按所有页面展开后效果）:
 * 开始 1~boundary页面 + "dot"属性页面 +start~end页面 + "dot"属性页面 + totalPage-boundary~totalPage页面
 */
public class Pager extends AbstartTagSupport{
  private static final long serialVersionUID = 1L;

  private int current, start, end;

  private void displayCurrentPage(JspContext jspContext)throws JspException, IOException
  {
    setPageAttribute(current);
    getJspBody().invoke(null);
    jspContext.removeAttribute("dot");
    clearPageAttribute();
  }

  @Override
  protected void handleTag() throws JspException, IOException {
    //初始化值
    initStartEnd();

    Pagination<?> p = getPagination();
    PageModel<?> model = p.getModel();
    JspContext jspContext=getJspContext();

    do {
      displayCurrentPage(jspContext);
      current++;
      //将要显示的页面处于0与boundary范围内
      if (current <= p.getBoundary()) {
        continue;
      }
      //将要显示的页面超出boundary并且小于start值
      if(current < start) {
        jspContext.setAttribute("dot", true);
        displayCurrentPage(jspContext);
        current=start;
      }
      //显示页面处于start和end之间
      if(current>=start && current<=end)
      {
        continue;
      }
      //end之后没有要显示的页面了
      if(p.getBoundary()<1)
      {
        break;
      }
      //end之后存在要显示的页面，且该页面处于end与totalPage-boundary+1之间
      if(current > end && current < model.getTotalPage() - p.getBoundary() + 1){
        jspContext.setAttribute("dot", true);
        displayCurrentPage(jspContext);
        current = model.getTotalPage() - p.getBoundary() + 1;
      }
      //后面大于totalPage-boundary全部需要显示，这里不做特殊处理，由循环完成显示

    }while (current<=model.getTotalPage());


  }

  private void initStartEnd(){
    Pagination<?> p = getPagination();
    PageModel<?> model = p.getModel();
    int extra = p.getShowPage() - 2 * p.getBoundary();
    if(model.getTotalPage() - 2 * p.getBoundary() > 0){
      if(model.getPageIndex() <= model.getTotalPage() / 2){
        start = Math.max(p.getBoundary() + 1, model.getPageIndex() - extra / 2);
        end = Math.min(start + extra - 1, model.getTotalPage() - p.getBoundary());
      }else{
        end = Math.min(model.getTotalPage() - p.getBoundary(), model.getPageIndex() + extra / 2);
        start = Math.max(end - extra + 1, p.getBoundary() + 1);
      }
    }else{
      start = 1;
      end = model.getTotalPage();
    }

    current = p.getBoundary() > 0 ? 1 : start;
  }

}
