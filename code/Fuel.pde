
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


  void killBody() {
    box2d.destroyBody(body);
  }

  void delete() {
    delete = true;
  }

  // Is the particle ready for deletion?
  boolean done() {
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
    fill(#ff0000);
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


  void makeBody(Vec2 center, float w, float h) {
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
    fd.restitution = 0.1;

    body.createFixture(fd);
  }
}
