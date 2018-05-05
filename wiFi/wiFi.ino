#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266HTTPClient.h>

#define USE_SERIAL Serial

ESP8266WiFiMulti WiFiMulti;

int r=1; //001
int g=2; //010
int b=4; //100

int RED_PIN=16;     //D0
int GREEN_PIN=2;    //D4
int BLUE_PIN=15;    //D8

String url=">>> Supply your URL Here <<<";

void setup() {

    USE_SERIAL.begin(115200);
    USE_SERIAL.println();
    USE_SERIAL.println();
    USE_SERIAL.println();

    pinMode(2, OUTPUT);   //G
    pinMode(15, OUTPUT);  //B
    pinMode(16, OUTPUT);  //R

    for(uint8_t t = 4; t > 0; t--) {
        USE_SERIAL.printf("[SETUP] WAIT %d...\n", t);
        USE_SERIAL.flush();
        delay(1000);
    }

    WiFiMulti.addAP("Username", "Password");
}

void loop() {
  if((WiFiMulti.run() == WL_CONNECTED)) {

        HTTPClient http;

        USE_SERIAL.println("[HTTP] begin...\n");
        http.begin(url);
        USE_SERIAL.println("[HTTP] GET...\n");
        int httpCode = http.GET();
        
        if(httpCode > 0) {
            if(httpCode == HTTP_CODE_OK) {
                String payload = http.getString();
                process(payload);
            }
        } else {
            USE_SERIAL.println("Server is not up");
        }
        String payload = http.getString();
        process(payload);
        http.end();
    }
    delay(200);
}


void process(String str){

  int i=0;
  if((i=str.indexOf(":"))>=0){
    //Data received
    String data=str.substring(i+3,i+4);
    furtherProcess(data);
    //USE_SERIAL.println(data);
  }else{
    USE_SERIAL.println("No Message");
  }
}

void furtherProcess(String str){

  int code=str.toInt();
  digitalWrite(RED_PIN, code&r?HIGH:LOW);
  digitalWrite(GREEN_PIN, code&g?HIGH:LOW);
  digitalWrite(BLUE_PIN, code&b?HIGH:LOW);
}
