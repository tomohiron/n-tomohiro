package twitterclient.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TwitterClient implements EntryPoint {

	private static final int REFRESH_INTERVAL = 5000; // ms

	private VerticalPanel mainPanel = new VerticalPanel();

	private TextBox idTextBox = new TextBox();
	private TextBox passwordTextBox = new PasswordTextBox();

	private FlexTable statusListFlexTable = new FlexTable();
	private HorizontalPanel sendPanel = new HorizontalPanel();
	private TextBox newStatusTextBox = new TextBox();
	private Button sendButton = new Button("send");
	private Label lastUpdatedLabel = new Label();
	private Label errorMsgLabel = new Label();

	private StatusListServiceAsync statusListSvc = GWT
			.create(StatusListService.class);

	public void onModuleLoad() {
		loadTwitterClient();
	}

	private void loadTwitterClient() {
		// Create table for stock data.
		statusListFlexTable.setText(0, 0, "name");
		statusListFlexTable.setText(0, 1, "status");

		statusListFlexTable.setCellPadding(6);
		statusListFlexTable.getRowFormatter().addStyleName(0, "listHeader");
		statusListFlexTable.addStyleName("list");

		// Assemble Add Stock panel.
		sendPanel.add(newStatusTextBox);
		sendPanel.add(sendButton);
		sendPanel.addStyleName("sendPanel");

		// Assemble Main panel.
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);

		mainPanel.add(idTextBox);
		mainPanel.add(passwordTextBox);

		mainPanel.add(errorMsgLabel);
		mainPanel.add(statusListFlexTable);
		mainPanel.add(sendPanel);
		mainPanel.add(lastUpdatedLabel);

		// Associate the Main panel with the HTML host page.
		RootPanel.get("statusList").add(mainPanel);

		// Move cursor focus to the input box.
		newStatusTextBox.setFocus(true);

		// Setup timer to refresh list automatically.
		Timer refreshTimer = new Timer() {
			@Override
			public void run() {
				refreshStatusList();
			}
		};
		refreshTimer.scheduleRepeating(REFRESH_INTERVAL);

		// Listen for mouse events on the Add button.
		sendButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				sendStatus();
			}

		});

		// Listen for keyboard events in the input box.
		newStatusTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
					sendStatus();
				}
			}
		});
	}

	private void sendStatus() {
	}

	private void refreshStatusList() {
		AsyncCallback<TwitterStatus[]> callback = new AsyncCallback<TwitterStatus[]>() {
			public void onFailure(Throwable caught) {
				String details = caught.getMessage();
				errorMsgLabel.setText("Error: " + details);
				errorMsgLabel.setVisible(true);
			}

			public void onSuccess(TwitterStatus[] result) {
				updateTable(result);
			}
		};

		String id = idTextBox.getText();
		String password = passwordTextBox.getText();
		statusListSvc.getStatusList(id, password, callback);
	}

	private void updateTable(TwitterStatus[] statusList) {
		for (int i = 0; i < statusList.length; ++i) {
			statusListFlexTable.setText(i + 1, 0, statusList[i].getName());
			statusListFlexTable.setText(i + 1, 1, statusList[i].getStatus());
		}

		lastUpdatedLabel.setText("Last update : "
				+ DateTimeFormat.getMediumDateTimeFormat().format(new Date()));

		errorMsgLabel.setVisible(false);
	}
}