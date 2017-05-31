++++++++++++++ A LITTLE GUIDE THROUGH THE SYSTEM ++++++++++++++

The users having access to the desktop application are the administrator and the mechanic of the Nordic Motorhomes
company. The user should run the application directly from the Java IDE, like IntelliJ, by pressing run or ALT+SHIFT+F10.
The application should be opened after this.
He will see the login page. The first thing the user should do is selecting from the check box if he wants to log in as
the administrator or the mechanic. Just click on the field under “Please log in as” and click on the type of user. Then,
he should write into the “Username” field, his given username and in the “Password” field the password. The “Log in”
button should be pressed afterwards. If the user fails to login, a pop up window will appear saying “Text invalid log in”.

After logging in as an administrator, the user will see 5 tabs. The first tab “Customers” contain information about
the customers. The user can not modify anything from this table. He can order the customers by id or by any other
field just by clicking on each column name. The “Reservation” tab contain fields for reservations. The user can add
a new reservation by filling all the fields accordingly. He should not fill the “id” text field, as it will be auto
completed. For “bike”, “picnic”, “child seat”, “Is it paid?”, “Fuel level over half?” and “Mechanic’s approval” fields,
there must entered the values 1 or 0. 1 if it’s true/yes and 0 if it’s false/no. For example, if the customer wants to
have a child seat in his motorhome, the administrator should write 1 in the “child seat” field. The user should leave
the “Mechanic’s comments” field empty. After entering this information about the reservation, the “Create” button should
be pressed. The fields will become empty. This means the creation was successful. The “Save button” will be further
explained.

The “Reservations” tab contains a table with all the attributes of a reservation from the database. The table lets you
order everything by the column. If you want to have the reservations ordered ascending by the starting date for example,
you should press on the “start date”. Double click on the same column if you want to have them sorted descending.
For using the “Edit” button, a reservation should be selected. The user should click on the reservation he wants to
modify and then click on the “Edit” button. Nothing will happen at a first glance. In order to modify the reservation
selected, the “Reservation” tab was filled with the information about that specific reservation. So, the user should
press on the “Reservation” tab and he will see all the fields completed. He should modify the field desired. The “Id”
field will be left untouched. The fields that contain the words “true” and “false” should be all modified and completed
with 1 or 0. 1 if the field contain “true” and 0 if “false”. After modifying the desired fields and making sure “bike”,
“picnic”, “child seat”, “Is it paid?”, “Fuel level over half?” and “Mechanic’s approval” contain the numbers 1 or 0,
the “Save” button will be pressed and the reservations will be modified. For deleting a reservation, an item from
“Reservations” table should be clicked. When the “Delete” button is pressed as well, that reservation dissapears. 

The “Type of vehicles” tab has a table with the id, the brand and the capacity of a type of vehicle. The columns
can again be sorted ascending or descending by clicking on the column’s name. To create a new type of vehicle,
the fields “Brand” and “Capacity” will be filled. The first one should have words and the second one should have numbers.
After writing the information about the new type of vehicle, the button “Add” should be pressed. The fields should
appear empty afterwards, if adding went well. The table is updated instantly. For deleting entries, the item from
the table should be clicked and then the “Delete” button should be pressed. 

The “Vehicles” tab’s table is about the vehicles and has information about them. If you want to add a new one,
you should fill the form on the left side, making sure you write in the “Type” field the id of the “type of vehicle”
and in the “Booked?” field 1 or 0, 1 - if the vehicle is booked, 0 - if not. For editing an entry, you should select
the desired item and then press “Edit”. The information about the vehicle will appear in the fields on the left.
After modifying the desired fields, you should make sure the “Booked?” field contain 1 or 0 and the “Type” and “Price”
fields an integer. For deleting items from the table, the user should select them and then press the “Delete” button.

As a logged in mechanic, you can see all the reservations. For approving a reservation, an item from the table should
be selected and the “Approve reservation” button should be pressed. If it is already approved, it won’t modify it.
For writing a comment about a reservation, an item from the table should be selected and then the comment should be
written in the text field. The “Write Comment” button will be pressed after this.

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
