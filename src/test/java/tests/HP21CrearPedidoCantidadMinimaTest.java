package tests;

import base.BaseTest;
import pages.LoginPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HP21CrearPedidoCantidadMinimaTest extends BaseTest {

    @Test
    void crearPedidoConCantidadMinimaUno() {
        try {
            System.out.println("CP-021: Crear pedido con cantidad mínima de 1 unidad");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("camarero", "123");

            driver.findElement(
                    By.xpath("//a[contains(text(),'Crear pedido') or contains(text(),'Crear Pedido')]")
            ).click();

            Select productos = new Select(driver.findElements(By.tagName("select")).get(0));
            productos.selectByVisibleText(productos.getOptions().get(1).getText());

            WebElement cantidad = driver.findElement(By.cssSelector("input[type='number']"));
            cantidad.clear();
            cantidad.sendKeys("1");

            driver.findElement(By.xpath("//button[contains(text(),'Agregar')]")).click();

            String textoPagina = driver.getPageSource();

            Assertions.assertTrue(
                    textoPagina.contains("Total:")
                            && !textoPagina.contains("Total: S/. 0.00"),
                    "El sistema no aceptó la cantidad mínima de 1 unidad."
            );

            driver.findElement(
                    By.xpath("//button[contains(text(),'Guardar Pedido') or contains(text(),'Guardar pedido')]")
            ).click();

            String textoFinal = driver.getPageSource();

            Assertions.assertTrue(
                    textoFinal.contains("Pedidos Actuales")
                            || textoFinal.contains("pending")
                            || textoFinal.contains("Pendiente"),
                    "El pedido con cantidad mínima 1 no se registró correctamente."
            );

            System.out.println("CP-021 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-021: " + e.getMessage());
        }
    }
}