package tests;

import base.BaseTest;
import pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HP16LoginConContrasenaIncorrectaTest extends BaseTest {

    @Test
    void loginConContrasenaIncorrecta() {
        try {
            System.out.println("CP-010: Login fallido con contraseña incorrecta");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("camarero", "incorrecta99");

            boolean loginSigueVisible = wait.until(d ->
                    !d.findElements(By.name("username")).isEmpty()
                            && !d.findElements(By.name("password")).isEmpty()
            );

            Assertions.assertTrue(
                    loginSigueVisible,
                    "El sistema permitió el acceso con contraseña incorrecta."
            );

            System.out.println("CP-010 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-010: " + e.getMessage());
        }
    }
}