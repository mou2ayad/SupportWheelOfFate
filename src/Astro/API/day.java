package Astro.API;


// day class
public class day extends baseClass {
	// first shift in the day (day shift)
	private Shift firstShift;
	public Shift getFirstShift() {
		return firstShift;
	}
	public void setFirstShift(Shift firstShift) {
		this.firstShift = firstShift;
	}
	// second shift in the day (night shift)
    private Shift secondShift;
    public Shift getSecondShift() {
		return secondShift;
	}
	public void setSecondShift(Shift secondShift) {
		this.secondShift = secondShift;
	}
	
	// next day in case current day is not the last day(means not day number 10)
	private day nextDay;
	public day getNextDay() {
		return nextDay;
	}
	public void setNextDay(day nextDay) {
		this.nextDay = nextDay;
	}
	
	// previous day in case current day is not the first day(means not day number 1)
	private day preDay;
	public day getPreDay() {
		return preDay;
	}
	public void setPreDay(day preDay) {
		this.preDay = preDay;
	}

    
    public day(int daynumber)
    {
        this.Id = daynumber;
    }
	
}
