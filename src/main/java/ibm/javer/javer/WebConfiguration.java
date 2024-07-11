package ibm.javer.javer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
    @Bean(name = "applicationName")
    public String applicationName(){
        return "Banco Javer";
    }
    @Bean(name = "nomeCliente")
    public String nomeCliente(){
        return "Letícia";
    }
}
