package demo;

import com.wz.springframework.annotation.WzReposity;
import com.wz.springframework.annotation.WzReposity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @package: com.wz.spring.demo
 * @Author:
 * @Date: 2018/4/29 0029 下午 7:52
 */
@WzReposity
public class HelloReposity {
    private Map<String,User> map=new HashMap<String,User>();

    public void addUser(User u){
        int a=this.randoms();
        map.put(a+"",u);
    }

    public void addUser2(User u){
        int b=genaratenum();
        map.put(b+"",u);
    }

    public int randoms(){
      return new Random().nextInt(50);
    }

    public int genaratenum(){
        final int count=0;
        AtomicInteger atomicInteger=new AtomicInteger(2);
        int a=atomicInteger.incrementAndGet();
        return a;
    }
}
