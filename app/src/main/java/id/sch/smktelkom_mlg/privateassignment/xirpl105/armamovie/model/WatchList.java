package id.sch.smktelkom_mlg.privateassignment.xirpl105.armamovie.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Moklet on 5/14/2017.
 */

public class WatchList extends SugarRecord implements Serializable {
    public String title;
    public String overview;
    public int color;

    public WatchList() {
    }

    public WatchList(String title, String overview, int color) {
        this.title = title;
        this.overview = overview;
        this.color = color;
    }
}
