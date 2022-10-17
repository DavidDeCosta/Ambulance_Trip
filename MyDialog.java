import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.xml.transform.Source;

import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.awt.*;

public class MyDialog extends JDialog
                        implements ActionListener
{
//==================================DATA MEMBERS ====================================================
    JComponent panel;
    GroupLayout groupLayout;

    JPanel panelForSubmitCancel;

    JLabel dateLabel;
    JLabel nameLabel;
    JLabel serviceCodeLabel;
    JLabel initalMileageLabel;
    JLabel mileageOnReturnLabel;
    JLabel billingRateLabel;
    JLabel commentsLabel;

    JTextField dateTF;
    JTextField nameTF;
    JTextField serviceCodeTF;
    JTextField initialMileageTF;
    JTextField mileageOnReturnTF;
    JTextField billingRateTF;
    JTextField commentsTF;

    JButton submitButton;
    JButton cancelButton;

    BillingVerifier billingVerifier;

    String []serviceCodeOptions = { "", "A0428", "A0429", "A0427", "A0434"};
    JComboBox comboBox;

    TripRecord record;
    String name;
    long date;
    int initialMileage;
    int mileageOnReturn;
    String serviceCode;
    String comments;
    double billingRate;

    DataManager dataManager;
    MyListModel justAListModel;

    
    SimpleDateFormat sdf;
    ParsePosition pos;

//==================================CONSTRUCTORS ===================================================
    MyDialog(DataManager manager)
    {
        this.dataManager = manager;
        buildGUI();
    }

    MyDialog(DataManager manager, TripRecord record, int index)
    {
        this.dataManager = manager;
        this.record = record;
        buildGUI();
        populateFields(record);

    }

//====================================METHODS ===========================================================

    void buildGUI()
    {

//============================= Instantiating Labels and TextFields ========================================

        dateTF = new JTextField(30);
        dateLabel = new JLabel("Enter the Date: ");
        dateTF.setInputVerifier(new DateVerifier()); // sets the verifier

        nameLabel = new JLabel("Enter name: ");
        nameTF = new JTextField(30);

        serviceCodeLabel = new JLabel("Enter seriveCode: ");
        comboBox = new JComboBox<>(serviceCodeOptions);

        initalMileageLabel = new JLabel("Enter initial Mileage: ");
        initialMileageTF= new JTextField(30);
        initialMileageTF.setInputVerifier(new MileageVerifier());

        mileageOnReturnLabel = new JLabel("Enter return mileage ");
        mileageOnReturnTF = new JTextField(30);
        mileageOnReturnTF.setInputVerifier(new MileageVerifier());

        billingRateLabel = new JLabel("Enter billing rate: ");
        billingRateTF = new JTextField(30);
        billingRateTF.setInputVerifier(new BillingVerifier()); //sets the verifier

        commentsLabel = new JLabel("Enter additional comments: ");
        commentsTF = new JTextField(30);

//===================================Adding buttons ===========================================================
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);


        panel = new JPanel();
        groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        panelForSubmitCancel = new JPanel();
        add(panelForSubmitCancel,BorderLayout.SOUTH);
        panelForSubmitCancel.add(submitButton);
        panelForSubmitCancel.add(cancelButton);
        
//=================================Creating horizonal and vertical groups ==========================================

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        hGroup.addGroup(groupLayout.createParallelGroup().
        addComponent(dateLabel).addComponent(nameLabel).addComponent(serviceCodeLabel).addComponent(initalMileageLabel)
        .addComponent(mileageOnReturnLabel).addComponent(billingRateLabel).addComponent(commentsLabel));
        hGroup.addGroup(groupLayout.createParallelGroup().
        addComponent(dateTF).addComponent(nameTF).addComponent(comboBox).addComponent(initialMileageTF)
        .addComponent(mileageOnReturnTF).addComponent(billingRateTF).addComponent(commentsTF));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(dateLabel).addComponent(dateTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(nameLabel).addComponent(nameTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(serviceCodeLabel).addComponent(comboBox));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(initalMileageLabel).addComponent(initialMileageTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(mileageOnReturnLabel).addComponent(mileageOnReturnTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(billingRateLabel).addComponent(billingRateTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(commentsLabel).addComponent(commentsTF));
        groupLayout.setVerticalGroup(vGroup);


        add(panel);
        setLocationRelativeTo(null);   //centers the JDialog
        setSize(400,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }


    void populateFields(TripRecord record)
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if(e.getActionCommand().equals("Cancel"))
        {
            this.dispose();
        }
        else if(e.getActionCommand().equals("Submit"))
        {
            handSubmit();
        }
        else if(e.getActionCommand().equals("serviceCodeTF"))
        {

        }
    }

    void handSubmit()
    {
        String tempForMileage;
        String tempforBilling;

        record = new TripRecord(name, date,serviceCode,initialMileage,mileageOnReturn,billingRate, comments );
        record.name = nameTF.getText();
        record.serviceCode = (String)comboBox.getSelectedItem();
        record.comments = commentsTF.getText();
        tempForMileage = initialMileageTF.getText();
        record.initialMileage = Integer.parseInt(tempForMileage);
        tempForMileage = mileageOnReturnTF.getText();
        record.mileageOnReturn = Integer.parseInt(tempForMileage);
        tempforBilling = billingRateTF.getText();
        record.billingRate = Double.parseDouble(tempforBilling);

        
        if(initialMileage >mileageOnReturn)
        {
            initialMileageTF.requestFocusInWindow();
        }

        if(nameTF.getText().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(this, "Must enter a name");
        }

/*
        String stringOfDate;
        stringOfDate = dateTF.getText();
        System.out.println(" \n THIS IS THE STRING date: " + stringOfDate + "\n");
        record.date = convertStringToDate(stringOfDate);    //convers the string from the textfield to a Date datatype
 */

        record.date = dateTF.getText();
        dataManager.add(record);
        justAListModel.numberOfTripRecords++;  // to keep track of how many to store


        
    }

    Date convertStringToDate(String stringOfDate)
    {
        
        Date date = null;
        sdf = new SimpleDateFormat("M/d/y");
//        pos = new ParsePosition(0);

        try 
        {
            date = (Date)sdf.parse(stringOfDate);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }


        return date;
    }
}
