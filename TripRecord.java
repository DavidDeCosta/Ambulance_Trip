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

        return rec;
    }


    @Override 
    public String toString() 
    { 
        return name;
    }

}
