package view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import dao.BookSaleDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import model.BookSale;
import model.CustomScrollBar;

public class UpdateBook extends JFrame implements ActionListener {
	private BookSale book;

	// JLabels to indicate what each field contains
	private JLabel lblId;
	private JLabel lblIsbn;
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblGenre;
	private JLabel lblPublisher;
	private JLabel lblEdition;
	private JLabel lblSalePrice;
	private JLabel lblQuantity;

	// JTextFields to allow typing information
	private JTextField txtId;
	private JTextField txtIsbn;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtPublisher;
	private JTextField txtEdition;
	private JTextField txtSalePrice;
	private JTextField txtQuantity;

	// JPanels to organise the visual elements
	private JPanel pnlMain;
	private JPanel pnlForm;
	private JPanel pnlButton;

	// JButton to allow interaction with the user
	private JButton btnUpdate;
	private JButton btnReturn;

	// Images and logos that will be used in the software
	private ImageIcon logo;

	// Importing method from the Dashboard class to update the dashboard
	private Dashboard dashboard;

	// Attributes for storing user information and manipulating the database
	private BookSaleDAO dao;

	public UpdateBook(Dashboard dashboard) {
		super();
		this.dashboard = dashboard;
		initComponents();
		setFields();
		pack();
		setLocationRelativeTo(null);
	}

	public UpdateBook(BookSale book, Dashboard dashboard) {
		this.book = book;
		this.dashboard = dashboard;
		initComponents();
		setFields();
		pack();
		setLocationRelativeTo(null);

		// Window settings
		this.setTitle("Library Messias");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(700, 650));
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(new Color(0xEAE7EE));
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				if (newSize.width < 700 || newSize.height < 650) {
					setSize(Math.max(newSize.width, 700), Math.max(newSize.height, 650));
				}
			}
		});

		// Software's window logo
		logo = new ImageIcon(LoginPage.class.getResource("/logo.png"));
		this.setIconImage(logo.getImage());
	}

	private void initComponents() {
		setTitle("Atualizar Livro");

		// Main Panel that will hold the other panels
		pnlMain = new JPanel();
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
		pnlMain.setBackground(new Color(0xEAE7EE));

		// Form Panel
		pnlForm = new JPanel(new GridBagLayout());
		pnlForm.setBackground(new Color(0xEAE7EE));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.LINE_START;

		// Creating custom border
		Border lineBorder = BorderFactory.createLineBorder(new Color(0x111315), 1);
		Border paddingBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);

		constraints.gridx = 0;
		constraints.gridy = 0;
		lblId = new JLabel("ID:*");
		lblId.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblId, constraints);

		constraints.gridy = 1;
		txtId = new JTextField(50);
		txtId.setFont(new Font("Inter", Font.PLAIN, 13));
		txtId.setBackground(new Color(0xEAE7EE));
		txtId.setBorder(compoundBorder);
		txtId.setEnabled(false);
		pnlForm.add(txtId, constraints);

		constraints.gridy = 3;
		lblIsbn = new JLabel("ISBN CODE:*");
		lblIsbn.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblIsbn, constraints);

		constraints.gridy = 4;
		txtIsbn = new JTextField(50);
		txtIsbn.setFont(new Font("Inter", Font.PLAIN, 13));
		txtIsbn.setBackground(new Color(0xEAE7EE));
		txtIsbn.setBorder(compoundBorder);
		pnlForm.add(txtIsbn, constraints);

		constraints.gridy = 5;
		lblTitle = new JLabel("BOOK TITLE:*");
		lblTitle.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblTitle, constraints);

		constraints.gridy = 6;
		txtTitle = new JTextField(50);
		txtTitle.setFont(new Font("Inter", Font.PLAIN, 13));
		txtTitle.setBackground(new Color(0xEAE7EE));
		txtTitle.setBorder(compoundBorder);
		pnlForm.add(txtTitle, constraints);

		constraints.gridy = 7;
		lblAuthor = new JLabel("AUTHOR:*");
		lblAuthor.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblAuthor, constraints);

		constraints.gridy = 8;
		txtAuthor = new JTextField(50);
		txtAuthor.setFont(new Font("Inter", Font.PLAIN, 13));
		txtAuthor.setBackground(new Color(0xEAE7EE));
		txtAuthor.setBorder(compoundBorder);
		pnlForm.add(txtAuthor, constraints);

		constraints.gridy = 9;
		lblGenre = new JLabel("GENRE:*");
		lblGenre.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblGenre, constraints);

		constraints.gridy = 10;
		txtGenre = new JTextField(50);
		txtGenre.setFont(new Font("Inter", Font.PLAIN, 13));
		txtGenre.setBackground(new Color(0xEAE7EE));
		txtGenre.setBorder(compoundBorder);
		pnlForm.add(txtGenre, constraints);

		constraints.gridy = 11;
		lblPublisher = new JLabel("PUBLISHER:*");
		lblPublisher.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblPublisher, constraints);

		constraints.gridy = 12;
		txtPublisher = new JTextField(50);
		txtPublisher.setFont(new Font("Inter", Font.PLAIN, 13));
		txtPublisher.setBackground(new Color(0xEAE7EE));
		txtPublisher.setBorder(compoundBorder);
		pnlForm.add(txtPublisher, constraints);

		constraints.gridy = 13;
		lblEdition = new JLabel("EDITION:");
		lblEdition.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblEdition, constraints);

		constraints.gridy = 14;
		txtEdition = new JTextField(50);
		txtEdition.setFont(new Font("Inter", Font.PLAIN, 13));
		txtEdition.setBackground(new Color(0xEAE7EE));
		txtEdition.setBorder(compoundBorder);
		pnlForm.add(txtEdition, constraints);

		constraints.gridy = 15;
		lblSalePrice = new JLabel("PRICE:*");
		lblSalePrice.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblSalePrice, constraints);

		constraints.gridy = 16;
		txtSalePrice = new JTextField(50);
		txtSalePrice.setFont(new Font("Inter", Font.PLAIN, 13));
		txtSalePrice.setBackground(new Color(0xEAE7EE));
		txtSalePrice.setBorder(compoundBorder);
		pnlForm.add(txtSalePrice, constraints);

		constraints.gridy = 17;
		lblQuantity = new JLabel("QUANTITY:*");
		lblQuantity.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblQuantity, constraints);

		constraints.gridy = 18;
		txtQuantity = new JTextField(50);
		txtQuantity.setFont(new Font("Inter", Font.PLAIN, 13));
		txtQuantity.setBackground(new Color(0xEAE7EE));
		txtQuantity.setBorder(compoundBorder);
		pnlForm.add(txtQuantity, constraints);

		pnlMain.add(pnlForm);

		// Button panel
		pnlButton = new JPanel(new FlowLayout());
		pnlButton.setBackground(new Color(0xEAE7EE));

		// Creating custom border
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(0x5E00D7), 2);
		Border paddingBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder2 = BorderFactory.createCompoundBorder(lineBorder2, paddingBorder2);

		// Instantiating JButtons
		btnReturn = new JButton("RETURN");
		btnReturn.setPreferredSize(new Dimension(110, 25));
		btnReturn.setFont(new Font("Inter", Font.BOLD, 13));
		btnReturn.setBackground(new Color(0xEDEDF2));
		btnReturn.setForeground(new Color(0x5E00D7));
		btnReturn.setBorder(compoundBorder2);
		btnReturn.addActionListener(this);
		pnlButton.add(btnReturn);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.setBorderPainted(false);
		btnUpdate.setPreferredSize(new Dimension(130, 25));
		btnUpdate.setFont(new Font("Inter", Font.BOLD, 13));
		btnUpdate.setBackground(new Color(0x5E00D7));
		btnUpdate.setForeground(new Color(0xFFFFFF));
		btnUpdate.addActionListener(this);
		pnlButton.add(btnUpdate);

		pnlMain.add(pnlButton);

		// Adding the Main panel to the scroll pane
		JScrollPane scrollPane = new JScrollPane(pnlMain);
		scrollPane.setPreferredSize(new Dimension(685, 610));

		// Getting the vertical scrollbar from JScrollPane
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

		// Appling the custom style
		verticalScrollBar.setUI(new CustomScrollBar());
		scrollPane.setBackground(new Color(0xEAE7EE));
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportBorder(null);
		scrollPane.getViewport().setBorder(null);

		this.getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

	private void setFields() {
		if (book != null) {
			txtId.setText(String.valueOf(book.getId()));
			txtIsbn.setText(String.valueOf(book.getIsbn()));
			txtTitle.setText(book.getTitle());
			txtAuthor.setText(book.getAuthor());
			txtGenre.setText(book.getGenre());
			txtPublisher.setText(book.getPublisher());
			txtEdition.setText(String.valueOf(book.getEdition()));
			txtSalePrice.setText(String.valueOf(book.getSalePrice()));
			txtQuantity.setText(String.valueOf(book.getQuantity()));
		}
	}

	private void updateBook() {
		Integer id = Integer.parseInt(txtId.getText());
		long isbn = Long.parseLong(txtIsbn.getText());
		String title = txtTitle.getText();
		String author = txtAuthor.getText();
		String genre = txtGenre.getText();
		String publisher = txtPublisher.getText();
		Integer edition = Integer.parseInt(txtEdition.getText());
		Double salePrice = Double.parseDouble(txtSalePrice.getText());
		Integer quantity = Integer.parseInt(txtQuantity.getText());

		// Update the book information in the book object
		book.setId(id);
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setGenre(genre);
		book.setPublisher(publisher);
		book.setEdition(edition);
		book.setSalePrice(salePrice);
		book.setQuantity(quantity);

		dao = new BookSaleDAO();
		dao.update(book);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Customazing the OptionPane buttons
		UIManager.put("OptionPane.yesButtonText", "YES");
		UIManager.put("OptionPane.noButtonText", "NO");
		UIManager.put("Button.background", new Color(0x5E00D7));
		UIManager.put("Button.foreground", new Color(0xFFFFFF));
		UIManager.put("Button.font", new Font("Inter", Font.BOLD, 13));
		UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));

		if (e.getSource() == btnUpdate) {
			updateBook();
			JOptionPane.showMessageDialog(this, "Information successfully updated!", "Success",
					JOptionPane.INFORMATION_MESSAGE);

			if (dashboard != null) {
				dashboard.refreshTable();
			}
			dispose();
		} else {
			if (e.getSource() == btnReturn) {
				if (!txtIsbn.getText().isEmpty() || !txtEdition.getText().isEmpty() || !txtTitle.getText().isEmpty()
						|| !txtAuthor.getText().isEmpty() || !txtGenre.getText().isEmpty()
						|| !txtPublisher.getText().isEmpty() || !txtSalePrice.getText().isEmpty()
						|| !txtQuantity.getText().isEmpty()) {
					int response = JOptionPane.showConfirmDialog(this,
							"Are you sure you want to come back? All changed infomration will not be saved.",
							"Confirmation", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						dispose();
					}
				} else {
					dispose();
				}
			}
		}
	}
}