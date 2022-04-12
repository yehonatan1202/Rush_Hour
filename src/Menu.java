import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.GridBagLayout;
public class Menu extends JFrame{
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((int)size.getWidth(), (int)size.getHeight());
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		ImageIcon img = new ImageIcon("Menu_Images/Menu_Background.Png");
		MenuButton play = new MenuButton("Menu_Images/Menu_Play.png");
		JLabel lb = new JLabel(img);
		JPanel bg = new JPanel(new GridBagLayout());
		bg.add(lb);
		add(play);
		add(bg);
		setVisible(true);
	}
//	int w = getWidth();
//	int h = getHeight();
//	int x = w/2-w/4;
//	int y = h/2-h/4;
//	g.fillRect(x,y,w/2,h/2);
public class MenuButton extends JButton
{
	Image backGroundImage;

	public MenuButton(String photo)
	{
		ImageIcon img = new ImageIcon(photo);
		backGroundImage = img.getImage();
		setBounds(900,750,492,164);
		setBackground(Color.black);
		setOpaque(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
			g.drawImage(backGroundImage, 0, 0, null);
	}
}
}
