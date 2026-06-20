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

public class HP20CrearPedidoMultiplesProductosTest extends BaseTest {

    @Test
    void crearPedidoConMultiplesProductos() {
        try {
            System.out.println("CP-020: Crear pedido con múltiples productos y verificar total");

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

            String productoUno = productos.getOptions().get(1).getText();
            productos.selectByVisibleText(productoUno);

            WebElement cantidad = wait.until(d ->
                    d.findElement(By.cssSelector("input[type='number']"))
            );
            cantidad.clear();
            cantidad.sendKeys("2");

            wait.until(d -> d.findElement(
                    By.xpath("//button[contains(text(),'Agregar')]")
            )).click();

            productos = new Select(wait.until(d -> d.findElements(By.tagName("select"))).get(0));

            String productoDos = productos.getOptions().get(2).getText();
            productos.selectByVisibleText(productoDos);

            cantidad = wait.until(d ->
                    d.findElement(By.cssSelector("input[type='number']"))
            );
            cantidad.clear();
            cantidad.sendKeys("1");

            wait.until(d -> d.findElement(
                    By.xpath("//button[contains(text(),'Agregar')]")
            )).click();

            boolean totalVisible = wait.until(d -> {
                String texto = d.getPageSource();
                return texto.contains("Total") || texto.contains("S/.");
            });

            Assertions.assertTrue(
                    totalVisible,
                    "No se visualiza el total del pedido antes de guardarlo."
            );

            wait.until(d -> d.findElement(
                    By.xpath("//button[contains(text(),'Guardar Pedido')]")
            )).click();

            boolean pedidoRegistrado = wait.until(d -> {
                String texto = d.getPageSource();
                return texto.contains("Pedidos Actuales")
                        || texto.contains("Pendiente")
                        || texto.contains(productoUno)
                        || texto.contains(productoDos);
            });

            Assertions.assertTrue(
                    pedidoRegistrado,
                    "El pedido con múltiples productos no aparece registrado."
            );

            System.out.println("CP-020 ejecutado correctamente.");

        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-020: " + e.getMessage());
        }
    }
}