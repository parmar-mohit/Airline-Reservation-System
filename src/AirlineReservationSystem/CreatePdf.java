package AirlineReservationSystem;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.sql.ResultSet;

public class CreatePdf {
    public CreatePdf(int flight_id,String username,String firstname,String lastname,String gender,int age,String seat,String email,String contactNo) throws Exception {
        DatabaseCon db = new DatabaseCon();
        ResultSet result = db.executeQuery("SELECT * FROM flight_schedule WHERE flight_id="+flight_id+";");
        result.next();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));
        document.open();
        PdfPTable table = new PdfPTable(2);

        table.addCell("Flight Id");table.addCell(flight_id+"");
        table.addCell("Boarding Date");table.addCell(result.getDate("boarding_date")+"");
        table.addCell("From");table.addCell(result.getString("source"));
        table.addCell("To");table.addCell(result.getString("destination"));
        table.addCell("username");table.addCell(username);
        table.addCell("Passenger Name");table.addCell(firstname+" "+lastname);
        table.addCell("Gender");table.addCell(gender);
        table.addCell("Age");table.addCell(age+"");
        table.addCell("Seat");table.addCell(seat);
        table.addCell("Email");table.addCell(email);
        table.addCell("Contact No");table.addCell(contactNo);

        document.add(table);
        document.close();
    }
}
