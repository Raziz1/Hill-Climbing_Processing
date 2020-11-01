//Import physics library BOX2D
import shiffman.box2d.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.*;

import java.util.*;


//Import Minim sound library________________________________________
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

Minim minim;
AudioPlayer sou;
AudioPlayer lou;
AudioPlayer cou;
AudioSample beep;




//Images________________________________________________________________________________
PFont gillbold;
PFont gill;
PImage flag;
PImage instructionscoin;
PImage instructionsaccelerations;
PImage instructionsrotation;
PImage instructiongas;
PImage car;
PImage map;
PImage tire;
PImage piston;
PImage suspension;
PImage speedometer;
PImage bit;
PImage moon;
PImage background;
PImage rally;

PImage wheel;
PImage face;
PImage jerrycan;

boolean callMethod=true;
boolean tp=false;
boolean music2=true;
//Variables__________________________________________________________________________________
float rectX=width/2;
float [] rectY= new float [4];       
float rectW= width*0.260416666;
float rectH=height*0.09259259;
int page=0;


float rectX3;
float rectY3;
float rectW3;
float rectH3;

float rectX4;
float rectY4;
float rectW4;
float rectH4;

//Map button Coordinates
float rectX6;
float [] rectY6= new float [2];
float rectW6;
float rectH6;
boolean mapBought=false;

//Car button Coordinates
float rectX7;
float [] rectY7= new float [2];
float rectW7;
float rectH7;
boolean carBought=false;


//Back button Coordinates__________________________________________
float rectX2;
float rectY2;       
float rectW2;
float rectH2;

//Upgrades Coordinates______________________________________________________
float rectX5;
float [] rectY5= new float [3];    
float rectW5;
float rectH5;

int [] lvl= new int [3];
float [] price= new float [3];

float engineSpeed=1;

float freq=1.5;
float damp=0.1;

float torque=100;

float fric=1;

float additive=0.1;

float densTire=1;

float densCar=1;



//For Fps counter_________________
int lastFrame;
int lastMillis;
int thisFrame;
int avgFrame;

float distance;
float gas=200;
float money=0;
boolean start=false;
boolean moon_map=false;
boolean rally_car=false;
//A reference to our box2d world
Box2DProcessing box2d;



//A method of calling our classes
Wheel wheels;
Box box;
WheelRevolute wheelrevolute;
WheelRevolute wheelrevolute1;
ArrayList<Fuel> fuel;
Person person;
Torso torso;
Head head;
ArrayList<Coin> coin;
ArrayList<Float> record;
//An object to store information about the uneven surface
Surface ground;


void setup() {
  //Using 3d render technique
  fullScreen(P3D);
  smooth();
  //Capping frame rate at 75
  frameRate(75);



  //Allows you to reset the screen size to lower resolution to boost performance________________________
  surface.setResizable(true);
  //Fonts______________________________________________________________________________________________
  gillbold=loadFont("GillSans-UltraBold-60.vlw");
  gill=loadFont("GillSansMT-60.vlw");
  //Loading Images______________________________________________________
  flag=loadImage("flag.png");
  instructionscoin=loadImage("coin_instructions.png");
  instructionsaccelerations=loadImage("acceleration_instructions.png");
  instructionsrotation=loadImage("rotation_instructions.png");
  instructiongas=loadImage("gas_instructions.png");
  car=loadImage("Car.png");
  map=loadImage("Map.png");
  tire=loadImage("Tire.png");
  suspension=loadImage("Suspension.png");
  piston=loadImage("Piston.png");
  wheel=loadImage("wheel.png");
  face=loadImage("head.png");
  jerrycan=loadImage("gas.png");
  speedometer=loadImage("speedometer.png");
  bit=loadImage("coin.png");
  moon=loadImage("Moon.png");
  background=loadImage("background.png");
  rally=loadImage("Super_Diesel.png");


  //Load audio Files___________________________________________________
  minim = new Minim(this);
  sou = minim.loadFile("SONG.mp3");
  lou= minim.loadFile("Manny Theme.mp3");
  cou=minim.loadFile("Main Theme.mp3");



  //Set the price of upgrades_______________________________________________________
  price[0]=100;
  price[1]=100;
  price[2]=100;
  lastMillis = millis();


  record= new ArrayList<Float>();

  //Just to set the initial record____________________________________________________________
  float x=0;
  record.add(x);

  rectX4=width/2;
  rectY4=height*0.09259;
  rectW4=width*0.18229;
  rectH4=height*0.09259;

  //Initialize box2d physics and create the world
  box2d= new Box2DProcessing(this);
  box2d.createWorld();
  //Turn on collision listening__________________
  box2d.listenForCollisions();
}


void draw() {
  background(#3b3e47);
  //If start is false the main menu screen is loaded


  if (music2) {
    music();
    music2=false;
  }

  if (start==false) {
    rectX3=(width/2+(width*0.02604166));
    rectY3=height*0.32407407;
    rectW3=width*0.15625;
    rectH3=height*0.09259259;

    rectX2=width*0.02604;
    rectY2= height*.04629;       
    rectW2= width*0.15625;


    rectH2=height*0.09259;
    //First page of the menu ('How to play', 'Settings', 'Start')______________________________________________________
    if (page==0) {
      pushStyle();
      textFont(gillbold);
      textAlign(CENTER);
      textSize(70);
      fill(255, 255, 0);
      text("Monster Climb", width/2, height/5);
      popStyle();


      pushStyle();
      rectMode(CENTER);
      strokeWeight(5);
      stroke(255, 255, 0);
      fill(255, 255, 0, 25);
      rect(width/2, height/2+(height*0.092592592), width*0.390625, height*0.601851, 10);
      popStyle();

      for (int i=1; i<4; i+=1) {
        rectX=width/2;
        rectY[i]=(height/5)+((height*0.1851851)*i);
        rectW= width*0.260416666;
        rectH=height*0.09259259;
        pushStyle();
        rectMode(CENTER);
        strokeWeight(5);
        stroke(255, 255, 0);
        if ((mouseX>rectX-rectW/2) && (mouseX<rectX+rectW/2) && (mouseY>rectY[i]-rectH/2)&&(mouseY<rectY[i]+rectH/2)) {
          fill(255, 0, 0, 50);
        } else {
          fill(255, 255, 0, 50);
        }
        rect(rectX, rectY[i], rectW, rectH, 10);
        popStyle();
      }

      pushStyle();
      textAlign(CENTER);
      textFont(gill);
      fill(255, 255, 255);
      text("Start", width/2, height*0.407407407);
      popStyle();

      pushStyle();
      textAlign(CENTER);
      textFont(gill);
      fill(255, 255, 255);
      text("Settings", width/2, height*0.59259259);
      popStyle();

      pushStyle();
      textAlign(CENTER);
      textFont(gill);
      fill(255, 255, 255);
      text("How To Play", width/2, height*0.77777777);
      popStyle();
    } else if (page==1) {
      selection();
    } else if (page==2) {
      Settings();
    } else if (page==3) {
      howtoplay();
    }
  } else if (start==true) {
    //This is so that if the screen size is changed the bodies get the correct positions
    // Resets the physics bodies (car & wheels & person)
    if (tp==true) {
      destroy(); 
      tp=false;
    }
    //Calls the method to initialize the physics bodies at the specified locations
    if (callMethod) {
      myMethod();
      callMethod=false;
    }

    //Main game____________________________________________________________________________________________________
    box2d.step();    //Steps in physics calculation
    cou.pause();
    cou.rewind();
    if (moon_map==true) {
      box2d.setGravity(0, -8);
      background(#030f49);
    } else {
      box2d.setGravity(0, -30);
      background(#97e8ff);
    }
    //Translate the all the images similar to a camera following the car________________________________________________--
    pushMatrix();
    Vec2 s=box2d.getBodyPixelCoord(box.body);
    distance=round((s.x-800)/75);
    if (s.x<=width/2) {
    } else {
      translate(-s.x+width/2, 0);
    }


    //draw background mountain range_________________________________________________________
    if (moon_map==false) {
      if (s.x<=width/2) {
        image(background, width/2-(width*0.5), +150, 1920, height/1.3333);
      } else {
        image(background, s.x-(width*0.5), +150, 1920, height/1.3333);
      }
    }

    //Displaying our classes__________________________________________________________



    person.display();
    ground.display();
    wheelrevolute.display();
    wheelrevolute1.display();
    //fuel.display();
    box.display();

    //Fuel__________________________________________________________________________
    gas-=0.05;
    pushStyle();
    strokeWeight(5);
    noFill();
    if (s.x>=width/2) {
      rect(s.x-(width*0.442708), height*0.04629, width*0.10416, height*0.04629);
    }
    popStyle();
    pushStyle();
    noStroke();
    if (gas>=100) {
      fill(0, 255, 0);
    } else if (gas<=100 && gas>=50) {
      fill(255, 255, 0);
    } else if (gas<=50) {
      fill(255, 0, 0);
    }
    if (s.x>width/2) {
      rect((s.x-(width*0.546875))+(gas*(width/1920.0)), height*0.04629, width*0.10416, height*0.04629);
      if (moon_map==true) {
        fill(#030f49);
      } else {
        fill(#97e8ff);
      }
      rect(s.x-(width*0.59895), height*0.04629, width*0.15625, height*0.04629);
    }
    popStyle();



    //Frame count_____________________________________________________________
    pushStyle();
    textSize(15);
    if (moon_map==true) {
      fill(255, 255, 255);
    } else {
      fill(0, 0, 0);
    }
    thisFrame = 1000/(1+millis()-lastMillis);
    //text("millis:   " + millis() + "\n" +"fcount: " + frameCount + "\n" +"fps:      " + thisFrame + "\n" +"avg:      " + frameCount/(millis()/(float)1000) + "\n", s.x, 20);
    text("FPS avg: "+frameCount/(millis()/(float)1000) + "\n", s.x-(width*0.052083), height*0.023148);
    lastMillis = millis();
    popStyle();

    pushStyle();
    textSize(30);
    if (moon_map==true) {
      fill(255, 255, 255);
    } else {
      fill(0, 0, 0);
    }
    textSize(width/64);
    if (s.x>width/2) {
      text("Distance: "+distance+"m", s.x+(width*0.26041), height*0.04629);
      text("Money: "+money+"$", s.x+(width*0.26041), height*0.08629);
    }
    popStyle();
    if (gas<=0) {
      wheelrevolute.joint.enableMotor(false);
      wheelrevolute1.joint.enableMotor(false);
    }
    if (gas<=-2) {
      //Tells you your record_____________________________________
      record.add(distance);
      tp=true;
      destroy();
      page=1;
      start=false;
    }

    //Speedometer_____________________________________________________________________________________________________________________
    if (s.x>=width/2) {
      pushMatrix();
      Vec2 sp=(box.body.getLinearVelocity());
      float vel=sp.x;

      pushStyle();
      translate(0, 0, +1);
      Vec2 l = box2d.getBodyPixelCoord(box.body);
      image(speedometer, l.x-width/2, height-(height*0.22222), width*0.243055, height*0.22222);
      popStyle();


      pushStyle();
      translate((l.x-width/2)+(width*0.1225), height-13);
      pushMatrix();
      strokeWeight(5);
      stroke(255, 0, 0);
      //if you are going backwards display as a positive velocity_________________________________________________
      if (vel<0) {
        rotate(-(vel/35));
      } else {
        rotate(vel/35);
      }
      line(0, 0, -(width*0.08214), 0);
      popStyle();
      popMatrix();
      popMatrix();
    }

    popMatrix();
  }
}






void keyPressed() {
  if (start==true) {
    if (key==CODED) {
      //Turns on motors____________________________________________________
      if (keyCode==LEFT) {
        wheelrevolute.toggleMotor();
        wheelrevolute1.toggleMotor();
        wheelrevolute1.joint.setMotorSpeed((4+engineSpeed)*PI);
        wheelrevolute.joint.setMotorSpeed((4+engineSpeed)*PI);
        box.body.applyAngularImpulse(-2000);
      } else if (keyCode==RIGHT) {      
        wheelrevolute.toggleMotor();
        wheelrevolute1.toggleMotor();
        wheelrevolute1.joint.setMotorSpeed((-4-engineSpeed)*PI);
        wheelrevolute.joint.setMotorSpeed((-4-engineSpeed)*PI);
        box.body.applyAngularImpulse(2000);
      }
    }
  }
}


void keyReleased() {
  if (start==true) {
    if (key==CODED) {
      if (keyCode==LEFT) {
        //Turns off motor__________________________________________________
        wheelrevolute.toggleMotor();
        wheelrevolute1.toggleMotor();
      } else if (keyCode==RIGHT) {
        wheelrevolute.toggleMotor();
        wheelrevolute1.toggleMotor();
      }
    }
  }
}


//Collision event Functions______________________________________________
void beginContact(Contact cp) {
  //Get both shapes
  Fixture f1=cp.getFixtureA();
  Fixture f2=cp.getFixtureB();

  Fixture f3=cp.getFixtureA();
  Fixture f4=cp.getFixtureB();

  Fixture f5=cp.getFixtureA();
  Fixture f6=cp.getFixtureB();


  //Get both bodies________________
  Body b1=f1.getBody();
  Body b2= f2.getBody();

  Body b3=f3.getBody();
  Body b4= f4.getBody();

  Body b5=f5.getBody();
  Body b6= f6.getBody();


  //Get our objects that reference these bodies
  Object o1= b1.getUserData();
  Object o2= b2.getUserData();

  Object o3= b3.getUserData();
  Object o4= b4.getUserData();

  Object o5= b5.getUserData();
  Object o6= b6.getUserData();



  //Collision with box & fuel canister_____________________________________
  if (o1==null || o2==null) {
    return;
  } else if (o1.getClass()==Fuel.class && o2.getClass()==Box.class) {
    Fuel p1=(Fuel) o1;
    Box p2=(Box) o2;
    gas=200;
    p1.delete();
  } else if (o1.getClass()==Box.class && o2.getClass()==Fuel.class) {
    Box p1=(Box) o1;
    Fuel p2=(Fuel) o2;
    gas=200;
    p2.delete();
  } else if (o1.getClass()==Fuel.class && o2.getClass()==Wheel.class) {
    Fuel p1=(Fuel) o1;
    Wheel p2=(Wheel) o2;
    gas=200;
    p1.delete();
  } else if (o1.getClass()==Box.class && o2.getClass()==Wheel.class) {
    Wheel p1=(Wheel) o1;
    Fuel p2=(Fuel) o2;
    gas=200;
    p2.delete();
  }



  //Collision of head & Ground______________________________

  if (o3==null || o4==null) {
    return;
  } else if (o3.getClass()==Head.class && o4.getClass()==Surface.class) {
    Head p1=(Head) o3;
    Surface p2=(Surface) o4;

    //Tells you your record_____________________________________
    record.add(distance);

    //Exits and destroys physics bodies___________________________
    start=false;
    //destroy();
    tp=true;
    music2=true;
  } else if (o3.getClass()==Surface.class && o4.getClass()==Head.class) {
    Surface p1=(Surface) o3;
    Head p2=(Head) o4;

    //Tells you your record_____________________________________
    record.add(distance);

    start=false;
    //destroy();
    tp=true;
    music2=true;
  }

  //Coin and box collision_______________________________________________________
  if (o5==null || o6==null) {
    return;
  } else if (o5.getClass()==Coin.class && o6.getClass()==Box.class) {
    Coin p1=(Coin) o5;
    Box p2=(Box) o6;
    p1.delete();
    money+=25;
  } else if (o5.getClass()==Box.class && o6.getClass()==Coin.class) {
    Box p1=(Box) o5;
    Coin p2=(Coin) o6;
    p2.delete();
    money+=25;
  } else if (o5.getClass()==Coin.class && o6.getClass()==Wheel.class) {
    Coin p1=(Coin) o5;
    Wheel p2=(Wheel) o6;
    p1.delete();
    money+=25;
  } else if (o5.getClass()==Wheel.class && o6.getClass()==Coin.class) {
    Wheel p1=(Wheel) o5;
    Coin p2=(Coin) o6;
    p2.delete();
    money+=25;
  }
}

void endContact(Contact cp) {
}



void mousePressed() {
  if (start==false) {
    for (int i=1; i<=4; i+=1) {
      if ((mouseX>rectX-rectW/2) && (mouseX<rectX+rectW/2) && (mouseY>rectY[1]-rectH/2)&&(mouseY<rectY[1]+rectH/2)&&(page==0)) {
        page=1;
      } else if ((mouseX>rectX-rectW/2) && (mouseX<rectX+rectW/2) && (mouseY>rectY[2]-rectH/2)&&(mouseY<rectY[2]+rectH/2)&&(page==0)) {
        page=2;
        rectX3=(width/2+50);
        rectY3=height*0.32407407;
        rectW3=width*0.15625;
        rectH3=height*0.09259259;
      } else if ((mouseX>rectX-rectW/2) && (mouseX<rectX+rectW/2) && (mouseY>rectY[3]-rectH/2)&&(mouseY<rectY[3]+rectH/2)&&(page==0)) {
        page=3;
      }
    }

    if ((mouseX>rectX3)&&(mouseX<rectX3+rectW3)&&(mouseY>rectY3)&&(mouseY<rectY3+rectH3)&&(page==2)) {
      if ((width>1440)&&(mouseButton==LEFT)) {
        surface.setSize(width-240, height-90); //Only 3 display ratios possible (1920x1080,1680x990,1440x900)
      }
      if ((width<1920)&&(mouseButton==RIGHT)) {
        surface.setSize(width+240, height+90);
      }
    }
    if ((mouseX>rectX4-(rectW4/2))&&(mouseX<rectX4+(rectW4/2))&&(mouseY>rectY4-(rectH4/2))&&(mouseY<rectY4+(rectH4/2))&&(page==1)) {
      start=true;
      callMethod=true;
    }
    //Upgrade check_______________________________________________________________________________________________________
    for (int i=0; i<3; i++) {
      if ((mouseX>rectX5-(rectW5/2))&&(mouseX<rectX5+(rectW5/2))&&(mouseY>rectY5[i]-(rectH5/2))&&(mouseY<rectY5[i]+(rectH5/2))&&(page==1)) {
        if ((price[i]<=money) && (lvl[i]<10)) { //If you have enough money for the upgrade
          lvl[i]+=1;    //Add one to the level
          money=money-price[i]; //Remove the price from your money
          price[i]=price[i]+100;  //Double the price
          if ((mouseX>rectX5-(rectW5/2))&&(mouseX<rectX5+(rectW5/2))&&(mouseY>rectY5[0]-(rectH5/2))&&(mouseY<rectY5[0]+(rectH5/2))&&(page==1)) {
            engine();
          } else if ((mouseX>rectX5-(rectW5/2))&&(mouseX<rectX5+(rectW5/2))&&(mouseY>rectY5[1]-(rectH5/2))&&(mouseY<rectY5[1]+(rectH5/2))&&(page==1)) {
            TIRE();
          } else if ((mouseX>rectX5-(rectW5/2))&&(mouseX<rectX5+(rectW5/2))&&(mouseY>rectY5[2]-(rectH5/2))&&(mouseY<rectY5[2]+(rectH5/2))&&(page==1)) {
            suspension();
          }
        }
      }
    }

    //Map________________________________________________________________
    for (int i=0; i<2; i++) {
      if ((mouseX>rectX6-(rectW6/2))&&(mouseX<rectX6+(rectW6/2))&&(mouseY>rectY6[0]-(rectH6/2))&&(mouseY<rectY6[0]+(rectH6/2))&&(page==1) ) {
        moon_map=false;
      } else if ((mouseX>rectX6-(rectW6/2))&&(mouseX<rectX6+(rectW6/2))&&(mouseY>rectY6[1]-(rectH6/2))&&(mouseY<rectY6[1]+(rectH6/2))&&(page==1) &&(money>=500)&&(mapBought==false)) {
        money=money-500;
        mapBought=true;
        moon_map=true;
      } else if ((mouseX>rectX6-(rectW6/2))&&(mouseX<rectX6+(rectW6/2))&&(mouseY>rectY6[1]-(rectH6/2))&&(mouseY<rectY6[1]+(rectH6/2))&&(page==1) &&(mapBought==true)) {
        moon_map=true;
      }
    }
    //Car____________________________________________________________________________________
    for (int i=0; i<2; i++) {
      if ((mouseX>rectX7-(rectW7/2))&&(mouseX<rectX7+(rectW7/2))&&(mouseY>rectY7[0]-(rectH7/2))&&(mouseY<rectY7[0]+(rectH7/2))&&(page==1)) {
        rally_car=false;
      } else if ((mouseX>rectX7-(rectW7/2))&&(mouseX<rectX7+(rectW7/2))&&(mouseY>rectY7[1]-(rectH7/2))&&(mouseY<rectY7[1]+(rectH7/2))&&(page==1) && (money>=500) && (carBought==false)) {
        money=money-500;
        rally_car=true;
        carBought=true;
      } else if ((mouseX>rectX7-(rectW7/2))&&(mouseX<rectX7+(rectW7/2))&&(mouseY>rectY7[1]-(rectH7/2))&&(mouseY<rectY7[1]+(rectH7/2))&&(page==1) && (carBought==true)) {
        rally_car=true;
      }
    }
  }
}



//How to play menu screen________________________________________________________________________________________________________________________________________________________________________
void howtoplay() {
  pushStyle();
  textFont(gillbold);
  textAlign(CENTER);
  textSize(70);
  fill(255, 255, 0);
  text("How To Play", width/2, height/5);
  popStyle();

  pushStyle();
  imageMode(CENTER);
  image(instructionsaccelerations, width*0.18229166, height*0.37037037, width*0.49479166, height*0.46296296);
  image(instructionscoin, width*0.6510466, height*0.37037037, width*0.49479166, height*0.46296296);
  image(instructionsrotation, width*0.18229166, height*0.74074074, width*0.49479166, height*0.46296296);
  image(instructiongas, width*0.6510466, height*0.74074074, width*0.49479166, height*0.46296296);

  popStyle();

  pushStyle();
  textFont(gill);
  textSize(25);
  fill(255, 255, 255);
  text("Press the left and right arrow keys to accelerate", width*0.26041, height*0.32407, 350, 500);
  text("Press the left and right arrow keys to rotate the vehicle", width*0.260416, height*0.69444, 350, 500);
  text("Collect coins to spend on vehicle upgrades", width*0.72916, height*0.32407, 350, 500);
  text("Run into gas canisters to refuel", width*0.72916, height*0.69444, 350, 500);
  popStyle();

  pushStyle();
  strokeWeight(5);
  stroke(255, 255, 0);
  if ((mouseX>rectX2)&&(mouseX<rectX2+rectW2)&&(mouseY>rectY2)&&(mouseY<rectY2+rectH2)&&(page==3)) {
    fill(255, 0, 0, 50);
    if (mousePressed==true) {
      page=0;
    }
  } else {
    fill(255, 255, 0, 25);
  }

  rect(rectX2, rectY2, rectW2, rectH2, 10);
  popStyle();

  pushStyle();
  textSize(width/32);
  text("Back", width*0.0390625, height*0.11574);
  popStyle();
}



//Settings Menu Page______________________________________________________________________________________________________________________________________________
void Settings() {
  pushStyle();
  textFont(gillbold);
  textAlign(CENTER);
  textSize(70);
  fill(255, 255, 0);
  text("Settings", width/2, height/5);
  popStyle();

  pushStyle();
  rectMode(CENTER);
  strokeWeight(5);
  stroke(255, 255, 0);
  fill(255, 255, 0, 25);
  rect(width/2, height/2+(height*0.092592592), width*0.390625, height*0.601851, 10);
  popStyle();

  pushStyle();
  rectMode(CENTER);
  strokeWeight(5);
  stroke(255, 255, 0);
  fill(255, 255, 0, 50);
  rect(width/2, height*0.3703, width*0.3645, height*0.0925, 10);
  popStyle();

  pushStyle();
  textAlign(CENTER);
  textFont(gill);
  textSize(width/30);
  text("Resolution", width/2-(width*0.10416666), height*0.3935185);
  popStyle();

  pushStyle();
  strokeWeight(5);
  stroke(255, 255, 0);
  fill(255, 0, 0, 100);
  rect(rectX3, rectY3, rectW3, rectH3, 10);
  popStyle();

  pushStyle();
  textAlign(CENTER);
  textFont(gill);
  textSize(width/30);
  text(width+"x"+height, width/2+(width*0.1041666), height*0.394351);
  popStyle();

  pushStyle();
  strokeWeight(5);
  stroke(255, 255, 0);
  if ((mouseX>rectX2)&&(mouseX<rectX2+rectW2)&&(mouseY>rectY2)&&(mouseY<rectY2+rectH2)&&(page==2)) {
    fill(255, 0, 0, 50);
    if (mousePressed==true) {
      page=0;
    }
  } else {
    fill(255, 255, 0, 25);
  }

  rect(rectX2, rectY2, rectW2, rectH2, 10);
  popStyle();

  pushStyle();
  textSize(width/32);
  text("Back", width*0.0390625, height*0.11574);
  popStyle();
}


//Start menu_____________________________________________________________________________________________
void selection() {

  //Stop Music and rewind when on the start menu________________________
  sou.pause();
  sou.rewind();

  lou.pause();
  lou.rewind();



  rectX4=width/2;
  rectY4=height*0.09259;
  rectW4=width*0.18229;
  rectH4=height*0.09259;

  pushStyle();
  textSize(60);
  textAlign(CENTER);
  text("Map", width*0.14322, height*0.277777);
  text("Vehicle", width*0.45052, height*0.277777);
  text("Upgrades", width*0.83333, height*0.277777);
  popStyle();




  for (int i=0; i<3; i+=1) {
    rectX5=width*0.83333;
    rectY5[i]=(height*0.39)+((height*0.19444444)*i);
    rectW5= width*0.16927;
    rectH5=height*0.1851851;
    pushStyle();
    rectMode(CENTER);
    strokeWeight(5);
    stroke(255, 255, 0);
    if ((mouseX>rectX5-rectW5/2) && (mouseX<rectX5+rectW5/2) && (mouseY>rectY5[i]-rectH5/2)&&(mouseY<rectY5[i]+rectH5/2)) {
      fill(255, 0, 0, 50);
    } else {
      fill(255, 255, 0, 50);
    }
    rect(rectX5, rectY5[i], rectW5, rectH5, 10);
    popStyle();
  }


  pushStyle();
  textSize(width/128);
  for (int i=0; i<3; i++) {
    text("Level: " + lvl[i], width*0.7552, (height*0.32407)+(i*(height*0.194444)));
  }
  popStyle();

  pushStyle();
  textSize(width/128);
  textAlign(RIGHT);
  for (int i=0; i<3; i++) {
    text("Price: " + price[i]+"$", width*0.915, (height*0.32407)+(i*(height*0.194444)));
  }
  popStyle();

  pushStyle();
  textSize(width/42.6667);
  textAlign(LEFT);
  text("Money: "+money+"$", width*0.70, 100);
  text("Record: "+  (Collections.max(record))+"m", width*0.70, 150);
  popStyle();

  pushStyle();

  rectMode(CENTER);
  strokeWeight(5);
  stroke(255, 255, 0);
  fill(255, 255, 0, 25);


  for (int i=0; i<2; i++) {
    rectX6=width*0.14322;
    rectY6[i]=(height*0.42)+((height*0.23)*i);
    rectW6= width*0.171875;
    rectH6=height*0.23;
    stroke(255, 255, 0);
    if ((mouseX>rectX6-rectW6/2) && (mouseX<rectX6+rectW6/2) && (mouseY>rectY6[i]-rectH6/2)&&(mouseY<rectY6[i]+rectH6/2)) {
      fill(255, 0, 0, 50);
    } else {
      fill(255, 255, 0, 50);
    }

    rect(rectX6, rectY6[i], rectW6, rectH6, 10);

    if (moon_map==true) {
      pushStyle();
      fill(0, 255, 0, 50);
      rect(rectX6, rectY6[1], rectW6, rectH6, 10);
      popStyle();
    } else if (moon_map==false) {
      pushStyle();
      fill(0, 255, 0, 50);
      rect(rectX6, rectY6[0], rectW6, rectH6, 10);
      popStyle();
    }
  }
  popStyle();



  pushStyle();
  rectMode(CENTER);
  strokeWeight(5);
  stroke(255, 255, 0);
  fill(255, 255, 0, 25);
  for (int i=0; i<2; i++) {
    rectX7=width*0.45052;
    rectY7[i]=(height*0.42)+((height*0.23)*i);
    rectW7= width*0.171875;
    rectH7=height*0.23;
    stroke(255, 255, 0);
    if ((mouseX>rectX7-rectW7/2) && (mouseX<rectX7+rectW7/2) && (mouseY>rectY7[i]-rectH7/2)&&(mouseY<rectY7[i]+rectH7/2)) {
      fill(255, 0, 0, 50);
    } else {
      fill(255, 255, 0, 50);
    }
    rect(rectX7, rectY7[i], rectW7, rectH7, 10);

    if (rally_car==true) {
      pushStyle();
      fill(0, 255, 0, 50);
      rect(rectX7, rectY7[1], rectW7, rectH7, 10);
      popStyle();
    } else if (rally_car==false) {
      pushStyle();
      fill(0, 255, 0, 50);
      rect(rectX7, rectY7[0], rectW7, rectH7, 10);
      popStyle();
    }
  }
  popStyle();



  pushStyle();

  imageMode(CENTER);
  image(car, width*0.445, height*0.415, width*0.208333333, 450);
  image(rally, width*0.45052, height*0.65, width*0.15, 150);
  image(map, width*0.14322, height*0.40, width*0.104166, height*0.17129);
  image(moon, width*0.14322, height*0.63, width*0.104166, height*0.17129);
  image(piston, width*0.83333, height/2-(height*0.09259), width*0.078125, height*0.13888);
  image(tire, width*0.83333, height/2+(height*0.09259), width*0.078125, height*0.13888); 
  image(suspension, width*0.83333, height/2+(height*0.29), width*0.078125, height*0.13888);
  popStyle();


  pushStyle();
  rectMode(CENTER);
  strokeWeight(5);
  stroke(255, 255, 0);
  if ((mouseX>rectX4-(rectW4/2))&&(mouseX<rectX4+(rectW4/2))&&(mouseY>rectY4-(rectH4/2))&&(mouseY<rectY4+(rectH4/2))&&(page==1)) {
    fill(255, 0, 0, 50);
  } else {
    fill(255, 255, 0, 25);
  }

  rect(rectX4, rectY4, rectW4, rectH4);
  popStyle();

  pushStyle();
  textAlign(CENTER);
  textSize(width/32);
  text("START", width/2, height*0.1157);
  popStyle();


  pushStyle();
  textAlign(CENTER);
  textSize(width/128);
  text("Price: 500$", width*0.14322, height*0.7407);
  text("Price: 500$", width*0.455729, height*0.7407);
  popStyle();



  pushStyle();
  strokeWeight(5);
  stroke(255, 255, 0);
  if ((mouseX>rectX2)&&(mouseX<rectX2+rectW2)&&(mouseY>rectY2)&&(mouseY<rectY2+rectH2)&&(page==1)) {
    fill(255, 0, 0, 50);
    if (mousePressed==true) {
      page=0;
    }
  } else {
    fill(255, 255, 0, 25);
  }

  rect(rectX2, rectY2, rectW2, rectH2, 10);
  popStyle();

  pushStyle();
  textSize(width/32);
  text("Back", width*0.0390625, height*0.11574);
  popStyle();
}



void myMethod() {
  //Creating new classes at specifed locations_____________________________
  ground=new Surface();
  //fuel= new Fuel(500, 200);
  fuel= new ArrayList<Fuel>();
  box= new Box(width*0.416666666, height*0.152777777, width*0.078125, 50);
  wheelrevolute= new WheelRevolute(width*0.390625, height*0.185185, 20);
  wheelrevolute1= new WheelRevolute(width*0.442708333, height*0.185185, 20);
  person= new Person (width*0.416666666, height*0.1296296, width*0.0078125, height*0.00925925);
  coin = new ArrayList<Coin>();
  gas=200;

  //Applying upgrades____________________________________________________________________________________
  if (rally_car==true) {
    wheelrevolute.joint.setSpringFrequencyHz(freq);
    wheelrevolute.joint.setSpringDampingRatio(damp);
    wheelrevolute1.joint.setSpringFrequencyHz(freq);
    wheelrevolute1.joint.setSpringDampingRatio(damp);
    wheelrevolute.joint.setMaxMotorTorque((800+(torque*2)));
    wheelrevolute1.joint.setMaxMotorTorque((800+(torque*2)));
    wheelrevolute.wheels1.body.getFixtureList().setFriction(fric*1.5);
    wheelrevolute1.wheels1.body.getFixtureList().setFriction(fric*1.5);
    wheelrevolute.wheels1.body.getFixtureList().setDensity(densTire);
    wheelrevolute1.wheels1.body.getFixtureList().setDensity(densTire);
    box.body.getFixtureList().setDensity(densCar+1);
  } else {
    wheelrevolute.joint.setSpringFrequencyHz(freq);
    wheelrevolute.joint.setSpringDampingRatio(damp);
    wheelrevolute1.joint.setSpringFrequencyHz(freq);
    wheelrevolute1.joint.setSpringDampingRatio(damp);
    wheelrevolute.joint.setMaxMotorTorque(700+torque);
    wheelrevolute1.joint.setMaxMotorTorque(700+torque);
    wheelrevolute.wheels1.body.getFixtureList().setFriction(fric);
    wheelrevolute1.wheels1.body.getFixtureList().setFriction(fric);
    wheelrevolute.wheels1.body.getFixtureList().setDensity(densTire);
    wheelrevolute1.wheels1.body.getFixtureList().setDensity(densTire);
    box.body.getFixtureList().setDensity(densCar+1);
  }

  //Loop soundtrack_____________________________________
  if (moon_map==true) {
    lou.loop();
  } else {
    sou.loop();
  }

  //println(box.body.getFixtureList().getDensity());

  //wheelrevolute.wheels1.body.getFixtureList().setDensity(dens);
  //wheelrevolute1.wheels1.body.getFixtureList().setDensity(dens);
  //println("Torque: "+torque);
  //println("Friction: "+fric);
  //println("engine: "+(engineSpeed+4));
  //println("freq: "+freq);
  //println("damp: "+damp);
  //println("dens: "+(densCar));
  //println("densTire: "+(densTire));
}


//Destroy function that destroys all the physics bodies___________________________________________________-
void destroy() {
  ArrayList<Body> bd;
  bd= new ArrayList<Body>();

  if (tp==true) {
    for (Body b=box2d.world.getBodyList(); b!=null; b=b.getNext()) {
      bd.add(b);
    }
    for (int i=bd.size()-1; i>=0; i--) {
      Body l= bd.get(i);
      box2d.destroyBody(l);
      bd.remove(l);
    }
  }
}

//Upgrade functions that change the box2d values___________________________________________________________________________________________________________________________________
void engine() {
  engineSpeed=engineSpeed*1.5;
  torque=torque*1.59;
  //wheelrevolute.joint.setMaxMotorTorque(800+torque);
  //wheelrevolute1.joint.setMaxMotorTorque(800+torque);
  densCar=densCar+0.175;
}

void TIRE() {
  additive*=2;
  fric=fric+additive;
  densTire=densTire+0.05;
}

void suspension() {
  freq=freq*1.15;
  damp=damp+0.1;
  //wheelrevolute.joint.setSpringFrequencyHz(freq);
  //wheelrevolute.joint.setSpringDampingRatio(damp);
  //wheelrevolute1.joint.setSpringFrequencyHz(freq);
  //wheelrevolute1.joint.setSpringDampingRatio(damp);
}

void music() {
  cou.loop();
}
