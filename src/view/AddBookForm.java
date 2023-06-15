package view;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import dao.BookSaleDAO;
import model.BookSale;

public class AddBookForm extends JFrame implements ActionListener {

	// JLabels to indicate what each field contains
	private JLabel lblIsbn;
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblGenre;
	private JLabel lblPublisher;
	private JLabel lblEdition;
	private JLabel lblSalePrice;
	private JLabel lblQuantity;

	// JTextFields to allow typing information
	private JTextField txtIsbn;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtPublisher;
	private JTextField txtEdition;
	private JTextField txtSalePrice;
	private JTextField txtQuantity;

	// JButton to allow interaction with the user
	private JButton bntAdd;
	private JButton btnReturn;

	// JPanels to organise the visual elements
	private JPanel pnlMain;
	private JPanel pnlForm;
	private JPanel pnlButton;

	// Images and logos that will be used in the software
	private ImageIcon logo;

	// Attributes for storing user information and manipulating the database
	private BookSale book;
	private BookSaleDAO dao;
	
	// Importing method from the Dashboard class to update the dashboard
	private Dashboard dashboard;

	public AddBookForm(Dashboard dashboard) {
		this.dashboard = dashboard;
		
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

		// Main Panel that will hold the other panels
		pnlMain = new JPanel(new GridBagLayout());
		pnlMain.setBackground(new Color(0xEAE7EE));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.CENTER;

		// Form Panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		pnlForm = new JPanel(new GridBagLayout());
		pnlForm.setBackground(new Color(0xEAE7EE));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.LINE_START;

		// Creating custom border
		Border lineBorder = BorderFactory.createLineBorder(new Color(0x111315), 1);
		Border paddingBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);

		// Setting the position and customizing JTexts and JLabels inside the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		lblIsbn = new JLabel("ISBN CODE:*");
		lblIsbn.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblIsbn, constraints);

		constraints.gridy = 1;
		txtIsbn = new JTextField(50);
		txtIsbn.setFont(new Font("Inter", Font.PLAIN, 13));
		txtIsbn.setBackground(new Color(0xEAE7EE));
		txtIsbn.setBorder(compoundBorder);
		pnlForm.add(txtIsbn, constraints);

		constraints.gridy = 2;
		lblTitle = new JLabel("BOOK TITLE:*");
		lblTitle.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblTitle, constraints);

		constraints.gridy = 3;
		txtTitle = new JTextField(50);
		txtTitle.setFont(new Font("Inter", Font.PLAIN, 13));
		txtTitle.setBackground(new Color(0xEAE7EE));
		txtTitle.setBorder(compoundBorder);
		pnlForm.add(txtTitle, constraints);

		constraints.gridy = 4;
		lblAuthor = new JLabel("AUTHOR:*");
		lblAuthor.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblAuthor, constraints);

		constraints.gridy = 5;
		txtAuthor = new JTextField(50);
		txtAuthor.setFont(new Font("Inter", Font.PLAIN, 13));
		txtAuthor.setBackground(new Color(0xEAE7EE));
		txtAuthor.setBorder(compoundBorder);
		pnlForm.add(txtAuthor, constraints);

		constraints.gridy = 6;
		lblGenre = new JLabel("GENRE:*");
		lblGenre.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblGenre, constraints);

		constraints.gridy = 7;
		txtGenre = new JTextField(50);
		txtGenre.setFont(new Font("Inter", Font.PLAIN, 13));
		txtGenre.setBackground(new Color(0xEAE7EE));
		txtGenre.setBorder(compoundBorder);
		pnlForm.add(txtGenre, constraints);

		constraints.gridy = 8;
		lblPublisher = new JLabel("PUBLISHER:*");
		lblPublisher.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblPublisher, constraints);

		constraints.gridy = 9;
		txtPublisher = new JTextField(50);
		txtPublisher.setFont(new Font("Inter", Font.PLAIN, 13));
		txtPublisher.setBackground(new Color(0xEAE7EE));
		txtPublisher.setBorder(compoundBorder);
		pnlForm.add(txtPublisher, constraints);

		constraints.gridy = 10;
		lblEdition = new JLabel("EDITION:");
		lblEdition.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblEdition, constraints);

		constraints.gridy = 11;
		txtEdition = new JTextField(50);
		txtEdition.setFont(new Font("Inter", Font.PLAIN, 13));
		txtEdition.setBackground(new Color(0xEAE7EE));
		txtEdition.setBorder(compoundBorder);
		pnlForm.add(txtEdition, constraints);

		constraints.gridy = 12;
		lblSalePrice = new JLabel("PRICE:*");
		lblSalePrice.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblSalePrice, constraints);

		constraints.gridy = 13;
		txtSalePrice = new JTextField(50);
		txtSalePrice.setFont(new Font("Inter", Font.PLAIN, 13));
		txtSalePrice.setBackground(new Color(0xEAE7EE));
		txtSalePrice.setBorder(compoundBorder);
		pnlForm.add(txtSalePrice, constraints);

		constraints.gridy = 14;
		lblQuantity = new JLabel("QUANTITY:*");
		lblQuantity.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblQuantity, constraints);

		constraints.gridy = 15;
		txtQuantity = new JTextField(50);
		txtQuantity.setFont(new Font("Inter", Font.PLAIN, 13));
		txtQuantity.setBackground(new Color(0xEAE7EE));
		txtQuantity.setBorder(compoundBorder);
		pnlForm.add(txtQuantity, constraints);

		// Creating custom border
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(0x5E00D7), 2);
		Border paddingBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder2 = BorderFactory.createCompoundBorder(lineBorder2, paddingBorder2);

		// Adding pnlForm to pnlMain
		pnlMain.add(pnlForm, gbc);

		// Button Panel
		gbc.gridy = 2;
		pnlButton = new JPanel(new FlowLayout());
		pnlButton.setBackground(new Color(0xEAE7EE));

		// Instantiating and customizing JButton
		btnReturn = new JButton("RETURN");
		btnReturn.setPreferredSize(new Dimension(110, 25));
		btnReturn.setFont(new Font("Inter", Font.BOLD, 13));
		btnReturn.setBackground(new Color(0xEDEDF2));
		btnReturn.setForeground(new Color(0x5E00D7));
		btnReturn.setBorder(compoundBorder2);
		btnReturn.addActionListener(this);
		pnlButton.add(btnReturn);

		// Customizing the JButton inside the panel
		bntAdd = new JButton("ADD");
		bntAdd.setBorderPainted(false);
		bntAdd.setPreferredSize(new Dimension(130, 25));
		bntAdd.setFont(new Font("Inter", Font.BOLD, 13));
		bntAdd.setBackground(new Color(0x5E00D7));
		bntAdd.setForeground(new Color(0xFFFFFF));
		bntAdd.addActionListener(this);
		pnlButton.add(bntAdd);

		// Adding the pnlButton to pnlMain
		pnlMain.add(pnlButton, gbc);
		
		this.add(pnlMain, BorderLayout.CENTER);

		// Instantiation of database manipulation attributes
		book = new BookSale();
		dao = new BookSaleDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Customazing the OptionPane buttons
		UIManager.put("OptionPane.yesButtonText", "YES");
		UIManager.put("OptionPane.noButtonText", "NO");
		UIManager.put("Button.background", new Color(0x5E00D7));
        UIManager.put("Button.foreground", new Color(0xFFFFFF));
        UIManager.put("Button.font", new Font("Inter", Font.BOLD, 13));
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		if (e.getSource() == bntAdd) {

			// Validating if the ISBN contains 13 digit numbers
			if (txtIsbn.getText().length() != 13 || !txtIsbn.getText().matches("\\d+")) {
				JOptionPane.showMessageDialog(this, "The ISBN must contain 13 numerical digits.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validating if the edition is a positive number
			Integer edition = null;
			if (!txtEdition.getText().isEmpty()) {
				try {
					edition = Integer.parseInt(txtEdition.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "The edit must be an integer numeric value.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// Validating if the sales price field contains monetary value
			Double salePrice = null;
			try {
				salePrice = Double.parseDouble(txtSalePrice.getText());
				if (salePrice < 0) {
					throw new NumberFormatException(); // Price cannot be negative
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "The sales price must be a valid numeric value. Tip: type 23.50 instead of 23,50", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validating if the quantity field contains only numbers
			Integer quantity = null;
			try {
				quantity = Integer.parseInt(txtQuantity.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "The quantity must be a valid integer value.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Add the information that was entered
			book.setIsbn(Long.parseLong(txtIsbn.getText()));
			book.setTitle(txtTitle.getText());
			book.setAuthor(txtAuthor.getText());
			book.setGenre(txtGenre.getText());
			book.setPublisher(txtPublisher.getText());
			if (!txtEdition.getText().isEmpty()) {
				book.setEdition(Integer.parseInt(txtEdition.getText()));
			}
			book.setSalePrice(Double.parseDouble(txtSalePrice.getText()));
			book.setQuantity(Integer.parseInt(txtQuantity.getText()));

			// Validating if all mandatory fields have been filled in
			if (txtIsbn.getText().isEmpty() || txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty()
					|| txtGenre.getText().isEmpty() || txtPublisher.getText().isEmpty()
					|| txtSalePrice.getText().isEmpty() || txtQuantity.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please complete all mandatory fields.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			dao.addBook(book);
		
			JOptionPane.showMessageDialog(this, "Book successfully added to the inventory!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			
			dashboard.refreshTable();
			
			dispose();
		} else {
			if (e.getSource() == btnReturn) {
				if (!txtIsbn.getText().isEmpty() || !txtEdition.getText().isEmpty() || !txtTitle.getText().isEmpty()
						|| !txtAuthor.getText().isEmpty() || !txtGenre.getText().isEmpty()
						|| !txtPublisher.getText().isEmpty() || !txtSalePrice.getText().isEmpty()
						|| !txtQuantity.getText().isEmpty()) {
					int response = JOptionPane.showConfirmDialog(this,
							"Are you sure you want to return? All filled in information will be lost.", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						dispose();
					}
				} else {
					dispose();
				}
			}
		}
	}

	public static void main(String[] args) {
		AddBookForm frame = new AddBookForm(null);
		frame.setVisible(true);
	}
}