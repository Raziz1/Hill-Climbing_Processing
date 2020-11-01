


class Surface {
  //Keep track of all of the surface points
  ArrayList<Vec2> ground; 
  float xoff=0.0;
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
        y= map(noise(xoff), 0, 1.3, 350, 800);
      } else if (x<width*6 && x>width*2) {
        y= map(noise(xoff), 0, 1.2, 350, 800);
      } else if (x<width*10 && x>width*6) {
        y= map(noise(xoff), 0, 1.1, 350, 800);
        //Now it becomes harder
      } else if (x>width*10 && x<width*20) {
        y= map(noise(xoff), 0, 0.9, 350, 800);
      } else if (x>width*20 && x<width*30) {
        y= map(noise(xoff), 0, 0.8, 350, 800);
      } else if (x>width*30 && x<width*40) {
        y= map(noise(xoff), 0, 0.7, 350, 800);
      } else if (x>width*40 && x<width*100) {
        y= map(noise(xoff), 0, 0.6, 350, 800);
      } else {
        y= map(noise(xoff), 0, 1, 350, 800);
      }

      //Draws a hill at the beginning so you can't drive past it _______________________________________________________________________________
      if (x==0) {
        ground.add(new Vec2(x, y-600));
      } else {
        ground.add(new Vec2(x, y));
      }


      xoff+=0.01;


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
  void display() {


    pushStyle();
    for (Vec2 l : line) {
      strokeWeight(35);
      if (moon_map==true) {
        stroke(#9392cb);
      } else {
        stroke(#964b00);
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
      stroke(#ded6d4);
    } else {
      stroke(#2FC125);
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
