/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import java.util.HashMap;

/**
 *
 * @author KEVIN Z
 */
public class StagesController {
    private HashMap<String, Showable> stages;

    public StagesController() {
        stages = new HashMap<>();
    }
    
    public void addStage(String name, Showable stage){
        stages.put(name, stage);
    }
    
    public void showStage(String name){
        stages.get(name).getStage().show();
    }
    
}
