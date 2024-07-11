/*
* Copyright (c) 2022-2024 DeadSOUL-Studios (https://github.com/deadsoul-studios)
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public
* License as published by the Free Software Foundation; either
* version 2 of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* General Public License for more details.
*
* You should have received a copy of the GNU General Public
* License along with this program; if not, write to the
* Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
* Boston, MA 02110-1301 USA
*
* Authored by: Ayush "DeadSOUL" <ayushkumar274549@gmail.com>
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.*;
import java.util.Enumeration;


class RoundedBorder implements Border {

    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}

public class Currency extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	JLabel[] label = new JLabel[5];
	JButton[] button = new JButton[5];
	JTextField[] field = new JTextField[3];
	
	String filePath = "C:/.ccbysoul-999-ayu";
	String api = "";
	
	JComboBox<String> comboBox;
	JComboBox<String> comboBox1;
	
	
	Currency(){
		
		String labelText[] = {"Currency Convertor", "Please enter your API Key:", "1st currency:", "2nd currency:", "Enter amount:"};
		String fieldText[] = {"API_KEY", "amount", "Result will be shown here"};
		String buttonText[] = {"Continue", "Calculate"};
		
		for(int i=0; i<5; i++) {
			label[i] = new JLabel(labelText[i]);
			label[i].setFont(new Font("Ink free", Font.PLAIN, 15));
			label[i].setForeground(Color.black);
			
			if(i<3) {
				field[i] = new JTextField(fieldText[i]);
				field[i].setFont(new Font("Ink free", Font.PLAIN, 15));
				field[i].setBorder(new RoundedBorder(5));
			}
			
			if(i<2) {
				button[i] = new JButton(buttonText[i]);
				button[i].addActionListener(this);
				button[i].setFont(new Font("Ink free", Font.PLAIN, 15));
				button[i].setBorder(new RoundedBorder(10));
				button[i].setForeground(Color.black);
				button[i].setBackground(Color.white);
				button[i].setFocusable(false);
			}
		}
		
		label[0].setForeground(Color.red);
		label[0].setBounds(60, -30, 200, 100);
		label[0].setFont(new Font("Ink free", Font.BOLD, 20));
		
		label[1].setBounds(10, 0, 280, 100);
		label[2].setBounds(10, 0, 280, 100);
		label[3].setBounds(10, 100, 100, 25);
		label[4].setBounds(10, 165, 100, 25);
		
		field[0].setBounds(10, 65, 265, 25);
		field[1].setBounds(10, 190, 265, 25);
		field[2].setBounds(10, 225, 265, 25);
		field[2].setEditable(false);
		field[2].setForeground(Color.green);
	
		button[0].setBounds(100, 100, 100, 25);
		button[1].setBounds(10, 265, 265, 25);
		
		URL icon = getClass().getResource("icon.png");
		
		this.setTitle("Currency Convertor");
		this.setIconImage(new ImageIcon(icon).getImage());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(new Dimension(300, 180));
		this.setLocationRelativeTo(null);
		
        File file = new File(filePath);

        if (file.exists()) {
            fileExists();
        } else {
            this.add(label[0]);
    		this.add(label[1]);
    		this.add(field[0]);
    		this.add(button[0]);
        }
		
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	JLabel label = new JLabel("Are You Sure to Close Application?");
		    	label.setFont(new Font("Ink free", Font.BOLD, 15));
	    	    try {
			        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			    } catch (Exception e1) {
			        e1.printStackTrace();
			    }
			    setUIFont(new javax.swing.plaf.FontUIResource("Ink free", Font.PLAIN, 15));

		        int confirm = JOptionPane.showOptionDialog(null, label, "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0)
		           System.exit(0);
		    }
		};
		this.addWindowListener(exitListener);
		
		this.setVisible(true);
	}
	
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
	
	private void fileExists() {
        File file1 = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                api = line;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        
        this.add(label[0]);
        this.add(label[2]);
        this.add(label[3]);
        this.add(label[4]);
        this.add(field[1]);
        this.add(field[2]);
        this.add(button[1]);
        
		this.remove(label[1]);
		this.remove(field[0]);
		this.remove(button[0]);
        
        String[] options = {
                "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
                "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
                "BSD", "BTN", "BWP", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNH",
                "CNY", "COP", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP",
                "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS", "GIP", "GMD",
                "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS",
                "INR", "IQD", "IRR", "ISK", "JMD", "JOD", "JPY", "KES", "KGS", "KHR",
                "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD",
                "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRU",
                "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NOK", "NPR",
                "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR",
                "RON", "RSD", "RUB", "RWF", "SAR", "SCR", "SDG", "SEK", "SGD", "SHP",
                "SLL", "SOS", "SRD", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP",
                "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VND",
                "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW"
            };
        
        comboBox = new JComboBox<>(options);
        comboBox.setBounds(10, 65, 265, 25);
        comboBox.setFont(new Font("Ink free", Font.PLAIN, 15));
        comboBox.setBorder(new RoundedBorder(2));
        comboBox.setSelectedIndex(0); 
        this.add(comboBox);
        
        comboBox1 = new JComboBox<>(options);
        comboBox1.setBounds(10, 125, 265, 25);
        comboBox1.setFont(new Font("Ink free", Font.PLAIN, 15));
        comboBox1.setBorder(new RoundedBorder(2));
        comboBox1.setSelectedIndex(0); 
        this.add(comboBox1);
        
        this.setSize(new Dimension(300, 350));
        this.setLocationRelativeTo(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0]) {
			if(field[0].getText().isEmpty()) {
				label[1].setText("Please enter your API Key (can't be empty):");
			}
			else {
				 File file = new File(filePath);

		         try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
		             writer.write(field[0].getText());
		             writer.flush();

		             fileExists();
		         } catch (IOException e1) {
		             System.out.println("An error occurred while writing to the file: " + e1.getMessage());
		         }
			}
		}
		
		if(e.getSource()==button[1]) {
			try {
	            Integer.parseInt(field[1].getText());
	            String apiUrl = "https://api.fastforex.io/convert?from="+comboBox.getSelectedItem()+"&to="+comboBox1.getSelectedItem()+"&amount="+field[1].getText()+"&api_key=" + api;

	            try {
	                @SuppressWarnings("deprecation")
					URL url = new URL(apiUrl);
	                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	                conn.setRequestMethod("GET");

	                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                StringBuilder response = new StringBuilder();
	                String line;

	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }
	                reader.close();
	                
	                field[2].setForeground(Color.green);
	                String value = "";
	                
	                if(!comboBox1.getSelectedItem().toString().equals(comboBox.getSelectedItem().toString())) {
	                	value = response.substring(response.indexOf(comboBox1.getSelectedItem().toString())+5);
	                	value = value.substring(0, value.indexOf(","));
	                }
	                else {
	                	value = field[1].getText();
	                }
	                
	                field[2].setText("Value of "+field[1].getText()+comboBox.getSelectedItem()+" in "+comboBox1.getSelectedItem()+" = "+value);
	                conn.disconnect();

	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }

	        } catch (NumberFormatException e1) {
	            field[2].setText("Please enter a valid amount!");
	            field[2].setForeground(Color.red);
	        }
		}
	}
}