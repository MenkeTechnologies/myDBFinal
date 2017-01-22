/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jacobmenke
 * Created: Dec 12, 2016
 */

create table ElectronicsCollection (
name varchar(20),
onFile varchar(40),
offFile varchar(40),
dateAdded DateTime,
id int Primary Key Auto_Increment
);

insert into ElectronicsCollection values('redLED', '/home/pi/Desktop/python/redLED.py','/home/pi/Desktop/python/redLEDOff.py',NOW(),0);

create Table LearningCollection (
    -> category varchar(20),
    -> learning text,
    -> dateAdded DateTime,
    -> id int Primary Key Auto_Increment
    -> );

+-----------+-------------+------+-----+---------+----------------+
| Field     | Type        | Null | Key | Default | Extra          |
+-----------+-------------+------+-----+---------+----------------+
| category  | varchar(20) | YES  |     | NULL    |                |
| learning  | text        | YES  |     | NULL    |                |
| dateAdded | datetime    | YES  |     | NULL    |                |
| id        | int(11)     | NO   | PRI | NULL    | auto_increment |
+-----------+-------------+------+-----+---------+----------------+


describe LearningCollection;
+-----------+-------------+------+-----+---------+----------------+
| Field     | Type        | Null | Key | Default | Extra          |
+-----------+-------------+------+-----+---------+----------------+
| category  | varchar(20) | YES  |     | NULL    |                |
| learning  | text        | YES  |     | NULL    |                |
| dateAdded | datetime    | YES  |     | NULL    |                |
| id        | int(11)     | NO   | PRI | NULL    | auto_increment |
+-----------+-------------+------+-----+---------+----------------+



insert into LearningCollection values('Programming', 'CSS background-size: cover', NOW(),0);