package tests;

import base.BaseTest;
import pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HP19CrearPedidoUnProductoTest extends BaseTest {

    @Test
    void crearPedidoConUnProducto() {
        try {
            System.out.println("CP-019: Crear pedido exitosamente con un producto seleccionado");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.abrirLogin();
            loginPage.iniciarSesion("camarero", "123");

            driver.findElement(By.xpath("//a[contains(text(),'Crear pedido') or contains(text(),'Crear Pedido')]")).click();

            List<WebElement> selects = driver.findElements(By.tagName("select"));
            Select productos = new Select(selects.get(0));
            productos.selectByVisibleText(productos.getOptions().get(1).getText());

            WebElement cantidad = driver.findElement(By.cssSelector("input[type='number']"));
            cantidad.clear();
            cantidad.sendKeys("1");

            driver.findElement(By.xpath("//button[contains(text(),'Agregar')]")).click();
            driver.findElement(By.xpath("//button[contains(text(),'Guardar Pedido')]")).click();

            String textoPagina = driver.getPageSource();

            Assertions.assertTrue(
                    textoPagina.contains("Pedidos Actuales") || textoPagina.contains("Pendiente"),
                    "El pedido no aparece como pendiente o en pedidos actuales."
            );

            System.out.println("CP-019 ejecutado correctamente.");
        } catch (Exception e) {
            Assertions.fail("Error durante la ejecución de CP-019: " + e.getMessage());
        }
    }
}