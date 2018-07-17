package reasearch.mybatis.dal.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import reasearch.mybatis.dal.plugins.ExecutorPlugin;
import reasearch.mybatis.dal.plugins.TestPlugin;
import reasearch.mybatis.dal.typeHandle.UserTypehandler;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @param
 * @Author: YanLong
 * @Description:
 * @Date: 2018/4/7 0007 下午 10:14
 */
@Configuration
@MapperScan(basePackages = "reasearch.mybatis.dal.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfig {
    @Resource
    public DataSource dataSource;

    @Lazy(value = false)
    @Bean(name = "sqlSesstionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new UserTypehandler()});
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new ExecutorPlugin(),new TestPlugin()});
        SqlSessionFactory factory = sqlSessionFactoryBean.getObject();
        factory.getConfiguration().setAggressiveLazyLoading(false);
        factory.getConfiguration().setLazyLoadingEnabled(true);
        return factory;
    }

    private PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Primary
    @Lazy(false)
    @Bean(name = "sqlSessionTemplete")
    public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(getSqlSessionFactory(), ExecutorType.SIMPLE);
    }

    @Lazy
    @Bean(name = "batchsqs")
    public SqlSessionTemplate getBatch() throws Exception {
        return new SqlSessionTemplate(getSqlSessionFactory(), ExecutorType.BATCH);
    }

    @Lazy
    @Bean(name = "txManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;


    }
}
