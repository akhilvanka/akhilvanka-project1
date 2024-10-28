import java.util.ArrayList;

public class TransferStation extends Station {
    public ArrayList<Station> otherStations; // Make public for Project1_Tester

    public TransferStation(String line_color, String station_name){
        super(line_color, station_name);
        this.otherStations = new ArrayList<Station>();
    }

    public void addTransferStationNext(Station station) {
        if (station == null) {
            return;
        }

        this.otherStations.add(station);
        station.prev = this;
    }

    public void addTransferStationPrev(Station station) {
        if (station == null) {
            return;
        }

        this.otherStations.add(station);
        station.next = this;
    }


    public String toString() {
        String result = "TRANSFER" + super.toString() + "\n\tTransfers: \n";
        for (Station station : this.otherStations) {
            result += "\t" + station.toString() + "\n";
        }
        return result;
    }
}
