
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
  void makeBody(float x, float y, float r) {
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
    fd.restitution=0.2;


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
