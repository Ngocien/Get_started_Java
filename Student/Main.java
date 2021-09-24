package Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.*;
import java.util.List;
import java.nio.file.Files;


class Student
{
    private String id;
    private String name;
    private int age;

    public Student(String id,String n, int a)
    {
        this.id= id;
        this.name= n;
        this.age =a;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getId()
    {
        return id;
    }

    public void setName(String n)
    {
        this.name = n;
    }
    public void setAge(int a)
    {
        this.age = a;

    }
    public void setId(String id)
    {
        this.id = id;
    }
}


public class Main {
    public static List<Student> read_csv() {
        List<Student> student = new ArrayList<>();
        Path pathToFile = Paths.get("src/Student/data.csv");
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line =br.readLine();
            while (line != null)
            {
                String[] values = line.split(",");
                Student t = createStudent(values);
                student.add(t);
                line= br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }


    private static Student createStudent(String[] metadata) {
        String id = metadata[0];
        String name = metadata[1];
        int age = Integer.parseInt(metadata[2]);
        return new Student(id,name,age);
    }


    static class Frame implements ActionListener
    {
        JFrame frame = new JFrame();
        JButton insert = new JButton("Insert");
        JButton remove = new JButton("Remove");
        JButton change = new JButton("Change");

        JLabel text= new JLabel("hello");
        List<Student>  students = read_csv();
        Frame()
        {
            setFrame();
//            show_text();
            add_button();
            //add_insert();
        }

        public void setFrame()
        {
            frame.setVisible(true);
            frame.setSize(700, 400);
            frame.setTitle("Menu");
            frame.getContentPane().setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void add_button()
        {
            insert.setBounds(50, 50, 100, 30);
            remove.setBounds(50, 150, 100, 30);
            change.setBounds(50,250, 100, 30);

            insert.addActionListener(this);
            remove.addActionListener(this);
            change.addActionListener(this);

            frame.add(insert);
            frame.add(remove);
            frame.add(change);
        }

        public void show_text()
        {
            int y_ = 180;
            for (Student i:students)
            {
                JLabel name = new JLabel("name" + i.getName());
                JLabel age = new JLabel("age" + i.getAge());

                name.setBounds(50, y_, 100, 30);
                age.setBounds(150, y_, 50, 30);
                frame.add(name);
                frame.add(age);

                y_ += 30;
            }
        }

        public void add_insert()
        {
            JTextField name = new JTextField();
            JTextField age = new JTextField();
            JLabel insert_name = new JLabel("Name");
            JLabel insert_age = new JLabel("Age");
            JButton submit = new JButton("OK");

            insert_name.setBounds(170, 20,100,30);
            name.setBounds(170,50,100,30);
            insert_age.setBounds(310,20,100,30);
            age.setBounds(310,50,100, 30);
            submit.setBounds(420, 50, 70, 30);
            frame.add(insert_name);
            frame.add(insert_age);
            frame.add(name);
            frame.add(age);
            frame.add(submit);

            frame.revalidate();

            submit.addActionListener(e ->
            {
                String id = "SV";
                String temp = "";
                for (Student i: students)
                    temp = i.getId().substring(2, i.getId().length());

                id = id + (Integer.parseInt(temp) +1 );
                String n = name.getText();
                int a = Integer.parseInt(age.getText());
                if (a > 0 & a < 100)
                {
                    Student t = new Student(id ,n, a);
                    students.add(t);
                    try
                    {
                        write_file(students);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    name.setText("");
                    age.setText("");
                }
            });
        }

        public void add_change()
        {
            JTextField new_name = new JTextField();
            JTextField new_age = new JTextField();
            ArrayList<String> choice = new ArrayList<String>();
            for (Student i: students)
            {
                choice.add(i.getId());
            }
            JComboBox combo = new JComboBox(choice.toArray());


            JLabel id = new JLabel("Choose id");
            JLabel name = new JLabel("New name");
            JLabel age = new JLabel("New age");

            JButton submit = new JButton("OK");

            id.setBounds(170,220, 70, 30);
            combo.setBounds(170,250,70,30);
            name.setBounds(310,220,100, 30);
            new_name.setBounds(290,250,100,30);
            age.setBounds(420,220, 100, 30);
            new_age.setBounds(400, 250, 100, 30);
            submit.setBounds(510, 250, 100, 30);

            frame.add(id);
            frame.add(name);
            frame.add(age);
            frame.add(new_name);
            frame.add(new_age);
            frame.add(combo);
            frame.add(submit);
            frame.revalidate();
            submit.addActionListener(e->
            {
                String temp = (String)combo.getSelectedItem();
                assert temp != null;
                for (Student i : students) {
                    int t = Integer.parseInt(new_age.getText());
                    if (!i.getId().equals(temp) || t <= 0 || t >= 100) {
                        continue;
                    }
                    i.setName(new_name.getText());
                    i.setAge(t);
                    break;

                }
                try
                {
                    write_file(students);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                new_name.setText("");
                new_age.setText("");
                combo.setSelectedItem(0);
            });

        }

        public void add_remove()
        {
            ArrayList<String> choice = new ArrayList<String>();
            for (Student i: students)
            {
                choice.add(i.getId());
            }
            JComboBox combo = new JComboBox(choice.toArray());
            JButton submit = new JButton("OK");


            combo.setBounds(170, 150, 100, 30);
            submit.setBounds(290, 150,100, 30);

            frame.add(combo);
            frame.add(submit);

            submit.addActionListener(e->
            {
                String choose = (String)combo.getSelectedItem();
                for (Student i: students)
                {
                    if (i.getId().equals(choose))
                    {
                        i.setId("");
                        i.setName("");
                        i.setAge(0);
                        break;
                    }
                }
                try {
                    write_file(students);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                combo.revalidate();
            });

                                                                                                                            

        }

        public void write_file(List<Student> student) throws FileNotFoundException {
            PrintWriter writer = new PrintWriter("src/Student/data.csv");
            for (Student i : student) {
                if (!i.getId().equals("") && !i.getName().equals(""))
                    writer.println(i.getId().toString() + "," + i.getName().toString() + "," + i.getAge());
            }
            writer.close();
        }



        @Override
        public void actionPerformed(ActionEvent e)
        {

            text.setBounds(50, 150, 100, 30);
            if (e.getSource() == insert)
            {
                add_insert();
            }

            else if (e.getSource() == remove)
            {
                add_remove();
            }
            else if (e.getSource() == change)
            {
                add_change();
            }
            frame.add(text);




        }

    }

    public static void main(String[] args)
    {
        new Frame();
    }

}
