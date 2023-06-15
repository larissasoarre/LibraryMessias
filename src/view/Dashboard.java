package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import dao.BookSaleDAO;
import model.BookSale;
import model.CustomScrollBar;

public class Dashboard extends JFrame implements ActionListener {

	// JLabels to indicate what each field contains
	private JLabel lblExtLogo;
	private JLabel lblMenuLBooks;
	private JLabel lblMainTitle;

	// JButton to allow interaction with the user
	private JButton btnAddBook;
	private JButton btnSignOut;

	// JPanels to organise the visual elements
	private JPanel pnlControl;
	private JPanel pnlMain;
	private JPanel pnlTop;
	private JPanel pnlBookInfo;

	// JTable for displaying data
	private JTable table;

	// Images and logos that will be used in the software
	private ImageIcon logo;
	private ImageIcon extendedLogo;

	// Importing method from the Dashboard class to update the dashboard
	private UpdateBook updateBook;
	private Dashboard dashboard;

	// Attributes for storing user information and manipulating the database
	private BookSale book;
	private BookSaleDAO dao;

	public Dashboard() {

		dashboard = this;
		updateBook = new UpdateBook(dashboard);

		// Window settings
		this.setTitle("Library Messias");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(750, 500));
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(new Color(0xEAE7EE));
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				if (newSize.width < 750 || newSize.height < 500) {
					setSize(Math.max(newSize.width, 750), Math.max(newSize.height, 500));
				}
			}
		});

		// Software's window logo
		logo = new ImageIcon(LoginPage.class.getResource("/logo.png"));
		this.setIconImage(logo.getImage());

		// Side control panel
		pnlControl = new JPanel();
		pnlControl.setLayout(new BoxLayout(pnlControl, BoxLayout.Y_AXIS));
		pnlControl.setBackground(new Color(0xDCD9DF));
		pnlControl.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
		pnlControl.setPreferredSize(new Dimension(250, 200));

		// Instantiating the Logo Label
		lblExtLogo = new JLabel();
		extendedLogo = new ImageIcon(Dashboard.class.getResource("/extended-logo.png"));
		lblExtLogo.setIcon(extendedLogo);
		lblExtLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblExtLogo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		pnlControl.add(lblExtLogo);

		// Menu Panel
		JPanel lblMenuPanel = new JPanel();
		lblMenuPanel.setLayout(new BoxLayout(lblMenuPanel, BoxLayout.X_AXIS));
		lblMenuPanel.setBackground(new Color(0x5E00D7));
		lblMenuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMenuPanel.setBorder(BorderFactory.createEmptyBorder(9, 75, 9, 75));
		lblMenuPanel.setPreferredSize(new Dimension(250, 30));

		// Instantiating the Book Label
		lblMenuLBooks = new JLabel("Books");
		lblMenuLBooks.setFont(new Font("Inter", Font.BOLD, 16));
		lblMenuLBooks.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblMenuLBooks.setForeground(new Color(0xFFFFFF));
		lblMenuPanel.add(lblMenuLBooks);

		// Adding pnlMenuPanel to pnControl
		pnlControl.add(lblMenuPanel);

		// Creating custom border
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(0x5E00D7), 2);
		Border paddingBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder2 = BorderFactory.createCompoundBorder(lineBorder2, paddingBorder2);

		// Instantiating the SignOut button
		btnSignOut = new JButton("LOG OUT");
		btnSignOut.setPreferredSize(new Dimension(110, 25));
		btnSignOut.setFont(new Font("Inter", Font.BOLD, 13));
		btnSignOut.setBackground(new Color(0xEDEDF2));
		btnSignOut.setForeground(new Color(0x5E00D7));
		btnSignOut.setBorder(compoundBorder2);
		btnSignOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSignOut.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnSignOut.addActionListener(this);
		pnlControl.add(btnSignOut);

		pnlControl.setLayout(new BoxLayout(pnlControl, BoxLayout.Y_AXIS));
		pnlControl.add(Box.createVerticalGlue());
		pnlControl.add(btnSignOut);

		this.add(pnlControl, BorderLayout.WEST);

		// Main panel
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBackground(new Color(0xEAE7EE));
		pnlMain.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

		// Title panel and add book button
		pnlTop = new JPanel(new BorderLayout());
		pnlTop.setBackground(new Color(0xEAE7EE));

		lblMainTitle = new JLabel("Books");
		lblMainTitle.setFont(new Font("Inter", Font.BOLD, 40));
		pnlTop.add(lblMainTitle, BorderLayout.WEST);

		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());

		btnAddBook = new JButton("ADD BOOK");
		btnAddBook.setBorderPainted(false);
		btnAddBook.setFont(new Font("Inter", Font.BOLD, 13));
		btnAddBook.setBackground(new Color(0x5E00D7));
		btnAddBook.setForeground(new Color(0xFFFFFF));
		btnAddBook.addActionListener(this);
		box.add(btnAddBook);
		pnlTop.add(box, BorderLayout.EAST);

		pnlMain.add(pnlTop, BorderLayout.NORTH);

		// Panel with a list of books
		pnlBookInfo = new JPanel(new GridLayout(1, 1));
		pnlBookInfo.setBackground(new Color(0xEAE7EE));
		pnlBookInfo.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

		// Creating the JTable model
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("ISBN");
		model.addColumn("Title");
		model.addColumn("Author");
		model.addColumn("Genre");
		model.addColumn("Publisher");
		model.addColumn("Edition");
		model.addColumn("Price");
		model.addColumn("Quantity");

		// Populating the model with ArrayList data
		dao = new BookSaleDAO();

		List<BookSale> books = dao.selectAll();
		for (BookSale book : books) {
			Object[] rowData = new Object[9];
			rowData[0] = book.getId();
			rowData[1] = book.getIsbn();
			rowData[2] = book.getTitle();
			rowData[3] = book.getAuthor();
			rowData[4] = book.getGenre();
			rowData[5] = book.getPublisher();
			rowData[6] = book.getEdition();
			rowData[7] = book.getSalePrice();
			rowData[8] = book.getQuantity();

			model.addRow(rowData);
		}

		// Creating a new JTable with the model
		table = new JTable(model);

		table.setBackground(new Color(0xEAE7EE));
		table.setFont(new Font("Inter", Font.PLAIN, 16));
		table.setBorder(null);
		table.setRowHeight(50);
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(new Color(0, 0, 0, 0));
		table.setDefaultEditor(Object.class, null);

		// Customizing each cell of the table
		TableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);

				((JComponent) component).setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0x8B8B8B)));
				setHorizontalAlignment(SwingConstants.CENTER);
				setVerticalAlignment(SwingConstants.CENTER);

				// Check if the table row is selected to add customizations
				if (isSelected) {
					component.setBackground(new Color(0xd8d1e1));
				} else {
					component.setBackground(table.getBackground());
				}

				return component;
			}
		};

		table.setDefaultRenderer(Object.class, cellRenderer);

		// Adjusting the width of each column
		table.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
		table.getColumnModel().getColumn(1).setPreferredWidth(180); // ISBN
		table.getColumnModel().getColumn(2).setPreferredWidth(350); // Title
		table.getColumnModel().getColumn(3).setPreferredWidth(150); // Author
		table.getColumnModel().getColumn(4).setPreferredWidth(80); // Genre
		table.getColumnModel().getColumn(5).setPreferredWidth(120); // Publisher
		table.getColumnModel().getColumn(6).setPreferredWidth(80); // Edition
		table.getColumnModel().getColumn(7).setPreferredWidth(80); // Price
		table.getColumnModel().getColumn(8).setPreferredWidth(120); // Quantity

		// Creating a mouse listener for the table rows
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Double-click event
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						// Retrieve the book information from the selected row
						int bookId = (int) table.getValueAt(selectedRow, 0);
						book = dao.selectById(bookId);

						// Open a new window to display the book information
						BookDetails bookDetailsWindow = new BookDetails(book, dashboard);
						bookDetailsWindow.setVisible(true);
					}
				}
			}
		});

		// Customizing the title of each column of the table
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				label.setFont(new Font("Inter", Font.BOLD, 13));
				label.setForeground(new Color(0x8B8B8B));
				label.setBackground(new Color(0xEAE7EE));
				label.setOpaque(true);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
				return label;
			}
		});

		// Adding the table to the scroll pane
		JScrollPane scrollPane = new JScrollPane(table);

		// Getting the vertical scrollbar from JScrollPane
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

		// Applying the custom style
		verticalScrollBar.setUI(new CustomScrollBar());
		scrollPane.setBackground(new Color(0xEAE7EE));
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportBorder(null);
		scrollPane.getViewport().setBorder(null);

		// Adding the table panel
		pnlBookInfo = new JPanel(new BorderLayout());
		pnlBookInfo.setBackground(new Color(0xEAE7EE));
		pnlBookInfo.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		pnlBookInfo.add(scrollPane, BorderLayout.CENTER);

		pnlMain.add(pnlBookInfo, BorderLayout.CENTER);

		this.add(pnlMain, BorderLayout.CENTER);
	}

	// Method responsible for refreshing the book table
	public void refreshTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Clears all the lines of the template

		List<BookSale> books = dao.selectAll();
		for (BookSale book : books) {
			Object[] rowData = new Object[9];
			rowData[0] = book.getId();
			rowData[1] = book.getIsbn();
			rowData[2] = book.getTitle();
			rowData[3] = book.getAuthor();
			rowData[4] = book.getGenre();
			rowData[5] = book.getPublisher();
			rowData[6] = book.getEdition();
			rowData[7] = book.getSalePrice();
			rowData[8] = book.getQuantity();

			model.addRow(rowData); // Adding the new rows to the model
		}
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

		if (e.getSource() == btnAddBook) {
			AddBookForm addBookForm = new AddBookForm(this);
			addBookForm.setVisible(true);
			refreshTable(); // Updates the table after adding the book
		} else {
			if (e.getSource() == btnSignOut) {
				int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave your account?",
						"Sair", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					dispose();
					LoginPage login = new LoginPage();
					login.setDefaultCloseOperation(EXIT_ON_CLOSE);
					login.setVisible(true);
				}
			}
		}
	}

	public static void main(String[] args) {
		Dashboard dashboard = new Dashboard();
		dashboard.setVisible(true);
		dashboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
