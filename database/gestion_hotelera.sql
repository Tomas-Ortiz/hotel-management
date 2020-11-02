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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

/*Data for the table `clientes` */

insert  into `clientes`(`id`,`nombres`,`apellidos`,`dni`,`nacionalidad`,`correo`,`nroTelefono`,`fechaNacimiento`) values (33,'Tomás','Ortiz',41967921,'Argentina','tomasOrtiz@gmail.com',2613985235,'04-08-1999'),(34,'Mariano','Gimenez',42342334,'España','marianPerez@gmail.com',2614223340,'15-10-1995'),(35,'Agustin','Fernandez',41345678,'Uruguay','Aguss_97@gmail.com',261348999,'19-09-1997');

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

/*Data for the table `habitaciones` */

insert  into `habitaciones`(`id`,`numero`,`tipo`,`estado`,`detalles`,`precioDia`) values (1,1,'Individual','Disponible','1 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',1350),(2,2,'Individual','Disponible','1 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',1800),(3,3,'Individual','Disponible','1 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',1900),(4,4,'Doble','Ocupada','2 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',1500),(5,5,'Doble','Disponible','2 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',1500),(6,6,'Doble','Ocupada','2 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',1700),(7,7,'Doble','Disponible','2 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nCon balcón.',2000),(8,8,'Matrimonial','Disponible','2 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',2300),(9,9,'Matrimonial','Limpieza','2 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nSin balcón.',2300),(10,10,'Matrimonial','Limpieza','2 Cama, \n1 TV, \nBaño, \nAire acondicionado, \nTodos los servicios, \nCon balcón.',2500),(12,12,'Matrimonial','Reparación','2 Camas, \n1 TV, \nAire acondicionado,  \nSin balcón, \nTodos los servicios.',2300),(13,13,'Doble','Reparación','2 Camas, \n1 TV, \nAire acondicionado, \nCon balcón, \nTodos los servicios.\n',1860),(48,20,'Doble','Disponible','1 cama, 1 TV, 1 balcón, todos los servicios.',2500);

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`,`marca`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `productos` */

insert  into `productos`(`id`,`nombre`,`marca`,`categoria`,`stock`,`proveedor`,`precioCompra`,`precioVenta`) values (1,'Café negro','La virginia','Bebida',42,'La Virginia Company',70,90),(3,'Vodka 750ml','Absolut raspberry','Bebida',15,'Absolut Vodka Company',610,1200),(4,'Lata de bebida de cola 350ml','Pepsi','Bebida',70,'Pepsi Company',50,80),(5,'Lata de bebida de cola 350ml','Coca-cola','Bebida',77,'Coca-cola Company',70,95),(6,'Barrita de chocolate 40 Gr.','KitKat','Alimento',88,'Nestlé Company',65,85),(7,'Barra de chocolate 300 Gr.','Milka','Alimento',50,'Milka Company',100,150),(8,'Cerveza 1L','Quilmes','Bebida',48,'Quilmes Company',100,125);

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
  `precioAlojamiento` float NOT NULL,
  `precioProductos` float DEFAULT 0,
  `precioTotal` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservas` */

insert  into `reservas`(`id`,`fechaEntrada`,`fechaSalida`,`horaEntrada`,`horaSalida`,`fk_cliente`,`fk_habitacion`,`tipoPago`,`estado`,`precioAlojamiento`,`precioProductos`,`precioTotal`) values (57,'01-10-2020','05-10-2020','00:30:00','01:30:00',33,1,'Efectivo','Cobrado',5200,840,6040),(58,'05-04-2021','14-04-2021','10:30:00','21:00:00',33,2,'Efectivo','Cobrado',14400,680,15080),(59,'14-10-2020','18-10-2020','08:00:00','10:00:00',34,4,'Efectivo','Pendiente',6000,0,6000),(60,'04-11-2020','09-11-2020','06:30:00','09:00:00',35,6,'Tarjeta de crédito','Pendiente',8500,705,9205);

/*Table structure for table `reservas_productos` */

DROP TABLE IF EXISTS `reservas_productos`;

CREATE TABLE `reservas_productos` (
  `reserva_id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  `cantProducto` int(11) DEFAULT NULL,
  `precioTotal` float DEFAULT NULL,
  PRIMARY KEY (`producto_id`,`reserva_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservas_productos` */

insert  into `reservas_productos`(`reserva_id`,`producto_id`,`cantProducto`,`precioTotal`) values (57,4,5,600),(58,4,4,300),(57,5,2,240),(58,5,4,380),(60,5,3,285),(60,6,2,170),(60,8,2,250);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
