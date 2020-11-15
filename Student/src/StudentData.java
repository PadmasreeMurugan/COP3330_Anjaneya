public class StudentData {
    private String name;
    private String grade;

    //constructor
    public StudentData(String name, String grade) {

        if(isNameValid(name)) {
            this.name = name;
        } else {
            throw new InvalidNameException("name is not valid; must be at least 1 character long");
        }

        if(isGradeValid(grade)) {
            this.grade = grade;
        } else {
            throw new InvalidGradeException("grade not valid; must be A, B, C, D, E, or F");
        }
    }

    //checking if name is valid
    private boolean isNameValid(String name) {
        return name.length() > 0;
    }

    //checking if grade is valid
    private boolean isGradeValid(String grade) {
        return grade.equalsIgnoreCase("A") ||
                grade.equalsIgnoreCase("B") ||
                grade.equalsIgnoreCase("C") ||
                grade.equalsIgnoreCase("D") ||
                grade.equalsIgnoreCase("F");
    }

    //getting name
    public String getName() {
        return this.name;
    }

    //getting grade
    public String getGrade() {
        return this.grade;
    }
}

class InvalidNameException extends IllegalArgumentException {
    public InvalidNameException(String msg) {
        super(msg);
    }
}

class InvalidGradeException extends IllegalArgumentException {
    public InvalidGradeException(String msg) {
        super(msg);
    }
}
