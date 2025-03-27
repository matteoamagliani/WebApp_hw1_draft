-- ##############################################
-- ##	  TRIGGER FUNCTIONS IMPLEMENTATION	   ##	
-- ##############################################

-- 1) Create the function that checks if the user trying to sign in is of legal age
CREATE OR REPLACE FUNCTION artbase.check_age()
RETURNS TRIGGER AS $$
BEGIN
    IF (DATE_PART('year', AGE(NEW.RegistrationDate, NEW.BirthDate)) < 18) THEN
        RAISE EXCEPTION 'The user must be of legal age.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for check_age() function
CREATE TRIGGER check_age_trigger
BEFORE INSERT OR UPDATE OF BirthDate, RegistrationDate
ON artbase.UserProfile
FOR EACH ROW
EXECUTE FUNCTION artbase.check_age();



-- 2) Create the function that checks if the ArtisticProfile organizing an event is an art gallery
CREATE OR REPLACE FUNCTION artbase.check_organizer()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT "Role" FROM artbase.ArtisticProfile WHERE UserId = NEW.ArtisticUserId_Organizer) != 'artgallery' THEN
        RAISE EXCEPTION 'The organizer must be an art gallery.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for check_organizer function
CREATE TRIGGER check_organizer_trigger
BEFORE INSERT OR UPDATE OF ArtisticUserId_Organizer
ON artbase.Event
FOR EACH ROW
EXECUTE FUNCTION artbase.check_organizer();



-- 3) Create the function to check that a user can't be both client and artistic
CREATE OR REPLACE FUNCTION artbase.check_user_profile()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the user is an artistic profile
	IF EXISTS (SELECT 1 FROM artbase.ArtisticProfile WHERE UserId = NEW.UserId) THEN
        RAISE EXCEPTION 'The user is already an artistic profile.';
    END IF;

    -- Check if the user is a client profile
	IF EXISTS (SELECT 1 FROM artbase.ClientProfile WHERE UserId = NEW.UserId) THEN
        RAISE EXCEPTION 'The user is already a client profile.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for check_user_type function (ArtisticProfile)
CREATE TRIGGER check_user_type_trigger_artistic
BEFORE INSERT OR UPDATE OF UserId
ON artbase.ArtisticProfile
FOR EACH ROW
EXECUTE FUNCTION artbase.check_user_profile();

-- Creation of the trigger for check_user_type function (ClientProfile)
CREATE TRIGGER check_user_type_trigger_client
BEFORE INSERT OR UPDATE OF UserId
ON artbase.ClientProfile
FOR EACH ROW
EXECUTE FUNCTION artbase.check_user_profile();

-- 6) Create the function that stops artist profiles from following themselves
CREATE OR REPLACE FUNCTION artbase.check_self_follow()
RETURNS TRIGGER AS $$
BEGIN
	IF (NEW.UserId_Follower = NEW.ArtisticUserId_Followed) THEN
        RAISE EXCEPTION 'The user can''t follow himself.';
    END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation fo the trigger to check_self_follow() function
CREATE TRIGGER check_self_follow_trigger
BEFORE INSERT OR UPDATE OF UserId_Follower, ArtisticUserId_Followed
ON artbase.Follows
FOR EACH ROW
EXECUTE FUNCTION artbase.check_self_follow();

-- 7) Create the function that stops non-artists from uploading art pieces, participating to events and doing commissions
CREATE OR REPLACE FUNCTION artbase.check_artist()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT "Role" FROM artbase.ArtisticProfile WHERE UserId = NEW.ArtisticUserId) != 'artist' THEN
        RAISE EXCEPTION 'The artistic profile must be of an artist.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger to check_artist() function (ArtPiece)
CREATE TRIGGER check_artpiece_author_trigger
BEFORE INSERT OR UPDATE OF ArtisticUserId
ON artbase.ArtPiece
FOR EACH ROW
EXECUTE FUNCTION artbase.check_artist();

-- 10) Create the function to insert the location in location table if not present 
CREATE OR REPLACE FUNCTION artbase.check_and_insert_location()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the location is already present in the location table
    IF NOT EXISTS (
        SELECT 1
        FROM artbase.location
        WHERE Country = NEW.LocationCountry
          AND City = NEW.LocationCity
          AND PostalCode = NEW.LocationPostalCode
          AND Address = NEW.LocationAddress
    ) THEN
        -- Insert the new location in the location table
        INSERT INTO artbase.location (Country, City, PostalCode, Address)
        VALUES (NEW.LocationCountry, NEW.LocationCity, NEW.LocationPostalCode, NEW.LocationAddress);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for artbase.check_and_insert_location() function
CREATE TRIGGER trg_check_and_insert_location
BEFORE INSERT ON artbase.UserProfile
FOR EACH ROW
EXECUTE FUNCTION artbase.check_and_insert_location();



-- 11) Has 1 trigger: check tag belong to "artpiece" category 
CREATE OR REPLACE FUNCTION artbase.check_tag_category_has1() RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT Category FROM artbase.Tag WHERE "Name" = NEW.TagName) != 'artpiece' THEN
        RAISE EXCEPTION 'The tag must belong to the "artpiece" category.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_tag_category_has1_trigger
BEFORE INSERT OR UPDATE OF TagName
ON artbase.Has1
FOR EACH ROW
EXECUTE FUNCTION artbase.check_tag_category_has1();

-- 12) Has 3 trigger: check tag belong to "artist" or "artgallery" categories
CREATE OR REPLACE FUNCTION artbase.check_tag_category_has3() RETURNS TRIGGER AS $$
DECLARE
    art_role TEXT;
BEGIN
    SELECT "Role" INTO art_role FROM artbase.ArtisticProfile WHERE UserId = NEW.ArtisticUserId;
    IF art_role = 'artist' THEN
        IF (SELECT Category FROM artbase.Tag WHERE "Name" = NEW.TagName) != 'artist' THEN
            RAISE EXCEPTION 'The tag must belong to the "artist" category.';
        END IF;
    ELSIF art_role = 'artgallery' THEN
        IF (SELECT Category FROM artbase.Tag WHERE "Name" = NEW.TagName) != 'artgallery' THEN
            RAISE EXCEPTION 'The tag must belong to the "artgallery" category.';
        END IF;
    ELSE
        RAISE EXCEPTION 'The role is not valid.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_tag_category_has3_trigger
BEFORE INSERT OR UPDATE OF TagName
ON artbase.Has3
FOR EACH ROW
EXECUTE FUNCTION artbase.check_tag_category_has3();

-- 13) Has 2 trigger: check tag belong to "event" category 
CREATE OR REPLACE FUNCTION artbase.check_tag_category_has2() RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT CategorY FROM artbase.Tag WHERE "Name" = NEW.TagName) != 'event' THEN
        RAISE EXCEPTION 'The tag must belong to the "event" category.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_tag_category_has2_trigger
BEFORE INSERT OR UPDATE OF TagName
ON artbase.Has2
FOR EACH ROW
EXECUTE FUNCTION artbase.check_tag_category_has2();

-- ##########################################
-- ## DERIVED ATTRIBUTES TRIGGER FUNCTIONS ## 
-- ##########################################

-- 14) Create the function to update LastPubDate
CREATE OR REPLACE FUNCTION artbase.update_last_pub_date()
RETURNS TRIGGER AS $$
BEGIN
    -- Update LastPubDate in ArtisticProfile with the current date 
    UPDATE artbase.ArtisticProfile
    SET LastPubDate = NEW.UploadDate
    WHERE UserId = NEW.ArtisticUserId;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for artbase.update_last_pub_date() function
CREATE TRIGGER trg_update_last_pub_date
AFTER INSERT ON artbase.ArtPiece
FOR EACH ROW
EXECUTE FUNCTION artbase.update_last_pub_date();



-- 15) Create the function to increase the follower count each new follow
CREATE OR REPLACE FUNCTION increase_follower_count()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE artbase.ArtisticProfile
    SET FollowerCount = FollowerCount + 1
    WHERE UserId = NEW.ArtisticUserId_Followed;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for update_follower_count() function
CREATE TRIGGER increase_follower_count_trigger
AFTER INSERT 
ON artbase.Follows
FOR EACH ROW
EXECUTE FUNCTION increase_follower_count();



-- 16) Create the function to decrease the follower count when a follow is removed
CREATE OR REPLACE FUNCTION decrease_follower_count()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE artbase.ArtisticProfile
    SET FollowerCount = FollowerCount - 1
    WHERE UserId = OLD.ArtisticUserId_Followed;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for decrease_follower_count() function
CREATE TRIGGER decrease_follower_count_trigger
AFTER DELETE
ON artbase.Follows
FOR EACH ROW
EXECUTE FUNCTION decrease_follower_count();



-- 17) Create the function to increase the number of published art pieces when one is uploaded
CREATE OR REPLACE FUNCTION increase_published_artpieces()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE artbase.ArtisticProfile
    SET NumPublishedArtPieces = NumPublishedArtPieces + 1
    WHERE UserId = NEW.ArtisticUserId;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for increase_published_artpieces() function
CREATE TRIGGER increase_published_artpieces_trigger
AFTER INSERT
ON artbase.ArtPiece
FOR EACH ROW
EXECUTE FUNCTION increase_published_artpieces();



-- 18) Create the function to decrease the number of published art pieces when one is deleted
CREATE OR REPLACE FUNCTION decrease_published_artpieces()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE artbase.ArtisticProfile
    SET NumPublishedArtPieces = NumPublishedArtPieces - 1
    WHERE UserId = OLD.ArtisticUserId;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for decrease_published_artpieces() function
CREATE TRIGGER decrease_published_artpieces_trigger
AFTER DELETE
ON artbase.ArtPiece
FOR EACH ROW
EXECUTE FUNCTION decrease_published_artpieces();



-- 19) Create the function to increase the number of sold art pieces when one is sold
CREATE OR REPLACE FUNCTION increase_sold_artpieces()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE artbase.ArtisticProfile
    SET NumSoldArtPieces = NumSoldArtPieces + 1
    WHERE UserId = (
        SELECT ArtisticUserId
        FROM artbase.ArtPiece
        WHERE id = NEW.ArtPieceId
    );

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for increase_sold_artpieces() function
CREATE TRIGGER trigger_increase_sold_artpieces
AFTER INSERT
ON artbase.Buys
FOR EACH ROW
EXECUTE FUNCTION increase_sold_artpieces();



-- 20) Create the function to decrease the number of sold art pieces if the selling is cancelled
CREATE OR REPLACE FUNCTION decrease_sold_artpieces()
RETURNS TRIGGER AS $$
BEGIN
    -- Decrementa NumSoldArtPieces di 1 per l'utente artistico proprietario dell'opera
    UPDATE artbase.ArtisticProfile
    SET NumSoldArtPieces = NumSoldArtPieces - 1
    WHERE UserId = (
        SELECT ArtisticUserId
        FROM artbase.ArtPiece
        WHERE id = OLD.ArtPieceId
    );

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Creation of the trigger for decrease_sold_artpieces() function
CREATE TRIGGER trigger_decrease_sold_artpieces
AFTER DELETE
ON artbase.Buys
FOR EACH ROW
EXECUTE FUNCTION decrease_sold_artpieces();
