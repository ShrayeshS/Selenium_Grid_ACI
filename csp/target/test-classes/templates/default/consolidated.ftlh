<#function to_id text>
  <#return text?lower_case?replace("[^a-z0-9]", "-", "r")>
</#function>
<html>
<head><style type="text/css">h1 {background-color:#9999CC;padding-top:5px;padding-bottom:5px;margin-top:30px;margin-bottom:0px;}
h2 {background-color:#BBBBCC;padding-top:5px;padding-bottom:5px;margin-top:30px;margin-bottom:5px;}
h3 {background-color:#DDDDFF}
th {border:1px solid black;background-color:#CCCCDD;}
td{border:1px solid black;}
table {border:1px solid black;border-collapse: collapse;}
.chart {border:0px none black;border-collapse: collapse;background-color: none;}
.passed {background-color:#b5d6a7;font-weight:bold;color:black}
.skipped {background-color:silver;font-weight:bold;color:darkgray}
.failed {background-color:#ff9a9a;font-weight:bold;color:black}
.undefined {background-color:gold;font-weight:bold;color:goldenrod}

.lowest {background-color:lightyellow;color:black}
.low {background-color:gold;color:black}
.medium {background-color:orange;color:black}
.high {background-color:orangered;color:black}
.highest {background-color:darkred;color:black}

.passed_description {background-color:#D0FFD0;font-weight:bold;font-style:italic;color:black}
.failed_description {background-color:#FFA898;font-weight:bold;font-style:italic;color:black}

.noborder {border: none;border-collapse: collapse;vertical-align: top;}

OL { counter-reset: item }
OL>LI { display: block }
OL>LI:before { content: counters(item, ".") " "; counter-increment: item }

div.header {
    display: block; text-align: center;
    position: running(header);
}
div.footer {
    display: block; text-align: center;
    position: running(footer);
}
div.content {page-break-after: always;}
div.content:last-child {page-break-after: avoid;}
@page {
	 size: ${pdfPageSize};
     @top-center {
     	content: "${title}";
	    color: silver;
	    font-size: 14px;
     }
     @top-right {
     	content: date("dd MMM, yyyy hh:mm");
	    color: silver;
	    font-size: 8px;
     }
    @bottom-right {
    	content: "Page " counter(page) " of " counter(pages) ;
	    color: silver;
	    font-size: 8px;
    }
}
ol a::after {
  content: leader('.') target-counter(attr(href), page);
}
</style>
${refreshData}
<title>${title}</title>
<meta charset="UTF-8" />
</head>
  <body>
    <div class='header'></div>
    <div class='footer'></div>
    <#if useTableOfContents>
<h1>Table of Contents</h1>
<ol>
		<#list contents?keys as section_name>
<li><a href="#${to_id(section_name)}">${section_name}</a></li>
		</#list>
</ol>
    </#if>
	<table class="noborder">
    <#assign index=0>
	<#list contents?keys as section_name>
		<#if index % columns == 0>
<tr class="noborder" valigh="top">			
		</#if>
<td class="noborder"><div class="content"><a id="${to_id(section_name)}"><h1>${section_name}</h1></a>
	${contents[section_name]}
</div></td>
		<#if index % columns == (columns - 1)>
</tr>			
		</#if>
	    <#assign index++>
	</#list>
	<#if index % columns != 0>
</tr>			
    </#if>
	</table>
  </body>
</html>