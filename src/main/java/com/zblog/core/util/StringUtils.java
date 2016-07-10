package com.zblog.core.util;

import java.util.Collection;

public final class StringUtils{

  private StringUtils(){
  }

  public static boolean isBlank(String str){
    return str == null || str.trim().length() == 0;
  }

  public static String join(Collection<String> collect, String delimiter){
    StringBuilder result = new StringBuilder();
    for(String temp : collect){
      result.append(temp).append(delimiter);
    }

    if(!collect.isEmpty())
      result.delete(result.length() - delimiter.length(), result.length());

    return result.toString();
  }

  public static String trimLeft(String source){
    if(isBlank(source))
      return null;

    int index = 0;
    for(; index < source.length(); index++){
      if(source.charAt(index) > 32)
        break;
    }

    return source.substring(index);
  }

  public static String trimRight(String source){
    if(isBlank(source))
      return null;

    int index = source.length() - 1;
    for(; index >= 0; index--){
      if(source.charAt(index) > 32)
        break;
    }

    return source.substring(0, index + 1);
  }

  public static String emptyDefault(String ifEmpty, String defaults){
    return isBlank(ifEmpty) ? defaults : ifEmpty;
  }

  public static boolean hasSubStr(String str,String subStr)
  {
    if(isBlank(str))
      return false;
    return str.indexOf(subStr)!=-1;
  }

  public static String addStrNotExist(String destStr,String addStr)
  {
    if(isBlank(destStr)||destStr.endsWith(addStr))
      return destStr;
    else
      return destStr+addStr;
  }

  public static String getLastDivisionStr(String destStr,String delimiter)
  {
    if(isBlank(destStr))
      return destStr;
    int index=destStr.lastIndexOf(delimiter);
    if(index==-1)
      return destStr;
    else
      return destStr.substring(index+delimiter.length());
  }

}
