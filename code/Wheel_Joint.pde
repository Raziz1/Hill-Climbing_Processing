

class WheelRevolute {

  WheelJoint joint;

  Wheel wheels1;
  Wheel wheels2;
  WheelRevolute(float x, float y, float r) {


    //Define joint as between two bodies
    WheelJointDef f= new WheelJointDef();
    wheels1= new Wheel(x, y, r);
    f.initialize(box.body, wheels1.body, wheels1.body.getPosition(), new Vec2(0, 1));

    f.frequencyHz=1.5;
    f.dampingRatio=0.1;
    f.motorSpeed=PI*-4;
    f.maxMotorTorque=800;
    f.enableMotor=false;
    joint=(WheelJoint)box2d.world.createJoint(f);
  }

  void toggleMotor() {
    joint.enableMotor(!joint.isMotorEnabled());
  }



  void display() {
    wheels1.display();
  }
}
