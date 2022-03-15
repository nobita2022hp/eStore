package com.estore.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@PropertySource("classpath:datasource.properties")
public class HibernateConfig {

    @Autowired
    private org.springframework.core.env.Environment env;

    @Bean
    public DataSource getDataSource(){
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
        var factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("com.estore.domain");
        factoryBean.setDataSource(dataSource);

        var props = factoryBean.getHibernateProperties();
        props.setProperty(Environment.DIALECT, env.getProperty("hb.dialect"));
        props.setProperty(Environment.SHOW_SQL, env.getProperty("hb.show-sql"));
        props.setProperty(Environment.HBM2DDL_AUTO, env.getProperty("hb.ddl-auto"));
        props.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, env.getProperty("hb.session"));

        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }
}
