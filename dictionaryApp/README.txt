—Set up---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Database: Create a database “wordnet31”, then import schema-dictionaryApp.sql in to phpmyadmin to set the database tables, then import the data contained in senses-dictionaryApp, synsets-dictionaryApp and words-dictionaryApp, this the data contained by the dictionary.
_____________________________________________________________________________________________________________

— Test(Swagger UI)------------------------------------------------------------------------------------------------------------------------------------------------------------
-Go in to http://localhost:8080/swagger-ui.html#/, here you will find the controller, that contains all of the methods and procedures for the models used on the project, words, glossaries and users. For get definition by POS(part of speech), use: “n “for noun, “v” for verb, “a” for adjective, “r” for adverb and “s” for adjective satellite, then choose a word e.g  “race” and “n”, expected output:race, n.
This is the only tricky method, since the pos are not listed on swagger. 
_____________________________________________________________________________________________________________
[
  [
    "race",
    "a canal for a current of water",
    "n"
  ],
  [
    "race",
    "a contest of speed",
    "n"
  ],
  [
    "race",
    "any competition",
    "n"
  ],
  [
    "race",
    "people who are believed to belong to the same genetic stock",
    "n"
  ],
  [
    "race",
    "(biology) a taxonomic group that is a division of a species; usually arises as a consequence of geographical isolation within a species",
    "n"
  ],
  [
    "race",
    "the flow of air that is driven backwards by an aircraft propeller",
    "n"
  ]
]
_____________________________________________________________________________________________________________



If zipped files has issues, or just for a better workflow clone the repository from: https://github.com/vkwakweni/dictionary-application