	<!-- Your user authorities -->
	<script type="text/javascript">
		var roles = [<#list roles as r>'${r!}'<#if r_has_next>,</#if></#list>];
		var user = {<#list user?keys as key>${key} : '${user[key]!}'<#if key_has_next>,</#if></#list>};
	</script>  
