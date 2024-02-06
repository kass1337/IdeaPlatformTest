
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class  Parser {
    private final List<TicketMapping> tickets;
    public Parser(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filename));
        Type ticketListType = new TypeToken<FileMapping>() {}.getType();
        FileMapping allTickets = gson.fromJson(reader, ticketListType);
        this.tickets = allTickets.getTickets();
    }

    public final List<TicketMapping> getTickets() {
        return tickets;
    }
}
