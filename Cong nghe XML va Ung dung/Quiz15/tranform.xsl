<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    
    <xsl:variable name="totalCountry" select="count(/countries/country)"/>
    
    <xsl:template match="countries">
        Total country is <xsl:value-of select="$totalCountry"/>
    </xsl:template>
    <!--<xsl:template match="countries/country">
        <xsl:value-of select="generate-id()"/>
        <xsl:copy-of select="."/>
    </xsl:template>-->
</xsl:stylesheet>
