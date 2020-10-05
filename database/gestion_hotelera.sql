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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `habitaciones` */

insert  into `habitaciones`(`id`,`numero`,`tipo`,`estado`,`detalles`,`precioDia`) values (1,1,'Individual','Disponible','1 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(2,2,'Individual','Disponible','1 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(3,3,'Individual','Disponible','1 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(4,4,'Doble','Disponible','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(5,5,'Doble','Disponible','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(6,6,'Doble','Disponible','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',1500),(7,7,'Doble','Disponible','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nCon balcón',2000),(8,8,'Matrimonial','Limpieza','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',2300),(9,9,'Matrimonial','Disponible','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nSin balcón',2300),(10,10,'Matrimonial','Limpieza','2 Cama\n1 TV\nBaño\nAire acondicionado\nTodos los servicios\nCon balcón',2500),(11,11,'Doble','Limpieza','2 camas\n1 TV\nSin aire acondicionado\nSin balcón\nTodos los servicios',1300),(12,12,'Matrimonial','Reparación','2 Camas\n1 TV\nAire acondicionado\nSin balcón\nTodos los servicios',2300),(13,13,'Doble','Reparación','2 Camas\n1 TV\nAire acondicionado\nCon balcón\nTodos los servicios\n',1860),(34,14,'Individual','Limpieza','1 cama\n1 aire acondicionado\ntodos los servicios\ncon balcón',2300);

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
