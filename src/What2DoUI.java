/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author SANDEEP
 */
public class What2DoUI extends javax.swing.JFrame {
	
char lastEvent=' ';
    /**
     * Creates new form What2DoUI
     */
    public What2DoUI() {
        initComponents();
    }
    String previousEntry="";
    int flag=0;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        textField1 = new java.awt.TextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jPanel1.setBackground(new java.awt.Color(241,235,194));

        textField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textField1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });
        textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
					textField1KeyTyped(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jTextPane2.setEditable(false);
        jTextPane2.setFont(new java.awt.Font("Monospaced", 2, 14)); // NOI18N
        jTextPane2.setText("FORMAT : [operation - add (OR '+') OR delete (OR '-') OR search OR update OR undo OR done OR undone OR exit]");
        jScrollPane4.setViewportView(jTextPane2);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo3.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo5.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jTextArea5.setEditable(false);
        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setFont(new java.awt.Font("Monospaced", 0, 12));
        jTextArea5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upcoming Events", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
        jScrollPane1.setViewportView(jTextArea5);

        jTextArea8.setEditable(false);
        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jTextArea8.setFont(new java.awt.Font("Monospaced", 0, 12));
        jTextArea8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Floating Events", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
        jScrollPane10.setViewportView(jTextArea8);

        jTextArea6.setEditable(false);
        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setFont(new java.awt.Font("Monospaced", 0, 12));
        jTextArea6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Priority Events", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
        jScrollPane8.setViewportView(jTextArea6);

        jTextArea7.setEditable(false);
        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jTextArea7.setFont(new java.awt.Font("Monospaced", 0, 12));
        jTextArea7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Results", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monospaced" , 1, 14))); // NOI18N
        jScrollPane9.setViewportView(jTextArea7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10)
                            .addComponent(jScrollPane9)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)))
                .addGap(1, 1, 1)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane9))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane10, jScrollPane8, jScrollPane9});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textField1KeyTyped(java.awt.event.KeyEvent evt) throws Exception {//GEN-FIRST:event_textField1KeyTyped
       
        if (evt.getKeyChar()==8){
        	if (textField1.getText().isEmpty()){
        		flag=0;
        		jTextPane2.setText("FORMAT : [operation - add (OR '+') OR delete (OR '-') OR search OR update OR undo OR done OR undone OR exit]");
        	}
        }
        if (flag==0 && ((evt.getKeyChar()=='a') || (evt.getKeyChar()=='+'))){
            flag=1;
            lastEvent=evt.getKeyChar();
            
            jTextPane2.setText("[add] [Key words] [start time and date] [End time and date] [r-reminder time]");
        }
        if (flag ==0 && (evt.getKeyChar()=='-')){
            flag=1;
            lastEvent=evt.getKeyChar();
            
            jTextPane2.setText("[delete] [index number]");
        }
        if(flag ==0 && evt.getKeyChar()=='d') {
        	lastEvent=evt.getKeyChar();
            jTextPane2.setText("[delete] [index number]");
        }
        if (evt.getKeyChar()=='e' && lastEvent=='d'){
            flag=1;
            lastEvent=evt.getKeyChar();
            
            jTextPane2.setText("[delete] [index number]");
        }
        else if (flag==0 && evt.getKeyChar()=='o' && lastEvent=='d'){
            flag=1;
            lastEvent=evt.getKeyChar();
            
            jTextPane2.setText("[done] [index number]");
        }
        if (flag ==0 && evt.getKeyChar()=='s'){
            flag=1;
            lastEvent=evt.getKeyChar();
            
            jTextPane2.setText("[search] [searchwords]");
        }
        if (flag==0 && evt.getKeyChar()=='u'){
            lastEvent='u';
            jTextPane2.setText("[update] [index number]");
        }
        if (flag==0 && evt.getKeyChar()=='e'){
            flag=1;
            lastEvent=evt.getKeyChar();
            
            jTextPane2.setText("[exit]");
        }
        if (flag==0 && evt.getKeyChar()=='n' && lastEvent=='u'){
            flag=1;
            lastEvent=evt.getKeyChar();
            jTextPane2.setText("[undone] [index number]");
        }
        else if (flag==0 && evt.getKeyChar()=='p' && lastEvent=='u'){
            flag=1;
            lastEvent=evt.getKeyChar();
            jTextPane2.setText("[update] [index number]");
        }
     
        if (evt.getKeyChar() == '\n') {
                flag=0;
		String data = textField1.getText();
		int index=Executor.analyze(data);
		String message=ExceptionHandler.getException(index);
		ArrayList<String> upcomingEvents = ListOfEvent.getListOfEventToDisplayInString();
		String upcomingEventsString= format(upcomingEvents, getMaximumLengths(upcomingEvents));
		String priorityEvents = Executor.printPriorityDataBase();
		String floatingEvents = Executor.printFloatingDataBase();
		String searchResults = Executor.printSearchResults();
		priorityEvents=format(priorityEvents);
		floatingEvents=format(floatingEvents);
		searchResults=formatForSearch(searchResults);
		jTextArea5.setText(upcomingEventsString);
		jTextArea6.setText(priorityEvents);
		jTextArea8.setText(floatingEvents);
		jTextArea7.setText(searchResults);
		textField1.setText("");
                
                previousEntry=data;
		}

    }//GEN-LAST:event_textField1KeyTyped
    private String format(ArrayList<String> upcomingEvents, int[] maximumLengths) {
    	String result="";
    	for(int i=0;i<upcomingEvents.size();i++)
    	{
    		String[] tempArray=upcomingEvents.get(i).split("\\..");
    		String[] tempArray2={"","","","","",""};
    		for(int j=0;j<tempArray.length;j++)
    		{
    			tempArray2[j]=tempArray[j];
    		}
    		result+=tempArray2[0]+getSpaces(maximumLengths[0]-tempArray2[0].length())+"  ";
    		result+=tempArray2[1]+getSpaces(maximumLengths[1]-tempArray2[1].length())+"  ";
    		result+=tempArray2[2]+getSpaces(maximumLengths[2]-tempArray2[2].length())+"  ";
    		result+=tempArray2[3]+getSpaces(maximumLengths[3]-tempArray2[3].length())+"  ";
    		result+=tempArray2[4]+getSpaces(maximumLengths[4]-tempArray2[4].length())+"  ";
    		result+=tempArray2[5]+getSpaces(maximumLengths[5]-tempArray2[5].length())+"  ";
    		result+="\n";
    	}
		return result;
	}

	private String getSpaces(int number) {
		String spaces="";
		for(int i=0;i<number;i++)
		{
			spaces+=" ";	
		}
		return spaces;
	}

	private String format(String text) {
		String newText = "";
		newText += "Index\t"+"      " + "Name\t"+"      " + "Additional Details\n";
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '.' && text.charAt(i + 1) == '.') {
				newText += '\t';
				i++;
			} else {
				newText += text.charAt(i);
			}

		}
		// TODO Auto-generated method stub
		return newText;
	}
	
	private String formatForSearch(String text) {
		String newText = "";
		newText += "Index\t"+"      " + "Name\t"+"      " + "Additional Details\n";
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '.' && text.charAt(i + 1) == '.') {
				newText += '\t';
				i++;
			} else {
				newText += text.charAt(i);
			}

		}
		// TODO Auto-generated method stub
		return newText;
	}
	private int[] getMaximumLengths(ArrayList<String> unformatted)
	{
		int[] lengths = new int[6];
		lengths[0]=0;
		lengths[1]=0;
		lengths[2]=0;
		lengths[3]=0;
		lengths[4]=0;
		lengths[5]=0;
		for(int i=0;i<unformatted.size();i++)
		{
			String[] tempStorage=unformatted.get(i).split("\\..");
			for(int k=0;k<tempStorage.length;k++)
			{
				if(tempStorage[k].length()>lengths[k])
					lengths[k]=tempStorage[k].length();
			}

		}
		return lengths;
	}
   

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void textField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyPressed
        if (evt.getKeyCode()==38){
            textField1.setText(previousEntry);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_textField1KeyPressed

    /**
     * @param args the command line arguments
     * @throws Exception 
     */
    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    	try{
    	Executor.loadDatabase();
    	}
    	catch(Exception e)
    	{
    		Executor.formatDatabase();
    	}
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        ExceptionHandler.setUpList();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new What2DoUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextPane jTextPane2;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}

