insert into authors values (default, '1. Plato', default, default);
insert into authors values (default, '2. Voltaire', default, default);
insert into authors values (default, '3. Homer', default, default);
insert into authors values (default, '4. Confucius', default, default);
insert into authors values (default, '5. Miroslav Krleža', default, default);


insert into books values(ISBN13('99921-58-10-7'), 'Book 1', 'Genre 1', default, default );
insert into books values(ISBN13('9971-5-0210-0'), 'Book 2', 'Genre 2', default, default );
insert into books values(ISBN13('960-425-059-0'), 'Book 3', 'Genre 3', default, default );
insert into books values(ISBN13('80-902734-1-6'), 'Book 4', 'Genre 4', default, default );
insert into books values(ISBN13('85-359-0277-5'), 'Book 5', 'Genre 5', default, default );

insert into authors_books values (
    (select author_id from authors where name = '1. Plato'),
    '99921-58-10-7'
);
insert into authors_books values (
    (select author_id from authors where name = '2. Voltaire'),
    '960-425-059-0'
);
insert into authors_books values (
    (select author_id from authors where name = '2. Voltaire'),
    '80-902734-1-6'
);
insert into authors_books values (
    (select author_id from authors where name = '4. Confucius'),
    '85-359-0277-5'
);
