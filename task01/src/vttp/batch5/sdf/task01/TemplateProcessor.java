package vttp.batch5.sdf.task01;


import vttp.batch5.sdf.task01.models.BikeEntry;
import java.util.ArrayList;

public class TemplateProcessor {
    
    private String TEMPLATE = "The <position> recorded number of cyclists was in <season>, on a <day> in the month of <month>.\nThere were a total of <total> cyclist. The weather was <weather>.\n<day> was <holiday>.";
    private final String[] POSITION = {"highest"
                                        ,"second highest"
                                        ,"third highest"
                                        ,"fourth highest"
                                        ,"fifth highest"};

    public final String[] WEATHER = { "Clear, Few clouds, Partly cloudy, Partly cloudy" 
                                            , "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist"
                                            , "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds"
                                            , "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog" };
    


    public void printFilled(ArrayList<BikeEntry> topEntries) {
        
        System.out.println("");

        for (int b = 0; b < topEntries.size(); b++) {

            String template = TEMPLATE;
            // Replace each variable with bike entries values
            template = template.replace("<position>", toPosition(b));
            template = template.replace("<season>", Utilities.toSeason(topEntries.get(b).getSeason()));
            template = template.replace("<day>", Utilities.toWeekday(topEntries.get(b).getWeekday()));
            template = template.replace("<month>", Utilities.toMonth(topEntries.get(b).getMonth()));
            template = template.replace("<total>", String.valueOf(topEntries.get(b).getCasual() + topEntries.get(b).getRegistered()));
            template = template.replace("<weather>", toWeather(topEntries.get(b).getWeather()));
            template = template.replace("<holiday>", toHoliday(topEntries.get(b).isHoliday()));

            System.out.println(template);
            System.out.println("");
        }
        
    }

    private String toPosition(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return POSITION[position];
            default:
                return "incorrect position";
        }
    }

    private String toWeather(int weatherCode) {
        switch (weatherCode) {
            case 1:
            case 2:
            case 3:
            case 4:
                return WEATHER[weatherCode - 1];
            default:
                return "incorrect weather";
        }
    }

    private String toHoliday(boolean isHoliday) {
        if (isHoliday) {
            return "a holiday";
        }
        else {
            return "not a holiday";
        }
    }
}
