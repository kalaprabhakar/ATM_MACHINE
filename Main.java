import com.icici.atm.factory.atmFactory;
import com.icici.atm.source.DBaccess.Database;
import com.icici.atm.source.atm;
import oracle.net.jdbc.TNSAddress.SOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        Database database = new Database();
        atm atm1 =atmFactory.getatmobj();
        BufferedReader bufferedreader  = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-----------------------------------------------------");
        System.out.println("Please Enter Your Account Number");
        String accountNumber = bufferedreader.readLine();
        System.out.println("Please Enter Your Secrete PIN ");
        int pin= Integer.parseInt(bufferedreader.readLine());
        database.databaseaccess(accountNumber,pin);
        atm1.setAccountBalance((float) database.accbal);
        System.out.println();
        System.out.println("************Welcome To ICICI Bank ATM************");
        while (true) {
            System.out.println("Choice  Your  Operation");
            System.out.println("1.BANKING");
            System.out.println("2.CHECK BALANCE");
            System.out.println("3.PIN CHANGE");
            System.out.println("4.EXIT");
            int num = Integer.parseInt(bufferedreader.readLine());
            System.out.println();
            switch (num){
                case 1:
                    System.out.println("1.WITHDRAWAL");
                    System.out.println("2.DEPOSIT");
                    System.out.println("3.TRANSFER");
                    System.out.println("4.EXIT");
                    int num1 = Integer.parseInt(bufferedreader.readLine());
                    switch (num1){
                        case 1 :
                            System.out.println("Enter The Amount For Withdrawal");
                            float wamnt = Float.parseFloat(bufferedreader.readLine());
                            float totalBalance = (float) (atm1.getAccountBalance()-wamnt);
                            atm1.setAccountBalance(totalBalance);
                            database.withdwral(accountNumber,pin,totalBalance);
                            System.out.println("Available Balance :"+atm1.getAccountBalance());

                            break;

                        case 2 :
                            System.out.println("Enter Amount To Deposit");
                            float amount =Float.parseFloat(bufferedreader.readLine());
                            float totalbal= (float) (atm1.getAccountBalance()+amount);
                            atm1.setAccountBalance(totalbal);
                            database.deposit(accountNumber,pin,totalbal);
                            System.out.println("Available Balance :"+atm1.getAccountBalance());
                            break;

                        case 3 :
                            System.out.println("Enter The Receiver  Account Number ");
                            String accNum = bufferedreader.readLine();
                            System.out.println("Enter The Amount ");
                            float trnamo = Float.parseFloat(bufferedreader.readLine());
                            float totalAmount= (float) (atm1.getAccountBalance()-trnamo);
                            atm1.setAccountBalance(totalAmount);
                            database.transafer(accountNumber,pin,totalAmount,trnamo,accNum);

                            break;

                        case 4:
                            System.exit(0);


                    }

                    break;
                case 2:
                    System.out.println("Your Account Balance is :"+atm1.getAccountBalance());

                    break;
                case 3 :
                    System.out.println("Enter Your Old PIN");
                    int pin1 =Integer.parseInt(bufferedreader.readLine());
                    System.out.println("Enter  Your New PIN");
                    int newpin1 =Integer.parseInt(bufferedreader.readLine());

                    database.pinchange(pin1,newpin1);

                    break;

                case 4 :
                    System.out.println("***************ThanQ Visit Again***************");
                    System.exit(0);


            }

            
        }
        




    }
}