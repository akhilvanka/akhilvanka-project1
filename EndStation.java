public class EndStation extends Station {
    public EndStation(String line_color, String station_name){
        super(line_color, station_name);
    }

    public void makeEnd() {
        if (this.getNextStation() != null) {
            this.addPrev(this.getNextStation());
        } else if (this.getPrevStation() != null) {
            this.addNext(this.getPrevStation());
        }
    }

    public String toString() {
        String original = super.toString();
        return "END" + original;
    }
}
