<?xml version='1.0' encoding='utf-8' ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" />

   <xsl:template match="/">
      <xsl:element name="OperacionResponse" namespace="http://selenetelecita.selene.salud.siemens.es/v1/Operations">
         <xsl:element name="OperacionResult" namespace="http://selenetelecita.selene.salud.siemens.es/v1/Operations">
            <xsl:variable name="huecos">
               <xsl:value-of select="count(//*[local-name()='Hueco'])" />
            </xsl:variable>

            <xsl:if test="$huecos=0">
               <xsl:apply-templates select="//*[local-name()='ERROR']" />
            </xsl:if>

            <xsl:if test="$huecos &gt;0">
               <xsl:element name="RespuestaHuecosDisponibles" namespace="http://selenetelecita.selene.salud.siemens.es/v1/respuestaHuecosDisponibles">
                  <xsl:apply-templates select="//*[local-name()='Contexto']" />

                  <xsl:apply-templates select="//*[local-name()='CodigoResultado']" />

                  <xsl:element name="Huecos" namespace="">
                     <xsl:attribute name="fechaYHoraOrigen">
                        <xsl:value-of select="/TOTALRESPONSE/REQUEST[1]/RESULT/*[name()='OperacionResponse']/*[name()='OperacionResult']/*[name()='RespuestaHuecosDisponibles']/*[name()='Huecos']/@fechaYHoraOrigen" />
                     </xsl:attribute>

                     <xsl:apply-templates select="//*[local-name()='Hueco']" />
                  </xsl:element>
               </xsl:element>
            </xsl:if>
         </xsl:element>
      </xsl:element>
   </xsl:template>

   <xsl:template match="*[local-name()='RespuestaHuecosDisponibles']">
   </xsl:template>

   <xsl:template match="*[local-name()='Contexto']">
      <xsl:variable name="contContexto">
         <xsl:value-of select="position()" />
      </xsl:variable>

      <xsl:if test="$contContexto=1">
         <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
            <xsl:apply-templates select="*[local-name()='Aplicacion']" />

            <xsl:apply-templates select="*[local-name()='MensajeID']" />

            <xsl:apply-templates select="*[local-name()='MensajeRaizID']" />

            <xsl:apply-templates select="*[local-name()='Origen']" />
         </xsl:element>
      </xsl:if>
   </xsl:template>

   <xsl:template match="*">
      <xsl:element name="{name(.)}" namespace="">
         <xsl:copy-of select="@*" />

         <xsl:apply-templates />
      </xsl:element>
   </xsl:template>

   <xsl:template match="*[local-name()='CodigoResultado']">
      <xsl:variable name="contCodigoResultado">
         <xsl:value-of select="position()" />
      </xsl:variable>

      <xsl:if test="$contCodigoResultado=1">
         <xsl:element name="{name(.)}" namespace="">
            <xsl:copy-of select="@*" />
         </xsl:element>
      </xsl:if>
   </xsl:template>

   <xsl:template match="*[local-name()='Hueco']">
      <xsl:element name="{name(.)}" namespace="">
         <xsl:apply-templates select="*[local-name()='FechaYHora']" />

         <xsl:apply-templates select="*[local-name()='Agenda']" />
      </xsl:element>
   </xsl:template>

   <xsl:template match="*[local-name()='Agenda']">
      <xsl:element name="{name(.)}" namespace="{namespace-uri(.)}">
         <xsl:copy-of select="@*" />

         <xsl:apply-templates select="*[local-name()='Especialista']" />

         <xsl:apply-templates select="*[local-name()='Servicio']" />

         <xsl:apply-templates select="*[local-name()='Especialidad']" />

         <xsl:apply-templates select="*[local-name()='Prestacion']" />

         <xsl:apply-templates select="*[local-name()='Destino']" />
      </xsl:element>
   </xsl:template>

   <xsl:template match="ERROR">
      <xsl:if test="position() = last()">
         <xsl:element name="RespuestaHuecosDisponibles" namespace="http://selenetelecita.selene.salud.siemens.es/v1/respuestaHuecosDisponibles">
            <xsl:element name="CodigoResultado">
               <xsl:attribute name="codigo">
                  <xsl:value-of select="-1003" />
               </xsl:attribute>

               <xsl:variable name="VERROR" select="../ENDPOINT/text()" />

               <xsl:attribute name="descripcion">
                  <xsl:value-of select="concat(text(), ':Endpoint ',$VERROR)" />
               </xsl:attribute>

               <xsl:attribute name="sistemaCodificacion">
                  <xsl:value-of select="'SELENE'" />
               </xsl:attribute>
            </xsl:element>
         </xsl:element>
      </xsl:if>
   </xsl:template>
</xsl:stylesheet>

