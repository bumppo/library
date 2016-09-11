package com.dev.config;

import com.dev.entity.BookEntity;
import com.dev.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
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

    @Value("${postgres.driverClassName}") private String postgresDriver;
    @Value("${postgres.url}") private String postgresURL;
    @Value("${postgres.username}") private String postgresUsername;
    @Value("${postgres.password}") private String postgresPassword;
    @Value("${h2.init}") private String h2Init;
    @Value("${h2.populate}") private String h2Populate;
    @Value("${users.table}") private String usersTable;
    @Value("${books.table}")private String booksTable;

    @Bean(destroyMethod="shutdown")
    @Profile("h2")
    public DataSource h2Source() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(h2Init)
                .addScript(h2Populate)
                .build();
    }

    @Bean
    @Profile("postgres")
    public DataSource postgresSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName(postgresDriver);
        dataSource.setUrl(postgresURL);
        dataSource.setUsername(postgresUsername);
        dataSource.setPassword(postgresPassword);
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
    public BeanPropertyRowMapper userRowMapper(){
        return BeanPropertyRowMapper.newInstance(UserEntity.class);
    }
    @Bean
    public BeanPropertyRowMapper bookRowMapper(){
        return BeanPropertyRowMapper.newInstance(BookEntity.class);
    }

    @Bean
    public SimpleJdbcInsert insertUser(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource)
                .withTableName(usersTable)
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "password");
    }
    @Bean
    public SimpleJdbcInsert insertBook(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource)
                .withTableName(booksTable)
                .usingGeneratedKeyColumns("id");
    }
}
