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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import dao.UserLoginDAO;
import model.UserLogin;

public class SignUpPage extends JFrame implements ActionListener {

	// JLabels to indicate what each field contains
	private JLabel lblExtLogo;
	private JLabel lblUsername;
	private JLabel lblPassword;

	// JTextFields to allow typing information
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/// JButton to allow interaction with the user
	private JButton bntSignUp;
	private JButton btnReturn;

	// JPanels to organise the visual elements
	private JPanel pnlExtLogo;
	private JPanel pnlForm;
	private JPanel pnlButton;
	private JPanel pnlMain;

	// Images and logos that will be used in the software
	private ImageIcon logo;
	private ImageIcon extendedLogo;

	// Attributes for storing user information and manipulating the database
	private UserLogin user;
	private UserLoginDAO dao;
	private GridBagConstraints constraints;

	public SignUpPage() {
		// Window settings
		this.setTitle("Library Messias");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(500, 500));
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(new Color(0xEAE7EE));
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize();
				if (newSize.width < 500 || newSize.height < 500) {
					setSize(Math.max(newSize.width, 500), Math.max(newSize.height, 500));
				}
			}
		});

		// Software's window logo
		logo = new ImageIcon(LoginPage.class.getResource("/logo.png"));
		this.setIconImage(logo.getImage());

		// Main Panel that will hold the other panels
		pnlMain = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.CENTER;

		// Logo Panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlExtLogo = new JPanel(new FlowLayout());

		// Instantiating JLabels and Image
		lblExtLogo = new JLabel();
		extendedLogo = new ImageIcon(LoginPage.class.getResource("/extended-logo.png"));
		lblExtLogo.setIcon(extendedLogo);
		pnlExtLogo.add(lblExtLogo);

		// Adding pnlExtLogo to pnlMain
		pnlMain.add(pnlExtLogo, gbc);

		// Form panel
		gbc.gridy = 1;
		pnlForm = new JPanel(new GridBagLayout());

		// Instantiating JLabels and JTextFields
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.LINE_START;

		// Username
		constraints.gridx = 0;
		constraints.gridy = 0;
		lblUsername = new JLabel("USERNAME:");
		lblUsername.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblUsername, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		txtUsername = new JTextField(30);
		txtUsername.setFont(new Font("Inter", Font.PLAIN, 13));
		txtUsername.setBackground(new Color(0xEDEDF2));
		Border lineBorder1 = BorderFactory.createLineBorder(new Color(0x111315), 1);
		Border paddingBorder1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder1 = BorderFactory.createCompoundBorder(lineBorder1, paddingBorder1);
		txtUsername.setBorder(compoundBorder1);
		pnlForm.add(txtUsername, constraints);

		// User password
		constraints.gridx = 0;
		constraints.gridy = 2;
		lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Inter", Font.PLAIN, 13));
		pnlForm.add(lblPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		txtPassword = new JPasswordField(30);
		txtPassword.setFont(new Font("Inter", Font.PLAIN, 13));
		txtPassword.setBackground(new Color(0xEDEDF2));
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(0x111315), 1);
		Border paddingBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder2 = BorderFactory.createCompoundBorder(lineBorder2, paddingBorder2);
		txtPassword.setBorder(compoundBorder2);
		pnlForm.add(txtPassword, constraints);

		// Adding pnlForm to pnlMain
		pnlMain.add(pnlForm, gbc);

		// Button panel
		gbc.gridy = 2;
		pnlButton = new JPanel(new FlowLayout());

		// Instantiating JButton
		btnReturn = new JButton("RETURN");
		btnReturn.setPreferredSize(new Dimension(110, 25));
		btnReturn.setFont(new Font("Inter", Font.BOLD, 13));
		btnReturn.setBackground(new Color(0xEDEDF2));
		btnReturn.setForeground(new Color(0x5E00D7));
		Border lineBorder3 = BorderFactory.createLineBorder(new Color(0x5E00D7), 2);
		Border paddingBorder3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder3 = BorderFactory.createCompoundBorder(lineBorder3, paddingBorder3);
		btnReturn.setBorder(compoundBorder3);
		btnReturn.addActionListener(this);
		pnlButton.add(btnReturn);

		bntSignUp = new JButton("SIGN UP");
		bntSignUp.setBorderPainted(false);
		bntSignUp.setPreferredSize(new Dimension(130, 25));
		bntSignUp.setFont(new Font("Inter", Font.BOLD, 13));
		bntSignUp.setBackground(new Color(0x5E00D7));
		bntSignUp.setForeground(new Color(0xFFFFFF));
		bntSignUp.addActionListener(this);
		pnlButton.add(bntSignUp);

		// Adding the pnlButton to pnlMain
		pnlMain.add(pnlButton, gbc);

		// Centering the pnlMain in the frame
		this.add(pnlMain, BorderLayout.CENTER);

		// Attributes for storing user information and manipulating the database
		user = new UserLogin();
		dao = new UserLoginDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UIManager.put("Button.background", new Color(0x5E00D7));
		UIManager.put("Button.foreground", new Color(0xFFFFFF));
		UIManager.put("Button.font", new Font("Inter", Font.BOLD, 13));
		UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));

		if (e.getSource() == bntSignUp) {
			try {
				user.setUsername(txtUsername.getText());
				char[] passwordChars = txtPassword.getPassword();
				String password = new String(passwordChars);
				user.setPassword(password);

				// Checks if the username already exists
				if (dao.usernameExists(user.getUsername())) {
					JOptionPane.showMessageDialog(this, "This username already exists", "ErroR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Checks if all fields have been filled in
				if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Create the user
				dao.createUser(user);

				JOptionPane.showMessageDialog(this, "Registration completed successfully.", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				dispose();

				// Redirects to the login page
				LoginPage loginPage = new LoginPage();
				loginPage.setDefaultCloseOperation(EXIT_ON_CLOSE);
				loginPage.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			if (e.getSource() == btnReturn) {
				dispose();

				// Redirects to the login page
				LoginPage loginPage = new LoginPage();
				loginPage.setDefaultCloseOperation(EXIT_ON_CLOSE);
				loginPage.setVisible(true);
			}
		}
	}

	public static void main(String[] args) {
		SignUpPage frame = new SignUpPage();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}