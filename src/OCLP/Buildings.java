package OCLP;

import OCLP.Controls.MoveControl;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;
import com.jme3.math.Vector3f;
import com.jme3.asset.*;
import com.jme3.math.Vector2f;
import com.jme3.scene.Spatial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Callable;
import javax.swing.Timer;


/**
*
* @author xenland
*/
public class Buildings implements ActionListener {
    private AssetManager assetManager;
    private Node rootNode;
    
    private Node buildings;
    private int buildingNR = 0;
    
    //Random generator
    Random random = new Random();
    Timer timer;
    
    public Buildings(AssetManager assetManager, Node rootNode){
        //Give access to assetManager for other functions to use it in this class
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        
        timer = new Timer(2500,this);
        //execute class
        SpawnBuildings();
    }
    
    public void startTimer(){
        timer.start();
    }
    
    public void stopTimer(){
        timer.stop();
    }
    
    private void SpawnBuildings(){
        buildings = new Node();
        
        //Spawn buildings
        Spatial geometry_building = createBuilding();
        geometry_building.setUserData("isAlive", true);
        geometry_building.addControl(new MoveControl());
        
        buildings.attachChild(geometry_building);
        
        rootNode.attachChild(buildings);
    }
    
    private Geometry createBuilding(){
        Vector3f boxVector = new Vector3f(9, 0.9f, 0.2f);
        Box building = new Box(boxVector, 0.2f, 3f, 0.2f);
        building.scaleTextureCoordinates(new Vector2f(1f,10f));
        Geometry geometry_building = new Geometry("Building" + buildingNR, building);        
        if(random.nextBoolean() == true){
            geometry_building.setLocalTranslation(0f,2.05f,random.nextFloat()*2.5f);
        }else{
            geometry_building.setLocalTranslation(0f,2.05f,random.nextFloat()*-2.5f);
        }
        
        geometry_building.setMaterial(assetManager.loadMaterial("Materials/Building.j3m"));

        return geometry_building;
    }

    protected void checkOutOfBounds() {
        for(Spatial s: buildings.getChildren()){
            boolean data = (Boolean)s.getUserData("isAlive");           
            if(data == false){
                buildings.detachChild(s);
            }           
        }
    }

    public void actionPerformed(ActionEvent e) {
        Main.app.enqueue(new Callable<Spatial>() {

            public Spatial call() throws Exception {
                 //Spawn buildings
                Spatial geometry_building = createBuilding();
                geometry_building.setUserData("isAlive", true);
                geometry_building.addControl(new MoveControl());

                buildings.attachChild(geometry_building);
                return null;
            }  
            
        });
    }
    
   
   
}