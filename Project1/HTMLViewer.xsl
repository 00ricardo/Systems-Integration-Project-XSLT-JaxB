<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <meta charset="utf-8" />
            <script src='https://kit.fontawesome.com/a076d05399.js'></script>
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
            <link rel="stylesheet" href="./style.css"/>
            <head>
                <title>BookDepository’s - Project 1</title>
            </head>
            <body>
                <h2>My BookDepository’s</h2>
                <div id="sucess-alert" class="alert alert-success" role="alert" style="opacity: 0.9;" >
                    <h6 class="alert-heading">Well done!</h6>
                    <p style= "font-size:13px" >
                        The research was completed sucessfully!<br></br>
                        <xsl:for-each select="listofauthors/statistics">
                            Total authors processed: <xsl:value-of select="totalauthorsprocessed"/>  <br></br>
                        </xsl:for-each>
                        <a class="alert-success" data-toggle="collapse"  href="#Copyright" >  
                            Auhor's names 
                        </a>
                    </p>
                    <ul id= "Copyright" class="collapse" style= "font-size:13px">  
                        <xsl:for-each select="listofauthors/statistics/authorName">
                            <li>
                                <xsl:value-of select="."/>
                            </li>
                        </xsl:for-each>
                    </ul>  
                </div>
                <div>
                    <xsl:for-each select="listofauthors/authorp">
                        <div class="card mb-3" style="max-width: 1000px;">
                            <div class="row no-gutters">
                                <div class="col-md-4">        
                                        <a class="text-muted" data-toggle="collapse">
                                            <xsl:attribute name="href">
                                                #a<xsl:value-of select="bookp/bookInitials"/> 
                                            </xsl:attribute>                                         
                                            Author:   
                                            <xsl:value-of select="name"/>                                                                                                                                                                        
                                        </a>                                            
                                        <xsl:for-each select="bookp">
                                        <div>        
                                            <button class="btn-sm btn-outline-info" type="button" data-toggle="collapse" aria-expanded="false" aria-controls="bookInfo">                                                                                                                                                                                                       
                                                <xsl:attribute  name="data-target">
                                                     #<xsl:value-of select="bookInitials"/> 
                                                </xsl:attribute>                                       
                                                <xsl:value-of select="bookTitle"/>
                                            </button>   
                                        </div>                                        
                                        </xsl:for-each>
                                </div>
                                <div class="col-md-8">
                                    <div class="collapse">
                                        <xsl:attribute name="id">a<xsl:value-of select="bookp/bookInitials"/></xsl:attribute> 
                                        <h5 class="card-title">Author Description</h5>
                                        <p class="card-text">                                          
                                            <xsl:value-of select="authorDescription"/>
                                        </p>
                                    </div>
                                    <xsl:for-each select="bookp"> 
                                    <div class="collapse"> 
                                        <xsl:attribute name="id">
                                            <xsl:value-of select="bookInitials"/> 
                                        </xsl:attribute>                                 
                                        <hr></hr>
                                            <div>
                                                <img>
                                                    <xsl:attribute name="src">
                                                        <xsl:value-of select="linkImg"/> 
                                                    </xsl:attribute>    
                                                </img>
                                            </div>
                                            <small class="card-text">
                                                Categorie:                                         
                                                <xsl:value-of select="categorie"/><br/>
                                                Pages:                                         
                                                <xsl:value-of select="pagesNumber"/><br/>
                                                Language:                                         
                                                <xsl:value-of select="language"/><br/>
                                                Rating:                                         
                                                <xsl:value-of select="rating"/> <i class='far fa-star'></i><br/> 
                                            </small>
                                            <small class="card-text">
                                                Seller Rank:                                         
                                                <xsl:value-of select="sellerRank"/><br/>
                                                ISBN10:                                         
                                                <xsl:value-of select="ISBN10"/><br/>
                                                ISBN13:                                         
                                                <xsl:value-of select="ISBN13"/><br/>
                                                Location:                                         
                                                <xsl:value-of select="locationCity"/>,<xsl:value-of select="locationCountry"/>
                                            </small>
                                            <hr></hr>
                                            <h5 class="card-title">
                                                Book Summary
                                            </h5>
                                            <p class="card-text">                                          
                                                <xsl:value-of select="bookSummary"/>
                                            </p>
                                            <p class="card-text">
                                                <small class="text-muted">Published in 
                                                    <xsl:value-of select="publicationDate"/>
                                                    by
                                                    <xsl:value-of select="publisher"/>
                                                </small>
                                            </p>                                        
                                    </div>  
                                  </xsl:for-each>
                                </div>
                            </div>
                        </div>
                    </xsl:for-each>
                </div>
            </body>    
            <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        </html>
    </xsl:template>
</xsl:stylesheet>
