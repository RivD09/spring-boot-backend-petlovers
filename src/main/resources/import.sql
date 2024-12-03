INSERT INTO rol (nombre_rol,descripcion) VALUES ('ROLE_ADMIN','Todos los permisos');
INSERT INTO rol (nombre_rol,descripcion) VALUES ('ROLE_CLIENTE','Permisos limitados');

INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Consulta General', 'Consulta médica general para evaluar el estado de salud de la mascota.', 50.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Vacunación', 'Administración de vacunas para la prevención de enfermedades comunes.', 30.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Desparasitación', 'Tratamiento para eliminar parásitos internos y externos.', 25.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Cirugía', 'Procedimientos quirúrgicos diversos para tratar enfermedades o lesiones.', 200.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Limpieza Dental', 'Servicio de limpieza dental para prevenir problemas bucales en las mascotas.', 75.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Radiografía', 'Realización de radiografías para el diagnóstico de problemas internos.', 100.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Ultrasonido', 'Ecografías para evaluar órganos internos y detectar posibles problemas.', 120.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Análisis de Sangre', 'Pruebas de laboratorio para evaluar la salud general de la mascota.', 60.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Hospitalización', 'Atención y cuidado de la mascota durante su estancia en el hospital veterinario.', 150.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Peluquería', 'Servicios de peluquería y estética para mantener a las mascotas limpias y bien cuidadas.', 40.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Emergencia 24/7', 'Atención médica de emergencia disponible las 24 horas del día, los 7 días de la semana.', 80.00);
INSERT servicio (nombre_servicio, descripcion_servicio, costo_servicio) VALUES ('Terapia Física', 'Sesiones de fisioterapia para ayudar a la recuperación de lesiones y mejorar la movilidad.', 90.00);


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
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (4, 8, 2, 'perro', 'labrador', 'Max');
INSERT mascota (edad, peso, id_cliente, especie, raza, nombre_mascota) VALUES (1, 2, 2, 'perro', 'chihuahua', 'Bella');
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

INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-02', 'Pagado', 'Chequeo general', 'Chequeo anual', 1, 1, 1);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-03', 'No Pagado', 'Aplicación de vacuna', 'Vacunación antirrábica', 2, 1, 2);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-04', 'Pagado', 'Desparasitación completa', 'Control de parásitos', 1, 1, 3);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-05', 'No Pagado', 'Limpieza dental completa', 'Higiene bucal', 2, 1, 5);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-06', 'Pagado', 'Cirugía menor', 'Extracción de quiste', 1, 1, 4);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-07', 'No Pagado', 'Radiografía de tórax', 'Chequeo respiratorio', 3, 2, 6);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-08', 'Pagado', 'Consulta por dolor', 'Evaluación de dolor', 3, 2, 7);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-09', 'No Pagado', 'Ultrasonido abdominal', 'Problemas digestivos', 4, 2, 8);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-10', 'Pagado', 'Hospitalización por un día', 'Observación', 4, 2, 9);
INSERT cita (fecha_cita, estado, detalles_visita, motivo, id_mascota, id_cliente, id_servicio) VALUES ('2024-12-11', 'No Pagado', 'Terapia física', 'Recuperación post-cirugía', 5, 2, 10);
