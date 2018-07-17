package reasearch.mybatis.dal.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

/**
 * @param
 * @Author: YanLong
 * @Description:
 * @Date: 2018/4/7 0007 下午 11:29
 */
@Configuration
@ComponentScan(basePackages = "reasearch.mybatis.dal")
@PropertySource("classpath:db.properties")
public class DataSourceConfig {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}" )
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    @Bean(name = "dataSource")
    public DataSource getDataSource(){
      DruidDataSource druidDataSource=new DruidDataSource();
      druidDataSource.setDriverClassName(driver);
      druidDataSource.setUrl(url);
      druidDataSource.setUsername(username);
      druidDataSource.setPassword(password);
      druidDataSource.setMinIdle(400);
      druidDataSource.setMaxActive(800);
      return druidDataSource;
  }


     @Bean
     public static PropertySourcesPlaceholderConfigurer loadProperties() {
         PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
         return configurer;
  }
}
