package Project;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;

/**
 * 
 * @author SANDEEP
 */
public class What2DoUI extends javax.swing.JFrame {

	/**
	 * Creates new form What2DoUI
	 */
	public What2DoUI() {
		initComponents();
	}
	
	//html1=setUp();
	String html1="<html><table class=\"MsoTableGrid\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse: collapse; border: none; \"><tbody><tr><td width=\"308\" valign=\"top\" style=\"width: 231.05pt; border: 1pt solid windowtext; padding: 0cm 5.4pt; \"><p class=\"MsoNormal\" style=\"margin-bottom: 0.0001pt; line-height: normal; \">&nbsp;</p></td><td width=\"308\" valign=\"top\" style=\"width: 231.05pt; border-style: solid solid solid none; border-top-color: windowtext; border-right-color: windowtext; border-bottom-color: windowtext; border-top-width: 1pt; border-right-width: 1pt; border-bottom-width: 1pt; padding: 0cm 5.4pt; \"><p class=\"MsoNormal\" style=\"margin-bottom: 0.0001pt; line-height: normal; \">&nbsp;</p></td></tr><tr><td width=\"308\" valign=\"top\" style=\"width: 231.05pt; border-style: none solid solid; border-right-color: windowtext; border-bottom-color: windowtext; border-left-color: windowtext; border-right-width: 1pt; border-bottom-width: 1pt; border-left-width: 1pt; padding: 0cm 5.4pt; \"><p class=\"MsoNormal\" style=\"margin-bottom: 0.0001pt; line-height: normal; \">&nbsp;</p></td><td width=\"308\" valign=\"top\" style=\"width: 231.05pt; border-style: none solid solid none; border-bottom-color: windowtext; border-bottom-width: 1pt; border-right-color: windowtext; border-right-width: 1pt; padding: 0cm 5.4pt; \"><p class=\"MsoNormal\" style=\"margin-bottom: 0.0001pt; line-height: normal; \">&nbsp;</p></td> </tr></tbody></table><p class=\"MsoNormal\">&nbsp;</p></html>";
	String demo="daff";
	char lastEvent = ' ';
	int flag = 0;
	boolean toUpdate = true;
	Vector<String> previousEntry = new Vector<String>();
	int previousIndex=0;
	int updateFlag=0;
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		jScrollPane6 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jTabbedPane2 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		textField1 = new java.awt.TextField();
		jScrollPane4 = new javax.swing.JScrollPane();
		jLabel4 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JLabel();
		
		jScrollPane2 = new javax.swing.JScrollPane();
		jLabel3 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JLabel();
		
		
		jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JLabel();
        jTextArea4.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
		
        //jLabel3 = new javax.swing.JLabel();

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		jScrollPane6.setViewportView(jTable1);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(241, 235, 194));

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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jLabel4.setFont(new java.awt.Font("Monospaced", 2, 14)); // NOI18N
		jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">Available operations are</span></b><span style=\"font-family: Helvetica, sans-serif; \">- </span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \">add (+)/delete(-)/search/update/undo/done/undone/exit</span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		jScrollPane4.setViewportView(jLabel4);

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Logo3.png"))); // NOI18N
		jLabel2.setText("jLabel2");
		jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(
				new java.awt.Color(0, 0, 0), 5));

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Logo5.png"))); // NOI18N
		jLabel1.setText("jLabel1");
		jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
				0, 0, 0), 5, true));

		jPanel2.setBackground(new java.awt.Color(240, 233, 194));

		//jTextArea2.setColumns(20);
		jTextArea2.setFont(new java.awt.Font("Monospaced", 1, 12));
		//jTextArea2.setRows(5);
		jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Upcoming Events",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
		jScrollPane3.setViewportView(jTextArea2);

		/*jTextArea3.setColumns(20);
		jTextArea3.setFont(new java.awt.Font("Monospaced", 1, 12));
		jTextArea3.setRows(5);
		jTextArea3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Floating Events",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
		jScrollPane5.setViewportView(jTextArea3);*/

		jScrollPane2.setViewportView(jLabel3);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE))
						.addComponent(jScrollPane2,
								javax.swing.GroupLayout.Alignment.TRAILING));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												17, Short.MAX_VALUE)
										.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		jPanel2.setBounds(0, 0, 890, 340);
		jLayeredPane1.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTextArea1.setOpaque(true);
		jTextArea1.setBackground(new java.awt.Color(255, 255, 255));

		
		jTextArea2.setOpaque(true);
		jTextArea2.setBackground(new java.awt.Color(255, 255, 255));


		jTextArea4.setOpaque(true);
		jTextArea4.setBackground(new java.awt.Color(255, 255, 255));
		

	    jTextArea1.setHorizontalAlignment(JLabel.LEFT);
	    jTextArea1.setVerticalAlignment(JLabel.TOP);
		
		
		jTextArea2.setHorizontalAlignment(JLabel.LEFT);
	    jTextArea2.setVerticalAlignment(JLabel.TOP);
		

	    jTextArea4.setHorizontalAlignment(JLabel.LEFT);
	    jTextArea4.setVerticalAlignment(JLabel.TOP);
		
		jPanel3.setBackground(new java.awt.Color(240, 233, 194));
		jPanel3.setVisible(false);
		
		jLabel3.setOpaque(true);
		jLabel3.setBackground(new java.awt.Color(255, 255, 255));
		jLabel4.setOpaque(true);
		jLabel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setVisible(false);

		
		//jTextArea1.setEditable(false);
		//jTextArea2.setEditable(false);
		//jTextArea4.setEditable(false);
		
		/*
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 12));
		*/
		jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Search Results",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
		jScrollPane1.setViewportView(jTextArea1);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 863,
								Short.MAX_VALUE).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 309,
								Short.MAX_VALUE).addContainerGap()));

		jPanel3.setBounds(0, 0, 883, 331);
		jLayeredPane1.add(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
		

 jPanel4.setBackground(new java.awt.Color(240, 232, 194));

        /*jTextArea4.setEditable(false);
        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        */
 		jTextArea4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Floating Events", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14), java.awt.Color.black)); // NOI18N
        jScrollPane7.setViewportView(jTextArea4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBounds(0, 0, 903, 335);
        jLayeredPane1.add(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

 

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(24, 24, 24)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																textField1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane4)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				276,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				591,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap(
																				20,
																				Short.MAX_VALUE))
														.addComponent(
																jLayeredPane1))));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																128,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel1))
										.addGap(1, 1, 1)
										.addComponent(
												textField1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLayeredPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												340, Short.MAX_VALUE)
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();
	}// </editor-fold>

	private void textField1KeyTyped(java.awt.event.KeyEvent evt) throws Exception {// GEN-FIRST:event_textField1KeyTyped

		if (evt.getKeyChar() == 8) {
			if (textField1.getText().isEmpty()) {
				flag = 0;
				jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">Available operations are</span></b><span style=\"font-family: Helvetica, sans-serif; \">- </span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \">add (+)/delete(-)/search/update/undo/done/undone/exit</span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
			}
		}
		if (flag == 0
				&& ((evt.getKeyChar() == 'a') || (evt.getKeyChar() == '+'))) {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[add] [Key words] [start time and date] [End time and date] [r-reminder time]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0 	&& ((evt.getKeyChar() == 'b'))) {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">Available operations are</span></b><span style=\"font-family: Helvetica, sans-serif; \">- </span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \">add (+)/delete(-)/search/update/undo/done/undone/exit</span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0 && (evt.getKeyChar() == '-')) {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[delete] [index number]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
			
		}
		if (flag == 0 && evt.getKeyChar() == 'd') {
			lastEvent = evt.getKeyChar();
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[delete] [index number]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (evt.getKeyChar() == 'e' && lastEvent == 'd') {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[delete] [index number]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
			
		}
		else if (flag == 0 && evt.getKeyChar() == 'o' && lastEvent == 'd') {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[done] [index number]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0 && evt.getKeyChar() == 's') {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[search] [keywords]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");

		}
		if (flag == 0 && evt.getKeyChar() == 'u') {
			lastEvent = 'u';
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[update] [index number]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");

		}
		if (flag == 0 && evt.getKeyChar() == 'e') {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[exit]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0 && evt.getKeyChar() == 'n' && lastEvent == 'u') {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[undo] [index number]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");

		} else if (flag == 0 && evt.getKeyChar() == 'p' && lastEvent == 'u') {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jLabel4.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; \">[update] [index number]</span></b><span style=\"font-family: Helvetica, sans-serif; \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}

		if (evt.getKeyChar() == '\n') {
			flag = 0;
			String data = textField1.getText();
			if (data.contains("search")) {
				jPanel2.setVisible(false);
				jPanel4.setVisible(false);
				jPanel3.setVisible(true);
			}
			if (data.contains("back")) {
				jPanel3.setVisible(false);
				jPanel4.setVisible(false);
				jPanel2.setVisible(true);
			}
			if (data.contains("floating")){
                jPanel2.setVisible(false);
                jPanel3.setVisible(false);
                jPanel4.setVisible(true);
            }
            if (data.contains("upcoming")){
                jPanel4.setVisible(false);
                jPanel3.setVisible(false);
                jPanel2.setVisible(true);
            }
            if (data.contains("update")){
            	updateFlag+=1;
            	updateFlag=updateFlag%3;
            	if (updateFlag==1){
            		//call anirup's function-textField1.setText("");
            	}
            		
            }
			int index = Executor.analyze(data);
			String message = ExceptionHandler.getException(index);
			toUpdate = !(message.contains("Error"));

			ArrayList<String> upcomingEvents = Executor.printDataBase();
			ArrayList<String> floatingEvents = Executor.printFloatingDataBase();
			ArrayList<String> searchResults = Executor.printSearchResults();
			
			//index, name, hash-tags, start time end time, reminder
			
			/*
			String upcomingEventsString = format(upcomingEvents,getMaximumLengths(upcomingEvents));
			String floatingEventsString = format(floatingEvents, getMaximumLengths(floatingEvents));
			String searchResultsString = format(searchResults,getMaximumLengths(searchResults));
			*/
			
			ArrayList<ArrayList<String>> demoUpcomingEvents=new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> demoFloatingEvents=new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> demoSearch=new ArrayList<ArrayList<String>>();
			for (int i=0; i<100; i++){
				ArrayList<String> temp =new ArrayList<String>();
				for (int j=0; j<10; j++){
					temp.add("");
				}
				demoUpcomingEvents.add(temp);
			}
			for (int i=0; i<100; i++){
				ArrayList<String> temp =new ArrayList<String>();
				for (int j=0; j<10; j++){
					temp.add("");
				}
				demoFloatingEvents.add(temp);
			}
			for (int i=0; i<100; i++){
				ArrayList<String> temp =new ArrayList<String>();
				for (int j=0; j<10; j++){
					temp.add("");
				}
				demoSearch.add(temp);
			}
			
			formatForUpcoming(demoUpcomingEvents, upcomingEvents);
			formatForUpcoming(demoSearch, searchResults);
			formatForFloating(demoFloatingEvents, floatingEvents);

			String html2="<html><p class=\"MsoNormal\"><b>ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Events Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(192, 80, 77); \">Details</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">Start</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">End</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(152, 72, 7); \">Reminder</span></b></p>";
			String html3="<html><p class=\"MsoNormal\"><b>ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Events Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(192, 80, 77); \">Details</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">Start</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">End</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(152, 72, 7); \">Reminder</span></b></p>";
			String html4="<html><p class=\"MsoNormal\"><b>ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Events Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(192, 80, 77); \">Details</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">Start</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">End</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(152, 72, 7); \">Reminder</span></b></p>";


			if (toUpdate) {
				
				for (int i=0; i<upcomingEvents.size(); i++){
					html2+=String.format("<br><p class=\"MsoNormal\"><b>%s &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; %s&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(192, 80, 77); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(152, 72, 7); \">%s</span></b></p>", demoUpcomingEvents.get(i+1).get(0), demoUpcomingEvents.get(i+1).get(1), demoUpcomingEvents.get(i+1).get(2), demoUpcomingEvents.get(i+1).get(3), demoUpcomingEvents.get(i+1).get(4), demoUpcomingEvents.get(i+1).get(5));					
				}
				for (int i=0; i<floatingEvents.size(); i++){
					html3+=String.format("<br><p class=\"MsoNormal\"><b>%s &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; %s&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(192, 80, 77); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(152, 72, 7); \">%s</span></b></p>", demoFloatingEvents.get(i+1).get(0), demoFloatingEvents.get(i+1).get(1), demoFloatingEvents.get(i+1).get(2), demoFloatingEvents.get(i+1).get(3), demoFloatingEvents.get(i+1).get(4), demoFloatingEvents.get(i+1).get(5));					
				}
				for (int i=0; i<floatingEvents.size(); i++){
					html4+=String.format("<br><p class=\"MsoNormal\"><b>%s &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; %s&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(192, 80, 77); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(31, 73, 125); \">%s</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"color: rgb(152, 72, 7); \">%s</span></b></p>", demoSearch.get(i+1).get(0), demoSearch.get(i+1).get(1), demoSearch.get(i+1).get(2), demoSearch.get(i+1).get(3), demoSearch.get(i+1).get(4), demoSearch.get(i+1).get(5));					
				}

				
				html2+="</html>";
				html3+="</html>";
				html4+="</html>";
				jTextArea2.setText(html2);
				jTextArea4.setText(html3);
				jTextArea1.setText(html4);
				jLabel3.setText(String.format("<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(0, 176, 80)\"; >%s</span></b></p></html>",message));
			} 
			else {
					jLabel3.setText(String.format("<html><p class=\"MsoNormal\"><b><span style=\"color: red; \">%s</span></b></p></html>", message));
			}
			textField1.setText("");
			previousEntry.add(data);
			previousIndex=previousEntry.size()-1;
		}

	}
	private void formatForUpcoming(ArrayList<ArrayList<String>> demo, ArrayList<String> events) {
		demo.get(0).set(0, "ID");
		demo.get(0).set(1, "Event Name");
		demo.get(0).set(2, "Details");
		demo.get(0).set(3, "Start");
		demo.get(0).set(4, "End");
		demo.get(0).set(5, "Reminder");
		
		for (int i = 0; i < events.size(); i++) {
			String[] tempArray = events.get(i).split("\\..");
			for (int j = 0; j < tempArray.length; j++) {
				demo.get(i+1).set(j, tempArray[j]);
			}
		}
	}
	private void formatForFloating(ArrayList<ArrayList<String>> demo, ArrayList<String> events) {
		demo.get(0).set(0, "ID");
		demo.get(0).set(1, "Event Name");
		demo.get(0).set(2, "Details");
		
		for (int i = 0; i < events.size(); i++) {
			String[] tempArray = events.get(i).split("\\..");
			for (int j = 0; j < tempArray.length; j++) {
				demo.get(i+1).set(j, tempArray[j]);
			}
		}
	}
	
	private String format(ArrayList<String> upcomingEvents, int[] maximumLengths) {
		String result = "";
		result += "ID" + getSpaces(maximumLengths[0] - 2) + "     "
				+ "Event Name" + getSpaces(maximumLengths[1] - 10) + "     "
				+ "Details" + getSpaces(maximumLengths[2] - 7) + "     "
				+ "Start" + getSpaces(maximumLengths[3] - 5) + "     " + "End"
				+ getSpaces(maximumLengths[4] - 3) + "     " + "Reminder"
				+ getSpaces(maximumLengths[5] - 8) + "     " + "\n";
		for (int i = 0; i < upcomingEvents.size(); i++) {
			String[] tempArray = upcomingEvents.get(i).split("\\..");
			String[] tempArray2 = { "", "", "", "", "", "" };
			for (int j = 0; j < tempArray.length; j++) {
				tempArray2[j] = tempArray[j];
			}
			result += tempArray2[0]
					+ getSpaces(maximumLengths[0] - tempArray2[0].length())
					+ "     ";
			result += tempArray2[1]
					+ getSpaces(maximumLengths[1] - tempArray2[1].length())
					+ "     ";
			result += tempArray2[2]
					+ getSpaces(maximumLengths[2] - tempArray2[2].length())
					+ "     ";
			result += tempArray2[3]
					+ getSpaces(maximumLengths[3] - tempArray2[3].length())
					+ "     ";
			result += tempArray2[4]
					+ getSpaces(maximumLengths[4] - tempArray2[4].length())
					+ "     ";
			result += tempArray2[5]
					+ getSpaces(maximumLengths[5] - tempArray2[5].length())
					+ "     ";
			result += "\n";
		}
		return result;
	}

	private String getSpaces(int number) {
		String spaces = "";
		for (int i = 0; i < number; i++) {
			spaces += " ";
		}
		return spaces;
	}



	private int[] getMaximumLengths(ArrayList<String> unformatted) {
		int[] lengths = new int[6];
		lengths[0] = 2;
		lengths[1] = 10;
		lengths[2] = 7;
		lengths[3] = 5;
		lengths[4] = 3;
		lengths[5] = 8;
		for (int i = 0; i < unformatted.size(); i++) {
			String[] tempStorage = unformatted.get(i).split("\\..");
			for (int k = 0; k < tempStorage.length; k++) {
				if (tempStorage[k].length() > lengths[k])
					lengths[k] = tempStorage[k].length();
			}

		}
		return lengths;
	}

	private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_textField1ActionPerformed

	private void textField1KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textField1KeyPressed
		if (evt.getKeyCode() == 38 && previousIndex>=0 && !previousEntry.isEmpty()) {
			textField1.setText(previousEntry.get(previousIndex));
			previousIndex--;
			}
		if (evt.getKeyCode() == 40 && previousIndex!=previousEntry.size()-1) {
			textField1.setText(previousEntry.get(++previousIndex));
			}
	}
	
	/**
	 * @param args
	 *            the command line arguments
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		ExceptionHandler.setUpList();
		Log.setup();
		try
		{
		Executor.loadDatabase();
		}
		catch(Exception e)
		{
			Executor.formatDatabase();
		}
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(What2DoUI.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new What2DoUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTabbedPane jTabbedPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JLabel jTextArea1;
	private javax.swing.JLabel jTextArea2;
	private javax.swing.JLabel jTextArea4;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private java.awt.TextField textField1;
	// End of variables declaration
}