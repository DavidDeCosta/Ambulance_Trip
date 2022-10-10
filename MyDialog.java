import java.awt.Dialog;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.*;

public class MyDialog extends JDialog
                        implements ActionListener
{
//==================================DATA MEMBERS ====================================================
    JComponent panel;
    GroupLayout groupLayout;

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

//==================================CONSTRUCTORS ===================================================
    MyDialog()
    {
        buildGUI();
    }

    MyDialog(TripRecord rec)
    {

    }

//====================================METHODS ===========================================================

    void buildGUI()
    {

//============================= Instantiating Labels and TextFields ========================================

        dateTF = new JTextField(30);
        dateLabel = new JLabel("Enter the Date: ");

        nameLabel = new JLabel("Enter your name: ");
        nameTF = new JTextField(30);

        serviceCodeLabel = new JLabel("Enter seriveCode: ");
        serviceCodeTF = new JTextField(30);

        initalMileageLabel = new JLabel("Enter initial Mileage: ");
        initialMileageTF= new JTextField(30);

        mileageOnReturnLabel = new JLabel("Enter return mileage ");
        mileageOnReturnTF = new JTextField(30);

        billingRateLabel = new JLabel("Enter billing rate: ");
        billingRateTF = new JTextField(30);

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
        
//=================================Creating horizonal and vertical groups ==========================================

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        hGroup.addGroup(groupLayout.createParallelGroup().
        addComponent(dateLabel).addComponent(nameLabel).addComponent(serviceCodeLabel).addComponent(initalMileageLabel)
        .addComponent(mileageOnReturnLabel).addComponent(billingRateLabel).addComponent(commentsLabel).addComponent(submitButton));
        hGroup.addGroup(groupLayout.createParallelGroup().
        addComponent(dateTF).addComponent(nameTF).addComponent(serviceCodeTF).addComponent(initialMileageTF)
        .addComponent(mileageOnReturnTF).addComponent(billingRateTF).addComponent(commentsTF).addComponent(cancelButton));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(dateLabel).addComponent(dateTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(nameLabel).addComponent(nameTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(serviceCodeLabel).addComponent(serviceCodeTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(initalMileageLabel).addComponent(initialMileageTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(mileageOnReturnLabel).addComponent(mileageOnReturnTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(billingRateLabel).addComponent(billingRateTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(commentsLabel).addComponent(commentsTF));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).
        addComponent(submitButton).addComponent(cancelButton));
        groupLayout.setVerticalGroup(vGroup);


        add(panel);
        setLocationRelativeTo(null);   //centers the JDialog
        setSize(400,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    void populateFields(TripRecord trip)
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
        
    }

    void handSubmit()
    {
        
    }
}
