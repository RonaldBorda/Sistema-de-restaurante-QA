package tests;

import base.BaseTest;
import pages.LoginPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HP22ProductoDeshabilitadoNoVisibleTest extends BaseTest {

    @Test
    void productoDeshabilitadoNoApareceEnDesplegable() {
        try {
            System.out.println("CP-22: Producto deshabilitado no aparece en Productos disponibles");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("camarero", "123");

            driver.findElement(
                    By.xpath("//a[contains(text(),'Crear pedido') or contains(text(),'Crear Pedido')]")
            ).click();

            WebElement desplegableProductos = driver.findElements(By.tagName("select")).get(0);
            Select productos = new Select(desplegableProductos);

            String textoOpciones = "";

            for (WebElement opcion : productos.getOptions()) {
                textoOpciones = textoOpciones + " " + opcion.getText().toLowerCase();
            }

            Assertions.assertFalse(
                    textoOpciones.contains("causa rellena"),
                    "El producto deshabilitado 'Causa rellena' aparece en el desplegable de productos disponibles."
            );

            System.out.println("CP-022 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-024: " + e.getMessage());
        }
    }
}