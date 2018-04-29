-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Dim 29 Avril 2018 à 04:35
-- Version du serveur :  5.6.37
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `LesArtistesG`
--

-- --------------------------------------------------------

--
-- Structure de la table `Album`
--

CREATE TABLE IF NOT EXISTS `Album` (
  `id` int(11) NOT NULL,
  `titre` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prix` int(11) DEFAULT NULL,
  `genre` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `annee_sortie` date DEFAULT NULL,
  `maison_distribution` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artiste_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Album`
--

INSERT INTO `Album` (`id`, `titre`, `prix`, `genre`, `annee_sortie`, `maison_distribution`, `image_url`, `artiste_id`) VALUES
(9, 'Boolean', 10, 'Pop', '1992-02-20', 'Programers', 'imageAlbum1.jpeg', 34),
(10, 'Interger', 25, 'Rock', '1992-02-20', 'Programers', 'imageAlbum2.jpeg', 31),
(11, 'String', 15, 'Classic', '1967-10-26', 'Teachers', 'imageAlbum3.jpeg', 33),
(12, 'Double', 60, 'Reggae', '1005-08-17', 'Stone Age', 'imageAlbum4.jpeg', 32),
(13, 'Char', 37, 'Nightcore', '2018-04-02', 'Students', 'imageAlbum5jpeg', 32);

-- --------------------------------------------------------

--
-- Structure de la table `Artiste`
--

CREATE TABLE IF NOT EXISTS `Artiste` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `membre` tinyint(1) DEFAULT NULL,
  `photo_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Artiste`
--

INSERT INTO `Artiste` (`id`, `nom`, `membre`, `photo_url`) VALUES
(30, 'Janne', 1, 'imageArtiste1.jpeg'),
(31, 'Megann', 0, 'imageArtiste2.jpeg'),
(32, 'Claude', 0, 'imageArtiste3.jpeg'),
(33, 'Magalie', 0, 'imageArtiste4.jpeg'),
(34, 'André', 1, 'imageArtiste5.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE IF NOT EXISTS `Utilisateur` (
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`nom`, `password`) VALUES
('Adrien', 'admin'),
('Farida', 'admin'),
('Groot', 'root'),
('Steven', 'admin'),
('Testeur', 'test');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Album`
--
ALTER TABLE `Album`
  ADD PRIMARY KEY (`id`),
  ADD KEY `genre_id` (`genre`),
  ADD KEY `artiste_id` (`artiste_id`);

--
-- Index pour la table `Artiste`
--
ALTER TABLE `Artiste`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`nom`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Album`
--
ALTER TABLE `Album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `Artiste`
--
ALTER TABLE `Artiste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Album`
--
ALTER TABLE `Album`
  ADD CONSTRAINT `fk_artiste_album` FOREIGN KEY (`artiste_id`) REFERENCES `Artiste` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
