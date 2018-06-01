package com.wz.springframework.support;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @package: com.wz.springframework.support
 * @Author:
 * @Date: 2018/5/26 0026 上午 1:53
 */
public class ViewReader extends BaseReader {


    private Map<String, File> viewList = new HashMap<>();
    private static final String Templete = "ScanLayout";

    public ViewReader(String locations) {
        super(locations);
        doscan2(pros.getProperty(Templete));
    }


    private void doscan2(String vmDirName) {
        URL url = this.getClass().getClassLoader().getResource("/" + vmDirName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                doscan2(vmDirName + "." + f.getName());
            } else {
                viewList.put(f.getName(), f);
            }

        }
    }

    public Map<String, File> loadViewList() {
        return viewList;
    }
}
