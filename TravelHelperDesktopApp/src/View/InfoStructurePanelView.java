/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.InfoStructurePanelController;
import Model.Structure;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.MalformedURLException;

/**
 *
 * @author ernestodecrecchio
 */
public class InfoStructurePanelView extends javax.swing.JPanel {
    
    InfoStructurePanelController controller;
    MainFrameView parent;
    Structure structure;
    
    public InfoStructurePanelView(MainFrameView parent, Structure structure) {
        initComponents();
        
        controller = new InfoStructurePanelController();
        this.parent = parent;
        this.structure = structure;
        
        idValueLabel.setText(structure.getId().toString());
        
        nameValueLabel.setText(structure.getName());
        
        placeValueLabel.setText(structure.getPlace());
        
        if(structure.getPlace() != null) {
            mapPanel.setLayout(new BorderLayout());
            mapPanel.add(controller.getMapOfPlace(structure.getPlace()), BorderLayout.CENTER);
            mapPanel.updateUI();
        }
        
        categoryValueLabel.setText(structure.getCategory());
        
        webSiteValueLabel.setText(structure.getWebSite());
        
        contactsValueLabel.setText(structure.getContacts());
        
        if(structure.getPrice() != null) {
            priceValueLabel.setText(structure.getPrice().toString() + "€");
        }
        
        descriptionValueTextPane.setText(structure.getDescription());
        
        if(structure.getImage() == null) {
            visualizeImageButton.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idLabel = new javax.swing.JLabel();
        idValueLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        placeLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        webSiteLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        contactsLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        placeValueLabel = new javax.swing.JLabel();
        contactsValueLabel = new javax.swing.JLabel();
        categoryValueLabel = new javax.swing.JLabel();
        webSiteValueLabel = new javax.swing.JLabel();
        priceValueLabel = new javax.swing.JLabel();
        nameValueLabel = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionValueTextPane = new javax.swing.JTextPane();
        mapPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        visualizeImageButton = new javax.swing.JButton();

        idLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        idLabel.setText("ID");

        idValueLabel.setText("---");

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        nameLabel.setText("Nome");

        placeLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        placeLabel.setText("Luogo");

        categoryLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        categoryLabel.setText("Categoria");

        webSiteLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        webSiteLabel.setText("Sito Web");

        priceLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        priceLabel.setText("Prezzo Medio");

        contactsLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        contactsLabel.setText("Contatti");

        descriptionLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        descriptionLabel.setText("Descrizione");

        placeValueLabel.setText("---");

        contactsValueLabel.setText("---");

        categoryValueLabel.setText("---");

        webSiteValueLabel.setText("---");

        priceValueLabel.setText("---");

        nameValueLabel.setText("---");

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/edit.png"))); // NOI18N
        editButton.setText("Modifica");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        imageLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        imageLabel.setText("Foto");

        descriptionValueTextPane.setEditable(false);
        jScrollPane2.setViewportView(descriptionValueTextPane);

        mapPanel.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        visualizeImageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/photo.png"))); // NOI18N
        visualizeImageButton.setText("Visualizza Immagine nel browser predefinito");
        visualizeImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(placeValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(webSiteValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contactsValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLabel)
                            .addComponent(nameLabel)
                            .addComponent(placeLabel)
                            .addComponent(categoryLabel)
                            .addComponent(webSiteLabel)
                            .addComponent(priceLabel)
                            .addComponent(contactsLabel))
                        .addGap(0, 253, Short.MAX_VALUE))
                    .addComponent(idValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descriptionLabel)
                                    .addComponent(visualizeImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 82, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLabel)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imageLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(visualizeImageButton)
                                .addGap(242, 242, 242)
                                .addComponent(editButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(placeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(placeValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(categoryLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(categoryValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(webSiteLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(webSiteValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contactsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contactsValueLabel)
                                .addGap(53, 53, 53)))
                        .addContainerGap())
                    .addComponent(jSeparator1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        controller.setEditPanel(parent, structure);
    }//GEN-LAST:event_editButtonActionPerformed

    private void visualizeImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeImageButtonActionPerformed
        try {
            URL myUrl = new URL(structure.getImage());
            openWebpage(myUrl);
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_visualizeImageButtonActionPerformed

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel categoryValueLabel;
    private javax.swing.JLabel contactsLabel;
    private javax.swing.JLabel contactsValueLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextPane descriptionValueTextPane;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idValueLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameValueLabel;
    private javax.swing.JLabel placeLabel;
    private javax.swing.JLabel placeValueLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel priceValueLabel;
    private javax.swing.JButton visualizeImageButton;
    private javax.swing.JLabel webSiteLabel;
    private javax.swing.JLabel webSiteValueLabel;
    // End of variables declaration//GEN-END:variables
}
