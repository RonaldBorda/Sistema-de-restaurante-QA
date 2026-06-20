package tests;

import base.BaseTest;
import pages.LoginPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HP21CrearPedidoCantidadMinimaTest extends BaseTest {

    @Test
    void crearPedidoConCantidadMinimaUno() {
        try {
            System.out.println("CP-021: Crear pedido con cantidad mínima de 1 unidad");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("camarero", "123");

            wait.until(d -> d.findElement(
                    By.xpath("//a[contains(text(),'Crear pedido') or contains(text(),'Crear Pedido')]")
            )).click();

            List<WebElement> selects = wait.until(d -> d.findElements(By.tagName("select")));
            Assertions.assertFalse(selects.isEmpty(), "No se encontró el selector de productos.");

            Select productos = new Select(selects.get(0));
            String productoSeleccionado = productos.getOptions().get(1).getText();
            productos.selectByVisibleText(productoSeleccionado);

            WebElement cantidad = wait.until(d ->
                    d.findElement(By.cssSelector("input[type='number']"))
            );
            cantidad.clear();
            cantidad.sendKeys("1");

            wait.until(d -> d.findElement(
                    By.xpath("//button[contains(text(),'Agregar')]")
            )).click();

            boolean totalValido = wait.until(d -> {
                String texto = d.getPageSource();
                return texto.contains("Total:")
                        && !texto.contains("Total: S/. 0.00");
            });

            Assertions.assertTrue(
                    totalValido,
                    "El sistema no aceptó la cantidad mínima de 1 unidad."
            );

            wait.until(d -> d.findElement(
                    By.xpath("//button[contains(text(),'Guardar Pedido') or contains(text(),'Guardar pedido')]")
            )).click();

            boolean pedidoRegistrado = wait.until(d -> {
                String texto = d.getPageSource();
                return texto.contains("Pedidos Actuales")
                        || texto.contains("Pendiente")
                        || texto.contains("pending")
                        || texto.contains(productoSeleccionado);
            });

            Assertions.assertTrue(
                    pedidoRegistrado,
                    "El pedido con cantidad mínima 1 no se registró correctamente."
            );

            System.out.println("CP-021 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-021: " + e.getMessage());
        }
    }
}