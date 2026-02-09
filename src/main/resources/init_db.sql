CREATE DATABASE IF NOT EXISTS freezervoir;

USE freezervoir;

CREATE TABLE freezer_items(
    item_ID VARCHAR(30) NOT NULL,
    date_added DATE NOT NULL,
    notes VARCHAR(255)
);


