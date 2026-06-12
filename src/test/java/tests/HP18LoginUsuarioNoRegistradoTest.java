package tests;

import base.BaseTest;
import pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class HP18LoginUsuarioNoRegistradoTest extends BaseTest {

    @Test
    void loginConUsuarioNoRegistrado() {
        try {
            System.out.println("CP-012: Login fallido con usuario no registrado");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("usuariofantasma", "cualquiera");

            boolean loginSigueVisible =
                    !driver.findElements(By.name("username")).isEmpty()
                            && !driver.findElements(By.name("password")).isEmpty();

            Assertions.assertTrue(
                    loginSigueVisible,
                    "El sistema permitió el acceso con un usuario no registrado."
            );

            System.out.println("HP18 - ejecutado correctamente.");
        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-012: " + e.getMessage());
        }
    }
}