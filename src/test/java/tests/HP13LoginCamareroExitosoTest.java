package tests;

import base.BaseTest;
import pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HP13LoginCamareroExitosoTest extends BaseTest {

    @Test
    void loginCamareroExitoso() {
        try {
            System.out.println("CP-007: Login exitoso con credenciales válidas de Camarero");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("camarero", "123");

            String textoPagina = driver.getPageSource();

            Assertions.assertTrue(
                    textoPagina.contains("Pedidos") || textoPagina.contains("Crear pedido"),
                    "El Panel Camarero no se muestra después del inicio de sesión."
            );

            System.out.println("CP-007 ejecutado correctamente.");
        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-007: " + e.getMessage());
        }
    }
}