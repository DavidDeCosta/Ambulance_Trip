import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class MileageVerifier extends InputVerifier
{


    @Override
    public boolean verify(JComponent input) 
    {
        String initialMileage;
        initialMileage = ((JTextField)(input)).getText().trim();

        if(initialMileage.equals(""))
        {
            return true;
        }

        double originalInitialMileageValue;
        int numForInitialMileage;

        originalInitialMileageValue = Double.parseDouble(initialMileage);     
        numForInitialMileage = (int)originalInitialMileageValue;

        if(numForInitialMileage == originalInitialMileageValue)  //makes sure there isn't any numbers in the decimal place. 'if any'
        {
            return true;
        }


        String mileageOnReturn;
        mileageOnReturn = ((JTextField)(input)).getText().trim();

        if(mileageOnReturn.equals(""))
        {
            return true;
        }


        double originalMileageOnReturn;
        int numForMileageOnReturn;

        originalMileageOnReturn = Double.parseDouble(mileageOnReturn);     
        numForMileageOnReturn = (int)originalMileageOnReturn;


        if(numForMileageOnReturn == originalMileageOnReturn)  //makes sure there isn't any numbers in the decimal place. 'if any'
        {
            return true;
        }

        if(numForInitialMileage < numForMileageOnReturn)
        {
            return true;
        }

        return false;
    }
    
}
