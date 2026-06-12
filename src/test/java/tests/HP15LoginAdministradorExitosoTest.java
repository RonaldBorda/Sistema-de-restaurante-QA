package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.AdminPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HP15LoginAdministradorExitosoTest extends BaseTest {

    @Test
    void loginAdministradorExitoso() {
        try {
            System.out.println("CP-009: Login exitoso de Administrador");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("admin", "admin");

            AdminPage adminPage = new AdminPage(driver);

            Assertions.assertTrue(
                    adminPage.panelAdministradorVisible(),
                    "El Panel Administrador no se muestra después del inicio de sesión."
            );

            Assertions.assertTrue(
                    adminPage.estadisticasVisibles(),
                    "La sección de estadísticas no se muestra."
            );

            System.out.println("CP-009 ejecutado correctamente.");
        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-009: " + e.getMessage());
        }
    }
}