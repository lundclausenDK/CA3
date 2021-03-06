# CA3

Group 3: 
- Benjamin Løvig Rasmussen
- Joachim Henvig Hansen 
- Mikkel Lund Clausen 
- Peter Leonhardt Rasmussen(Minimekill)

## Backend feature list:

The server is able to create and hand out security tokens to frontend users to make sure that the server can recognise
users and admins.

The server is able to hand requests to return data. Data is returned by the rest end points in the package "rest". The login rest endpoint is located in the security package. 
Following actions can be done by the rest endpoints:
- register a new user "api/register"
- login an existing user "api/login"
- get all places existing in the database "api/places"
- get all users existing in the database "api/users"
- insert new place in the database "api/upload/place"
- add a picture to the database "api/upload/picture"
- add/delete/edit users "api/user_control/(add/delete/edit)"

all rest endpoints retrieves and insert their data into the sql via the facades interface located in the package "facades".


## Web frontend feature list: 

- a non user can register on the page and become a member.

- logging in as admin (username: user3 password: test) and clicking on the adminpage will show all current users in the system, and do the following actions:
	- add a user
	- edit a user
	- delete a user
- non users can see all places without being logged in, by clicking places in the top bar.

- the information about the places includes:
	
	- name
	- short description
	
	- address
	
	- gps coordinates (GEO)
	
	- rating
	
	- an image of the place

	- it is possible to sort through the places after name and after rating (both ascending and descending). 

	- searching through the places by entering part of the name of the place.

- a user can click on "places" in the top menu and rate a place
- a user can insert a new place using the "add a new place" in the top menu


## App frontend feature list:
- a non user can see all places



## Future of this project:

web/app

- as a user i would like to be able to book a place from the list of places and cancel bookings. (8 hours)
- as a user i would like to see a list of bookings made by me. (2 hours)
- as a user i would like to delete/edit bookings made by me. (2 hours)
- as a user i would like to be able to leave a comment on a place and edit and delete this comment. (4 hours)
- as a user i would like to be able to read comments from other users on a place. (2 hours)
- as a user i would like to put pictures on a place. (2 hours)
- as a user i would like to delete my own pictures on a place. (1 hours)
- as a user i would like to see all pictures users has put on a place. (1 hours)
- as a user i would like to put pictures with GEO location on a place. (2 hours)
- as a user i would like to see a place with pictures from other users and geo locations and see how far theese pictures are taken from  from the place. (3 hours)
- as a user i would like if there was a online chat/message system to talk with other users/admin. (16 hours)
- as a user i would like that the web/app looks good and is easy to use. (4 hours)
- as a user i would like to see events on a place (such as festivals). (3 hours)
- as a user i would like to put events on a place. (3 hours)

53 hours

web

- as an admin i would like to be able to edit/delete any place. (2 hours)
- as an admin i would like to see a list of bookings on any given place in the system. (2 hours)
- as an admin i would like to delete/edit bookings on any given place in the system. (2 hours)
- as an admin i would like to see all pictures on a place and edit/delete theese. (3 hours)
- as an admin i would like to see users and admins currently online. (8 hours)
- as an admin i would like to be able to message users and other admins. (2 hours)

19 hours

total: 72 hours.
Since our teams velocity is not determined yet, no more user stories will be added until several sprints are completed, and the velocity that we add user stories is learned.

## Manual: how to use the system!

go to the website https://designcookies.dk/#/. The only element you can interact with is the top menu.
- Places

Without logging in you can already click on places and see the current places in the systems database. Here you can either search for a place using the search bar and clicking submit. You are also able to sort the list of places by clicking the "Sort on name" button, which will sort the list alphabetically. Clicking "Sort on rating" will sort the places according to their current raiting, reclicking this button will shift the ascending and descending order of how the places are sorted.

- Signup

As a new user u can register on the system by clicking "signup" which is located on the right on the topbar. The new page that you are taken to has 2 input fields where you write a username and a password that you wish to have. Press Register and you are taken back to the front page but you can now see that you are logged in in the top bar.

- Login

If you already have a user, you can login by clicking the "login" on the right side of the top bar. The new page yhat you are taken to has 2 input fields where you insert your username and your password (user2,test). Pressing login will take you back to front page but you can now see that you are logged in in the top bar.

- Add new place

If you are logged in you can see in the top menu that theres a link "Add new place", press this.
The page is showing 5 user inputs where you can specify a place that you wish to add to the system. Pressing Submit will save your new place to the server.

- Rating

If you are logged, click the "places" in the top menu. A list of places stored in the system is shown. Each place has a dropdown menu, where you can choose how much you wanna rate a place.

- Admin tools

Logging in with as a admin ("admin","test") you now have acces to a "users page", link located in the top menu. From here you can add, edit delete and promote users. Promoting users will promote them to admins.


## Who did what

Who made what?:

- Joachim Henvig Hansen: Java, REACT, REACT Native, rest, JPA
- Benjamin Løvig Rasmussen: Java, REACT, rest, JPA
- Peter Leonhardt Rasmussen: Java, REACT, rest
- Mikkel Lund Clausen: Java, REACT, REACT Native, rest, JPA


link to website
https://designcookies.dk/#/
