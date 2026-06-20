package tests;

import base.BaseTest;
import pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HP13LoginCamareroExitosoTest extends BaseTest {

    @Test
    void loginCamareroExitoso() {
        try {
            System.out.println("CP-007: Login exitoso con credenciales válidas de Camarero");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("camarero", "123");

            boolean panelVisible = wait.until(d -> {
                String texto = d.getPageSource();
                return texto.contains("Crear pedido")
                        || texto.contains("Pedidos Actuales")
                        || texto.contains("Pre-Boleta");
            });

            Assertions.assertTrue(
                    panelVisible,
                    "El Panel Camarero no se muestra después del inicio de sesión."
            );

            System.out.println("CP-007 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-007: " + e.getMessage());
        }
    }
}