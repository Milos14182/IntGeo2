package com.milos.neo4j.intrestinggeography;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.milos.neo4j.repository")
@EnableTransactionManagement
public class MyNeo4jConfiguration extends Neo4jConfiguration {

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://intgeo:0EbuUIXFA7i20bHS5nMP@hobby-cjpmkomjfhocgbkeddbolenl.dbs.graphenedb.com:24789");
        return config;
    }
    
    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "com.milos.neo4j.domain");
    }

    @Override
    @Bean
    public Session getSession() throws Exception {
        return super.getSession();
    }
//	@Bean
//	public SessionFactory getSessionFactory() {
//		 return new SessionFactory("com.milos.neo4j.domain");
//	}
//
//	@Bean
//	public Neo4jServer neo4jServer() {
//		return new RemoteServer("http://hobby-cjpmkomjfhocgbkeddbolenl.dbs.graphenedb.com:24789/db/data/", "intgeo", "0EbuUIXFA7i20bHS5nMP");
//	}
//	
//	@Bean
//    public Session getSession() throws Exception {
//        return super.getSession();
//    }

}
