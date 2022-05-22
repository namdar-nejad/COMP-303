import java.util.ArrayList;

public class MovieScript implements Script{
    private ArrayList<String> lang = new ArrayList<String>();      // languages to look for
    private ArrayList<String> studio = new ArrayList<String>();    // studios to look for
    private int num = -1;                                          // number of movies
    private String tag = null;                             // movies that have this tag

    public MovieScript() {}

    public void setLang(ArrayList<String> lang){
        this.lang = new ArrayList<>(lang);
    }

    public ArrayList<String> getLangs(){
        return this.lang;
    }

    public void setStudio(ArrayList<String> studio){
        this.studio = new ArrayList<>(studio);
    }
    public ArrayList<String> getStudios(){
        return this.studio;
    }

    public void setNum(int num){
        this.num = num;
    }

    public int getNum(){
        return this.num;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
