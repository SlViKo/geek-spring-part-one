package ru.geekbrains.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;



@Configuration
@PropertySource("classpath:application.properties")
public class SpringConfig {

    @Value("${database.driver.class}")
    private String driverClassName;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Bean
    public ChatServer chatServer(AuthService authService) {
        return new ChatServer(authService);
    }

    @Bean
    public AuthService authService(UserRepository userRepository) {
        return new AuthServiceJdbcImpl(userRepository);
    }

    @Bean
    public UserRepository userRepository(DataSource dataSource) throws SQLException {
        return new UserRepository(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setPassword("qQ15935714789632");
        ds.setUsername("root");
        ds.setUrl("jdbc:mysql://localhost:3306/network_chat?&userUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        return ds;
    }

}
