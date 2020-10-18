<style>
h2.exercice-toggle:not([raw='true'])::before {
	content: 'Step ' counter(exercice) ' : ';
	counter-increment: exercice;
}

h4.question-toggle:not([raw='true'])::before {
	content: 'Step ' counter(exercice) '.' counter(question, lower-alpha) '. ';
	counter-increment: question;
    width: 200px;
    
}


objectif::before {
  content: 'Objectives : ';
}
remarque::before {
	content: 'Remark: ';
	font-weight: bold; 
}
</style>

<chapitre title="Yocto Discovery Lab">
	
    <center> <img src="./TPS/TPRestVersasense/psiot-DistributedArchitecture.png"></img> </center>
    	
    <objectif>
       	In this lab, we explore the interactions between  3 YoctoPuce devices and a client application through an Ethernet Hub. 
    	The REST API provided by YoctoPuce may also be used to interact with an IoT platform. <br>
  	</objectif>
	<span style="background-color:Yellow;">
	TODO : <br>
	- make an executable of each JavaFX program : visualizeData and analyzeData <br>
	- make YoctoVisualisation and YoctoCloud parts if possible <br>
	</span>
	
  	<exercice title="Yocto Devices description" unwrap=true>

    	<p>
      		In this lab, we work with some <a target = '_blank' href="https://www.yoctopuce.com">YoctoPuce</a> MicroPnP sensors and one Yocto Ethernet hub.
    	</p>
    	<p>
      		<img src="./TPS/TPRestVersasense/Yoctopuce.jpg" width="350"></img><br><br>
      		List of devices:
      		<ul>
				<li> 1 Ethernet hub : manages wireless devices, provides a web interface and opens APIs over Ethernet and Wi-Fi </li>
				<li> 3 Plug-and-play sensors/actuators named <i>Thing1</i>, <i>Thing2</i> and <i>Thing3</i>):
	  				<ul>
	    				<li><b>Thing1</b>:
	      					<ul>
								<li><span style="background-color:LightSkyBlue;"><a href="http://www.yoctopuce.com/EN/products/usb-actuators/yocto-color-v2">Yocto Color</a></span> 
								An actuator equiped with 2 leds that can change of color and luminosity)</li>
	    					</ul>
	    				</li>
	    				<li><b>Thing2</b>:
	      					<ul>
								<li><span style="background-color:PeachPuff;"><a href="http://www.yoctopuce.com/EN/products/usb-sensors/yocto-meteo-v2">Yocto Meteo</a></span>
								 A weather station with temperature, humidity and pressure sensors</li>
	    					</ul>
	    				</li>
	    				<li><b>Thing3</b>:
	      					<ul>
								<li><span style="background-color:Plum;"><a href="http://www.yoctopuce.com/EN/products/usb-displays/yocto-maxidisplay">Yocto Display</a></span> 
								An actuator equiped with a display that can render simple graphic objects and display text</li>
	    					</ul>
	    				</li>
	  				</ul>
				</li>
      		</ul>
    	</p>
  	</exercice>
	<br>
	<exercice title="Understand the YoctoPuce architecture">
		Yoctopuce designs, manufactures and sells USB devices to let your computer sit in the real world. <br>
		Their USB modules are tiny, easy to install and easy to drive programmatically.<br>
		Among them you will find hubs, sensors or actuators :

    	<question title="Devices" unwrap=true><br><br>
    		In this lab, we use three kinds of devices : the <b>sensors</b>, that collect data of the environment, the <b>actuators</b> that produce data when they are told to, and the <b>displays</b> that can basically display anything. <br><br>
			<li><b style="color:MediumAquaMarine;">The environmental sensors</b> </li> <br>
				The USB environmental <b>sensors</b> are tiny. They can also be connected directly to the network using a YoctoPuce Hub (cf. next step). <br>
				Using these devices, you can easily build systems that adapt their behavior based on environmental parameters. <br><br>
				
				These USB devices are driver-free. You can integrate the source code to control them directly into your solution. <br>
				Currently, they support a lot of different programming languages : C#, Python, C++, Java, Javascript, Node.js, PHP, Objective-C, UWP, VisualBasic .NET and Delphi. <br><br>
				 
			<li><b style="color:MediumAquaMarine;">The actuators </b> </li> <br>
				The <b>actuators</b> are USB-driven. They can also be connected directly to the network using a YoctoPuce Hub (cf. next step). <br> 
				Using these devices, your system can act on the environment. For instance, it can power up machines and control them, or signal events to the user without need for a screen.<br><br>
				
				These USB devices are driver-free. You can integrate the source code to control them directly into your solution. <br>
				Currently, they support a lot of different programming languages : C#, Python, C++, Java, Javascript, Node.js, PHP, Objective-C, UWP, VisualBasic .NET and Delphi. <br><br>
				 
			<li><b style="color:MediumAquaMarine;">The displays </b> </li> <br>
				The <b>displays</b> ar small OLED (<i>Organic Light-Emitting Diode</i>) USB screens. They are quite handy when you need to monitor the activity of headless computers. <br>
				These displays can also be connected directly to the network using a YoctoPuce Hub. <br><br>
				
				These USB devices are driver-free. You can integrate the source code to control them directly into your solution. <br>
				Currently, they support a lot of different programming languages : C#, Python, C++, Java, Javascript, Node.js, PHP, Objective-C, UWP, VisualBasic .NET and Delphi. <br><br>
			
			<li><b style="color:MediumAquaMarine;">The devices used in this lab</b> </li> <br>
			
				The first device used in this lab is a environmental sensor. The <b>Yocto-Meteo-V2</b> is a device that lets you measure via USB the current humidity, pressure, and temperature,<br>
				as well as record the pressure, ambient temperature and humidity on its internal flash for later retrieval when connected again by USB (built-in data logger).<br>
				This device can be connected directly to an Ethernet network using a YoctoPuce Ethernet Hub (cf. next step), to a WiFi network or to a GSM network. <br><br>
				
				The second device used in this lab is an actuator. The <b>Yocto-Color-V2</b> is an RGB LED driver. You can change color and luminosity of both LEDs. You can use it for simple signaling as well as for spectacular light effects. <br>
				This device doesn't need an active USB connection to work. You can program each LED color at start up and even program simple animations that automatically start at device power up. <br>
				Then, you only have to power the device with a regular USB charger. <br>
				This device can be connected directly to an Ethernet network using a YoctoPuce Ethernet Hub (cf. next step), to a WiFi network or to a GSM network. <br><br>
				
				The third device used in this lab is a display. The <b>Yocto-MaxiDisplay</b> is a 128x64 OLED (<i>Organic Light-Emitting Diode</i>) display, driven directly from USB. <br>
				It features a built-in controller which can render simple graphic objects, display text with various fonts, and even play pre-recorded animated sequences. <br>
				This device can be connected directly to an Ethernet network using a YoctoPuce Ethernet Hub (cf. next step), to a WiFi network or to a GSM network. <br><br>
				
    	</question>
    	
    	<question title="Hubs" unwrap=true><br><br>
			In this lab, we use a YoctoPuce <b>Ethernet Hub</b>. <br><br>
			
			<li><b style="color:MediumAquaMarine;">The different kind of hubs</b> </li> <br>
				YoctoPuce sells four different kind of hubs : <br>
				<ul type=circle>
					<li>the <b>GSM Hub</b> : <br>
						This hub is a wireless-enabled module that can host three Yoctopuce modules to access them remotely through a 3G GSM cellular network. <br>
						You can use the wireless hub in the same way as a VirtualHub (cf. "Interact with the sensors through web portal" part) running on a little computer, <br>
						but it is much easier to setup and maintain than a computer. It is smaller and consumes less. </li>
					<li>the <b>Shield Hub</b> : <br>
						This hub is an extension used if you need to connect more modules than allowed on the network hubs.<br>
						It allows you to connect 4 additional Yoctopuce modules to the network. </li>
					<li>the <b>Wireless Hub</b> : <br>
						This hub is a module which can host three Yoctopuce modules to access them remotely through a WiFi network. <br>
						You can use the wireless hub in the same way as a VirtualHub (cf. "Interact with the sensors through web portal" part) running on a little computer, <br>
						but it is much easier to setup and maintain than a computer. It is smaller and consumes less. </li>					
				</ul>
			<br>	
			<li><b style="color:MediumAquaMarine;">The Ethernet Hub</b> </li> <br>
				The fourth kind of hub sold by YoctoPuce is the <b>Ethernet Hub</b>, which is the hub that we use in this lab. <br>
				This hub is a Fast-Ethernet module which can host three Yoctopuce modules to access them remotely. It can be powered either by a Micro-B USB cable, or directly from the Ethernet cable. <br>
				You can use the wireless hub in the same way as a VirtualHub (cf. "Interact with the sensors through web portal" part) running on a little computer, <br>
				but it is much easier to setup and maintain than a computer. It is smaller and consumes less. <br><br>
			
				Here is a scheme of how you are using the hub in this lab : <br><br>
				<img src="./TPS/TPRestYocto/resources/SchemeHubSensors.png"></img> <br> <br>
			</li>
				
    	</question>
    
    	<question title="Rest API" unwrap=true><br><br>
			YoctoPuce also provides a programming interface (API) that has been carefully designed to be easy to use. <br>
			It is pretty convenient because of all the languages it is available with. <br>
    	</question>
    
    	<question title="YoctoPuce and the IoT platforms" unwrap=true><br><br>
			The YoctoPuce modules can be used with IoT platforms (microsoft Azure or OM2M for example). <br>
			They can be used with a hub that provides HTTP callbacks or other intermediaries such as Raspberry Pi. <br>
			However in this lab, we won't use an IoT platform. <br>
    	</question>
    
	</exercice>
  	<br>
  	<exercice title="Interact with the sensors through web portal">
  		
  			<question title="What is VirtualHub" unwrap=true> <br><br>
  				The VirtualHub is a toolbox for Yoctopuce USB devices. It will allow you to : <br>
				<li>configure and test Yoctopuce devices;</li>
				<li>remotely control Yoctopuce devices through network;</li>
				<li>control Yoctopuce devices with languages which do not provide a direct access to USB devices, such as Javascript and PHP.</li>
  			</question>
  			
			<question title="Access to VirtualHub with USB connected devices" unwrap=true> <br><br>
				As said above, VirtualHub is made for devices connected with through an USB port to your computer.<br> 
				You don't have physically access to the devices but you can still try to download VirtualHub and visualize the interface. <br>
				However, you won't be able to see any devices. <br><br>
			   	<li><b style="color:MediumAquaMarine;">Download VirtualHub (if VirtualHub is not already installed on your computer)</b></li> <br>
				The first thing you need to do is to download VirtualHub. <br>
				You can do it at : <a target = '_blank' href="https://www.yoctopuce.com/FR/virtualhub.php">Download Virtual Hub</a>.<br>
				Choose the right option for your own OS. <br>
				Once you've downloaded the file, follow the instructions until you find the following files in your repository : <br> <br>
				<img src="./TPS/TPRestYocto/resources/VirtualHubRepository.png"></img> <br> <br>

				<li><b style="color:MediumAquaMarine;">Open VirtualHub</b></li> <br>
				Click on the VirtualHub file. <br>
				<img src="./TPS/TPRestYocto/resources/VirtualHubRepository2.png"></img> <br> <br>
				A terminal should appear. Spot the phrase "HTTP server is listening port 4444". (The port number may change from a computer to an other). <br>
				Go to your favorite browser and type the following URL: "localhost:<b style="color:Red;">4444</b>" (change the port number into your own if needed). <br>
				You should see a window like this one : <br> <br>
				<img src="./TPS/TPRestYocto/resources/VirtualHubWelcomPage.png"></img> <br> <br>
				
				Even if the hub would have been connected to your computer, you wouldn't have seen the details of the three devices connected to it. <br>
				But the thing is, you are supposed to get the data from the sensors (as knowing what is the temperature) and control the actuators (as turning on the leds of the color actuator). <br>
				Unfortunately, this is a default from VirtualHub and YoctoPuce : you can not display the data on VirtualHub. <br><br>
				
				If you want to do so, you need :
				<li>either to plug each sensor to your computer and control the actuators one by one, which is not convenient at all; </li>
				<li>either to use the hub of which the main function is to provide data. </li> <br>
				This is what the next part of this lab is about. <br>
				
    		</question>
    		<question title="Use VirtualHub same interface to access an Ethernet Hub and visualize the sensors" unwrap=true><br><br>
        			In this part, we focus on interacting with each device and understanding their roles. <br><br>
    				
    				<li><b style="color:MediumAquaMarine;">Access the hub interface </b> </li> <br>
    				First, in order to get access to the interface of the hub, you need to get his IP address. <br>
    				Ask the teacher for it. Here, we'll consider the following IP address : <b style="color:Red;">169.254.14.184</b>. <br>	
    				Finally, go the following address : <a target = '_blank' href="http://169.254.14.184/webapp.html">http://169.254.14.184/webapp.html</a> and replace the IP address by the own you got from the teacher.<br><br>
    				
    				<li><b style="color:MediumAquaMarine;">Visualize the data streams received on the hub </b> </li> <br>
    				Then, if you want to go get the data streams received on the hub on live, you just have to click on the hub as below : <br>
    				<img src="./TPS/TPRestYocto/resources/HubVirtualHub.png"></img> <br><br>
    				Here is an example where :
    				<ul type=circle>
    					<li>the hub received the data from the meteo : the humidity was 46%RH, the pressure was 10002.6mbar and the temperature was 24.41°C;</li>
    					<li>the hub received the data from the color actuator : none of the leds were turned on;</li>
    					<li>the hub received the data from the display : nothing was diplayed on the screen.</li>
    				</ul>
    				<img src="./TPS/TPRestYocto/resources/DataStreamsOnHub.png"></img> <br><br>
    				
        			<li><b style="color:MediumAquaMarine;">The <span style="background-color:LightSkyBlue; color:black;">Color</span> actuator </b> </li> <br>
    				Here is the actuator plugged to the hub : <br>
    				<img src="./TPS/TPRestYocto/resources/ColorActuatorVirtualHub.png"></img> <br><br>
    				Click on the <img src="./TPS/TPRestYocto/resources/ConfigureButton.png"></img> button. <br>
    				You can see that the actuator has 2 leds : colorLed1 et colorLed2. <br>
    				To turn on and off the leds, you can use the black circles to choose the color of each led. <br>
    				<b style="background-color:RED;">WARNING</b> The actuator has only 2 leds so you can only choose one (or both) of the following two circles :<br><br>
    				<img src="./TPS/TPRestYocto/resources/ChoiceLeds.png"></img> <br><br>
    				You can try to change the colors of the leds but it won't be done permanently. If you choose a color for a led but you quit the configuration interface, the leds will turn off. <br>
    				Let's try to configure the leds permanently. <br>
    				Click on the button as shown below : <br>
    				<img src="./TPS/TPRestYocto/resources/changeColor.png"></img> <br> <br>
    				You can see an interface that looks like that : <br>
    				<img src="./TPS/TPRestYocto/resources/chooseColor.png"></img> <br> <br>
    				Now, you can do the same as before but the colors of the leds will be configured even if you quit VirtualHub. <br><br>
    				
    				
        			<li><b style="color:MediumAquaMarine;">The <span style="background-color:PeachPuff; color:black;">Meteo</span> detector sensor </b> </li> <br>
    				Here is the sensor plugged to the hub : <br><br>
    				<img src="./TPS/TPRestYocto/resources/MeteoSensorVirtualHub.png"></img> <br><br>
    				Click on the <img src="./TPS/TPRestYocto/resources/ShowDeviceFunctionsButton.png"></img> button. <br>
    				You can see that the meteo sensor has 3 factors : humidity, pressure and temperature. <br>
    				If the sensors are pretty near you, you can try a little experience : put your hands around your mouth and exhale slowly next to the sensor. You should be able to watch the data variations live on VirtualHub. <br><br>
    
        			<li><b style="color:MediumAquaMarine;">The <span style="background-color:Plum; color:black;">Display</span> actuator </b> </li> <br>
    
    				Here is the display actuator plugged to the hub : <br><br>
    				<img src="./TPS/TPRestYocto/resources/DisplayVirtualHub.png"></img> <br><br>
    				Click on the <img src="./TPS/TPRestYocto/resources/ConfigureButton.png"></img> button.
    				You can now see the different functions of the display.<br>
    				For now, the display doesn't display anything because it wasn't told to.<br>
    				To try to display a text, click on the name of the display : <br><br>
    				<img src="./TPS/TPRestYocto/resources/WriteTextOnDisplay.png"></img> <br><br>
    				In the section "Display", you can now try to diplay a text by writting your text on the "Text to display" blank. <br><br>
    				
    				YoctoPuce provides other very interesting tools that allows you to product graphs of your data. However in this lab, we propose to try a hand-made java application that could look like their owns.<br>
        	</question>
    </exercice>
	<br>
  	<exercice title="Interact with the sensors through the REST API and java clients">
    
    	<question title="Try the REST API with a REST client" unwrap=true> <br><br>
    		For this question, choose a REST Client and interact with the hub, with these selected REST interactions. You also need to get the IP address of the hub.<br> 
    		If you don't already have it, ask the teacher for it. Here, we'll consider the following IP address : <b style="color:Red;">169.254.14.184</b>. <br><br>
      		
      		<ul>
				<li>Get all devices information 
					<ul type=circle>
						<li><b>169.254.14.184</b> is the IP address of the hub, you need to ask the teacher which one to use in your case.</li>
					</ul>
					<code class='code-block'>GET http://169.254.14.184/api.json</code>  
					Output example: <details> <code url="./TPS/TPRestYocto/resources/jsonResults/getAllDevices.json"></code> </details>
				</li>
				<br>
				<li>Get one device information (for example <span style="background-color:PeachPuff;">thing1</span>) 
					<ul type=circle>
						<li><b>169.254.14.184</b> is the IP address of the hub, you need to ask the teacher which one to use in your case;</li>
						<li><b>METEOMK2-F5AD2</b> is the name of the module you want to get data from.</li>
					</ul>
					<code class='code-block'>GET http://169.254.14.184/bySerial/METEOMK2-F5AD2/api.json</code> 
					Output example: <details> <code url="./TPS/TPRestYocto/resources/jsonResults/getDeviceThing1.json"></code> </details>
				</li> 
				<br>
				<li>Get one object description (for example the first led of <span style="background-color:LightSkyBlue;">thing2</span>)
					<ul type=circle>
						<li><b>169.254.14.184</b> is the IP address of the hub, you need to ask the teacher which one to use in your case;</li>
						<li><b>YRGBLED2-1082CB</b> is the name of the module you want to get data from;</li>
						<li><b>colorLed1</b> is the name of the device you want to get data from.</li>
					</ul>
	  				<code class='code-block'>GET http://169.254.14.184/bySerial/YRGBLED2-1082CB/api/colorLed1/api.json</code>
	  				Output example: <details> <code url="./TPS/TPRestYocto/resources/jsonResults/getFirstLedInfo.json"></code> </details>
				</li>
    			<br>
    			<li>Get one object observation (for exemple the temperature sensor of <span style="background-color:PeachPuff;">thing1</span>)
    				<ul type=circle>
						<li><b>169.254.14.184</b> is the IP address of the hub, you need to ask the teacher which one to use in your case;</li>
						<li><b>METEOMK2-F5AD2</b> is the name of the module you want to get data from;</li>
						<li><b>temperature</b> is the name of the device you want to get data from.</li>
					</ul>
	  				<code class='code-block'>GET http://169.254.14.184/bySerial/METEOMK2-F5AD2/api/module/temperature/api.json </code>
	  				Output example: <details> <code url="./TPS/TPRestYocto/resources/jsonResults/getTempObservation.json"></code> </details>
				</li>
				<br>
    			<li>Change the color of the first led of <span style="background-color:LightSkyBlue;">thing2</span>
    				<ul type=circle>
						<li><b>169.254.14.184</b> is the IP address of the hub, you need to ask the teacher which one to use in your case;</li>
						<li><b>YRGBLED2-1082CB</b> is the name of the module you want to get data from;</li>
						<li><b>colorLed1</b> is the name of the device you want to get data from;</li>
						<li><b>rgb</b> is the name of the attribute you want to change;</li>
						<li>
							<b>00000000</b> is the new value of the attribute, in this case it means the led will turn OFF.
							If you want to try some other colors, here are the equivalents between colors and rgbColor : <br>
	  						BLACK (OFF) : rgbColor=0x000000<br>
	  						<span style="background-color:#F00909;">RED</span> : rgbColor=0xF00909<br>
	  						<span style="background-color:#093AF0;">BLUE</span> : rgbColor=0x093AF0<br>
        	  				<span style="background-color:#FFEC00;">YELLOW</span> : rgbColor=0xFFEC00<br>
        	  				<span style="background-color:#10F009;">GREEN</span> : rgbColor=0x10F009<br>
        	  				<span style="background-color:#C734C7;">PURPLE</span> : rgbColor=0xC734C7<br>
        	  				WHITE :	rgbColor=0xFFFFFF<br>
						</li>
					</ul>
	  				<code class='code-block'>POST http://169.254.14.184/bySerial/YRGBLED2-1082CB/api/colorLed1/module?rgbColor=00000000</code>
				</li>
			</ul>   	
    	</question>
	</exercice>
	<br>
	<exercice title="Compile your first java codes">
		<b style="background-color:RED;">WARNING</b> The purpose of the YoctoPuce Ethernet Hub is to provide network connectivity to the sub-modules connected to it, it does not behave like a USB hub. <br>
		The USB port of the YoctoPuce Ethernet Hub is only used to power and configure it.<br> 
		To access the modules connected to the hub, you must go through a network connection.<br>

		<question title="Download and install programs" unwrap=true> <br><br>
			You will compile and run two programs built on eclipse and using among others YoctoPuce librairies and JavaFX : <br> <br>
			<li>the first program aims to show you how to collect data from the devices and how to present them on a (simple) graphical interface; </li>
			<li>the second program aims to show you how the devices may communicate with each other. </li> <br>
			You do not need to install eclipse or other libraries. <br><br>

			<span style="color:Red;"> This part is not done yet : need to make an executable of the programs</span>			
    	</question>
    
    	<question title="Program to collect data from the devices" unwrap=true> <br><br>
    		The program visualizeData aims to show you how to present the data collected by the hub on a quite simple graphical interface. <br>
    		Click on the .jar file. You should see a window opens, similar as the following : <br><br>
    		<img src="./TPS/TPRestYocto/resources/programs/visualizeData.png"></img> <br><br>
    		On the left, you can see the name of the YoctoPuce modules detected by the hub and by your computer. <br>
    		On the right, you can see live the data collected by each module. <br>
    		You can try to reach the VirtualHub interface of the hub right here to try to modify the colors of the leds and check what happens on the application : <a target = '_blank' href="http://169.254.14.184/webapp.html">Click here</a>.<br><br>
    		
    		<b style="background-color:RED;">WARNING</b> If you don't click on the "Quit simulation" button, the window will close but the program will still be running : please, use the button. <br> <br>
    		
			So, collecting the data is great but what you might want to do is make the devices communicate. For example, you might want that the led turns on when the temperature becomes too high. <br>
    	</question>

		<question title="Program to make the devices communicate with each other" unwrap=true> <br><br>
			The program analyzeData aims to show you how to control the devices and how to make them communicate with each other. This application also provides a graphical interface.<br>
    		Click on the .jar file. You should see a window opens, similar as the following : <br><br>
    		<img src="./TPS/TPRestYocto/resources/programs/analyzeData.png"></img> <br><br>
    		In the first part, you can see :
    			<li>On the left, you can see the name of the YoctoPuce modules detected by the hub and by your computer. </li>
    			<li>On the right, you can see live the data collected by each module. </li><br>
    		In the part below, you can play with the devices, here is what you can do : <br>
    			<li>choose the threshold temperature, from which the LEDs will light up <br>
    				<img src="./TPS/TPRestYocto/resources/programs/analyzeDataTemp.png"></img></li><br>
    			<li>choose to display or not the temperature on the screen connected to the hub <br>
    				<img src="./TPS/TPRestYocto/resources/programs/analyzeDataDisplay.png"></img></li><br>
    			<li>choose the color of the first LED when it lights up once the threshold temperature has been crossed <br>
    				<img src="./TPS/TPRestYocto/resources/programs/analyzeDataLed1.png"></img></li><br>
    			<li>choose the color of the second LED when it lights up once the threshold temperature has been crossed <br>
    				<img src="./TPS/TPRestYocto/resources/programs/analyzeDataLed2.png"></img></li><br><br>
    				
    		<b style="background-color:RED;">WARNING</b> If you don't click on the "Quit simulation" button, the window will close but the program will still be running : please, use the button. <br> <br>
    	</question>
    
	</exercice>
	<br>
  	<exercice title="Conclusions of the lab">
  		In home automation, this kind of tool can be very useful. <br>
    	For example, you can easily set up a program that can vibrate a buzzer in case of too high humidity or CO2 in a room.<br>
    	This would indeed avoid certain domestic accidents.<br><br>
    	
    	Now, if you are a big company and you need to store very very large amounts of data, the applications provided by the device constructors are not enough.<br>
    	This is one of the main goal of the IoT platforms : they allow you, among a lot of other things, to store a lot of data, to keep an oeil on your equipments, manage security of your devices and your data, etc. <br>
    	
  	</exercice>
  
 </chapitre>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<hr style="margin-right: 90%;">
$Date: 2019-07-01 08:58:14 +0200 (lun. 01 juil. 2019) $
