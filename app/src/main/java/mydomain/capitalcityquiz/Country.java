package mydomain.capitalcityquiz;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "countries")
public class Country implements Parcelable {

    @DatabaseField (id = true)
    private String countryName;
    @DatabaseField
    private String capitalName;
    @DatabaseField
    private String continent;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public Country (){
        //This no-args constructor is required to implement ORMLite
    }

    public Country (String countryName, String capitalName, String continent){

        this.countryName = countryName;
        this.capitalName = capitalName;
        this.continent = continent;
    }

    private Country (Parcel in){
        this.countryName = in.readString();
        this.capitalName = in.readString();
        this.continent = in.readString();
    }

    public String getCountryName (){
        return countryName;
    }

    public String getCapitalName (){
        return capitalName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.countryName);
        dest.writeString(this.capitalName);
        dest.writeString(this.continent);
    }


}
