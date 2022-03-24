public class Employee extends Staff implements ICalculator{
    public static final int EMPLOYEE_BASIC_SALARY = 3000000; // Lương cơ bản của nhân viên
    int overtime;

    public Employee(String staffID, String staffName, int staffAge, double coefficientsSalary, String dateStartWork, String department,int offDay, int overtime, double staffSalary) {
        super(staffID, staffName, staffAge, coefficientsSalary, dateStartWork, department, offDay, staffSalary);
        this.overtime = overtime;
        //staffSalary = calculateSalary();

    }

    @Override
    public double calculateSalary(){
        //Nhân viên: Hệ số lương * 3,000,000 + số giờ làm thêm * 200,000
        double salary = getCoefficientsSalary() * EMPLOYEE_BASIC_SALARY + 200000 * overtime;
        return salary;
    }

    // Hiển thị thông tin nhân viên
    @Override
    public void displayInformation(){
        /*
        System.out.printf("ID: %s", staffID);
        System.out.printf("\tName: %18s", staffName);
        System.out.printf("\t Age: %d", staffAge);
        System.out.printf("\tDepartment: %15s", department);
        System.out.printf("\t Date start work: %15s", dateStartWork);
        */
        String info = "ID: " + getStaffID()
                    + "\t Name: " + getStaffName()
                    + "\t Age: " + getStaffAge()
                    + "\t Department: " + department
                    + "\t Date start work: " + dateStartWork
                    + "\t";
        System.out.print(info);
        System.out.printf("\t Salary: %.0f", staffSalary);
        System.out.printf("\t OT: %d\n", overtime);
    }
}
