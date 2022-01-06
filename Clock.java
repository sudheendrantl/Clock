import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class Clock extends JComponent implements ActionListener{

	public static void main(String[] args) {
		new Clock();
	}

	Clock() {
		JFrame frame = new JFrame();
		frame.setSize(205,230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false);
		frame.setVisible(true);
		javax.swing.Timer timer = new javax.swing.Timer(1000,this);	
		timer.start();
		timer.setRepeats(true);
	}

	public void actionPerformed( ActionEvent ae ) {
		this.repaint();
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		int diameter = getSize().width;
		int radius = diameter/2;
		int centerX = diameter/2;
		int centerY = diameter/2;
		RenderingHints rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);

		Calendar cal = new GregorianCalendar();
		Integer i = (cal.get(Calendar.SECOND)/5)+1;
		String s = "Images/Clock"+i.toString()+".jpg";
		ImageIcon clockIcon= new ImageIcon(s);
		Image clockImage = clockIcon.getImage();
		BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g1 = resizedImage.createGraphics();
		g1.drawImage(clockImage, 0, 0, 200, 200, null);
		g1.dispose();
		g2d.drawImage(resizedImage, 0, 0, this);

		int seconds = cal.get(Calendar.SECOND);
		int degrees = seconds * 6;
		double x = radius * 0.8 * Math.cos(Math.toRadians(90-degrees));
		double y = radius * 0.8 * Math.sin(Math.toRadians(90-degrees));
		g2d.setPaint(Color.RED);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine(centerX, centerY, centerX+(int)x, centerY-(int)y);

		int minutes = cal.get(Calendar.MINUTE);
		degrees = minutes * 6;
		x = radius * 0.7 * Math.cos(Math.toRadians(90-degrees));
		y = radius * 0.7 * Math.sin(Math.toRadians(90-degrees));
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(centerX, centerY, centerX+(int)x, centerY-(int)y);

		int hours = cal.get(Calendar.HOUR);
		degrees = (hours * 30) + ( minutes * 30 / 60 );
		x = radius * 0.5 * Math.cos(Math.toRadians(90-degrees));
		y = radius * 0.5 * Math.sin(Math.toRadians(90-degrees));
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(centerX, centerY, centerX+(int)x, centerY-(int)y);
	}
}
