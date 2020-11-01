import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import shiffman.box2d.*; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.joints.*; 
import org.jbox2d.collision.shapes.*; 
import org.jbox2d.collision.shapes.Shape; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.*; 
import org.jbox2d.dynamics.contacts.*; 
import java.util.*; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Full_game extends PApplet {

//Import physics library BOX2D












//Import Minim sound library________________________________________







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
float rectW= width*0.260416666f;
float rectH=height*0.09259259f;
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

float freq=1.5f;
float damp=0.1f;

float torque=100;

float fric=1;

float additive=0.1f;

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


public void setup() {
  //Using 3d render technique
  
  
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
  rectY4=height*0.09259f;
  rectW4=width*0.18229f;
  rectH4=height*0.09259f;

  //Initialize box2d physics and create the world
  box2d= new Box2DProcessing(this);
  box2d.createWorld();
  //Turn on collision listening__________________
  box2d.listenForCollisions();
}


public void draw() {
  background(0xff3b3e47);
  //If start is false the main menu screen is loaded


  if (music2) {
    music();
    music2=false;
  }

  if (start==false) {
    rectX3=(width/2+(width*0.02604166f));
    rectY3=height*0.32407407f;
    rectW3=width*0.15625f;
    rectH3=height*0.09259259f;

    rectX2=width*0.02604f;
    rectY2= height*.04629f;       
    rectW2= width*0.15625f;


    rectH2=height*0.09259f;
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
      rect(width/2, height/2+(height*0.092592592f), width*0.390625f, height*0.601851f, 10);
      popStyle();

      for (int i=1; i<4; i+=1) {
        rectX=width/2;
        rectY[i]=(height/5)+((height*0.1851851f)*i);
        rectW= width*0.260416666f;
        rectH=height*0.09259259f;
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
      text("Start", width/2, height*0.407407407f);
      popStyle();

      pushStyle();
      textAlign(CENTER);
      textFont(gill);
      fill(255, 255, 255);
      text("Settings", width/2, height*0.59259259f);
      popStyle();

      pushStyle();
      textAlign(CENTER);
      textFont(gill);
      fill(255, 255, 255);
      text("How To Play", width/2, height*0.77777777f);
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
      background(0xff030f49);
    } else {
      box2d.setGravity(0, -30);
      background(0xff97e8ff);
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
        image(background, width/2-(width*0.5f), +150, 1920, height/1.3333f);
      } else {
        image(background, s.x-(width*0.5f), +150, 1920, height/1.3333f);
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
    gas-=0.05f;
    pushStyle();
    strokeWeight(5);
    noFill();
    if (s.x>=width/2) {
      rect(s.x-(width*0.442708f), height*0.04629f, width*0.10416f, height*0.04629f);
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
      rect((s.x-(width*0.546875f))+(gas*(width/1920.0f)), height*0.04629f, width*0.10416f, height*0.04629f);
      if (moon_map==true) {
        fill(0xff030f49);
      } else {
        fill(0xff97e8ff);
      }
      rect(s.x-(width*0.59895f), height*0.04629f, width*0.15625f, height*0.04629f);
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
    text("FPS avg: "+frameCount/(millis()/(float)1000) + "\n", s.x-(width*0.052083f), height*0.023148f);
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
      text("Distance: "+distance+"m", s.x+(width*0.26041f), height*0.04629f);
      text("Money: "+money+"$", s.x+(width*0.26041f), height*0.08629f);
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
      image(speedometer, l.x-width/2, height-(height*0.22222f), width*0.243055f, height*0.22222f);
      popStyle();


      pushStyle();
      translate((l.x-width/2)+(width*0.1225f), height-13);
      pushMatrix();
      strokeWeight(5);
      stroke(255, 0, 0);
      //if you are going backwards display as a positive velocity_________________________________________________
      if (vel<0) {
        rotate(-(vel/35));
      } else {
        rotate(vel/35);
      }
      line(0, 0, -(width*0.08214f), 0);
      popStyle();
      popMatrix();
      popMatrix();
    }

    popMatrix();
  }
}






public void keyPressed() {
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


public void keyReleased() {
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
public void beginContact(Contact cp) {
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

public void endContact(Contact cp) {
}



public void mousePressed() {
  if (start==false) {
    for (int i=1; i<=4; i+=1) {
      if ((mouseX>rectX-rectW/2) && (mouseX<rectX+rectW/2) && (mouseY>rectY[1]-rectH/2)&&(mouseY<rectY[1]+rectH/2)&&(page==0)) {
        page=1;
      } else if ((mouseX>rectX-rectW/2) && (mouseX<rectX+rectW/2) && (mouseY>rectY[2]-rectH/2)&&(mouseY<rectY[2]+rectH/2)&&(page==0)) {
        page=2;
        rectX3=(width/2+50);
        rectY3=height*0.32407407f;
        rectW3=width*0.15625f;
        rectH3=height*0.09259259f;
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
public void howtoplay() {
  pushStyle();
  textFont(gillbold);
  textAlign(CENTER);
  textSize(70);
  fill(255, 255, 0);
  text("How To Play", width/2, height/5);
  popStyle();

  pushStyle();
  imageMode(CENTER);
  image(instructionsaccelerations, width*0.18229166f, height*0.37037037f, width*0.49479166f, height*0.46296296f);
  image(instructionscoin, width*0.6510466f, height*0.37037037f, width*0.49479166f, height*0.46296296f);
  image(instructionsrotation, width*0.18229166f, height*0.74074074f, width*0.49479166f, height*0.46296296f);
  image(instructiongas, width*0.6510466f, height*0.74074074f, width*0.49479166f, height*0.46296296f);

  popStyle();

  pushStyle();
  textFont(gill);
  textSize(25);
  fill(255, 255, 255);
  text("Press the left and right arrow keys to accelerate", width*0.26041f, height*0.32407f, 350, 500);
  text("Press the left and right arrow keys to rotate the vehicle", width*0.260416f, height*0.69444f, 350, 500);
  text("Collect coins to spend on vehicle upgrades", width*0.72916f, height*0.32407f, 350, 500);
  text("Run into gas canisters to refuel", width*0.72916f, height*0.69444f, 350, 500);
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
  text("Back", width*0.0390625f, height*0.11574f);
  popStyle();
}



//Settings Menu Page______________________________________________________________________________________________________________________________________________
public void Settings() {
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
  rect(width/2, height/2+(height*0.092592592f), width*0.390625f, height*0.601851f, 10);
  popStyle();

  pushStyle();
  rectMode(CENTER);
  strokeWeight(5);
  stroke(255, 255, 0);
  fill(255, 255, 0, 50);
  rect(width/2, height*0.3703f, width*0.3645f, height*0.0925f, 10);
  popStyle();

  pushStyle();
  textAlign(CENTER);
  textFont(gill);
  textSize(width/30);
  text("Resolution", width/2-(width*0.10416666f), height*0.3935185f);
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
  text(width+"x"+height, width/2+(width*0.1041666f), height*0.394351f);
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
  text("Back", width*0.0390625f, height*0.11574f);
  popStyle();
}


//Start menu_____________________________________________________________________________________________
public void selection() {

  //Stop Music and rewind when on the start menu________________________
  sou.pause();
  sou.rewind();

  lou.pause();
  lou.rewind();



  rectX4=width/2;
  rectY4=height*0.09259f;
  rectW4=width*0.18229f;
  rectH4=height*0.09259f;

  pushStyle();
  textSize(60);
  textAlign(CENTER);
  text("Map", width*0.14322f, height*0.277777f);
  text("Vehicle", width*0.45052f, height*0.277777f);
  text("Upgrades", width*0.83333f, height*0.277777f);
  popStyle();




  for (int i=0; i<3; i+=1) {
    rectX5=width*0.83333f;
    rectY5[i]=(height*0.39f)+((height*0.19444444f)*i);
    rectW5= width*0.16927f;
    rectH5=height*0.1851851f;
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
    text("Level: " + lvl[i], width*0.7552f, (height*0.32407f)+(i*(height*0.194444f)));
  }
  popStyle();

  pushStyle();
  textSize(width/128);
  textAlign(RIGHT);
  for (int i=0; i<3; i++) {
    text("Price: " + price[i]+"$", width*0.915f, (height*0.32407f)+(i*(height*0.194444f)));
  }
  popStyle();

  pushStyle();
  textSize(width/42.6667f);
  textAlign(LEFT);
  text("Money: "+money+"$", width*0.70f, 100);
  text("Record: "+  (Collections.max(record))+"m", width*0.70f, 150);
  popStyle();

  pushStyle();

  rectMode(CENTER);
  strokeWeight(5);
  stroke(255, 255, 0);
  fill(255, 255, 0, 25);


  for (int i=0; i<2; i++) {
    rectX6=width*0.14322f;
    rectY6[i]=(height*0.42f)+((height*0.23f)*i);
    rectW6= width*0.171875f;
    rectH6=height*0.23f;
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
    rectX7=width*0.45052f;
    rectY7[i]=(height*0.42f)+((height*0.23f)*i);
    rectW7= width*0.171875f;
    rectH7=height*0.23f;
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
  image(car, width*0.445f, height*0.415f, width*0.208333333f, 450);
  image(rally, width*0.45052f, height*0.65f, width*0.15f, 150);
  image(map, width*0.14322f, height*0.40f, width*0.104166f, height*0.17129f);
  image(moon, width*0.14322f, height*0.63f, width*0.104166f, height*0.17129f);
  image(piston, width*0.83333f, height/2-(height*0.09259f), width*0.078125f, height*0.13888f);
  image(tire, width*0.83333f, height/2+(height*0.09259f), width*0.078125f, height*0.13888f); 
  image(suspension, width*0.83333f, height/2+(height*0.29f), width*0.078125f, height*0.13888f);
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
  text("START", width/2, height*0.1157f);
  popStyle();


  pushStyle();
  textAlign(CENTER);
  textSize(width/128);
  text("Price: 500$", width*0.14322f, height*0.7407f);
  text("Price: 500$", width*0.455729f, height*0.7407f);
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
  text("Back", width*0.0390625f, height*0.11574f);
  popStyle();
}



public void myMethod() {
  //Creating new classes at specifed locations_____________________________
  ground=new Surface();
  //fuel= new Fuel(500, 200);
  fuel= new ArrayList<Fuel>();
  box= new Box(width*0.416666666f, height*0.152777777f, width*0.078125f, 50);
  wheelrevolute= new WheelRevolute(width*0.390625f, height*0.185185f, 20);
  wheelrevolute1= new WheelRevolute(width*0.442708333f, height*0.185185f, 20);
  person= new Person (width*0.416666666f, height*0.1296296f, width*0.0078125f, height*0.00925925f);
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
    wheelrevolute.wheels1.body.getFixtureList().setFriction(fric*1.5f);
    wheelrevolute1.wheels1.body.getFixtureList().setFriction(fric*1.5f);
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
public void destroy() {
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
public void engine() {
  engineSpeed=engineSpeed*1.5f;
  torque=torque*1.59f;
  //wheelrevolute.joint.setMaxMotorTorque(800+torque);
  //wheelrevolute1.joint.setMaxMotorTorque(800+torque);
  densCar=densCar+0.175f;
}

public void TIRE() {
  additive*=2;
  fric=fric+additive;
  densTire=densTire+0.05f;
}

public void suspension() {
  freq=freq*1.15f;
  damp=damp+0.1f;
  //wheelrevolute.joint.setSpringFrequencyHz(freq);
  //wheelrevolute.joint.setSpringDampingRatio(damp);
  //wheelrevolute1.joint.setSpringFrequencyHz(freq);
  //wheelrevolute1.joint.setSpringDampingRatio(damp);
}

public void music() {
  cou.loop();
}

class Box {


  // We need to keep track of a Body and a width and height
  Body body;
  float w;
  float h; 

  // Constructor
  Box(float x_, float y_, float w_, float h_) {
    float x = x_;
    float y = y_;
    float w=w_;
    float h=h_;
    // Add the box to the box2d world
    makeBody(new Vec2(x, y), w, h);
    body.setUserData(this);
  }




  // Drawing the box
  public void display() {
    pushMatrix();
    pushStyle();
    // We look at each body and get its screen position
    Vec2 pos = box2d.getBodyPixelCoord(body);
    // Get its angle of rotation
    float a = body.getAngle();
    rectMode(PConstants.CENTER);
    translate(pos.x, pos.y);
    rotate(-a);
    fill(175);
    stroke(0);
    //rect(0, 0, w, h);
    popMatrix();
    popStyle();

    pushMatrix();
    translate(pos.x, pos.y); //Translates the origin coordinates for rotation
    rotate(-a);
    if (rally_car==true) {
      image(rally, -width*0.05f, -height*0.070f, width*0.1f, height*0.132f);
    } else {
      image(car, -width*0.0802083333f, -height*0.1851851f, width*0.15625f, height*0.37037f);
    }
    popMatrix();
  }




  // This function adds the rectangle to the box2d world
  public void makeBody(Vec2 center, float w_, float h_) {
    // Define and create the body
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(center));
    bd.angularDamping=1;
    body = box2d.createBody(bd);

    // Define a polygon (this is what we use for a rectangle)
    PolygonShape sd = new PolygonShape();
    float box2dW = box2d.scalarPixelsToWorld(w_/2);
    float box2dH = box2d.scalarPixelsToWorld(h_/2);
    sd.setAsBox(box2dW, box2dH);

    // Define a fixture
    FixtureDef fd = new FixtureDef();
    fd.shape = sd;
    // Parameters that affect physics
    //Increase density while on moon so it's no always floating
    if (moon_map==true) {
      fd.density =densCar*2 ;
    } else {
      fd.density =densCar ;
    }

    fd.friction = 1;
    fd.restitution = 0.1f;

    body.createFixture(fd);
  }
}
class Coin {
  //Keep track of Body and a radius
  Body body;
  float r=20;
  boolean delete=false;

  Coin(float x, float y, float r) {
    makeBody(x, y, r);
    body.setUserData(this);
  }


  public void killBody() {
    box2d.destroyBody(body);
  }

  public void delete() {
    delete = true;
  }

  // Is the particle ready for deletion?
  public boolean done() {
    // Let's find the screen position of the particle
    Vec2 pos = box2d.getBodyPixelCoord(body);
    // Is it off the bottom of the screen?
    if (delete) {
      killBody();
      return true;
    }
    return false;
  }
  // 



  //Adds the coin to the Box2D world
  public void makeBody(float x, float y, float r) {
    BodyDef bd= new BodyDef();
    //Set its position
    bd.position=box2d.coordPixelsToWorld(x, y);
    bd.type=BodyType.DYNAMIC;
    bd.linearDamping=100;
    body=box2d.world.createBody(bd);


    //Make the body's shape a circle
    CircleShape cs= new CircleShape();
    cs.m_radius= box2d.scalarPixelsToWorld(r);

    FixtureDef fd= new FixtureDef();
    fd.shape=cs;

    //Parameters that affect physics
    fd.density=1;
    fd.friction=1;
    fd.restitution=0.1f;


    //Attach fixture to body
    body.createFixture(fd);
  }

  public void display() {
    Vec2 pos= box2d.getBodyPixelCoord(body);
    //Get its angle of rotation
    float a =body.getAngle();
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    fill(175);
    noStroke();
    ellipse(0, 0, r*2, r*2);
    // Add a line so we can see the rotation
    //line(0, 0, r, 0);
    popMatrix();

    pushMatrix();
    translate(pos.x, pos.y); //Translates the origin coordinates for rotation
    rotate(-a);
    image(bit, -20, -20, 40, 40);
    popMatrix();
  }
}

class Fuel {
  Body body;
  float w;
  float h;
  boolean delete = false;
  Fuel(float x, float y) {
    w=30;
    h=45;

    makeBody(new Vec2(x, y), w, h);
    body.setUserData(this);
  }


  public void killBody() {
    box2d.destroyBody(body);
  }

  public void delete() {
    delete = true;
  }

  // Is the particle ready for deletion?
  public boolean done() {
    // Let's find the screen position of the particle
    Vec2 pos = box2d.getBodyPixelCoord(body);
    // Is it off the bottom of the screen?
    if (delete) {
      killBody();
      return true;
    }
    return false;
  }
  // 



  // Drawing the box
  public void display() {
    pushMatrix();
    pushStyle();
    // We look at each body and get its screen position
    Vec2 pos = box2d.getBodyPixelCoord(body);
    // Get its angle of rotation
    float a = body.getAngle();

    rectMode(PConstants.CENTER);
    translate(pos.x, pos.y);
    rotate(-a);
    fill(0xffff0000);
    noStroke();
    rect(0, 0, w, h);
    popMatrix();

    pushMatrix();
    translate(pos.x, pos.y); //Translates the origin coordinates for rotation
    rotate(-a);
    image(jerrycan, -30, -25, 50, 50);
    popStyle();
    popMatrix();
  }


  public void makeBody(Vec2 center, float w, float h) {
    // Define and create the body
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(center));
    body = box2d.createBody(bd);

    // Define a polygon (this is what we use for a rectangle)
    PolygonShape sd = new PolygonShape();
    float box2dW = box2d.scalarPixelsToWorld(w/2);
    float box2dH = box2d.scalarPixelsToWorld(h/2);
    sd.setAsBox(box2dW, box2dH);

    // Define a fixture
    FixtureDef fd = new FixtureDef();
    fd.shape = sd;
    // Parameters that affect physics
    fd.density = 1;
    fd.friction = 1;
    fd.restitution = 0.1f;

    body.createFixture(fd);
  }
}


//Person class attaches the torso and head___________________________________________
class Person {
  RevoluteJoint joint;
  RevoluteJoint joint1;
  DistanceJoint dj;
  float x;
  float y;
  float personWidth;
  float personHeight;
  Torso torso1;
  Head head1;
  Person(float x, float y, float personWidth, float personHeight) {
    RevoluteJointDef rjd = new RevoluteJointDef();
    RevoluteJointDef rjd1 = new RevoluteJointDef();

    torso1= new Torso(x, y-(personHeight/2));
    head1= new Head(x, y-(personHeight+personWidth*1), personWidth*1);

    //Revolute Joint between Torso and head_______________________________
    Vec2 ts= torso1.body.getWorldCenter();
    Vec2 jointPos= new Vec2(ts.x, ts.y+(personHeight/4.5f));
    rjd.initialize(head1.body, torso1.body, jointPos);
    rjd.upperAngle=0*PI;
    rjd.lowerAngle=0*PI;
    rjd.enableLimit=true;


    //Revolute Joint between torso and car_______________________________
    Vec2 bs= box.body.getWorldCenter();
    Vec2 boxPos= new Vec2(bs.x, bs.y+(personHeight/4));
    rjd1.initialize(torso1.body, box.body, boxPos);

    joint1= (RevoluteJoint) box2d.world.createJoint(rjd1);

    joint= (RevoluteJoint) box2d.world.createJoint(rjd);

    //Distance Joint between car and torso______________________________
    DistanceJointDef djd= new DistanceJointDef();
    djd.bodyA=torso1.body;
    djd.bodyB=box.body;
    // Equilibrium length
    djd.length = box2d.scalarPixelsToWorld(75);

    // These properties affect how springy the joint is 
    djd.frequencyHz = 10;  // Try a value less than 5 (0 for no elasticity)
    djd.dampingRatio = 1; // Ranges between 0 and 1

    dj = (DistanceJoint) box2d.world.createJoint(djd);
  }
  public void display() {
    torso1.display();
    head1.display();
  }
}

//Head__________________________________________________________________________________
class Head {
  Body body;
  float r=12;
  Head(float x, float y, float r) {
    makeBody(x, y, r);
    body.setUserData(this);
  }

  public void makeBody(float x, float y, float r) {
    BodyDef bd= new BodyDef();
    bd.position=box2d.coordPixelsToWorld(x, y);
    bd.type=BodyType.DYNAMIC;
    body=box2d.world.createBody(bd);

    //Make the body's shape a circle
    CircleShape cs= new CircleShape();
    cs.m_radius=box2d.scalarPixelsToWorld(r);

    FixtureDef fd= new FixtureDef();
    fd.shape=cs;
    //Parameters that affect physics
    fd.density=0.1f;
    fd.friction=1;
    fd.restitution=0.3f;



    //Attach fixture to body
    body.createFixture(fd);
  }

  public void display() {
    Vec2 pos= box2d.getBodyPixelCoord(body);
    //Get its angle of rotation
    float a =body.getAngle();
    pushMatrix();
    pushStyle();
    translate(pos.x, pos.y);
    rotate(-a);
    fill(200);
    noStroke();
    //ellipse(0, 0, r*2, r*2);

    popMatrix();
    popStyle();

    pushMatrix();
    translate(pos.x, pos.y); //Translates the origin coordinates for rotation
    rotate(-a);
    image(face, -(width*0.018229f), -(height*0.032407f), width*0.03645f, width*0.03645f);
    popMatrix();
  }
}



//Torso____________________________________________________________________________________________
class Torso {
  Body body;
  float h=height*0.02314f;
  float w=width*0.0078125f;
  Torso(float x, float y) {
    makeBody(new Vec2(x, y), h, w);
    body.setUserData(this);
  }

  public void makeBody(Vec2 center, float h, float w) {
    // Define and create the body
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(center));
    body = box2d.createBody(bd);

    // Define a polygon (this is what we use for a rectangle)
    PolygonShape sd = new PolygonShape();
    float box2dW = box2d.scalarPixelsToWorld(w/2);
    float box2dH = box2d.scalarPixelsToWorld(h/2);
    sd.setAsBox(box2dW, box2dH);

    // Define a fixture
    FixtureDef fd = new FixtureDef();
    fd.shape = sd;
    // Parameters that affect physics
    fd.density = 0.1f;
    fd.friction = 1;
    fd.restitution = 0.1f;

    body.createFixture(fd);
  }

  public void display() {
    pushMatrix();
    pushStyle();
    // We look at each body and get its screen position
    Vec2 pos = box2d.getBodyPixelCoord(body);
    // Get its angle of rotation
    float a = body.getAngle();

    rectMode(PConstants.CENTER);
    translate(pos.x, pos.y);
    rotate(-a);
    fill(0xffac3d47);
    noStroke();
    rect(0, 0, w, h);
    popMatrix();
    popStyle();
  }
}



class Surface {
  //Keep track of all of the surface points
  ArrayList<Vec2> ground; 
  float xoff=0.0f;
  ArrayList<Vec2> line;
  ArrayList<Coin> coin;
  ArrayList<Fuel> fuel;

  Surface() {
    ground= new ArrayList<Vec2>();
    line = new ArrayList<Vec2>();
    fuel= new ArrayList<Fuel>();
    coin = new ArrayList<Coin>();

    //Box2d uses to put the surface in its world
    ChainShape chain= new ChainShape();
    //Perlin noise argument
    for (float x=0; x<width*100; x+=7) {
      float y;

      //Increases height of hills as distance increases_______________________________________________________________________________________
      if (x<width*2) {
        //Makes the beginning easier for the first upgrades__________________________________
        y= map(noise(xoff), 0, 1.3f, 350, 800);
      } else if (x<width*6 && x>width*2) {
        y= map(noise(xoff), 0, 1.2f, 350, 800);
      } else if (x<width*10 && x>width*6) {
        y= map(noise(xoff), 0, 1.1f, 350, 800);
        //Now it becomes harder
      } else if (x>width*10 && x<width*20) {
        y= map(noise(xoff), 0, 0.9f, 350, 800);
      } else if (x>width*20 && x<width*30) {
        y= map(noise(xoff), 0, 0.8f, 350, 800);
      } else if (x>width*30 && x<width*40) {
        y= map(noise(xoff), 0, 0.7f, 350, 800);
      } else if (x>width*40 && x<width*100) {
        y= map(noise(xoff), 0, 0.6f, 350, 800);
      } else {
        y= map(noise(xoff), 0, 1, 350, 800);
      }

      //Draws a hill at the beginning so you can't drive past it _______________________________________________________________________________
      if (x==0) {
        ground.add(new Vec2(x, y-600));
      } else {
        ground.add(new Vec2(x, y));
      }


      xoff+=0.01f;


      //Drawing ground using thick lines_______________________________________________________________________________________________________
      if (x%35==0) {
        line.add(new Vec2(x, y));
      }

      //Adding fuel tanks every certain distance________________________________________________________________________________________________
      if (x%8400==0) {
        Fuel f= new Fuel(x, y-100);
        fuel.add(f);
      }
      if (x%2100==0) {
        Coin c= new Coin(x, y-25, 10);
        coin.add(c);
      }
    }







    //Build an array of vertices in Box2D coordinates (Converts each vetex to Box2D World Coordinates)_____________________________________________________________
    Vec2[] vertices= new Vec2[ground.size()];
    for (int i=0; i<vertices.length; i++) {
      Vec2 edge = box2d.coordPixelsToWorld(ground.get(i));
      vertices[i]=edge;
    }

    //Create chain!
    chain.createChain(vertices, vertices.length);

    //Edge chain is now attached to a body via a fixture____________________________________________________________________________________________________________
    BodyDef bd= new BodyDef();
    bd.type=BodyType.STATIC;
    bd.position.set(0.0f, 0.0f);
    bd.awake=false;
    Body body= box2d.createBody(bd);

    //Shortcut, we coud define a fixture if we want to specify frictions, restitution, etc.________________________________________________________________________
    body.createFixture(chain, 1);
    body.setUserData(this);
  }


  //Draw Loop_________________________________________________________________________________________________________________________________________________________
  public void display() {


    pushStyle();
    for (Vec2 l : line) {
      strokeWeight(35);
      if (moon_map==true) {
        stroke(0xff9392cb);
      } else {
        stroke(0xff964b00);
      }
      line(l.x, l.y+8, l.x, height);
    }
    popStyle();

    pushStyle();
    for (int i = fuel.size()-1; i >= 0; i--) {
      Fuel f = fuel.get(i);
      f.display();
      // Particles that leave the screen, we delete them
      // (note they have to be deleted from both the box2d world and our list
      if (f.done()) {
        fuel.remove(i);
      }
    }

    popStyle();

    pushStyle();
    strokeWeight(25);
    if (moon_map==true) {
      stroke(0xffded6d4);
    } else {
      stroke(0xff2FC125);
    }
    noFill();
    beginShape();
    for (Vec2 v : ground) {
      vertex(v.x, v.y+5);
    }
    endShape();
    popStyle();

    pushStyle();
    for (int i = coin.size()-1; i >= 0; i--) {
      Coin c = coin.get(i);
      c.display();
      // Particles that leave the screen, we delete them
      // (note they have to be deleted from both the box2d world and our list
      if (c.done()) {
        coin.remove(i);
      }
    }

    popStyle();
  }
}

class Wheel {

  //Keep track of Body and a radius
  Body body;
  ;
  float r=width/96;
  Wheel(float x, float y, float r) {

    makeBody(x, y, r);
    body.setUserData(this);
  }



  //Adds the wheel to the Box2D world
  public void makeBody(float x, float y, float r) {
    BodyDef bd= new BodyDef();
    //Set its position
    bd.position=box2d.coordPixelsToWorld(x, y);
    bd.type=BodyType.DYNAMIC;
    bd.linearDamping=1;
    body=box2d.world.createBody(bd);


    //Make the body's shape a circle
    CircleShape cs= new CircleShape();
    cs.m_radius= box2d.scalarPixelsToWorld(r);

    FixtureDef fd= new FixtureDef();
    fd.shape=cs;

    //Parameters that affect physics
    fd.density=densTire;
    fd.friction=1;
    fd.restitution=0.2f;


    //Attach fixture to body
    body.createFixture(fd);
  }



  public void display() {

    Vec2 pos= box2d.getBodyPixelCoord(body);
    //Get its angle of rotation
    float a =body.getAngle();
    pushMatrix();
    pushStyle();
    translate(pos.x, pos.y);
    rotate(-a);
    fill(175);
    noStroke();
    ellipse(0, 0, r*2, r*2);
    // Add a line so we can see the rotation
    //line(0, 0, r, 0);
    popStyle();
    popMatrix();



    pushMatrix();
    translate(pos.x, pos.y); //Translates the origin coordinates for rotation
    rotate(-a);
    pushStyle();
    imageMode(CORNER);
    image(wheel, -r, -r, r*2, r*2);
    popStyle();
    popMatrix();
  }
}


class WheelRevolute {

  WheelJoint joint;

  Wheel wheels1;
  Wheel wheels2;
  WheelRevolute(float x, float y, float r) {


    //Define joint as between two bodies
    WheelJointDef f= new WheelJointDef();
    wheels1= new Wheel(x, y, r);
    f.initialize(box.body, wheels1.body, wheels1.body.getPosition(), new Vec2(0, 1));

    f.frequencyHz=1.5f;
    f.dampingRatio=0.1f;
    f.motorSpeed=PI*-4;
    f.maxMotorTorque=800;
    f.enableMotor=false;
    joint=(WheelJoint)box2d.world.createJoint(f);
  }

  public void toggleMotor() {
    joint.enableMotor(!joint.isMotorEnabled());
  }



  public void display() {
    wheels1.display();
  }
}
  public void settings() {  fullScreen(P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Full_game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
