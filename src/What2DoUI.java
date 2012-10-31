import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * 
 * @author SANDEEP
 */
public class What2DoUI extends javax.swing.JFrame {

	char lastEvent = ' ';

	/**
	 * Creates new form What2DoUI
	 */
	public What2DoUI() {
		initComponents();
	}

	String previousEntry = "";
	int flag = 0;

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
		jTextPane2 = new javax.swing.JTextPane();
		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jScrollPane5 = new javax.swing.JScrollPane();
		jTextArea3 = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextPane();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();

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

		jTextPane2.setFont(new java.awt.Font("Monospaced", 2, 14)); // NOI18N
		jTextPane2
				.setText("FORMAT : [operation - add (OR '+') OR delete (OR '-') OR search OR update OR undo OR done OR undone OR exit]");
		jScrollPane4.setViewportView(jTextPane2);

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

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Upcoming Events",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
		jScrollPane3.setViewportView(jTextArea2);

		jTextArea3.setColumns(20);
		jTextArea3.setRows(5);
		jTextArea3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Floating Events",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Monospaced", 1, 14), java.awt.Color.black)); // NOI18N
		jScrollPane5.setViewportView(jTextArea3);

		jScrollPane2.setViewportView(jTextPane1);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																880,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																880,
																Short.MAX_VALUE)))
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
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												142,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												141,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		jPanel2.setBounds(0, 0, 890, 340);
		jLayeredPane1.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jPanel3.setBackground(new java.awt.Color(240, 233, 194));
		jPanel3.setVisible(false);

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
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

	private void textField1KeyTyped(java.awt.event.KeyEvent evt)
			throws Exception {// GEN-FIRST:event_textField1KeyTyped

		if (evt.getKeyChar() == 8) {
			if (textField1.getText().isEmpty()) {
				flag = 0;
				jTextPane2
						.setText("FORMAT : [operation - add (OR '+') OR delete (OR '-') OR search OR update OR undo OR done OR undone OR exit]");
			}
		}
		if (flag == 0
				&& ((evt.getKeyChar() == 'a') || (evt.getKeyChar() == '+'))) {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jTextPane2
					.setText("[add] [Key words] [start time and date] [End time and date] [r-reminder time]");
		}
		if (flag == 0 && (evt.getKeyChar() == '-')) {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jTextPane2.setText("[delete] [index number]");
		}
		if (flag == 0 && evt.getKeyChar() == 'd') {
			lastEvent = evt.getKeyChar();
			jTextPane2.setText("[delete] [index number]");
		}
		if (evt.getKeyChar() == 'e' && lastEvent == 'd') {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jTextPane2.setText("[delete] [index number]");
		} else if (flag == 0 && evt.getKeyChar() == 'o' && lastEvent == 'd') {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jTextPane2.setText("[done] [index number]");
		}
		if (flag == 0 && evt.getKeyChar() == 's') {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jTextPane2.setText("[search] [searchwords]");
		}
		if (flag == 0 && evt.getKeyChar() == 'u') {
			lastEvent = 'u';
			jTextPane2.setText("[update] [index number]");
		}
		if (flag == 0 && evt.getKeyChar() == 'e') {
			flag = 1;
			lastEvent = evt.getKeyChar();

			jTextPane2.setText("[exit]");
		}
		if (flag == 0 && evt.getKeyChar() == 'n' && lastEvent == 'u') {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jTextPane2.setText("[undone] [index number]");
		} else if (flag == 0 && evt.getKeyChar() == 'p' && lastEvent == 'u') {
			flag = 1;
			lastEvent = evt.getKeyChar();
			jTextPane2.setText("[update] [index number]");
		}

		if (evt.getKeyChar() == '\n') {
			flag = 0;
			String data = textField1.getText();
			if (data.contains("search")) {
				jPanel2.setVisible(false);
				jPanel3.setVisible(true);
			}
			if (data.contains("back")) {
				jPanel3.setVisible(false);
				jPanel2.setVisible(true);
			}

			int index = Executor.analyze(data);
			String message = ExceptionHandler.getException(index);
			ArrayList<String> upcomingEvents = ListOfEvent
					.getListOfEventToDisplayInString();
			String upcomingEventsString = format(upcomingEvents,
					getMaximumLengths(upcomingEvents));
			String priorityEvents = Executor.printPriorityDataBase();
			String floatingEvents = Executor.printFloatingDataBase();
			String searchResults = Executor.printSearchResults();
			priorityEvents = format(priorityEvents);
			floatingEvents = format(floatingEvents);
			searchResults = formatForSearch(searchResults);
			jTextArea2.setText(upcomingEventsString);
			// jTextArea6.setText(priorityEvents);
			jTextArea3.setText(floatingEvents);
			jTextArea1.setText(searchResults);
			textField1.setText("");

			previousEntry = data;
		}

	}// GEN-LAST:event_textField1KeyTyped

	private String format(ArrayList<String> upcomingEvents, int[] maximumLengths) {
		String result = "";
		for (int i = 0; i < upcomingEvents.size(); i++) {
			String[] tempArray = upcomingEvents.get(i).split("\\..");
			String[] tempArray2 = { "", "", "", "", "", "" };
			for (int j = 0; j < tempArray.length; j++) {
				tempArray2[j] = tempArray[j];
			}
			result += tempArray2[0]
					+ getSpaces(maximumLengths[0] - tempArray2[0].length())
					+ " ";
			result += tempArray2[1]
					+ getSpaces(maximumLengths[1] - tempArray2[1].length())
					+ " ";
			result += tempArray2[2]
					+ getSpaces(maximumLengths[2] - tempArray2[2].length())
					+ " ";
			result += tempArray2[3]
					+ getSpaces(maximumLengths[3] - tempArray2[3].length())
					+ " ";
			result += tempArray2[4]
					+ getSpaces(maximumLengths[4] - tempArray2[4].length())
					+ " ";
			result += tempArray2[5]
					+ getSpaces(maximumLengths[5] - tempArray2[5].length())
					+ " ";
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

	private String format(String text) {
		String newText = "";
		newText += "Index\t" + " " + "Name\t" + " " + "Additional Details\n";
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
		newText += "Index\t" + " " + "Name\t" + " " + "Additional Details\n";
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

	private int[] getMaximumLengths(ArrayList<String> unformatted) {
		int[] lengths = new int[6];
		lengths[0] = 0;
		lengths[1] = 0;
		lengths[2] = 0;
		lengths[3] = 0;
		lengths[4] = 0;
		lengths[5] = 0;
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
		if (evt.getKeyCode() == 38) {
			textField1.setText(previousEntry);
		}
		if (evt.getKeyCode() == 39) {
			jTextPane2
					.setText("FORMAT : [operation] [key words] [date and time] [r-reminder time]");
		}// TODO add your handling code here: // TODO add your handling code
			// here:
	}// GEN-LAST:event_textField1KeyPressed

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
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTabbedPane jTabbedPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextArea jTextArea3;
	private javax.swing.JTextPane jTextPane1;
	private javax.swing.JTextPane jTextPane2;
	private java.awt.TextField textField1;
	// End of variables declaration
}