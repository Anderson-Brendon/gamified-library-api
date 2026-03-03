-- Seed data generated from Java models
-- 15 rows per table

-- GENRES
INSERT INTO genre (id, name) VALUES
(1,'Fiction'),(2,'Fantasy'),(3,'Science Fiction'),(4,'Romance'),(5,'Mystery'),
(6,'History'),(7,'Biography'),(8,'Self-Help'),(9,'Horror'),(10,'Adventure'),
(11,'Poetry'),(12,'Children'),(13,'Philosophy'),(14,'Technology'),(15,'Comics');

-- AUTHORS
INSERT INTO author (id, name) VALUES
(1,'Alice Walker'),(2,'Bruno Silva'),(3,'Carla Mendes'),(4,'Diego Pereira'),(5,'Eva Costa'),
(6,'Fernando Lima'),(7,'Gabriela Souza'),(8,'Hugo Rocha'),(9,'Isabela Ramos'),(10,'Joao Martins'),
(11,'Karla Fernandes'),(12,'Lucas Oliveira'),(13,'Mariana Alves'),(14,'Nina Duarte'),(15,'Otavio Nunes');

-- USERS
INSERT INTO app_user (id, username, profile_pic, email, password_hash, is_verified, verification_code) VALUES
(1,'user01','https://placehold.co/128x128?text=user01','user01@example.com','hash01',true,'VC0011'),
(2,'user02','https://placehold.co/128x128?text=user02','user02@example.com','hash02',false,'VC0022'),
(3,'user03','https://placehold.co/128x128?text=user03','user03@example.com','hash03',true,'VC0033'),
(4,'reader04','https://placehold.co/128x128?text=reader04','reader04@example.com','hash04',true,'VC0044'),
(5,'reader05','https://placehold.co/128x128?text=reader05','reader05@example.com','hash05',false,'VC0055'),
(6,'booklover6','https://placehold.co/128x128?text=booklover6','booklover6@example.com','hash06',true,'VC0066'),
(7,'anna7','https://placehold.co/128x128?text=anna7','anna7@example.com','hash07',false,'VC0077'),
(8,'marc8','https://placehold.co/128x128?text=marc8','marc8@example.com','hash08',true,'VC0088'),
(9,'lisa9','https://placehold.co/128x128?text=lisa9','lisa9@example.com','hash09',true,'VC0099'),
(10,'paulo10','https://placehold.co/128x128?text=paulo10','paulo10@example.com','hash10',false,'VC0100'),
(11,'zara11','https://placehold.co/128x128?text=zara11','zara11@example.com','hash11',true,'VC0111'),
(12,'mike12','https://placehold.co/128x128?text=mike12','mike12@example.com','hash12',false,'VC0122'),
(13,'sara13','https://placehold.co/128x128?text=sara13','sara13@example.com','hash13',true,'VC0133'),
(14,'tom14','https://placehold.co/128x128?text=tom14','tom14@example.com','hash14',false,'VC0144'),
(15,'ivy15','https://placehold.co/128x128?text=ivy15','ivy15@example.com','hash15',true,'VC0155');

-- BOOKS
INSERT INTO book (id, title, description, release_year, book_cover, pdf, slug, author_id, genre_id) VALUES
(1,'The First Tale','A gripping first tale',2001,'https://placehold.co/400x600?text=Cover+1','/pdfs/p1.pdf','the-first-tale',1,1),
(2,'Magic Roads','Fantasy adventures',1998,'https://placehold.co/400x600?text=Cover+2','/pdfs/p2.pdf','magic-roads',2,2),
(3,'Future Worlds','Sci-fi stories',2015,'https://placehold.co/400x600?text=Cover+3','/pdfs/p3.pdf','future-worlds',3,3),
(4,'Love & Loss','A touching romance',2010,'https://placehold.co/400x600?text=Cover+4','/pdfs/p4.pdf','love-loss',4,4),
(5,'The Hidden Key','A mysterious journey',2005,'https://placehold.co/400x600?text=Cover+5','/pdfs/p5.pdf','the-hidden-key',5,5),
(6,'Ancient Times','Historical accounts',1995,'https://placehold.co/400x600?text=Cover+6','/pdfs/p6.pdf','ancient-times',6,6),
(7,'Life Lines','Biography snapshots',2018,'https://placehold.co/400x600?text=Cover+7','/pdfs/p7.pdf','life-lines',7,7),
(8,'Better You','Self-help guide',2020,'https://placehold.co/400x600?text=Cover+8','/pdfs/p8.pdf','better-you',8,8),
(9,'Night Whispers','Horror stories',2008,'https://placehold.co/400x600?text=Cover+9','/pdfs/p9.pdf','night-whispers',9,9),
(10,'Island Run','Adventure novel',1992,'https://placehold.co/400x600?text=Cover+10','/pdfs/p10.pdf','island-run',10,10),
(11,'Quiet Poems','Collected poems',1987,'https://placehold.co/400x600?text=Cover+11','/pdfs/p11.pdf','quiet-poems',11,11),
(12,'Tiny Tales','Children stories',2012,'https://placehold.co/400x600?text=Cover+12','/pdfs/p12.pdf','tiny-tales',12,12),
(13,'Thinking Deep','Philosophy essays',2003,'https://placehold.co/400x600?text=Cover+13','/pdfs/p13.pdf','thinking-deep',13,13),
(14,'Code Craft','Technology guide',2021,'https://placehold.co/400x600?text=Cover+14','/pdfs/p14.pdf','code-craft',14,14),
(15,'Graphic Panels','Comic collection',2019,'https://placehold.co/400x600?text=Cover+15','/pdfs/p15.pdf','graphic-panels',15,15);

-- FAVORITE_BOOK (composite keys user_id, book_id)
INSERT INTO favorite_book (user_id, book_id) VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15);

-- READING_LIST_BOOK
INSERT INTO reading_list_book (user_id, book_id, current_page, is_complete) VALUES
(1,1,12,false),(2,2,0,false),(3,3,150,true),(4,4,34,false),(5,5,0,false),(6,6,200,true),(7,7,5,false),(8,8,88,false),(9,9,0,false),(10,10,300,true),(11,11,2,false),(12,12,60,false),(13,13,0,false),(14,14,123,false),(15,15,0,false);

-- REVIEW
INSERT INTO review (user_id, book_id, rate, comment) VALUES
(1,1,5,'Excellent book'),(2,2,4,'Really enjoyed it'),(3,3,3,'Good but okay'),(4,4,5,'Loved it'),(5,5,2,'Not my taste'),
(6,6,4,'Well written'),(7,7,5,'Inspiring'),(8,8,3,'Mixed feelings'),(9,9,4,'Scary and good'),(10,10,5,'A classic'),
(11,11,4,'Nice poems'),(12,12,5,''),(13,13,3,'Thought-provoking'),(14,14,5,'Very useful'),(15,15,4,'Fun read');

-- QUIZ_QUESTION
INSERT INTO quiz_question (id, book_id, question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES
(1,1,'What is the main character''s name?','Anna','John','Luca','Mia','A'),
(2,2,'Where does the story take place?','Forest','City','Space','Island','A'),
(3,3,'Which year is the setting?','2000','2050','1990','2015','B'),
(4,4,'What theme predominates?','Love','War','Hate','Joy','A'),
(5,5,'What object is central?','Key','Map','Ring','Book','A'),
(6,6,'Which era is depicted?','Medieval','Modern','Ancient','Futuristic','C'),
(7,7,'Who is the subject?','Politician','Artist','Scientist','Explorer','B'),
(8,8,'What is the main advice?','Be brave','Be kind','Be smart','Be patient','B'),
(9,9,'What causes fear?','Silence','Noise','Light','Shadow','D'),
(10,10,'Where is the treasure?','Cave','Island','House','Ship','B'),
(11,11,'What style is used?','Free','Formal','Abstract','Rhymed','D'),
(12,12,'Who is the protagonist?','Child','Parent','Teacher','Animal','A'),
(13,13,'Which idea is central?','Existence','Economy','Science','Art','A'),
(14,14,'What is the main tool?','Laptop','Hammer','Pen','Camera','A'),
(15,15,'What format is the work?','Strip','Novel','Essay','Poem','A');

-- QUIZ_RESULT (composite keys)
INSERT INTO quiz_result (user_id, book_id, correct_answers, points, randon_answers_choosed) VALUES
(1,1,8,80,2),(2,2,6,60,3),(3,3,7,70,1),(4,4,9,90,0),(5,5,5,50,4),(6,6,10,100,0),(7,7,4,40,6),(8,8,6,60,2),(9,9,7,70,3),(10,10,8,80,2),(11,11,5,50,5),(12,12,9,90,1),(13,13,6,60,2),(14,14,10,100,0),(15,15,3,30,7);


