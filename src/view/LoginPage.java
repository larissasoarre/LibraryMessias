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

import org.mindrot.jbcrypt.BCrypt;

import dao.UserLoginDAO;
import model.UserLogin;

public class LoginPage extends JFrame implements ActionListener {

	// JLabels to indicate what each field contains
	private JLabel lblExtLogo;
	private JLabel lblUsername;
	private JLabel lblPassword;

	// JTextFields to allow typing information
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	// JButton to allow interaction with the user
	private JButton btnLogin;
	private JButton btnSignUp;

	// JPanels to organise the visual elements
	private JPanel pnlExtLogo;
	private JPanel pnlForm;
	private JPanel pnlButton;
	private JPanel pnlMain;

	// Images and logos that will be used in the software
	private ImageIcon logo;
	private ImageIcon extendedLogo;

	// Importing method from the Dashboard class to update the dashboard
	private UserLogin user;
	private UserLoginDAO dao;

	public LoginPage() {

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
		GridBagConstraints constraints = new GridBagConstraints();
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
		btnSignUp = new JButton("SIGN UP");
		btnSignUp.setPreferredSize(new Dimension(110, 25));
		btnSignUp.setFont(new Font("Inter", Font.BOLD, 13));
		btnSignUp.setBackground(new Color(0xEDEDF2));
		btnSignUp.setForeground(new Color(0x5E00D7));
		Border lineBorder3 = BorderFactory.createLineBorder(new Color(0x5E00D7), 2);
		Border paddingBorder3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder3 = BorderFactory.createCompoundBorder(lineBorder3, paddingBorder3);
		btnSignUp.setBorder(compoundBorder3);
		btnSignUp.addActionListener(this);
		pnlButton.add(btnSignUp);

		btnLogin = new JButton("LOGIN");
		btnLogin.setBorderPainted(false);
		btnLogin.setPreferredSize(new Dimension(110, 25));
		btnLogin.setFont(new Font("Inter", Font.BOLD, 13));
		btnLogin.setBackground(new Color(0x5E00D7));
		btnLogin.setForeground(new Color(0xFFFFFF));
		btnLogin.addActionListener(this);
		pnlButton.add(btnLogin);

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

		if (e.getSource() == btnLogin) {
			String username = txtUsername.getText();
			char[] passwordChars = txtPassword.getPassword();
			String password = new String(passwordChars);

			user = dao.getUserbyUsername(username);

			// Checking the login data
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Fill in all the fields.", "Login Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (user != null && BCrypt.checkpw(password, user.getPassword())) {
					dispose();

					Dashboard dashboard = new Dashboard();
					dashboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
					dashboard.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			if (e.getSource() == btnSignUp) {
				dispose();

				SignUpPage signUp = new SignUpPage();
				signUp.setDefaultCloseOperation(EXIT_ON_CLOSE);
				signUp.setVisible(true);
			}
		}
	}

	public static void main(String[] args) {
		LoginPage frame = new LoginPage();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}