select t2.lemma, t3.pos, t3.definition
from (select t1.wordid, t1.lemma, t2.synsetid
	  from words as t1 inner join senses as t2 on t1.wordid = t2.wordid) as t2 inner join synsets as t3 on t2.synsetid = t3.synsetid 
where lemma = 'mischievous'