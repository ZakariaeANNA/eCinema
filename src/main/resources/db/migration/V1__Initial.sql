-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 25, 2022 at 03:58 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecine`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `email` varchar(255) NOT NULL,
  `firstname` varchar(40) NOT NULL,
  `lastname` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `added_date`, `email`, `firstname`, `lastname`) VALUES
(1, '2022-10-08 20:55:49', 'moaad.akdi@emeal.nttdata.com', 'moaad', 'Akdi'),
(2, '2022-10-08 20:56:34', 'al.mohamed@gmail.com', 'moahmed', 'al youssri');

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `id` bigint(20) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `annee` int(11) NOT NULL,
  `duree` int(11) NOT NULL,
  `titre` varchar(50) NOT NULL,
  `genre_id` bigint(20) DEFAULT NULL,
  `nationalite_id` bigint(20) DEFAULT NULL,
  `director_id` bigint(20) DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`id`, `added_date`, `annee`, `duree`, `titre`, `genre_id`, `nationalite_id`, `director_id`, `cover`) VALUES
(18, '2022-10-25 12:55:15', 2022, 124, 'Black Adam', 1, 1, 11, '/photos/covers/948ec333-a1b7-44f7-8019-5120191f9517MV5BYzZkOGUwMzMtMTgyNS00YjFlLTg5.jpg'),
(19, '2022-10-25 13:15:52', 2022, 130, 'Top Gun: Maverick', 1, 1, 13, '/photos/covers/49c7ba8b-e9e0-4ce2-84d2-65611b8dcdcfMV5BZWYzOGEwNTgtNWU3NS00ZTQ0LWJk.jpg'),
(20, '2022-10-25 13:52:03', 2022, 129, 'Broker', 2, 2, 18, '/photos/covers/ba3d117c-7b1c-42b0-b32e-43194c613821MV5BY2UxOTEwOGUtYmFmOS00NTc4LWI0.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `film_acteur`
--

CREATE TABLE `film_acteur` (
  `actor_id` bigint(20) NOT NULL,
  `film_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `film_acteur`
--

INSERT INTO `film_acteur` (`actor_id`, `film_id`) VALUES
(3, 2),
(3, 4),
(3, 6),
(4, 2),
(4, 3),
(5, 2),
(5, 3),
(12, 3),
(12, 2),
(18, 8),
(18, 9),
(18, 10),
(19, 14),
(19, 12),
(19, 15),
(20, 16),
(20, 17);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`id`, `libelle`) VALUES
(1, 'Action'),
(2, 'Drama'),
(3, 'Comedy'),
(4, 'Thriller'),
(5, 'Science fiction');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE `media` (
  `id` bigint(20) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `media` varchar(100) NOT NULL,
  `type_media` varchar(255) DEFAULT NULL,
  `film_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`id`, `added_date`, `media`, `type_media`, `film_id`) VALUES
(12, '2022-10-20 18:55:33', '/photos/albums/397936c3-d612-4f40-b7d9-0d67cdeaa735aaaa.jpg', 'IMAGE', 1),
(13, '2022-10-20 18:56:44', '/photos/albums/9405f68c-2351-4084-9b3d-0596bda96a17aaaa.jpg', 'IMAGE', 1),
(15, '2022-10-20 20:29:40', '/photos/albums/6ca8f5e0-f26f-4e7d-9776-12f9bdf460dcaaaa.jpg', 'IMAGE', 17),
(16, '2022-10-20 20:29:40', '/photos/albums/32a4d190-357d-486d-a8f5-b62b2d47a2defff.jpg', 'IMAGE', 17),
(18, '2022-10-20 20:29:59', '/photos/albums/c416ccc4-bb21-490e-b079-24585db36788fff.jpg', 'IMAGE', 17),
(19, '2022-10-21 09:04:17', '/photos/albums/6d0efde9-ff18-41a7-ac87-9b83ca2fc900fff.jpg', 'IMAGE', 1),
(20, '2022-10-25 12:55:15', '/photos/albums/38d6d2c1-4981-4436-9992-4e8f7c61d7d5MV5BMThkZGM5NTgtZjJhMy00ZmNiLTk0.jpg', 'IMAGE', 18),
(21, '2022-10-25 12:55:15', '/photos/albums/31c3a35d-7fee-4fc9-b925-aad43c4e9153MV5BOTlhYmEwMjMtODBkOC00MjE3LTky.jpg', 'IMAGE', 18),
(22, '2022-10-25 12:56:43', '/photos/albums/d490624a-0242-4c1c-a01e-0560ddd835edMV5BYzJhYTAyZjItNzU5MS00MDRlLWI0.jpg', 'IMAGE', 18),
(23, '2022-10-25 13:15:52', '/photos/albums/8101bb3f-5501-46c3-adcb-bcad1d74ab59MV5BMzNkODU5M2EtZGE2Zi00ZTg0LWI1.jpg', 'IMAGE', 19),
(24, '2022-10-25 13:28:13', '/photos/albums/477bc53a-b41a-4a8d-b804-dc147b976738MV5BNzQ3YzczNWItZTIyMS00MDQ3LWI5N2Mt.jpg', 'IMAGE', 19),
(25, '2022-10-25 13:31:12', '/photos/albums/0cedc89d-9da1-47e7-95a4-fcedb6178f7bMV5BNGFhZDY4MTQtNzk0ZS00NzJhLTgw.jpg', 'IMAGE', 19),
(26, '2022-10-25 13:52:03', '/photos/albums/95911ad6-0e01-40a9-a575-82f5b9db5e7bMV5BZDQ0YjczNmMtYTE4ZS00MjA3LTg.jpg', 'IMAGE', 20),
(27, '2022-10-25 13:52:03', '/photos/albums/f9893265-eefb-4d2c-8eb0-b6abdc8e3214MV5BZjIwMWVjZWYtNmI0NS00OWRjLWE0ZmUtZjk3Mj.jpg', 'IMAGE', 20),
(28, '2022-10-25 13:52:03', '/photos/albums/b6ca815f-da34-4b5a-971f-7ea997c82313MV5BNmY4NGZjMjYtZGRiOS00MTgxLWExMmMtZm.jpg', 'IMAGE', 20),
(29, '2022-10-25 13:52:03', '/photos/albums/e5299c7d-7cca-4b6a-91db-15427997a3c2MV5BYzhhODYwMmYtMmIwYy00.jpg', 'IMAGE', 20);

-- --------------------------------------------------------

--
-- Table structure for table `nationalite`
--

CREATE TABLE `nationalite` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nationalite`
--

INSERT INTO `nationalite` (`id`, `libelle`) VALUES
(1, 'United State Of America'),
(2, 'Korea'),
(4, 'Spain'),
(5, 'Japan');

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `id` bigint(20) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `date_naissance` datetime DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `prenom` varchar(50) NOT NULL,
  `type_personne` varchar(50) NOT NULL,
  `nationalite_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id`, `added_date`, `date_naissance`, `nom`, `photo`, `prenom`, `type_personne`, `nationalite_id`) VALUES
(8, '2022-10-25 12:37:37', '1972-05-02 00:00:00', 'Johnson', '/photos/personnes/9110fcbc-6594-46cf-bf0c-fdbd72758a3dMV5BMTkyNDQ3NzAxM15BMl5BanBnXkFt.jpg', 'Dwayne', 'ACTEUR', 1),
(9, '2022-10-25 12:41:31', '1986-09-20 00:00:00', 'Hodge', '/photos/personnes/81406a48-5094-474a-8ea5-aa9a41f9a2dcMV5BMjA5MjQ1MDk1NF5BMl5BanBnXkFt.jpg', 'Aldis', 'ACTEUR', 1),
(10, '2022-10-25 12:43:51', '1953-05-16 00:00:00', 'Brosnan', '/photos/personnes/8349b3ff-9d09-4fb1-ba2f-7ba6f85cc791MV5BMTMwMjMxNzM4OV5BMl5BanBnXkFt.jpg', 'Pierce', 'ACTEUR', 1),
(11, '2022-10-25 12:50:43', '1974-03-23 00:00:00', 'Collet-Serra', '/photos/personnes/66e0d2c3-e1ad-4610-8468-496774a47788MV5BMTc3OTk0MTk2M15BMl5BanBnXkFt.jpg', 'Jaume', 'REALISATEUR', 4),
(12, '2022-10-25 13:09:18', '1962-07-03 00:00:00', 'CRUISE', '/photos/personnes/a176123c-0020-4e29-83bf-a70c28da52c8MV5BYTFlOTdjMjgtNmY0ZC00MDgxLThj.jpg', 'Tom', 'ACTEUR', 1),
(13, '2022-10-25 13:14:15', '1974-05-03 00:00:00', 'Kosinski', '/photos/personnes/d50e4745-b067-4415-b580-93a3f183048dMV5BNWIzZDI1YWEtYzE1NS00NTFmLWJi.jpg', 'Joseph', 'REALISATEUR', 1),
(14, '2022-10-25 13:20:30', '1970-12-12 00:00:00', 'Connelly', '/photos/personnes/454b0ee3-d487-47d8-9482-4d5a5d4751b9MV5BNWM0OTlhYmQtZWI0NS00NzM0LWE5.jpg', 'Jennifer', 'ACTEUR', 1),
(15, '2022-10-25 13:33:37', '1971-03-10 00:00:00', 'Hamm', '/photos/personnes/f7db07b4-fab8-4206-bff1-e44ea2c7590eMV5BNzg0MzA4MTY5M15BMl5BanBn.jpg', 'Jon', 'ACTEUR', 1),
(16, '2022-10-25 13:43:19', '1967-01-17 00:00:00', 'Kang-ho', '/photos/personnes/dc07e375-1f57-4da5-a250-5cb69b5ed217MV5BMDA4ZWYyNGItMzdjOC00YzEzLWFj.jpg', 'Song', 'ACTEUR', 2),
(17, '2022-10-25 13:45:58', '1981-01-18 00:00:00', 'Gang', '/photos/personnes/ab3e4aa9-8680-4b93-8eb5-9851292594c7MV5BZDJkNjM0NGEtZ.jpg', 'Dong-won', 'ACTEUR', 2),
(18, '2022-10-25 13:47:54', '1962-06-06 00:00:00', 'Koreeda', '/photos/personnes/89cf059f-8ff0-41c9-bf9a-8088a5d63769MV5BNjc3NDY4MzgzMV5B.jpg', 'Hirokazu', 'REALISATEUR', 5);

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

CREATE TABLE `salle` (
  `id` bigint(20) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `capacite` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `nom_salle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salle`
--

INSERT INTO `salle` (`id`, `added_date`, `capacite`, `numero`, `nom_salle`) VALUES
(1, '2022-10-08 11:30:56', 30000, 12, NULL),
(2, '2022-10-08 21:17:58', 10000, 2545, NULL),
(3, '2022-10-17 13:30:53', 1000, 19, NULL),
(4, '2022-10-25 13:53:13', 500, 25, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `seance`
--

CREATE TABLE `seance` (
  `id` bigint(20) NOT NULL,
  `date_projection` datetime DEFAULT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `film_id` bigint(20) DEFAULT NULL,
  `salle_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seance`
--

INSERT INTO `seance` (`id`, `date_projection`, `heure_debut`, `heure_fin`, `film_id`, `salle_id`) VALUES
(1, '2022-01-26 00:00:00', '18:00:00', '20:00:00', 20, 2);

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id`, `email`, `password`, `username`) VALUES
(1, 'moaadakdi@gmail.com', 'omarrami123', 'moaadakdi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`) USING HASH;

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK72h8eraicy2k4cn11ibevrg7m` (`genre_id`),
  ADD KEY `FKg9fhcn138vw45qkc7ligu3u8u` (`nationalite_id`),
  ADD KEY `FKcldyhuux59siv1auyk9erp8s0` (`director_id`);

--
-- Indexes for table `film_acteur`
--
ALTER TABLE `film_acteur`
  ADD KEY `FKk64q02b3b08hdovqbtp0do6fs` (`film_id`),
  ADD KEY `FKqn8rmou6bowxrsrkvdda9h5ho` (`actor_id`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbfdkb31etafed8p6k94pbnv90` (`film_id`);

--
-- Indexes for table `nationalite`
--
ALTER TABLE `nationalite`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkenihid5kc1hh7v33oab2u74f` (`nationalite_id`);

--
-- Indexes for table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKchlcmip8ejlfuo4c990k5ry8y` (`film_id`),
  ADD KEY `FKhupu0bkkkinurpu14xeyjcuep` (`salle_id`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_i6qjjoe560mee5ajdg7v1o6mi` (`email`) USING HASH,
  ADD UNIQUE KEY `UK_jhib4legehrm4yscx9t3lirqi` (`username`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `film`
--
ALTER TABLE `film`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `media`
--
ALTER TABLE `media`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `nationalite`
--
ALTER TABLE `nationalite`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `salle`
--
ALTER TABLE `salle`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `seance`
--
ALTER TABLE `seance`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
