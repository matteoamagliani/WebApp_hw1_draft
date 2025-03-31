--#############################
--# Database creation PAINT #
--#############################

-- connect to the default database postgres
-- connect postgres -- only psql

-- We used this query in pgAdmin to close active current session
/*
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'paint' AND pid <> pg_backend_pid();
*/

-- Drop the database if it exists
-- DROP DATABASE IF EXISTS paintdb;

-- Create user "PAINT_owner" with superuser privileges
--DROP ROLE IF EXISTS "PAINT_owner";

CREATE ROLE "PAINT_owner" WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  REPLICATION
  BYPASSRLS
  PASSWORD 'paint2425';

-- Database Creation
CREATE DATABASE paintdb OWNER PAINT_owner ENCODING 'UTF-8';

-- Connect to the db
connect paintdb --only psql

-- Create new Schema
DROP SCHEMA IF EXISTS paint CASCADE;
CREATE SCHEMA paint;

-- Create new domains:

-- Password domain
CREATE DOMAIN paint.password AS VARCHAR(254)
CONSTRAINT properpassword CHECK (VALUE ~* '^[A-Za-z0-9.%!–,8]+$');

-- Email domain
CREATE DOMAIN paint.emailaddress AS VARCHAR(254)
CONSTRAINT properemail CHECK (VALUE ~* '^[A-Za-z0-9.%]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$');

-- Score domain for reviews
CREATE DOMAIN paint.Reviewscore AS SMALLINT
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
CREATE TYPE paint.userRole AS ENUM (
    'artist',
    'artgallery',
    'genericuser',
    'businessuser'
);

-- Order Status
CREATE TYPE paint.orderStatus AS ENUM (
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
CREATE TYPE paint.tagsCategory AS ENUM ('artpiece', 'artist', 'artgallery', 'event');

-- Tags
CREATE TYPE paint.allTags AS ENUM (
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
CREATE TYPE paint.imageExtension AS ENUM ('jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp');

-- ##############################################
-- ##			  TABLES CREATION:		        ##
-- ##############################################

-- 1. Create the LOCATION table
CREATE TABLE paint.Location(
	Country VARCHAR(30),
	City VARCHAR(30),
	PostalCode VARCHAR(10),
	Address VARCHAR(254),
	PRIMARY KEY (Country, City, PostalCode, Address)
);

-- 2. Create the USER PROFILE table
CREATE TABLE paint.UserProfile (
    "id" UUID PRIMARY KEY,
    ProfilePicture BYTEA,
    PictureExtension paint.imageExtension,
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
        REFERENCES paint.location (Country, City, PostalCode, Address)
);

-- 3. Create the LOGIN CREDENTIALS table
CREATE TABLE paint.Credentials (
	UserId UUID PRIMARY KEY,
	Email paint.emailaddress NOT NULL UNIQUE,
	"Password" paint.password NOT NULL,
	Username VARCHAR(30) NOT NULL UNIQUE,
	FOREIGN KEY (UserId) REFERENCES paint.UserProfile ("id")
);

-- 6. Create the ARTISTIC PROFILE table
CREATE TABLE paint.ArtisticProfile (
	UserId UUID PRIMARY KEY,
	"Role" paint.userRole NOT NULL,
	Biography TEXT NOT NULL,
	FollowerCount INTEGER CHECK (FollowerCount>=0) NOT NULL,
	NumPublishedArtPieces INTEGER NOT NULL,
	NumSoldArtPieces INTEGER NOT NULL,
	LastPubDate DATE,
	FOREIGN KEY (UserId) REFERENCES paint.UserProfile("id")
);

-- 7. Create the ART PIECE table
CREATE TABLE paint.ArtPiece (
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
    FOREIGN KEY (ArtisticUserId) REFERENCES paint.ArtisticProfile (UserId)
);

-- 8. Create the ART PIECE ALBUM table
CREATE TABLE paint.ArtPieceImage (
    ArtPieceId UUID,
    ImageIndex SMALLINT CHECK (ImageIndex > 0 AND ImageIndex <= 5),
    ImageData BYTEA NOT NULL,
    Extension paint.imageExtension NOT NULL,
    PRIMARY KEY (ArtPieceId, ImageIndex),
    FOREIGN KEY (ArtPieceId) REFERENCES paint.ArtPiece ("id")
);

-- 9. Create the ADVERTISEMENT table
CREATE TABLE paint.Advertisement (
    ArtPieceId UUID PRIMARY KEY,
    Price NUMERIC(10, 2) NOT NULL,
    PublicationDate DATE NOT NULL,
    FOREIGN KEY (ArtPieceId) REFERENCES paint.ArtPiece ("id")
);

-- 10. Create the FOLLOWS table
CREATE TABLE paint.Follows (
    UserId_Follower UUID,
    ArtisticUserId_Followed UUID,
    FollowDate DATE NOT NULL,
    PRIMARY KEY (UserId_Follower, ArtisticUserId_Followed),
    FOREIGN KEY (UserId_Follower) REFERENCES paint.UserProfile ("id"),
    FOREIGN KEY (ArtisticUserId_Followed) REFERENCES paint.ArtisticProfile (UserId)
);

-- 11. Create the TAG table
CREATE TABLE paint.Tag (
    "Name" paint.allTags PRIMARY KEY,
	Category paint.tagsCategory NOT NULL
);

-- 12. Create the HAS 1 table
CREATE TABLE paint.Has1 (
    TagName paint.allTags,
    ArtPieceId UUID,
    PRIMARY KEY (TagName, ArtPieceId),
    FOREIGN KEY (TagName) REFERENCES paint.Tag ("Name"),
    FOREIGN KEY (ArtPieceId) REFERENCES paint.ArtPiece ("id")
);

-- 13. Create the HAS 3 table
CREATE TABLE paint.Has3 (
    TagName paint.allTags,
    ArtisticUserId UUID,
    PRIMARY KEY (TagName, ArtisticUserId),
    FOREIGN KEY (TagName) REFERENCES paint.Tag ("Name"),
    FOREIGN KEY (ArtisticUserId) REFERENCES paint.ArtisticProfile (UserId)
);

-- 14. Create the CLIENT PROFILE table
CREATE TABLE paint.ClientProfile (
	UserId UUID PRIMARY KEY,
	"Role" paint.userRole NOT NULL,
	FOREIGN KEY (UserId) REFERENCES paint.UserProfile("id")
);

-- 15. Create the REVIEWS 2 table
CREATE TABLE paint.Reviews2 (
    ClientUserId UUID,
    ArtPieceId UUID,
    Score paint.Reviewscore NOT NULL,
    ReviewDate DATE NOT NULL,
    PRIMARY KEY (ClientUserId, ArtPieceId),
    FOREIGN KEY (ClientUserId) REFERENCES paint.ClientProfile (UserId),
    FOREIGN KEY (ArtPieceId) REFERENCES paint.ArtPiece ("id")
);

-- 16. Create the ORDER DETAILS table
CREATE TABLE paint.OrderDetails (
    "id" UUID PRIMARY KEY,
    ShippingPrice NUMERIC(10, 2) NOT NULL,
    Status paint.orderStatus NOT NULL,
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
		REFERENCES paint.location (Country, City, PostalCode, Address),
    FOREIGN KEY (LocationCountry_Delivery, LocationCity_Delivery, LocationPostalCode_Delivery, LocationAddress_Delivery)
		REFERENCES paint.location (Country, City, PostalCode, Address)
);

-- 17. Create the BUYS table
CREATE TABLE paint.Buys (
    ClientUserId UUID,
    OrderId UUID,
    ArtPieceId UUID,
    PRIMARY KEY (ClientUserId, OrderId, ArtPieceId),
    FOREIGN KEY (ClientUserId) REFERENCES paint.ClientProfile (UserId),
    FOREIGN KEY (OrderId) REFERENCES paint.OrderDetails ("id"),
    FOREIGN KEY (ArtPieceId) REFERENCES paint.Advertisement (ArtPieceId)
);

-- 21. Create the EVENT table
CREATE TABLE paint.Event (
    "id" UUID PRIMARY KEY,
    ArtisticUserId_Organizer UUID NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Title VARCHAR(50) NOT NULL,
    Description TEXT NOT NULL,
    UploadDate DATE NOT NULL,
    FOREIGN KEY (ArtisticUserId_Organizer) REFERENCES paint.ArtisticProfile (UserId)
);

-- 22. Create the PARTICIPATES table
CREATE TABLE paint.Participates (
    EventId UUID,
    ArtisticUserId UUID,
    PRIMARY KEY (EventId, ArtisticUserId),
    FOREIGN KEY (EventId) REFERENCES paint.Event ("id"),
    FOREIGN KEY (ArtisticUserId) REFERENCES paint.ArtisticProfile (UserId)
);

-- 23. Create the EVENT ALBUM table
CREATE TABLE paint.EventImage (
    EventId UUID,
    ImageIndex SMALLINT CHECK (ImageIndex > 0 AND ImageIndex <= 5),
    ImageData BYTEA NOT NULL,
    Extension paint.imageExtension NOT NULL,
    PRIMARY KEY (EventId, ImageIndex),
    FOREIGN KEY (EventId) REFERENCES paint.Event ("id")
);

-- 24. Create the HAS 2 table
CREATE TABLE paint.Has2 (
    TagName paint.allTags,
    EventId UUID,
    PRIMARY KEY (TagName, EventId),
    FOREIGN KEY (TagName) REFERENCES paint.Tag ("Name"),
    FOREIGN KEY (EventId) REFERENCES paint.Event ("id")
);

-- 25. Create the REVIEWS 1 table
CREATE TABLE paint.Reviews1 (
    ClientUserId UUID,
    EventId UUID,
    Score paint.Reviewscore NOT NULL,
    ReviewDate DATE NOT NULL,
    PRIMARY KEY (ClientUserId, EventId),
    FOREIGN KEY (ClientUserId) REFERENCES paint.ClientProfile (UserId),
    FOREIGN KEY (EventId) REFERENCES paint.Event ("id")
);
