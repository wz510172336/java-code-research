package com.wz.springframework.beans;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @package: com.wz.springframework.beans
 * @Author:
 * @Date: 2018/5/26 0026 上午 2:58
 */
public class ViewResoler {
    private String viewName;
    private File viewFile;
    private Map<String,File> stringFileMap=new HashMap<>();
    public ViewResoler(String viewName,File viewFile) {
        this.viewFile=viewFile;
        this.viewName=viewName;
        stringFileMap.put(viewName,viewFile);
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public File getViewFile() {
        return viewFile;
    }

    public void setViewFile(File viewFile) {
        this.viewFile = viewFile;
    }

    public String resolveView(WzModelandView wzModelandView)throws Exception{
        File viewfile=stringFileMap.get(wzModelandView.getViewName());
        RandomAccessFile randomAccessFile=new RandomAccessFile(viewfile,"r");
        StringBuffer sb=new StringBuffer();
        String Line=null;
        while (null!=(Line=randomAccessFile.readLine())){
            Line=new String(Line.getBytes("ISO-8859-1"),"UTF-8");
                 Matcher matcher=wzMatch(Line);
                 while (matcher.find()){
                     for(int i=1;i<=matcher.groupCount();i++){
                              String str=matcher.group(i);
                              Object ob=wzModelandView.getModel().get(str);
                         Line=Line.replaceAll("#\\{"+str+"\\}",ob.toString());
                         Line=new String(Line.getBytes("ISO-8859-1"),"UTF-8");

                     }
                 }
            sb.append(Line);

        }

        return sb.toString();
    }
    public Matcher wzMatch(String str){
        Pattern pattern=Pattern.compile("#\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(str);
        return  matcher;

    }

}
