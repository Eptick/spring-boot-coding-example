DROP TRIGGER IF EXISTS audit_authors_updated_at on authors CASCADE;
DROP TRIGGER IF EXISTS audit_books_updated_at on books CASCADE;
DROP TABLE IF EXISTS authors_books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS reports;
-- CREATE EXTENSION IF NOT EXISTS isn; -- JPA does not agree well with this

/*
**DISCLAIMER**

UUID's are chosen because they provide a safe non predictable unique way
of identification

Text type instead of varchar is used because according to
https://www.postgresql.org/docs/13/datatype-character.html
There is no performance difference among these three types, apart from
increased storage space when using the blank-padded type, and a few extra
CPU cycles to check the length when storing into a length-constrained
column. While character(n) has performance advantages in some other database
systems, there is no such advantage in PostgreSQL; in fact character(n) is
usually the slowest of the three because of its additional storage costs.
In most situations text or character varying should be used instead.
*/

CREATE TABLE authors (
    author_id   uuid  DEFAULT gen_random_uuid(),
    /*
    A decision here was made to make the name into a single field rather split
    it into 2 or more field (first_name, middle_name, last_name).
    This decision was made because there are existing author that are
    mononymous, meaning they have only one name (Plato, Aristotle, Voltaire...
    One can argue that I could make only one of those not null and the rest of
    them nullable. I think that the decision of what that field should be could
    have consequences in the future.
    */
    name        TEXT NOT NULL, -- unique not applicable
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(author_id)
);

CREATE TABLE books (
    ISBN        TEXT NOT NULL,

    title       TEXT NOT NULL, -- unique not applicable
    genre       text NOT NULL, -- unique not applicable
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ISBN)
);

CREATE TABLE authors_books (
    author_id   uuid REFERENCES authors(author_id),
    book_isbn   TEXT REFERENCES books(ISBN),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(author_id, book_isbn)
);

CREATE TABLE reports (
    id                  int NOT NULL,
    last_execution_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(id)
);

CREATE INDEX ON authors ((lower(name)), created_at );
CREATE INDEX ON books ((lower(title)), created_at );

CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS '
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
' language 'plpgsql';

CREATE TRIGGER audit_authors_updated_at BEFORE UPDATE ON authors FOR EACH ROW
EXECUTE PROCEDURE update_updated_at();
CREATE TRIGGER audit_books_updated_at BEFORE UPDATE ON books FOR EACH ROW
EXECUTE PROCEDURE update_updated_at();