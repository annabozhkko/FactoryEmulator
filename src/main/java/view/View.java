package view;

import factory.FactoryModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JPanel implements ActionListener{
    private JFrame frame = new JFrame();
    private FactoryModel factoryModel;
    private JSlider sliderBody = new JSlider(0,10000);
    private JSlider sliderAccessory = new JSlider(0,10000);
    private JSlider sliderMotor = new JSlider(0, 10000);
    private JSlider sliderDealer = new JSlider(0, 10000);
    private JLabel labelBody = new JLabel();
    private JLabel labelAccessory = new JLabel();
    private JLabel labelMotor = new JLabel();
    private JLabel labelTotalBody = new JLabel();
    private JLabel labelTotalAccessory = new JLabel();
    private JLabel labelTotalMotor = new JLabel();
    private JLabel labelCar = new JLabel();
    private JLabel labelRequires = new JLabel();

    private Timer timer = new Timer(500, this);

    public View(FactoryModel factoryModel){
        this.factoryModel = factoryModel;
        frame.setTitle("Factory");
        frame.setSize(new Dimension(500, 400));
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ChangeListener listenerAccessory = new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                JSlider source = (JSlider) event.getSource();
                for(int i = 0; i < factoryModel.getAccessorySuppliers().length; ++i)
                    factoryModel.getAccessorySuppliers()[i].setWaitTime(source.getValue());
            }
        };

        sliderAccessory.addChangeListener(listenerAccessory);
        this.add(sliderAccessory);
        this.add(new JLabel("Accessory"));

        ChangeListener listenerBody = new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                JSlider source = (JSlider) event.getSource();
                factoryModel.getBodySupplier().setWaitTime(source.getValue());
            }
        };

        sliderBody.addChangeListener(listenerBody);
        this.add(sliderBody);
        this.add(new JLabel("Body"));

        ChangeListener listenerMotor = new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                JSlider source = (JSlider) event.getSource();
                factoryModel.getMotorSupplier().setWaitTime(source.getValue());
            }
        };

        sliderMotor.addChangeListener(listenerMotor);
        this.add(sliderMotor);
        this.add(new JLabel("Motor"));

        ChangeListener listenerDealer = new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                JSlider source = (JSlider) event.getSource();
                for(int i = 0; i < factoryModel.getDealers().length; ++i)
                    factoryModel.getDealers()[i].setWaitTime(source.getValue());
            }
        };

        sliderDealer.addChangeListener(listenerDealer);
        this.add(sliderDealer);
        this.add(new JLabel("Dealer"));

        this.add(labelAccessory);
        this.add(labelBody);
        this.add(labelMotor);
        this.add(labelTotalAccessory);
        this.add(labelTotalBody);
        this.add(labelTotalMotor);
        this.add(labelCar);
        this.add(labelRequires);

        frame.getContentPane().add(this);
        timer.start();

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        labelBody.setText("Bodies: " + factoryModel.getBodyStorage().getCountItems());
        labelMotor.setText("Motors: " + factoryModel.getMotorStorage().getCountItems());
        labelAccessory.setText("Accessory: " + factoryModel.getAccessoryStorage().getCountItems());
        labelTotalBody.setText("Total bodies: " + factoryModel.getBodyStorage().getAllCountItems());
        labelTotalAccessory.setText("Total accessory: " + factoryModel.getAccessoryStorage().getAllCountItems());
        labelTotalMotor.setText("Total motors: " + factoryModel.getMotorStorage().getAllCountItems());
        labelCar.setText("Cars: " + factoryModel.getCarStorage().getAllCountItems());
        labelRequires.setText("Requires: " + factoryModel.getController().getCountRequires());
    }
}
