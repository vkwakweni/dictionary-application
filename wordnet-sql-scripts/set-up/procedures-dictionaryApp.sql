DROP PROCEDURE IF EXISTS searchWordsByPattern;
CREATE PROCEDURE searchWordsByPattern(IN searchPattern varchar(255))
begin
	select
		lemma as lemma
	from
		words
	WHERE lemma like concat('%', searchPattern, '%');
end; 

DROP PROCEDURE IF EXISTS GetDefinitionsByPOS;
CREATE PROCEDURE GetDefinitionsByPOS(IN wordLemma VARCHAR(255), IN part VARCHAR(10))
BEGIN
	select w.lemma as word,
			s.definition,
			s.pos
	from words w
	join (select se.wordid, se.synsetid, sy.pos, sy.definition
			from senses se
			join synsets sy on se.synsetid = sy.synsetid) s on w.wordid = s.wordid
	where w.lemma = wordLemma
	and s.pos = part;	
END;