import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import jpaConnection.orTv_JPAInterface;

@Configuration
@ComponentScan(basePackages = {"orClient_Tv_React.jpaConnection"})
public class AppConfig {

}
