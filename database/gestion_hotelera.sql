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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

/*Data for the table `clientes` */

insert  into `clientes`(`id`,`nombres`,`apellidos`,`dni`,`nacionalidad`,`correo`,`nroTelefono`,`fechaNacimiento`) values (18,'Renzo','Cenation',41691353,'Perú','renzo@hotmail.com',123,'10-10-2020'),(26,'Agustin','Rivas',43691325,'Argentina','agustinRivas@gmail.com',152616216713,'16-10-1998'),(29,'Enzo','Ferreyra',1234567,'Peru','enzo@hotmail.com',12345678,'17-10-1998'),(30,'hola','asd',123,'asd','asd',123,'09-10-2020');

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;

/*Data for the table `habitaciones` */

insert  into `habitaciones`(`id`,`numero`,`tipo`,`estado`,`detalles`,`precioDia`) values (1,1,'Individual','Ocupada','1 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(2,2,'Individual','Ocupada','1 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(3,3,'Individual','Ocupada','1 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(4,4,'Doble','Ocupada','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(5,5,'Doble','Ocupada','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(6,6,'Doble','Ocupada','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(7,7,'Doble','Ocupada','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nCon balcón',2000),(8,8,'Matrimonial','Ocupada','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',2300),(9,9,'Matrimonial','Ocupada','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',2300),(10,10,'Matrimonial','Ocupada','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nCon balcón',2500),(11,11,'Doble','Ocupada','2 camas\n1 TV\nSin aire acondicionado\nSin balcón\nTodos los servicios',1300),(12,12,'Matrimonial','Reparación','2 Camas\n1 TV\nAire acondicionado\nSin balcón\nTodos los servicios',2300),(13,13,'Doble','Reparación','2 Camas\n1 TV\nAire acondicionado\nCon balcón\nTodos los servicios\n',1860),(34,14,'Individual','Ocupada','1 cama\n1 aire acondicionado\ntodos los servicios\ncon balcón',2300),(36,15,'Individual','Ocupada','1 cama',1300),(37,16,'Individual','Limpieza','abc',2300),(42,19,'Matrimonial','Ocupada','2 camas',3500),(44,18,'Individual','Disponible','1 cama',1500),(45,20,'Individual','Limpieza','asd',1500);

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservas` */

insert  into `reservas`(`id`,`fechaEntrada`,`fechaSalida`,`horaEntrada`,`horaSalida`,`fk_cliente`,`fk_habitacion`,`tipoPago`,`estado`,`precioTotal`) values (22,'14-10-2020','17-11-2020','01:00:00','22:00:00',18,2,'Tarjeta de crédito','Pendiente',51000),(24,'20-10-2020','24-10-2020','01:30:00','09:30:00',18,3,'Efectivo','Pendiente',6000),(27,'08-10-2020','24-10-2020','01:00:00','01:30:00',26,9,'Efectivo','Pendiente',36800),(28,'14-10-2020','24-10-2020','02:00:00','07:30:00',26,36,'Efectivo','Pendiente',13000),(30,'14-10-2020','24-10-2020','01:00:00','01:30:00',26,8,'Efectivo','Pendiente',23000),(31,'07-10-2020','17-10-2020','00:30:00','02:00:00',29,1,'Efectivo','Pendiente',15000),(32,'15-10-2020','25-10-2020','01:00:00','04:00:00',18,5,'Efectivo','Pendiente',15000),(33,'07-10-2020','15-10-2020','01:00:00','10:00:00',29,6,'Efectivo','Pendiente',12000);

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

/*Data for the table `usuarios` */

insert  into `usuarios`(`id`,`nombre`,`apellido`,`email`,`usuario`,`contraseña`,`rol`,`activo`) values (8,'Tomás','Ortiz','admin@hotel.com','admin','dG9taXMyMTIz','admin',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
