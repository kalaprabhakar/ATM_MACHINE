package com.icici.atm.source.DBaccess;

import com.icici.atm.factory.atmFactory;
import com.icici.atm.source.atm;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Stack;


public class Database {
    public double accbal;
    atm atm2 = atmFactory.getatmobj();
    public Database() {

    }

    public  void databaseaccess(String accountnumber , int pin){

        try(
               // BufferedReader bufferedreader  = new BufferedReader(new InputStreamReader(System.in));
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","Kprabhakar1999");
                PreparedStatement preparedStatement = connection.prepareStatement("select * from atm where accno = ? and pinno = ?");
                ) {
            preparedStatement.setString(1,accountnumber);
            preparedStatement.setInt(2,pin);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("************Login Successfully************");

                accbal=(Double.parseDouble(String.valueOf(resultSet.getInt(3))));


            }else {
                System.out.println("************Login Failure************\n************Incorrect Credential************");

                System.exit(0);
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void pinchange(int oldpin,int newpin){
        try (
                Connection connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","Kprabhakar1999");
                PreparedStatement preparedStatement = connection.prepareStatement("update atm set pinno = ? where pinno = ?");
                ){
            preparedStatement.setInt(1,newpin);
            preparedStatement.setInt(2,oldpin);
            int rowCount = preparedStatement.executeUpdate();
            if(rowCount==1){
                System.out.println("**********Your PIN is Successfully Updated**********");
            }else {
                System.out.println("**********Something Get Wrong Please Try Again**********");
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("**********Incorrect Old PIN**********");
        }
    }
    public void  withdwral(String accno , int pin , float totalbal){
        try(
                Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","Kprabhakar1999");
                PreparedStatement preparedStatement = connection.prepareStatement("update atm set accbal = ? where accno = ? and pinno = ?");
                ) {
            preparedStatement.setFloat(1,totalbal);
            preparedStatement.setString(2,accno);
            preparedStatement.setInt(3,pin);

           int rowCount2 = preparedStatement.executeUpdate();
           if(rowCount2 ==1){
               System.out.println("Transaction Processing...........");
               System.out.println("**********Collect Your Money**********");

           }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("**********ERROR : Transaction Cancelled**********");
        }
    }
    public  void  deposit(String accno ,int pin ,float totalbalance){
        try(
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","Kprabhakar1999");
                PreparedStatement preparedStatement = connection.prepareStatement("update atm set accbal = ? where accno = ? and pinno = ?");
                ) {
            preparedStatement.setFloat(1,totalbalance);
            preparedStatement.setString(2,accno);
            preparedStatement.setInt(3,pin);

            int rowCount3 =preparedStatement.executeUpdate();
            if(rowCount3==1){
                System.out.println("**********Please Deposit Money**********");
                System.out.println("**********Money Successfully Deposited**********");
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("**********ERROR:Transaction Failure**********");
        }
    }
    public void transafer(String accno , int pin , float totalbal , float trasfamo,String trnsAcc){
        try(
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","Kprabhakar1999");
                PreparedStatement preparedStatement = connection.prepareStatement("update atm set accbal = ? where accno = ? and pinno = ?");
                PreparedStatement preparedStatement1 = connection.prepareStatement("update atm set accbal = accbal + ? where accno = ? ");
                ) {
            preparedStatement.setFloat(1,totalbal);
            preparedStatement.setString(2,accno);
            preparedStatement.setInt(3,pin);

            preparedStatement1.setFloat(1,trasfamo);
            preparedStatement1.setString(2,trnsAcc);


            int rowCount4 =preparedStatement.executeUpdate();
            int rowCount5 = preparedStatement1.executeUpdate();

            if(rowCount4==1){
                System.out.println("**********TRANSACTION SUCCESSFUL**********");
            }

            if(rowCount5==1){
                System.out.println("Amount Transferred To "+trnsAcc+" Account");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
