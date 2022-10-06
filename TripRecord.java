import java.io.*;
import javax.swing.JOptionPane;
import java.sql.Date;

public class TripRecord {

//========================================Data Members //=================================================
    
    long Date;                    //Date of the trip
    String name;                  // Name of the patient
    String serviceCode;           // service code
    int initialMileage;           // initial mileage
    int mileageOnReturn;          // mileage on return
    double billingRate;           //billing rate
    String comments;

//========================================Constructors //===================================================
    TripRecord()
    {

    }

    TripRecord(long numberForDate)
    {
        Date date;
        date = new Date(numberForDate);
        
    }

    TripRecord(String name)
    {
        this.name = name;
    }

    TripRecord(DataInputStream dis)
    {
        try 
        {
            this.name = dis.readUTF();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Could not read name");
        }
    }

//=========================================Methods //===================================================

    void store(DataOutputStream dos)
    {
        try 
        {
            dos.writeUTF(name);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    static TripRecord getRandom()
    {
        TripRecord rec;
        rec = new TripRecord();

        String [] ranName = {"David", "Emily","Vincent", "Nathan"};
        String [] ranDate = {"10/06/2022" , "10/27/2022", "11/01/2022", "11/19/2022"};
        String [] ranCode = {"A0428", "A0429", "A0427", "A0434"};
        String [] ranInitial ={"0", "1,500", "30,000", "100,000"};
        String [] ranReturn = {"300", " 2,000", " 30,120", " 100,350"};
        String [] ranBilling = {"$500/mi" , "$1,000/mi", "$2,000/mi", "$2,500/mi"};
        String [] ranComment = {"Patient was crazy", "Patient was calm", "Patient was in pain", "No comment"};

        

        return rec;
    }


    @Override 
    public String toString() 
    { 
        return name;
    }

}
