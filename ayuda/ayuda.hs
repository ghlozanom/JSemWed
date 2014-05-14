<?xml version='1.0' encoding='ISO-8859-1' ?>
<!DOCTYPE helpset PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 2.0//EN" "http://java.sun.com/products/javahelp/helpset_2_0.dtd">

<helpset version="1.0">
  <title>jsemweb</title>
   <maps>
    <homeID>acerca_de_jsemwed_htm</homeID>
    <mapref location="ayuda_map.xml" />
  </maps>

  <view mergetype="javax.help.UniteAppendMerge">
    <name>TOC</name>
    <label>Tabla de Contenido</label>
    <type>javax.help.TOCView</type>
    <data>ayuda_toc.xml</data>
  </view>

  <view>
    <name>Index</name>
    <label>Índice</label>
    <type>javax.help.IndexView</type>
    <data>ayuda_ndx.xml</data>
  </view>

  <view>
    <name>Search</name>
    <label>Search</label>
    <type>javax.help.SearchView</type>
    <data engine="com.sun.java.help.search.DefaultSearchEngine">ayuda_JavaHelpSearch</data>
  </view>

  <view>
    <name>Glossary</name>
    <label>Glossary</label>
    <type>javax.help.GlossaryView</type>
    <data>ayuda_glo.xml</data>
  </view>

  <view>
    <name>Favorites</name>
    <label>Favorites</label>
    <type>javax.help.FavoritesView</type>
    <data></data>
  </view>


</helpset>
