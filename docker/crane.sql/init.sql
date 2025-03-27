--#############################
--# Database creation ArtBase #
--#############################

-- Delete any pre-existing instance of the ArtBase database
connect postgres -- only psql

-- We used this query in pgAdmin to close active current session
/*
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'artbase' AND pid <> pg_backend_pid();
*/

DROP DATABASE IF EXISTS ArtBase;

-- Database Creation
CREATE DATABASE ArtBase ENCODING 'UTF-8';

-- Connect to the db
connect ArtBase --only psql

-- Create new Schema
DROP SCHEMA IF EXISTS artbase CASCADE;
CREATE SCHEMA artbase;

-- Create new domains:

-- Password domain
CREATE DOMAIN artbase.password AS VARCHAR(254)
CONSTRAINT properpassword CHECK (VALUE ~* '^[A-Za-z0-9.%!–,8]+$');

-- Email domain
CREATE DOMAIN artbase.emailaddress AS VARCHAR(254)
CONSTRAINT properemail CHECK (VALUE ~* '^[A-Za-z0-9.%]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$');

-- Score domain for reviews
CREATE DOMAIN artbase.Reviewscore AS SMALLINT
CONSTRAINT evaluatescore CHECK (VALUE >= 1 AND VALUE <= 5);

-- Generating uuid values requires a plug-in. In Postgres, a plug-in is
-- known as an extension. To install an extension, call CREATE EXTENSION. To
-- avoid re-installing, add IF NOT EXISTS.

-- The extension for uuids is an open-source library built in C: uuid-ossp.

-- When creating uuids, use function 'uuid_generate_v4' that generates an
-- uuid deriving it entirely from random numbers. See:
-- https://www.postgresql.org/docs/devel/uuid-ossp.html for details

-- We documented the creation of the uuid-ossp module,
-- even though we did not actually use it during the dataset population.
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- Create new data types:

-- User Roles
CREATE TYPE artbase.userRole AS ENUM (
    'artist',
    'artgallery',
    'genericuser',
    'businessuser'
);

-- Order Status
CREATE TYPE artbase.orderStatus AS ENUM (
    'pending',
    'awaiting payment',
    'awaiting shipment',
    'shipped',
    'delivered',
    'canceled',
    'declined',
    'disputed',
    'refunded'
);

-- Tags category
CREATE TYPE artbase.tagsCategory AS ENUM ('artpiece', 'artist', 'artgallery', 'event');

-- Tags
CREATE TYPE artbase.allTags AS ENUM (
    -- Art Piece Tags
    'classic', 'modern', 'abstract', 'realism', 'surrealism',
    'landscape', 'portrait', 'installation', 'digital', 'sculpture',
    -- Artist Tags
    'painter', 'sculptor', 'photographer', 'digital_artist', 'printmaker',
    'conceptual_artist', 'street_artist', 'ceramist', 'illustrator', 'multimedia_artist',
    -- Art Gallery Tags
    'contemporary', 'traditional', 'local', 'international', 'emerging_artists',
    'private', 'public', 'online', 'museum', 'cultural_center',
    -- Event Tags
    'exhibition', 'auction', 'workshop', 'artist_talk', 'art_fair',
    'gallery_opening', 'residency_program', 'live_performance', 'charity_event', 'vernissage'
);

-- Image extensions
CREATE TYPE artbase.imageExtension AS ENUM ('jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp');

-- ##############################################
-- ##			  TABLES CREATION:		        ##
-- ##############################################

-- 1. Create the LOCATION table
CREATE TABLE artbase.Location(
	Country VARCHAR(30),
	City VARCHAR(30),
	PostalCode VARCHAR(10),
	Address VARCHAR(254),
	PRIMARY KEY (Country, City, PostalCode, Address)
);

-- 2. Create the USER PROFILE table
CREATE TABLE artbase.UserProfile (
    "id" UUID PRIMARY KEY,
    ProfilePicture BYTEA,
    PictureExtension artbase.imageExtension,
   	"Name" VARCHAR(50),
    Surname VARCHAR(50),
    BrandName VARCHAR(50),
    BirthDate DATE NOT NULL, --FORSE
    RegistrationDate DATE NOT NULL,
    LocationCountry VARCHAR(30),
    LocationCity TEXT,
    LocationPostalCode VARCHAR(10),
    LocationAddress VARCHAR(254),
	FOREIGN KEY (LocationCountry, LocationCity, LocationPostalCode, LocationAddress)
        REFERENCES artbase.location (Country, City, PostalCode, Address)
);

-- 3. Create the LOGIN CREDENTIALS table
CREATE TABLE artbase.Credentials (
	UserId UUID PRIMARY KEY,
	Email artbase.emailaddress NOT NULL UNIQUE,
	"Password" artbase.password NOT NULL,
	Username VARCHAR(30) NOT NULL UNIQUE,
	FOREIGN KEY (UserId) REFERENCES artbase.UserProfile ("id")
);

-- 6. Create the ARTISTIC PROFILE table
CREATE TABLE artbase.ArtisticProfile (
	UserId UUID PRIMARY KEY,
	"Role" artbase.userRole NOT NULL,
	Biography TEXT NOT NULL,
	FollowerCount INTEGER CHECK (FollowerCount>=0) NOT NULL,
	NumPublishedArtPieces INTEGER NOT NULL,
	NumSoldArtPieces INTEGER NOT NULL,
	LastPubDate DATE,
	FOREIGN KEY (UserId) REFERENCES artbase.UserProfile("id")
);

-- 7. Create the ART PIECE table
CREATE TABLE artbase.ArtPiece (
    "id" UUID PRIMARY KEY,
    ArtisticUserId UUID NOT NULL,
    "Description" TEXT NOT NULL,
    "Weight" FLOAT NOT NULL,
    Title VARCHAR(50) NOT NULL,
    CreationDate DATE NOT NULL,
    DimensionWidth FLOAT NOT NULL,
    DimensionHeight FLOAT NOT NULL,
    DimensionLength FLOAT NOT NULL,
    UploadDate DATE NOT NULL,
    FOREIGN KEY (ArtisticUserId) REFERENCES artbase.ArtisticProfile (UserId)
);

-- 8. Create the ART PIECE ALBUM table
CREATE TABLE artbase.ArtPieceImage (
    ArtPieceId UUID,
    ImageIndex SMALLINT CHECK (ImageIndex > 0 AND ImageIndex <= 5),
    ImageData BYTEA NOT NULL,
    Extension artbase.imageExtension NOT NULL,
    PRIMARY KEY (ArtPieceId, ImageIndex)
    FOREIGN KEY (ArtPieceId) REFERENCES artbase.ArtPiece ("id")
);

-- 9. Create the ADVERTISEMENT table
CREATE TABLE artbase.Advertisement (
    ArtPieceId UUID PRIMARY KEY,
    Price NUMERIC(10, 2) NOT NULL,
    PublicationDate DATE NOT NULL,
    FOREIGN KEY (ArtPieceId) REFERENCES artbase.ArtPiece ("id")
);

-- 10. Create the FOLLOWS table
CREATE TABLE artbase.Follows (
    UserId_Follower UUID,
    ArtisticUserId_Followed UUID,
    FollowDate DATE NOT NULL,
    PRIMARY KEY (UserId_Follower, ArtisticUserId_Followed),
    FOREIGN KEY (UserId_Follower) REFERENCES artbase.UserProfile ("id"),
    FOREIGN KEY (ArtisticUserId_Followed) REFERENCES artbase.ArtisticProfile (UserId)
);

-- 11. Create the TAG table
CREATE TABLE artbase.Tag (
    "Name" artbase.allTags PRIMARY KEY,
	Category artbase.tagsCategory NOT NULL
);

-- 12. Create the HAS 1 table
CREATE TABLE artbase.Has1 (
    TagName artbase.allTags,
    ArtPieceId UUID,
    PRIMARY KEY (TagName, ArtPieceId),
    FOREIGN KEY (TagName) REFERENCES artbase.Tag ("Name"),
    FOREIGN KEY (ArtPieceId) REFERENCES artbase.ArtPiece ("id")
);

-- 13. Create the HAS 3 table
CREATE TABLE artbase.Has3 (
    TagName artbase.allTags,
    ArtisticUserId UUID,
    PRIMARY KEY (TagName, ArtisticUserId),
    FOREIGN KEY (TagName) REFERENCES artbase.Tag ("Name"),
    FOREIGN KEY (ArtisticUserId) REFERENCES artbase.ArtisticProfile (UserId)
);

-- 14. Create the CLIENT PROFILE table
CREATE TABLE artbase.ClientProfile (
	UserId UUID PRIMARY KEY,
	"Role" artbase.userRole NOT NULL,
	FOREIGN KEY (UserId) REFERENCES artbase.UserProfile("id")
);

-- 15. Create the REVIEWS 2 table
CREATE TABLE artbase.Reviews2 (
    ClientUserId UUID,
    ArtPieceId UUID,
    Score artbase.Reviewscore NOT NULL,
    ReviewDate DATE NOT NULL,
    PRIMARY KEY (ClientUserId, ArtPieceId),
    FOREIGN KEY (ClientUserId) REFERENCES artbase.ClientProfile (UserId),
    FOREIGN KEY (ArtPieceId) REFERENCES artbase.ArtPiece ("id")
);

-- 16. Create the ORDER DETAILS table
CREATE TABLE artbase.OrderDetails (
    "id" UUID PRIMARY KEY,
    ShippingPrice NUMERIC(10, 2) NOT NULL,
    Status artbase.orderStatus NOT NULL,
    Note VARCHAR(254),
    CreationDate DATE NOT NULL,
    LocationCountry_Shipment VARCHAR(30),
    LocationCity_Shipment TEXT,
    LocationPostalCode_Shipment VARCHAR(10),
    LocationAddress_Shipment VARCHAR(254),
    LocationCountry_Delivery VARCHAR(30),
    LocationCity_Delivery TEXT,
    LocationPostalCode_Delivery VARCHAR(10),
    LocationAddress_Delivery VARCHAR(254),
    FOREIGN KEY (LocationCountry_Shipment, LocationCity_Shipment, LocationPostalCode_Shipment, LocationAddress_Shipment)
		REFERENCES artbase.location (Country, City, PostalCode, Address),
    FOREIGN KEY (LocationCountry_Delivery, LocationCity_Delivery, LocationPostalCode_Delivery, LocationAddress_Delivery)
		REFERENCES artbase.location (Country, City, PostalCode, Address)
);

-- 17. Create the BUYS table
CREATE TABLE artbase.Buys (
    ClientUserId UUID,
    OrderId UUID,
    ArtPieceId UUID,
    PRIMARY KEY (ClientUserId, OrderId, ArtPieceId),
    FOREIGN KEY (ClientUserId) REFERENCES artbase.ClientProfile (UserId),
    FOREIGN KEY (OrderId) REFERENCES artbase.OrderDetails ("id"),
    FOREIGN KEY (ArtPieceId) REFERENCES artbase.Advertisement (ArtPieceId)
);

-- 21. Create the EVENT table
CREATE TABLE artbase.Event (
    "id" UUID PRIMARY KEY,
    ArtisticUserId_Organizer UUID NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Title VARCHAR(50) NOT NULL,
    Description TEXT NOT NULL,
    UploadDate DATE NOT NULL,
    FOREIGN KEY (ArtisticUserId_Organizer) REFERENCES artbase.ArtisticProfile (UserId)
);

-- 22. Create the PARTICIPATES table
CREATE TABLE artbase.Participates (
    EventId UUID,
    ArtisticUserId UUID,
    PRIMARY KEY (EventId, ArtisticUserId),
    FOREIGN KEY (EventId) REFERENCES artbase.Event ("id"),
    FOREIGN KEY (ArtisticUserId) REFERENCES artbase.ArtisticProfile (UserId)
);

-- 23. Create the EVENT ALBUM table
CREATE TABLE artbase.EventImage (
    EventId UUID,
    ImageIndex SMALLINT CHECK (ImageIndex > 0 AND ImageIndex <= 5),
    ImageData BYTEA NOT NULL,
    Extension artbase.imageExtension NOT NULL,
    PRIMARY KEY (EventId, ImageIndex)
    FOREIGN KEY (EventId) REFERENCES artbase.Event ("id")
);

-- 24. Create the HAS 2 table
CREATE TABLE artbase.Has2 (
    TagName artbase.allTags,
    EventId UUID,
    PRIMARY KEY (TagName, EventId),
    FOREIGN KEY (TagName) REFERENCES artbase.Tag ("Name"),
    FOREIGN KEY (EventId) REFERENCES artbase.Event ("id")
);

-- 25. Create the REVIEWS 1 table
CREATE TABLE artbase.Reviews1 (
    ClientUserId UUID,
    EventId UUID,
    Score artbase.Reviewscore NOT NULL,
    ReviewDate DATE NOT NULL,
    PRIMARY KEY (ClientUserId, EventId),
    FOREIGN KEY (ClientUserId) REFERENCES artbase.ClientProfile (UserId),
    FOREIGN KEY (EventId) REFERENCES artbase.Event ("id")
);
