<html>
<head><style type="text/css">
h1 {background-color:#9999CC}
h2 {background-color:#BBBBCC}
h3 {background-color:#DDDDFF}
th {border:1px solid black;background-color:#CCCCDD;}
td{border:1px solid black;}
table {border:1px solid black;border-collapse: collapse;}
.chart {border:0px none black;border-collapse: collapse;background-color: none;}
.passed {background-color:#b5d6a7;font-weight:bold;color:darkgreen}
.skipped {background-color:silver;font-weight:bold;color:darkgray}
.failed {background-color:#ff9a9a ;font-weight:bold;color:darkred}


div.header {
    display: block; text-align: center;
    position: running(header);
}
div.footer {
    display: block; text-align: center;
    position: running(footer);
}
div.content {page-break-after: always;}
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
</style>
<title>${title}</title>
<meta charset="UTF-8" />
</head>
  <body>
    <div class='header'></div>
    <div class='footer'></div>
<#include "pie_chart">
<#include "tables">
  <h1>Overview Chart</h1>
	<table class="chart">
	<tr>
	<th class="chart">Test Sets (Feature) Status</th><th class="chart">Test Cases (Scenario) Status</th>
	</tr>
	<tr class="chart">
    <td class="chart">
	     <@drawPieChart 
	     	id="feature_status"
	     	width=450 height=300
	     	values=[overallStats.getFeaturesPassed(),
	     		 	overallStats.getFeaturesFailed()]
	     		 	labels=["Test Sets Passed", "Test Sets Failed"]
	     	colors=["#399c05", "#ed071e"]
	     	shadowColors=["green", "#b30517"]
	     	pieThickness=20
	     	shift=2 />
	</td>
    <td class="chart">
	     <@drawPieChart 
	     	id="scenario_status"
	     	width=450 height=300
	     	values=[overallStats.getScenariosPassed(),
	     		 	overallStats.getScenariosFailed()]
	     	labels=["Test Cases Passed", "Test Cases Failed"]
	     	colors=["#399c05", "#ed071e"]
	     	shadowColors=["green", "#b30517"]
	     	pieThickness=20
	     	shift=2 />
	</td>
	</tr>
	</table>

<h1>TestSets/TestCases/TestSteps Summary</h1>

<@drawOverviewStatsTable stats=overallStats />
	
<h1>Test Sets (Feature) Status</h1>
	
<table>
	<tr>
		<th>Test Set (Feature) Name</th>
		<th>Test Set Status</th>
		<th>Test Cases Passed</th>
		<th>Test Cases Failed</th>
		<th>Test Cases Total</th>
	</tr>
<#list features as feature>
<#if feature.featureName?has_content>
	<tr class="${feature.status}">
		<td>${feature.featureName}</td>
		<td>${feature.status}</td>
		<td>${feature.stats.scenariosPassed}</td>
		<td>${feature.stats.scenariosFailed}</td>
		<td>${feature.stats.getScenariosTotal()}</td>
	</tr>	
</#if>
</#list>

</table>
	
<h1>Test Cases (Scenario) Status</h1>

<table>
	<tr>
		<th>Test Set (Feature) Name</th>
		<th>Test Case(Scenario)Name</th>
		<th>Test Case Status</th>
		<th>Test Steps Passed</th>
		<th>Test Steps Failed</th>
		<th>Test Steps Total</th>
	</tr>
<#list scenarios as scenario>
	<tr class="${scenario.status}">
		<td>${scenario.featureName}</td>
		<td>${scenario.scenarioName}</td>
		<td>${scenario.status}</td>
		<td>${scenario.stats.stepsPassed}</td>
		<td>${scenario.stats.stepsFailed}</td>
		<td>${scenario.stats.getStepsTotal()}</td>
	</tr>	
</#list>
</table>	
	</body>
</html>