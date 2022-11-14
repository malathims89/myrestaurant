# Simple restaurant booking application in Java using muserver


## Project Setup

* **Clone the master** 
  or download as zip
* **Import it to your workspace as a Maven Project**


**Booting the Appliction:**  
**Muserver https://muserver.io/:** is a lightweight, modern web server . It is an excellent alternative to Spring boot to 
muserver by default runs on random ports unless you specify. This application runs on port 8080.
from your IDE , run the ##BookingApplication.java . This is the entry point


**How the use cases are approached**

**User Story 1** : As an Admin , I should be seeing the bookings for a given date
Once the server is up , hit http://localhost:8080/booking/listAllBookings/yyyy-mm-dd on your browser or any other API platform using the GET call.
Your response will contain a list of bookings for that particular date.

**User Story 2** : As a user , I should be able to book a table. Here , we pass a request body as an input ( Booking object) that contains the following fields

* *id (has an autoincrement logic after each insertion)*
* *bookingSize (table size)*
* *bookingDate*
* *bookingTime*

http://localhost:8080/booking/createBooking

{

    "bookingName": "Malathi",
    "bookingSize":"10",
    "bookingDate":"2020-12-10",
    "bookingTime":"08:43:20"
}

The response is a simple string with status code to notify the user whether the booking was successuful (or any apporpriate message)
**Added 200 OK**

## Additional Feature [BETA]

Since the restaurant would have tables *say family size , small , medium etc* based on the head count* , this was also introduced. (user would provide only the tableSize. The mapping happens internally).

