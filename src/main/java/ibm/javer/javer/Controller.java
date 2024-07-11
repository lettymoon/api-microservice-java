package ibm.javer.javer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    @Qualifier("applicationName")
    private String applicationName;
    @Autowired
    @Qualifier("nomeCliente")
    private String nomeCliente;

    @GetMapping("/banco-javer")
    public String BancoJaver(){
        return applicationName;
    }
    @GetMapping("/clientes")
    public String Clientes(){
        return nomeCliente;
    }
}
