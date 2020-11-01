

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
    Vec2 jointPos= new Vec2(ts.x, ts.y+(personHeight/4.5));
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
  void display() {
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

  void makeBody(float x, float y, float r) {
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
    fd.density=0.1;
    fd.friction=1;
    fd.restitution=0.3;



    //Attach fixture to body
    body.createFixture(fd);
  }

  void display() {
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
    image(face, -(width*0.018229), -(height*0.032407), width*0.03645, width*0.03645);
    popMatrix();
  }
}



//Torso____________________________________________________________________________________________
class Torso {
  Body body;
  float h=height*0.02314;
  float w=width*0.0078125;
  Torso(float x, float y) {
    makeBody(new Vec2(x, y), h, w);
    body.setUserData(this);
  }

  void makeBody(Vec2 center, float h, float w) {
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
    fd.density = 0.1;
    fd.friction = 1;
    fd.restitution = 0.1;

    body.createFixture(fd);
  }

  void display() {
    pushMatrix();
    pushStyle();
    // We look at each body and get its screen position
    Vec2 pos = box2d.getBodyPixelCoord(body);
    // Get its angle of rotation
    float a = body.getAngle();

    rectMode(PConstants.CENTER);
    translate(pos.x, pos.y);
    rotate(-a);
    fill(#ac3d47);
    noStroke();
    rect(0, 0, w, h);
    popMatrix();
    popStyle();
  }
}
