package academic.model;

/**
 * @author NIM Nama
 */

public class Course {
    private String code;
    private String name;
    private int credit;
    private String passingGrade;

    public Course(String code, String name, int credit, String passingGrade) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
    }

    //buatlah getter dan setter
    public String getCode() {
        return code;
    }
    //getter setter name
    public String getName() {
        return name;
    }
    //getter setter credit
    public int getCredit() {
        return credit;
    }
    //getter setter passingGrade
    public String getPassingGrade() {
        return passingGrade;
    }


    @Override
    public String toString() {
        return code + "|" + name + "|" + credit + "|" + passingGrade;
    }

    //buat comparator
    
}

