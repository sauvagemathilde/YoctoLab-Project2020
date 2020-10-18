# 2020-59-iot - YoctoLab Projects - analyzeData Maven Project

## 1.   Structure of the project

This project contains a main package wich contains the classes uses in the project. <br>
The classes are the following : 
   - **Main** : main class that controls the display of all the other items;
   - **Bouton** : class that other classes **chooseColorBtn**, **chooseDisplayBtn** and **chooseTempBtn** inherit, allows the user to control the YoctoPuce devices;
   - **ColorLed** (that **FirstLed** and **SecondLed** inherit), **Display**, **Temperature** and **Hub** : classes associated with each of the YoctoPuce objects.

This project also contains a rest.properties file in the resources folder. This file allows you to choose between several way of connected to the objects. In fact, it helps you to enter the IP address needed to connect to the YoctoPuce Ethernet Hub and collect the data (cf. next part).

## 2.   Options to choose

Before running the project, you need to choose and enter the IP address needed to connect to the YoctoPuce Ethernet Hub and collect the data. In fact, you'll need to enter the following information :
  - the **rest.serveraddress** : (example : http://169.254.14.184/webapp.html);
  - the **name.module.hub** : the name of the YoctoPuce hub you want to use (example : YHUBETH1-D0EB8);
  - the **name.module.meteo** : the name of the YoctoPuce meteo module you want to use (example : METEOMK2-F5AD2);
  - the **name.module.color** : the name of the YoctoPuce color module you want to use (example : YRGBLED2-1082CB);
  - the **name.module.display** : the name of the YoctoPuce display you want to use (example : YD128X64-10CC92).

The names of the several modules can be found by connecting to the rest.serveraddress. <br>
If you don't have one of the module, please enter one of the examples of previous names.

Two examples are already entered in the rest.properties file. You can whether choose one of these two or enter your own information (however, be careful to enter the 5 information as seen above).

## 3.   How to open the project

As the project is not exported yet, you need to open it in a proper IDE (Eclipse for example). <br>
In Eclipse, you need to import the project as a Maven project or as a Java Project.
In order for the project to be working, you need to follow the steps below :
  - **install e(fx)clipse** : 
    - open Eclipse;
    - in the **Help** menu, choose **Eclipse Marketplace...**;
    - as the window opens, in the **Find** section, type **e(fx)clise** and then install the software;
    - restart Eclipse.
  - **allow javafx to be accessible** :
    - after importing the project in Eclipse, right click on the project and choose **properties**;
    - as the window opens, choose **Java Build Path** on the menu on the left;
    - choose the **Libraries** section;
    - drop down the **JRE System Library** menu, select the **Access rules** section and choose to add a new rule;
    - choose the **Accessible** option of **resolution** and type **javafx/**** in the **Rule Pattern** section;
    - **Apply and Close** all the windows;
    - restart Eclipse.
  - **add the external JAR libraries of YoctoPuce** :
    - after importing the project in Eclipse, right click on the project and choose **properties**;
    - as the window opens, choose **Java Build Path** on the menu on the left;
    - choose the **Libraries** section;
    - click on the right **Add Library...** button;
    - as the window opens, choose **User Library**, click on the **Next >** button;
    - click on the right ***User Libraries...** button;
    - as the window opens, click on the right **New...** button, choose a **User library name** (**YoctoPuce Libraries** for instance), then click **OK**;
    - select your new library, click on the right **Add External JARs...** button and then select all the .jar file available in the **TPYoctoLibrairies** folder of the YoctoLabProjects folder;
    - click **Open** and **Apply and Close** all the windows;
    - restart Eclipse.

## 4.   How to run the project

To run the project from the Eclipse IDE, right click on the project, then click on "Run As" and then choose "Java application".
