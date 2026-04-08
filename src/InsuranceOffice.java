import java.util.ArrayList;

public class InsuranceOffice {
    private String name;
    private ArrayList<Policy> policies;

    public InsuranceOffice(String name) {
        this.name = name;
        this.policies = new ArrayList<>();
    }

    public void addPolicy(Policy policy){
        policies.add(policy);
    }
    public void printReport(){
        System.out.println("REPORT: " + name);
        for (Policy p : policies){
            System.out.println(p);
        }

    }
    public double calculateTotalPremium(){
        double total = 0;
        for (Policy p : policies){
            total+= p.calculateFinalPremium();
        }return total;
    }
    public double calculateTotalRenewalForecast(){
        double total = 0;
        for (Policy p : policies){
            total+= p.calculateRenewalPremium();
        }return total;
    }
    public int countHighRiskPolicies(){
        int count = 0;
        for (Policy p : policies){
            if(p.getRiskLevel() > 4){
                count++;
            }
        }return count;
    }
    public Policy findByNumber(String policyNumber){
        for (Policy p : policies){
            if(p.getPolicyNumber().equals(policyNumber)){
                return p;
            }
        }return null;
    }
    public void  printCheaperThan(double threshold){
        System.out.println("CHEAPER THAN " + threshold);
        for (Policy p : policies){
            if(p.calculateFinalPremium() < threshold){
                System.out.println(p);
            }
        }
    }
}

