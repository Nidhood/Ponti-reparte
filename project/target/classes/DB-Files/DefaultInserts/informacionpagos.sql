-- Efectivo:
INSERT INTO informacionpagos (nombretitular, pin, tipodepago)
VALUES ('Anonimo', 1234, 'Efectivo');

-- Tarjeta & debito:
INSERT INTO informacionpagos (nombretitular, pin, tipotarjeta, tipodepago)
VALUES ('Anonimo', 5678, 'Debito', 'Tarjeta');

-- Tarjeta & credito:
INSERT INTO informacionpagos (nombretitular, pin, tipotarjeta, tipodepago)
VALUES ('Anonimo', 9876, 'Credito', 'Tarjeta');

-- PontiPuntos:
INSERT INTO informacionpagos (nombretitular, pin, tipodepago)
VALUES ('Anonimo', 8976, 'PontiPuntos');