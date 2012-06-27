/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OCLP;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl.ControlDirection;

/**
 *
 * @author Kevin
 */
public class Bird implements AnalogListener{

    InputManager inputManager;    
    Node bird = new Node();
    private float speed = 1.0f;
    private int rot = 0;
    private CameraNode camNode;
    
    public Bird(AssetManager assetManager, Node rootNode, Camera cam, InputManager inputManager){
        this.inputManager = inputManager;    
   
        
        //Load up & attach our bird
        Spatial birdModel = assetManager.loadModel("Models/bird.j3o");
        rootNode.attachChild(birdModel);
        birdModel.setLocalScale(0.3f, 0.3f, 0.3f);
        bird.attachChild(birdModel);
        bird.move(0, 1, 0);

        
        //Setup input
        setUpKeys();
        
        //Setup the Camera
        // Disable the default flyby cam
        //create the camera Node
        camNode = new CameraNode("Camera Node", cam);
        //This mode means that camera copies the movements of the target:
        camNode.setControlDir(ControlDirection.SpatialToCamera);
        //Attach the camNode to the target:
        bird.attachChild(camNode);
        //Move camNode, e.g. behind and above the target:
        camNode.setLocalTranslation(new Vector3f(-2.5f, 1.75f, 0));
        //Rotate the camNode to look at the target:
        camNode.lookAt(bird.getLocalTranslation(), Vector3f.UNIT_Y);
            
        rootNode.attachChild(bird);                     
    }
    
    
    private void setUpKeys(){
        inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addListener(this, new String[]{"Left", "Right"});
        inputManager.addListener(this, "rotateUp", "rotateDown");

    }

    public void onAnalog(String name, float value, float tpf) {
        if(name.equals("Left")){
            if(bird.getLocalTranslation().z > -2.5){
                bird.move(0, 0, -speed*tpf);
            }   
        }else if(name.equals("Right")){
            if(bird.getLocalTranslation().z < 2.5){
                bird.move(0, 0, speed*tpf);
            }              
        }
        
        if (name.equals("rotateUp") && rot!=5) {
      rot+=1;
      bird.rotate(0, 0, 2 * tpf);
    }
      if (name.equals("rotateDown")&& rot!=-5) {
      rot+=-1;
      bird.rotate(0, 0, -2 * tpf);
    }
      System.out.println(rot);
    
}

}
