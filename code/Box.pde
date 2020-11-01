
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
    fill(175);
    stroke(0);
    //rect(0, 0, w, h);
    popMatrix();
    popStyle();

    pushMatrix();
    translate(pos.x, pos.y); //Translates the origin coordinates for rotation
    rotate(-a);
    if (rally_car==true) {
      image(rally, -width*0.05, -height*0.070, width*0.1, height*0.132);
    } else {
      image(car, -width*0.0802083333, -height*0.1851851, width*0.15625, height*0.37037);
    }
    popMatrix();
  }




  // This function adds the rectangle to the box2d world
  void makeBody(Vec2 center, float w_, float h_) {
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
    fd.restitution = 0.1;

    body.createFixture(fd);
  }
}
