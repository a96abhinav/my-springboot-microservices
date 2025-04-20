package com.springboot.rest.config.db.myspringappdb;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = { "com.springboot.rest.entity.myspringappdb",
		                                "com.springboot.rest.repository.myspringappdb" }, 
                       entityManagerFactoryRef = "mySpringAppDbEntityManagerFactory", 
                       transactionManagerRef = "mySpringAppDbTransactionManager",
						repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class MySpringAppDbConfig {

	@Autowired
	private MySpringAppDbProperties dbConfigs;

	@Bean(name = "mySpringAppDataSource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().url(dbConfigs.getUrl()).username(dbConfigs.getUsername())
				.password(dbConfigs.getPassword()).driverClassName(dbConfigs.getDriverClassName()).build();
	}

	@Primary
	@Bean(name = "mySpringAppDbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier("mySpringAppDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.springboot.rest.entity.myspringappdb");
		em.setPersistenceUnitName("myspringappdb");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return em;
	}

	@Bean(name = "mySpringAppDbTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("mySpringAppDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
