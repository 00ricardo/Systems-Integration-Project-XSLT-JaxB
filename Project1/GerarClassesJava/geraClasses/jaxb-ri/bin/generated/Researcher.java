//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.25 at 05:05:32 PM WEST 
//


package generated;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}researchername"/&gt;
 *         &lt;element ref="{}institution"/&gt;
 *         &lt;element ref="{}department" minOccurs="0"/&gt;
 *         &lt;element ref="{}position"/&gt;
 *         &lt;element ref="{}rgscore" minOccurs="0"/&gt;
 *         &lt;element ref="{}academicqualifications"/&gt;
 *         &lt;element ref="{}about"/&gt;
 *         &lt;element ref="{}network" minOccurs="0"/&gt;
 *         &lt;element ref="{}projects" minOccurs="0"/&gt;
 *         &lt;element ref="{}research" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "researchername",
    "institution",
    "department",
    "position",
    "rgscore",
    "academicqualifications",
    "about",
    "network",
    "projects",
    "research"
})
@XmlRootElement(name = "researcher")
public class Researcher {

    @XmlElement(required = true)
    protected String researchername;
    @XmlElement(required = true)
    protected String institution;
    protected String department;
    @XmlElement(required = true)
    protected String position;
    protected BigDecimal rgscore;
    @XmlElement(required = true)
    protected String academicqualifications;
    @XmlElement(required = true)
    protected About about;
    protected Network network;
    protected Projects projects;
    protected Research research;

    /**
     * Gets the value of the researchername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResearchername() {
        return researchername;
    }

    /**
     * Sets the value of the researchername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResearchername(String value) {
        this.researchername = value;
    }

    /**
     * Gets the value of the institution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Sets the value of the institution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitution(String value) {
        this.institution = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartment(String value) {
        this.department = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the rgscore property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRgscore() {
        return rgscore;
    }

    /**
     * Sets the value of the rgscore property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRgscore(BigDecimal value) {
        this.rgscore = value;
    }

    /**
     * Gets the value of the academicqualifications property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicqualifications() {
        return academicqualifications;
    }

    /**
     * Sets the value of the academicqualifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicqualifications(String value) {
        this.academicqualifications = value;
    }

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link About }
     *     
     */
    public About getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link About }
     *     
     */
    public void setAbout(About value) {
        this.about = value;
    }

    /**
     * Gets the value of the network property.
     * 
     * @return
     *     possible object is
     *     {@link Network }
     *     
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * Sets the value of the network property.
     * 
     * @param value
     *     allowed object is
     *     {@link Network }
     *     
     */
    public void setNetwork(Network value) {
        this.network = value;
    }

    /**
     * Gets the value of the projects property.
     * 
     * @return
     *     possible object is
     *     {@link Projects }
     *     
     */
    public Projects getProjects() {
        return projects;
    }

    /**
     * Sets the value of the projects property.
     * 
     * @param value
     *     allowed object is
     *     {@link Projects }
     *     
     */
    public void setProjects(Projects value) {
        this.projects = value;
    }

    /**
     * Gets the value of the research property.
     * 
     * @return
     *     possible object is
     *     {@link Research }
     *     
     */
    public Research getResearch() {
        return research;
    }

    /**
     * Sets the value of the research property.
     * 
     * @param value
     *     allowed object is
     *     {@link Research }
     *     
     */
    public void setResearch(Research value) {
        this.research = value;
    }

}