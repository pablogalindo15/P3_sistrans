create sequence proyecto_sequence;

CREATE TABLE clientes (
    id NUMBER PRIMARY KEY,
    num_doc NUMBER,
    tipo_doc VARCHAR(255),
    nombre VARCHAR(255),
    dir VARCHAR(255),
    mail VARCHAR(255),
    tel NUMBER,
    ciudad VARCHAR(255),
    clave VARCHAR(255),
    login VARCHAR(255),
    nacionalidad VARCHAR(255),
    dept VARCHAR(255),
    codigo_post NUMBER,
    tipo VARCHAR(255),
    estado VARCHAR(255)
);

CREATE TABLE cuentas (
    id NUMBER PRIMARY KEY,
    tipo VARCHAR(255),
    numero NUMBER,
    saldo NUMBER,
    estado VARCHAR(255),
    id_cliente NUMBER,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);
CREATE TABLE obcs (
    id NUMBER PRIMARY KEY,
    fecha DATE,
    valor NUMBER,
    tipo VARCHAR(255),
    id_cuenta NUMBER,
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id)
);
CREATE TABLE transferencias (
    fecha DATE,
    valor NUMBER,
    tipo VARCHAR(255),
    id_cuenta_1 NUMBER,
    id_cuenta_2 NUMBER,
    PRIMARY KEY (fecha, valor, tipo, id_cuenta_1, id_cuenta_2),
    FOREIGN KEY (id_cuenta_1) REFERENCES cuentas(id),
    FOREIGN KEY (id_cuenta_2) REFERENCES cuentas(id)
);

CREATE TABLE prestamos (
    id NUMBER PRIMARY KEY,
    monto NUMBER,
    interes NUMBER,
    cuotas NUMBER,
    dia_pago DATE,
    estado VARCHAR(255),
    tipo VARCHAR(255),
    id_cliente NUMBER,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE obps (
    id NUMBER PRIMARY KEY,
    fecha DATE,
    valor NUMBER,
    tipo VARCHAR(255),
    id_prestamo NUMBER,
    FOREIGN KEY (id_prestamo) REFERENCES prestamos(id)
);

CREATE TABLE oficinas (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR(255),
    dir VARCHAR(255),
    num_pa VARCHAR(255)
);

CREATE TABLE empleados (
    id NUMBER PRIMARY KEY,
    num_doc NUMBER,
    tipo_doc VARCHAR(255),
    nombre VARCHAR(255),
    dir VARCHAR(255),
    mail VARCHAR(255),
    tel NUMBER,
    ciudad VARCHAR(255),
    clave VARCHAR(255),
    login VARCHAR(255),
    nacionalidad VARCHAR(255),
    dept VARCHAR(255),
    codigo_post NUMBER,
    tipo VARCHAR(255),
    estado VARCHAR(255),
    cargo VARCHAR(255),
    id_oficina NUMBER,
    FOREIGN KEY (id_oficina) REFERENCES oficinas(id)
);
CREATE TABLE pas (
    id NUMBER PRIMARY KEY,
    tipo VARCHAR(255),
    id_oficina NUMBER,
    FOREIGN KEY (id_oficina) REFERENCES oficinas(id)
);





