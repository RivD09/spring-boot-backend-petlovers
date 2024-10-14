INSERT INTO rol (nombre_rol,descripcion) VALUES ('ROLE_ADMIN','Todos los permisos');
INSERT INTO rol (nombre_rol,descripcion) VALUES ('ROLE_CLIENTE','Permisos limitados');

INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('CarlosG', 'carlos.gomez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555123456', 1);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('MaríaF', 'maria.fernandez@mail.com', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', '555234567', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('JuanP', 'juan.perez@mail.com', 'password123', '555345678', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('LucíaG', 'lucia.garcia@mail.com', 'password123', '555456789', 2);
INSERT INTO usuario (nombre, email, password, telefono, id_rol) VALUES ('MiguelR', 'miguel.ruiz@mail.com', 'password123', '555567890', 2);