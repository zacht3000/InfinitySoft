-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: InfintySoft
-- ------------------------------------------------------
-- Server version	8.0.11
--
--
--Borrado de todas las tablas

DROP TABLE OPINIONES CASCADE CONSTRAINT;
DROP TABLE FORMULARIO CASCADE CONSTRAINT;
DROP TABLE PRODUCTO CASCADE CONSTRAINT;
DROP TABLE USUARIO CASCADE CONSTRAINT;

CREATE TABLE PRODUCTO (COD NUMBER(4) PRIMARY KEY,PRECIO NUMBER(6,2),NOMBRE VARCHAR2(20),PATH_RUTA VARCHAR2(50));
CREATE TABLE USUARIO (NICKNAME VARCHAR2(20) PRIMARY KEY,CORREO VARCHAR2(100) UNIQUE,NOMBRE VARCHAR2(20),APELLIDOS VARCHAR2(80),CONTRASENYA VARCHAR2(40),TIPO VARCHAR2(20),CONSTRAINT RULE_TIPO CHECK (TIPO LIKE 'ADMINISTRADOR' OR TIPO LIKE 'NORMAL'));
CREATE TABLE OPINIONES (ID NUMBER(4) PRIMARY KEY,DESCRIPCION VARCHAR2(250),PUNTUACION NUMBER(6,2),COD_PRODUCTO NUMBER(4),NICKNAME VARCHAR2(20),CONSTRAINT FK_OPINIONES_PROD FOREIGN KEY (COD_PRODUCTO)REFERENCES PRODUCTO, CONSTRAINT FK_OPINIONES_USER FOREIGN KEY (NICKNAME)REFERENCES USUARIO ON DELETE CASCADE);
CREATE TABLE FORMULARIO (ID NUMBER(4) PRIMARY KEY, NICKNAME VARCHAR2(20), NOMBRE VARCHAR2(20), APELLIDOS VARCHAR2(50), CORREO VARCHAR2(50),AREA VARCHAR2(80),PREGUNTA VARCHAR2(80),CONSTRAINT FK_FORMULARIO_USER FOREIGN KEY (NICKNAME) REFERENCES USUARIO);

INSERT INTO USUARIO VALUES('anonimo', 'anonymous@iespacomolla.es', null, null, '1234', 'NORMAL');
INSERT INTO USUARIO VALUES('admin', 'admin@iespacomolla.es', null, null, 'admin', 'ADMINISTRADOR');
INSERT INTO PRODUCTO VALUES(1, 50, 'Plantilla Basica', '/media/img/jpg/1.jpg');
INSERT INTO PRODUCTO VALUES(2, 100, 'Plantilla Premiun', '/media/img/jpg/2.jpg');
INSERT INTO PRODUCTO VALUES(3, 150, 'Plantilla Plus', '/media/img/jpg/3.jpg');
CREATE OR REPLACE TRIGGER UPD_USER_NAME AFTER UPDATE ON USUARIO FOR EACH ROW BEGIN UPDATE OPINIONES SET NICKNAME =:new.NICKNAME WHERE NICKNAME =:old.NICKNAME; UPDATE FORMULARIO SET NICKNAME =:new.NICKNAME WHERE NICKNAME =:old.NICKNAME;DBMS_OUTPUT.PUT_LINE('Se ha actualizado el nombre');end;















