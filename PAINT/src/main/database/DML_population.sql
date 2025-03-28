-- TODO: CHECK CORERENZA ESEGUENDO SU PGADMIN 
-- COMPLETARE tipo di dati BYTEA ?!?!

-- ###########################
-- ##  DATABASE POPULATION  ##
-- ###########################

-- Inserting data into the LOCATION table
INSERT INTO artbase.Location (Country, City, PostalCode, Address)
VALUES ('Italy', 'Rome', '00100', 'Via del Corso, 1'),
       ('Italy', 'Milan', '20100', 'Via Montenapoleone, 24'),
       ('Italy', 'Florence', '50100', 'Piazza del Duomo, 11'),
       ('Italy', 'Venice', '30100', 'Piazza San Marco, 29'),
       ('Italy', 'Naples', '80100', 'Via Toledo, 38'),
       ('Italy', 'Ancona', '60100', 'Corso Mazzini, 88'),
       ('Italy', 'Turin', '10100', 'Via Roma, 6'),
       ('Italy', 'Bologna', '40100', 'Via dell''Indipendenza, 44'),
       ('Italy', 'Genoa', '16100', 'Via XX Settembre, 9'),
       ('Italy', 'Bari', '70100', 'Corso Vittorio Emanuele II, 18'),
       ('Italy', 'Palermo', '90100', 'Via Maqueda, 91'),
       ('Italy', 'Verona', '37100', 'Piazza Bra, 16');

-- Inserting data into the USER PROFILE table 
-- (UUID-id: last 3 characters a.x.x)
-- a01 to a03 are generic users
-- a04 to a06 are artists
-- a07 to a09 are art galleries
-- a10 to a12 are business users
INSERT INTO artbase.UserProfile (id, ProfilePicture, "Name", Surname, BrandName, BirthDate, RegistrationDate, LocationCountry, LocationCity, LocationPostalCode, LocationAddress)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'https://www.example.com/profile_picture.jpg', 'Mario', 'Rossi', NULL, '1990-01-01', '2024-01-01', 'Italy', 'Rome', '00100', 'Via del Corso, 1'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'https://www.example.com/profile_picture.jpg', 'Luca', 'Agostini', NULL, '2000-02-15', '2024-09-14', 'Italy', 'Milan', '20100', 'Via Montenapoleone, 24'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03', 'https://www.example.com/profile_picture.jpg', 'Filippo', 'Neri', NULL, '1976-01-01', '2023-04-20', 'Italy', 'Florence', '50100', 'Piazza del Duomo, 11'),          
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', 'https://www.example.com/profile_picture.jpg', 'Luigi', 'Salvadori', NULL, '1987-06-06', '2021-02-03', 'Italy', 'Venice', '30100', 'Piazza San Marco, 29'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05', 'https://www.example.com/profile_picture.jpg', 'Antonella', 'Bianchi', NULL, '1998-02-01', '2021-04-05', 'Italy', 'Naples', '80100', 'Via Toledo, 38'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06', 'https://www.example.com/profile_picture.jpg', 'Franco', 'Ruta', NULL, '1964-05-25' , '2018-06-15', 'Italy', 'Ancona', '60100', 'Corso Mazzini, 88'),              
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07', 'https://www.example.com/profile_picture.jpg', NULL, NULL, 'Quantum Frost', '1965-11-12' , '2021-11-12', 'Italy', 'Turin', '10100', 'Via Roma, 6'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08', 'https://www.example.com/profile_picture.jpg', NULL, NULL, 'Apex Glide', '1966-10-11', '2018-01-15', 'Italy', 'Bologna', '40100', 'Via dell''Indipendenza, 44'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09', 'https://www.example.com/profile_picture.jpg', NULL, NULL, 'Vortex Pulse', '1945-01-05', '2019-02-16', 'Italy', 'Genoa', '16100', 'Via XX Settembre, 9'),       
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10', 'https://www.example.com/profile_picture.jpg', NULL, NULL, 'Orion Bloom', '1987-05-05', '2019-02-17', 'Italy', 'Bari', '70100', 'Corso Vittorio Emanuele II, 18'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'https://www.example.com/profile_picture.jpg', NULL, NULL, 'Echo Sphere', '1984-03-08', '2022-02-18', 'Italy', 'Palermo', '90100', 'Via Maqueda, 91'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'https://www.example.com/profile_picture.jpg', NULL, NULL, 'New Opera', '1966-07-11', '2020-02-04', 'Italy', 'Verona', '37100', 'Piazza Bra, 16');

-- Inserting data into the CREDENTIALS table
INSERT INTO artbase.Credentials (UserId, Email, "Password", Username)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'mario.rossi@gmail.com', 'mariorossi123', 'mario.rossi'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'luca.agostini@hotmail.com', 'lucaagostini123', 'luca.agostini'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03', 'filippo.neri@outlook.com', 'filipponeri123', 'filippo_neri'), 
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', 'luigi.salvadori@libero.it', 'luigisalvadori123', 'luigi_salvadori'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05', 'antonella.bianchi@outlook.it', 'antonellabianchi123', 'antonellabianchi'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06', 'franco.ruta@gmail.it', 'FrancoRuta123', 'Franco_Ruta'),       
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07', 'administration@quantumfrost.com', 'quantumfrost789', 'QuantumFrost'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08', 'administration@apexglide.com', 'apexglide789', 'ApexGlide'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09', 'administration@vortexpulse.com', 'vortexpulse789', 'VortexPulse'),       
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10', 'administration@orionbloom.com', 'orionbloom789', 'OrionBloom'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'administration@echosphere.com', 'echosphere789', 'EchoSphere'),
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'administration@newopera.com','newopera789', 'NewOpera');

-- Inserting data into the ARTISTIC PROFILE table
INSERT INTO artbase.ArtisticProfile (UserId, "Role", Biography, FollowerCount, NumPublishedArtPieces, NumSoldArtPieces, LastPubDate)
VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', 'artist', 
  'Luigi Salvadori is an Italian artist born in Venice in 1987. 
  His art focuses on painting and sculpture, exploring the interaction between light and shadow. 
  His passion for the city of Venice is evident in every piece of his work.', 
  3, 1, 1, '2023-04-10'),  
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05', 'artist', 
  'Antonella Bianchi is a young artist from Naples, born in 1998. 
  Her art expresses emotions through vibrant colors and stylized figures, reflecting her connection to Mediterranean culture.', 
  2, 1, 1, '2022-12-15'),
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06', 'artist', 
  'Franco Ruta is a veteran artist from Ancona, born in 1964. His works blend tradition and modernity, 
  addressing themes related to nature and society, with a strong social commitment.', 
  10, 4, 0, NULL), 
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07', 'artgallery', 
  'Quantum Frost is an innovative art gallery in Turin, founded in 2021. 
  It stands out for showcasing contemporary art that explores the interaction between technology and artistic creativity.', 
  1, 0, 0, NULL),  
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08', 'artgallery', 
  'Apex Glide is an art gallery in Bologna, established in 2018, that promotes emerging young artists 
  with a focus on modern and avant-garde artistic techniques, aiming to attract an international audience.', 
  0, 0, 0, NULL),  
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a09', 'artgallery', 
  'Vortex Pulse is an art gallery in Genoa, founded in 2019, known for its dynamic exhibitions that blend 
  traditional art with innovative concepts. It seeks to inspire a new generation of art enthusiasts.', 
  0, 0, 0, NULL);

-- Inserting data into the ART PIECE table
-- (UUID-id: last 3 characters b.x.x)
INSERT INTO artbase.ArtPiece (id, ArtisticUserId, "Description", "Weight", Title, CreationDate, DimensionWidth, DimensionHeight, DimensionLength, UploadDate)
VALUES 
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', 
 'The great painting is a masterpiece by Luigi Salvadori, depicting the vibrant essence of Venice. 
 This large canvas combines intricate details with bold brushstrokes, capturing the iconic cityscape at sunset.',
  2.5, 'The great painting', '2022-07-29', 6.3, 2.1, 15, '2023-09-01'),
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05', 
 'Heavy-metal is a bold and striking sculpture by Antonella Bianchi, made from a mix of metal and resin. 
 Created in 2001, it weighs 96.12kg and explores themes of power, industrialization, and sound.',
  96.12, 'Heavy-metal', '2001-02-01', 25.56, 12.3, 5.2, '2024-10-10');

-- Inserting data into the ART PIECE IMAGE table
INSERT INTO artbase.ArtPieceImage (ArtPieceId, Pointer)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b01', 'https://www.example.com/pointer.jpg'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b02', 'https://www.example.com/pointer.jpg');

-- Inserting data into the ADVERTISEMENT table
INSERT INTO artbase.Advertisement (ArtPieceId, Price, PublicationDate)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b01', 89.99, '2023-12-01'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b02', 2500.00, '2024-12-10');

-- Inserting data into the FOLLOWS table
INSERT INTO artbase.Follows (UserId_Follower, ArtisticUserId_Followed, FollowDate)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', '2024-10-06'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', '2024-09-22'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', '2023-05-21'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05', '2021-07-01'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05', '2022-02-25'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07', '2022-06-06');

-- Inserting data into the TAG table
INSERT INTO artbase.Tag ("Name", Category)
VALUES ('classic', 'artpiece'), ('modern', 'artpiece'), ('abstract', 'artpiece'), ('realism', 'artpiece'), ('surrealism', 'artpiece'), 
       ('landscape', 'artpiece'), ('portrait', 'artpiece'), ('installation', 'artpiece'), ('digital', 'artpiece'), ('sculpture', 'artpiece'),
       ('painter', 'artist'), ('sculptor', 'artist'), ('photographer', 'artist'), ('digital_artist', 'artist'), ('printmaker', 'artist'), 
       ('conceptual_artist', 'artist'), ('street_artist', 'artist'), ('ceramist', 'artist'), ('illustrator', 'artist'), ('multimedia_artist', 'artist'),
       ('contemporary', 'artgallery'), ('traditional', 'artgallery'), ('local', 'artgallery'), ('international', 'artgallery'), ('emerging_artists', 'artgallery'), 
       ('private', 'artgallery'), ('public', 'artgallery'), ('online', 'artgallery'), ('museum', 'artgallery'), ('cultural_center', 'artgallery'),
       ('exhibition', 'event'), ('auction', 'event'), ('workshop', 'event'), ('artist_talk', 'event'), ('art_fair', 'event'), 
       ('gallery_opening', 'event'), ('residency_program', 'event'), ('live_performance', 'event'), ('charity_event', 'event'), ('vernissage', 'event');

-- Inserting data into the HAS 1 table
INSERT INTO artbase.Has1 (TagName, ArtPieceId)
VALUES ('abstract', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b01'),
       ('modern', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b01'),
       ('classic', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b02');

-- Inserting data into the HAS 3 table
INSERT INTO artbase.Has3 (TagName, ArtisticUserId)
VALUES ('painter', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'),
       ('sculptor', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'),
       ('conceptual_artist', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04');

-- Inserting data into the CLIENT PROFILE table
INSERT INTO artbase.ClientProfile (UserId, "Role")
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'genericuser'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'genericuser'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03', 'genericuser'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10', 'businessuser'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'businessuser'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'businessuser');

-- Inserting data into the REVIEWS 2 table
INSERT INTO artbase.Reviews2 (ClientUserId, ArtPieceId, Score, ReviewDate)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b01', 4, '2024-05-15'), 
	     ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b02', 3, '2024-12-15'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b02', 4, '2024-12-15');

-- Inserting data into the ORDER DETAILS table
-- (UUID-id: last 3 characters c.x.x)
INSERT INTO artbase.OrderDetails (id, ShippingPrice, Status, Note, CreationDate, LocationCountry_Shipment, LocationCity_Shipment, LocationPostalCode_Shipment, LocationAddress_Shipment, LocationCountry_Delivery, LocationCity_Delivery, LocationPostalCode_Delivery, LocationAddress_Delivery)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380c01', 5.0, 'pending', 'The street number is 29/A', '2024-03-10', 'Italy', 'Venice', '30100', 'Piazza San Marco, 29', 'Italy', 'Rome', '00100', 'Via del Corso, 1'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380c02', 0.0, 'delivered', NULL, '2024-12-12', 'Italy', 'Naples', '80100', 'Via Toledo, 38', 'Italy', 'Milan', '20100', 'Via Montenapoleone, 24'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380c03', 0.0, 'delivered', NULL, '2024-12-02', 'Italy', 'Naples', '80100', 'Via Toledo, 38', 'Italy', 'Milan', '20100', 'Via Montenapoleone, 24');

-- Inserting data into the BUYS table
INSERT INTO artbase.Buys (ClientUserId, OrderId, ArtPieceId)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380c01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b01'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380c02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380b02');

-- Inserting data into the EVENT table
-- (UUID-id: last 3 characters e.x.x)
INSERT INTO artbase.Event (id, ArtisticUserId_Organizer, StartDate, EndDate, Title, Description, UploadDate)
VALUES 
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a07', '2024-01-01', '2024-12-10', 
 'The Art of Quantum Expression', 
 'A year-long exhibition featuring innovative works by various artists inspired by the concept of quantum physics and the exploration of new visual expressions. 
 This event, organized by Quantum Frost.', '2023-12-01'),
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a08', '2023-06-01', '2023-08-31', 
 'Sculptural Innovations: Modern Forms', 
 'A summer event curated by Apex Glide, focusing on the exploration of modern sculpture. The exhibition brings together contemporary sculptors to showcase 
 innovative works that push the boundaries of form, material, and meaning.', '2023-05-15');

-- Inserting data into the PARTICIPATES table
INSERT INTO artbase.Participates (EventId, ArtisticUserId)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a04'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a05');

-- Inserting data into the EVENT IMAGE table
INSERT INTO artbase.EventImage (EventId, Pointer)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e01', 'https://www.example.com/pointer.jpg'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e02', 'https://www.example.com/pointer.jpg');

-- Inserting data into the HAS 2 table
INSERT INTO artbase.Has2 (TagName, EventId)
VALUES ('exhibition', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e01'),
       ('auction', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e02');

-- Inserting data into the REVIEWS 1 table
INSERT INTO artbase.Reviews1 (ClientUserId, EventId, Score, ReviewDate)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e01', 5, '2024-11-05'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380e02', 3, '2023-08-12');
