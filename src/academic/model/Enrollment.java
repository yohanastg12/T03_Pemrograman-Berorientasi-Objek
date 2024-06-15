package academic.model;

/**
 * @author 12S22050 YOHANA CHRISTINE SITANGGANGG
 */
public class Enrollment {

    private String code;  
    private String id;
    private String academicYear;
    private String semester;
    private String grade = "None"; 
    

    //construction  
    public Enrollment(String _code, String _id, String _academicYear, String _semester) {
        code = _code;
        id = _id;
        academicYear = _academicYear; 
        semester = _semester;
    }

    // get code
    public String getCode() {
        return code;
    }

    // get id
    public String getId() {
        return id;
    }

    // get academic year
    public String getAcademicYear() {
        return academicYear;
    }

    // get semester
    public String getSemester() {
        return semester;
    }

    // get grade
    public String getGrade() {
        return grade;
    }

    // set grade
    public void setGrade(String _grade) {
        grade = _grade;
    }

    @Override
    public String toString() {
        // <code>|<id>|<academicYear>|<semester>|<grade>
        return code + "|" + id + "|" + academicYear + "|" + semester + "|" + grade ;
    }

//buat comparator
    public boolean equals(Enrollment enrollment) {
        return code.equals(enrollment.getCode());

    }

}