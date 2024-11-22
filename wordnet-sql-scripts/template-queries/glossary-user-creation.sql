create table Users(id int(11) not null primary key auto_increment,
                    username varchar(255) not null)

create table Glossary(id int(11) not null primary key  auto_increment,
                    user_id int(11) not null on delete restrict on update restrict,
                    foreign key (word_id) references Users(id));

create table glossary_words(glossary_id int (11) not null on delete restrict on update cascade,
                            word_id int(10) not null on delete restrict on update cascade,
                            foreign key (glossary_id) references Glossary(id),
                            foreign key (word_id) references seneses(wordid),
                            PRIMARY KEY (glossary_id, word_id))