-- Samples for NamedQuery, Query

-- get all word and synsets
select wordid, synsetid
from senses


-- find the duplicate words
select wordid, count(wordid)
from senses
group by wordid
having count(wordid) > 1

-- getting the synsets for a particular word
select synsetid
from senses
where wordid=515 -- here, 18 is a word we would run as a parameter in Java
