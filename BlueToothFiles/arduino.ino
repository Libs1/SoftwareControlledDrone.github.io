char incomingByte;  // incoming data
int  LED = 13;      // LED pin
int i = 0;

void setup() {
  Serial.begin(9600); // initialization
  pinMode(LED, OUTPUT);
  Serial.println("Press 1 to LED ON or 0 to LED OFF...");
}

void loop() {
  if (Serial.available() > 0) {  // if the data came
    incomingByte = Serial.read(); // read byte
    if(incomingByte == '0') {
       digitalWrite(LED, LOW);  // if 1, switch LED Off
       Serial.println("LED OFF. Press 1 to LED ON!");  // print message
    }
    if(incomingByte == '1') {
       digitalWrite(LED, HIGH); // if 0, switch LED on
       Serial.println("LED ON. Press 0 to LED OFF!");
    }

    if(incomingByte == '2') {
      Serial.println("LED ON. Press 0 to LED OFF!");
      for(i=0;i<10;i++){
       digitalWrite(LED, HIGH); // if 0, switch LED on
       delay(300);
       digitalWrite(LED, LOW); // if 0, switch LED on
       delay(300);
      }
    }
  }
}
