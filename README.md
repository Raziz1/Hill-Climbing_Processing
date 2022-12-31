# Hill-Climbing_Processing 🌄🚗💰⛽
Hill-Climbing remake using Processing & BOX2D

<p> 
    <img width = 480 height = 270 align='Right' src="https://github.com/Raziz1/Hill-Climbing_Processing/blob/main/image/hill_climb.png? raw=true" >
</p> 


## Libraries 📚
* [Processing Core Library](https://processing.org/)
    - [The Coding Train "Simulating Natural Systems with Processing"](https://www.youtube.com/c/TheCodingTrain/playlists?view=50&flow=grid&shelf_id=9)
* [Box2D Physics Engine](https://box2d.org/)
    - [Box2D PDF Resource](https://github.com/Raziz1/Hill-Climbing_Processing/blob/main/image/18-Box2D.pdf)
    - [Box2D GITHUB Processing Examples](https://github.com/shiffman/Box2D-for-Processing/tree/master/Box2D-for-Processing/dist/box2d_processing/examples)
    - [The Coding Train Box2D](https://www.youtube.com/playlist?list=PLRqwX-V7Uu6Zy4FyZtCHsZc_K0BrXzxfE)
* [Minim Audio Library](http://code.compartmental.net/tools/minim/)

## Overview 
The following project is written in Processing which was built around JAVA. The game isn't fully complete but can still be played. 
### Features
* 2 maps
* 2 cars
* Upgradable parts
* Coin system
* Fuel System
* Resizable Window
* Soundtrack Credits @ [THREESIXTY](https://open.spotify.com/artist/3uCyergxu3WFt6R1qGe3V5)

## Other Notes 📝
* The terrain is generated using a Box2D chainshape and perlin noise. It isn't truly infinite because I didn't implement the dynamic construction of the chainshape. This resulted in not ideal performance on my machine.
    1. This can be done by drawing and creating a chainshape size that is only the size of the viewing window
    2. Check the distance between the car and the position of the last element in the chainshape
    3. If it is below a certain threshold then expand the ground array list and add a grouping of new vector points to the end 
    4. Use perlin offset noise to randomize their position
    5. Expand the array containing the vertices with BOX2D coordinates
    6. Recreate the BOX2D body and attach the fixture
    7. To improve performance we can draw only the ground that is visible in the viewing window
* The car is attached to the wheels using a wheel joint (Made of a revolute joint, and distance joint)
* Was converted to an Android Project and can run on an android device (Not Included)
* Contains an exported application file
* Does not contain the ability to save progress when the program is exited
* (.pde) files are to be opened in the Processing application
* In the Processing application click on File > Export Application to export the project
    - Make sure you have the latest version of JAVA on your computer
