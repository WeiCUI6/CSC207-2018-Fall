public class Student extends Person {
    private final int studentNumber;

    public int getStudentNumber() {
        return studentNumber;
    }

    /**
     * A person at the UofT named n with UTORid id.
     *
     * @param id the person's UTORid
     * @param n  the person's name
     */
    public Student(String id, String[] n, int studentNum) {
        super(id, n);
        this.studentNumber = studentNum;
    }
}
