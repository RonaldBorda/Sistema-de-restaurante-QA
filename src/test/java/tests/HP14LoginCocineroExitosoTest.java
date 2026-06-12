package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.CocineroPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HP14LoginCocineroExitosoTest extends BaseTest {

    @Test
    void loginCocineroExitoso() {
        try {
            System.out.println("CP-008: Login exitoso con credenciales válidas de Cocinero");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("cocinero", "123");

            CocineroPage cocineroPage = new CocineroPage(driver);

            Assertions.assertTrue(
                    cocineroPage.panelVisible(),
                    "El Panel Cocinero no se muestra después del inicio de sesión."
            );

            Assertions.assertTrue(
                    cocineroPage.pedidosPendientesVisible(),
                    "La sección Pedidos Pendientes no se muestra."
            );

            System.out.println("CP-008 ejecutado correctamente.");
        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-008: " + e.getMessage());
        }
    }
}