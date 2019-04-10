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
1, '1.txt', true,'100388202069890344398', '114005839740342106632529'
);
INSERT INTO friends VALUES (
2, '2.txt', true, '100388202069890344398', '231111123123123'
);
INSERT INTO friends VALUES (
3, '3.txt', true, '100388202069890344398', '22222222222222222222'
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