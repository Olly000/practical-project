INSERT INTO bands(band_name, genre, year_formed) VALUES
('tortoise', 'post-rock', 1990),
('the sea and cake', 'jazz-rock', 1994),
('slint', 'indie-rock',  1986),
('isotope 217', 'jazz', 1997),
('the breeders', 'indie-rock', 1989);

INSERT INTO recordings(title, band_id, label, release_year) VALUES
('millions now living will never die', 1, 'thrill jockey', 1996),
('tnt', 1, 'thrill jockey', 1999),
('one bedroom', 2, 'thrill jockey', 2003),
('spiderland', 3, 'touch and go', 1991),
('the unstable molecule', 4, 'thrill jockey', 1997);

INSERT INTO musicians(full_name, instrument) VALUES
('John McIntyre', 'drums'),
('David Pajo', 'guitar'),
('Sam Prekop', 'guitar'),
('Kim Deal', 'vocals');

INSERT INTO played_on(musician_id, recording_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(2, 4);