import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

//creating a class
public class StudentList
{
    List<StudentData> students;

    // Student List is an ordered sequence of StudentData indexed by an integer value from 0 to n
    public StudentList() {

        students = new ArrayList<>();
    }

    //adding an individual student grade and name to list of students
    public void add(StudentData data)
    {

        students.add(data);
    }

    public void remove

    public void write(String filename) {
        try(Formatter output = new Formatter(filename)) {
            for(int i = 0; i < students.size(); i++) {
                StudentData data = students.get(i);
                output.format("%s;%s%n", data.getName(), data.getGrade());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

