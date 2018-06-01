package com.wz.springframework.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * @package: com.wz.springframework.beans
 * @Author:
 * @Date: 2018/5/26 0026 上午 3:00
 */
public class WzModelandView {
    private String viewName;
    private Map<String,?> model;

   public WzModelandView(){}

    public WzModelandView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
