-- Creacion de tablas:

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS informacionpagos
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombretitular STRING(20) NOT NULL,
    pin SMALLINT NOT NULL,
    tipotarjeta STRING(20),
    tipodepago STRING(20) NOT NULL,
    CONSTRAINT ok_card_type CHECK (tipotarjeta IN ('Debito', 'Credito')),
    CONSTRAINT ok_payment_type CHECK (tipodepago IN ('Efectivo', 'Tarjeta', 'PonitPuntos')),
    UNIQUE (nombretitular, pin)
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ubicaciones
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    edificio STRING(320) NOT NULL,
    numero SMALLINT,
    descripcion STRING(320),
    UNIQUE (edificio)
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS estadospedidos
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    estadopago STRING(20) NOT NULL,
    estadoenvio STRING(20) NOT NULL,
    estadopedido STRING(20) NOT NULL,
    UNIQUE (estadopago, estadoenvio, estadopedido),
    CONSTRAINT ok_pay_state CHECK (estadopago IN ('Pagado', 'No pagado')),
    CONSTRAINT ok_send_state CHECK (estadoenvio IN ('Enviado', 'No enviado')),
    CONSTRAINT ok_order_state CHECK (estadopedido IN ('Aceptado', 'No aceptado'))
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS fotos
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tipofoto STRING(20) NOT NULL,
    nombre STRING(50) NOT NULL,
    descripcion STRING(320),
    foto STRING(1200) NOT NULL,
    UNIQUE (nombre),
    CONSTRAINT ok_photo_type CHECK (tipofoto IN ('Producto', 'Tienda', 'Perfil', 'FrontEnd'))
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ingredientes
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombreingrediente STRING(20) NOT NULL,
    UNIQUE (nombreingrediente)
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS usuarios
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    idjaveriana STRING(20) NOT NULL,
    nombreusuario STRING(320) NOT NULL,
    fotoid UUID NOT NULL,
    tipousuario STRING(20) NOT NULL,
    contrasena STRING(320) NOT NULL,
    correoinstitucional STRING(320) NOT NULL,
    nombre STRING(20) NOT NULL,
    apellido STRING(20) NOT NULL,
    edad SMALLINT NOT NULL,
    telefono STRING(20) NOT NULL,
    puntos INT DEFAULT 0,
    calificacion DECIMAL(10,4),
    estadosesion STRING(20) NOT NULL,
    disponibilidad STRING(20) NOT NULL,
    CONSTRAINT ok_user_type CHECK (tipousuario IN ('Comprador', 'Repartidor', 'Personal de tienda')),
    CONSTRAINT ok_session_state CHECK (estadosesion IN ('Conectada', 'Desconectada')),
    CONSTRAINT ok_availability CHECK (disponibilidad IN ('Disponible', 'No disponible')),
    CONSTRAINT fk_user_photo FOREIGN KEY (fotoid) REFERENCES fotos (id) ON DELETE CASCADE,
    UNIQUE (idjaveriana, nombreusuario)
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS tiendas
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombretienda STRING(20) NOT NULL,
    ubicacionid UUID NOT NULL,
    fotoid UUID NOT NULL,
    estadotienda STRING(20) NOT NULL,
    CONSTRAINT ok_pay_state CHECK (estadotienda IN ('Abierta', 'Cerrada')),
    CONSTRAINT fk_store_address FOREIGN KEY (ubicacionid) REFERENCES ubicaciones (id) ON DELETE CASCADE,
    CONSTRAINT fk_store_photo FOREIGN KEY (fotoid) REFERENCES fotos (id) ON DELETE CASCADE
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS tiendaspersonaldetienda
(
    personaltiendaid UUID NOT NULL,
    tiendasid UUID NOT NULL,
    CONSTRAINT fk_staff FOREIGN KEY (personaltiendaid) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_store FOREIGN KEY (tiendasid) REFERENCES tiendas (id) ON DELETE CASCADE
    )
;



--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS productos
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombreproducto STRING(20) NOT NULL,
    fotoid UUID NOT NULL,
    preciodinero DECIMAL(10,4) NOT NULL,
    preciopuntos DECIMAL(10,4) NOT NULL,
    descripcion STRING(320),
    promocion DECIMAL(10,4) DEFAULT 0,
    disponibleconpuntos STRING(40) NOT NULL,
    CONSTRAINT fk_product_photo FOREIGN KEY (fotoid) REFERENCES fotos (id) ON DELETE CASCADE,
    CONSTRAINT ok_disponibleconpuntos CHECK (disponibleconpuntos IN ('Disponible con puntos', 'No disponible con puntos')),
    UNIQUE (nombreproducto)
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS combos
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre STRING(20) NOT NULL,
    precio DECIMAL(10, 4) NOT NULL,
    UNIQUE (nombre)
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS productoscombos
(
    productoid UUID NOT NULL,
    comboid UUID NOT NULL,
    cantidadproductos INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_comboproducts_product  FOREIGN KEY (productoid) REFERENCES productos (id) ON DELETE CASCADE,
    CONSTRAINT fk_comboproducts_combo  FOREIGN KEY (comboid) REFERENCES combos (id) ON DELETE CASCADE
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ingredientesproductos
(
    ingredienteid UUID NOT NULL,
    productoid UUID NOT NULL,
    cantidad INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_ingredientsproduct_ingredient  FOREIGN KEY (ingredienteid) REFERENCES ingredientes (id) ON DELETE CASCADE,
    CONSTRAINT fk_ingredientsproduct_producs  FOREIGN KEY (productoid) REFERENCES productos (id) ON DELETE CASCADE
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS tiendasproductos
(
    tiendaid UUID NOT NULL,
    productoid UUID NOT NULL,
    cantidaddisponible INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_storeproducts_store  FOREIGN KEY (tiendaid) REFERENCES tiendas (id) ON DELETE CASCADE,
    CONSTRAINT fk_storeproducts_product FOREIGN KEY (productoid) REFERENCES productos (id) ON DELETE CASCADE
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS pedidos
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    numeropedido INT NOT NULL,
    compradorid UUID NOT NULL,
    repartidorid UUID NOT NULL,
    tiendaid UUID NOT NULL,
    estadopedidoid UUID NOT NULL,
    ubicacionid UUID NOT NULL,
    tipopedido STRING(20) NOT NULL,
    valortotal DECIMAL(10,4) NOT NULL,
    CONSTRAINT ok_order_state CHECK (tipopedido IN ('Domicilio', 'Reservar')),
    CONSTRAINT fk_order_shopper  FOREIGN KEY (compradorid) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_order_dealer FOREIGN KEY (repartidorid) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_order_state FOREIGN KEY (estadopedidoid) REFERENCES estadospedidos (id) ON DELETE CASCADE,
    CONSTRAINT fk_order_locations FOREIGN KEY (ubicacionid) REFERENCES ubicaciones (id) ON DELETE CASCADE,
    CONSTRAINT fk_order_shops FOREIGN KEY (tiendaid) REFERENCES tiendas (id) ON DELETE CASCADE,
    UNIQUE (numeropedido)
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS productospedidos
(
    pedidoid UUID NOT NULL,
    productoid UUID NOT NULL,
    CONSTRAINT fk_productsorders_order  FOREIGN KEY (pedidoid) REFERENCES pedidos (id) ON DELETE CASCADE,
    CONSTRAINT fk_productsorders_product  FOREIGN KEY (productoid) REFERENCES productos (id) ON DELETE CASCADE
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS combospedidos
(
    pedidoid UUID NOT NULL,
    comboid UUID NOT NULL,
    CONSTRAINT fk_comboorders_order  FOREIGN KEY (pedidoid) REFERENCES pedidos (id) ON DELETE CASCADE,
    CONSTRAINT fk_comboorders_combo  FOREIGN KEY (comboid) REFERENCES combos (id) ON DELETE CASCADE
    )
;

--------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS pedidosinformacionpagos
(
    pedidoid UUID NOT NULL,
    informacionpagosid UUID NOT NULL,
    tipodepago STRING(20) NOT NULL,
    aclaraciones STRING(320),
    propina DECIMAL(10,4),
    CONSTRAINT fk_comboorders_order  FOREIGN KEY (pedidoid) REFERENCES pedidos (id) ON DELETE CASCADE,
    CONSTRAINT fk_comboorders_combo  FOREIGN KEY (informacionpagosid) REFERENCES informacionpagos (id) ON DELETE CASCADE
    )
;