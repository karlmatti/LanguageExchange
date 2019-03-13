INSERT INTO user VALUES (
'114005839740106632529',
'Russian',22,'Finnish',
'I would like to improve my Finnish. I have studied Finnish for 1 and half years but I would like to get better at this.',
'Estonian;English','Karl',
'Fishing;Programming',
'Matti',
'/img/114005839740106632529.PNG'

);

INSERT INTO friends VALUES (
1, '114005839740106632529', '55555555555555555555555',
);
INSERT INTO friends VALUES (
2, '111111111111111111111', '114005839740106632529',
);
INSERT INTO friends VALUES (
3, '00000000000000000', '22222222222222222222',
);


/* Json  example
[
  {
    "id": 0,
    "googleId": 3,
    "firstName": "Karl",
    "lastName": "Matti",
    "cLvlLangs": "Estonian;English",
    "bLvlLangs": "Finnish",
    "aLvlLangs": "Russian",
    "hobbies": "Fishing;Programming",
    "photoLocation": "/img/114005839740106632529.PNG"
  }
]
*/