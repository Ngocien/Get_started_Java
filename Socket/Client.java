package Socket;

class Client {
    private String name;
    private String pass;

    public Client(String name, String pass)
    {
        this.name= name;
        this.pass= pass;
    }

    public void setName(String n)
    {
        this.name = n;
    }
    public void setPass(String p)
    {
        this.pass = p;
    }

    public String getName()
    {
        return name;
    }
    public  String getPass()
    {
        return pass;
    }

    public static Client createClient(String[] metadata)
    {
        String name = metadata[0];
        String pass= metadata[1];
        return new Client(name, pass);
    }
}
