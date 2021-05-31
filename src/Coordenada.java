import java.util.Objects;

public class Coordenada {
    private byte x, y;

    public Coordenada(byte x,byte y)throws Exception{
        if(x<0||y<0)
            throw new Exception();
        this.x=x;
        this.y=y;
    }

    public byte getX() {
        return x;
    }
    public byte getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x+";"+y+')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordenada)) return false;
        Coordenada that = (Coordenada) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Coordenada(Coordenada modelo)throws Exception{
        if (modelo==null) throw new Exception();
        this.x = modelo.x;
        this.y = modelo.y;
    }

    public Coordenada clone(){
        Coordenada ret = null;

        try{
            ret = new Coordenada( this);
        }
        catch (Exception ignored){ }

        return ret;
    }
}