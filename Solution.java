
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Solution {
    private final List<TicketMapping> tickets;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

    public Solution() throws FileNotFoundException {
        Parser parser = new Parser("tickets.json");
        tickets = parser.getTickets();
    }
    public HashMap<String, Long> minTripTime() {
        HashMap<String, Long> carrierTripTimeFromVVOtoTLV = new HashMap<String, Long>();
        for (TicketMapping ticket : this.tickets) {
            if(ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                Long newTripTime = calcTripTime(ticket.getDeparture_date() + " " + ticket.getDeparture_time(),
                        ticket.getArrival_date() + " " + ticket.getArrival_time()); //время в полете у каждого перевозчика
                if(carrierTripTimeFromVVOtoTLV.containsKey(ticket.getCarrier())){
                    if(carrierTripTimeFromVVOtoTLV.get(ticket.getCarrier()) > newTripTime){
                        carrierTripTimeFromVVOtoTLV.put(ticket.getCarrier(), newTripTime);
                    }
                }
                else {
                    carrierTripTimeFromVVOtoTLV.put(ticket.getCarrier(), newTripTime);
                }
            }
        }
        return carrierTripTimeFromVVOtoTLV;
    }
    public Double differenceAvgMedian() {
        List<Double> prices = new ArrayList<>();
        Double avg;
        Double median;
        Double sum = 0.0;
        for (TicketMapping ticket : this.tickets) {
            if(ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                Double price = ticket.getPrice();
                prices.add(price);
                sum += price;
            }
        }
        Collections.sort(prices); // O(nlogn)
        if (prices.size() % 2 == 0) {

            median = (prices.get(prices.size() / 2) + prices.get(prices.size() / 2  - 1)) / 2; //полусумма рядом стоящих если четное количество
        }
        else {
            median = prices.get(prices.size() / 2);
        }
        avg = sum / prices.size();
    return avg - median;
    }
    private Long calcTripTime(String departureTime, String arrivalTime) {
        LocalDateTime departure = LocalDateTime.parse(departureTime, formatter);
        LocalDateTime arrival = LocalDateTime.parse(arrivalTime, formatter);
        return ChronoUnit.MINUTES.between(departure, arrival);
    }

}
