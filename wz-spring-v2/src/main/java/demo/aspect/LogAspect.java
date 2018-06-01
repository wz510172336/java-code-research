package demo.aspect;

import org.aspectj.lang.JoinPoint;

/**
 * @package: demo.aspect
 * @Author:
 * @Date: 2018/5/29 0029 上午 1:09
 */
public class LogAspect {
    public void before(JoinPoint jointPoint){
        System.out.println("日志开始le"+jointPoint);
    }
    public void around(JoinPoint jointPoint){
        System.out.println("方法执行环绕");
    }
    public void  after(JoinPoint jointPoint){
        System.out.println("方法执行后");

    }
    public void  returna(JoinPoint jointPoint){
        System.out.println("返回后执行" +
                "");

    }

    public void throwE(JoinPoint jointPoint){
        System.out.println("抛出异常后日志记录");
    }
}
