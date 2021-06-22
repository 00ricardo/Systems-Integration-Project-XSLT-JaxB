
package mainpackage;

import generatedclass.Author;
import generatedclass.Book;
import generatedclass.Bookdepository;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author david
 */
public class Selector {
    
    // Interface Menu Options
    private final static String searchOptions = "0 - Exit\n" 
            + "1 - Stop Searching\n"
            + "2 - Search by Author's Name,\n"
            + "3 - Search by minimum publication Year\n"
            + "4 - Search by Book Title\n"
            + "5 - Search by minimum Rating\n"
            + "6 - Search by maximum Bestsellers Rank\n"
            + "7 - Seach by Book Categorie\n";
    
    private final static int MAXIMUM_MENU_OPTION = 7;   // Maximum menu option allowed
    private final static int MINIMUM_MENU_OPTION = 0;   // Minimum menu option allowed
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {

        try {
            selectorInterface();
        } catch (ExitException ex) {
            System.exit(0);
        } catch (NoSearchResultException ex) {
            System.out.println("The search result returns zero books! Please, repeat the selector execution.");
        } catch (JAXBException ex) {
            System.out.println("Exception: Impossble to convert java objects into a bookdepository XML file");
        } catch (SAXException ex) {
            System.out.println("Exception: Impossble to convert Bookdepository XML file into java objects.");
        }
    }
    
    /**
     * 
     * @throws ExitException
     * @throws NoSearchResultException if the search returns zero books
     * @throws JAXBException
     * @throws SAXException 
     */
    public static void selectorInterface() throws ExitException, NoSearchResultException, JAXBException, SAXException{
        File XMLFileBookDepository = new File("bookdepository.xml");        
        Bookdepository bookdepository = jaxbXmlFileToObject(XMLFileBookDepository);
        ArrayList<Integer> readMenuOptions = new ArrayList<>();
        ArrayList<String> readSearchOptions = new ArrayList<>();
        Boolean stopReadFromStdin = true;
        Boolean noCommandsInput = true;
        
        // Read the search preferences from the user
        while(stopReadFromStdin){
            // Print the interface Menu on the console
            printMenu();
            // Read the users options
            int opt = Selector.readOption(MINIMUM_MENU_OPTION, MAXIMUM_MENU_OPTION);
            switch(opt){
                case 0:
                    throw new ExitException("Exit Exception");
                case 1:
                    if(noCommandsInput)
                        throw new ExitException("Exit Exception");
                    stopReadFromStdin = false;
                    break;
                case 2:
                    readMenuOptions.add(opt);
                    System.out.print("Insert the Author's Name: ");
                    readSearchOptions.add(readString());
                    if(noCommandsInput)
                        noCommandsInput = false;
                    break;
                case 3:
                    readMenuOptions.add(opt);
                    System.out.print("Insert the minimum publication Year: ");
                    readSearchOptions.add(readString());
                    if(noCommandsInput)
                        noCommandsInput = false;
                    break;
                case 4:
                    readMenuOptions.add(opt);
                    System.out.print("Insert the Book Title: ");
                    readSearchOptions.add(readString());
                    if(noCommandsInput)
                        noCommandsInput = false;
                    break;
                case 5:
                    readMenuOptions.add(opt);
                    System.out.print("Insert the minimum Rating: ");
                    readSearchOptions.add(Double.toString(readDoubleNumber(0, 5)));
                    if(noCommandsInput)
                        noCommandsInput = false;
                    break;
                case 6:
                    readMenuOptions.add(opt);
                    System.out.print("Insert the maximum Bestsellers Rank: ");
                    readSearchOptions.add(readString());
                    if(noCommandsInput)
                        noCommandsInput = false;
                    break;
                case 7:
                    readMenuOptions.add(opt);
                    System.out.print("Insert the Book Categorie: ");
                    readSearchOptions.add(readString());
                    if(noCommandsInput)
                        noCommandsInput = false;
                    break;
                default:
                    break;
            }
        }
        //Do the Search
        bookdepository = processUserPreferrences(readMenuOptions, readSearchOptions, bookdepository);
        if(bookdepository.getBook().isEmpty())  //If the result of the seatch returns zero results
            throw new NoSearchResultException();
        else
            jaxbObjectToXmlFile(bookdepository);  
    }
    
    /**
     * Convert a XML file into an Bookdepository object
     * @param XMLFile - XML File
     * @return a bookdepository object with the information read from the XML file
     */
    public static Bookdepository jaxbXmlFileToObject(File XMLFile) throws JAXBException, SAXException{
        
        Bookdepository bookdepository = new Bookdepository();
        JAXBContext jc;
        Unmarshaller jaxbUnmarshaller = null;
        SchemaFactory schmFact = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schemaXSD = null;
        
        try {
            jc = JAXBContext.newInstance(Bookdepository.class);
            jaxbUnmarshaller = jc.createUnmarshaller();
            schemaXSD = schmFact.newSchema(new File("bookdepositoryXSD.xsd"));
            jaxbUnmarshaller.setSchema(schemaXSD);
            //Unmarshal the XML
            bookdepository = (Bookdepository) jaxbUnmarshaller.unmarshal(XMLFile);
        } catch (JAXBException ex) {
            throw ex;
        } catch (SAXException ex) {
            throw ex;
        }

        return bookdepository;
    }
    
    /**
     * Convert a Bookdepository object into a XML File
     * @param depository Book depository with the books processed
     */
    private static void jaxbObjectToXmlFile(Bookdepository depository) throws JAXBException{
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bookdepository.class); 
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File("BookdepositoryAfterSelector.xml");         
            //Writes the bookdepository object to the XML file created befores
            jaxbMarshaller.marshal(depository, file); 
        } 
        catch (JAXBException e) 
        {
            throw e;
        }
    }
    
    /**
     * Print the interface menu 
     */
    private static void printMenu(){
        System.out.println(searchOptions);
    }
    
    /**
     * Read an int (option menu) from stdin
     * @param minimumOption allowed 
     * @param maximumOptiona allowed
     * @return an validate int 
     */
    private static int readOption(int minimumOption, int maximumOption){
        int opt=0;
        System.out.print("Insert your option: ");
        Scanner scan = new Scanner(System.in);
	boolean aux = true;
	while (aux) {
            if (scan.hasNextInt()) {
		opt = scan.nextInt();
                if (opt>=minimumOption && opt<= maximumOption)
                    aux = false;
                else 
                    System.out.print("Invalid option. Try again: ");
            }
            else {
                System.out.print("Invalid option. Try again: ");
                scan.next();
            }
	}
        return opt;
    }
    
    /**
     * Read an double from stdin
     * @param min allowed
     * @param max allowed
     * @return an validate double
     */
    private static double readDoubleNumber(double min, double max){
        double number=0;
        System.out.print("Insert your number: ");
        Scanner scan = new Scanner(System.in);
	boolean aux = true;
	while (aux) {
            if (scan.hasNextDouble()) {
		number = scan.nextDouble();
                if (number>=min && number<= max)
                    aux = false;
                else 
                    System.out.print("Invalid number. Try again: ");
            }
            else {
                System.out.print("Invalid number. Try again: ");
                scan.next();
            }
	}
        return number;
    }
    
    /**
     * Read a String from stdin
     * @return a string
     */
    private static String readString(){
        Scanner sc= new Scanner(System.in); 
        String str = sc.nextLine();
        return str; 
    }
    
    /**
     * Process the user search preferences and return the new books collection
     * @param options 
     * @param optionsDescription
     * @return the new books collection in a Bookdepository object
     */
    private static Bookdepository processUserPreferrences(ArrayList<Integer> options, ArrayList<String> optionsDescription, Bookdepository books){
        Bookdepository newBookDepository = new Bookdepository();
        Boolean firstTimeFlag = true;
        
        for (int i = 0; i < options.size(); i++) {
            switch(options.get(i)){
                case 2: // By Author Name
                    if(firstTimeFlag == true){ // If is the first search condition
                        for (Book book : books.getBook()) {
                            for (Author author : book.getAuthor()) {
                                if(author.getAuthorName().contains(optionsDescription.get(i))){
                                    newBookDepository.getBook().add(book);
                                    firstTimeFlag = false;
                                }
                            }
                        }
                    } else {  // If is not the first search condition
                        ArrayList<Book> auxBooks = new ArrayList<>();
                        for (Book book : newBookDepository.getBook()) {
                            for (Author author : book.getAuthor()) {
                                // if not contains the author, the book is to remove
                                if(!author.getAuthorName().contains(optionsDescription.get(i))){
                                    auxBooks.add(book);
                                }
                            }
                        }
                        for (Book auxBook : auxBooks) {
                            newBookDepository.getBook().remove(auxBook);
                        }
                    }
                    break;
                case 3:  // By minimum publication Year
                    if(firstTimeFlag == true){  // If is the first search condition
                        for (Book book : books.getBook()) {
                            if(book.getPublicationDate().getYear() >= Integer.parseInt(optionsDescription.get(i))){
                                newBookDepository.getBook().add(book);
                                firstTimeFlag = false;
                            }
                        }
                    } else { // If is not the first search condition
                        ArrayList<Book> auxBooks = new ArrayList<>();
                        for (Book book : newBookDepository.getBook()) {
                            // If contains books that not respect the users search, the books must be removed
                            if(book.getPublicationDate().getYear() < Integer.parseInt(optionsDescription.get(i))){
                                auxBooks.add(book);
                            }
                        }
                        for (Book auxBook : auxBooks) {
                            newBookDepository.getBook().remove(auxBook);
                        }
                    }
                    break;
                case 4:  // By book title
                    if(firstTimeFlag == true){  // If is the first search condition
                        for (Book book : books.getBook()) {
                            if(book.getBookTitle().contains(optionsDescription.get(i))){
                                newBookDepository.getBook().add(book);
                                firstTimeFlag = false;
                            }
                        }
                    } else {   // If is not the first search condition
                        ArrayList<Book> auxBooks = new ArrayList<>();
                        for (Book book : newBookDepository.getBook()) {
                            // if not contains the title, the book is to remove
                            if(!book.getBookTitle().contains(optionsDescription.get(i))){
                                auxBooks.add(book);
                            }
                        }
                        for (Book auxBook : auxBooks) { 
                            newBookDepository.getBook().remove(auxBook);
                        }
                    }
                    break;
                case 5:  // By minimum Rating
                    if(firstTimeFlag == true){  // If is the first search condition
                        for (Book book : books.getBook()) {
                            if(Double.parseDouble(book.getBookRank()) < Double.parseDouble(optionsDescription.get(i))){
                                newBookDepository.getBook().add(book);
                                firstTimeFlag = false;
                            }
                        }
                    } else { // If is not the first search condition
                        ArrayList<Book> auxBooks = new ArrayList<>();
                        for (Book book : newBookDepository.getBook()) {
                            // If contains books that not respect the users search, the books must be removed
                            if(Double.parseDouble(book.getBookRank()) < Double.parseDouble(optionsDescription.get(i))){
                                auxBooks.add(book);
                            }
                        }
                        for (Book auxBook : auxBooks) {
                            newBookDepository.getBook().remove(auxBook);
                        }
                    }
                    break;
                case 6:   // By maximum Bestsellers Rank
                    if(firstTimeFlag == true){  // If is the first search condition
                        for (Book book : books.getBook()) {
                            if(book.getSellerRank().intValue() <= Integer.parseInt(optionsDescription.get(i))){
                                newBookDepository.getBook().add(book);
                                firstTimeFlag = false;
                            }
                        }
                    } else { // If is not the first search condition
                        ArrayList<Book> auxBooks = new ArrayList<>();
                        for (Book book : newBookDepository.getBook()) {
                            // If contains books that not respect the users search, the books must be removed
                            if(book.getSellerRank().intValue() > Integer.parseInt(optionsDescription.get(i))){
                                auxBooks.add(book);
                            }
                        }
                        for (Book auxBook : auxBooks) {
                            newBookDepository.getBook().remove(auxBook);
                        }
                    }
                    break;
                case 7:   // By book categorie
                    if(firstTimeFlag == true){  // If is the first search condition
                        for (Book book : books.getBook()) {
                            if(book.getCategorie().contains(optionsDescription.get(i))){
                                newBookDepository.getBook().add(book);
                                firstTimeFlag = false;
                            }
                        }
                    } else {   // If is not the first search condition
                        ArrayList<Book> auxBooks = new ArrayList<>();
                        for (Book book : newBookDepository.getBook()) {
                            // if not contains the title, the book is to remove
                            if(!book.getCategorie().contains(optionsDescription.get(i))){
                                auxBooks.add(book);
                            }
                        }
                        for (Book auxBook : auxBooks) { 
                            newBookDepository.getBook().remove(auxBook);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return newBookDepository;
    }
}
