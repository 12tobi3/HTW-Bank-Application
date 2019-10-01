package view;

	import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
	import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
	import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.plaf.SplitPaneUI;

import bankverwaltung.Bank;
import bankverwaltung.Konto;
import bankverwaltung.Kunde;
import controller.HauptfensterMenuListener;

	public class Hauptfenster extends JFrame implements ActionListener {
		
		Bank bank;
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JFrame hauptfenster = new JFrame("Konten: ");
		String text = "";
		JTextArea uebersicht = new JTextArea("Bitte wählen Sie einen Kunden");
		String saldo = " ";
		double gesamtSaldo = 0;
		String kundenName = "";
		JPanel kontobox = new JPanel();
		JLabel iban = new JLabel("IBAN");
		JLabel kontostand = new JLabel("Kontostand: ");
		
		
		
		ArrayList<JLabel> transaktionen = new ArrayList<>();
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		//Hauptfenster
		public Hauptfenster(Bank bank) {
			super();
			this.bank = bank;
			hauptfenster.setTitle("Konten: ");
			hauptfenster.getContentPane().setBackground(Color.WHITE);
			hauptfenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			hauptfenster.setSize(1600,900);
			hauptfenster.setLocationRelativeTo(null);
			hauptfenster.setVisible(true);
			hauptfenster.toFront();
			hauptfenster.setLayout(new GridLayout(2, 1));
			hauptfenster.setJMenuBar(erstelleMenu());
			panel1 = erstelleTranksaktionenUebersicht();
			JScrollPane scrollPane = new JScrollPane(panel1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			hauptfenster.add(scrollPane);
			hauptfenster.add(panel2);

			
		}
		
		//Transaktionen-Übersicht
		public JPanel erstelleTranksaktionenUebersicht() {
			JPanel panel = new JPanel();
			panel.add(uebersicht);
			
			
			return panel;
		}
		// Konten
		public JPanel erstelleKontenUebersicht(Kunde kunde) {
			JPanel panel = new JPanel();
			//panel.add(erstelleKontobox());
			//panel.add(erstelleKontobox());
			//panel.add(erstelleKontobox());
			for (Konto konto : kunde.getKonten()) {
				panel.add(erstelleKontobox(konto));
			}
			panel.setLayout(new GridLayout(3,3));
			return panel;
		}	
		
		//Kontoboxen
		public JPanel erstelleKontobox(Konto konto){
			JButton einzahlung = new JButton("Einzahlung");
			JButton auszahlung = new JButton("Auszahlung");
			JTextField betragIn = new JTextField(10);
			JTextField betragOut = new JTextField(10);
			saldo = " "+konto.getKontostand();
			JLabel kontostandZahl = new JLabel(saldo);
			kontobox = new JPanel();
			iban = new JLabel(konto.getIban());
			kontostand = new JLabel("Kontostand: ");

		
			einzahlung.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					double wert =0; 
					try {
						wert = Double.parseDouble(betragIn.getText()) ;
					} catch (NumberFormatException e1) {
						System.out.println("Ungültiges Format!");
					}
					if(wert > 0) {
						update(wert, konto, "Einzahlung", kontostandZahl);
					} else 
						System.out.println("Bitte geben Sie eine Zahl größer Null ein!");
					
					
				}
			});
			auszahlung.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					double wert =0; 
					try {
						wert = Double.parseDouble(betragOut.getText()) ;
					} catch (NumberFormatException e1) {
						System.out.println("Ungültiges Format!");
					}
					if(wert > 0) {
						update(wert, konto, "Auszahlung", kontostandZahl);
					} else 
						System.out.println("Bitte geben Sie eine Zahl größer Null ein!");
					
				}
			});
			kontobox.setLayout(new GridBagLayout());
			
			gbc.gridy = 0;
			gbc.gridx = 0;
			
			gbc.insets = new Insets(0, 8, 4, 8);
			kontobox.add(iban, gbc);
			
			gbc.gridx = 0; 
			gbc.gridy = 1;

			kontobox.add(kontostand, gbc);
			
			gbc.gridx = 0; 
			gbc.gridy = 2;
			kontobox.add(einzahlung, gbc);
			
			gbc.gridx = 0; 
			gbc.gridy = 3;
			kontobox.add(auszahlung, gbc);
			
			gbc.gridx = 2; 
			gbc.gridy = 1;
			kontobox.add(kontostandZahl, gbc);
			
			
			gbc.gridx = 2; 
			gbc.gridy = 2;
			kontobox.add(betragIn, gbc);
			
			
			gbc.gridx = 2; 
			gbc.gridy = 3;

			kontobox.add(betragOut, gbc);
	
		
			gbc.gridx = 4; 
			gbc.gridy = 1;
			kontobox.add(new JLabel("Euro"), gbc);
			
			gbc.gridx = 4; 
			gbc.gridy = 2;
			kontobox.add(new JLabel("Euro"), gbc);
			
			gbc.gridx = 4; 
			gbc.gridy = 3;
			kontobox.add(new JLabel("Euro"), gbc);
			
			
			
			return kontobox;
		}
		
		public JMenuBar erstelleMenu() {
			JMenuBar menu = new JMenuBar();			
			JMenu kunden = new JMenu("Kunden");
			JMenu kundenWählen = new JMenu("Kunden wählen");
			JMenuItem beenden = new JMenuItem("Beenden");
			beenden.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					hauptfenster.dispose();
					
				}
			});
			
			kunden.add(kundenWählen);
			kunden.add(beenden);
			JMenuItem info = new JMenuItem("Info");
			info.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					hauptfenster.remove(panel2);
					hauptfenster.validate();
					uebersicht.setText("Tobias Schultze\n5444645");	
					
				}
			});
			for (Kunde kunde : bank.getKunden()) {
				
				JMenuItem item = new JMenuItem(kunde.getName());
				item.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						hauptfenster.remove(panel2);
						hauptfenster.validate();
						text = "";
						initialisiere(kunde);	
					}
				});
				kundenWählen.add(item);
			}
			
			menu.add(kunden);
			menu.add(info);
			return menu;
		}
		
		public void initialisiere(Kunde kunde) {
			gesamtSaldo = kunde.berechneGesamtSaldo();
			kundenName = kunde.getName();
			uebersicht.setText("Kunde: " + kundenName + "\nGesamt Saldo: " + gesamtSaldo);
			panel2 = erstelleKontenUebersicht(kunde);
			hauptfenster.add(panel2);
			hauptfenster.validate();
		}
		
		public void update(Double betrag, Konto konto, String art, JLabel kontostand) {
			boolean valid = false; 
			if(art.equalsIgnoreCase("Einzahlung")) {
				konto.setKontostand(konto.getKontostand()+betrag);
				gesamtSaldo += betrag;
				valid = true;
			} else if (art.equalsIgnoreCase("Auszahlung") && betrag > konto.getKontostand()) {
				System.out.println("Sie haben nicht genug Geld");
			} else {
				konto.setKontostand(konto.getKontostand()-betrag);
				gesamtSaldo -= betrag;
				valid = true;
			}
			if (valid) {
				saldo = " "+konto.getKontostand();
				kontostand.setText(saldo);
				if (art.equals("Einzahlung")) {
					text = text + "\n " + art + " " +betrag + "  auf das Konto:  " + konto.getIban();
				} else 
					text = text + "\n " + art + " " +betrag + "  vom Konto:  " + konto.getIban();

				uebersicht.setText("Kunde: " + kundenName + "\nGesamt Saldo: " + gesamtSaldo + "\n" + text);
			}
			
			
			
		}
		
		
}


