-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 19 Avril 2018 à 21:07
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
  `prix` decimal(10,2) DEFAULT NULL,
  `genre_id` int(11) DEFAULT NULL,
  `annee_sortie` date DEFAULT NULL,
  `maison_distribution` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artiste_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Album`
--

INSERT INTO `Album` (`id`, `titre`, `prix`, `genre_id`, `annee_sortie`, `maison_distribution`, `image_url`, `artiste_id`) VALUES
(1, 'Test ajout', '30.00', 2, '1999-02-02', 'test', 'test', 1),
(3, 'Test ajout', '30.00', 2, '1999-02-02', 'test', 'test', 1),
(55, 'a-TestModiAlbum-InValide', '99.90', 1, '2000-01-01', 'MaisonInValide', 'ImageInValide', 1),
(57, 'Test ajout', '30.22', 2, '1999-02-02', 'test', 'test', 1),
(65, 'Test ajout', '30.22', 2, '1999-02-02', 'test', 'test', 1),
(72, 'Test ajout', '30.22', 2, '1999-02-02', 'test', 'test', 1);

-- --------------------------------------------------------

--
-- Structure de la table `Artiste`
--

CREATE TABLE IF NOT EXISTS `Artiste` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `membre` tinyint(1) DEFAULT NULL,
  `photo_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Artiste`
--

INSERT INTO `Artiste` (`id`, `nom`, `membre`, `photo_url`) VALUES
(1, 'Test1', 0, 'Test1aa'),
(2, 'Test2', 1, 'Test2aa'),
(3, 'Test3', 1, 'Test3aa'),
(47, 'a-TestModifier-InValide', 0, 'Modifition-InValide'),
(79, 'TestAjout', 0, 'Test5aa'),
(92, 'TestAjout', 0, 'Test5aa');

-- --------------------------------------------------------

--
-- Structure de la table `Genre`
--

CREATE TABLE IF NOT EXISTS `Genre` (
  `id` int(11) NOT NULL,
  `description` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Genre`
--

INSERT INTO `Genre` (`id`, `description`) VALUES
(1, 'Nightcore'),
(2, 'Hip Hop'),
(3, 'Instrumental'),
(4, 'Reggae'),
(5, 'Pop'),
(6, 'Rock');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Album`
--
ALTER TABLE `Album`
  ADD PRIMARY KEY (`id`),
  ADD KEY `genre_id` (`genre_id`),
  ADD KEY `artiste_id` (`artiste_id`);

--
-- Index pour la table `Artiste`
--
ALTER TABLE `Artiste`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Genre`
--
ALTER TABLE `Genre`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Album`
--
ALTER TABLE `Album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=81;
--
-- AUTO_INCREMENT pour la table `Artiste`
--
ALTER TABLE `Artiste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=96;
--
-- AUTO_INCREMENT pour la table `Genre`
--
ALTER TABLE `Genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Album`
--
ALTER TABLE `Album`
  ADD CONSTRAINT `fk_artiste_album` FOREIGN KEY (`artiste_id`) REFERENCES `Artiste` (`id`),
  ADD CONSTRAINT `fk_genre_album` FOREIGN KEY (`genre_id`) REFERENCES `Genre` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
