const { Builder, By, until } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome"); // Agrega esta línea

// Función para pausar durante un tiempo dado
function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function loginAccount() {
  let driver;

  try {
    // Configura las opciones del navegador para abrir en pantalla completa
    let chromeOptions = new chrome.Options(); // Asegúrate de importar "chrome" aquí
    chromeOptions.addArguments("--start-maximized");

    // Intenta construir el driver con las opciones
    driver = await new Builder()
      .forBrowser("chrome")
      .setChromeOptions(chromeOptions)
      .build();

    await driver.get("http://127.0.0.1:5500/index.html");

    // Después de cargar la página, espera a que el enlace de registro esté presente y haz clic en él
    await driver.wait(until.elementLocated(By.className("reg")), 5000);
    let registerLink = await driver.findElement(By.className("reg"));
    await registerLink.click();

    // Ingresa todos los campos necesarios para el registro
    await driver.findElement(By.id("ID")).sendKeys("0002329420");
    await driver.findElement(By.id("Nombreusuario")).sendKeys("Jarvis");
    await driver.findElement(By.id("contrasena")).sendKeys("SoyUnRobot");
    await driver.findElement(By.id("nombre")).sendKeys("Iron");
    await driver.findElement(By.id("apellido")).sendKeys("Man");
    await driver.findElement(By.id("telefono")).sendKeys("3156395755");
    await driver
      .findElement(By.id("correo"))
      .sendKeys("elGatoConBotas@gmail.com");
    await driver.findElement(By.id("fecha")).sendKeys("01/01/2000");
    await sleep(5000);

    // Haz clic en el botón de registro
    await driver.findElement(By.className("camposLlenar")).submit();

    await sleep(10000);
    // Continuar con otras acciones después de hacer clic en el botón
  } catch (error) {
    // Captura cualquier error y muestra un mensaje en la consola
    console.error("Error:", error.message);
  } finally {
    // Asegúrate de que el driver se cierre incluso en caso de error
    if (driver) {
      await driver.quit();
    }
  }
}

loginAccount();
