/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package View;

// Controller
import Controller.NewStructurePanelController;

// Helper
import Helper.JTextFieldRegularPopupMenu;

// Java & Swing
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

// Model
import Model.Structure;

/**
 * @author ernestodecrecchio
 */
public class NewStructurePanelView extends javax.swing.JPanel {

    NewStructurePanelController controller;
    MainFrameView parent;
    
    /**
     * Creates new form NewStructurePanelView
     */
    public NewStructurePanelView(MainFrameView parent) {
        initComponents();
        
        controller = new NewStructurePanelController();
        this.parent = parent;

        mapPanel.setLayout(new BorderLayout());
        
        JTextFieldRegularPopupMenu.addTo(nameTextField);
        JTextFieldRegularPopupMenu.addTo(contactsTextField);
        JTextFieldRegularPopupMenu.addTo(placeTextField);
        JTextFieldRegularPopupMenu.addTo(webSiteTextField);
        //JTextAreaRegularPopupMenu.addTo(descriptionTextPane);  
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        placeLabel = new javax.swing.JLabel();
        webSiteLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        placeTextField = new javax.swing.JTextField();
        webSiteTextField = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        priceTextField = new javax.swing.JTextField();
        contactsLabel = new javax.swing.JLabel();
        contactsTextField = new javax.swing.JTextField();
        priceSlider = new javax.swing.JSlider();
        jSeparator1 = new javax.swing.JSeparator();
        mapPanel = new javax.swing.JPanel();
        categoryLabel = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox<>();
        enablePriceCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextPane = new javax.swing.JEditorPane();
        visualizeMapButton = new javax.swing.JButton();
        photoLabel = new javax.swing.JLabel();
        searchImageButton = new javax.swing.JButton();
        imagePathTextField = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        nameLabel.setText("*Nome");

        placeLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        placeLabel.setText("Luogo");

        webSiteLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        webSiteLabel.setText("Sito Web");

        descriptionLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        descriptionLabel.setText("Descrizione");

        priceLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        priceLabel.setText("Prezzo Medio (in €)");

        nameTextField.setToolTipText("Inserire il nome della nuova struttura.");

        placeTextField.setToolTipText("Inserire il luogo in cui si trova la struttura o in cui si svolge l'attività.");

        webSiteTextField.setToolTipText("Inserire il sito web della struttura.");

        confirmButton.setBackground(new java.awt.Color(0, 153, 0));
        confirmButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/confirm.png"))); // NOI18N
        confirmButton.setText("Conferma");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/close.png"))); // NOI18N
        cancelButton.setText("Annulla");

        priceTextField.setToolTipText("Inserire il prezzo medio della struttura (selezionabile anche tramite lo slider).");
        priceTextField.setEnabled(false);
        priceTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                priceTextFieldFocusLost(evt);
            }
        });

        contactsLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        contactsLabel.setText("Contatti");

        contactsTextField.setToolTipText("Inserire i contatti di riferimento della struttura.");

        priceSlider.setMajorTickSpacing(100);
        priceSlider.setMaximum(500);
        priceSlider.setMinorTickSpacing(50);
        priceSlider.setPaintLabels(true);
        priceSlider.setPaintTicks(true);
        priceSlider.setSnapToTicks(true);
        priceSlider.setValue(0);
        priceSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        priceSlider.setEnabled(false);
        priceSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                priceSliderMouseReleased(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

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

        categoryLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        categoryLabel.setText("*Categoria");

        categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "Hotel", "Resort", "Attività", "Cibo" }));
        categoryComboBox.setToolTipText("Selezionare la categoria che rappresenta la struttura.");

        enablePriceCheckBox.setText("Abilita Prezzo");
        enablePriceCheckBox.setToolTipText("Selezionare se si vuole indicare un prezzo medio della struttura.");
        enablePriceCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enablePriceCheckBoxActionPerformed(evt);
            }
        });

        descriptionTextPane.setToolTipText("Inserire una descrizione della struttura.");
        jScrollPane1.setViewportView(descriptionTextPane);

        visualizeMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pin.png"))); // NOI18N
        visualizeMapButton.setText("Visualizza");
        visualizeMapButton.setToolTipText("Visualizza una mappa che indica il luogo in cui si trova la struttura o svolge l'attività.");
        visualizeMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeMapButtonActionPerformed(evt);
            }
        });

        photoLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        photoLabel.setText("Foto");

        searchImageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/upload.png"))); // NOI18N
        searchImageButton.setText("Carica Immagine");
        searchImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchImageButtonActionPerformed(evt);
            }
        });

        imagePathTextField.setEditable(false);
        imagePathTextField.setToolTipText("Percorso dell'immagine selezionata");
        imagePathTextField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webSiteTextField)
                    .addComponent(categoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(placeTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(visualizeMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(contactsTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(priceSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(priceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enablePriceCheckBox))
                    .addComponent(contactsLabel)
                    .addComponent(webSiteLabel)
                    .addComponent(categoryLabel)
                    .addComponent(nameLabel)
                    .addComponent(placeLabel)
                    .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionLabel)
                            .addComponent(photoLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(imagePathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(confirmButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(placeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(placeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(visualizeMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webSiteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webSiteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceLabel)
                            .addComponent(enablePriceCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(photoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imagePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchImageButton))
                        .addGap(204, 204, 204)
                        .addComponent(confirmButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void enablePriceCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enablePriceCheckBoxActionPerformed
        if(enablePriceCheckBox.isSelected()) {
            priceSlider.setEnabled(true);
            priceTextField.setEnabled(true);
            priceTextField.setText(String.valueOf(priceSlider.getValue()));
        } else {
            priceSlider.setEnabled(false);
            priceTextField.setEnabled(false);
            priceTextField.setText("");
        }
    }//GEN-LAST:event_enablePriceCheckBoxActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        if(!nameTextField.getText().isBlank() && (categoryComboBox.getSelectedIndex() != 0)) {
            Structure newStructure = controller.createNewStructure(nameTextField.getText(), 
                                                                   placeTextField.getText(), 
                                                                   categoryComboBox.getSelectedItem().toString(),
                                                                   priceTextField.getText(), 
                                                                   webSiteTextField.getText(), 
                                                                   contactsTextField.getText(), 
                                                                   descriptionTextPane.getText(),
                                                                   imagePathTextField.getText());
        
            if(newStructure != null) {
                JOptionPane.showMessageDialog(this, "Struttura aggiunta con successo!", "OK!", JOptionPane.INFORMATION_MESSAGE);
                controller.setInfoStructurePanel(parent, newStructure); //Change the panel shown
            } else {
                JOptionPane.showMessageDialog(this, "Struttura non aggiunta.\nControllare che i campi non contengano caratteri speciali", "Errore creazione!", JOptionPane.ERROR_MESSAGE);
            }   
        } else {
            JOptionPane.showMessageDialog(this, "Struttura non aggiunta.\nIl nome e la categoria sono campi obbligatori.", "Errore campi obbligatori!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void priceSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_priceSliderMouseReleased
        priceTextField.setText(Integer.toString(priceSlider.getValue()));
    }//GEN-LAST:event_priceSliderMouseReleased

    private void priceTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceTextFieldFocusLost
        priceSlider.setValue(Integer.parseInt(priceTextField.getText()));
    }//GEN-LAST:event_priceTextFieldFocusLost

    private void visualizeMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeMapButtonActionPerformed
        if(!placeTextField.getText().isBlank()) {
            mapPanel.removeAll();
            mapPanel.add(controller.getMapOfPlace(placeTextField.getText()), BorderLayout.CENTER);
            mapPanel.updateUI();
        } else {
            JOptionPane.showMessageDialog(this, "Per visualizzare la mappa bisogna indicare \nun luogo (paese, regione, indirizzo ecc)\nnel campo apposito.", "Luogo non valido", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_visualizeMapButtonActionPerformed

    private void searchImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchImageButtonActionPerformed
        JFileChooser file = new JFileChooser();
        
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
                            
              imagePathTextField.setText(path);
          }
           //if the user click on save in Jfilechooser
        
          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
   
        
    }//GEN-LAST:event_searchImageButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel contactsLabel;
    private javax.swing.JTextField contactsTextField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JEditorPane descriptionTextPane;
    private javax.swing.JCheckBox enablePriceCheckBox;
    private javax.swing.JTextField imagePathTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JLabel placeLabel;
    private javax.swing.JTextField placeTextField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JSlider priceSlider;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JButton searchImageButton;
    private javax.swing.JButton visualizeMapButton;
    private javax.swing.JLabel webSiteLabel;
    private javax.swing.JTextField webSiteTextField;
    // End of variables declaration//GEN-END:variables
}
