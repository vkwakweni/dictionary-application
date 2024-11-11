CREATE DATABASE CHAT;
CREATE TABLE senses(wordid int(10),
					casedwordid int(10),
					synsetid int(10),
					senseid int(10),
					sensenum smallint(5),
					lexid smallint(5),
					tagcount int(10),
					sensekey varchar(100));
INSERT INTO chat.senses (wordid,casedwordid,synsetid,senseid,sensenum,lexid,tagcount,sensekey) VALUES
	 (1,NULL,108659519,1,1,0,0,'''hood%1:15:00::'),
	 (2,1,108970180,2,1,0,0,'''s_gravenhage%1:15:00::'),
	 (3,NULL,400252367,3,1,0,0,'''tween%4:02:00::'),
	 (4,NULL,400500491,4,1,0,0,'''tween_decks%4:02:00::'),
	 (5,NULL,104510146,5,1,0,0,'.22%1:06:00::'),
	 (6,NULL,303157978,8,1,0,0,'.22_caliber%3:01:00::'),
	 (7,NULL,303157978,9,1,0,0,'.22_calibre%3:01:00::'),
	 (8,NULL,303157978,6,1,0,0,'.22-caliber%3:01:00::'),
	 (9,NULL,303157978,7,1,0,0,'.22-calibre%3:01:00::'),
	 (10,NULL,303158270,12,1,0,0,'.38_caliber%3:01:00::');
INSERT INTO chat.senses (wordid,casedwordid,synsetid,senseid,sensenum,lexid,tagcount,sensekey) VALUES
	 (11,NULL,303158270,13,1,0,0,'.38_calibre%3:01:00::'),
	 (12,NULL,303158270,10,1,0,0,'.38-caliber%3:01:00::'),
	 (13,NULL,303158270,11,1,0,0,'.38-calibre%3:01:00::'),
	 (14,NULL,303158563,16,1,0,0,'.45_caliber%3:01:00::'),
	 (15,NULL,303158563,17,1,0,0,'.45_calibre%3:01:00::'),
	 (16,NULL,303158563,14,1,0,0,'.45-caliber%3:01:00::'),
	 (17,NULL,303158563,15,1,0,0,'.45-calibre%3:01:00::'),
	 (18,NULL,113764498,18,1,0,20,'0%1:23:00::'),
	 (18,NULL,302193771,19,1,0,3,'0%5:00:00:cardinal:00'),
	 (19,NULL,113764713,20,1,0,21,'1%1:23:00::');
