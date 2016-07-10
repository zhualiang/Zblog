package com.zblog.core.dal.constants;

public class CategoryConstants{
  private CategoryConstants(){
  }

  /**
   * 顶级默认分类名称
   */
  public static final String ROOT = "Root";
  /**
   * 存储category名字时存储相对于ROOT的全路径，目录之间使用此分隔符隔开
   */
  public static final String NAME_DELIMITER="/";

}
