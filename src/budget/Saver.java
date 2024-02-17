package budget;

public class Saver {
	
	private double monthlyIncome;
	private double needsTotal;
	private double wantsTotal;
	private double savingsTotal;
	private double wholeTotal;
	
	//categories for needs section
	private double foodAmt;
	private double houseAmt;
	private double tranAmt;
	private double insureAmt;
	private double utilAmt;
	private double childAmt;
	
	//categories for wants section
	private double travelAmt;
	private double eatoutAmt;
	private double subAmt;
	private double clothAmt;
	private double hobAmt;
	private double entAmt;
	
	//categories for savings section
	private double emeFundAmt;
	private double retireAmt;
	private double debtAmt;
	
	public double getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public double getNeedsTotal() {
		return needsTotal;
	}
	public void setNeedsTotal(double needsTotal) {
		this.needsTotal = needsTotal;
	}
	public double getWantsTotal() {
		return wantsTotal;
	}
	public void setWantsTotal(double wantsTotal) {
		this.wantsTotal = wantsTotal;
	}
	public double getSavingsTotal() {
		return savingsTotal;
	}
	public void setSavingsTotal(double savingsTotal) {
		this.savingsTotal = savingsTotal;
	}
	public double getWholeTotal() {
		return wholeTotal;
	}
	public void setWholeTotal(double wholeTotal) {
		this.wholeTotal = wholeTotal;
	}
	public double getFoodAmt() {
		return foodAmt;
	}
	public void setFoodAmt(double foodAmt) {
		this.foodAmt = foodAmt;
	}
	public double getHouseAmt() {
		return houseAmt;
	}
	public void setHouseAmt(double houseAmt) {
		this.houseAmt = houseAmt;
	}
	public double getTranAmt() {
		return tranAmt;
	}
	public void setTranAmt(double tranAmt) {
		this.tranAmt = tranAmt;
	}
	public double getInsureAmt() {
		return insureAmt;
	}
	public void setInsureAmt(double insureAmt) {
		this.insureAmt = insureAmt;
	}
	public double getUtilAmt() {
		return utilAmt;
	}
	public void setUtilAmt(double utilAmt) {
		this.utilAmt = utilAmt;
	}
	public double getChildAmt() {
		return childAmt;
	}
	public void setChildAmt(double childAmt) {
		this.childAmt = childAmt;
	}
	public double getTravelAmt() {
		return travelAmt;
	}
	public void setTravelAmt(double travelAmt) {
		this.travelAmt = travelAmt;
	}
	public double getEatoutAmt() {
		return eatoutAmt;
	}
	public void setEatoutAmt(double eatoutAmt) {
		this.eatoutAmt = eatoutAmt;
	}
	public double getSubAmt() {
		return subAmt;
	}
	public void setSubAmt(double subAmt) {
		this.subAmt = subAmt;
	}
	public double getClothAmt() {
		return clothAmt;
	}
	public void setClothAmt(double clothAmt) {
		this.clothAmt = clothAmt;
	}
	public double getHobAmt() {
		return hobAmt;
	}
	public void setHobAmt(double hobAmt) {
		this.hobAmt = hobAmt;
	}
	public double getEntAmt() {
		return entAmt;
	}
	public void setEntAmt(double entAmt) {
		this.entAmt = entAmt;
	}
	public double getEmeFundAmt() {
		return emeFundAmt;
	}
	public void setEmeFundAmt(double emeFundAmt) {
		this.emeFundAmt = emeFundAmt;
	}
	public double getRetireAmt() {
		return retireAmt;
	}
	public void setRetireAmt(double retireAmt) {
		this.retireAmt = retireAmt;
	}
	public double getDebtAmt() {
		return debtAmt;
	}
	public void setDebtAmt(double debtAmt) {
		this.debtAmt = debtAmt;
	}
	
	public void calcNeeds() {
		setNeedsTotal(getFoodAmt()+getHouseAmt()+getTranAmt()+getInsureAmt()+getUtilAmt()+getChildAmt());
	}
	
	public void calcWants() {
		setWantsTotal(getTravelAmt()+getEatoutAmt()+getSubAmt()+getClothAmt()+getHobAmt()+getEntAmt());
	}
	
	public void calcSavings() {
		setSavingsTotal(getEmeFundAmt()+getRetireAmt()+getDebtAmt());
	}
	
	public void calcWholeTotal() {
		setWholeTotal(getNeedsTotal()+getWantsTotal()+getSavingsTotal());
	}
	
	Saver(){
		
	}
	
	
	

}
