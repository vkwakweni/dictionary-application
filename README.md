# Dictionary API
An online dictionary with synonyms and images.

## Getting Started
To be able ro develop and run the application locally, follow the following steps.

### Prequisites
|Software|Description|
|---|---|
|[XAMPP](https://sourceforge.net/projects/xampp/files/)|Handles the various servers and connectors.|
|NB Spring Boot|Stage for developing the API|
|[MySQL Connector](https://dev.mysql.com/downloads/connector/j/)|Accessing the database for the API|
|mysql-server|MySQL Command Line tool for Linux

### Setting up the WordNet Database locally
[WordNet](https://wordnet.princeton.edu/) is the data source for this Dictionary API. WordNet is lexical database for the English language that provides IDs for faster searching.

1. Access phpMyAdmin from your local server at http://localhost/phpmyadmin/.
2. Create a database named `wordnet31`.
3. To load the (empty) schemas, run [`mysql-wn-schema.sql`](./wordnet-sql-scripts/set-up/mysql-wn-schema.sql)
    1. You can import SQL files straight into phpMyAdmin. However if you are unable to, do the following:
        1. Access MySQL from the command line/shell/terminal. On Linux, this is done with mysql-server, with the command `mysql -h 127.0.0.1 -P 3306 -u root -p wordnet31`.
        2. To load 'import' the tables, run `source dictionary-application/wordnet-sql-scripts/mysql-wn-schema.sql;`
        3. You can quit once you've loaded all the files you need to.
4. To load the data into the schemas, run [`mysql-wn-data.sql`](./wordnet-sql-scripts/set-up/mysql-wn-data.sql), following the same procedures in step 3.

### Using the WordNet Dictionary from SQL
In the folder [`template-queries`](./wordnet-sql-scripts/template-queries) are templates for using the WordNet SQL database as a dictionary.

* [`lemma-definition.sql`](./wordnet-sql-scripts/template-queries/lemma-definition.sql) gets all the words with their definitions
* [`specific-lemma-definition.sql`](./wordnet-sql-scripts/template-queries/specific-lemma-definition.sql) gets the entries for a particular word.
* [`lemma-synonym.sql`](./wordnet-sql-scripts/template-queries/lemma-synonym.sql) gets gets synonyms for words.
