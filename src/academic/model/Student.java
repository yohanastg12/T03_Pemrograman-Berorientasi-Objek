package academic.model;


public class Student {
    private String id;
    private String name;
    private String year;
    private String studyProgram;

   
         public Student(String id, String name, String year, String studyProgram) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
    //getter setter untuk id
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }

  
    public boolean equals(Student student) {
        return id.equals(student.getId());
    }
}