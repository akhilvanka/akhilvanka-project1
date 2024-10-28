import java.util.ArrayList;

public class Station {
    protected String line_color; 
    protected String station_name;
    protected Station next; 
    protected Station prev;
    protected Boolean service_availability;

    public Station(String line_color, String station_name){
        this.line_color = line_color;
        this.station_name = station_name;
        this.next = null;
        this.prev = null;
        this.service_availability = true;
    }

    public String getLineColor(){
        return this.line_color;
    }

    public String getStationName(){
        return this.station_name;
    }

    public Station getNextStation(){
        return this.next;
    }

    public Station getPrevStation(){
        return this.prev;
    }

    public void addNext(Station next_station){
        this.next = next_station;
        if (next.prev == null) {
            next.prev = this;
        }
    }

    public void addPrev(Station prev_station){
        this.prev = prev_station;
        if (prev.next == null) {
            prev.next = this;
        }
    }


    // Connect two stations together as primaries 
    public void connect(Station other) {
        if (other == null) {
            return;
        }
 
        this.addNext(other);
        other.addPrev(this);
    }

    public Boolean isAvailable(){
        return this.service_availability;
    }
    
    public void switchAvailable() {
        this.service_availability = !this.service_availability;
    }

    public String toString(){
        return "STATION " + station_name + ": " + line_color + " line, in service: " + (service_availability ? "true" : "false") + ", previous station: " + (prev == null ? "none": prev.getStationName()) + ", next station: " + (next == null ? "none": next.getStationName());
    }

    public Boolean equals(Station other){
        return this.line_color.equals(other.getLineColor()) && this.station_name.equals(other.getStationName());
    }

    public int tripLength(Station other) {
        ArrayList<Station> visitedStations = new ArrayList<Station>();
        return tripLength(other, visitedStations);
    }

    private int tripLength(Station other, ArrayList<Station> visitedStations) {
        if (this.equals(other)) {
            return 0;
        }
        visitedStations.add(this);
        if (this.next != null && !visitedStations.contains(this.next)) {
            int nextLength = this.next.tripLength(other, visitedStations);
            if (nextLength != -1) {
                return 1 + nextLength;
            }
        }

        if (this instanceof TransferStation) {
            for (Station station : ((TransferStation)this).otherStations) {
                if (!visitedStations.contains(station)) {
                    int nextLength = station.tripLength(other, visitedStations);
                    if (nextLength != -1) {
                        return 1 + nextLength;
                    }
                }
            }
        }
        return -1;
    }
}
