public class Clerk {
    private String name;

    public Clerk(String clerkName){
        this.name = clerkName;
    }

    public String getName(){
        return this.name;
    }

    public void arriveAtStore(int dayNum){
        System.out.printf("----- %s arrives at the store on day %d -----\n", this.name, dayNum);
        throw new UnsupportedOperationException("TODO - add PlaceAnOrder follow-up from previous day");
    }

    public void checkRegister(CashRegister register){
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s counted $%.2f in the register.\n", this.name, moneyInReg);
        if (moneyInReg < 75.0){
            this.goToBank(register);
        }
    }

    private void goToBank(CashRegister register){
        register.addMoneyFromBank(1000.0);
        register.addToRegister(1000.0);
        double moneyInReg = register.getMoneyAmt();
        System.out.printf("%s went to the bank and put $1000 in the register. There is now $%.2f in the register.\n", this.name, moneyInReg);
    }

    public void doInventory(){
        throw new UnsupportedOperationException("TODO");
    }

    public void placeAnOrder(){
        throw new UnsupportedOperationException("TODO");
    }

    public void openTheStore(){
        throw new UnsupportedOperationException("TODO");
    }

    public void cleanTheStore(){
        throw new UnsupportedOperationException("TODO");
    }

    public void leaveTheStore(){
        throw new UnsupportedOperationException("TODO");
    }
}
