-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-11-2019 a las 04:25:21
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pruebaestudiantes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `avisos`
--

CREATE TABLE `avisos` (
  `idaviso` int(11) NOT NULL,
  `nomaviso` varchar(100) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `imagen` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `avisos`
--

INSERT INTO `avisos` (`idaviso`, `nomaviso`, `descripcion`, `imagen`) VALUES
(2, 'siiiiiiiiiiiiiiiiiiiii', 'fuyfuyfuyfuifuifyu', 'http://192.168.43.224/Servicios/Fotos/avisouno.jpg'),
(3, 'soooooooooooooooooooo', 'fuyfuyfuyfuifuifyu', 'http://192.168.43.224/Servicios/Fotos/avisouno.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiantes`
--

CREATE TABLE `estudiantes` (
  `Nombre` varchar(55) DEFAULT NULL,
  `NoControl` varchar(8) NOT NULL,
  `Carrera` varchar(50) DEFAULT NULL,
  `Foto` longtext DEFAULT NULL,
  `QR` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estudiantes`
--

INSERT INTO `estudiantes` (`Nombre`, `NoControl`, `Carrera`, `Foto`, `QR`) VALUES
('Jose Didiere Ramirez Renteria', '16980254', 'TICS', 'http://192.168.43.224/Servicios/Fotos/16980254.jpg', 'http://192.168.43.224/Servicios/Fotos/16980254-QR.png'),
('Juan Daniel Torres Moreno', '16980503', 'TICS', 'http://192.168.43.224/Servicios/Fotos/16980503.png', 'http://192.168.43.224/Servicios/Fotos/16980503-QR.png'),
('Marco Antonio Balderas Velazquez', '16980598', 'TICS', 'http://192.168.43.224/Servicios/Fotos/16980598.jpg', 'http://192.168.43.224/Servicios/Fotos/16980598-QR.png'),
('Alan Renato Ortegon Castro', '16980876', 'TICS', 'http://192.168.43.224/Servicios/Fotos/16980876.jpg', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `avisos`
--
ALTER TABLE `avisos`
  ADD PRIMARY KEY (`idaviso`);

--
-- Indices de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD PRIMARY KEY (`NoControl`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `avisos`
--
ALTER TABLE `avisos`
  MODIFY `idaviso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
