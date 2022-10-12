import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class BillingVerifier extends InputVerifier
{


    @Override
    public boolean verify(JComponent input) {
        
        String billingRate;
        billingRate = ((JTextField)(input)).getText().trim();

        int numForBilling;
//        numForBilling = Integer.parseInt(billingRate);

        double originalBillingNum;
        originalBillingNum = Double.parseDouble(billingRate);

        numForBilling = (int)(originalBillingNum*100);

        numForBilling /= 100.0;

 //       String[] splitString = billingRate.split("\\.");

        if(billingRate.equals(""))
        {
            return true;
        }
        else if(numForBilling == originalBillingNum)
        {
            return true;
        }

        return false;
    }
    
}
