package mydomain.capitalcityquiz.Utils;

import java.util.List;

import mydomain.capitalcityquiz.Country;

public class CountryListManager implements CountryListManagerInterface{

    private List<Country> list;

    public CountryListManager (){ }

    public List<Country> getList() {
        return list;
    }

    public void setList(List<Country> list) {
        this.list = list;
    }

    public void removeCountry(Country country){
        for (Country c : list){
            if (country.equals(c)){
                list.remove(c);
                break;
            }
        }
    }
}
