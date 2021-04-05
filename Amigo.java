public class Amigo {

    private String login;

    public Amigo(String login){
        this.login = login;
    }

    @Override
    public String toString(){
        return "Usuario(a): " + login;
    }
}
