/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Structure;
import View.MainFrameView;

/**
 *
 * @author ernestodecrecchio
 */
public class InfoStructurePanelController {
    
    public void setEditPanel(MainFrameView parent, Structure structure) {
        parent.setEditStructurePanel(structure);
    }
}
