package pl.camp.it.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("pl.camp.it")
public class AppConfiguration {

   @Bean
    public Connection connection(){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           return DriverManager.getConnection("jdbc:mysql://localhost:3306/shopstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8", "root", "");
       } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
       }
       return null;
    }
}
