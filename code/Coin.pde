class Coin {
  //Keep track of Body and a radius
  Body body;
  float r=20;
  boolean delete=false;

  Coin(float x, float y, float r) {
    makeBody(x, y, r);
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



  //Adds the coin to the Box2D world
  void makeBody(float x, float y, float r) {
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
    fd.restitution=0.1;


    //Attach fixture to body
    body.createFixture(fd);
  }

  void display() {
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
