public abstract class Staff implements ICalculator{



    //Thuộc tính: mã nhân viên, tên nhân viên, tuổi nhân viên, hệ số lương, ngày vào làm, bộ phận làm việc, số ngày nghỉ phép, lương nhân viên
    private String staffID;
    private String staffName;
    private int staffAge;
    private double coefficientsSalary;
    String dateStartWork;
    String department;
    int offDay;
    public double staffSalary;

    // Constructor của Staff
    public Staff (String staffID, String staffName, int staffAge, double coefficientsSalary, String dateStartWork, String department, int offDay, double staffSalary){
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffAge = staffAge;
        this.coefficientsSalary = coefficientsSalary;
        this.dateStartWork = dateStartWork;
        this.department = department;
        this.offDay = offDay;
        this.staffSalary = staffSalary;
    }

    abstract void displayInformation(); // Hàm in ra thông tin nhân viên
    // Hàm lấy giá trị StaffID
    public String getStaffID(){
        return staffID;
    }
    // Hàm lấy giá trị StaffName
    public String getStaffName(){
        return staffName;
    }
    // Hàm lấy giá trị StaffAge
    public int getStaffAge(){
        return staffAge;
    }
    // Hàm lấy giá trị CoefficientsSalary (hệ số lương)
    public double getCoefficientsSalary(){
        return coefficientsSalary;
    }

    public void in(){
        System.out.println("Hello");
    }
}
