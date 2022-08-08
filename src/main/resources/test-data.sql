INSERT INTO bands(name, genre, year_formed, active)
VALUES
("Tortoise", "post-rock", 1990, 1),
("The Sea and Cake", "jazz rock", 1994, 1),
("Slint", "indie rock",  1986, 0),
("Isotope 217", "jazz", 1997, 0);

INSERT INTO records(title, band_id, label, release_year)
VALUES
("Millions now living will never die", 1, "Thrill Jockey", 1996),
("One Bedroom", 2, "Thrill Jockey", 2003),
("Spiderland", 3, "Touch and Go", 1991),
("The Unstable Molecule", 4, "Thrill Jockey", 1997);

INSERT INTO musicians(surname, forename, instrument)
VALUES
("McIntyre", "John", "drums"),
("Pajo", "David", "guitar"),
("Prekop", "Sam", "guitar");
