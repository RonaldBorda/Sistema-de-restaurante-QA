package tests;

import base.BaseTest;
import pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class HP17LoginCamposVaciosTest extends BaseTest {

    @Test
    void loginConCamposVacios() {
        try {
            System.out.println("CP-011: Login fallido con campos vacíos");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("", "");

            boolean loginSigueVisible =
                    !driver.findElements(By.name("username")).isEmpty()
                            && !driver.findElements(By.name("password")).isEmpty();

            Assertions.assertTrue(
                    loginSigueVisible,
                    "El sistema no mantuvo visible el login con campos vacíos."
            );

            System.out.println("HP-17 ejecutado correctamente.");
        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-011: " + e.getMessage());
        }
    }
}