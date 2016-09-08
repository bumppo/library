package com.dev.config;

import com.dev.entity.BookEntity;
import com.dev.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:db/db.properties")
@ComponentScan(basePackages = {"com.dev.repository"})
public class DalConfig {

    @Autowired
    Environment env;

    @Bean(destroyMethod="shutdown")
    @Profile("h2")
    public DataSource h2Source() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/initH2.sql")
                .addScript("classpath:db/populateH2.sql")
                .build();
    }

    @Bean
    @Profile("postgres")
    public DataSource postgresSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();

        dataSource.setDriverClassName(env.getProperty("postgres.driverClassName"));
        dataSource.setUrl(env.getProperty("postgres.url"));
        dataSource.setUsername(env.getProperty("postgres.username"));
        dataSource.setPassword(env.getProperty("postgres.password"));

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Qualifier("userRowMapper")
    public BeanPropertyRowMapper userRowMapper(){
        return BeanPropertyRowMapper.newInstance(UserEntity.class);
    }
    @Bean
    @Qualifier("bookRowMapper")
    public BeanPropertyRowMapper bookRowMapper(){
        return BeanPropertyRowMapper.newInstance(BookEntity.class);
    }

    @Bean
    @Qualifier("insertUser")
    public SimpleJdbcInsert insertUser(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "password");
    }
    @Bean
    @Qualifier("insertBook")
    public SimpleJdbcInsert insertBook(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource)
                .withTableName("books")
                .usingGeneratedKeyColumns("id");
    }
}
