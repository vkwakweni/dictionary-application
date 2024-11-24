-- schema + data
-- senses, synsets, words
--
-- Table structure for table `words`
--

DROP TABLE IF EXISTS `words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `words` (
  `wordid` int(10) unsigned NOT NULL DEFAULT '0',
  `lemma` varchar(80) NOT NULL,
  PRIMARY KEY (`wordid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `senses`
--

--
-- Table structure for table `synsets`
--

DROP TABLE IF EXISTS `synsets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `synsets` (
  `synsetid` int(10) unsigned NOT NULL DEFAULT '0',
  `pos` enum('n','v','a','r','s') NOT NULL,
  `lexdomainid` smallint(5) unsigned NOT NULL DEFAULT '0',
  `definition` mediumtext,
  PRIMARY KEY (`synsetid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `senses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `senses` (
  `wordid` int(10) unsigned NOT NULL DEFAULT '0',
  `casedwordid` int(10) unsigned DEFAULT NULL,
  `synsetid` int(10) unsigned NOT NULL DEFAULT '0',
  `senseid` int(10) unsigned DEFAULT NULL,
  `sensenum` smallint(5) unsigned NOT NULL DEFAULT '0',
  `lexid` smallint(5) unsigned NOT NULL DEFAULT '0',
  `tagcount` int(10) unsigned DEFAULT NULL,
  `sensekey` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`wordid`,`synsetid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- schema
-- glossary, glossary_word, users

-- wordnet31.glossary definition

CREATE TABLE `glossary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- wordnet31.glossary_words definition

CREATE TABLE `glossary_words` (
  `glossary_id` int(11) NOT NULL,
  `word_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`glossary_id`,`word_id`),
  KEY `glossary_words_ibfk_2` (`word_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- wordnet31.users definition

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
