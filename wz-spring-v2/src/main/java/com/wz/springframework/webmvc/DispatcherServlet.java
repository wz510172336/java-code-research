package com.wz.springframework.webmvc;

import com.wz.springframework.annotation.WzControllor;
import com.wz.springframework.annotation.WzRequestMapping;
import com.wz.springframework.annotation.WzRequestParam;
import com.wz.springframework.beans.HandlerAdapter;
import com.wz.springframework.beans.HandlerMapping;
import com.wz.springframework.beans.ViewResoler;
import com.wz.springframework.beans.WzModelandView;
import com.wz.springframework.context.WzApplicationContext;
import com.wz.springframework.support.ViewReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @package: com.wz.springframework.webmvc
 * @Author:
 * @Date: 2018/5/1 0001 上午 10:36
 */
public class DispatcherServlet extends HttpServlet {
    //方法的HandlerMapping,跟url关联
    List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    //一个方法上的HandlerMapping,HandlerAdapter，跟参数关联
    Map<HandlerMapping, HandlerAdapter> handlerAdapterMap = new LinkedHashMap<>();
    // view 列表
    List<ViewResoler> resolers = new ArrayList<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        WzApplicationContext wzApplicationContext = new WzApplicationContext(config.getInitParameter("contextConfigLocation"));
        super.init(config);
        initHandlerMapping(wzApplicationContext);
        initHandlerAdapter(wzApplicationContext);
        initViewSovler(wzApplicationContext);
    }

    //初始化试图解析器
    private void initViewSovler(WzApplicationContext wzApplicationContext) {
        String[] config = wzApplicationContext.getConfigLocations();
        ViewReader viewReader = new ViewReader(config[0]);
        Map<String, File> viewMap = viewReader.loadViewList();
        viewMap.entrySet().stream().forEach(viewEntry -> {
            ViewResoler viewResoler = new ViewResoler(viewEntry.getKey(), viewEntry.getValue());
            resolers.add(viewResoler);
        });


    }

    // 初始化handlerAdapter
    private void initHandlerAdapter(WzApplicationContext wzApplicationContext) {
        handlerMappings.stream().forEach(handlerMapping -> {
            //方法上的参数列表
            Map<String, Integer> paramHandler = new HashMap<String, Integer>();
            //带注解参数
            Annotation[][] pa =handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof WzRequestParam) {
                        String str = ((WzRequestParam) a).value();
                        if (!"".equals(str)) {
                            paramHandler.put(str, i);
                        }
                    }
                }
            }
            //不带注解的参数
            Class<?>[] clayy = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < clayy.length; i++) {
                if (clayy[i] == HttpServletResponse.class || clayy[i] == HttpServletRequest.class) {
                    paramHandler.put(clayy[i].getSimpleName(), i);
                }
            }
            handlerAdapterMap.put(handlerMapping, new HandlerAdapter(paramHandler));
        });
    }

    //初始化 handlerMapping
    private void initHandlerMapping(WzApplicationContext wzApplicationContext) {
        wzApplicationContext.getBeanFactoryNames().stream().forEach((factoryName) -> {
            Object instance =wzApplicationContext.getBean(factoryName);
            Class<?> clazz=instance.getClass();
            if (clazz.isAnnotationPresent(WzControllor.class)) {
                WzRequestMapping wzRequestMapping = clazz.getAnnotation(WzRequestMapping.class);
                String BaseUrl = wzRequestMapping.value();
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(WzRequestMapping.class)) {
                        WzRequestMapping wzRequestMapping2 = method.getAnnotation(WzRequestMapping.class);
                        BaseUrl = (BaseUrl + "/" + wzRequestMapping2.value()).replaceAll("/+", "/");
                        Pattern pattern = Pattern.compile(BaseUrl);
                        HandlerMapping handlerMapping = new HandlerMapping();
                        handlerMapping.setPattern(pattern);
                        handlerMapping.setControllor(instance);
                        handlerMapping.setMethod(method);
                        handlerMappings.add(handlerMapping);
                    }
                }

            }


        });
    }


    //接受外部请求阶段
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("<font size=25px,color=red>500 Error</font>" + "\r\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandlerMapping handlerMapping = getHandler(req);
        if (handlerMapping == null) {
            throw new Exception("404,not Found");
        }
        HandlerAdapter ha = getHandlerAdapter(handlerMapping);
        WzModelandView wzModelandView = ha.handle(req, resp, handlerMapping);
        if (wzModelandView == null) return;
        ProcessResult(resp, wzModelandView);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        String url = req.getRequestURI();
        String root = req.getContextPath();
        url = url.replace(root, "/").replaceAll("/+", "/");
        for (HandlerMapping handlerMapping : handlerMappings) {
            Pattern pattern = handlerMapping.getPattern();
            Matcher matcher = pattern.matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handlerMapping;
        }
        return null;
    }


    private HandlerAdapter getHandlerAdapter(HandlerMapping handlerMapping) {
        if (handlerAdapterMap.isEmpty()) {
            return null;
        }
        return handlerAdapterMap.get(handlerMapping);
    }

    private void ProcessResult(HttpServletResponse resp, WzModelandView wzModelandView) throws Exception {
        if(wzModelandView==null)return;
        if(resolers.isEmpty())return;
        for (ViewResoler viewResoler : resolers) {
            if (!wzModelandView.getViewName().equals(viewResoler.getViewName())) {
                continue;
            }
           String out= viewResoler.resolveView(wzModelandView);
           if(out!=null) {resp.getWriter().write(out);}
            break;
        }
    }


}