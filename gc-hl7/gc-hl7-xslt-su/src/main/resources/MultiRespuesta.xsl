<?xml version='1.0' encoding='utf-8' ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" />

   <xsl:template match="/">
      <xsl:element name="MFR_M01" namespace="urn:hl7-org:v2xml">
         <xsl:variable name="MF_QUERYS">
            <xsl:value-of select="count(//*[name()='MFR_M01.MF_QUERY'])" />
         </xsl:variable>

<!--xsl:text>MF_QUERYS:</xsl:text>
       <xsl:value-of select="$MF_QUERYS" /-->
         <xsl:if test="$MF_QUERYS=0">
            <xsl:apply-templates select="//*[name()='ERROR']" />
         </xsl:if>

         <xsl:apply-templates select="//*[name()='MSH']" />

         <xsl:apply-templates select="//*[name()='MSA']" />

         <xsl:apply-templates select="//*[name()='QAK']" />

         <xsl:apply-templates select="//*[name()='QRD']" />

         <xsl:apply-templates select="//*[name()='QRF']" />

         <xsl:apply-templates select="//*[name()='MFR_M01.MF_QUERY']" />
      </xsl:element>
   </xsl:template>

   <xsl:template match="*">
      <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
         <xsl:copy-of select="@*" />

         <xsl:apply-templates />
      </xsl:element>
   </xsl:template>

   <xsl:template match="*[name()='MSH']">
      <xsl:if test="position()=1">
         <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
            <xsl:copy-of select="@*" />

            <xsl:apply-templates />
         </xsl:element>
      </xsl:if>
   </xsl:template>

   <xsl:template match="*[name()='MSA']">
      <xsl:if test="position()=1">
         <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
            <xsl:copy-of select="@*" />

            <xsl:apply-templates />
         </xsl:element>
      </xsl:if>
   </xsl:template>

   <xsl:template match="*[name()='QAK']">
      <xsl:if test="position()=1">
         <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
            <xsl:copy-of select="@*" />

            <xsl:apply-templates />
         </xsl:element>
      </xsl:if>
   </xsl:template>

   <xsl:template match="*[name()='QRD']">
      <xsl:if test="position()=1">
         <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
            <xsl:copy-of select="@*" />

            <xsl:apply-templates />
         </xsl:element>
      </xsl:if>
   </xsl:template>

   <xsl:template match="*[local-name()='QRF']">
      <xsl:if test="position()=1">
         <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
            <xsl:copy-of select="@*" />

            <xsl:apply-templates />
         </xsl:element>
      </xsl:if>
   </xsl:template>

   <xsl:template match="*[name()='MFR_M01.MF_QUERY']">
      <xsl:copy-of select="." />
   </xsl:template>

   <xsl:template match="*[name()='ERROR']">
      <xsl:if test="position() = 1">
         <xsl:element name="MSH" namespace="urn:hl7-org:v2xml">
            <xsl:element name="MSH.2" namespace="urn:hl7-org:v2xml">
               <xsl:value-of select="'^~\&amp;'" />
            </xsl:element>

            <xsl:element name="MSH.3" namespace="urn:hl7-org:v2xml">
               <xsl:element name="HD.1" namespace="urn:hl7-org:v2xml">
                  <xsl:value-of select="'HUP'" />
               </xsl:element>
            </xsl:element>

            <xsl:element name="MSH.4" namespace="urn:hl7-org:v2xml">
               <xsl:element name="HD.1" namespace="urn:hl7-org:v2xml">
                  <xsl:value-of select="'GSM'" />
               </xsl:element>
            </xsl:element>

            <xsl:element name="MSH.5" namespace="urn:hl7-org:v2xml">
               <xsl:element name="HD.1" namespace="urn:hl7-org:v2xml">
                  <xsl:value-of select="'SELENE'" />
               </xsl:element>
            </xsl:element>

            <xsl:element name="MSH.6" namespace="urn:hl7-org:v2xml">
               <xsl:element name="HD.1" namespace="urn:hl7-org:v2xml">
                  <xsl:value-of select="'HL7'" />
               </xsl:element>
            </xsl:element>

            <xsl:element name="MSH.7" namespace="urn:hl7-org:v2xml">
            </xsl:element>

            <xsl:element name="MSH.9" namespace="urn:hl7-org:v2xml">
               <xsl:element name="CM_MSG.2" namespace="urn:hl7-org:v2xml">
                  <xsl:value-of select="'ACK'" />
               </xsl:element>
            </xsl:element>

            <xsl:element name="MSH.10" namespace="urn:hl7-org:v2xml">
            </xsl:element>

            <xsl:element name="MSH.11" namespace="urn:hl7-org:v2xml">
            </xsl:element>

            <xsl:element name="MSH.12" namespace="urn:hl7-org:v2xml">
               <xsl:element name="VID.1" namespace="urn:hl7-org:v2xml">
                  <xsl:value-of select="'2.5'" />
               </xsl:element>
            </xsl:element>

            <xsl:element name="MSH.13" namespace="urn:hl7-org:v2xml">
               <xsl:value-of select="1" />
            </xsl:element>

            <xsl:element name="MSH.16" namespace="urn:hl7-org:v2xml">
               <xsl:value-of select="'AL'" />
            </xsl:element>

            <xsl:element name="MSH.18" namespace="urn:hl7-org:v2xml">
               <xsl:value-of select="'ASCII'" />
            </xsl:element>
         </xsl:element>

         <xsl:element name="MSA" namespace="urn:hl7-org:v2xml">
            <xsl:element name="MSA.1" namespace="urn:hl7-org:v2xml">
               <xsl:value-of select="'AE'" />
            </xsl:element>
            <xsl:element name="MSA.3" namespace="urn:hl7-org:v2xml">
               <xsl:value-of select="//REQUEST[last()]//ERROR/text()" />
            </xsl:element>
         </xsl:element>
      </xsl:if>

      <xsl:if test="position() != last()">
         <xsl:element name="ERR" namespace="urn:hl7-org:v2xml">
            <xsl:variable name="VERROR" select="../ENDPOINT/text()" />

            <xsl:element name="ERR.1" namespace="urn:hl7-org:v2xml">
               <xsl:element name="CM_ELD.4" namespace="urn:hl7-org:v2xml">
                  <xsl:element name="CE.1" namespace="urn:hl7-org:v2xml">
                     <xsl:value-of select="1000" />
                  </xsl:element>

                  <xsl:element name="CE.2" namespace="urn:hl7-org:v2xml">
                     <xsl:value-of select="concat(text(), ':Endpoint ',$VERROR)" />
                  </xsl:element>
               </xsl:element>
            </xsl:element>
         </xsl:element>
      </xsl:if>
   </xsl:template>
</xsl:stylesheet>

