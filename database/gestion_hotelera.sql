/*
SQLyog Ultimate v10.42 
MySQL - 5.5.5-10.4.8-MariaDB : Database - gestion_hotelera
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gestion_hotelera` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gestion_hotelera`;

/*Table structure for table `clientes` */

DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(70) DEFAULT NULL,
  `apellidos` varchar(70) DEFAULT NULL,
  `dni` bigint(20) DEFAULT NULL,
  `nacionalidad` varchar(70) DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `nroTelefono` bigint(20) DEFAULT NULL,
  `fechaNacimiento` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;


/*Table structure for table `habitaciones` */

DROP TABLE IF EXISTS `habitaciones`;

CREATE TABLE `habitaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `tipo` enum('Individual','Doble','Matrimonial') NOT NULL,
  `estado` enum('Disponible','Ocupada','Limpieza','Reparación') NOT NULL,
  `detalles` varchar(500) DEFAULT NULL,
  `precioDia` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;


/*Table structure for table `productos` */

DROP TABLE IF EXISTS `productos`;

CREATE TABLE `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `marca` varchar(200) DEFAULT NULL,
  `categoria` enum('Alimento','Bebida') DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `proveedor` varchar(200) DEFAULT NULL,
  `precioCompra` float DEFAULT NULL,
  `precioVenta` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `reservas` */

DROP TABLE IF EXISTS `reservas`;

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaEntrada` varchar(50) NOT NULL,
  `fechaSalida` varchar(50) NOT NULL,
  `horaEntrada` time DEFAULT NULL,
  `horaSalida` time DEFAULT NULL,
  `fk_cliente` int(11) NOT NULL,
  `fk_habitacion` int(11) NOT NULL,
  `tipoPago` varchar(50) DEFAULT NULL,
  `estado` enum('Cobrado','Pendiente') DEFAULT NULL,
  `precioTotal` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;


/*Table structure for table `reservas_productos` */

DROP TABLE IF EXISTS `reservas_productos`;

CREATE TABLE `reservas_productos` (
  `reserva_id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  `cantProducto` int(11) DEFAULT NULL,
  `precioTotal` float DEFAULT NULL,
  PRIMARY KEY (`producto_id`,`reserva_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `email` varchar(60) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `contraseña` varchar(25) NOT NULL,
  `rol` varchar(50) NOT NULL DEFAULT 'user',
  `activo` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unico_email` (`email`),
  UNIQUE KEY `unico_usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
