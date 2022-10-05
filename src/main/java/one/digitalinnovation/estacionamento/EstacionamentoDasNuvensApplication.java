package one.digitalinnovation.estacionamento;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Estacionamento nas Nuvens API", version = "0.0.1-SNAPSHOT", description = "API criada como parte do bootcamp Spring Boot Experience"))
@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class EstacionamentoDasNuvensApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstacionamentoDasNuvensApplication.class, args);
    }

}
