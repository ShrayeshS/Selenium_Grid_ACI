<#assign Math=statics['java.lang.Math'] >
<#macro drawOverviewStatsTable stats>

<table>
	<tr><th></th><th>Passed</th><th>Failed</th><th>Total</th><th>%Passed</th></tr>
	<tr><th>Test Sets (Features)</th>
		<td class="passed" id="features_passed">${stats.getFeaturesPassed()}</td>
		<td class="failed" id="features_failed">${stats.getFeaturesFailed()}</td>
		<td id="features_total">${stats.getFeaturesTotal()}</td>
		<td id="features_rate">
			<#if stats.getFeaturesTotal() == 0>
			NaN
			<#else>
				#{100 * (stats.getFeaturesPassed() + stats.getFeaturesKnown()) / stats.getFeaturesTotal() ;M0}%
			</#if>
		</td>
	</tr>
	<tr><th>Test Cases (Scenarios)</th>
		<td class="passed" id="scenarios_passed">${stats.getScenariosPassed()}</td>
		<td class="failed" id="scenarios_failed">${stats.getScenariosFailed()}</td>
		<td id="scenarios_total">${stats.getScenariosTotal()}</td>
		<td id="scenarios_rate">
			<#if stats.getScenariosTotal() == 0>
			NaN
			<#else>
				#{100 * (stats.getScenariosPassed() + stats.getScenariosKnown()) / stats.getScenariosTotal() ;M0}%
			</#if>
		</td>
	</tr>
	<tr><th>Test Steps (Steps)</th>
		<td class="passed" id="steps_passed">${stats.getStepsPassed()}</td>
		<td class="failed" id="steps_failed">${stats.getStepsFailed()}</td>
		<td id="steps_total">${stats.getStepsTotal()}</td>
		<td id="steps_rate">
			<#if stats.getStepsTotal() == 0>
			NaN
			<#else>
				#{100 * (stats.getStepsPassed() + stats.getStepsKnown()) / stats.getStepsTotal() ;M0}%
			</#if>
		</td>
	</tr>
</table>
<div><b>Overall Duration: ${(stats.overallDuration/3600)?string["0;; roundingMode=floor"]}h ${((stats.overallDuration % 3600) / 60)?string["00"]}m ${((stats.overallDuration % 3600) % 60)?string["00"]}s</b></div>
</#macro>