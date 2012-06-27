package OCLP;

import OCLP.Controls.MoveControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.asset.*;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;


public class Road{
    Geometry geometry_ground;
    
    protected Road(AssetManager assetManager,Node rootNode){
        Box ground = new Box(Vector3f.ZERO, 10000, 0.1f, 3);
        ground.scaleTextureCoordinates(new Vector2f(1f,3300f));
        geometry_ground = new Geometry("Road", ground);
        geometry_ground.setMaterial(assetManager.loadMaterial("Materials/Road.j3m"));
        Spatial S_geometry_ground = geometry_ground;
        S_geometry_ground.addControl(new MoveControl());
        rootNode.attachChild(S_geometry_ground);
    }    
 
}