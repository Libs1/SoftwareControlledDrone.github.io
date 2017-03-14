---
csl: apa.csl
bibliography: SCDCitations.bib
---

Software Controlled Drone Project
=================================

Team Members: Kevin Libdan & Denis Stepanov

Project’s website: https://github.com/Libs1/SoftwareControlledDrone.github.io

Discipline: Computer Engineering Technology

Date of Submission: TBD

Declaration of Joint Authorship
===============================

The authorship of this project is evenly divided between Denis Stepanov and
Kevin Libdan. Denis Stepanov is responsible for handling the android application
which will be used to control the drone from an android device. He is also
responsible for creating the design aspect of the android application (this
includes all the layouts, animations, languages e.t.c). Kevin Libdan is
responsible for creating the database which will be used to communicate with the
android application and store information such as user information and drone
information (flight duration, date and location). Denis Stepanov and Kevin
Libdan will both be responsible for the hardware aspect. The hardware will allow
the Eachine H8 drone be controlled by two analog joysticks connected to an
Arduino and an android application which will have two virtual joysticks. Denis
Stepanov and Kevin Libdan will also be responsible for the web interface. The
web interface will mimic the same functionalities as the android application
which will allow the users to register, login and view their drone’s flight
information.

 

Approved Proposal
=================

 

*Proposal for the Software Controlled Drone Project*

Prepared by Denis Stepanov and Kevin Libdan  
*Computer Engineering Technology Student*  
github.com/libs1/softwarecontrolleddrone.github.io

**Executive Summary**

As students in the Computer Engineering Technology program, We will be
integrating the knowledge and skills we have learned from our program into this
Internet of Things themed capstone project. This proposal requests the approval
to build the hardware portion that will connect to a database as well as to a
mobile device application. The internet connected hardware will include a custom
PCB with sensors and actuators for controlling a drone. The database will store
information on flight control. The mobile device functionality will include
software support for controlling the flight of the drone and will be further
detailed in the mobile application proposal. We will be collaborating with the
following company/department Future Humber Quadcopter Swarm. The hardware will
be completed in CENG 317 Hardware Production Techniques independently and the
application will be completed in CENG 319 Software Project. These will be
integrated together in the current term in CENG 355 Computer Systems Project as
a member of a 2 student group.

**Background**

The problem solved by this project is to control a drone remotely with an
application built for android devices. The problem is to recognize the radio
signal used to communicate with the drone and make the android application to
send proper flight instructions to the drone.

Drone technology is fairly new and it’s becoming popular day by day. It has
become popular enough so that an organization like Amazon is testing product
delivery by drone but controlling manual or automated flight path of drones is
difficult and requires dedicated controllers. Controllers can sometimes be more
expensive or non-intuitive to use. In this situation widely popular can make the
task more fun, intuitive and widely accessible. Android applications are easily
obtainable and can have very intuitive user interface. In addition android
devices are ubiquitous. So having proper android application for controlling the
drones has very high potential to make drones more popular and user friendly.

I have searched for prior art via Humber’s IEEE subscription selecting “My
Subscribed Content” and have found and read three articles which provides
insight into similar efforts.

The first article discusses how UAVS have been getting a lot of attention due to
its low cost of implementation and how an AR drone is being controlled by a
motion capture system to follow a moving target. [@7786340]

The second article discusses how to collect input information for the controller
used for the AR drone. [@6852167]

The third article discusses estimating UAV systems total ownership cost
including hardware components, software design, and operations. [@6496852]

In the Computer Engineering Technology program we have learned about the
following topics from the respective relevant courses:

-   Java Docs from CENG 212 Programming Techniques In Java,

-   Construction of circuits from CENG 215 Digital And Interfacing Systems,

-   Rapid application development and Gantt charts from CENG 216 Intro to
    Software Engineering,

-   Micro computing from CENG 252 Embedded Systems,

-   SQL from CENG 254 Database With Java,

-   Web access of databases from CENG 256 Internet Scripting; and,

-   Wireless protocols such as 802.11 from TECH152 Telecom Networks.

This knowledge and skill set will enable me to build the subsystems and
integrate them together as my capstone project.

**Methodology**

This proposal is assigned in the first week of class and is due at the beginning
of class in the second week of the winter semester. My coursework will focus on
the first two of the 3 phases of this project:  
Phase 1 Hardware build.  
Phase 2 System integration.  
Phase 3 Demonstration to future employers.

*Phase 1 Hardware build*

The hardware build will be completed in the fall term. It will fit within the
CENG Project maximum dimensions of 12 13/16" x 6" x 2 7/8" (32.5cm x 15.25cm x
7.25cm) which represents the space below the tray in the parts kit. The highest
AC voltage that will be used is 16Vrms from a wall adaptor from which +/- 15V or
as high as 45 VDC can be obtained. Maximum power consumption will be 20 Watts.

*Phase 2 System integration*

The system integration will be completed in the fall term.

*Phase 3 Demonstration to future employers*

This project will showcase the knowledge and skills that I have learned to
potential employers.

The tables below provide rough effort and non-labour estimates respectively for
each phase. A Gantt chart will be added by week 3 to provide more project
schedule details and a more complete budget will be added by week 4. It is
important to start tasks as soon as possible to be able to meet deadlines.

| **Labour Estimates**                                                                      | **Hrs**        | **Notes**                                                                                                                      |
|-------------------------------------------------------------------------------------------|----------------|--------------------------------------------------------------------------------------------------------------------------------|
| **Phase 1**                                                                               |                |                                                                                                                                |
| Writing proposal.                                                                         | 9              | Tech identification quiz.                                                                                                      |
| Creating project schedule. Initial project team meeting.                                  | 9              | Proposal due.                                                                                                                  |
| Creating budget. Status Meeting.                                                          | 9              | Project Schedule due.                                                                                                          |
| Acquiring components and writing progress report.                                         | 9              | Budget due.                                                                                                                    |
| Mechanical assembly and writing progress report. Status Meeting.                          | 9              | Progress Report due (components acquired milestone).                                                                           |
| PCB fabrication.                                                                          | 9              | Progress Report due (Mechanical Assembly milestone).                                                                           |
| Interface wiring, Placard design, Status Meeting.                                         | 9              | PCB Due (power up milestone).                                                                                                  |
| Preparing for demonstration.                                                              | 9              | Placard due.                                                                                                                   |
| Writing progress report and demonstrating project.                                        | 9              | Progress Report due (Demonstrations at Open House Saturday, November 7, 2015 from 10 a.m. - 2 p.m.).                           |
| Editing build video.                                                                      | 9              | Peer grading of demonstrations due.                                                                                            |
| Incorporation of feedback from demonstration and writing progress report. Status Meeting. | 9              | 30 second build video due.                                                                                                     |
| Practice presentations                                                                    | 9              | Progress Report due.                                                                                                           |
| 1st round of Presentations, Collaborators present.                                        | 9              | Presentation PowerPoint file due.                                                                                              |
| 2nd round of Presentations                                                                | 9              | Build instructions up due.                                                                                                     |
| Project videos, Status Meeting.                                                           | 9              | 30 second script due.                                                                                                          |
| **Phase 1 Total**                                                                         | **135**        |                                                                                                                                |
| **Phase 2**                                                                               |                |                                                                                                                                |
| Meet with collaborators                                                                   | 9              | Status Meeting                                                                                                                 |
| Initial integration.                                                                      | 9              | Progress Report                                                                                                                |
| Meet with collaborators                                                                   | 9              | Status Meeting                                                                                                                 |
| Testing.                                                                                  | 9              | Progress Report                                                                                                                |
| Meet with collaborators                                                                   | 9              | Status Meeting                                                                                                                 |
| Meet with collaborators                                                                   | 9              | Status Meeting                                                                                                                 |
| Incorporation of feedback.                                                                | 9              | Progress Report                                                                                                                |
| Meet with collaborators                                                                   | 9              | Status Meeting                                                                                                                 |
| Testing.                                                                                  | 9              | Progress Report                                                                                                                |
| Meet with collaborators                                                                   | 9              | Status Meeting                                                                                                                 |
| Prepare for demonstration.                                                                | 9              | Progress Report                                                                                                                |
| Complete presentation.                                                                    | 9              | Demonstration at Open House Saturday, April 9, 2016 10 a.m. to 2 p.m.                                                          |
| Complete final report. 1st round of Presentations.                                        | 9              | Presentation PowerPoint file due.                                                                                              |
| Write video script. 2nd round of Presentations, delivery of project.                      | 9              | Final written report including final budget and record of expenditures, covering both this semester and the previous semester. |
| Project videos.                                                                           | 9              | Video script due                                                                                                               |
| **Phase 2 Total**                                                                         | **135**        |                                                                                                                                |
| **Phase 3**                                                                               |                |                                                                                                                                |
| Interviews                                                                                | TBD            |                                                                                                                                |
| **Phase 3 Total**                                                                         | **TBD**        |                                                                                                                                |
| **Material Estimates**                                                                    | **Cost**       | **Notes**                                                                                                                      |
| **Phase 1**                                                                               |                |                                                                                                                                |
| Arduino Uno R3                                                                            | \>\$30.95      | (Arduino) Amazon                                                                                                               |
| SparkFun Transceiver Breakout - nRF24L01                                                  | \>\$29.37      | CanadaRobotix                                                                                                                  |
| Eachine H8 Mini Quadcopter                                                                | \>\$28.99      | (EachineDirect) Amazon                                                                                                         |
| Lithium AA Batteries                                                                      | \>\$5.64       | CanadianTire (1 pack comes with 4 AA batteries)                                                                                |
| Analog Joysticks                                                                          | \>\$32.02      | Brainy-Bits (2 analog sticks)                                                                                                  |
|                                                                                           |                |                                                                                                                                |
| **Phase 1 Total**                                                                         | **\>\$115.33** |                                                                                                                                |
| **Phase 2**                                                                               |                |                                                                                                                                |
| Materials to improve functionality, fit, and finish of project.                           |                |                                                                                                                                |
| **Phase 2 Total**                                                                         | **TBD**        |                                                                                                                                |
| **Phase 3**                                                                               |                |                                                                                                                                |
| Off campus colocation                                                                     | \<\$100.00     |                                                                                                                                |
| *Shipping*                                                                                | *TBD*          |                                                                                                                                |
| *Tax*                                                                                     | *TBD*          |                                                                                                                                |
| *Duty*                                                                                    | *TBD*          |                                                                                                                                |
| **Phase 3 Total**                                                                         | **TBD**        |                                                                                                                                |

**Concluding remarks**

This proposal presents a plan for providing an IoT solution for *Future Humber
Quadcopter Swarm*. This is an opportunity to integrate the knowledge and skills
developed in our program to create a collaborative IoT capstone project
demonstrating my ability to learn how to support projects. I request approval of
this project.

 

Abstract
========

This project is aimed to manipulate the same binding signal that the drone used
with the stock controller and then being able to control the drone remotely with
an application built for android devices. The android application would also
allow users to register if they do not have an account, sign in and view their
drone’s flight information. We started this project by building a piece of
hardware that would allow us to communicate with the drone and also being able
to fly it. The major components of our hardware are an Arduino Uno R3, an
NRF24l01 wireless transceiver, and two analog joysticks. Developing the
application, database, and web interface allowed us to utilize with our
hardware.

 

Table of Contents
=================

[Declaration of Joint Authorship](#declaration-of-joint-authorship)

[Proposal](#approved-proposal)

[Abstract](#abstract)

[Illustrations or Diagrams](#illustrations-or-diagrams)

1.  [Introduction](#1-introduction)

    [1.1 Purpose](#11-purpose)

    [1.2 Scope](#12-scope)

2.  [Project Description](#2-project-description)

    [2.1 Build Instructions](#21-build-instructions)

    [2.1.1 Introduction](#211-introduction)

    [2.1.2 Build of Materials/Budget](#212-build-of-materialsbudget)

    [2.1.3 Time Commitment](#213-time-commitment)

    [2.1.4 Mechanical Assembly](#214-mechanical-assembly)

    [2.1.5 Power Up](#215-power-up)

    [2.1.6 Unit Testing](#216-unit-testing)

    [2.2 External Interface Requirements](#22-external-interface-requirements)

    [2.2.1 Database](#221-database)

    [2.2.2 Web Interface](#222-web-interface)

    [2.2.3 Hardware](#223-hardware)

    [2.2.4 Application](#224-application)

    [2.3 Project Specifications](#23-project-specifications)

    [2.3.1 Project Perspective](#231-project-perspective)

    [2.3.2 System Interface](#232-system-interface)

    [2.3.3 User Interface](#233-user-interface)

    [2.3.4 Hardware Interface](#234-hardware-interface)

    [2.3.5 Software Interface](#235-software-interface)

    [2.3.6 Communication Interface](#236-communication-interface)

    [2.4 Progress Reports](#24-progress-reports)

3.  [Conclusion](#3-conclusion)

4.  [Recommendations](#4-recommendations)

5.  [Bibliography](#5-bibliography)

 

**Illustrations or Diagrams**
-----------------------------

 

**1. Introduction**
-------------------

### 1.1 Purpose

Drone technology is fairly new and it’s becoming popular day by day. It has
become popular enough that organizations like Amazon are testing product
delivery by drones, but controlling manual or automated flight path of drones is
difficult and requires dedicated controllers. Controllers can sometimes be more
expensive or non-intuitive to use. Android applications are easily obtainable
and can have very intuitive user interface and in addition android devices are
ubiquitous. For this reason, having a proper android application that would be
able to control drones would increase the popularity for drones. The Software
Controlled Drone project is designed to manipulate the binding sequence and as
well as controlling the Eachine H8 mini quadcopter drone. This project is
focused on creating hardware and an android application that will be capable of
controlling the drone. This Software Controlled Drone (SCD) project will have a
hardware component, an android application, a database and a web interface.

 

### 1.2 Scope

The Software Controlled Drone project will be able to control the Eachine H8
mini quadcopter drone. The android application will have the capability of
controlling drone remotely by using two virtual analog sticks in the controller
activity and allowing the users to view their drone’s flight information such as
the duration of the flight and as well as the date of which the drone has been
flown. The database will contain the user’s information such as their first and
last name, username and password. The database will also store information of
the drone’s flight information that will be displayed to the user. The web
interface will allow users to review their drone’s flight information.

 

**2. Project Description**
--------------------------

**2.1 Build Instructions**
--------------------------

### 2.1.1 Introduction

In this section of the technical report, there will be information on how to
recreate the hardware to control the Eachine H8 Mini Quadcopter with an Arduino
and an Android device, which will use an NRF24L01 transceiver to communicate
with the drone.

 

### 2.1.2 Build of Materials/Budget

| **Item**                                 | **Quantity**                   | **Total Price(With Tax + Shipping)** |
|------------------------------------------|--------------------------------|--------------------------------------|
| Arduino Uno R3                           | 1                              | \$30.95                              |
| SparkFun Transceiver Breakout - nRF24L01 | 1                              | \$29.37                              |
| Eachine H8 Mini Quadcopter               | 1                              | \$28.99                              |
| AA Batteries                             | 1 pack(containing 4 batteries) | \$5.64                               |
| BreadBoard                               | 1                              | \$12.99                              |
| Pin Header (8-pin) Male                  | 1                              | \$0.50                               |
| Jumper Wires                             | 40 pack                        | \$9.03                               |
| Analog Joysticks                         | 2                              | \$36.02                              |
| HC-05 Bluetooth Module                   | 1                              | \$18.65                              |

 

### 2.1.3 Time Commitment

| **Task**                                                | **Time To Complete The Task**                 |
|---------------------------------------------------------|-----------------------------------------------|
| Ordering Parts                                          | 3 Days(Shipping)                              |
| Soldering the pin header to the NRF24L01 Transceiver    | 20 minutes                                    |
| Wiring the NRF24L01 transceiver to the Arduino          | 5 minutes                                     |
| Wiring the joysticks to the Arduino                     | 5 minutes                                     |
| Downloading the Arduino IDE                             | 10 minutes(Depending on computer Performance) |
| Importing the NRF24L01\_Multipro library to the Arduino | 2 minutes                                     |
| Running the code                                        | 5 minutes                                     |

 

### 2.1.4 Mechanical Assembly

**Step 1: Purchase the required parts**

 

**Step 2: Preparing the NRF24L01**

Solder the pin header (8-pin) into the pins indicated with a green dot as shown
on Figure 1.1. The pin headers will act as legs for the transceiver when you
place the transceiver on the breadboard. Soldering the pin headers to the pins
of the transceiver may be challenging due to the size of the pins and as well
for individuals who are unexperienced in soldering.

Figure 1.1

![nrf24l01 solder.png](https://github.com/Libs1/SoftwareControlledDrone.github.io/blob/master/Images/nrf24l01%20solder.png)

**Step 3: Hooking up the NRF24L01 to the Arduino**

**           ** Before we make any wiring connections between the Arduino and
the NRF24L01 transceiver, make sure the transceiver is sitting on the breadboard
properly. When the transceiver is in place, make the following connections
between the Arduino and the NRF24L01 transceiver. Figure 2.1 shows how the
connections should look when all connections are made.

This is the pin set up for the Arduino to the NRF24L01 Transceiver:

5v -\> VCC

GND -\> GND

MOSI -\> 3

SCK -\> 4

CE -\> 5

MISO -\> A0

CSN -\> A1

Figure 2.1

![nrf24l01 hookup.png](https://github.com/Libs1/SoftwareControlledDrone.github.io/blob/master/Images/nrf24l01%20hookup.png)

**Step 4: Hooking up the joysticks to the Arduino**

Make the following connections to hook up both analog sticks to the Arduino.
Figure 3.1 shows the connections made between the two analog joysticks and the
Arduino. Whichever joystick you set up to A2 and A3 on the Arduino will be your
left joystick as it will control the throttle (up and down movement of the
drone) and rudder (left rotate and right rotate of the drone). The other
joystick, which will be your right joystick, will control the Aileron (leftward
and rightward movement of the drone) and elevator (forward and backward movement
of the drone).

**Left Joystick to Arduino:**

X -\> A2

Y-\> A3

VCC -\> 5v

GND -\> GND

**Right Joystick to Arduino:**

X -\> A4

Y -\> A5

VCC -\> 5v

GND -\> GND

Figure 3.1

![joystick hookup.png](https://github.com/Libs1/SoftwareControlledDrone.github.io/blob/master/Images/joystick%20hookup.png)

### 2.1.5 Power Up

Make sure that all connections are in the appropriate pins and that there are
not any loose connections. Once the circuit has been built, plug in the Arduino
into the PC and open the Arduino IDE. Plug in the power to the Eachine H8 drone
and the LED lights should be blinking in a steady pace. Now that we have the
drone powered on, you can download the zip file which contains the Arduino code
that will communicate with the drone. You can download it from
[here](https://github.com/Libs1/SoftwareControlledDrone.github.io/tree/master/Arduino%20Code).
The original code can be found on Goebish’s github which is found
[here](https://github.com/goebish/nrf24_multipro). Our code has been modified to
be compatible with our two analog joysticks. Go back to your Arduino IDE and
open up the “nrf24l01\_multipro.ino” file and then upload the sketch to the
Arduino. Once the code is uploaded, the drone’s LEDs should be blinking rapidly,
this indicates that the drone is ready to bind. At this point, moving your left
joystick down should bind the drone and the drone’s LEDs should be steady.

 

### 2.1.6 Unit Testing

Ensure that the drone’s LEDs would be steady as it would indicate a successful
binding sequence when the left joystick is moved. If the binding sequence was
not successful, it would most likely be a hardware problem. It is important to
check that all of the connections made between the NRF24L01, joysticks and the
Arduino are made properly and secured. Another test is to ensure that the
joysticks are controlling the drone as it is supposed to. The throttle (vertical
movement) of the left joystick would move the drone up and down; the rudder
(horizontal movement) of the left joystick would right and left rotate the
drone, the elevator (vertical movement) of the right joystick would move the
drone forward and backwards and the Aileron (horizontal movement) of the right
joystick would move the drone leftward and rightward.

 

**2.2 External Interface Requirements**
---------------------------------------

### 2.2.1 Database

The MYSQL database is responsible for storing the users account information and
as well as the drone’s activity information. The database uses PHP in order for
it to connect with the android application and as well, the database will be
running on hostinger which is a free hosting website. There are two tables
within the database; the first table is the DroneMembers table which contains
the user’s information and the second table which is the DroneInfo table which
contains information of the drone’s activity. The DroneMembers table contains
four fields which are first name, last name, username and password. The first
name and last name fields contain the user’s first name and last name. The
username field contains the user’s username which is used for logging in. The
password field contains the user’s password which is used when logging in. The
DroneInfo field contains two fields which are date and flight duration. The date
field is contains the date of when the drone has been flown and the flight
duration field contains the user’s total flight time. (Developed by Kevin
Libdan)

 

### 2.2.2 Web Interface

Depending the how long the binding process takes we will develop the web
interface. The web interface will be developed to allow users to register an
account. The website will also be capable of allowing the users to view their
drone’s information such the date of when the drone has been flown and as well
as the flight duration. The website will handle basic functionality such as
viewing the user’s drone runtime information. Denis Stepanov is responsible for
the HTML, CSS and the overall design aspect of the website. Kevin Libdan will be
responsible for creating the PHP scripts that will be used to connect to the
database when a user registers an account, sign in and view their drone’s
information. (Developed by Denis Stepanov and Kevin Libdan)

 

### 2.2.3 Hardware

The hardware built is used to control the Syma X12s drone. The hardware to
control the Syma X12s is an Arduino, a NRF24L01+ transceiver and two analog
joysticks. As for now, we are still having trouble trying to bind the Syma X12s
drone with our hardware. One of the main resources to helping us find a solution
to this problem is Goebish. Goebish is responsible for creating source code for
multiple protocols that support the NRF24L01+ After contacting Goebish, we have
tried to bind our drone with his source code by modifying it to force select the
Syma X12s protocol and as well as modifying the code to bind the drone with the
two analog sticks. With multiple attempts to bind the drone with our transceiver
and back and forth communication with Goebish, we have concluded that there was
not anything wrong with the code itself but the hardware. The protocol we were
trying to bind our drone with was the Syma X12 protocol (compatible with X5C-1,
X11, X11C) when we had a Syma X12s drone. We were informed that Syma X12s may be
using a different protocol than the Syma X12 protocol while the source code does
not support the Syma X12s. We were suggested to get a different drone, Eachine
H8 mini which is compatible with the code as it has the supported protocol.
Kevin Libdan is responsible for acquiring the materials and as well as the
assembly of the materials. Denis Stepanov is responsible for creating the
Arduino sketch that would be compatible with the hardware.(Developed by Denis
Stepanov and Kevin Libdan) 

 

### 2.2.4 Application

Software Drone Android Application is the software used to bind with the
quadcopter, maneuver it and save data in MYSQL database using the readings from
the drone's flight, implemented in one of the classes. Android Application
consists of 10 different java classes which support multiple functionalities.
Main classes, which would be visible to users, such as
'ControllerActivity','FlightsActivity','LoginActivity','MenuActivity',
'RegisterActivity' and 'PopActivity' use specific layouts assigned to them. Each
one of those activities' layouts support phone, tablet, and large tablet (\>10")
size of the screen. All of visible activities have the function to adjust to
both portrait and landscape mode (except 'ControllerActivity', which is always
set on landscape to create more user-friendly interface). All of the main
classes use custom 'drawables' to make the application more presentable. There
are also 5 other classes which are used for better functionality of the
application. All of those 5 classes have communication in some sort with
database. For example, 'TableData' class is consists of the information about
the drone flight (date and flight duration).Moreover, the application adjusts to
different resolutions of images and icons for different devices automatically
using Android Asset. There is also a support for 4 different languages (English,
French, Spanish, and Russian). (Developed by Denis Stepanov)

 

**2.3 Project Specifications**
------------------------------

### 2.3.1 Project Perspective

 

### 2.3.2 System Interface

 

### 2.3.3 User Interface

 

### 2.3.4 Hardware Interface

 

### 2.3.5 Software Interface

 

### 2.3.6 Communication Interface

 

**2.4 Progress Reports**
------------------------

### **2.4.1 Progress report**

**Recent project activities:**

We have contacted the person who designed the Arduino code for NRF24L01
communication and realised that Syma x12s may be using a different protocol than
the Syma x12. It resulted in the Syma x12s’ incompatibility with our Arduino
sketch. As a group we have decided to purchase a new quadcopter, Eachine H8,
which was one of the supported drones for the Arduino sketch.
(<https://www.amazon.ca/Eachine-Quadcopter-Headless-Drone-Black/dp/B00XHOOWA0>).

 

**Current Objectives:**

As of today, our primary goal would be to establish communication between
Arduino and Android Application.

 

**Problems / Opportunities, Solutions:**

For almost a month and a half we had a problem with binding our quadcopter and
Arduino. After finding out that Syma X12s might not be compatible with latest
version of NRF24L01 breakout board we came up with a new solution. With some
help from Goebish (<https://github.com/goebish/nrf24_multipro>), who has a lot
of experience in deviation, we decided to purchase another quadcopter (Eachine
H8, as it was stated above). It allowed us to bind the drone with Arduino and
control it with the joysticks.

 

**Financial Updates:**

Pair of Joysticks for Arduino: \$18.01

Eachine H8 mini: \$30

 

All of the prices do not include HST nor Shipping estimates. However, they will
be included in the final updated version of Budget Report after we would
establish the connection between quadcopter and Android Application.

 

**Additional Links:**

<https://www.makehardware.com/2016/07/04/how-to-control-your-drone-from-a-computer/><https://www.makehardware.com/2016/07/04/how-to-control-your-drone-from-a-computer/>

<https://brainy-bits.com/tutorials/arduino-joystick-tutorial/><https://brainy-bits.com/tutorials/arduino-joystick-tutorial/>

 

### **2.4.2 Progress Report**

**Recent Project Activities:**

As we now have full control over the drone with the two analog joysticks, we are
now focusing on manipulating the drone by Android Application. We have also
created a website where anybody is able to sign up, log in and view their
drone’s flight information. During this process, we had created several PHP
files in order to display the user’s respective flight information. In the drone
application, we had modified our controller where it would be more user friendly
than our initial controller we had designed. We have also added a small feature
within the app where the user is able to delete their existing flight
information from the database when the user clicks on the “Delete All” button.

 

**Current Objectives:**

Our current and main objective is to establish communication between our Arduino
and Android application in order to control our Eachine H8 drone. We are testing
2 ways of manipulating the drone, such as using OTG Cable, which has direct
connection between Arduino and Android device, and Bluetooth Module HC-05. The
latter has more complications in order to achieve full communication, but it
will be our prior way of connection. If the connection won’t be established
between Arduino and Android device by using the Bluetooth Module, we will be
using an OTG cable since it is an easier way to communicate.

 

**Problems/Opportunities, Solutions:**

The main problem we are encountering is the communication between the Arduino
and our Android application. We have two options in which we can establish the
connection, the first option would be to use an OTG cable and establish a serial
connection and the second option would be to use Bluetooth by using the HC-05
Bluetooth Module. As the solution, we are testing basic communication through
lighting up the LED connected to the Arduino circuit right now.

 

**Financial Updates:**

OTG Cable Micro USB male to USB Female: \$6.99 with shipping + Tax (Amazon
Prime)

**OR**

HC-05 Bluetooth Module: \$18.65 with shipping + Tax (CanadaRobotix)

 

**Additional Links:**

<https://www.allaboutcircuits.com/projects/communicate-with-your-arduino-through-android/>

<http://www.instructables.com/id/Android-Bluetooth-Control-LED-Part-2/>

 

**3. Conclusion**
-----------------

The Software Controlled Drone project will meet all the requirements specified
in the technical report. We have created hardware that is capable of controlling
and communicating with the Eachine H8 mini drone. The MYSQL database is able to
record the user’s flight duration, the date of when the drone has been flown and
as well as the user’s account information such as first and last name, username
and password. The android application is capable of allowing users to sign in or
sign up for an account if they do not have one and allow users to control the
Eachine H8 drone. The android application is also capable of updating the
database with the user’s personal runtime information and giving the option to
users where they will be able to view their flight information within the app.
The web interface is capable of allowing users to sign up and sign in to their
account and view their drone’s flight information which is stored in the
database.

 

**4. Recommendations**
----------------------

 

**5. Bibliography**
-------------------
