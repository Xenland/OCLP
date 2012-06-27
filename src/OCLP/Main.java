package OCLP;

import com.jme3.app.SimpleApplication;


public class Main extends SimpleApplication {
    
    Buildings buildings;
    public static Main app;
    
    public static void main(String[] args) {
        app = new Main();
        app.start();        
    }

    @Override
    public void simpleInitApp() {
        //Set up the lighting, fog and position the camera
        Environment env = new Environment(assetManager, rootNode, viewPort);

        
        Road road = new Road(assetManager, rootNode);
            
        //set up buildings
        buildings = new Buildings(assetManager, rootNode);
        
        //Add the main player(Bird)
        flyCam.setEnabled(false);
        Bird bird = new Bird(assetManager,rootNode,cam,inputManager);
        buildings.startTimer();
    }

    @Override
    public void simpleUpdate(float tpf) {
        buildings.checkOutOfBounds();
    }
    
    
    
    
    

   
}
