async function get(nombreUsuario, contrasena, tipousuario) {
  return await fetch("http://localhost:8080/usuario/ingreso", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      contrasena: contrasena,
      nombreusuario: nombreUsuario,
      tipousuario: tipousuario
    }),
  });
}

$(document).ready(function () {
  function showAlert() {
    $(".alert").addClass("show");
    $(".alert").removeClass("hide");
    $(".alert").addClass("showAlert");
    setTimeout(function () {
      $(".alert").removeClass("show");
      $(".alert").addClass("hide");
    }, 10000);
  }

  $(".close-btn").click(function () {
    $(".alert").removeClass("show");
    $(".alert").addClass("hide");
  });

  $(".camposLlenar").on("submit", function (event) {
    event.preventDefault(); // This prevents the form from being submitted

    var isValid = true; // Assume the form is valid to start\\

    var inputNombreusuario = $("#Nombreusuario").val();
    var inputcontrasena = $("#contrasena").val();
    var inputTipo;

    if ($("#Tipo").is(":checked")) {
      inputTipo = "Repartidor";
    } else {
      inputTipo = "Comprador";
    }

    if (isValid) {
      const promesa = get(inputNombreusuario, inputcontrasena, inputTipo);

      promesa
        .then((res) => {
          console.log(res.ok);
          console.log(res.json);
          if (res.ok) {
            res.json().then((data) => {
              console.log(data.id);
              console.log(data.foto.foto);
              sessionStorage.clear(); 
              sessionStorage.setItem("idUsuario", data.id); //GUARDAR EL ID DEL USUARIO EN EL LOCAL STORAGE
              sessionStorage.setItem("fotoPerfil", data.foto.foto); //PEDIR EL AVATAR Y GUARDARLO EN EL LOCAL STORAGE
              // Desvincula el evento 'submit' y luego envía el formulario
              if(inputTipo == "Comprador")
              {
                window.location.href = "../html/MenuUsuario.html";
              }
              else
              {
                window.location.href = "../html/Menudomiciliario";
              }
            });
          } else {
            $(".msg").text(`Existe un error en la información`);
            showAlert();
          }
        })
        .catch((error) => {
          $(".msg").text(`Existe un error en la información`);
          showAlert();
          console.log("error:", error);
        });
    }
  });
});
