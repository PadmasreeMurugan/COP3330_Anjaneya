public class StudentData {
    private String name;
    private String grade;

    public StudentData(String name, String grade) {
        if (isNameValid(name)) {
            this.name = name;
        } else {
            throw new InvalidNameException("name is not valid");
        }


        if (isGradeValid(grade)) {
            this.grade = grade;
        } else {
            throw new InvalidGradeException("Grade not valid, must be A, B, C, D, E or F");

        }

    }

    private boolean isNameValid(String name)
    {
        return name.length() > 0;
    }

    private boolean isGradeValid(String grade) {
        return grade.equalsIgnoreCase("A") ||
                grade.equalsIgnoreCase("B") ||
                grade.equalsIgnoreCase("C") ||
                grade.equalsIgnoreCase("D") ||
                grade.equalsIgnoreCase("F");
    }

    public String getName() {
        return this.name;
    }

    public String getGrade() {
        return this.grade;
    }
}

    class InvalidNameException extends IllegalArgumentException
    {
        public InvalidNameException(String msg)
        {
            super(msg);
        }

    }

class InvalidGradeException extends IllegalArgumentException
{
    public InvalidGradeException(String msg)
    {
        super(msg);
    }

}

