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

    // Pausa de 2 segundos antes de ingresar la contraseña
    await driver.findElement(By.id("contrasena")).sendKeys("megustalamanzana");

    // Pausa de 2 segundos antes de hacer clic en el botón de inicio de sesión
    await driver.findElement(By.className("camposLlenar")).submit();

    await sleep(5000);

    // Identificar el producto "chocolate" (debes ajustar esta parte)
    let product = await driver.findElement(
      By.id("2796978b-293a-4065-9af5-26ea1cddf676")
    ); // Reemplaza "id_del_producto_chocolate" con el ID real

    // Hacer clic en el producto "chocolate"
    await product.click();

    await sleep(2000);
    // Esperar a que las tiendas estén presentes antes de realizar la selección
    await driver.wait(
      until.elementLocated(By.className("scrollBoxTienda")),
      5000
    );

    // Seleccionar la sección "RadioOptions"
    let radioOptionsSection = await driver.findElement(By.id("RadioOptions"));

    // Esperar a que las opciones de tienda estén presentes dentro de "RadioOptions"
    await driver.wait(
      until.elementLocated(By.css("#RadioOptions input[type='radio']")),
      5000
    );

    // Seleccionar la primera opción de tienda (ajusta el selector según tu estructura real)
    let primeraOpcionTienda = await radioOptionsSection.findElement(
      By.css("#RadioOptions input[type='radio']:first-child")
    );

    // Desplazarse al elemento para hacerlo visible
    await driver.executeScript(
      "arguments[0].scrollIntoView(true);",
      primeraOpcionTienda
    );

    // Esperar un breve momento después del desplazamiento
    await sleep(1000);

    // Hacer clic en la primera opción de tienda utilizando JavaScript
    await driver.executeScript("arguments[0].click();", primeraOpcionTienda);

    await sleep(2000);
    // Hacer clic en el botón "Agregar e ir a pagar 0"
    let agregarPagarButton = await driver.findElement(
      By.id("BAgregarEirPagar")
    );
    await agregarPagarButton.click();
    await sleep(2000);
    // Espera a que los elementos de entrada estén presentes antes de llenar los datos
    await driver.wait(until.elementLocated(By.id("Nombre-tiendas")), 5000);

    // Llena los datos de ubicación
    await driver
      .findElement(By.id("Nombre-tiendas"))
      .sendKeys("Nombre de la tienda");
    await driver.findElement(By.id("inp2")).sendKeys("Estare en el piso M");

    // Espera a que la estimación esté presente antes de continuar
    await driver.wait(until.elementLocated(By.className("tiempo")), 5000);

    // Espera un momento antes de seleccionar el método de pago
    await sleep(2000);

    // Selecciona el método de pago (por ejemplo, Efectivo)
    await driver.findElement(By.id("mod1")).click();

    // Espera un momento antes de continuar
    await sleep(2000);

    // Selecciona el tipo de entrega (por ejemplo, Domicilio)
    await driver.findElement(By.id("mod4")).click();

    // Espera un momento antes de continuar
    await sleep(2000);

    // Hacer clic en el botón "Hacer pedido"
    await driver.findElement(By.id("hacepedido")).click();
    await sleep(50000);
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
