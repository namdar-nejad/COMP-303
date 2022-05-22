import java.util.Iterator;

public class EpisodeScript implements Script{
    private TvShow show = null;                 // from a certain TvShow
    private int num = -1;                       // number of episodes
    private String tag = null;                  // movies that have this tag

    public EpisodeScript(TvShow show) {
        this.show = show;
    }

    public TvShow getShow(){
        return this.show;
    }

    public void setNum(int num){
        this.num = num;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public int getNum() {
        return num;
    }

    public String getTag() {
        return tag;
    }
}