import java.util.ArrayList;

public class Manager extends Staff implements ICalculator{
    public static final int MANAGER_BASIC_SALARY = 5000000; // Lương cơ bản của quản lý
    String position;

    public Manager(String staffID, String staffName, int staffAge, double coefficientsSalary, String dateStartWork, String department, int offDay, String position, double staffSalary) {
        super(staffID, staffName, staffAge, coefficientsSalary, dateStartWork, department, offDay, staffSalary);
        this.position = position;
        //staffSalary = calculateSalary();
    }

    @Override
    public void displayInformation() {
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
        System.out.printf("\tSalary: %.0f",staffSalary);
        System.out.printf("\tPoisition: %s\n", position);
    }

    @Override
    public double calculateSalary() {
        double salary = getCoefficientsSalary() * MANAGER_BASIC_SALARY;
        double resSalary; // Lương trách nhiệm
        if (super.department.equalsIgnoreCase("Business")) {
            resSalary = 8000000;
        } else if (super.department.equalsIgnoreCase("Project")) {
            resSalary = 5000000;
        } else if (super.department.equalsIgnoreCase("Technical")){
            resSalary = 6000000;
        } else {
            resSalary = 7000000; //Lương trách nhiệm cho quản lý cuả các phòng ban khác với 3 phòng trên
        }
        return salary + resSalary;
    }
}
