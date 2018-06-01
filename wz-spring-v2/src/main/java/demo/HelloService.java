package demo;

import com.wz.springframework.annotation.WzAutowired;
import com.wz.springframework.annotation.WzService;
import com.wz.springframework.annotation.WzAutowired;
import com.wz.springframework.annotation.WzService;

/**
 * @param
 * @package: com.wz.spring.demo
 * @Author:
 * @Description:
 * @Date: 2018/4/29 0029 下午 7:40
 */

@WzService
public class HelloService {
@WzAutowired
private HelloReposity helloReposity;

public void addhello(User u){
    helloReposity.addUser2(u);
    System.out.println(u);
}

}
