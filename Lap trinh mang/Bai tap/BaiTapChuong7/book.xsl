<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="book">
        <html>
            <div style="border: 2px solid black; width: 500px; margin:0 auto; padding:25px; text-align: center;">
                <h1 style="padding:0; margin:0;"><xsl:value-of select="title"/></h1>
                <h3 style="font-weight:normal; padding:0; margin:10px 0;"><xsl:value-of select="edition"/></h3>
                <h3 style="font-weight:normal; padding:0; margin-top:30px; margin-bottom:0;">By</h3>
                <xsl:for-each select="author">
                    <h3 style="padding:0; margin:0;"><xsl:value-of select="."/></h3>
                </xsl:for-each>
                <div style="border: 1px solid black; padding:15px; text-align:left; margin-top:30px;"><xsl:value-of select="desciption"/></div>
            </div>
        </html>
    </xsl:template>
</xsl:stylesheet>
