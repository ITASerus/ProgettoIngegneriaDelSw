/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SearchStructurePanelController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ernestodecrecchio
 */
public class SearchStructurePanelView extends javax.swing.JPanel {

    SearchStructurePanelController controller;
    MainFrameView parent;
    
    /**
     * Creates new form SearchStructurePanelView
     */
    public SearchStructurePanelView(MainFrameView parent) {
        initComponents();
        
        controller = new SearchStructurePanelController();
        this.parent = parent;
        
        populateTable();
    }

    void populateTable() {
        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
        resultTable.setModel(model);
        model.setRowCount(0);
        
        JsonArray results = controller.getByFilter(
                                                    nameTextField.getText().isBlank() ? null : nameTextField.getText(),
                                                    placeTextField.getText().isBlank() ? null : placeTextField.getText(),
                                                    categoryComboBox.getSelectedItem().toString().equals("---") ? null : categoryComboBox.getSelectedItem().toString(),
                                                    contactsTextField.getText().isBlank() ? null : contactsTextField.getText(),
                                                    webSiteTextField.getText().isBlank() ? null : webSiteTextField.getText(),
                                                    lowerPriceTextField.getText().isBlank() ? null : Integer.parseInt(lowerPriceTextField.getText()),
                                                    upperPriceTextField.getText().isBlank() ? null : Integer.parseInt(upperPriceTextField.getText()),
                                                    avgPointsComboBox.getSelectedItem().toString().equals("---") ? null : avgPointsComboBox.getSelectedItem().toString());
        
        String webSite;
        String contacts;
        
        if(results.size() == 0) {
            JOptionPane.showMessageDialog(this, "La ricerca non ha prodotto risultati.", "Risultato vuoto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for(int i = 0; i < results.size(); i++) {
                JsonObject element = results.get(i).getAsJsonObject();
            
                webSite = element.get("webSite").toString().equals("null") ? "---" :  element.get("webSite").getAsString();
                contacts = element.get("contacts").toString().equals("null") ? "---" :  element.get("contacts").getAsString();
            
                model.addRow(new Object[] {element.get("id").getAsInt(), element.get("name").getAsString(), webSite, contacts});//, element.get("contacts") == null ? "N/A" :  element.get("contacts").getAsString()});
            }
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

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        separator = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        placeTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        contactsTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        webSiteTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lowerPriceTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        avgPointsComboBox = new javax.swing.JComboBox<>();
        upperPriceTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        infoButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();

        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Sito Web", "Contatti"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(resultTable);
        if (resultTable.getColumnModel().getColumnCount() > 0) {
            resultTable.getColumnModel().getColumn(0).setMinWidth(35);
            resultTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            resultTable.getColumnModel().getColumn(0).setMaxWidth(150);
            resultTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            resultTable.getColumnModel().getColumn(2).setMinWidth(80);
            resultTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            resultTable.getColumnModel().getColumn(2).setMaxWidth(500);
            resultTable.getColumnModel().getColumn(3).setMinWidth(80);
            resultTable.getColumnModel().getColumn(3).setPreferredWidth(130);
            resultTable.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Nome");

        nameTextField.setToolTipText("Inserire il nome della struttura che si vuole ricercare.");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Luogo");

        placeTextField.setToolTipText("Inserire il luogo (paese, regione, indirizzo ecc) della struttura che si vuole ricercare. ");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Contatti");

        contactsTextField.setToolTipText("Inserire i contatti (numero di telefono, cellulare, FAX) chella struttura che si vuole ricercare.");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Categoria");

        categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "Hotel", "Resort", "Attività", "Cibo" }));
        categoryComboBox.setToolTipText("Inserire la categoria che di cui fa parte la struttura che si vuole ricercare.");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel5.setText("Sito Web");

        webSiteTextField.setToolTipText("Inserire il sito web della struttura che si vuole ricercare.");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel6.setText("Fascia di prezzo");

        lowerPriceTextField.setToolTipText("Inserire il limite inferiore di prezzo medio della struttura che si vuole ricercare.");

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/search.png"))); // NOI18N
        searchButton.setText("Ricerca");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel7.setText("Punteggio medio");

        avgPointsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "0 e oltre", "1 e oltre", "2 e oltre", "3 e oltre", "4 e oltre", "5" }));
        avgPointsComboBox.setToolTipText("Selezionare il punteggio medio della struttura che si vuole ricercare.");

        upperPriceTextField.setToolTipText("Inserire il limite superiore di prezzo medio della struttura che si vuole ricercare.");

        jLabel8.setText("tra");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("e");

        infoButton.setBackground(new java.awt.Color(51, 153, 255));
        infoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/information.png"))); // NOI18N
        infoButton.setText("Info Struttura Selezionata");
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(placeTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contactsTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(webSiteTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lowerPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(upperPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(avgPointsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separator)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(placeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webSiteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lowerPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(upperPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(avgPointsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        try {
            if(!lowerPriceTextField.getText().isBlank() && !upperPriceTextField.getText().isBlank() &&
               Integer.parseInt(lowerPriceTextField.getText()) > Integer.parseInt(upperPriceTextField.getText()) ) {
               JOptionPane.showMessageDialog(this, "Il top deve essere inferiore al bot", "Errore fascia di prezzo", JOptionPane.ERROR_MESSAGE);
            }
            
            populateTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La fascia di prezzo deve contenere solo numeri interi", "Errore fascia di prezzo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
       if(!resultTable.getSelectionModel().isSelectionEmpty()) {
           Integer id = (Integer)resultTable.getValueAt(resultTable.getSelectedRow(), 0);
            parent.setInfoStructurePanel(controller.getSelectedStructure(id));
       } else {
           JOptionPane.showMessageDialog(this, "Bisogna selezionare la riga corrispondente\nalla struttura di cui si vogliono avere\nle informazioni.", "Errore richiesta informazioni", JOptionPane.ERROR_MESSAGE);
       }   
    }//GEN-LAST:event_infoButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> avgPointsComboBox;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JTextField contactsTextField;
    private javax.swing.JButton infoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField lowerPriceTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField placeTextField;
    private javax.swing.JTable resultTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JSeparator separator;
    private javax.swing.JTextField upperPriceTextField;
    private javax.swing.JTextField webSiteTextField;
    // End of variables declaration//GEN-END:variables
}
