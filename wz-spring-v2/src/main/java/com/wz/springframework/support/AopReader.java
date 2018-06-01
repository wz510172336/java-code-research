package com.wz.springframework.support;

import org.omg.CORBA.Object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.wz.springframework.support
 * @Author:
 * @Date: 2018/5/29 0029 上午 12:30
 */
public class AopReader extends BaseReader {

    List<String[]> aoplist=new ArrayList<>();
    public AopReader(String locations) {
        super(locations);
       doScan(pros.getProperty("ScanaopPackage")); 
    }

    private void doScan(String scanaopPackage) {
        URL url=this.getClass().getClassLoader().getResource("/"+scanaopPackage.replaceAll("\\.", "/"));
        File dirfile=new File(url.getFile());
        for(File f:dirfile.listFiles()){

            StringBuffer sb=new StringBuffer();
            try {
                RandomAccessFile r=new RandomAccessFile(f,"r");
                String line=null;
                while (null!=(line=r.readLine())){
                    String[] strings=line.split("=");
                    aoplist.add(strings);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e){
                System.out.println(e.getStackTrace());
            }
        }
    }

    public List<String[]> getAoplist() {
        return aoplist;
    }
}
