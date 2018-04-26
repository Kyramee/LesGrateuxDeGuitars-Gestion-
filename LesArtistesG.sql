-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 26 Avril 2018 à 21:24
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
  `prix` decimal(10,0) DEFAULT NULL,
  `genre` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `annee_sortie` date DEFAULT NULL,
  `maison_distribution` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artiste_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Album`
--

INSERT INTO `Album` (`id`, `titre`, `prix`, `genre`, `annee_sortie`, `maison_distribution`, `image_url`, `artiste_id`) VALUES
(1, 'Test ajout', '30', '2', '1999-02-02', 'test', 'test', 1),
(2, 'Test ajout', '30', '2', '1999-02-02', 'test', 'test', 1),
(3, 'Test ajout', '30', '2', '1999-02-02', 'test', 'test', 1),
(4, 'Test ajout', '30', '2', '1999-02-02', 'test', 'test', 1),
(9, 'Test ajout', '30', '2', '1999-02-02', 'test', 'test', 1);

-- --------------------------------------------------------

--
-- Structure de la table `Artiste`
--

CREATE TABLE IF NOT EXISTS `Artiste` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `membre` tinyint(1) DEFAULT NULL,
  `photo_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Artiste`
--

INSERT INTO `Artiste` (`id`, `nom`, `membre`, `photo_url`) VALUES
(1, 'TestAjout', 0, 'Test5aa'),
(2, 'Test2', 1, 'Test2aa'),
(3, 'Test3', 1, 'Test3aa'),
(24, 'TestAjout', 0, 'Test5aa'),
(25, 'fvvvvvvvvgfdh', 1, 'fdhfg'),
(26, 'fvvvvvvvvgfdh', 1, 'fdhfg'),
(27, 'testAjout1', 0, 'safd'),
(28, 'thurewgtru', 1, 'dsd'),
(29, 'sda', 1, 'saf'),
(31, 'sda', 1, 'sda'),
(32, 'sda', 1, 'sda'),
(33, 'sda', 1, 'sda'),
(34, 'sda', 1, 'sda'),
(35, 'sda', 1, 'sda'),
(36, 'sda', 1, 'sda'),
(37, 'sda', 1, 'sda'),
(38, 'sda', 1, 'sda'),
(39, 'sda', 1, 'sda'),
(40, 'zv', 1, 'vc'),
(41, 'sda', 1, 'sda'),
(42, 'sda', 1, 'sda'),
(43, 'sda', 1, 'sda'),
(44, 'sda', 1, 'sda'),
(45, 'sda', 1, 'sda'),
(46, 'sda', 1, 'sda'),
(47, 'sda', 1, 'sda');

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
('Adrien', 'root'),
('Groot', 'root'),
('Steven', 'root');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `Artiste`
--
ALTER TABLE `Artiste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=48;
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
