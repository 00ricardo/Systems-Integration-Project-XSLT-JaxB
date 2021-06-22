<?xml version="1.0" encoding="UTF-8"?>
<html xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xsl:version="1.0">
	<head>
		<title>ResearchGate</title>
	</head>
	<body>
		<h1 >Researchers ordered by skill</h1>
			<xsl:for-each select="/skillsCatalog">
				<h2>Statistics</h2>
				<dt>Total number of citations: <xsl:value-of select="totalCitationsNr"/></dt>
				<dt>Total number of researchers: <xsl:value-of select="totalNrResearchesrs"/></dt>
				<dt>Top Researchers: </dt>
					<ol>
				 	<xsl:for-each select="topNResearchers/researcherName">
				  		<li> <xsl:value-of select="."/> </li>
				 	</xsl:for-each>
					 </ol>
				<xsl:for-each select="skillExpertise">
					<h2>Skill: <xsl:value-of select="skillName"/></h2>
						<ul>
						<xsl:for-each select="person">
						<dl>
		  					<dt><h4><b>Researcher's Name: <xsl:value-of select="personName"/></b></h4></dt> 
		  						<ul style="list-style-type:square;">
		 						
				 					<dt>Institution: </dt>
				 						<xsl:for-each select="institution">
				  							<ul style="list-style-type:square;">
				  								<li> <xsl:value-of select="instName"/>, <xsl:value-of select="department"/>, <xsl:value-of select="position"/> </li>
				 							</ul>
				 						</xsl:for-each>
				 						
				 					<dt>Introduction: <xsl:value-of select="introduction"/></dt>
				 						
				 					<dt>Skills: </dt>
				 						<xsl:for-each select="skill">
				  							<ul style="list-style-type:square;">
				  								<li> <xsl:value-of select="."/> </li>
				 							</ul>
				 						</xsl:for-each>
				 					<dt>Reads: <xsl:value-of select="reads"/></dt>
				 					<dt>Citations: <xsl:value-of select="citations"/></dt>
				 					
				 					<dt>Cited</dt>
				 						<xsl:for-each select="cited">
				  							<ul style="list-style-type:square;">
				  								<li> <xsl:value-of select="personName"/>, <xsl:value-of select="instName"/> </li>
				 							</ul>
				 						</xsl:for-each>
				 					<dt>Cited By</dt>
				 						<xsl:for-each select="citedby">
				  							<ul style="list-style-type:square;">
				  								<li> <xsl:value-of select="personName"/>, <xsl:value-of select="instName"/> </li>
				 							</ul>
				 						</xsl:for-each>
				 					<dt>Followers</dt>
				 						<xsl:for-each select="followers">
				  							<ul style="list-style-type:square;">
				  								<li> <xsl:value-of select="personName"/>, <xsl:value-of select="instName"/> </li>
				 							</ul>
				 						</xsl:for-each>
				 					<dt>Following</dt>
				 						<xsl:for-each select="following">
				  							<ul style="list-style-type:square;">
				  								<li> <xsl:value-of select="personName"/>, <xsl:value-of select="instName"/> </li>
				 							</ul>
				 						</xsl:for-each>
				 					
				 					<dt>Research Experience: </dt>
				 						<xsl:for-each select="researchExperience">
				  							<ul style="list-style-type:square;">
				  								<li> Date: <xsl:value-of select="datei"/> to <xsl:value-of select="datef"/> </li>
				  								<li> Institution: <xsl:value-of select="instName"/> </li>
				  								<li> Departament: <xsl:value-of select="department"/> </li>
				  								<li> Address: <xsl:value-of select="address"/> </li>
				  								<li> Description: <xsl:value-of select="description"/> </li>
				 							</ul>
				 						</xsl:for-each>
				 					
				 					<dt>List of Projects</dt>
				 						<xsl:for-each select="project">
				 							<ul style="list-style-type:square;">
				 								<li> Project:  </li>
						  							<ul style="list-style-type:square;">
						  								<li> Name: <xsl:value-of select="projectName"/> </li>
						  								<li> State: <xsl:value-of select="state"/></li>
						  								<li> Description: <xsl:value-of select="description"/></li>
						 							
						 							</ul>
				 							</ul>
				 						</xsl:for-each>
				 					
				 					<dt>Research: </dt>
				 						<xsl:for-each select="research">
				  							<ul style="list-style-type:square;">
				  							<li> Research:  </li>
				  								<ul style="list-style-type:square;">
				  									<li> Research: <xsl:value-of select="researchName"/> </li> 
				  									<li> Type: <xsl:value-of select="type"/> </li>
				  									<li> Date: <xsl:value-of select="date"/> </li>
				  									<li> Authors: <xsl:for-each select="author">, <xsl:value-of select="."/> </xsl:for-each> </li>
				  									<li> Description: <xsl:value-of select="description"/> </li>
				  								</ul>
				 								</ul>
				 						</xsl:for-each>
				 						
				 					<dt>Co-authors</dt>
				 						<xsl:for-each select="coauthor">
				 							<ul style="list-style-type:square;">
				 								<li><xsl:value-of select="personName"/>, <xsl:value-of select="instName"/></li>
				 							</ul>
				 						</xsl:for-each>
		 						</ul>
		 					</dl>
		 				</xsl:for-each>
		 				</ul>		 		
					</xsl:for-each>
					
			</xsl:for-each>
	</body>
</html>