package deliveryapp;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class DeliveryRunnerLabel extends JLabel {

    public DeliveryRunnerLabel() {
        this.setText("Delivery Runner");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.BOTTOM);

        ImageIcon picture = new ImageIcon("C:\\Mana\\Object Oriented with Java\\Delivery_runner.png");
        this.setIcon(picture);

        int width = 720;
        int height = 680;
        picture.setImage(picture.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.TOP);

        Font labelFont = new Font("Times New Roman", Font.BOLD, 20);
        this.setFont(labelFont);
        this.setBounds(0, 20, 720, 700);
    }
}
