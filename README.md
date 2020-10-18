# 2020-59-iot - YoctoLab Projects

## 1.  Presentation of the two Maven Projects

This folder contains two Maven projects used in the YoctoPuce discovery lab. <br>
These two projects are Maven projects but can be used as Java projects. <br>

For now, these two projects are not exported and cannot be used without a proper IDE. <br>

These two projects use the following libraries, which will need to be added to the IDE: <br>
   - JavaFX;
   - YoctoPuce libraries.

The YoctoPuce libraries needed here are available in the **TPYoctoLibraries** folder.

The **TPYoctoResources** folder contains the editable resources (images, screenshots, schemes, .docx files, etc.) that are used in the YoctoPuce discovery lab.

## 2.  Maven Project : visualizeData

The **visualizeData** maven project aims to show the students how to collect data from some YoctoPuce devices and how to present them on a simple graphical interface. <br>
It uses the JavaFX library in order to build the graphical interface and the YoctoPuce libraries in order to collect data from a YoctoPuce Ethernet Hub.

## 3.  Maven Project : analyzeData

The **analyzeData** maven project aims to show the students how to collect data from some YoctoPuce devices, how to present them on a simple graphical interface and how to control them from an application. <br>

This application allows students to choose :
   - the threshold temperature from which the LEDs will light up;
   - the colors of each LED;
   - whether the temperature should be displayed on the YoctoPuce display or not.

It uses the JavaFX library in order to build the graphical interface and the YoctoPuce libraries in order to collect data from a YoctoPuce Ethernet Hub.
