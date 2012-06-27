package OCLP;

import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;

public class Environment {
    private AssetManager assetManager;
    private Node rootNode;
    private ViewPort viewPort;    
    
    protected Environment(AssetManager assetManager, Node rootNode,ViewPort viewPort){
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        this.viewPort = viewPort;    
        
        //Setup environment
        setUpLight();
        setUpFog();
    }
    
    
    private void setUpLight(){        
        /** A white ambient light source. */ 
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient); 
                
        /** A white, directional light source */ 
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalize());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun); 
    }
    
    private void setUpFog(){       
      
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.setNumSamples(4);
        FogFilter fog = new FogFilter();
        fog.setFogColor(new ColorRGBA(0.8f, 0.8f, 1.0f, 1.0f));
        fog.setFogDistance(7);
        fog.setFogDensity(1.4f);
        fpp.addFilter(fog);
        viewPort.addProcessor(fpp);
        
    }    
   
}
