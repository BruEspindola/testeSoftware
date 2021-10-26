package com.testeMantemLivro.exercicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ExercicioApplicationTests {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	public void login(){
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
	}

	@Test
	@Order(1)
	public void first() {
		login();
		
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1996");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("Harper Lee");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).sendKeys("O Sol é para todos");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		driver.findElement(By.cssSelector("html")).click();
		Assertions.assertEquals("Lista de livros", driver.findElement(By.id("paginaConsulta")).getText());
	}

	@Test
	@Order(2)
	public void second() {
		login();

		driver.findElement(By.linkText("Lista de Livros")).click();
		driver.findElement(By.linkText("Editar")).click();
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).clear();
		driver.findElement(By.id("autor")).sendKeys("Harper Lee");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).clear();
		driver.findElement(By.id("titulo")).sendKeys("O sol é para ninguem");
		driver.findElement(By.cssSelector(".btn")).click();
		Assertions.assertEquals("Lista de livros", driver.findElement(By.id("paginaConsulta")).getText());
	}

	@Test
	@Order(3)
	public void third() {
		login();

		driver.findElement(By.linkText("Lista de Livros")).click();
		driver.findElement(By.linkText("Excluir")).click();
		Assertions.assertEquals("Lista de livros", driver.findElement(By.id("paginaConsulta")).getText());
	}
}
