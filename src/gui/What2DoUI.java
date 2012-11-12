package gui;

import java.lang.Object;
import javax.swing.BorderFactory;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import alarm.AlarmThread;
import javax.swing.JFrame;
import javax.swing.plaf.ColorUIResource;

import logAndException.Log;
import logAndException.MessageHandler;
import executor.Executor;
//import gui.FeedbackToUser;
import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author SANDEEP
 */
public class What2DoUI extends javax.swing.JFrame {

	private static final String MESSAGE_FEEDBACK_NOT_UPDATE_HTML = "<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif;color: red; \">%s</span></b></p></html>";
	private static final String MESSAGE_FEEDBACK_UPDATE_HTML = "<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif;color: rgb(50, 205, 50)\"; >%s</span></b></p></html>";
	private static final String MESSAGE_NEXT_PAGE_HTML = "<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(50,205,50)\"; >Switched to Next Page</span></b></p></html>";
	private static final String MESSAGE_PREVIOUS_PAGE_HTML = "<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(50,205,50)\"; >Switched to Previous page</span></b></p></html>";

	// Constants related to positioning in containers,
	private static final String DOT_DOT = "..";
	private static final int ARROW_KEY_UP = 40;
	private static final int ARROW_KEY_DOWN = 38;

	// Constants to set characterisitcs of Jcomponents
	private static final int TEXT_AREAS_PREFERRED_SIZE_VERTICAL = 298;
	private static final int TEXT_AREAS_PREFERRED_SIZE_HORIZONTAL = 880;
	private static final int TEXT_AREAS_HEIGHT = 320;
	private static final int TEXT_AREAS_WIDTH = 930;
	private static final int TEXT_AREAS_Y_COORDINATE = 10;
	private static final int TEXT_AREA_X_COORDINATE = 10;
	private static final char KEY_RETURN = '\n';
	private static final int FONT_SIZE_OTHER_TEXT_DISPLAYS = 12;
	private static final int FONT_STYLE_OTHER_TEXT_DISPLAYS = 1;
	private static final int FONT_SIZE_USER_INPUT_AREA = 18;
	private static final int FONT_STYLE_USER_INPUT_AREA = 0;
	private static final String FONT_DISPLAY_TEXT_AREAS = "Monospaced";
	private static final int WHITE_BACKGROUND_B = 227;
	private static final int WHITE_BACKGROUND_G = 227;
	private static final int WHITE_BACKGROUND_R = 227;
	private static final int GREY_BACKGROUND_B = 59;
	private static final int GREY_BACKGROUND_G = 59;
	private static final int GREY_BACKGROUND_R = 59;
	private static final String MESSAGE_WELCOME_HTML = "<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(223,223,223)\">Welcome! This is Igor, at your service.</b></span></p></html>";
	private static final String MESSAGE_SUGGESTION_INITIAL = "<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255)\">You can do any of the following :</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255)\">- </span><b><span style=\"font-family: Helvetica, sans-serif; color: red; \">add (+)/delete(-)/search/update/undo/done/undone/exit</span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>";
	private static final int SCREEN_HALF = 6;
	private static final int FONT_HEADER_STYLE = 1;
	private static final int FONT_HEADER_SIZE = 14;
	private static final String FONT_HEADERS = "Arial";

	// Heading messages
	private static final String MESSAGE_WELCOME = "This is Igor, at your service";
	private static final String MESSAGE_TITLE_UPCOMING = "Your upcoming events are :";
	private static final String MESSAGE_TITLE_FLOATING = "Your floating events are :";
	private static final String MESSAGE_TITLE_SEARCH = "Your search results are :";

	public What2DoUI() {
		initComponents();
	}

	DataToUser dataToUser = new DataToUser();
	//FeedbackToUser feedbackToUser=new FeedbackToUser();
	ArrayList<String> upcomingEvents = new ArrayList<String>();
	ArrayList<String> floatingEvents = new ArrayList<String>();
	ArrayList<String> searchResults = new ArrayList<String>();

	ArrayList<ArrayList<String>> formattedUpcomingEvents = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> formattedFloatingEvents = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> formattedSearchResults = new ArrayList<ArrayList<String>>();

	String data_upcoming_events = "";
	String data_floating_events = "";
	String data_search_results = "";

	Vector<Integer> shiftArray = new Vector<Integer>();
	boolean shiftFlag = false;
	int upcoming_start_position = 0;
	int upcoming_end_position = 6;
	int floating_start_position = 0;
	int floating_end_position = 6;
	int search_start_position = 0;
	int search_end_position = 6;

	boolean nextEntryFlag = false;
	String incompleteString = "";

	String view = "upcoming";

	char lastEvent = ' ';
	int flag = 0;
	static boolean toUpdate = true;
	Vector<String> previousEntry = new Vector<String>();
	int previousIndex = 0;
	int updateFlag = 0;
	boolean searchView = false;
	boolean updateFlagBool = false;

	@SuppressWarnings("unchecked")
	private void initComponents() {
		initializeGUIElements();
		setExitOnClose();
		setFonts();
		setBackgrounds();
		setCursorForUserTextFields();
		addListenerToUserTextField();
		setViewPorts();
		setBorders();
		setOpacity();
		setTextAlignment();
		hideCertainAreasInitially();
		setLayoutsOfComponents();
		displayDatabase("");
		setTitle(MESSAGE_WELCOME);
		setApplicationIcon();
		setResizable(false);
		setInitialText();
		setAppToScreenCenter();
		createSystemTrayIcon();
		pack();

	}

	private void createSystemTrayIcon() {
		SystemTrayIntegration systemTray = new SystemTrayIntegration(this);
		systemTray.createSystemTray();
	}

	private void setAppToScreenCenter() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / SCREEN_HALF - this.getSize().width
				/ SCREEN_HALF, dim.height / SCREEN_HALF - this.getSize().height
				/ SCREEN_HALF);
	}

	private void setExitOnClose() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setApplicationIcon() {
		Image image = null;
		try {
			image = ImageIO.read(getClass().getResource("/logo.jpg"));
		} catch (IOException ex) {
		}

		setIconImage(image);
	}

	private void setLayoutsOfComponents() {
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				upcomingEventsPanel);
		upcomingEventsPanel.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPaneForUpcoming,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								TEXT_AREAS_PREFERRED_SIZE_HORIZONTAL,
								Short.MAX_VALUE).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPaneForUpcoming,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								TEXT_AREAS_PREFERRED_SIZE_VERTICAL,
								Short.MAX_VALUE).addContainerGap()));
		upcomingEventsPanel.setBounds(TEXT_AREA_X_COORDINATE,
				TEXT_AREAS_Y_COORDINATE, TEXT_AREAS_WIDTH, TEXT_AREAS_HEIGHT);
		jLayeredPane1.add(upcomingEventsPanel,
				javax.swing.JLayeredPane.DEFAULT_LAYER);
		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				searchResulsPanel);
		searchResulsPanel.setLayout(jPanel3Layout);
		searchResulsPanel.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPaneForSearch,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								TEXT_AREAS_PREFERRED_SIZE_HORIZONTAL,
								Short.MAX_VALUE).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPaneForSearch,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								TEXT_AREAS_PREFERRED_SIZE_VERTICAL,
								Short.MAX_VALUE).addContainerGap()));
		searchResulsPanel.setBounds(TEXT_AREA_X_COORDINATE,
				TEXT_AREAS_Y_COORDINATE, TEXT_AREAS_WIDTH, TEXT_AREAS_HEIGHT);
		jLayeredPane1.add(searchResulsPanel,
				javax.swing.JLayeredPane.DEFAULT_LAYER);
		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				floatingEventsPanel);
		floatingEventsPanel.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPaneForFloating,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								TEXT_AREAS_PREFERRED_SIZE_HORIZONTAL,
								Short.MAX_VALUE).addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPaneForFloating,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								TEXT_AREAS_PREFERRED_SIZE_VERTICAL,
								Short.MAX_VALUE).addContainerGap()));
		floatingEventsPanel.setBounds(TEXT_AREA_X_COORDINATE,
				TEXT_AREAS_Y_COORDINATE, TEXT_AREAS_WIDTH, TEXT_AREAS_HEIGHT);
		jLayeredPane1.add(floatingEventsPanel,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				panelContainingEverything);
		panelContainingEverything.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLayeredPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																946,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								userInputField,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								909,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								scrollPaneForFeedback,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								909,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								scrollPaneForSuggestions,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								909,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(0, 0, Short.MAX_VALUE)));
		;
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												userInputField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												26,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												scrollPaneForFeedback,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												scrollPaneForSuggestions,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												29,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLayeredPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												316,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { scrollPaneForSuggestions,
						scrollPaneForFeedback, userInputField });

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelContainingEverything,
				javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelContainingEverything,
				javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
	}

	private void hideCertainAreasInitially() {
		hideFloatingDisplay();
		hideSearchDisplay();
	}

	private void hideSearchDisplay() {
		floatingEventsPanel.setVisible(false);
	}

	private void hideFloatingDisplay() {
		searchResulsPanel.setVisible(false);
	}

	private void setTextAlignment() {
		setHorizontalTextAlignment();
		setVerticalTextAlignment();
	}

	private void setVerticalTextAlignment() {
		searchResultsTextArea.setVerticalAlignment(JLabel.TOP);
		upcomingEventsTextArea.setVerticalAlignment(JLabel.TOP);
		floatingEventsTextArea.setVerticalAlignment(JLabel.TOP);
		suggestionLabel.setVerticalAlignment(JLabel.CENTER);
		feedbackLabel.setVerticalAlignment(JLabel.CENTER);
	}

	private void setHorizontalTextAlignment() {
		searchResultsTextArea.setHorizontalAlignment(JLabel.LEFT);
		upcomingEventsTextArea.setHorizontalAlignment(JLabel.LEFT);
		floatingEventsTextArea.setHorizontalAlignment(JLabel.LEFT);
		suggestionLabel.setHorizontalAlignment(JLabel.CENTER);
		feedbackLabel.setHorizontalAlignment(JLabel.CENTER);
	}

	private void setOpacity() {
		searchResultsTextArea.setOpaque(true);
		upcomingEventsTextArea.setOpaque(true);
		floatingEventsTextArea.setOpaque(true);
		suggestionLabel.setOpaque(true);
		feedbackLabel.setOpaque(true);
	}

	private void setBorders() {

		upcomingEventsTextArea.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, MESSAGE_TITLE_UPCOMING,
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font(FONT_HEADERS, FONT_HEADER_STYLE,
								FONT_HEADER_SIZE), java.awt.Color.black));
		searchResultsTextArea.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, MESSAGE_TITLE_SEARCH,
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font(FONT_HEADERS, 1, 14),
						java.awt.Color.black));
		floatingEventsTextArea.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, MESSAGE_TITLE_FLOATING,
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font(FONT_HEADERS, 1, 14),
						java.awt.Color.black));
	}

	private void setViewPorts() {
		scrollPaneForFeedback.setViewportView(feedbackLabel);
		scrollPaneForUpcoming.setViewportView(upcomingEventsTextArea);
		scrollPaneForSearch.setViewportView(searchResultsTextArea);
		scrollPaneForFloating.setViewportView(floatingEventsTextArea);
		scrollPaneForSuggestions.setViewportView(suggestionLabel);

	}

	private void setInitialText() {
		feedbackLabel.setText(MESSAGE_WELCOME_HTML);
		suggestionLabel.setText(MESSAGE_SUGGESTION_INITIAL);

	}

	private void addListenerToUserTextField() {
		userInputField.addKeyListener(new java.awt.event.KeyAdapter() {
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
	}

	private void setCursorForUserTextFields() {
		userInputField.setCursor(new java.awt.Cursor(
				java.awt.Cursor.TEXT_CURSOR));
	}

	private void setBackgrounds() {
		panelContainingEverything.setBackground(new java.awt.Color(
				GREY_BACKGROUND_R, GREY_BACKGROUND_G, GREY_BACKGROUND_B));
		upcomingEventsPanel.setBackground(new java.awt.Color(GREY_BACKGROUND_R,
				GREY_BACKGROUND_G, GREY_BACKGROUND_B));
		searchResulsPanel.setBackground(new java.awt.Color(GREY_BACKGROUND_R,
				GREY_BACKGROUND_G, GREY_BACKGROUND_B));
		suggestionLabel.setBackground(new java.awt.Color(GREY_BACKGROUND_R,
				GREY_BACKGROUND_G, GREY_BACKGROUND_B));
		feedbackLabel.setBackground(new java.awt.Color(GREY_BACKGROUND_R,
				GREY_BACKGROUND_G, GREY_BACKGROUND_B));
		floatingEventsPanel.setBackground(new java.awt.Color(GREY_BACKGROUND_R,
				GREY_BACKGROUND_G, GREY_BACKGROUND_B));
		userInputField.setBackground(new java.awt.Color(WHITE_BACKGROUND_R,
				WHITE_BACKGROUND_G, WHITE_BACKGROUND_B));
		searchResultsTextArea.setBackground(new java.awt.Color(
				WHITE_BACKGROUND_R, WHITE_BACKGROUND_G, WHITE_BACKGROUND_B));
		upcomingEventsTextArea.setBackground(new java.awt.Color(
				WHITE_BACKGROUND_R, WHITE_BACKGROUND_G, WHITE_BACKGROUND_B));
		floatingEventsTextArea.setBackground(new java.awt.Color(
				WHITE_BACKGROUND_R, WHITE_BACKGROUND_G, WHITE_BACKGROUND_B));
	}

	private void setFonts() {
		userInputField.setFont(new java.awt.Font(FONT_DISPLAY_TEXT_AREAS,
				FONT_STYLE_USER_INPUT_AREA, FONT_SIZE_USER_INPUT_AREA)); // NOI18N

		suggestionLabel.setFont(new java.awt.Font(FONT_DISPLAY_TEXT_AREAS,
				FONT_STYLE_OTHER_TEXT_DISPLAYS, FONT_SIZE_OTHER_TEXT_DISPLAYS));
		feedbackLabel.setFont(new java.awt.Font(FONT_DISPLAY_TEXT_AREAS,
				FONT_STYLE_OTHER_TEXT_DISPLAYS, FONT_SIZE_OTHER_TEXT_DISPLAYS));

		upcomingEventsTextArea.setFont(new java.awt.Font(
				FONT_DISPLAY_TEXT_AREAS, FONT_STYLE_OTHER_TEXT_DISPLAYS,
				FONT_SIZE_OTHER_TEXT_DISPLAYS));
		searchResultsTextArea.setFont(new java.awt.Font(
				FONT_DISPLAY_TEXT_AREAS, FONT_STYLE_OTHER_TEXT_DISPLAYS,
				FONT_SIZE_OTHER_TEXT_DISPLAYS));
		floatingEventsTextArea.setFont(new java.awt.Font(
				FONT_DISPLAY_TEXT_AREAS, FONT_STYLE_OTHER_TEXT_DISPLAYS,
				FONT_SIZE_OTHER_TEXT_DISPLAYS)); // NOI18N

	}

	private void initializeGUIElements() {
		panelContainingEverything = new javax.swing.JPanel();
		userInputField = new java.awt.TextField();
		scrollPaneForFeedback = new javax.swing.JScrollPane();
		feedbackLabel = new javax.swing.JLabel();
		suggestionLabel = new javax.swing.JLabel();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		upcomingEventsPanel = new javax.swing.JPanel();
		scrollPaneForUpcoming = new javax.swing.JScrollPane();
		upcomingEventsTextArea = new javax.swing.JLabel();
		searchResulsPanel = new javax.swing.JPanel();
		scrollPaneForSearch = new javax.swing.JScrollPane();
		searchResultsTextArea = new javax.swing.JLabel();
		floatingEventsPanel = new javax.swing.JPanel();
		scrollPaneForFloating = new javax.swing.JScrollPane();
		floatingEventsTextArea = new javax.swing.JLabel();
		scrollPaneForSuggestions = new javax.swing.JScrollPane();
	}

	private void textField1KeyTyped(java.awt.event.KeyEvent evt)
			throws Exception {// GEN-FIRST:event_textField1KeyTyped
		if (!(shiftFlag)) {

			if (evt.getKeyChar() != KEY_RETURN){
				setSuggestionBoxTest(evt);
			}
		actUponUserCommand(evt);
		//actUponUserCommand(evt, userInputField.getText());
		}		
		else{
			prepareForNextEntry("");
		}
		shiftFlag=false;
	}

	private void actUponUserCommand(java.awt.event.KeyEvent evt)
			throws Exception {
		if (evt.getKeyChar() == KEY_RETURN) {

			flag = 0;

			String data = userInputField.getText();

			if (data.equals("n")) {
				updateUpcomingStartPosition();
				if (view.equals("floating")) {
					setViewToFloating();
					updateFloatingStartPosition();
				}
				if (view.equals("search")) {
					updateSearchStartPosition();
					setViewToSearch();
				}

				displayDatabase("");
				feedbackLabel.setText(MESSAGE_NEXT_PAGE_HTML);
				prepareForNextEntry(data);
				return;

			}
			if (data.equals("p")) {
				if (view.equals("upcoming")) {
					setViewToUpcoming();
					decreaseUpcomingStartPosition();

				}
				if (view.equals("floating")) {
					setViewToFloating();
					decreaseFloatingStartPosition();
				}
				if (view.equals("search")) {
					decreaseSearchStartPosition();
					setViewToSearch();
				}
				displayDatabase("");
				feedbackLabel.setText(MESSAGE_PREVIOUS_PAGE_HTML);
				prepareForNextEntry(data);
				return;

			}
			if (data.toLowerCase().trim().startsWith("update")) {
				data = data.trim();
				int index = extractIndex(data);
				if (index != 0) {
					updateFlagBool = true;
					String event = updateStream(data, index);
					if (event != null) {
						data += event;
						userInputField.setText(data);
					} else {
						userInputField.setText("");
						feedbackLabel
								.setText(String
										.format("<html><p class=\"MsoNormal\"><b><span style=\"color: red\"; >%s</span></b></p></html>",
												"ERROR: Update Index not Found"));
						prepareForNextEntry(data);
						return;
					}
				} else {
					updateFlagBool = false;
				}
			}
			if (data.toLowerCase().contains("search")) {
				searchView = true;
				setViewToSearch();
				Executor.searchToTrue();
				feedbackLabel
						.setText(String
								.format("<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(0, 176, 80)\"; >%s</span></b></p></html>",
										"Switched to Search View"));
				prepareForNextEntry(data);
			} else if (data.toLowerCase().contains("floating")) {
				searchView = false;
				Executor.searchToFalse();
				setViewToFloating();
				feedbackLabel
						.setText(String
								.format("<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(0, 176, 80)\"; >%s</span></b></p></html>",
										"Switched to Floating View"));
				prepareForNextEntry(data);
				return;
			} else if (data.toLowerCase().contains("upcoming")) {
				searchView = false;
				Executor.searchToFalse();
				setViewToUpcoming();
				feedbackLabel
						.setText(String
								.format("<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(0, 176, 80)\"; >%s</span></b></p></html>",
										"Switched to Default View"));
				prepareForNextEntry(data);
				return;
			}
			if (!(data.toLowerCase().contains("update"))) {
				updateFlagBool = false;
			}
			if (updateFlagBool == false) {
				String message = analyzeInputUsingLogic(data);
				ArrayList<String> feedback = Executor.getFeedback();
				if (!feedback.isEmpty()) {
					String feedbackMessage = "";
					if (feedback.size() == 1)
						feedbackMessage = feedback.get(0);
					if (feedback.size() > 1)
						feedbackMessage = "WARNING: The New Event is Clashed AND Before PRESENT TIME.";
					feedbackMessage += "\nDo you want to PROCEED? - ENTER YES/NO";
					String[] option = { "YES", "NO" };
					String input;
					Object inputObject = JOptionPane.showInputDialog(this,
							feedbackMessage, "WARNING!",
							JOptionPane.WARNING_MESSAGE, null, option, "YES");
					if (inputObject != null)
						input = inputObject.toString();
					else
						input = null;
					if (input != null) {
						input = input.trim().toLowerCase();
						if (input.startsWith("no") || input.startsWith("n")) {
							Executor.analyze("undo");
							prepareForNextEntry(data);
							return;
						}
					} else if (input == null) {
						Executor.analyze("undo");
						prepareForNextEntry(data);
						return;
					}
				}
				displayDatabase(message);
				prepareForNextEntry(data);

			}
		}
	}

	private void decreaseSearchStartPosition() {
		dataToUser.search_start_position -= 6;
		if (dataToUser.search_start_position < 0) {
			dataToUser.search_start_position = 0;
		}
	}

	private void decreaseFloatingStartPosition() {
		dataToUser.floating_start_position -= 6;
		if (dataToUser.floating_start_position < 0) {
			dataToUser.floating_start_position = 0;
		}
	}

	private void decreaseUpcomingStartPosition() {
		dataToUser.upcoming_start_position -= 6;

		if (dataToUser.upcoming_start_position < 0) {
			dataToUser.upcoming_start_position = 0;
		}
	}

	private void updateSearchStartPosition() {
		dataToUser.search_start_position += 6;
	}

	private void updateFloatingStartPosition() {
		dataToUser.floating_start_position += 6;
	}

	private void updateUpcomingStartPosition() {
		if (view.equals("upcoming"))
			dataToUser.upcoming_start_position += 6;
	}

	private String analyzeInputUsingLogic(String data) throws Exception {
		int index;
		String message;

		index = obtainIndexFromExecutor(data);
		message = obtainMessageFromMessageHandler(index);
		checkIfToUpdate(message);

		return message;
	}

	private void checkIfToUpdate(String message) {
		toUpdate = !(message.contains("Error"));
	}

	private String obtainMessageFromMessageHandler(int index) {
		String message;
		message = MessageHandler.getMessage(index);
		return message;
	}

	private int obtainIndexFromExecutor(String data) throws Exception {
		int index;
		index = Executor.analyze(data);
		return index;
	}

	private void setViewToFloating() {
		view = "floating";
		hideUpcomingDisplay();
		hideFloatingDisplay();
		unhideFloatingDisplay();
	}

	private void unhideFloatingDisplay() {
		floatingEventsPanel.setVisible(true);
	}

	private void hideUpcomingDisplay() {
		upcomingEventsPanel.setVisible(false);
	}

	private void setViewToUpcoming() {
		view = "upcoming";
		hideFloatingDisplay();
		hideSearchDisplay();
		unhideUpcomingDisplay();
	}

	private void unhideUpcomingDisplay() {
		upcomingEventsPanel.setVisible(true);
	}

	private void setViewToSearch() {
		view = "search";
		hideUpcomingDisplay();
		hideSearchDisplay();
		unhideSearchDisplay();
	}

	private void unhideSearchDisplay() {
		searchResulsPanel.setVisible(true);
	}

	// @Akaash
	private void setSuggestionBoxTest(java.awt.event.KeyEvent evt) {
		if (evt.getKeyChar() == 8) {
			flag = 0;
			if (!incompleteString.isEmpty()) {
				incompleteString = incompleteString.substring(0,
						incompleteString.length() - 1);
			}
			if (incompleteString.isEmpty()) {
				suggestionLabel
						.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif;color: rgb(255,255,255); \">You can do any of the following :</span></b><span style=\"font-family: Helvetica, sans-serif;color: rgb(255,255,255) \">- </span><b><span style=\"font-family: Helvetica, sans-serif; color: red; \">add (+)/delete(-)/search/update/undo/done/undone/exit</span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
			}
		} else {
			incompleteString += evt.getKeyChar();
			if (nextEntryFlag == true) {
				feedbackLabel
						.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(223,223,223)\">This is Igor, at your service.</b></span></p></html>");
				suggestionLabel
						.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif;color: rgb(255,255,255); \">You can do any of the following :</span></b><span style=\"font-family: Helvetica, sans-serif;color: rgb(255,255,255) \">- </span><b><span style=\"font-family: Helvetica, sans-serif; color: red; \">add (+)/delete(-)/search/update/undo/done/undone/exit</span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
				nextEntryFlag = false;
			}
		}
		boolean foundFlag = false;
		/*
		 * if (flag == 0 && ((evt.getKeyChar() == 'a') || (evt.getKeyChar() ==
		 * '+'))) {
		 */
		if (flag == 0
				&& ((("add ".startsWith(incompleteString.toLowerCase())) || "+ "
						.startsWith(incompleteString.toLowerCase())) || ((incompleteString
						.toLowerCase().startsWith("add ")) || incompleteString
						.toLowerCase().startsWith("+ ")))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("add ")
					|| incompleteString.equalsIgnoreCase("+ ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: [add] &nbsp <span style=\"color: red; font-family: Helvetica, sans-serif; \">[Key words]&nbsp [start time and date]&nbsp [End time and date]&nbsp [r-reminder time]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ((("delete ".startsWith(incompleteString.toLowerCase())) || "- "
						.startsWith(incompleteString.toLowerCase())) || ((incompleteString
						.toLowerCase().startsWith("delete ")) || incompleteString
						.toLowerCase().startsWith("- ")))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("delete ")
					|| incompleteString.equalsIgnoreCase("- ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: [delete] &nbsp<span style=\"font-family: Helvetica, sans-serif; color: red; \">[Index Number]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("done ".startsWith(incompleteString.toLowerCase()) || incompleteString
						.toLowerCase().startsWith("done "))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("done ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: [done]&nbsp <span style=\"font-family: Helvetica, sans-serif; color: red; \">[Index Number]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("search ".startsWith(incompleteString.toLowerCase()) || incompleteString
						.toLowerCase().startsWith("search "))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("search ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: [search] &nbsp<span style=\"font-family: Helvetica, sans-serif; color: red; \">[KeyWords (with/without Hash Tags)]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("update ".startsWith(incompleteString.toLowerCase()) || incompleteString
						.toLowerCase().startsWith("update "))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("update ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: [update] &nbsp<span style=\"font-family: Helvetica, sans-serif; color: red; \">[Index Number]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("undone ".startsWith((incompleteString).toLowerCase()) || incompleteString
						.toLowerCase().startsWith("undone "))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("undone ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: [undone]&nbsp <span style=\"font-family: Helvetica, sans-serif; color: red; \">[Index Number]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("undo ".startsWith(incompleteString.toLowerCase()) || incompleteString
						.toLowerCase().startsWith("undo "))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("undo ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: [undo]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); color: rgb(192, 80, 77); \"></span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("exit ".startsWith(incompleteString.toLowerCase()) || incompleteString
						.toLowerCase().startsWith("exit "))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("exit ")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: </span><span style=\"font-family: Helvetica, sans-serif; color: red; \"> [exit]</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("floating".startsWith(incompleteString.toLowerCase()) || incompleteString
						.toLowerCase().startsWith("floating"))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("floating")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: </span><span style=\"font-family: Helvetica, sans-serif; color: red; \"> [floating] <span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"> - To Switch to Floating tasks.</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0
				&& ("upcoming".startsWith(incompleteString.toLowerCase()) || incompleteString
						.toLowerCase().startsWith("upcoming"))
				&& !incompleteString.isEmpty()) {
			foundFlag = true;
			if (incompleteString.equalsIgnoreCase("upcoming")) {
				flag = 1;
			}
			lastEvent = evt.getKeyChar();
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \">FORMAT: </span><span style=\"font-family: Helvetica, sans-serif; color: red; \"> [upcoming] <span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"> - To Switch to Floating tasks.</span></b><span style=\"font-family: Helvetica, sans-serif; color: rgb(255,255,255); \"></span><b><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
		if (flag == 0 && foundFlag == false) {
			suggestionLabel
					.setText("<html><p class=\"MsoNormal\"><b><span style=\"font-family: Helvetica, sans-serif;color: rgb(255,255,255); \">You can do any of the following :</span></b><span style=\"font-family: Helvetica, sans-serif;color: rgb(255,255,255) \">- </span><b><span style=\"font-family: Helvetica, sans-serif; color: red; \">add (+)/delete(-)/search/update/undo/done/undone/exit</span><span style=\"color: rgb(192, 80, 77); \"></span></b></p></html>");
		}
	}

	private void prepareForNextEntry(String data) {
		incompleteString = "";
		nextEntryFlag = true;
		userInputField.setText("");
		if (!(data.equals(""))){
			previousEntry.add(data);
			previousIndex = previousEntry.size();
		}
	}

	// @Sandeep
	private String updateStream(String message, int index) throws Exception {
		String result = findEventByIndex(index);
		String formattedResult = formatResult(result);
		return formattedResult;
	}

	private String formatResult(String result) {
		if (result != null) {
			String formattedResult = " ";
			String formattedResultArray[] = result.split("\\..");
			formattedResult += formattedResultArray[1] + " ";
			formattedResult += "#" + formattedResultArray[2] + "";
			formattedResult += formattedResultArray[3] + " ";
			if (formattedResultArray.length > 5
					&& formattedResultArray[5] != "") {
				formattedResult += formattedResultArray[5] + " ";
			}
			if (formattedResultArray.length > 6
					&& formattedResultArray[6] != "") {
				formattedResult += formattedResultArray[6] + " ";
			}
			if (formattedResultArray.length > 7
					&& formattedResultArray[7] != "") {
				formattedResult += formattedResultArray[7] + " ";
			}
			formattedResult.trim();
			return formattedResult;
		} else
			return null;
	}

	private String findEventByIndex(int index) {
		String index_string = Integer.toString(index) + DOT_DOT;
		if (index == 0) {
			return null;
		} else {
			for (int i = 0; i < searchResults.size(); i++)
				if (searchResults.get(i).startsWith(index_string))
					return searchResults.get(i);
			for (int i = 0; i < upcomingEvents.size(); i++)
				if (upcomingEvents.get(i).startsWith(index_string))
					return upcomingEvents.get(i);
			for (int i = 0; i < floatingEvents.size(); i++)
				if (floatingEvents.get(i).startsWith(index_string))
					return floatingEvents.get(i);
		}
		return null;
	}

	private int extractIndex(String message) {
		String trimmedMessage = message.replace("update ", "");
		try {
			return Integer.parseInt(trimmedMessage);
		} catch (Exception noIndex) {
			return 0;
		}
	}
/*
	private void displayDatabase(String message) {

		dataToUser.getResults();
		data_upcoming_events = dataToUser.data_upcoming_events;
		data_floating_events = dataToUser.data_floating_events;
		data_search_results = dataToUser.data_search_results;
		displayOnScreen();

		if (!toUpdate) {
			feedbackLabel.setText(String.format(
					MESSAGE_FEEDBACK_NOT_UPDATE_HTML, message));
		} else {
			feedbackLabel.setText(String.format(MESSAGE_FEEDBACK_UPDATE_HTML,
					message));
		}
	}
*/
	private void displayDatabase(String message) {

		dataToUser.getResults();
		data_upcoming_events = dataToUser.data_upcoming_events;
		data_floating_events = dataToUser.data_floating_events;
		data_search_results = dataToUser.data_search_results;
		upcomingEvents=dataToUser.upcomingEvents;
		floatingEvents=dataToUser.floatingEvents;
		searchResults=dataToUser.searchResults;
		displayOnScreen();
		
		if (!toUpdate) {
			feedbackLabel.setText(String.format(
					MESSAGE_FEEDBACK_NOT_UPDATE_HTML, message));
		} else {
			feedbackLabel.setText(String.format(MESSAGE_FEEDBACK_UPDATE_HTML,
					message));
		}
	}
	private void displayOnScreen() {
		displayUpcomingEvents(data_upcoming_events);
		displayFloatingEvents(data_floating_events);
		displaySearchResults(data_search_results);
	}

	private void displaySearchResults(String data_search_results) {
		searchResultsTextArea.setText(data_search_results);
	}

	private void displayFloatingEvents(String data_floating_events) {
		floatingEventsTextArea.setText(data_floating_events);
	}

	private void displayUpcomingEvents(String data_upcoming_events) {
		upcomingEventsTextArea.setText(data_upcoming_events);
	}

	private void textField1KeyPressed(java.awt.event.KeyEvent evt) {
		
		shiftArray.add(evt.getKeyCode());

		boolean onlyOneEntry = (previousIndex == previousEntry.size() - 1);
		boolean noEntries = previousEntry.isEmpty();
		boolean unusedPreviousEntriesRemaining = previousIndex > 0;

		if (evt.getKeyCode() == ARROW_KEY_DOWN
				&& unusedPreviousEntriesRemaining && !noEntries) {
			userInputField.setText(previousEntry.get(--previousIndex));
		}
		if (evt.getKeyCode() == ARROW_KEY_UP && !onlyOneEntry && !noEntries) {
			userInputField.setText(previousEntry.get(++previousIndex));
		} 
		else {
			if (evt.getKeyCode() == ARROW_KEY_UP && onlyOneEntry) {
				userInputField.setText("");
			}
		}
		if(shiftArray.size()>1 && shiftArray.get(shiftArray.size()-2)==17 && shiftArray.get(shiftArray.size()-1)==70)
		{
			shiftArray.clear();
			setViewToFloating();
			shiftFlag=true;
			feedbackLabel
			.setText(String
					.format("<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(0, 176, 80)\"; >%s</span></b></p></html>",
							"Switched to Floating View"));
		}
		if(shiftArray.size()>1 && shiftArray.get(shiftArray.size()-2)==17 && shiftArray.get(shiftArray.size()-1)==85)
		{
			shiftArray.clear();
			setViewToUpcoming();
			shiftFlag=true;
			feedbackLabel
			.setText(String
					.format("<html><p class=\"MsoNormal\"><b><span style=\"color: rgb(0, 176, 80)\"; >%s</span></b></p></html>",
							"Switched to Default View"));
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		MessageHandler.setUpList();
		Log.setup();
		AlarmThread newAlarm = AlarmThread.getInstance();
		new Thread(newAlarm).start();
		try {
			Executor.loadDatabase();
		} catch (Exception e) {
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
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new What2DoUI().setVisible(true);
			}
		});
	}

	// Declaration of JComponents
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JPanel panelContainingEverything;
	private javax.swing.JPanel upcomingEventsPanel;
	private javax.swing.JPanel searchResulsPanel;
	private javax.swing.JPanel floatingEventsPanel;
	private javax.swing.JScrollPane scrollPaneForSearch;
	private javax.swing.JScrollPane scrollPaneForSuggestions;
	private javax.swing.JScrollPane scrollPaneForUpcoming;
	private javax.swing.JScrollPane scrollPaneForFeedback;
	private javax.swing.JScrollPane scrollPaneForFloating;
	private javax.swing.JLabel searchResultsTextArea;
	private javax.swing.JLabel upcomingEventsTextArea;
	private javax.swing.JLabel floatingEventsTextArea;
	private javax.swing.JLabel suggestionLabel;
	private javax.swing.JLabel feedbackLabel;
	private java.awt.TextField userInputField;

}