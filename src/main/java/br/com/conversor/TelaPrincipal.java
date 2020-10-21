package br.com.conversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.json.JSONException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaPrincipal {

	private JFrame frmPobrezaCalculator;
	private JTextField textField;
	private ConverteMain converteMain;
	private JComboBox comboBox;
	private JLabel valorConvertidoLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPobrezaCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public TelaPrincipal() throws JSONException, IOException {
		converteMain = new ConverteMain();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPobrezaCalculator = new JFrame();
		frmPobrezaCalculator.setTitle("POBREZA CALCULATOR");
		frmPobrezaCalculator.setBounds(100, 100, 450, 300);
		frmPobrezaCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPobrezaCalculator.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frmPobrezaCalculator.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Converte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (!textField.getText().isBlank() || !textField.getText().isEmpty()) {
						
						valorConvertidoLabel.setText(
								converteMain
								.getValor(comboBox.getSelectedItem()
										.toString(),Double.valueOf(textField.getText())));
						
					}
				}catch(NoSuchMethodError error) {
					
					if (!textField.getText().equals("") || !textField.getText().isEmpty()) {
						
						valorConvertidoLabel.setText(
								converteMain
								.getValor(comboBox.getSelectedItem()
										.toString(),Double.valueOf(textField.getText())));
					}
					
				}
				
				
			}
		});
		btnNewButton.setBounds(154, 227, 119, 23);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(10, 37, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Valor em Reais");
		lblNewLabel.setBounds(10, 11, 153, 14);
		panel.add(lblNewLabel);
		
		valorConvertidoLabel = new JLabel("0");
		valorConvertidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		valorConvertidoLabel.setBounds(10, 87, 414, 114);
		panel.add(valorConvertidoLabel);
		
		JLabel lblValorEmDollar = new JLabel("Valor convertido");
		lblValorEmDollar.setBounds(10, 68, 153, 14);
		panel.add(lblValorEmDollar);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(converteMain.populaLista()));
		comboBox.setBounds(310, 36, 89, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Escolha a conversao");
		lblNewLabel_2.setBounds(249, 11, 175, 14);
		panel.add(lblNewLabel_2);
	}
}
