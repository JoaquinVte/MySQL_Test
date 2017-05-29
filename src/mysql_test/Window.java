package mysql_test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldNombre;
	private JTextField txtFieldApellidos;
	private MySQL db;
	private JTextField txtFieldDNI;

	/**
	 * Create the frame.
	 */
	public Window(MySQL db) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				db.closeConnection();
			}
		});
		this.db=db;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		
		txtFieldNombre = new JTextField();
		txtFieldNombre.setColumns(10);
		
		txtFieldApellidos = new JTextField();
		txtFieldApellidos.setColumns(10);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblDni = new JLabel("DNI:");
		
		txtFieldDNI = new JTextField();
		txtFieldDNI.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblApellidos)
								.addComponent(lblDni))
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFieldApellidos, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
								.addComponent(txtFieldNombre, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
								.addComponent(txtFieldDNI, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(txtFieldDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellidos)
						.addComponent(txtFieldApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnInsertarEnBbdd = new JButton("Insertar en BBDD");
		btnInsertarEnBbdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db.insertData("usuario", txtFieldNombre.getText(), txtFieldApellidos.getText(),txtFieldDNI.getText());
				txtFieldNombre.setText("");
				txtFieldApellidos.setText("");
				txtFieldDNI.setText("");
			}
		});
		panel.add(btnInsertarEnBbdd);
		
		JButton btnVisualizarRegistros = new JButton("Visualizar Registros");
		btnVisualizarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText(db.getValues("usuario"));
			}
		});
		
		JButton btnBorrarUsuario = new JButton("Borrar Usuario");
		btnBorrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteRecord("usuario", txtFieldDNI.getText());
			}
		});
		panel.add(btnBorrarUsuario);
		panel.add(btnVisualizarRegistros);
		contentPane.setLayout(gl_contentPane);
	}
}
