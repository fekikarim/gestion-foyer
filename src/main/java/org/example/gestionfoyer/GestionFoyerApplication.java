package org.example.gestionfoyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class GestionFoyerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionFoyerApplication.class, args);
//        System.out.println("Application Gestion Foyer démarrée avec succès!");
//        System.out.println("Swagger UI disponible sur: http://localhost:8089/gestion-foyer/swagger-ui.html");
    }
}
