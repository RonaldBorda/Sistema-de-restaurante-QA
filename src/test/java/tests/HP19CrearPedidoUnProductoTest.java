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

public class HP19CrearPedidoUnProductoTest extends BaseTest {

    @Test
    void crearPedidoConUnProducto() {
        try {
            System.out.println("CP-019: Crear pedido exitosamente con un producto seleccionado");

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

            wait.until(d -> d.findElement(
                    By.xpath("//button[contains(text(),'Guardar Pedido')]")
            )).click();

            boolean pedidoAparece = wait.until(d -> {
                String textoPagina = d.getPageSource();
                return textoPagina.contains("Pedidos Actuales")
                        || textoPagina.contains("Pendiente")
                        || textoPagina.contains(productoSeleccionado);
            });

            Assertions.assertTrue(
                    pedidoAparece,
                    "El pedido no aparece como pendiente o en pedidos actuales."
            );

            System.out.println("CP-019 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-019: " + e.getMessage());
        }
    }
}