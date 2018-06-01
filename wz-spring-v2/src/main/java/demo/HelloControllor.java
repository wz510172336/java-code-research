package demo;

import com.wz.springframework.annotation.WzAutowired;
import com.wz.springframework.annotation.WzControllor;
import com.wz.springframework.annotation.WzRequestMapping;
import com.wz.springframework.annotation.WzRequestParam;
import com.wz.springframework.annotation.WzAutowired;
import com.wz.springframework.annotation.WzControllor;
import com.wz.springframework.annotation.WzRequestMapping;
import com.wz.springframework.annotation.WzRequestParam;
import com.wz.springframework.beans.WzModelandView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @package: com.wz.spring.demo
 * @Author:
 * @Description:
 * @Date: 2018/4/29 0029 下午 7:40
 */

@WzControllor
@WzRequestMapping("/test")
public class HelloControllor {
    @WzAutowired
    private HelloService helloService;
    @WzRequestMapping("/add" )
    public WzModelandView insertUser(@WzRequestParam(value ="wzname")String name, @WzRequestParam(value ="wzage")String age,HttpServletResponse response)throws IOException{
       User user=new User();
       user.setAge(Integer.parseInt(age));
       user.setName(name);
     helloService.addhello(user);
        System.out.println("----------------------------");
       WzModelandView wzModelandView=new WzModelandView();
       Map<String,User> userMap=new HashMap();
       userMap.put("teacher",user);
       wzModelandView.setModel(userMap);
       wzModelandView.setViewName("first.html");
       response.setContentType("utf-8");
       response.getWriter().write("nih");
       return wzModelandView;
    }

}
