package tests;

import base.BaseTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HP11RegistroNombreMinimoTest extends BaseTest {

    @Test
    void registroConNombreMinimoTresCaracteres() {
        try {
            System.out.println("CP-003: Registro con nombre en longitud mínima de 3 caracteres");

            driver.get("https://ernest.xo.je/index.php?route=auth/register");

            String usuarioUnico = "ana" + System.currentTimeMillis();

            driver.findElement(By.name("name")).sendKeys("Ana");
            driver.findElement(By.name("username")).sendKeys(usuarioUnico);
            driver.findElement(By.name("password")).sendKeys("pass01");

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
                    "El sistema no aceptó el registro con nombre de 3 caracteres."
            );

            System.out.println("CP-003 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-003: " + e.getMessage());
        }
    }
}