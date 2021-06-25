-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Facturacion
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Facturacion
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Facturacion` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
-- -----------------------------------------------------
-- Schema facturacion
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema facturacion
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `facturacion` DEFAULT CHARACTER SET utf8 ;
USE `Facturacion` ;

-- -----------------------------------------------------
-- Table `Facturacion`.`ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`ciudad` (
  `id_ciudad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_ciudad`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Facturacion`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `celular` DOUBLE NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `id_ciudad` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Facturacion`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Facturacion`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`articulo` (
  `id_articulo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `cant_stock` DOUBLE NOT NULL,
  `descripccion` VARCHAR(100) NULL,
  `id_categoria` INT NOT NULL,
  PRIMARY KEY (`id_articulo`, `id_categoria`),
  INDEX `fk_articulo_categoria1_idx` (`id_categoria` ASC),
  CONSTRAINT `fk_articulo_categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `Facturacion`.`categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Facturacion`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`empleado` (
  `id_empleado` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `id_ciudad` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC),
  INDEX `fk_empleado_ciudad1_idx` (`id_ciudad` ASC),
  CONSTRAINT `fk_empleado_ciudad1`
    FOREIGN KEY (`id_ciudad`)
    REFERENCES `Facturacion`.`ciudad` (`id_ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Facturacion`.`metodo_pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`metodo_pago` (
  `id_metodo_pago` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_metodo_pago`),
  UNIQUE INDEX `tipo_UNIQUE` (`tipo` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Facturacion`.`factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`factura` (
  `id_factura` INT NOT NULL AUTO_INCREMENT,
  `fecha_fac` DATETIME NOT NULL,
  `val_iva` DOUBLE NOT NULL,
  `val_sub_total` DOUBLE NOT NULL,
  `total` DOUBLE NOT NULL,
  `id_cliente` INT NOT NULL,
  `id_metodo_pago` INT NOT NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Facturacion`.`detalle_fac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Facturacion`.`detalle_fac` (
  `id_detalle_fac` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `total` DOUBLE NOT NULL,
  `descuento` FLOAT NOT NULL,
  `val_decuento` DOUBLE NOT NULL,
  `id_factura` INT NOT NULL,
  `id_articulo` INT NOT NULL,
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
ENGINE = InnoDB;

USE `facturacion` ;

-- -----------------------------------------------------
-- Table `facturacion`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`categoria` (
  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`articulo` (
  `id_articulo` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `cant_stock` DOUBLE NOT NULL,
  `descripccion` VARCHAR(100) NULL DEFAULT NULL,
  `id_categoria` INT(11) NOT NULL,
  PRIMARY KEY (`id_articulo`, `id_categoria`),
  INDEX `fk_articulo_categoria1_idx` (`id_categoria` ASC),
  CONSTRAINT `fk_articulo_categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `facturacion`.`categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`.`ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`ciudad` (
  `id_ciudad` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_ciudad`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`cliente` (
  `id_cliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `celular` DOUBLE NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `id_ciudad` INT(11) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC),
  UNIQUE INDEX `celular_UNIQUE` (`celular` ASC),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC),
  INDEX `fk_cliente_ciudad1_idx` (`id_ciudad` ASC),
  CONSTRAINT `fk_cliente_ciudad1`
    FOREIGN KEY (`id_ciudad`)
    REFERENCES `facturacion`.`ciudad` (`id_ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`.`metodo_pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`metodo_pago` (
  `id_metodo_pago` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_metodo_pago`),
  UNIQUE INDEX `tipo_UNIQUE` (`tipo` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`.`factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`factura` (
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
    REFERENCES `facturacion`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_metodo_pago1`
    FOREIGN KEY (`id_metodo_pago`)
    REFERENCES `facturacion`.`metodo_pago` (`id_metodo_pago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`.`detalle_fac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`detalle_fac` (
  `id_detalle_fac` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad` INT(11) NOT NULL,
  `total` DOUBLE NOT NULL,
  `descuento` FLOAT NOT NULL,
  `val_decuento` DOUBLE NOT NULL,
  `id_factura` INT(11) NOT NULL,
  `id_articulo` INT(11) NOT NULL,
  PRIMARY KEY (`id_detalle_fac`, `id_articulo`),
  INDEX `fk_detalle_fac_factura1_idx` (`id_factura` ASC),
  INDEX `fk_detalle_fac_articulo1_idx` (`id_articulo` ASC),
  CONSTRAINT `fk_detalle_fac_articulo1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `facturacion`.`articulo` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_fac_factura1`
    FOREIGN KEY (`id_factura`)
    REFERENCES `facturacion`.`factura` (`id_factura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`empleado` (
  `id_empleado` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `id_ciudad` INT(11) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC),
  INDEX `fk_empleado_ciudad1_idx` (`id_ciudad` ASC),
  CONSTRAINT `fk_empleado_ciudad1`
    FOREIGN KEY (`id_ciudad`)
    REFERENCES `facturacion`.`ciudad` (`id_ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
