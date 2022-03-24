import java.util.*;

public class HumanResources  {

    static String staffID; //ID của nhân viên sẽ được thêm tự động khi ta thêm 1 nhân viên mới vào
    static int idx = 0;
    static ArrayList<Department> listDepartment = new ArrayList<>(); //listDepartment để chứa thông tin về các phòng ban
    public static void main (String[] args) {
        Scanner mainInput = new Scanner(System.in);
        System.out.println("CHÀO MỪNG BẠN ĐẾN VỚI PHẦN MỀM QUẢN LÝ NHÂN SỰ");
        System.out.println("Để bắt đầu chương trình, bạn phải nhập vào các phòng ban của công ty");
        System.out.print("Mời bạn nhập vào số lượng các phòng ban của công ty: ");
        int numberOfDepartment = mainInput.nextInt();
        String enter = mainInput.nextLine(); //Bắt kí tự enter thừa
        for (int i = 1; i <= numberOfDepartment; i++){
            System.out.println("Mời bạn nhập vào tên của phòng ban thứ " + i + ": ");
            String departmentName = mainInput.nextLine();
            String departmentID = departmentName.substring(0,3).toUpperCase(); //ID của phòng ban lấy 3 kí tự đầu của tên phòng ban
            Department department = new Department(departmentID, departmentName, 0);
            listDepartment.add(department); //Thêm thông tin phòng ban vào danh sách các phòng ban
        }
        ArrayList<Staff> listStaff = new ArrayList<>(); //listStaff để quản lý thông tin toàn bộ nhân viên bao gồm cả Employee và Manager
        int check; // Để người dùng lựa chon thao tác phù hợp
        String nextInput;
        //System.out.println(listStaff.size());

        //Vòng lặp do while để chạy lại menu cho người dùng lựa chọn thao tác
        do {
            // iN MENU
            printMenu();
            //Vòng lặp do while để ràng buộc người dùng chỉ được nhập vào các lựa chọn có ở menu
            do {
                check = mainInput.nextInt(); // Nhấn vào lựa chọn mà người dùng muốn chương trình thực hiện
                if (check < 0 || check > 8){
                    System.out.print("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
                }
            } while (check < 0 || check > 8);


            switch (check) {
                case 0: { // Thoát chương trình
                    break;
                }
                case 1: { // Thêm staff
                    do {
                        System.out.println("Mời bạn nhập vào lựa chọn");
                        System.out.println("1. Thêm nhân viên\t\t2. Thêm quản lý");

                        // Vòng lặp do while để ràng buộc người dùng chỉ được nhập 2 giá trị là 1 hoặc 2
                        do {
                            check =  mainInput.nextInt();
                            if (check != 1 && check != 2) {
                                System.out.print("Lựa chọn không đúng, vui lòng nhập lại: ");
                            }
                        } while (check != 1 && check != 2);

                        // Kiểm tra thông tin mà người dung nhập vào để xác định thêm nhân viên hay thêm quản lý
                        if (check == 1) {
                            // Thêm nhân viên
                            Employee newStaff = addNewStaff();
                            listStaff.add(newStaff);
                            //System.out.println(listStaff.size());
                            System.out.print("Bạn có muốn nhập thêm thông tin của nhân viên? Nhấn y để đồng ý, nhấn n để thoát: ");
                            enter = mainInput.nextLine(); // Bắt kí tự enter thừa
                            nextInput = mainInput.nextLine();
                        } else {
                            //Thêm quản lý
                            Manager newLeader = addNewLeader();
                            listStaff.add(newLeader);
                            //System.out.println(listStaff.size());
                            System.out.print("Bạn có muốn nhập thêm thông tin của nhân viên? Nhấn y để đồng ý, nhấn n để thoát: ");
                            enter = mainInput.nextLine(); // Bắt kí tự enter thừa
                            nextInput = mainInput.nextLine(); //Tiếp tục thêm nhân viên hay không
                        }
                    } while (nextInput.equalsIgnoreCase("y"));
                    break;
                }
                case 2: {//Hiển thị các bộ phận trong công ty
                    System.out.println("Thông tin các bộ phận hiện có trong công ty");
                    for (int i = 0; i < listDepartment.size(); i++){
                        System.out.println(listDepartment.get(i).toString());
                    }
                    break;
                }
                case 3: { //Hiển thị nhân viên hiện có trong công ty
                    System.out.println("Thông tin nhân viên hiện có trong công ty");
                    for (int i = 0; i < listStaff.size(); i++) {
                        listStaff.get(i).displayInformation();
                    }
                    break;
                }
                case 4: { //Hiển thị nhân viên theo từng bộ phận
                    System.out.println("Mời bạn chọn phòng ban muốn hiển thị");
                    //Vòng for để hiển thị các phòng ban của nhân viên cho người dùng lựa chọn
                    for (int i = 0; i < listDepartment.size(); i++){
                        System.out.print((i+1) + "." + listDepartment.get(i).departmentName + "\t");
                    }
                    System.out.println();
                    int select;
                    do {
                        select = mainInput.nextInt();
                        if (select < 1 || select > listDepartment.size()){
                            System.out.print("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
                        }
                    } while (select < 1 || select > listDepartment.size()); //Chỉ được lựa chọn theo danh mục menu
                    System.out.println("Danh sách nhân viên của phòng " + listDepartment.get(select-1).departmentName + " là: ");
                    // Duyệt qua danh sách nhân viên và in ra nhân viên của phòng ban tương ứng lựa chọn
                    for (int i = 0; i < listStaff.size(); i++) {
                        //if (listStaff.get(i).department == listDepartment.get(select-1).departmentName){ //Sao dùng dấu == vẫn được, kết quả vẫn đúng
                        if (listStaff.get(i).department.equals(listDepartment.get(select-1).departmentName)){
                            listStaff.get(i).displayInformation();
                        }
                    }
                    break;
                }
                case 5: { //Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên
                    String keySearch;
                    System.out.println("Mời bạn lựa chọn cách thức tìm kiếm");
                    System.out.println("1.Tìm kiếm theo tên\t\t2.Tìm kiếm theo mã nhân viên");
                    int search;
                    do {
                        search = mainInput.nextInt();
                        if (search != 1 && search != 2){
                            System.out.println("Lựa chọn không đúng, vui lòng chọn lại: ");
                        }
                    } while (search != 1 && search != 2);
                    if (search == 1){ //Tìm kiếm theo tên
                        System.out.print("Mời bạn nhập vào họ tên đầy đủ của nhân viên cần tìm kiếm: ");
                        enter = mainInput.nextLine(); //Bắt ki tự enter thừa
                        keySearch = mainInput.nextLine();
                        int checkStaffName = -1; //Biến trả về vị trí tên nhân viên cần tìm
                        for (int i = 0; i < listStaff.size(); i++) {
                            if (listStaff.get(i).getStaffName().equalsIgnoreCase(keySearch)){
                                listStaff.get(i).displayInformation();
                                checkStaffName = i;
                            }
                        }
                        if (checkStaffName == -1) {
                            System.out.println("Tên nhân viên không tồn tại, xin kiểm tra lại!");
                        }
                    } else { //Tìm kiếm theo mã nhân viên
                        System.out.println("Mời bạn nhập vào mã nhân viên cần tìm kiếm, ví dụ: VFM1, VFM2... ");
                        keySearch = mainInput.next();
                        int checkStaffID = -1; //Biến trả về vị trí mã nhân viên cần tìm
                        for (int i = 0; i < listStaff.size(); i++) {
                            if (listStaff.get(i).getStaffID().equalsIgnoreCase(keySearch)){
                                checkStaffID = i;
                                break;
                            }
                        }
                        if (checkStaffID == -1){
                            System.out.println("Mã nhân viên không tồn tại, xin kiểm tra lại!");
                        } else {
                            listStaff.get(checkStaffID).displayInformation();
                        }
                    }
                    break;
                }
                case 6: { //Hiển thị bảng lương của nhân viên toàn công ty theo thứ tự tăng dần
                    ArrayList<Staff> newListStaff= new ArrayList<>(); //Tạo Array List mới để xử lý, tránh ảnh hưởng đến list nhân viên hiện tại
                    newListStaff.addAll(listStaff); //Copy toàn bộ dữ liệu sang list mới để xử lý
                    sortAscendingSalary(newListStaff);
                    System.out.println("Bảng lương của công ty theo thứ tự tăng dần");
                    for (int i = 0; i < newListStaff.size(); i++) {
                        newListStaff.get(i).displayInformation();
                    }
                    //System.out.println(listStaff.size());
                    break;
                }
                case 7: { //Hiển thị bảng lương của nhân viên toàn công ty theo thứ tự giảm dần
                    ArrayList<Staff> newListStaff= new ArrayList<>(); //Tạo Array List mới để xử lý, tránh ảnh hưởng đến list nhân viên hiện tại
                    newListStaff.addAll(listStaff); //Copy toàn bộ dữ liệu sang list mới để xử lý
                    sortDescendingSalary(newListStaff);
                    System.out.println("Bảng lương của công ty theo thứ tự giảm dần");
                    for (int i = 0; i < newListStaff.size(); i++) {
                        newListStaff.get(i).displayInformation();
                    }
                    //System.out.println(listStaff.size());
                    break;
                }
                case 8: {
                    do {
                        System.out.println("Mời bạn nhập vào tên của phòng ban: ");
                        enter = mainInput.nextLine(); //Bắt kí tự enter thừa
                        String departmentName = mainInput.nextLine();
                        String departmentID = departmentName.substring(0,3).toUpperCase(); //ID của phòng ban lấy 3 kí tự đầu của tên phòng ban
                        Department department = new Department(departmentID, departmentName, 0);
                        listDepartment.add(department); //Thêm thông tin phòng ban vào danh sách các phòng ban
                        System.out.print("Bạn có muốn nhập thêm phòng ban của công ty? Nhấn y để đồng ý, nhấn n để thoát: ");
                        nextInput = mainInput.next(); //Tiếp tục thêm nhân viên hay không
                    } while (nextInput.equalsIgnoreCase("y"));

                }
            }
        } while (check != 0);
    }

    // Hàm thêm nhân viên bình thường
    public static Employee addNewStaff(){
        Scanner inputStaff = new Scanner(System.in);
        String staffName;
        int staffAge;
        double coefficientsSalary;
        String dateStartWork;
        String department;
        int offDay;
        int overtime;
        int checkDepartment;
        double staffSalary = 0;

        idx++;
        staffID = "VFM" + idx;
        System.out.print("Nhập vào tên nhân viên: ");
        staffName = inputStaff.nextLine();
        System.out.print("Nhập vào tuổi nhân viên " + staffName + ": ");
        staffAge = inputStaff.nextInt();
        System.out.print("Nhập vào hệ số lương của nhân viên " + staffName + ": ");
        coefficientsSalary = inputStaff.nextDouble();
        String enter = inputStaff.nextLine(); // Bắt kí tự enter thừa
        System.out.print("Nhập ngày vào làm của nhân viên " + staffName + " theo định dạng ngày / tháng / năm: ");
        dateStartWork = inputStaff.nextLine();
        System.out.println("Mời bạn lựa chọn bộ phận của nhân viên " + staffName + ": ");
        //Vòng for để hiển thị các phòng ban của nhân viên cho người dùng lựa chọn
        for (int i = 0; i < listDepartment.size(); i++){
            System.out.print((i+1) + "." + listDepartment.get(i).departmentName + "\t");
        }
        System.out.println();
        do {
            checkDepartment = inputStaff.nextInt();
            if (checkDepartment < 1 || checkDepartment > listDepartment.size()) {
                System.out.print("Lựa chọn không đúng, vui lòng nhập lại: ");
            }
        } while (checkDepartment < 1 || checkDepartment > listDepartment.size());
        listDepartment.get(checkDepartment-1).numberOfStaff++; //Khi chọn phòng ban cho nhân viên mới thì số lượng nhân viên của phòng ban đó sẽ tăng lên 1
        department = listDepartment.get(checkDepartment-1).departmentName; //Gán tên phòng ban tương ứng cho biến department
        System.out.print("Nhập vào số ngày nghỉ của nhân viên " + staffName + ": ");
        offDay = inputStaff.nextInt();
        System.out.print("Nhập vào số giờ làm thêm của nhân viên " + staffName + ": ");
        overtime = inputStaff.nextInt();
        Employee employee = new Employee(staffID, staffName, staffAge, coefficientsSalary, dateStartWork, department, offDay, overtime, staffSalary);
        employee.staffSalary = employee.calculateSalary();
        return employee;
    }

    //Hàm thêm nhân viên quản lý
    public static Manager addNewLeader(){
        Scanner inputLeader = new Scanner(System.in);
        String staffName;
        int staffAge;
        double coefficientsSalary;
        String dateStartWork;
        String department;
        int offDay;
        int checkDepartment;
        double staffSalary = 0;
        String poisition = "Leader";

        idx++;
        staffID = "VFM" + idx;
        System.out.print("Nhập vào tên quản lý: ");
        staffName = inputLeader.nextLine();
        System.out.print("Nhập vào tuổi quản lý " + staffName + ": ");
        staffAge = inputLeader.nextInt();
        System.out.print("Nhập vào hệ số lương của quản lý " + staffName + ": ");
        coefficientsSalary = inputLeader.nextDouble();
        String enter = inputLeader.nextLine(); // Bắt kí tự enter thừa
        System.out.print("Nhập ngày vào làm của quản lý " + staffName + " theo định dạng ngày / tháng / năm: ");
        dateStartWork = inputLeader.nextLine();
        System.out.println("Mời bạn lựa chọn bộ phận của quản lý " + staffName + ": ");
        //Vòng for để hiển thị các phòng ban của nhân viên cho người dùng lựa chọn
        for (int i = 0; i < listDepartment.size(); i++){
            System.out.print((i+1) + "." + listDepartment.get(i).departmentName + "\t");
        }
        System.out.println();
        do {
            checkDepartment = inputLeader.nextInt();
            if (checkDepartment < 1 || checkDepartment > listDepartment.size()) {
                System.out.print("Lựa chọn không đúng, vui lòng nhập lại: ");
            }
        } while (checkDepartment < 1 || checkDepartment > listDepartment.size());
        listDepartment.get(checkDepartment-1).numberOfStaff++; //Khi chọn phòng ban cho nhân viên mới thì số lượng nhân viên của phòng ban đó sẽ tăng lên 1
        department = listDepartment.get(checkDepartment-1).departmentName;
        System.out.print("Nhập vào số ngày nghỉ của quản lý " + staffName + ": ");
        offDay = inputLeader.nextInt();

        Manager leader = new Manager(staffID, staffName, staffAge, coefficientsSalary, dateStartWork, department, offDay, poisition, staffSalary);
        leader.staffSalary=leader.calculateSalary();
        return leader;
    }

    // Hàm để in ra menu các tính năng
    public static void printMenu (){
        System.out.println();
        System.out.println("==================== HUMAN RESOURCES PROGRAM ==========================");
        System.out.println("-------------------------------MENU------------------------------------");
        System.out.println("1.Thêm nhân viên mới vào công ty");
        System.out.println("2.Hiển thị các bộ phận trong công ty");
        System.out.println("3.Hiển thị nhân viên hiện có trong công ty");
        System.out.println("4.Hiển thị nhân viên theo từng bộ phận");
        System.out.println("5.Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên");
        System.out.println("6.Hiển thị bảng lương của nhân viên toàn công ty theo thứ tự tăng dần");
        System.out.println("7.Hiển thị bảng lương của nhân viên toàn công ty theo thứ tự giảm dần");
        System.out.println("8. Thêm phòng ban của công ty");
        System.out.println("0.Thoát chương trình");
        System.out.println("========================================================================");
        System.out.println("Mời bạn lựa chọn thao tác muốn thực hiện: ");
    }

    // Hàm sắp xếp nhân viên theo mức lương tăng dần
    public  static void sortAscendingSalary (ArrayList<Staff> listStaff){
        //System.out.println("Sắp xếp tăng dần");
        for (int i = 0; i < listStaff.size(); i++){
            //System.out.printf("i = %d\n", i);
            for (int j = i + 1; j < listStaff.size(); j++){
                //System.out.println("Lương thứ " + i + " trước là:"+listStaff.get(i).staffSalary);
                //System.out.println("Lương thứ " + j + "trước là:"+listStaff.get(j).staffSalary);
                if (listStaff.get(i).staffSalary > listStaff.get(j).staffSalary){
                    Staff a = listStaff.get(i);
                    listStaff.set(i, listStaff.get(j));
                    listStaff.set(j, a);
                }
                //System.out.println("Lương thứ " + i + " sau là:"+listStaff.get(i).staffSalary);
                //System.out.println("Lương thứ " + j + "sau là:"+listStaff.get(j).staffSalary);
            }
        }
    }

    // Hàm sắp xếp nhân viên theo mức lương giảm dần
    public  static void sortDescendingSalary (ArrayList<Staff> listStaff){
        for (int i = 0; i < listStaff.size(); i++){
            for (int j = i + 1; j < listStaff.size(); j++){
                if (listStaff.get(i).staffSalary < listStaff.get(j).staffSalary){
                    Staff a = listStaff.get(i);
                    listStaff.set(i, listStaff.get(j));
                    listStaff.set(j, a);
                }
            }
        }
    }
}
