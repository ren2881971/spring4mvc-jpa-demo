package com.jit.ota4.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.activation.DataSource;
import javax.annotation.Resource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/9.
 */
@Configuration
@ComponentScan("com.jit.ota4")
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource(value = {"classpath:/META-INF/jdbc.properties"})

@EnableJpaRepositories(basePackages = "com.jit.ota4.repository",repositoryFactoryBeanClass = com.jit.ota4.repository.BaseRepositoryFactoryBean.class)
@Import({MvcConfig.class,SecurityConfiguration.class})
public class WebAppConfig {
    @Value("${basics.connection.driver_class}")
    private String driverClass;
    @Value("${basics.connection.url}")
    private String jdbcUrl;
    @Value("${basics.connection.username}")
    private String user;
    @Value("${basics.connection.password}")
    private String password;
    @Value("${basics.connection.maxPoolSize}")
    private String maxPoolSize;
    @Value("${basics.connection.minPoolSize}")
    private String minPoolSize;
    @Value("${basics.connection.initialPoolSize}")
    private String initPoolSize;
    @Value("${basics.connection.maxIdleTime}")
    private String maxIdleTime;
    @Value("${basics.connection.checkoutTimeout}")
    private String checkoutTimeout;
    @Value("${basics.connection.acquireIncrement}")
    private String acquireIncrement;
    @Value("${basics.connection.acquireRetryAttempts}")
    private String acquireRetryAttempts;
    @Value("${basics.connection.acquireRetryDelay}")
    private String acquireRetryDelay;
    @Value("${basics.connection.autoCommitOnClose}")
    private boolean autoCommitOnClose;
    @Value("${basics.connection.automaticTestTable}")
    private String automaticTestTable;
    @Value("${basics.connection.breakAfterAcquireFailure}")
    private boolean breakAfterAcquireFailure;
    @Value("${basics.connection.idleConnectionTestPeriod}")
    private String idleConnectionTestPeriod;
    @Value("${basics.connection.maxStatements}")
    private String maxStatements;
    @Value("${basics.connection.maxStatementsPerConnection}")
    private String maxStatementsPerConnection;
    @Value("${basics.connection.dialect}")
    private String dialect;
    @Value("${basics.connection.showSql}")
    private boolean showSQL;

    private static final String P_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.jit.ota4.entity";

    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverClass);
            dataSource.setJdbcUrl(this.jdbcUrl);
            dataSource.setUser(this.user);
            dataSource.setPassword(this.password);
            dataSource.setMaxPoolSize(Integer.valueOf(this.maxPoolSize));
            dataSource.setMinPoolSize(Integer.valueOf(this.minPoolSize));
            dataSource.setInitialPoolSize(Integer.valueOf(this.initPoolSize));
            dataSource.setMaxIdleTime(Integer.valueOf(this.maxIdleTime));
            dataSource.setCheckoutTimeout(Integer.valueOf(this.checkoutTimeout));
            dataSource.setAcquireIncrement(Integer.valueOf(this.acquireIncrement));
            dataSource.setAcquireRetryAttempts(Integer.valueOf(this.acquireRetryAttempts));
            dataSource.setAcquireRetryDelay(Integer.valueOf(this.acquireRetryDelay));
            dataSource.setAutoCommitOnClose(this.autoCommitOnClose);
            dataSource.setAutomaticTestTable(this.automaticTestTable);
            dataSource.setBreakAfterAcquireFailure(this.breakAfterAcquireFailure);
            dataSource.setIdleConnectionTestPeriod(Integer.valueOf(this.idleConnectionTestPeriod));
            dataSource.setMaxStatements(Integer.valueOf(this.maxStatements));
            dataSource.setMaxStatementsPerConnection(Integer.valueOf(this.maxStatementsPerConnection));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setPackagesToScan(P_ENTITYMANAGER_PACKAGES_TO_SCAN);
        //b.setPersistenceUnitName("mysqldb");
        bean.setJpaDialect(new HibernateJpaDialect());
        bean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        bean.setPersistenceProvider(new HibernatePersistence());
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(showSQL);
        adapter.setDatabasePlatform(dialect);
        bean.setJpaVendorAdapter(adapter);
        bean.setJpaProperties(jpaProperties());

        return bean;
    }

    private Properties jpaProperties(){
        Properties pro = new Properties();
        pro.put("hibernate.dialect",this.dialect);
        pro.put("hibernate.connection.release_mode","on_close");
        pro.put("hibernate.connection.useUnicode",true);
        pro.put("hibernate.connection.characterEncoding","utf8");
        pro.put("hibernate.show_sql",showSQL);
        pro.put("hibernate.connection.autocommit",false);
        pro.put("hibernate.cache.provider_class","org.hibernate.cache.EhCacheProvider");
        pro.put("hibernate.cache.use_second_level_cache",false);
        pro.put("hibernate.cache.use_query_cache",false);
        pro.put("hibernate.hbm2ddl.auto","update");
        return pro;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
