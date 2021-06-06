-- MySQL Workbench Synchronization
-- Generated: 2021-06-06 12:00
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Camilo Garcia

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Facturacion` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `Facturacion`.`cliente` (
  `id_cliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `celular` DOUBLE NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `id_ciudad` INT(11) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC),
  UNIQUE INDEX `celular_UNIQUE` (`celular` ASC),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC),
  INDEX `fk_cliente_ciudad1_idx` (`id_ciudad` ASC),
  CONSTRAINT `fk_cliente_ciudad1`
    FOREIGN KEY (`id_ciudad`)
    REFERENCES `Facturacion`.`ciudad` (`id_ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `Facturacion`.`articulo` (
  `id_articulo` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` DOUBLE NOT NULL,
  `cant_stock` DOUBLE NOT NULL,
  `descripccion` VARCHAR(100) NULL DEFAULT NULL,
  `id_categoria` INT(11) NOT NULL,
  PRIMARY KEY (`id_articulo`),
  INDEX `fk_articulo_categoria1_idx` (`id_categoria` ASC),
  CONSTRAINT `fk_articulo_categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `Facturacion`.`categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `Facturacion`.`categoria` (
  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `Facturacion`.`empleado` (
  `id_empleado` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `id_ciudad` INT(11) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC),
  INDEX `fk_empleado_ciudad1_idx` (`id_ciudad` ASC),
  CONSTRAINT `fk_empleado_ciudad1`
    FOREIGN KEY (`id_ciudad`)
    REFERENCES `Facturacion`.`ciudad` (`id_ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `Facturacion`.`metodo_pago` (
  `id_metodo_pago` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_metodo_pago`),
  UNIQUE INDEX `tipo_UNIQUE` (`tipo` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `Facturacion`.`factura` (
  `id_factura` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_fac` DATETIME NOT NULL,
  `val_iva` DOUBLE NOT NULL,
  `val_sub_total` DOUBLE NOT NULL,
  `total` DOUBLE NOT NULL,
  `id_cliente` INT(11) NOT NULL,
  `id_metodo_pago` INT(11) NOT NULL,
  PRIMARY KEY (`id_factura`),
  INDEX `fk_factura_cliente1_idx` (`id_cliente` ASC),
  INDEX `fk_factura_metodo_pago1_idx` (`id_metodo_pago` ASC),
  CONSTRAINT `fk_factura_cliente1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `Facturacion`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_metodo_pago1`
    FOREIGN KEY (`id_metodo_pago`)
    REFERENCES `Facturacion`.`metodo_pago` (`id_metodo_pago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `Facturacion`.`detalle_fac` (
  `id_detalle_fac` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad` INT(11) NOT NULL,
  `total` DOUBLE NOT NULL,
  `descuento` FLOAT(11) NOT NULL,
  `val_decuento` DOUBLE NOT NULL,
  `id_factura` INT(11) NOT NULL,
  `id_articulo` INT(11) NOT NULL,
  PRIMARY KEY (`id_detalle_fac`, `id_articulo`),
  INDEX `fk_detalle_fac_factura1_idx` (`id_factura` ASC),
  INDEX `fk_detalle_fac_articulo1_idx` (`id_articulo` ASC),
  CONSTRAINT `fk_detalle_fac_factura1`
    FOREIGN KEY (`id_factura`)
    REFERENCES `Facturacion`.`factura` (`id_factura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_fac_articulo1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `Facturacion`.`articulo` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `Facturacion`.`ciudad` (
  `id_ciudad` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_ciudad`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
