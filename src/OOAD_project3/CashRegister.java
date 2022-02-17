package src.OOAD_project2;

public class CashRegister {
    private double moneyAmt;
    private double moneyFromBank;
    
    // CashRegister constructor
    public CashRegister(){
        this.moneyAmt = 0.0;
        this.moneyFromBank = 0.0;
    }

    //Gets the amount in the cash register
    public double getMoneyAmt(){
        return this.moneyAmt;
    }

    // Gets the total amount added to the register from the bank
    public double getMoneyFromBank(){
        return this.moneyFromBank;
    }

    // Adds an amount to the register
    public void addToRegister(double addAmt){
        this.moneyAmt += addAmt;
    }

    // Keeps track of money from the bank
    public void addMoneyFromBank(double addAmt){
        this.moneyFromBank += addAmt;
    }

    // Pays the customer from the register
    public void payCustomer(double amtPaid){
        this.moneyAmt -= amtPaid;
    }
}
