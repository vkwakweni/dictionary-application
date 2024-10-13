-- Getting synonyms

-- Table with synonyms of all words
select distinct t4.lemma, t5.lemma, t4.pos
from (select t2.lemma, t3.pos, t3.definition, t3.synsetid, t2.wordid
		from (select t1.wordid, t1.lemma, t2.synsetid
			  from words as t1 inner join senses as t2 on t1.wordid = t2.wordid) as t2 
			  inner join synsets as t3 on t2.synsetid = t3.synsetid) as t4
	left join (select t2.lemma, t3.pos, t3.synsetid
				from (select t1.wordid, t1.lemma, t2.synsetid
	  			from words as t1 inner join senses as t2 on t1.wordid = t2.wordid) as t2
	  				inner join synsets as t3 on t2.synsetid = t3.synsetid) as t5 on t4.synsetid = t5.synsetid
where t4.lemma != t5.lemma

-- Table with synonyms of a word
select distinct t4.lemma, t5.lemma, t4.pos
from (select t2.lemma, t3.pos, t3.definition, t3.synsetid, t2.wordid
		from (select t1.wordid, t1.lemma, t2.synsetid
			  from words as t1 inner join senses as t2 on t1.wordid = t2.wordid) as t2 
			  inner join synsets as t3 on t2.synsetid = t3.synsetid) as t4
	left join (select t2.lemma, t3.pos, t3.synsetid
				from (select t1.wordid, t1.lemma, t2.synsetid
	  			from words as t1 inner join senses as t2 on t1.wordid = t2.wordid) as t2
	  				inner join synsets as t3 on t2.synsetid = t3.synsetid) as t5 on t4.synsetid = t5.synsetid
where t4.lemma != t5.lemma
and t4.lemma = 'see'

-- A Synset memeber set (groups synonyms together)
select t4.synsetid, t4.pos, GROUP_CONCAT(lemma), t4.definition
from (select t2.lemma, t3.pos, t3.definition, t2.synsetid
		from (select t1.wordid, t1.lemma, t2.synsetid
	  			from words as t1 inner join senses as t2 on t1.wordid = t2.wordid) as t2 inner join synsets as t3 on t2.synsetid = t3.synsetid) as t4
GROUP BY synsetid