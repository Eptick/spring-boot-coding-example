insert into authors values ('0096d201-d4f2-43ed-9973-dbf77e9b06d9', '1. Plato', default, default);
insert into authors values ('a26c3451-8809-4fe4-a5d4-c2270bfa0b90', '2. Voltaire', default, default);
insert into authors values ('f991ff57-e897-48d2-a299-e524b9ef3047', '3. Homer', default, default);
insert into authors values ('0498e926-f7c7-4a21-9655-bad190c04d29', '4. Confucius', default, default);
insert into authors values ('35ecf6e8-9216-4e67-a3ea-66ecd2d19a66', '5. Miroslav Krleža', default, default);


insert into books values('99921-58-10-7', 'Book 1', 'Genre 1', default, default );
insert into books values('9971-5-0210-0', 'Book 2', 'Genre 2', default, default );
insert into books values('960-425-059-0', 'Book 3', 'Genre 3', default, default );
insert into books values('80-902734-1-6', 'Book 4', 'Genre 4', default, default );
insert into books values('85-359-0277-5', 'Book 5', 'Genre 5', default, default );

CREATE OR REPLACE FUNCTION getAuthorId(name_param text) RETURNS uuid AS '
    BEGIN
        return (select author_id from authors where name = name_param);
    END;
' LANGUAGE plpgsql;

insert into authors_books values (
    getAuthorId('1. Plato'),
    '99921-58-10-7'
);
insert into authors_books values (
    getAuthorId('2. Voltaire'),
    '960-425-059-0'
);
insert into authors_books values (
    getAuthorId('3. Homer'),
    '960-425-059-0'
);
insert into authors_books values (
    getAuthorId('2. Voltaire'),
    '80-902734-1-6'
);
insert into authors_books values (
    getAuthorId('4. Confucius'),
    '85-359-0277-5'
);
