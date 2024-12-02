INSERT INTO rol (nombre_rol,descripcion) VALUES ('ROLE_ADMIN','Todos los permisos');
INSERT INTO rol (nombre_rol,descripcion) VALUES ('ROLE_CLIENTE','Permisos limitados');

INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('CarlosG', 'carlos.gomez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555123456', 1);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('MaríaF', 'maria.fernandez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555234567', 2);

INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('JuanP', 'juan.perez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555345678', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('LauraH', 'laura.herrera@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555456789', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('MiguelC', 'miguel.castro@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555567890', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('AnaM', 'ana.lopez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555678901', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('LuisM', 'luis.martinez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555789012', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('CarmenR', 'carmen.ramirez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555890123', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('JorgeV', 'jorge.velasco@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555901234', 2);



INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (2, 'Los alamos 231', 'Dueño de gatos');
INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (1, 'Los laureles 310', 'Dueño de hurones');

INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (3, 'Calle Las Flores 450', 'Dueño de perros');
INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (4, 'Avenida Las Palmas 120', 'Dueño de aves exóticas');
INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (5, 'Pasaje Los Pinos 78', 'Dueño de reptiles');
INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (6, 'Boulevard Central 555', 'Dueño de roedores');
INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (7, 'Urbanización Los Jazmines 102', 'Dueño de peces tropicales');
INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (8, 'Callejón Los Olivos 321', 'Dueño de perros y gatos');
INSERT INTO cliente (id_usuario, direccion, informacion_adicional) VALUES (9, 'Avenida Los Tulipanes 202', 'Dueño de caballos');


INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (3, 10, 1, 'gato', 'naranja', 'Whiskas');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (2, 5, 1, 'gato', 'naranja', 'Ron');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (7, 3, 2, 'huron', 'blanco', 'Draco');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (4, 8, 3, 'perro', 'labrador', 'Max');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (1, 2, 3, 'perro', 'chihuahua', 'Bella');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (5, 15, 4, 'gato', 'siamés', 'Simba');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (6, 20, 4, 'gato', 'persa', 'Milo');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (3, 12, 5, 'conejo', 'angora', 'Nube');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (2, 6, 5, 'conejo', 'enano', 'Luna');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (1, 5, 6, 'hamster', 'ruso', 'Rayo');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (2, 7, 6, 'hamster', 'sirio', 'Bolt');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (4, 10, 2, 'tortuga', 'orejas rojas', 'Turbo');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (3, 9, 1, 'loro', 'amazona', 'Pepe');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (5, 3, 3, 'pez', 'betta', 'Nemo');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (1, 0.2, 6, 'pez', 'goldfish', 'Dory');
