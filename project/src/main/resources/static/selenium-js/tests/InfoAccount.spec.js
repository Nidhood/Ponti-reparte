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

    // Espera a que el elemento esté presente antes de realizar el clic
    await driver.wait(until.elementLocated(By.id("ingreso")), 5000);

    // Localiza el botón de ingreso por su ID y haz clic en él
    let loginButton = await driver.findElement(By.id("ingreso"));
    await loginButton.click();

    // Ingresa los datos de inicio de sesión en los campos correspondientes
    await driver.findElement(By.id("Nombreusuario")).sendKeys("msrobot");
    await driver.findElement(By.id("contrasena")).sendKeys("megustalamanzana");
    await driver.findElement(By.className("camposLlenar")).submit();
    await sleep(5000);

    // Espera a que el elemento "infocuenta" esté presente y sea interactable
    let infoCuentaButton = await driver.findElement(By.id("fotoPerfil"));
    await infoCuentaButton.click();
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
