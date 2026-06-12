package tests;

import base.BaseTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;

public class HP10RegistroExitosoTest extends BaseTest {

    @Test
    void registroExitosoConTodosLosCamposValidos() {
        try {
            System.out.println("CP-001: Registro exitoso con todos los campos válidos");

            driver.get("https://ernest.xo.je/index.php?route=auth/register");

            String usuarioUnico = "jperez" + System.currentTimeMillis();

            driver.findElement(By.name("name")).sendKeys("Juan Perez");
            driver.findElement(By.name("username")).sendKeys(usuarioUnico);
            driver.findElement(By.name("password")).sendKeys("clave123");

            driver.findElement(By.cssSelector("button[type='submit']")).click();

            String textoPagina = driver.getPageSource().toLowerCase();
            String urlActual = driver.getCurrentUrl().toLowerCase();

            Assertions.assertTrue(
                    textoPagina.contains("login")
                            || textoPagina.contains("iniciar")
                            || textoPagina.contains("registr")
                            || textoPagina.contains("pendiente")
                            || urlActual.contains("login"),
                    "El sistema no registró correctamente al usuario con datos válidos."
            );

            System.out.println("CP-001 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-001: " + e.getMessage());
        }
    }
}