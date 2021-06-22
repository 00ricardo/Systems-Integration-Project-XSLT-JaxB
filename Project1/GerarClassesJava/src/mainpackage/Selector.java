package mainpackage;


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

import generated.Researchgate;

public class Selector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File XMLfile = new File("project1Research.xml");
		
		Researchgate rg = jaxbXmlFileToObject(XMLfile);																			
		
		
		Researchgate rgNew = new Researchgate();
		ArrayList<String> instList = getListInstitutions(rg);
		ArrayList<String> skills = getSkills(rg);
		
		boolean done  = false;
		while(!done) {
			System.out.println("****************Selector****************");
			System.out.println("Perform selection by: ");
			System.out.println("(0) Institution ");
			System.out.println("(1) Skill ");
			System.out.println("(2) Institution and Skill ");
			System.out.println("(3) Exit ");
			
			int option = lerInt(0, 4);
			
			if (option == 0) {
				
				for (int i = 0; i<instList.size(); i++) {
					System.out.print("("+(i)+")");
					System.out.println(instList.get(i));
				}
				int option2 = lerInt(0,instList.size());
				String chosen_institution = instList.get(option2);
				
				rgNew = selectPersonByInstitution(rg, chosen_institution);
				jaxbObjectToXML(rgNew);
				done = true;
				
			}
			else if (option == 1) {
				
				ArrayList<String> chosenSkillsList = new  ArrayList<String>();
				
				System.out.println("Keep on selecting more skills until you're done.");
				int count=0;
				boolean done2 = false;
				while(!done2){
					
					int i=0;
					System.out.println("Select the option: ");
					for (i=0 ; i < skills.size(); i++) {
						System.out.print("("+(i)+")");
						System.out.println(skills.get(i));
					}
					int option3;
					if (count==0) {
						option3 = lerInt(0,skills.size());
					}
					else {
						System.out.println("("+(i)+")"+"I am done");
						option3 = lerInt(0,skills.size()+1);
						
					}
					if (option3==skills.size()) {
						done2 = true;
					}
					else {
						String chosenSkill = skills.get(option3);
						chosenSkillsList.add(chosenSkill);
						rgNew = selectPersonBySkill(rg, chosenSkill);
						
						skills = getSkills(rgNew);
						for (int j=0; j<chosenSkillsList.size(); j++) {
							skills.remove(chosenSkillsList.get(j));
						}
					}
					
					count++;
				}
					
				jaxbObjectToXML(rgNew);
				done = true;
				
			}
				
			else if (option == 2) {
				System.out.println("Select the institution: ");
				for (int i = 0; i<instList.size(); i++) {
					System.out.print("("+(i)+")");
					System.out.println(instList.get(i));
				}
				int option2 = lerInt(0,instList.size());
				String chosen_institution = instList.get(option2);
				
				rgNew = selectPersonByInstitution(rg, chosen_institution);		//rgNew only has a list of Person belonging to the desired institution
				
				//Selecting the Skills from the list of persons in a certain institution
				ArrayList<String> skills2 = getSkills(rgNew);
				for (int i = 0; i < skills2.size(); i++) {
					System.out.print("("+(i)+")");
					System.out.println(skills2.get(i));
				}
				int option3 = lerInt(0,skills2.size());
				String chosen_skill = skills2.get(option3);
				
				// creating a new empty Researchgate
				
				Researchgate rgNewNew = new Researchgate();
				
				rgNewNew = selectPersonBySkill(rgNew, chosen_skill);
				jaxbObjectToXML(rgNewNew);
				done = true;
			}
			else if (option == 3) {
				System.out.println("You have successfully exited the Selector.");
				done = true;
			}
		}	
	}
	
	
	private static Researchgate jaxbXmlFileToObject(File XMLfile) {
		/**
		 * reads the XMLfile to Java object 
		 * returns rg
		 */
		
		JAXBContext jc;
		Researchgate rg = new Researchgate();
		try {
			
			jc = JAXBContext.newInstance(Researchgate.class);
			// Create unnarshaller
            Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
            
            SchemaFactory schmFact = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File("schema.xsd");
            Schema schemaXSD;
			try {
				schemaXSD = schmFact.newSchema(schemaFile);
				jaxbUnmarshaller.setSchema(schemaXSD);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            
            rg = (Researchgate) jaxbUnmarshaller.unmarshal(XMLfile);

            
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return (rg);
	}
	
	private static void jaxbObjectToXML(Researchgate rg) 
    {
		/**
		 * converts Java Oject (rg) to XML file
		 */
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Researchgate.class);
             
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            //Required formatting
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
           //Store XML to File
            File file = new File("XMLafterSelector.xml");
             
            //Writes XML file to file-system
            jaxbMarshaller.marshal(rg, file); 
        } 
        catch (JAXBException e) 
        {
            e.printStackTrace();
        }
    }
	
	/**
	 * Metodo que so aceita a introducao de um inteiro
	 * @return int
	 */
	public static int lerInt(int init, int fin ) {	
		Scanner scan = new Scanner(System.in);
		int num = 0;
		boolean aux = true;
		while (aux) {
			if (scan.hasNextInt()) {
				num = scan.nextInt();
				if (num>=init && num< fin) {
					aux = false;
				}
				else {
					System.out.print("Invalid option. Try again: ");
				}
			}
			else {
				System.out.print("Invalid option. Try again: ");
				scan.next();
			}
		}
		return num;
	}
		
	/**
	 * 	This method returns a list with all the different universities
	 * @param rg
	 * @return
	 */
	public static ArrayList<String> getListInstitutions (Researchgate rg){
		
		ArrayList<String> institutions = new ArrayList<String>();
		for (int i = 0; i < rg.getPerson().size(); i++) {
			for (int  j = 0; j < rg.getPerson().get(i).getInstitution().size(); j++) {
				if (!institutions.contains(rg.getPerson().get(i).getInstitution().get(j).getInstName())) {
					institutions.add(rg.getPerson().get(i).getInstitution().get(j).getInstName());	
				}
			}
		}
		return institutions;	
	}
	
	/**
	 * Returns a Researchgate with only the Person objects that are associated to a certain institution
	 * @param rg
	 * @param institution
	 * @return rgNew
	 */
	public static Researchgate selectPersonByInstitution (Researchgate rg, String institution) {
		Researchgate rgNew = new Researchgate();
		
		for (int i = 0; i < rg.getPerson().size(); i++) {
			for (int  j = 0; j < rg.getPerson().get(i).getInstitution().size(); j++) {
				if (rg.getPerson().get(i).getInstitution().get(j).getInstName().equals(institution)) {
					rgNew.getPerson().add(rg.getPerson().get(i));
					j = rg.getPerson().get(i).getInstitution().size();
				}
			}
		}
		
		
		return rgNew;
		
	}
	/**
	 * This method returns a list with all the different skills
	 * @param rg
	 * @return
	 */
	public static ArrayList<String> getSkills (Researchgate rg){
		
		ArrayList<String> skills = new ArrayList<String>();
		for (int i = 0; i < rg.getPerson().size(); i++) {
			for (int  j = 0; j < rg.getPerson().get(i).getSkill().size(); j++) {
				if (!skills.contains(rg.getPerson().get(i).getSkill().get(j))) {
					skills.add(rg.getPerson().get(i).getSkill().get(j));	
				}
			}
		}
		return skills;	
	}
	
	/**
	 * Returns a Researchgate with only the Person objects that haver a certain skill
	 * @param rg
	 * @param skill
	 * @return rgNew
	 */
	public static Researchgate selectPersonBySkill (Researchgate rg, String skill) {
		
		Researchgate rgNew = new Researchgate();
		
		for (int i = 0; i < rg.getPerson().size(); i++) {
			for (int  j = 0; j < rg.getPerson().get(i).getSkill().size(); j++) {
				if (rg.getPerson().get(i).getSkill().get(j).equals(skill)) {
					rgNew.getPerson().add(rg.getPerson().get(i));
					j = rg.getPerson().get(i).getSkill().size();
				}
			}
		}
		
		return rgNew;
		
	}
	

}
