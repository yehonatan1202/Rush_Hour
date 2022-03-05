import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Menu extends JFrame {
    public Menu() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Rectangle r = getBounds();
        JPanel bg = new JPanel();
        ImageIcon img = new ImageIcon("Menu_Images/Background.GIF");
        JLabel gif = new JLabel(img);
        bg.add(gif);
        bg.setBounds(0, 0, 3840, 2160);
        MenuButton play = new MenuButton("Menu_Images/Menu_Play.png");
        // ("Menu_Images/Menu_Play.png");
        // play.setBounds(500,500,200,80);
        add(play);
        add(bg);
        setVisible(true);
    }

    public class Panel extends JPanel {
        Image backGroundImage;
        int Height, Width;

        public Panel(String photo) {
            setBounds(0, 0, 3840, 2160);
            ImageIcon img = new ImageIcon(photo);
            backGroundImage = img.getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backGroundImage, 0, 0, 2600, 1440, null);
            // repaint();

        }
    }

    public class MenuButton extends JButton {
        Image backGroundImage;

        public MenuButton(String photo) {
            ImageIcon img = new ImageIcon(photo);
            backGroundImage = img.getImage();
            setBounds(900, 750, 492, 164);
            setBackground(Color.black);
            setOpaque(false);
            Border emptyBorder = BorderFactory.createEmptyBorder();
            setBorder(emptyBorder);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backGroundImage, 0, 0, null);
        }
    }
}
