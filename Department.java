public class Department {
    //mã bộ phận, tên bộ phận, số lượng nhân viên hiện tại
    String departmentID;
    String departmentName;
    int numberOfStaff;

    //Constructor của Department
    public Department (String departmentID, String departmentName, int numberOfStaff) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.numberOfStaff = numberOfStaff;
    }
    @Override
    public String toString() {
        /*
        String department = "Department: " + departmentName
                            + "\t\tDepartment ID: " + departmentID
                            + "\tNumber of staff: " + numberOfStaff;
        System.out.print(department);
        */
        String department1 = String.format("Department: %15s", departmentName);
        String department2 = "\t\tDepartment ID: " + departmentID;
        String department3 = "\t\tNumber of staff: " + numberOfStaff;
        return department1 + department2 + department3;
    }
}
