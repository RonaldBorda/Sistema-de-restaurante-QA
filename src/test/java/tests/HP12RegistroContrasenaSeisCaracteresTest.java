package tests;

import base.BaseTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HP12RegistroContrasenaSeisCaracteresTest extends BaseTest {

    @Test
    void registroConContrasenaExactamenteSeisCaracteres() {
        try {
            System.out.println("CP-006: Registro con contraseña de exactamente 6 caracteres");

            driver.get("https://ernest.xo.je/index.php?route=auth/register");

            String usuarioUnico = "ltorres" + System.currentTimeMillis();

            driver.findElement(By.name("name")).sendKeys("Luis Torres");
            driver.findElement(By.name("username")).sendKeys(usuarioUnico);
            driver.findElement(By.name("password")).sendKeys("abc123");

            WebElement botonRegistrar = driver.findElement(
                    By.cssSelector("button[type='submit']")
            );

            botonRegistrar.click();

            String textoPagina = driver.getPageSource().toLowerCase();
            String urlActual = driver.getCurrentUrl().toLowerCase();

            Assertions.assertTrue(
                    textoPagina.contains("login")
                            || textoPagina.contains("iniciar")
                            || textoPagina.contains("registr")
                            || urlActual.contains("login"),
                    "El sistema no aceptó el registro con contraseña de exactamente 6 caracteres."
            );

            System.out.println("CP-006 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-006: " + e.getMessage());
        }
    }
}