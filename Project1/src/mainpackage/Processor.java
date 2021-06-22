package mainpackage;

import generatedclass.Author;
import generatedclass.Authorp;
import generatedclass.Book;
import generatedclass.Bookdepository;
import generatedclass.Bookp;
import generatedclass.Listofauthors;
import generatedclass.Statistics;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


/**
 *
 * @author david
 */
public class Processor {
    
    public static void main(String[] args) {
        int nAuthors;
        if(args.length == 0){
            System.out.println("Please, insert the number of authots name that you want see on the processor statistics: ");
            nAuthors = readOption();
        }else
            nAuthors = Integer.parseInt(args[0]);
        try {
            processorInterface(nAuthors);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    /**
     * Runs the Processor
     * @param numberOfAuthors that have to be sorted by bestsellers rank scores
     * @throws Exception 
     */
    public static void processorInterface(int numberOfAuthors) throws Exception{
        Bookdepository listOfBooks;
        try {
            listOfBooks = Selector.jaxbXmlFileToObject(new File("BookdepositoryAfterSelector.xml"));
        } catch (Exception ex) {
            throw new Exception("Exception[Processor]: Impossible to convert XML file into java objects");
        }
        Listofauthors listofAuthors = reorganizeInformation(listOfBooks, numberOfAuthors);
        jaxbObjectToXmlFileProcessor(listofAuthors);
    }
  
    
    /**
     * Convert a Listofauthors object into a XML File
     * @param listOfAuthors List of authors with books sorted by Bestseller Rank
     */
    private static void jaxbObjectToXmlFileProcessor(Listofauthors listOfAuthors) throws Exception{
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(Listofauthors.class); 
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File("listofauthors.xml");         
            //Writes the listofauthors object to the XML file created befores
            jaxbMarshaller.marshal(listOfAuthors, file); 
        } 
        catch (JAXBException e) 
        {
            throw new Exception("Exception[Processor]: Impossible to convert java objects into XML files");
        }
    }
    
    /**
     * Reorganize the information from the list of books reorganize by author and sorted by bestseller rank and add the statistics 
     * @param listOfBooks with the books information
     * @param numberOfAuthors that have to be added to the statistics 
     * @return a listofauthors with the information reorganize by author and sorted by bestseller rank
     */
    private static Listofauthors reorganizeInformation(Bookdepository listOfBooks, int numberOfAuthors){
        Listofauthors newInformationList = new Listofauthors();
        Statistics statistics = new Statistics();
        ArrayList<String> auxAuthors = new ArrayList<>();
        ArrayList<Integer> auxBestSellerRank = new ArrayList<>();
        
        // Arrange Authors and books information
        for (Book book : listOfBooks.getBook()) {
            Bookp newBook = transformBookToBookp(book);
            for (Author author : book.getAuthor()) {
                Authorp newAuthor = transformAuthortoAuthorp(author);
                if(!newInformationList.getAuthorp().contains(newAuthor)){ //If the new list don't contains the author
                    // Add the book to the new author
                    newAuthor.getBookp().add(newBook);
                    // Add the new author to the new list
                    newInformationList.getAuthorp().add(newAuthor);
                } else { // The list already contains the author
                    for (Authorp authorp : newInformationList.getAuthorp()) {
                        if(authorp.getName().contains(author.getAuthorName()))
                            authorp.getBookp().add(newBook);     
                    }
                }
            }
        }
        
        // Sort Books by Bestseller Ranking
        for (Authorp authorp : newInformationList.getAuthorp()) {
            authorp.getBookp().sort(new Comparator<Bookp>(){
                @Override
                public int compare(Bookp book1, Bookp book2) {
                    int bestSellerRank1 = book1.getSellerRank().intValue();
                    int besrSellerRank2 = book2.getSellerRank().intValue();
                    return bestSellerRank1 - besrSellerRank2; // Ascending Sorted
                }
        });
        }
      
        // Set Statistics Information
        statistics.setTotalauthorsprocessed(BigInteger.valueOf(newInformationList.getAuthorp().size()));
        for (Authorp authorp : newInformationList.getAuthorp()){
            auxAuthors.add(authorp.getName());
            auxBestSellerRank.add(authorp.getBookp().get(0).getSellerRank().intValue());
        }
        for(int i=0; i<auxAuthors.size(); i++){ //Sort Authors Names by asceding bestseller rank
            for(int j=0; j<auxAuthors.size(); j++){
                if(auxBestSellerRank.get(j) > auxBestSellerRank.get(i)){
                    int tmpSellerRank = auxBestSellerRank.get(i);
                    String tmpAuthorName = auxAuthors.get(i);
                    auxBestSellerRank.set(i, auxBestSellerRank.get(j));
                    auxBestSellerRank.set(j, tmpSellerRank);
                    auxAuthors.set(i, auxAuthors.get(j));
                    auxAuthors.set(j, tmpAuthorName);
                } 
            }
        }
        for(int i=0; i<numberOfAuthors && i<auxAuthors.size(); i++) //Adding the authors names into the statistics
            statistics.getAuthorName().add(auxAuthors.get(i));
        newInformationList.setStatistics(statistics);
        
        return newInformationList;
    }
        
    /**
     * Convert a Author object into a Authorp object
     * @param author object to convert
     * @return Authorp object
     */
    private static Authorp transformAuthortoAuthorp(Author author){
        Authorp newAuthor = new Authorp();
        newAuthor.setName(author.getAuthorName());
        newAuthor.setAuthorDescription(author.getAuthorDescription());
        return newAuthor;
    }

    /**
     * Convert a Book object into a Bookp object
     * @param book object to convert
     * @return Bookp object
     */
    private static Bookp transformBookToBookp(Book book) {
        Bookp newBook = new Bookp();
        newBook.setBookSummary(book.getBookSummary());
        newBook.setBookTitle(book.getBookTitle());
        newBook.setBookInitials(book.getBookInitials());
        newBook.setCategorie(book.getCategorie());
        newBook.setISBN10(book.getISBN10());
        newBook.setISBN13(book.getISBN13());
        newBook.setLanguage(book.getLanguage());
        newBook.setLocationCity(book.getLocationCity());
        newBook.setLocationCountry(book.getLocationCountry());
        newBook.setPagesNumber(book.getPagesNumber());
        newBook.setPublicationDate(book.getPublicationDate());
        newBook.setPublisher(book.getPublisher());
        newBook.setRating(book.getRating());
        newBook.setSellerRank(book.getSellerRank());
        newBook.setLinkImg(book.getLinkImg());
        return newBook;
    }
    
    /**
     * Read a int from stdin and return them
     * @return opt
     */
    private static int readOption(){
        int opt=0;
        System.out.print("Insert your option: ");
        Scanner scan = new Scanner(System.in);
	boolean aux = true;
	while (aux) {
            if (scan.hasNextInt()) {
		opt = scan.nextInt();
                aux = false;
            }
            else {
                System.out.print("Invalid option. Try again: ");
                scan.next();
            }
	}
        return opt;
    }
}
