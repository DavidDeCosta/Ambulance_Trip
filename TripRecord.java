import java.io.*;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class TripRecord {

//========================================Data Members //=================================================
    
    long date;                    //Date of the trip   long?
    String name;                  // Name of the patient
    String serviceCode;           // service code
    int initialMileage;           // initial mileage int?
    int mileageOnReturn;          // mileage on return int?
    double billingRate;           //billing rate   double?
    String comments;

    SimpleDateFormat sdf;
    String myDate;
    String anotherDate;

    Calendar cal;
    ParsePosition pos;

    Date datee;


//========================================Constructors //===================================================
    TripRecord()
    {

    }
    
    TripRecord(long numberForDate)
    {
        Date date;
        date = new Date(numberForDate);
        
    }

    TripRecord(String name, long date, String serviceCode, int initialMileage, int mileageOnReturn,
                double billingRate, String comments)
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
        int randomNumber;
        int randomNumber2;
        
        Random ran;
        ran = new Random();
        
        TripRecord rec;
        rec = new TripRecord();

        String [] ranName = {"David", "Emily","Vincent", "Nathan"};
        String [] ranCode = {"A0428", "A0429", "A0427", "A0434"};
        String [] dates = {"10/05/1990", "08/19/1999", "04/25/2000", "11/03/2018"};
        String [] ranComment = {"Patient was crazy", "Patient was calm", "Patient was in pain", "No comment"};

        rec.myDate = (dates[ran.nextInt(4)]);     // returns a random long to be used as a date

        rec.name = (ranName[ran.nextInt(4)]);
        rec.serviceCode = (ranCode[ran.nextInt(4)]);

        randomNumber = (ran.nextInt(30000)+100);   // random number for initial mileage
        rec.initialMileage = (randomNumber);

        randomNumber2 = randomNumber + ran.nextInt(1000);  // random number for mileage on return
        rec.mileageOnReturn = (randomNumber2);

        rec.billingRate = (ran.nextDouble(0,2000));

        rec.comments = (ranComment[ran.nextInt(4)]);


        return rec;
    }


    @Override 
    public String toString() 
    {

        billingRate = Math.floor(billingRate * 100) /100;   //makes the decimal show to 2 places

        return myDate + "   " + name + "   " + serviceCode + "   " + initialMileage + "   " + mileageOnReturn + "   "
               +  billingRate + "     " + comments + "     " ;
    }


    
}
