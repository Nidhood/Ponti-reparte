-- Insertar combinaciones para estadospedidos
INSERT INTO estadospedidos (estadopago, estadoenvio, estadopedido)
VALUES ('Pagado', 'Enviado', 'Aceptado'),
       ('Pagado', 'Enviado', 'No aceptado'),
       ('Pagado', 'No enviado', 'Aceptado'),
       ('Pagado', 'No enviado', 'No aceptado'),
       ('No pagado', 'Enviado', 'Aceptado'),
       ('No pagado', 'Enviado', 'No aceptado'),
       ('No pagado', 'No enviado', 'Aceptado'),
       ('No pagado', 'No enviado', 'No aceptado');