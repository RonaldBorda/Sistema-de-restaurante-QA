package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.AdminPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HP15LoginAdministradorExitosoTest extends BaseTest {

    @Test
    void loginAdministradorExitoso() {
        try {
            System.out.println("CP-009: Login exitoso de Administrador");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("admin", "admin");

            AdminPage adminPage = new AdminPage(driver);

            boolean panelAdministradorVisible = wait.until(d ->
                    adminPage.panelAdministradorVisible()
            );

            Assertions.assertTrue(
                    panelAdministradorVisible,
                    "El Panel Administrador no se muestra después del inicio de sesión."
            );

            boolean estadisticasVisibles = wait.until(d ->
                    adminPage.estadisticasVisibles()
            );

            Assertions.assertTrue(
                    estadisticasVisibles,
                    "La sección de estadísticas no se muestra."
            );

            System.out.println("CP-009 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-009: " + e.getMessage());
        }
    }
}