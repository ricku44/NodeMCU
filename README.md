# Home Assistant

Combination of ESP8266 Node MCU and some relays which allowed us to control the home appliances on the go.
Wi-Fi capability of Node MCU helped in sending voice instructions through Android Application.
Communication was established through REST API implement using NodeJS Server.


1. Deploy JSON Server to Heroku. Use the link https://github.com/ricku44/json-server
2. Copy the URL of Deployed Server and add "/0" at last.
3. Open WiFi folder, paste the URL & Upload it to NodeMCU.
4. Import the project in Android Studio.
5. Paste the URL in WifiBasedHomeAutomation.java
6. RUN.

<img src="https://github.com/ricku44/NodeMCU/blob/master/com.led.led.jpg" width="300"/>



Bluetooth version of same is available at https://github.com/ricku44/HC-05
