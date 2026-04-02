public class Policy {
    private String policyNumber;
    private String clientName;
    private double basePremium;
    private int riskLevel;
    private double vehicleValue;
    private boolean hasAlarm;
    private boolean claimFreeClient;
    private static int createdPolicyCount = 0;
    private static final double ADMINISTRATIVE_FEE = 50.0;

    public Policy(String policyNumber, String clientName, double basePremium, int riskLevel, double vehicleValue, boolean hasAlarm, boolean claimFreeClient) {
        this.policyNumber = policyNumber;
        this.clientName = clientName;
        this.basePremium = basePremium;
        this.riskLevel = riskLevel;
        this.vehicleValue = vehicleValue;
        this.hasAlarm = hasAlarm;
        this.claimFreeClient = claimFreeClient;
        createdPolicyCount++;
    }

    public String getPolicyNumber() {return policyNumber;}
    public String getClientName() {return clientName;}
    public double getBasePremium() {return basePremium;}
    public int getRiskLevel() {return riskLevel;}
    public double getVehicleValue() {return vehicleValue;}
    public boolean isHasAlarm() {return hasAlarm;}
    public boolean isClaimFreeClient() {return claimFreeClient;}

    public double calculateFinalPremium(){
        double premium = basePremium;
        premium += riskLevel * 120;
        if(vehicleValue > 60000){
            premium += 200;
        } else if (vehicleValue > 30000){
            premium += 100;
        }
        if (hasAlarm) {
            premium *= 0.92;
        }
        if (claimFreeClient) {
            premium *= 0.90;
        }
        premium+= ADMINISTRATIVE_FEE;
        if (premium < basePremium) {
            premium = basePremium;
        }
        return premium;
    }
    public double calculateRenewalPremium(){
        double renewal  = calculateFinalPremium();
        if( riskLevel == 4){
            renewal *= 1.10;
        } else if (riskLevel >= 5){
            renewal *= 1.20;
        }
        if(vehicleValue > 60000){
            renewal += 150;
        }
        if(claimFreeClient){
            renewal *= 0.92;
        }
        if (hasAlarm) {
            renewal *= 0.95;
        }
        double currentFinal = calculateFinalPremium();
        if (renewal < currentFinal*0.90) {
            renewal = currentFinal*0.90;
        }
        if(renewal > currentFinal*1.25){
            renewal = currentFinal*1.25;
        }
        return Math.round(renewal*100.0)/100.0;
    }
    public String getRiskSummary(){
        if (riskLevel <= 2) return "Low risk";
        if (riskLevel == 3) return "Medium risk";
        if (riskLevel == 4) return "High risk";
        return "Very high risk";
    }
    public static int getCreatedPolicyCount(){

        return createdPolicyCount;}
    }
}
