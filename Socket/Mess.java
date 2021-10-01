package Socket;

public class Mess {

    private String name;
    private String mess;

    public Mess(String name, String mess)
    {
        this.name = name;
        this.mess = mess;

    }

    public void setName(String name)
    {
        this.name = name;
    }
    public  void setMess(String mess)
    {
        this.mess = mess;
    }

    public String getName()
    {
        return this.name;
    }
    public  String getMess()
    {
        return this.mess;
    }

    public String create_mess(Mess metadata)
    {
        this.name = metadata.getName();
        this.mess = metadata.getMess();
        return this.name + ": " + this.mess;
    }
}
