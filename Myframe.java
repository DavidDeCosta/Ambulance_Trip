import javax.swing.*;                           //for JButton
import javax.swing.event.*;                    //for ChangeListener
import java.awt.event.*;                       // for ActionListener
import java.io.*;
import java.awt.*;                             // for Dimension and Toolkit
import java.util.Date;

public class Myframe extends JFrame
                        implements ActionListener, ListSelectionListener
{
    //=======================================DATA MEMBERS =====================================================

    Dimension screenSize;
    Toolkit toolkit;
    MyListModel justAListModel;
    TripRecord record;

    JMenuBar menuBar;
    JMenu theMenuOnTheBar;
    JMenuItem loadFromMenu;
    JMenuItem saveFromMenu;
    JMenuItem addFromMenu;
    JMenuItem deleteFromMenu;
    JMenuItem saveAsFromMenu;
    JMenuItem clear;

    JPanel southPanel;
    JPanel centerPanel;

    JButton load;
    JButton save;
    JButton saveAs;
    JButton add;
    JButton delete;
    JButton add_random;
    JButton exit;
    

    JDialog userImputField;

    JTextField date;
    JTextField enterName;
    JTextField enterServiceCode;
    JTextField initialMileage;
    JTextField mileageOnReturn;
    JTextField billingRate;
    JTextField comments;

    Date myDate;

    String userName;

    JFileChooser theFileChooser;                  

    JList<TripRecord> displayList;                           // displays their names
    JScrollPane tripScrollPane;



    //======================================CONSTRUCTOR =======================================================
    Myframe()
    {
        theFileChooser = new JFileChooser(".");   //opens current working directory

        addComponents();
        buildGUI();
    }


//=========================================METHODS ==================================================================


    void addComponents()
    {
        
        //======================= setting up the JList to view //===================================================
        justAListModel = new MyListModel();
        displayList = new JList(justAListModel);
        tripScrollPane = new JScrollPane(displayList);
        add(tripScrollPane, BorderLayout.CENTER);
        
        //======================= setting up south Panel for buttons //===============================================
        southPanel = new JPanel();
        southPanel.setBackground(Color.GRAY);
        southPanel.setPreferredSize(new Dimension(100,30));
        add(southPanel, BorderLayout.SOUTH);

        load = new JButton("load");
        load.addActionListener(this);
        southPanel.add(load);

        save = new JButton("save");
        save.addActionListener(this);
        southPanel.add(save);

        saveAs = new JButton("saveAs");
        southPanel.add(saveAs);
        saveAs.setToolTipText("alt + s");
        saveAs.setMnemonic('s');             //press alt + d to delete
        saveAs.addActionListener(this);

        add = new JButton("add");
        add.addActionListener(this);
        add.setToolTipText("alt+a , add name");
        add.setMnemonic('A');                //press alt + a to add
        southPanel.add(add);

        delete = new JButton("delete");
        delete.addActionListener(this);
        delete.setToolTipText("alt + d, to delete");
        delete.setMnemonic('d');             //press alt + d to delete
        southPanel.add(delete);

        add_random = new JButton("add_random");
        add_random.addActionListener(this);
        southPanel.add(add_random);

        exit = new JButton("exit");
        exit.addActionListener(this);
        southPanel.add(exit);


        //===================================setting up the JMenu Bar  //============================================
        menuBar = new JMenuBar();
        add(menuBar,BorderLayout.NORTH);

        theMenuOnTheBar = new JMenu("File");
        menuBar.add(theMenuOnTheBar);

        loadFromMenu = new JMenuItem("load");
        loadFromMenu.addActionListener(this);
        theMenuOnTheBar.add(loadFromMenu);

        saveFromMenu = new JMenuItem("save");
        saveFromMenu.addActionListener(this);
        theMenuOnTheBar.add(saveFromMenu);

        deleteFromMenu = new JMenuItem("delete");
        deleteFromMenu.addActionListener(this);
        theMenuOnTheBar.add(deleteFromMenu);

        addFromMenu = new JMenuItem("add");
        theMenuOnTheBar.add(addFromMenu);
        addFromMenu.addActionListener(this);

        saveAsFromMenu = new JMenuItem("saveAs");
        theMenuOnTheBar.add(saveAsFromMenu);
        saveAsFromMenu.addActionListener(this);

        clear = new JMenuItem("clear");
        theMenuOnTheBar.add(clear);
        clear.addActionListener(this);

 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("exit"))
        {
            this.dispose();
        }
        else if(e.getActionCommand().equals("add"))
        {
            handleAdd();
        }
        else if(e.getActionCommand().equals("saveAs"))
        {
            handleSaveAs();
        }
        else if(e.getActionCommand().equals("save"))
        {
            handleSave();
        }
        else if(e.getActionCommand().equals("delete"))
        {
            handleDelete();
        }
        else if(e.getActionCommand().equals("load"))
        {
            handleLoad();
        }
        else if(e.getActionCommand().equals("clear"))
        {
            justAListModel.clear();
            justAListModel.numberOfTripRecords = 0;                    //sets the list to empty
        }
        else if(e.getActionCommand().equals("add_random"))
        {
            handleadd_random();
        }
        
    }

    void handleAdd()
    {
        date = new JTextField(30);
        enterName = new JTextField(30);
        enterServiceCode = new JTextField(30);
        initialMileage = new JTextField(30);
        mileageOnReturn = new JTextField(30);
        billingRate = new JTextField(30);
        comments = new JTextField(30);

        Object [] fields = {
            "Enter Date of trip", date,
            "Enter name", enterName,
            "Enter Service Code", enterServiceCode,
            "Enter initial mileage", initialMileage,
            "Enter mileage on return", mileageOnReturn,
            "Enter the billing rate", billingRate,
            "Additional comments", comments

        };

        JOptionPane.showConfirmDialog(null, fields, "Ambulance trip info", JOptionPane.OK_CANCEL_OPTION);

        

//        userImputField = new JDialog();
//        userImputField.setLayout((new GridLayout(4,2)));
//        userImputField.add(enterName);
//        userImputField.add(enterServiceCode);
//        userImputField.setSize(250, 120);
//        userImputField.setVisible(true);


//        userName = JOptionPane.showInputDialog("Enter a name");  //userName will hold the response from the user
//        record = new TripRecord(userName);
//        justAListModel.addElement(record);
//        justAListModel.numberOfTripRecords += 1;
//        System.out.println("There are " + justAListModel.numberOfTripRecords + " record in the list");
    }

    void handleDelete()
    {
        int index[];
        index = displayList.getSelectedIndices();
        for(int n  = index.length-1; n >= 0; n--)
        {
            justAListModel.removeElementAt(index[n]);
            justAListModel.numberOfTripRecords -= 1;
            System.out.println("There are " + justAListModel.numberOfTripRecords + " record in the list");
        }
    }

    void handleSaveAs()
    {
        int savedOrNot;                                               // did they cancel or save?
        File theFileTheUserChooses;                                   // file for what they typed in or picked?
        File theFileTheUserChooses2;
        DataOutputStream dos;                                          // will pass the File to the dos

        savedOrNot = theFileChooser.showSaveDialog(null);     // if returns 0 they saved file if 1 they exited
        if(savedOrNot == JFileChooser.APPROVE_OPTION)
        {
            theFileTheUserChooses = theFileChooser.getSelectedFile();           //grabs the file the user types or selected
            if(theFileTheUserChooses.exists())
            {
                
                int n = JOptionPane.showConfirmDialog(this, "Do you want to Overwrite the file? ", "Confirm Overwrite", JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION)
                {
                try 
                {
                    theFileTheUserChooses = theFileChooser.getSelectedFile();           //grabs the file the user types or selected
                    dos = new DataOutputStream(new FileOutputStream(theFileTheUserChooses));     //form a dos with the file
                    justAListModel.store(dos);                                                   // store it to write to later
                }
                catch (FileNotFoundException e1) 
                {
                    JOptionPane.showMessageDialog(this, "Could not save the file");
                }
                }
                else
                {
                    System.out.println("Do nothing");
                }
            }
            else
            {
                try 
                {
                    theFileTheUserChooses2 = theFileChooser.getSelectedFile();           //grabs the file the user types or selected
                    dos = new DataOutputStream(new FileOutputStream(theFileTheUserChooses));     //form a dos with the file
                    justAListModel.store(dos);                                                   // store it to write to later
                }
                catch (FileNotFoundException e1) 
                {
                    JOptionPane.showMessageDialog(this, "Could not save the file");
                }
            }
        }
    }   


    void handleSave()
    {
        DataOutputStream dos;
        if(theFileChooser.getSelectedFile() == null)
        {
            handleSaveAs();
        }
        else
        {
            try 
            {
                dos = new DataOutputStream(new FileOutputStream(theFileChooser.getSelectedFile()));     //form a dos with the file
                justAListModel.store(dos);                                                   // store it to write to later
            }
            catch (FileNotFoundException e1) 
            {
                JOptionPane.showMessageDialog(this, "Could not save the file");
            }
        }
    }

    void handleLoad()
    {
        DataInputStream dis;
        int fileChooser;
        fileChooser = theFileChooser.showOpenDialog(null);

        if(fileChooser == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                dis = new DataInputStream(new FileInputStream(theFileChooser.getSelectedFile()));
                justAListModel = new MyListModel(dis);
                displayList.setModel(justAListModel);


            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(this, "Error, could not load");
            }
        }
    }

    void handleadd_random()
    {
        long number;
        number = myDate.getTime();

        TripRecord randomInstance;
        randomInstance = new TripRecord(number);
    }

    void buildGUI()
    {
        toolkit = Toolkit.getDefaultToolkit();                      // used to help get the users screen size
        screenSize = toolkit.getScreenSize();                       //get the users screen size
        setSize(screenSize.width/3, screenSize.height/3);           // makes JFrame 1/3 the users screensize
        setLocationRelativeTo(null);                             // window is placed in the center of screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //when close frame the program stops
        setTitle("Project 3 Ambulance Trip Record");
        setVisible(true);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        
        
    }

}