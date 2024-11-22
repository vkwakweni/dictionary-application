-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 22, 2024 at 03:35 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wordnet31`
--

-- --------------------------------------------------------

--
-- Table structure for table `glossary_words`
--

CREATE TABLE `glossary_words` (
  `glossary_id` int(11) NOT NULL,
  `word_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `glossary_words`
--

INSERT INTO `glossary_words` (`glossary_id`, `word_id`) VALUES
(1, 14),
(1, 18),
(2, 14);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `glossary_words`
--
ALTER TABLE `glossary_words`
  ADD PRIMARY KEY (`glossary_id`,`word_id`),
  ADD KEY `word_id` (`word_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `glossary_words`
--
ALTER TABLE `glossary_words`
  ADD CONSTRAINT `glossary_words_ibfk_1` FOREIGN KEY (`glossary_id`) REFERENCES `Glossary` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `glossary_words_ibfk_2` FOREIGN KEY (`word_id`) REFERENCES `senses` (`wordid`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
