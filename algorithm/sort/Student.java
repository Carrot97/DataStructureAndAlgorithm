package sort;

import java.io.Serializable;

public class Student implements Serializable {
    static final long serialVersionUID = 463252114342L;

    private String name;
    private int group;

    public Student() {
    }

    public Student(String name, int group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return  name + "  " +group;
    }
}
